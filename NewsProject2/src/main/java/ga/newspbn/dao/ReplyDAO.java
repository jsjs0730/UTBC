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
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(Integer rnum) throws Exception;
	
	public List<ReplyVO> listPage(Integer bnum, SearchCriteria cri) throws Exception;
	public int count(Integer bnum) throws Exception;
	
	public void createRe(ReplyVO vo) throws Exception;
	public Integer maxGrp(ReplyVO vo) throws Exception;
	public Integer chkGrp(ReplyVO vo) throws Exception;
	public void updateGrp(ReplyVO vo) throws Exception;
	public int getBnum(Integer rnum) throws Exception;
}
