package com.cwc.interfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;  
  
@RestController  
@RequestMapping("interfaces")  
public class RestInterface {
    @Autowired  
    JavaMailSender mailSender;  
      
    @Value("${spring.mail.username}")
    private String userName;
    
    @RequestMapping(value ="sendemail",method = RequestMethod.GET)  
    public Object sendEmail(String mail)   {  
        try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom(userName);  
            message.setTo(mail);  
         //   message.setTo("282034670@qq.com");  
            message.setSubject("业余交流群系统验证码"); 
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
            
            message.setText(sb.toString());  
            this.mailSender.send(mimeMessage);  
            
        }  
        catch(Exception ex)     {
           ex.printStackTrace(); 
        }  
        return null;  
    }  
}
