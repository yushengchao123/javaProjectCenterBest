package com.cwc.business.login.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cwc.business.login.dao.VerificationLogDao;
import com.cwc.business.login.model.UserBo;
import com.cwc.business.login.model.VerificationLogDo;
import com.cwc.business.login.service.LoginService;
import com.cwc.business.login.service.Send;
import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.model.UserInfo;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private MyBatisDao dao;
    @Autowired
    private VerificationLogDao userDao;
    @Override
    public boolean checkUserName(String userName) {
        UserInfo user = dao.findUserByName(userName);
        if(user == null)
            return true;
        return false;
    }
    @Override
    public JSONObject registered(UserBo user) throws RuntimeException{
        JSONObject json = new JSONObject();
    
        try {
            // 1.校验验证码（验证码不通过直接返回注册失败）
            VerificationLogDo verDo =  userDao.getVerificationLog(user.getMail());
            if(verDo == null) {
                //超过有效时间
                json.put("result", false);
                json.put("result", "没有该邮箱的验证码");
                return json;
            }
             Date sendTime = verDo.getSendTime();
            if( (new Date().getTime() -sendTime.getTime())>Send.EFFECTIVE_TIME ) {
                //超过有效时间
                json.put("result", false);
                json.put("result", "超过有效时间");
                return json;
            }
            
            // 2.插入用户表
            userDao.saveUser(user);
            // 3.插入用户角色对应表
            userDao.saveUserRole(user);
            json.put("result", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return json;
    }

}
