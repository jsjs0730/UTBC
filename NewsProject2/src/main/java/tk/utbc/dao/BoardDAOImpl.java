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

import tk.utbc.vo.BoardVO;
import tk.utbc.vo.Criteria;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace = "tk.utbc.mapper.boardMapper.";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> homeList(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace+"listHome", cri);
	}

	@Override
	public List<BoardVO> homeListSlide(int vlike) throws Exception {
		return sqlSession.selectList(namespace+"listSlide", vlike);
	}

	@Override
	public List<BoardVO> homeListFavorite(int vlike) throws Exception {
		return sqlSession.selectList(namespace+"listFavorite", vlike);
	}

	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		sqlSession.insert(namespace+"insertBoard", vo);

	}
	



	@Override
	public BoardVO read(Integer bnum) throws Exception {
		
		return sqlSession.selectOne(namespace+"read", bnum);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		
		sqlSession.update(namespace+"update", vo);
	}

	@Override
	public void delete(Integer bnum) throws Exception {
	
		sqlSession.delete(namespace+"delete", bnum);
	}

	@Override
	public void deleteAllReply(Integer bnum) throws Exception {
		sqlSession.delete(namespace+"deleteAllReply", bnum);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bnum, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bnum", bnum);
		paramMap.put("amount", amount);
		sqlSession.update(namespace+"updateReplyCnt", paramMap);
		
	}

	@Override
	public void updateViewCnt(Integer bnum) throws Exception {
		sqlSession.update(namespace+"updateViewCnt", bnum);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		sqlSession.insert(namespace+"addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bnum) throws Exception {
		return sqlSession.selectList(namespace+"getAttach", bnum);
	}

	@Override
	public void deleteAttach(Integer bnum) throws Exception {
		sqlSession.delete(namespace+"deleteAttach", bnum);
	}
	
	@Override
	public void replaceAttach(String fullName, Integer bnum) throws Exception{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bnum", bnum);
		paramMap.put("fullName", fullName);
		
		sqlSession.insert(namespace+"replaceAttach", paramMap);
	}

	@Override
	public List<BoardVO> voteResult(Integer bnum) throws Exception {
		return sqlSession.selectList(namespace+"getVoteResult", bnum);
	}

}
