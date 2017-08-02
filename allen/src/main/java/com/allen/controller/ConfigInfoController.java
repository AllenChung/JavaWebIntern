package com.allen.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.bean.ResultBean;
import com.allen.dto.Config;
import com.allen.dto.ConfigInfoTest;
import com.allen.model.ConfigInfo;
import com.allen.service.ConfigInfoService;
import com.allen.util.ResultBeanFactory;

@Controller
public class ConfigInfoController {
	
	@Autowired
	private ConfigInfoService conService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletResponse response;

	//controller for create or update config
	@PostMapping("/configInfo")
	public String postConfigInfo(Config configBean) throws Exception {
		conService.saveOrUpdate(new ConfigInfo(configBean));
		return "redirect:configIndex";
	}
	
	//controller for check whether the config changed
	@PostMapping("/configInfoTest") 
	public @ResponseBody ResultBean testConfigInfo(@RequestBody ConfigInfoTest configInfoTest) {
		if (conService.testConfigChange(configInfoTest)) {
			return ResultBeanFactory.getResult(1, null, null);
		} else {
			response.setStatus(HttpStatus.NOT_MODIFIED.value());
			return ResultBeanFactory.getResult(0, null, null);
			
		}
	}
	
	//controller for return config index page
	@GetMapping("/configIndex")
	public String getConfigIndex() {
		List<Config> configBeans = new LinkedList<>();
		for (ConfigInfo c: conService.getList()) {
			configBeans.add(new Config(c));
		}
		session.setAttribute("configList", configBeans);
		return "configIndex";
	}
	
	
	@GetMapping("/configInfo/{key:.*}")
	public @ResponseBody ResultBean getByKey(@PathVariable String key) {
		System.out.println(key);
		Map<String, Object> map = new HashMap<>();
		map.put(key, conService.getValueBykey(key));
		return ResultBeanFactory.getResult(1, null, map);
	}
}
