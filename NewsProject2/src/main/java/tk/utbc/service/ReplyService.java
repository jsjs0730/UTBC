/**
 * 
 */
package tk.utbc.service;

import java.util.List;

import tk.utbc.vo.ReplyVO;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface ReplyService {
	public void addReply(ReplyVO vo) throws Exception;
	public void modifyReply(ReplyVO vo) throws Exception;
	public void removeReply(Integer rnum) throws Exception;
	
	public int getRnum() throws Exception;
	public void updateIdxAndDepth(ReplyVO vo) throws Exception;
	public void createReReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer bnum, SearchCriteria cri) throws Exception;
	public int count(Integer bnum) throws Exception;
	
}
