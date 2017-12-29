/**
 * 
 */
package ga.newspbn.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ga.newspbn.vo.ReplyVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "ga.newspbn.mapper.ReplyMapper.";
	@Override
	public void create(ReplyVO vo) throws Exception {
		
		session.insert(namespace+"create", vo);
	}
	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace+"update", vo);
	}

	@Override
	public void delete(Integer rnum) throws Exception {
		session.delete(namespace+"delete", rnum);
	}

	@Override
	public List<ReplyVO> listPage(Integer bnum, SearchCriteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bnum", bnum);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace+"listPage", paramMap);
	}

	@Override
	public int count(Integer bnum) throws Exception {
		return session.selectOne(namespace+"count", bnum);
	}
	@Override
	public void createRe(ReplyVO vo) throws Exception {
		session.insert(namespace+"createRereply", vo);
	}
	@Override
	public Integer maxGrp(ReplyVO vo) throws Exception {
		return session.selectOne(namespace+"maxGrp", vo);		
	}
	@Override
	public Integer chkGrp(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+"checkGrp", vo);
	}
	@Override
	public void updateGrp(ReplyVO vo) throws Exception {
		session.update(namespace+"updateRereply", vo);		
	}
	@Override
	public int getBnum(Integer rnum) throws Exception {
		return session.selectOne(namespace+"getBnum", rnum);
	}
	
	
}
