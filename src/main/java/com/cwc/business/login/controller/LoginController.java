package com.cwc.business.login.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cwc.business.login.model.UserBo;
import com.cwc.business.login.service.LoginService;
import com.cwc.business.login.service.Send;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private Send sender;
    
    @ResponseBody
    @RequestMapping(value = { "/checkUsername" }, method = RequestMethod.POST)
    public  JSONObject save(@RequestBody JSONObject param) {
        boolean checkResult =false;
        try {
            String username = param.getString("username");
            checkResult = loginService.checkUserName(username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject result = new JSONObject();
        result.put("result", checkResult);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = { "/sendVerificationCode" }, method = RequestMethod.POST)
    public  JSONObject sendVerificationCode(@RequestBody JSONObject param) {  
        boolean result =false;
        try  {  
              String mail =  param.getString("mailAddress");
              result =     sender.sendMail(mail);
        }  
        catch(Exception ex)     {
           ex.printStackTrace(); 
        }  
        JSONObject msg = new JSONObject();
        msg.put("result", result);
        return msg;  
    }
    @ResponseBody
    @RequestMapping(value = { "/registered" }, method = RequestMethod.POST)
    public  JSONObject registered(@RequestBody UserBo param) {  
        JSONObject msg = new JSONObject();
        try  
        {  
            param.setId(UUID.randomUUID().toString().replace("-", ""));
            //注册时候没有指定角色默认是普通用户
            if(StringUtils.isBlank(param.getRoleId())){
                param.setRoleId("2");
            }
            msg = loginService.registered(param);
        }  
        catch(Exception ex)     {
            msg.put("result", false);
            msg.put("result", "用户注册异常");
            ex.printStackTrace(); 
        }  
        return msg;  
    }
}
