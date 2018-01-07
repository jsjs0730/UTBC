/**
 * 
 */
package tk.utbc.service;

import java.util.List;

import tk.utbc.vo.BoardVO;
import tk.utbc.vo.Criteria;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface BoardService {
	
	public List<BoardVO> listHomePage(Criteria cri) throws Exception;
	public List<BoardVO> listSlide(int vlike) throws Exception;
	public List<BoardVO> listFavorite(int vlike) throws Exception;
	public void insert(BoardVO vo) throws Exception; //insertBoard
	public BoardVO read(Integer bnum) throws Exception; //read
	public void modify(BoardVO vo) throws Exception; //update + file modify
	public void remove(Integer bnum) throws Exception; //delete + deleteAllReply
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public List<String> getAttach(Integer bnum) throws Exception;
	
	public List<BoardVO> getVoteResult(Integer bnum) throws Exception;
}
