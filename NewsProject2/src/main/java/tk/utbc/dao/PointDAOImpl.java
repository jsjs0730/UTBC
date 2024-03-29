/**
 * 
 */
package tk.utbc.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tk.utbc.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Repository
public class PointDAOImpl implements PointDAO {
	private static final String namespace="tk.utbc.mapper.pointMapper.";
	
	@Inject
	private SqlSession sqlSession;

	
	@Override
	public int chkVoteCount(PointCycleLogVO pclvo) throws Exception {
		return sqlSession.selectOne(namespace+"chkVoteCount", pclvo);
	}

	@Override
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception {
		sqlSession.insert(namespace+"voteBoardPoint", pclvo);
	}

	
	@Override
	public void updateVote(int bnum, int vlike, int dislike) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bnum", bnum);
		paramMap.put("vlike", vlike);
		paramMap.put("dislike",dislike);
		sqlSession.update(namespace+"updateVote", paramMap);
	}

	@Override
	public String chkUid(String usernick) throws Exception {
		return sqlSession.selectOne(namespace+"chkuid", usernick);
	}
/*	@Override
	public int calculatingPoint(String chk) throws Exception {
		return sqlSession.selectOne(namespace+"calculatingPoint", chk);
	}*/
	@Override
	public void updatePoint(String uid, int ipoint) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("point", ipoint);
		sqlSession.update(namespace+"updatePoint", paramMap);
	}

	@Override
	public void deletePointLog(PointCycleLogVO pclvo) throws Exception {
		sqlSession.delete(namespace+"deleteBoardPointLog", pclvo);
	}

	@Override
	public void deleteReplyPointLog(PointCycleLogVO pclvo) throws Exception {
		sqlSession.delete(namespace+"deleteReplyPointLog", pclvo);
	}

	@Override
	public String chkUsernick(int bnum) throws Exception {
		return sqlSession.selectOne(namespace+"chkunameForBoard", bnum);
	}

	@Override
	public String chkUsernickForReply(int rnum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"chkunameForReply", rnum);
	}
	
	

}
