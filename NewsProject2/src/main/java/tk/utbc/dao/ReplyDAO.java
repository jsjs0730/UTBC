/**
 * 
 */
package tk.utbc.dao;

import java.util.List;

import tk.utbc.vo.ReplyVO;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface ReplyDAO {
	//부모댓글
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(Integer rnum) throws Exception;
	public void replyNotDelete(Integer rnum) throws Exception;
	
	//입력하고 글번호 가져와서 저장
	public int getRnum() throws Exception;
	
	//생성 전 depth 조정
	public void updateIdxAndDepth(ReplyVO vo) throws Exception;
	
	//대댓글 생성전 depth중 최대치 구하기
	public Integer getMaxDepth(ReplyVO vo) throws Exception;
	
	//대댓글 생성
	public void createReReply(ReplyVO vo) throws Exception;
	
	
	
	//댓글목록
	public List<ReplyVO> listPage(Integer bnum, SearchCriteria cri) throws Exception;
	//댓글 수
	public int count(Integer bnum) throws Exception;
	//댓글이 속한 게시물 번호
	public int getBnum(Integer rnum) throws Exception;
}
