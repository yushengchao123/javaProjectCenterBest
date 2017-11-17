package com.cwc.interfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cwc.business.login.model.UserBo;
import com.cwc.business.login.service.LoginService;
import com.cwc.business.login.service.Send;
import com.cwc.business.message.mail.Sendmail;  
  
@RestController  
@RequestMapping("/interfaces")  
public class RestInterface {
    @Autowired  
    Sendmail mailSender;  
    @ResponseBody
    @RequestMapping(value ="sendemail",method = RequestMethod.GET)  
    public Object sendEmail(String mail)   {  
        try  
        {  
            Integer random = (int)(Math.random()* 100000);
            DateFormat hour = new SimpleDateFormat("HH:mm:ss");
            DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            StringBuilder sb = new StringBuilder();
            sb.append("尊敬的用户，您好。");
            sb.append("\n");
            sb.append("欢迎您使用业余交流群系统。");
            sb.append("\n");
            sb.append("您本次操作的验证码是："+random+"，有效时间：900 秒。");
            sb.append("\n");
            sb.append("尊敬的用户，您好。");
            sb.append("发送时间");
            sb.append(hour.format(date));
            sb.append("，日期");
            sb.append(day.format(date));
            sb.append("。");
            mailSender.sendMail(mail, sb.toString(),"业余交流群系统验证码");
            
        }  
        catch(Exception ex)     {
           ex.printStackTrace(); 
        }  
        return null;  
    }  
    
    


   /* @Autowired
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
    }*/
    /*@Autowired
    private LoginService loginService;
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
*/
    
    
}
