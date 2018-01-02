/**
 * 
 */
package ga.newspbn.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ga.newspbn.service.BoardService;
import ga.newspbn.service.PointService;
import ga.newspbn.vo.BoardVO;
import ga.newspbn.vo.PageMaker;
import ga.newspbn.vo.PointCycleLogVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@Inject
	private PointService pointService;

	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public void insertGET(BoardVO vo, Model model) throws Exception{
		logger.info("글쓰기 페이지로 들어감........get");
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(BoardVO vo, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("글쓰기 페이지에서 등록을 시도함.....post");
//		logger.info(vo.toString());
		rttr.addAttribute("bname", cri.getBname());	//글쓰고 해당 글이 있는 곳으로 날림
		service.insert(vo);
//		int calPoint = pointService.calculatingPoint("bck") *30;//글 한개 쓸 때 마다 30point
		
		pointService.updatePoint(vo.getUid(), 30);	
							//			페이지명
		rttr.addFlashAttribute("msg", "success");//데이터를 담아 전달
//		return "/board/success"; // F5 무한 도배 가능
		return "redirect:/board/listPage";		
	}
		
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		logger.info("리스트 페이지.........."+cri.getBname());
		logger.info(cri.toString());
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalDataCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	

	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void readPaging(@RequestParam("bnum") int bnum, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bnum));
	}
	
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String removePage(@RequestParam("bnum") int bnum, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		//로그 삭제전 포인트 로그 삭제
		PointCycleLogVO pclvo = new PointCycleLogVO();
		pclvo.setBnum(bnum);
		pclvo.setUid(pointService.chkUid(pointService.chkUsernick(bnum)));
		pclvo.setChk("bck");
		pointService.deleteBoardPointLog(pclvo);
		//포인트 회수
		pointService.updatePoint(pclvo.getUid(), -15);
		service.remove(bnum);
		
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("bname", cri.getBname());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());		
		rttr.addAttribute("searchKeyword", cri.getSearchKeyword());
		rttr.addAttribute("searchTarget", cri.getSearchTarget());
		
		
		return "redirect:/board/listPage";
	}
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)//보기 -> 수정
	public void modifyPagingGET(@RequestParam("bnum") int bnum, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bnum));
	}
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)//수정 -> 리스트
	public String modifyPagingPOST(BoardVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.modify(vo);
		
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("bname", cri.getBname());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchKeyword", cri.getSearchKeyword());
		rttr.addAttribute("searchTarget", cri.getSearchTarget());
		
		
		return "redirect:/board/listPage";
	}
	@ResponseBody
	@RequestMapping("/getAttach/{bnum}")	
	public List<String> getAttach(@PathVariable("bnum") Integer bnum) throws Exception{
		return service.getAttach(bnum);
	}
	
	
}
