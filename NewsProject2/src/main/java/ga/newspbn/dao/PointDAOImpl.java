/**
 * 
 */
package ga.newspbn.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ga.newspbn.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Repository
public class PointDAOImpl implements PointDAO {
	private static final String namespace="ga.newspbn.mapper.pointMapper.";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception {
		sqlSession.insert(namespace+"voteBoardPoint", pclvo);
	}

	@Override
	public void voteBoardPointDis(PointCycleLogVO pclvo) throws Exception {
		sqlSession.insert(namespace+"voteBoardPointDis", pclvo);
	}
	@Override
	public String chkUid(String usernick) throws Exception {
		return sqlSession.selectOne(namespace+"chkuid", usernick);
	}
	@Override
	public int calculatingPoint(String chk) throws Exception {
		return sqlSession.selectOne(namespace+"calculatingPoint", chk);
	}
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
	public String chkUsernick(int bnum) throws Exception {
		return sqlSession.selectOne(namespace+"chkuname", bnum);
	}
	
	

}
