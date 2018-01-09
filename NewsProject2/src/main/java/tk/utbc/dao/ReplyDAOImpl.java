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

import tk.utbc.vo.ReplyVO;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "tk.utbc.mapper.ReplyMapper.";
	@Override
	public void create(ReplyVO vo) throws Exception {
//		vo.setIdx(vo.getIdx()+1);
//		vo.setDepth(String.valueOf((vo.getIdx()+1)));
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
	public void replyNotDelete(Integer rnum) throws Exception {
		session.update(namespace+"replyNotDelete", rnum); 
	}

	@Override
	public int getRnum() throws Exception {
		return session.selectOne(namespace + "getRnum");
	}


	@Override
	public void updateIdxAndDepth(ReplyVO vo) throws Exception {
		session.update(namespace + "updateIdxAndDepth", vo);
	}
	
	
	
	@Override
	public Integer getMaxDepth(ReplyVO vo) throws Exception {
		return session.selectOne(namespace + "getMaxDepth", vo);
	}


	@Override
	public void createReReply(ReplyVO vo) throws Exception {
		session.insert(namespace+"createReReply", vo);
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
	public int getBnum(Integer rnum) throws Exception {
		return session.selectOne(namespace+"getBnum", rnum);
	}
	
	
}
