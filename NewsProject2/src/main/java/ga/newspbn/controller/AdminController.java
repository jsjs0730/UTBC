/**
 * 
 */
package ga.newspbn.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ga.newspbn.service.BoardService;
import ga.newspbn.vo.PageMaker;
import ga.newspbn.vo.SearchCriteria;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	BoardService service;
	
	@RequestMapping(value="/admin/listPage", method=RequestMethod.GET)
	public void adminList(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception {
		logger.info("리스트 페이지.........."+cri.getBname());
		logger.info(cri.toString());
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalDataCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
}
