/**
 * 
 */
package tk.utbc.dao;

import java.util.List;

import tk.utbc.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public interface MemberDAO {
	public void insertUser(MemberVO vo) throws Exception;
	public void insertAuthority(String uid, String Authority) throws Exception;
	public void insertUserPoint(String uid) throws Exception;
	public int checkUser(MemberVO vo) throws Exception;
	public int writedBoardCnt(String uname) throws Exception;
	public int writedReplyCnt(String uname) throws Exception;
	public int getMyPoint(String uname) throws Exception;
	
	
	public void dropPointLog(String uname) throws Exception;
	public void dropPoint(String uname) throws Exception;
	public void dropAuthority(String uname) throws Exception;
	public void dropout(String uname) throws Exception;
	
}
