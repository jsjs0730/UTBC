/**
 * 
 */
package ga.newspbn.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ga.newspbn.vo.MemberVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace="ga.newspbn.mapper.memberMapper.";
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

	

}
