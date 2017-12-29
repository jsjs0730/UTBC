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
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception {
		pdao.voteBoardPoint(pclvo);
	}
	@Override
	public void voteBoardPointDis(PointCycleLogVO pclvo) throws Exception {
		pdao.voteBoardPointDis(pclvo);
	}
	@Override
	public int calculatingPoint(String chk) throws Exception {
		return pdao.calculatingPoint(chk);
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
	public String chkUsernick(int bnum) throws Exception {
		return pdao.chkUsernick(bnum);
	}
	
}
