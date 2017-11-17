package com.cwc.business.message.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cwc.business.message.SendMessage;
@Service
public class Sendmail implements SendMessage{
    
    @Autowired  
    JavaMailSender mailSender;  
    
    @Value("${spring.mail.username}")
    private String userName;
    @Override
    public void sendMail(String target, String content, String subject) {
        
        try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setSubject(subject); 
            message.setFrom(userName);  
            message.setTo(target);  
            message.setText(content);  
            this.mailSender.send(mimeMessage);  
        }  
        catch(Exception ex)     {
           ex.printStackTrace(); 
        }  
    }

}
