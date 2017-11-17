package com.cwc.business.login.service;

public interface Send {
    /**
     * 验证码默认有效时间（15分钟）
     */
    public static final Long EFFECTIVE_TIME = 15*60*1000L;
    
    boolean sendMail(String mail);
}
