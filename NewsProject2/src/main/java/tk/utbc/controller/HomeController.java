package tk.utbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tk.utbc.service.BoardService;
import tk.utbc.util.GetParse;
import tk.utbc.vo.Criteria;
import tk.utbc.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	MessageSource messageSource;
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("cri")Criteria cri, Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();
		logger.info("지금 접속한 자의 IP : "+ip);
		
		System.out.println("ip의 데이터 타입 : "+ip.getClass().getName());
		
		int vlike = 1;
	
		
		model.addAttribute("member", new MemberVO());
		model.addAttribute("slide", service.listSlide(vlike));
		model.addAttribute("list", service.listHomePage(cri));
		model.addAttribute("favorite", service.listFavorite(vlike));
		return "home";
	}
	@ResponseBody
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public Map<String, Object> weatherPage() throws Exception{
		GetParse gp = new GetParse();
		List<Map> wt = gp.getWeather();
		Map<String, Object> result = new HashMap<>();
		result.put("weather", wt);
		return result;
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGET(Model model) {
		/*@RequestHeader를 이용해 REFERER를 받아올 수 있으나
		access가 정해진 페이지에서 처리하는 정적인 referer와 충돌하므로
		동적인 referer는 따로 처리를 한다.*/
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String referer = req.getHeader("REFERER");
		if(referer != "") {
			model.addAttribute("loginRedirect", referer);
		}else {
			model.addAttribute("loginRedirect", "http://localhost/");
		}
		return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPOST() {
		Locale locale = LocaleContextHolder.getLocale();
		String testmsg1 = messageSource.getMessage("AbstractLdapAuthenticationProvider.badCredentials", null, "시큐리티 디폴트 메시지", Locale.KOREA);
		String testmsg2 = messageSource.getMessage("TestKey.testParam", null, "디폴트 메시지", Locale.KOREA);
		
		logger.debug("testmsg1 : {}", testmsg1);
		logger.debug("testmsg2 : {}", testmsg2);
		return "login";
	}
	
}
