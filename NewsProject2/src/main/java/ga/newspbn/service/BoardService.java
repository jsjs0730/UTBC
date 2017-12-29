/**
 * 
 */
package ga.newspbn.service;

import java.util.List;

import ga.newspbn.vo.BoardVO;
import ga.newspbn.vo.Criteria;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface BoardService {
	
	public List<BoardVO> listHomePage(Criteria cri) throws Exception;
	
	public void insert(BoardVO vo) throws Exception; //insertBoard
	public BoardVO read(Integer bnum) throws Exception; //read
	public void modify(BoardVO vo) throws Exception; //update + file modify
	public void remove(Integer bnum) throws Exception; //delete + deleteAllReply
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public List<String> getAttach(Integer bnum) throws Exception;
	
}