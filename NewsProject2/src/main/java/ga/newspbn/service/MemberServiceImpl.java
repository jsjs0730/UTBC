/**
 * 
 */
package ga.newspbn.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ga.newspbn.dao.MemberDAO;
import ga.newspbn.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void createUser(MemberVO vo) throws Exception {
		dao.insertUser(vo);
	}
	
	@Override
	public void createAuthority(String uid, String authority) throws Exception {
		dao.insertAuthority(uid, authority);
	}

	@Override
	public int chkUser(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkUser(vo);
	}

	@Override
	public void createUserPoint(String uid) throws Exception {
		dao.insertUserPoint(uid);
	}
	
	
}
