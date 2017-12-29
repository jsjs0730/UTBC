/**
 * 
 */
package ga.newspbn.service;

import ga.newspbn.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public interface MemberService {
	public void createUser(MemberVO vo) throws Exception;
	public void createAuthority(String uid, String authority) throws Exception;
	public void createUserPoint(String uid) throws Exception;
	public int chkUser(MemberVO vo) throws Exception;
	
}