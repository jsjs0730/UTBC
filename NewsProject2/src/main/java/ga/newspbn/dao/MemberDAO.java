/**
 * 
 */
package ga.newspbn.dao;

import ga.newspbn.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public interface MemberDAO {
	public void insertUser(MemberVO vo) throws Exception;
	public void insertAuthority(String uid, String Authority) throws Exception;
	public void insertUserPoint(String uid) throws Exception;
	public int checkUser(MemberVO vo) throws Exception;
}
