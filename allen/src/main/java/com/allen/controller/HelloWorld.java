package com.allen.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.bean.EssayBean;
import com.allen.model.Essay;
import com.allen.service.EssayService;

/**
 * 
 * @author cvter
 *controller for the front end 
 */
@Controller
public class HelloWorld {
	
	@Autowired
	private EssayService essService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping(value="/")
	public String hello() {
		return "index";
	}
	
	@GetMapping(value="/showEssay/{id}")
	public String showEssay(@PathVariable String id) {
		session.setAttribute("id", id);
		return "showEssay";
	}	
	
	@PostMapping(value="/postEssay")
	public String postEssay(EssayBean essayBean) throws ParseException {
		essService.saveOrUpdate(new Essay(essayBean));
		return "redirect:/";
	}
	
	@GetMapping(value="/deleteEssay/{id}")
	public String deleteEssay(@PathVariable String id) {
		essService.deleteEssay(id);
		return "redirect:/";
	}
	
	@GetMapping(value="/createEssay")
	public String createEssay() {
		return "createEssay";
	}
	
	@GetMapping("/essayList")
	public @ResponseBody List<EssayBean> getEssayList() {
		return essService.getEssayList();
	}
	
	@GetMapping("/essaySingle/{id}")
	public @ResponseBody EssayBean getEssay(@PathVariable String id) {
		return essService.getEssay(id);
	}
}
