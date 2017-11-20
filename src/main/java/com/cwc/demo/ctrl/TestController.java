package com.cwc.demo.ctrl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cwc.common.utils.UrlTitleUtils;
import com.cwc.common.utils.page.Pagination;
import com.cwc.demo.model.ActivityProvPo;
import com.cwc.demo.model.ChatTalk;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.Msg;
import com.cwc.demo.model.UserInfo;
import com.cwc.demo.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService service;
	private ActivityProvPo po;
	private String page;
	private Map<String,Object> map= new HashMap<String,Object>();
	private static Logger log = Logger.getLogger(TestController.class);
	private ChatTalk chat;
	Map<String,String> requestMap = new HashMap<String,String>();
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public void setChat(ChatTalk chat){
		this.chat=chat;
		
	}
	
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request){	
		log.info(request.getRemoteAddr());
		UserInfo userinfo = new UserInfo();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = service.getUserList();
		model.addAttribute("singlePerson", userinfo);
		model.addAttribute("people", list);
		map.put("path", request.getContextPath());
		map.put("host", request.getRemoteHost());
		map.put("ip", request.getRemoteAddr());
		map.put("port", request.getRemotePort());
		map.put("time", new Date());
		model.addAttribute("request",map);
		return "index";
	}
	
/*	@RequestMapping("/login")
	public @ResponseBody Map<String,Object> validLogin(UserInfo u){
		log.info("登录校验");
		Boolean b = service.validLogin(u);
		String url = "main";
		map.put("success", b);
		if(b){
			map.put("url", url);
		}
		return map;
	}*/
	
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
	public String traditionalLogin(Model model,HttpServletRequest request) {	
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
	@RequestMapping(value = { "/main" })
	public String main(Model model,HttpServletRequest request) {	
		UserInfo userinfo = new UserInfo();
		List<UserInfo> list = new ArrayList<UserInfo>();
		list = service.getUserList();
		model.addAttribute("singlePerson", userinfo);
		model.addAttribute("people", list);
		map.put("path", request.getContextPath());
		map.put("host", request.getRemoteHost());
		map.put("ip", request.getRemoteAddr());
		map.put("port", request.getRemotePort());
		map.put("time", new Date());
		model.addAttribute("request",map);
		return "mainlist";
	}		
	@RequestMapping(value = { "/demo" })
	public String ReactLogin(Model model,HttpServletRequest request) {	
		UserInfo userinfo = new UserInfo();
		
		return "ReactLogin";
	}
	
	@RequestMapping(value = { "/TableDemo" })
	public String ReactTableDemo(Model model,HttpServletRequest request) {	
		UserInfo userinfo = new UserInfo();
		
		
		
		
		return "TableDemo";
	}
	@RequestMapping(value = { "/ListDemo" })
	public String ReactListDemo(Model model,HttpServletRequest request) {	
		
		return "ListDemo";
	}
	
	
	
	@RequestMapping(value = {"/list"},method = RequestMethod.POST)
	public @ResponseBody Pagination getActivityList(Model model, ActivityProvPo po, Pagination pagination){
		
		if (po == null) {
			po = new ActivityProvPo();
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();		
		list = service.getActivityList(po);
		
		pagination = service.getActivityList2(po, pagination);
		System.out.println(JSON.toJSONString(pagination));

		return pagination;
		
	}
	
	@RequestMapping(value = {"/saveTalk"},method = RequestMethod.POST)
	public @ResponseBody String saveTalk(HttpServletRequest  request,HttpServletResponse  response){
		init(request);
		
		System.out.println("--------------```"+requestMap);
		/*if (chat == null) {
			chat = new ChatTalk();
		}
		chat.setUserName("ysc");
		String cc=chat.getUserName();
		System.out.println(cc);
		chat.setCreateTime("2017/10/11 14:55");
		chat.setUserTalkDesc("siiasdifhaisdhfoiashdfoasidhfoasdhfoiaisdhfioas");
		*/
		service.savechatComment(requestMap);
		try {
			init(request);
			
			List<Map<String, String>> result   = service.selectList(requestMap);
			System.out.println(result+"ssssss");

			return JSON.toJSONString(result);
		} catch (Exception e) {
			return null;
		}

		
	}
	@RequestMapping(value = {"/selectList"},method = RequestMethod.POST)
	public @ResponseBody String selectList(HttpServletRequest  request,HttpServletResponse  response){
		
	
		
		try {
			init(request);
			
			List<Map<String, String>> result   = service.selectList(requestMap);
			System.out.println(result+"ssssss");

			return JSON.toJSONString(result);
		} catch (Exception e) {
			return null;
		}
		
		
		
		
	}
	
	private void init(HttpServletRequest  request){
		try {
			getRequestMap(request);
			String result = StringUtils.isNullOrEmpty(requestMap.get("userName"))?requestMap.put("userName", "匿名"):null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//requestMap.put("tenantId","uni097");
	}
	
	private void getRequestMap(HttpServletRequest request)
			throws UnsupportedEncodingException {
		requestMap.clear();
		Map<String,String[]> tempMap = request.getParameterMap();
		Set<String> keys = tempMap.keySet();
		for(String key:keys){
			if(request.getParameter(key) != null){
				byte source [] = request.getParameter(key).getBytes("UTF-8");
	            String modelname = new String (source,"UTF-8");
	            requestMap.put(key,modelname);
			}
		}	
	}
	
	    @RequestMapping("/msg")
	    public String index(Model model){
	        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
	        model.addAttribute("msg", msg);
	        return "home";
	    }
	    @RequestMapping("/admin")
	    @ResponseBody
	    public String hello(){
	        return "hello admin";
	    }
	
	
	
	
	@RequestMapping("/403")
    public String error(){
        return "403";
    }
}
