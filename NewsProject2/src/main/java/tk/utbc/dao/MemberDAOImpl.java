/**
 * 
 */
package tk.utbc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tk.utbc.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace="tk.utbc.mapper.memberMapper.";
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertUser(MemberVO vo) throws Exception {
		sqlSession.insert(namespace+"createUser", vo);
	}

	@Override
	public int checkUser(MemberVO vo) throws Exception {
		if(vo.getUid() != null) {
			return sqlSession.selectOne(namespace+"checkUid", vo);
		}
		else{
			return sqlSession.selectOne(namespace+"checkUname", vo);
		}
	}

	@Override
	public void insertAuthority(String uid, String authority) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("authority", authority);
		sqlSession.insert(namespace+"createAuthority", paramMap);
		
	}

	@Override
	public void insertUserPoint(String uid) throws Exception {
		sqlSession.insert(namespace+"createUserPoint", uid);
	}

	@Override
	public int writedBoardCnt(String uname) throws Exception {
		return sqlSession.selectOne(namespace+"writedBoardCnt", uname);
	}

	@Override
	public int writedReplyCnt(String uname) throws Exception {
		return sqlSession.selectOne(namespace+"writedReplyCnt", uname);
	}

	@Override
	public int getMyPoint(String uname) throws Exception {
		return sqlSession.selectOne(namespace + "getMyPoint", uname);
	}

	
	
	@Override
	public void dropPointLog(String uname) throws Exception {
		sqlSession.delete(namespace + "dropPointLog", uname);
	}

	@Override
	public void dropPoint(String uname) throws Exception {
		sqlSession.delete(namespace + "dropPoint", uname);
	}

	@Override
	public void dropAuthority(String uname) throws Exception {
		sqlSession.delete(namespace + "dropAuthority", uname);
	}

	@Override
	public void dropout(String uname) throws Exception {
		sqlSession.delete(namespace + "dropout", uname);
		
	}

	@Override
	public String findMyId(String email) throws Exception {
		return sqlSession.selectOne(namespace+"findMyId", email);
		
	}

	@Override
	public String findMyPwd(String uid, String email) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("email", email);
		return sqlSession.selectOne(namespace + "findMyPwd", paramMap);
	}

	@Override
	public void updatePwd(String uid, String encodePwd) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("encodePwd", encodePwd);
		sqlSession.update(namespace + "updatePwd", paramMap);
	}

	@Override
	public void updateUserProfile(MemberVO vo) throws Exception {
		sqlSession.update(namespace+"updateUserProfile", vo);
	}
	
}
