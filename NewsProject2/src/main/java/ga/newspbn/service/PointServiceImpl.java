/**
 * 
 */
package ga.newspbn.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ga.newspbn.dao.PointDAO;
import ga.newspbn.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Service
public class PointServiceImpl implements PointService {
		
	@Inject
	PointDAO pdao;
	private static final Logger logger = LoggerFactory.getLogger(PointServiceImpl.class);

	
	
	@Override
	public int chkVoteCount(PointCycleLogVO pclvo) throws Exception {
		return pdao.chkVoteCount(pclvo);
	}
	@Override
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception {
		pdao.voteBoardPoint(pclvo);
	}
/*	@Override
	public int calculatingPoint(String chk) throws Exception {
		return pdao.calculatingPoint(chk);
	}*/
	
	@Override
	public void updateVote(int bnum, int like, int dislike) throws Exception {
		pdao.updateVote(bnum, like, dislike);
	}
	
	@Override
	public void updatePoint(String uid, int ipoint) throws Exception {
		pdao.updatePoint(uid, ipoint);
	}
	@Override
	public String chkUid(String usernick) throws Exception {
		return pdao.chkUid(usernick);
	}
	@Override
	public void deleteBoardPointLog(PointCycleLogVO pclvo) throws Exception {
		pdao.deletePointLog(pclvo);
	}
	@Override
	public void deleteReplyPointLog(PointCycleLogVO pclvo) throws Exception {
		pdao.deleteReplyPointLog(pclvo);
	}
	@Override
	public String chkUsernick(int bnum) throws Exception {
		return pdao.chkUsernick(bnum);
	}
	@Override
	public String chkUsernickForReply(int rnum) throws Exception {
		return pdao.chkUsernickForReply(rnum);
	}
	
}
