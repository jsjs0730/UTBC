/**
 * 
 */
package tk.utbc.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import tk.utbc.dao.BoardDAO;
import tk.utbc.dao.PointDAO;
import tk.utbc.vo.BoardVO;
import tk.utbc.vo.Criteria;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Inject
	private PointDAO pdao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);	
	
	@Override
	public List<BoardVO> listHomePage(Criteria cri) throws Exception {
		return dao.homeList(cri);
	}
	@Override
	public List<BoardVO> listSlide(int vlike) throws Exception {
		return dao.homeListSlide(vlike);
	}
	@Override
	public List<BoardVO> listFavorite(int vlike) throws Exception {
		return dao.homeListFavorite(vlike);
	}
	@Transactional
	@Override
	public void insert(BoardVO vo) throws Exception {
		vo.setUid(pdao.chkUid(vo.getUsernick()));
		dao.insertBoard(vo);

		String files[] = vo.getFiles();
		if(files == null) {
			return;
		}
		for(String fileName : files) {
			dao.addAttach(fileName);
		}
	
				
	}
					//격리수준 : 다른 연결이 커밋하지 않은 데이터는 볼 수 없도록 함
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bnum) throws Exception {
		dao.updateViewCnt(bnum);
		return dao.read(bnum);
	}

//	@Override
//	public void modify(BoardVO vo) throws Exception {
//		
//		dao.update(vo);
//	}
	@Transactional
	@Override
	public void remove(Integer bnum) throws Exception {
		dao.deleteAllReply(bnum);
		dao.deleteAttach(bnum);
		dao.delete(bnum);
		
		//tbl_attach가 tbl_board를 참조하고 있기때문에 반드시 attach부터 지운다.
	}


	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return dao.listSearchCount(cri);
	}
	
	@Override
	public List<String> getAttach(Integer bnum) throws Exception {
		return dao.getAttach(bnum);
	}
	
	@Transactional
	@Override
	public void modify(BoardVO vo) throws Exception{
		dao.update(vo);
		Integer bnum = vo.getBnum();
		dao.deleteAttach(bnum);
		String [] files = vo.getFiles();
		if(files == null) {
			return;
		}
		for(String fileName : files) {
			dao.replaceAttach(fileName, bnum);
		}
	}
	@Override
	public List<BoardVO> getVoteResult(Integer bnum) throws Exception {
		return dao.voteResult(bnum);
	}
	
	
}
