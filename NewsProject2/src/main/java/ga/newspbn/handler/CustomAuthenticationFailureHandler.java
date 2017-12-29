/**
 * 
 */
package ga.newspbn.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/* 인증 실패 핸들러
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	private String loginidname; //로그인 id 값이 들어오는 input 태그 name
	private String loginpasswordname; //로그인 pw 값이 들어오는 input 태그 name
	private String loginredirectname; //로그인 성공시 redirect 할 URL이 지정되어 있는 input 태그 name
	private String exceptionmsgname; //예외 메시지를 request의 attribute에 저장할 때 사용될 key
	private String defaultFailureUrl; //화면에 보여줄 URL(로그인 화면)
	
	
	
	/**
	 * @param loginidname
	 * @param loginpasswordname
	 * @param loginredirectname
	 * @param exceptionmsgname
	 * @param defaultFileureUrl
	 */
	public CustomAuthenticationFailureHandler() {
		this.loginidname = "j_username";
		this.loginpasswordname = "j_password";
		this.loginredirectname = "loginRedirect";
		this.exceptionmsgname = "securityexceptionmsg";
		this.defaultFailureUrl = "/login";
	}



	public String getLoginidname() {
		return loginidname;
	}



	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}



	public String getLoginpasswordname() {
		return loginpasswordname;
	}



	public void setLoginpasswordname(String loginpasswordname) {
		this.loginpasswordname = loginpasswordname;
	}



	public String getLoginredirectname() {
		return loginredirectname;
	}



	public void setLoginredirectname(String loginredirectname) {
		this.loginredirectname = loginredirectname;
	}



	public String getExceptionmsgname() {
		return exceptionmsgname;
	}



	public void setExceptionmsgname(String exceptionmsgname) {
		this.exceptionmsgname = exceptionmsgname;
	}



	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}



	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}



	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		//Request 객체의 Attribute에 사용자가 실패시 입력했던 로그인 ID와 비밀번호를 저장해 두어 로그인 페이지에서 이를 접근하도록 한다.
		String uid = request.getParameter(loginidname);
		String upw = request.getParameter(loginpasswordname);
		String loginRedirect = request.getParameter(loginredirectname);
//		System.out.println("loginid :"+uid + " / loginpwd : "+upw);
		System.out.println("loginRedirect : "+loginRedirect );
//		System.out.println("세션  : "+request.getSession(false));
		request.setAttribute(loginidname, uid);
		request.setAttribute(loginpasswordname, upw);
		request.setAttribute(loginredirectname, loginRedirect);
		//Request 객체의 Attribute 에 예외 메시지 저장
		request.setAttribute(exceptionmsgname, exception.getMessage());
		
		//인증을 실패했기 때문에 왜 실패했는지 메시지를 보여줘야 하는데 이 메시지 또한 setAttribute 메소드를 이용해서
		//메시지를 세팅해주어 나중에 보여줄 페이지에서 jstl 을 이용해 그대로 보여줄 수 있다. 마지막으로 httpServletRequest클래스의
		//getRequestDispatcher 메소드를 이용해서 보여줘야할 화면으로 forward 해준다.그래야 jstl을 이용해 setAttribute로 저장한 값을 가져올 수 있다.(forward 특성상 유지)
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
		
	}

}
