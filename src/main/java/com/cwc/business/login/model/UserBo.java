package com.cwc.business.login.model;

import java.util.List;

import com.cwc.demo.model.RoleInfo;

/**
 * 
 * 保存用户信息业务处理需要的参数
 *
 * @author 任壮
 * @version 2017年11月17日
 * @see UserBo
 * @since
 */
public class UserBo {


    private String id;
    private String password;
    private String sex;
    private String name;
    private String age;
    private String nickName;
    private String userName;
    private String mail;
    /**
     * 角色id
     */
    private String roleId;
    
   /**    
    * 验证码
    */
    private String verificationCode;
    
    private List<RoleInfo> roles;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<RoleInfo> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleInfo> roles) {
        this.roles = roles;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getRoleId() {
        return roleId;
    }
    public String getVerificationCode() {
        return verificationCode;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
    

    
    
    


}
