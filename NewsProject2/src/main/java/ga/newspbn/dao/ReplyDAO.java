/**
 * 
 */
package ga.newspbn.dao;

import java.util.List;

import ga.newspbn.vo.ReplyVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface ReplyDAO {
	//부모댓글
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(Integer rnum) throws Exception;
	
	//대댓글
	//생성 전 sequence 조정
	
	//대댓글 생성
	public void createReReply(ReplyVO vo) throws Exception;
	
	
	//댓글목록
	public List<ReplyVO> listPage(Integer bnum, SearchCriteria cri) throws Exception;
	//댓글 수
	public int count(Integer bnum) throws Exception;
	//댓글이 속한 게시물 번호
	public int getBnum(Integer rnum) throws Exception;
}
