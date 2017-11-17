package com.cwc.business.login.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.business.login.dao.VerificationLogDao;
import com.cwc.business.login.model.VerificationLogDo;
import com.cwc.business.login.service.Send;
import com.cwc.business.message.mail.Sendmail;
@Service
public class MailSend implements Send{

    @Autowired  
    private Sendmail mailSender; 
    @Autowired  
    private VerificationLogDao verificationLog; 
    @Override
    public boolean  sendMail(String mail) {
        boolean flag= false;
        
        try {
            // 1.生成验证码的值
            Date date = new Date();
            Integer random = (int)(Math.random()* 100000);
            
            DateFormat hour = new SimpleDateFormat("HH:mm:ss");
            DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            
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
            //2.将验证码的值保存进数据库
            VerificationLogDo verDo = new VerificationLogDo();
            verDo.setRecipient(mail);
            verDo.setSendTime(date);
            verDo.setVerificationCode(random);
            verDo.setContent(sb.toString());
            verificationLog.saveVerificationLog(verDo);
            //3.发送到邮箱
            mailSender.sendMail(mail, sb.toString(),"业余交流群系统验证码");
            flag =true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return flag; 
    }
   
}
