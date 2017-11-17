package com.cwc.business.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.business.login.service.LoginService;
import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.model.UserInfo;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private MyBatisDao dao;
    @Override
    public boolean checkUserName(String userName) {
        UserInfo user = dao.findUserByName(userName);
        if(user == null)
            return true;
        return false;
    }

}
