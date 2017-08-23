package com.cwc.demo.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.UserInfo;
import com.cwc.demo.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService service;
	
	private String page;
	private Map<String,Object> map= new HashMap<String,Object>();
	private static Logger log = Logger.getLogger(TestController.class);

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	@RequestMapping("/index")
	public String index(Model model){		
		UserInfo userinfo = new UserInfo();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = service.getUserList();
		model.addAttribute("singlePerson", userinfo);
		model.addAttribute("people", list);
		return "index";
	}
	
	@RequestMapping(value = { "/save" })
	public @ResponseBody String save(@RequestParam("eventfile") MultipartFile file,GeneralModel model,
			HttpServletRequest request) {
		System.out.println("66666666control");
		Boolean flag=service.save(model, file, request);
		log.info("保存文件:"+flag);
		map.put("success", flag);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value = { "/showtime" })
	public String traditionalLogin(Model model) {	
		UserInfo userinfo = new UserInfo();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = service.getUserList();
		model.addAttribute("singlePerson", userinfo);
		model.addAttribute("people", list);
		return "traditionalLogin";
	}
	@RequestMapping(value = {"/getAge"},method = RequestMethod.POST)
	public @ResponseBody String getAge(Model model){
		log.info("获取年龄开始");
		map.put("success", true);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("age", 18);
		map.put("person", map2);
		return JSON.toJSONString(map);
		
	}
			
	
}
