package com.cwc.business.login.model;

import java.util.Date;

/***
 * 
 * 验证码对应的实体类
 *
 * @author 任壮
 * @version 2017年11月17日
 * @see VerificationLogDo
 * @since
 */
public class VerificationLogDo {

    private int id;
    /**
     * 接受者
     */
    private String recipient;
    /**
     * 接受者
     */
    private Date sendTime;
    /**
     * 接受者
     */
    private String content;
    /**
     * 接受者
     */
    private Integer verificationCode;
    /**
     * 数据库记录生成时间
     */
    private Date createTime;
    public int getId() {
        return id;
    }
    public String getRecipient() {
        return recipient;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public String getContent() {
        return content;
    }
    public Integer getVerificationCode() {
        return verificationCode;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
}
