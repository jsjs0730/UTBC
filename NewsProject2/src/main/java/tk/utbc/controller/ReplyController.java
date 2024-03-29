/**
 * 
 */
package tk.utbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.utbc.service.PointService;
import tk.utbc.service.ReplyService;
import tk.utbc.vo.PageMaker;
import tk.utbc.vo.PointCycleLogVO;
import tk.utbc.vo.ReplyVO;
import tk.utbc.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@RestController
@RequestMapping("/board/replies")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	@Inject
	private PointService pointService;
		
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			service.addReply(vo);
			
			pointService.updatePoint(vo.getUid(), 10);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/addReReply", method=RequestMethod.POST)
	public ResponseEntity<String> insertReReply(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			System.out.println("vo.getDepth() : " + vo.getDepth());
			service.createReReply(vo);
			pointService.updatePoint(vo.getUid(), 10);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
				
	} 
	
	@RequestMapping(value="/com/{bnum}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bnum") Integer bnum, @PathVariable("page") int page){
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			SearchCriteria cri = new SearchCriteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = service.listReplyPage(bnum, cri);
			map.put("list", list); //Model을 사용할 수 없으므로 Map을 이용하여 전송한다.(RestController는 API만)
			Integer replyCount = service.count(bnum);
			pageMaker.setTotalDataCount(replyCount);
			map.put("pageMaker", pageMaker);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/update/{rnum}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rnum") Integer rnum, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRnum(rnum);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/delete/{rnum}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rnum") Integer rnum){
		ResponseEntity<String> entity = null;
		try {
			//삭제전 포인트 로그 삭제
			PointCycleLogVO pclvo = new PointCycleLogVO();
			pclvo.setRnum(rnum);
			pclvo.setUid(pointService.chkUid(pointService.chkUsernickForReply(rnum)));
			pclvo.setChk("rck");
			pointService.deleteBoardPointLog(pclvo);
			//포인트 회수
			pointService.updatePoint(pclvo.getUid(), -5);
			service.removeReply(rnum);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
