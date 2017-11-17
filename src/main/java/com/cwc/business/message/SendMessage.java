package com.cwc.business.message;

public interface SendMessage {

    void sendMail(String target, String content, String subject);
}
