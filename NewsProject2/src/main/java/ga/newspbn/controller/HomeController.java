package ga.newspbn.controller;

import java.util.Locale;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ga.newspbn.service.BoardService;
import ga.newspbn.util.GetPharse;
import ga.newspbn.vo.Criteria;

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
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("cri")Criteria cri, Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();
		logger.info("지금 접속한 자의 IP : "+ip);
		
		System.out.println("ip의 데이터 타입 : "+ip.getClass().getName());
		GetPharse gp = new GetPharse();
		//gp.getSuyongso();
		
		if(!locale.toString().equals("ko_KR") && !locale.toString().equals("ko")) {
			System.out.println("센족짱개 새끼는 날리는거임");
			return "redirect:https://www.naver.com";
		}
		if(!ip.equals("127.0.0.1") && !ip.equals("0:0:0:0:0:0:0:1")) {
			System.out.println("ip가 다른 모르는 새끼는 날리는거임");
			return "redirect:https://www.naver.com";
		}
		
		model.addAttribute("list", service.listHomePage(cri));
		
		return "home";
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
