package com.cwc.business.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
        String username = param.getString("username");
        boolean  checkResult =  loginService.checkUserName(username);
        JSONObject result = new JSONObject();
        result.put("result", checkResult);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = { "/sendVerificationCode" }, method = RequestMethod.POST)
    public  JSONObject sendVerificationCode(@RequestBody JSONObject param) {  
        try  
        {  
            String mail =  param.getString("mailAddress");
            boolean  result =     sender.sendMail(mail);
            JSONObject msg = new JSONObject();
            msg.put("result", result);
            return msg;
        }  
        catch(Exception ex)     {
           ex.printStackTrace(); 
        }  
        return null;  
    }
    
    
}
