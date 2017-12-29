/**
 * 
 */
package ga.newspbn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ga.newspbn.service.ReplyService;
import ga.newspbn.vo.PageMaker;
import ga.newspbn.vo.ReplyVO;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 *	Park Jong-hyun
 */
@RestController
@RequestMapping("/board/replies")
public class ReplyController {
	@Inject
	private ReplyService service;
		
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 대 댓글 작성하기
	 @RequestMapping(value = "/re", method = RequestMethod.POST)
	 public ResponseEntity<String> reregister(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		try {
			Integer maxGrp = service.maxRereply(vo);			
			Integer chkGrp = service.checkRereply(vo);
			
			if(maxGrp == null || maxGrp == vo.getGrp()){
				vo.setGrp(vo.getGrp()+1);
			}else if(vo.getLvl()>0){
				System.out.println("받은vo.getGrp : " + vo.getGrp());
				vo.setGrp(chkGrp);
			}
			else {
				vo.setGrp(maxGrp+1);
				System.out.println("최대값");
			}
			System.out.println("들어간 글번호 : " + vo.getGrp());
			System.out.println("딸린 자식 수 : "+chkGrp+ ", 레벨수 : "+vo.getLvl());
			
			service.updateRereply(vo);
			service.createRereply(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	 }
	@RequestMapping(value="/{bnum}/{page}", method=RequestMethod.GET)
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
	@RequestMapping(value="/{rnum}", method= {RequestMethod.PUT, RequestMethod.PATCH})
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
	@RequestMapping(value="/{rnum}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rnum") Integer rnum){
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rnum);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}