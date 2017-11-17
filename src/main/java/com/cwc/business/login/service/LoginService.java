package com.cwc.business.login.service;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cwc.business.login.model.UserBo;

public interface LoginService {

    /**
     * 
     * Description: 校验用户名是否存在
     * 
     *@return boolean
     *
     * @see
     */
    boolean checkUserName(String userName);
    /**
     * 
     * Description: 校验用户名是否存在
     * 
     * 注册不成功的情况下回滚
     *@return boolean
     *
     * @see
     */
    @Transactional(rollbackFor=RuntimeException.class)
    JSONObject registered(UserBo user) throws RuntimeException;
    
}
