/**
 * 
 */
package tk.utbc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tk.utbc.service.MailService;
import tk.utbc.service.MemberService;
import tk.utbc.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger =LoggerFactory.getLogger(MemberController.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//form data 전송시 datetype 에러가 생긴다
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	@Inject
	private MemberService service;

	@SuppressWarnings("unused")
	@Inject
	private MailService mailService;
	
	@RequestMapping("/terms")
	public String terms() throws Exception{
		return "/member/terms";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String createUserGET() throws Exception{
		logger.info("회원 등록을 시도함.....get");
		return "/member/signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String createUserPOST(MemberVO vo) throws Exception{
		vo.setUpw(BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt(10)));
		logger.info("회원 등록을 시도함.....post");
		logger.info(vo.toString());
		//트랜잭션을 사용해도 무관
		//회원정보
		service.createUser(vo);
		//아이디 : 권한
		service.createAuthority(vo.getUid(), vo.getAuth());
		//회원포인트 테이블
		service.createUserPoint(vo.getUid());
		return "redirect:/";
	}

	//닉 또는 아이디 중복 검사
	@ResponseBody
	@RequestMapping(value="/chkUser")
	public String checkUser(MemberVO vo) throws Exception {
		int cnt = service.chkUser(vo);
		return String.valueOf(cnt);
	}
	
	/*@RequestMapping(value="/passwordEncoder", method= {RequestMethod.GET, RequestMethod.POST})
	public String passwordEncoder(@RequestParam(value="targetStr", required=false, defaultValue="") String targetStr, Model model){
		if(StringUtils.hasText(targetStr)) {
			//암호화 작업
			String bCryptString = passwordEncoder.encode(targetStr);
			model.addAttribute("targetStr", targetStr);
			model.addAttribute("bCryptString", bCryptString);
		}
		return "/common/showBCryptString";
	}*/
	@ResponseBody
	@RequestMapping(value="/{uname}", method=RequestMethod.GET)
	public Map<String, Object> userStat(@PathVariable("uname") String uname) throws Exception{
		Map<String, Object> ult = service.getStat(uname);
		Map<String, Object> result = new HashMap<>();
		result.put("stat", ult);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/{uname}", method=RequestMethod.DELETE)
	public Map<String, Object> userDropout(@PathVariable("uname") String uname) throws Exception{
		service.dropout(uname);
		Map<String, Object> result = new HashMap<>();
		result.put("code", "success");
		return result;
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find() {
		return "/member/find";
	}
	
	@RequestMapping(value="/find/id", method=RequestMethod.POST)
	public String findMyIdPOST(@RequestParam String email, RedirectAttributes rttr) throws Exception{
		logger.info("email 검사 : "+email);
		Map<String,Object> result = new HashMap<>();
		String id = service.findMyId(email);
		if(id != null) {
			String title = "UTBC 아이디 찾기 안내 메일 입니다.";
			StringBuilder sb = new StringBuilder();
			sb.append("귀하의 아이디는 " + id + " 입니다.");
			mailService.send(title, sb.toString(), "bakamono56789@yahoo.co.jp", email, null);
			result.put("code", "success");
			result.put("message", "귀하의 이메일 주소로 아이디가 전송될 것입니다.");
		}else {
			result.put("code", "danger");
			result.put("message", "입력하신 이메일의 가입된 아이디가 존재하지 않습니다.");
		}
		rttr.addFlashAttribute("result", result);
		return "redirect:/member/find";
	}
	
	@RequestMapping(value="/find/password", method=RequestMethod.POST)
	public String findMyPasswordPOST(@RequestParam String uid, @RequestParam String email, RedirectAttributes rttr) throws Exception{
		logger.info("uid : " + uid + "// email : " + email);
		Map<String,Object> result = new HashMap<>();
		
		String id = service.findMyPwd(uid, email); //간단한 ID, 이메일 검증
		if(id != null) {
			int ran = new Random().nextInt(500000) + 10000; //10000 ~ 499999
			
			String newPwd = String.valueOf(ran);//일종의 rawPwd 생성
			String encodePwd = BCrypt.hashpw(newPwd, BCrypt.gensalt(10)); //DB에 저장할 때는 encode
			service.updatePwd(id, encodePwd); //DB
			
			String title = "UTBC 임시 비밀번호 안내 메일 입니다.";
			StringBuilder sb = new StringBuilder();
			sb.append("귀하의 임시비밀 번호 : " + newPwd);
			mailService.send(title, sb.toString(), "bakamono56789@yahoo.co.jp", email, null);
			result.put("code", "success");
			result.put("message", "귀하의 이메일 주소로 임시 비밀번호가 전송될 것입니다.");
		}else {
			result.put("code", "danger");
			result.put("message", "입력하신 아이디 또는 이메일 정보로 가입된 아이디가 존재하지 않습니다.");
		}
		rttr.addFlashAttribute("result", result);
		return "redirect:/member/find";
	}
}
