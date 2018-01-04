/**
 * 
 */
package ga.newspbn.dao;

import java.util.List;

import ga.newspbn.vo.BoardVO;
import ga.newspbn.vo.Criteria;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface BoardDAO {
	public List<BoardVO> homeList(Criteria cri) throws Exception;
	public List<BoardVO> homeListSlide(int vlike) throws Exception;
	public List<BoardVO> homeListFavorite(int vlike) throws Exception;
	public void insertBoard(BoardVO vo) throws Exception;
	public BoardVO read(Integer bnum) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(Integer bnum) throws Exception;
	public void deleteAllReply(Integer bnum) throws Exception;
	//페이징
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	//댓글
	public void updateReplyCnt(Integer bnum, int amount) throws Exception;
	public void updateViewCnt(Integer bnum) throws Exception;
	
	//업로드 관련
	public void addAttach(String fullName) throws Exception;
	public List<String> getAttach(Integer bnum) throws Exception;
	public void deleteAttach(Integer bnum) throws Exception;
	public void replaceAttach(String fullName, Integer bnum) throws Exception;
	
	//추천 - 관련
	public List<BoardVO> voteResult(Integer bnum) throws Exception;
}
