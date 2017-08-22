package com.cwc.demo.ctrl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;








import com.cwc.demo.model.FileModel;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.UserInfo;
import com.cwc.demo.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService service;
	
	private String page;
	private Map<String,Object> map= new HashMap<String,Object>();
	
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
	public String policyAdd2(@RequestParam("eventfile") MultipartFile file,GeneralModel model,
			HttpServletRequest request) {
		System.out.println("66666666control");
		service.save(model, file, request);
		return "index";
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
			
	
}
