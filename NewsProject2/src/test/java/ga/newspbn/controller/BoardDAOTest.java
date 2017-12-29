/**
 * 
 */
package ga.newspbn.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ga.newspbn.dao.BoardDAO;
import ga.newspbn.vo.BoardVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testInsert() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setTitle("제목 테스트");
		vo.setContent("테스트 메소드");
		vo.setBname("community");
		vo.setUsernick("김치");
		dao.insertBoard(vo);
	}
	
	@Test
	public void testRead() throws Exception{
		//		.info 도 가능
		logger.debug(dao.read(3).toString());
	}
	
	@Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setBnum(5);
		vo.setTitle("배추");
		vo.setContent("저는 정병 입니다.");
		dao.update(vo);
	}
	
	@Test
	public void testDelete() throws Exception{
		dao.delete(4);
	}
	
	/*@Test
	public void testAll() throws Exception{
		dao.listAll();
	}
	
	
	@Test
	public void testListPage() throws Exception{
		int page = 3;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO vo : list){
			logger.info(vo.getBnum() + " : " + vo.getTitle());
		}	
	}*/
	
	
/*	@Test
	public void testListCriteria() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);	// 페이지 번호
		cri.setPerPageNum(20); //한페이지당 데이터수
		List<BoardVO> list = dao.listCriteria(cri);
		for(BoardVO vo : list){
			logger.info(vo.getBnum() + " : " + vo.getTitle());
		}	
	}*/
	
	@Test
	public void testDynamic1() throws Exception{
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setSearchTarget("t");
		cri.setSearchKeyword("개븅신");
		cri.setBname("community");
		
		logger.info("================================================");
		List<BoardVO> list = dao.listSearch(cri);
		
		for(BoardVO vo : list) {
			logger.info(vo.getBnum() + " : " + vo.getTitle());
		}
				
		logger.info("================================================");
		
		logger.info("갯수 : " + dao.listSearchCount(cri));
	}
}
