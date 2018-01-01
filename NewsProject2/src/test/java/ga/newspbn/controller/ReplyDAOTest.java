/**
 * 
 */
package ga.newspbn.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ga.newspbn.dao.ReplyDAO;
import ga.newspbn.vo.ReplyVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReplyDAOTest {

	@Inject
	private ReplyDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);
	@Test
	public void testMethod() throws Exception{
		ReplyVO vo = new ReplyVO();
		String oldDepth = "5@53@45456";
		vo.setDepth(oldDepth);
		String chkdep = dao.chkDepth(vo);
		logger.debug(dao.chkDepth(vo).toString());
		
	}
}
