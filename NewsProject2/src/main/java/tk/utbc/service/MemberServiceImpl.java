/**
 * 
 */
package tk.utbc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.utbc.dao.MemberDAO;
import tk.utbc.vo.MemberVO;

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

	@SuppressWarnings("null")
	@Transactional
	@Override
	public Map<String, Object> getStat(String uname) throws Exception {
		Map<String, Object> userProfile = new HashMap<>();
		int point = dao.getMyPoint(uname);
		int writedBoardCnt = dao.writedBoardCnt(uname);
		int writedReplyCnt = dao.writedReplyCnt(uname);
		userProfile.put("point", point);
		userProfile.put("writedBoardCnt", writedBoardCnt);
		userProfile.put("writedReplyCnt", writedReplyCnt);
		return userProfile;
	}

	@Transactional
	@Override
	public void dropout(String uname) throws Exception {
		dao.dropPointLog(uname);
		dao.dropPoint(uname);
		dao.dropAuthority(uname);
		dao.dropout(uname);
	}

	@Override
	public String findMyId(String email) throws Exception {
		return dao.findMyId(email);
	}
	
	
	
}
