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
import org.springframework.util.StringUtils;

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
	public void testGetMaxDepth() throws Exception{
		ReplyVO vo = new ReplyVO();
		String oldDepth = "8@1";
		String tempDepth = oldDepth + "@";
		vo.setDepth(tempDepth);
		int getMaxDepth = (Integer)dao.getMaxDepth(vo);
		logger.debug("int getMaxDepth = (Integer)dao.getMaxDepth(vo);" + getMaxDepth);
		//결과1 :  oldDepth = "28"; 28@2 에 있는 2 를 반환 함
		//결과2 :  oldDepth = "38"; null 을 반환 했다
		String newDepth = tempDepth + ((int)getMaxDepth+1);
		int cntDepth = StringUtils.countOccurrencesOf(newDepth, "@");
		System.out.println("새로운 번호를 가진 getDepth : " + newDepth + " cntDepth : " + cntDepth);
	}
}
