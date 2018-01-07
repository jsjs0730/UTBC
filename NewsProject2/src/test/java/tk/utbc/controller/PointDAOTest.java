/**
 * 
 */
package tk.utbc.controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tk.utbc.dao.PointDAO;
import tk.utbc.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 * 2018. 01. 04 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PointDAOTest {
	@Inject
	PointDAO dao;
	
	private static Logger logger = Logger.getLogger("PointDAOTest.class");
	
	@Test
	public void testUpdateVote() throws Exception {
		dao.updateVote(4029, 1, 0);
	}
	
	@Test
	public void testChkVoteCount() throws Exception{
		PointCycleLogVO pclvo = new PointCycleLogVO();
		pclvo.setBnum(4029);
		pclvo.setUid("user1");
		pclvo.setChk("rck");
		int cnt = dao.chkVoteCount(pclvo);
		logger.debug("cnt = " + cnt);
	}
}
