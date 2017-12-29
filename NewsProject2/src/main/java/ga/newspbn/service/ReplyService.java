/**
 * 
 */
package ga.newspbn.service;

import java.util.List;

import ga.newspbn.vo.ReplyVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public interface ReplyService {
	public void addReply(ReplyVO vo) throws Exception;
	public void modifyReply(ReplyVO vo) throws Exception;
	public void removeReply(Integer rnum) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer bnum, SearchCriteria cri) throws Exception;
	public int count(Integer bnum) throws Exception;
	
	public void createRereply(ReplyVO vo) throws Exception;
	public Integer maxRereply(ReplyVO vo) throws Exception;
	public Integer checkRereply(ReplyVO vo) throws Exception;
	public void updateRereply(ReplyVO vo) throws Exception;
}
