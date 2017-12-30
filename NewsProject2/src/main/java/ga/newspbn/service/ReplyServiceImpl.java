/**
 * 
 */
package ga.newspbn.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.newspbn.dao.BoardDAO;
import ga.newspbn.dao.ReplyDAO;
import ga.newspbn.vo.ReplyVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Inject
	private BoardDAO bdao;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
		bdao.updateReplyCnt(vo.getBnum(), 1); //댓글이 추가 되면 카운트를 올림
	}
	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);		
	}
	@Transactional
	@Override
	public void removeReply(Integer rnum) throws Exception {
		int bnum = dao.getBnum(rnum); //댓글이 위치한 게시물 번호를 가져옴
		dao.delete(rnum);
		bdao.updateReplyCnt(bnum, -1); //댓글이 삭제 되면 카운트를 한개 내림
	}
	@Override
	public List<ReplyVO> listReplyPage(Integer bnum, SearchCriteria cri) throws Exception {
		return dao.listPage(bnum, cri);
	}
	@Override
	public int count(Integer bnum) throws Exception {
		return dao.count(bnum);
	}


}
