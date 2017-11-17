package com.cwc.business.login.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cwc.business.login.model.UserBo;
import com.cwc.business.login.model.VerificationLogDo;

@Repository
public interface VerificationLogDao {
    void saveVerificationLog(VerificationLogDo verificationLog);
    /**
     * 
     * Description: 保存到用户主表
     * 
     *@param userBo void
     *
     * @see
     */
    void saveUserRole(UserBo userBo);
    /***
     * 
     * Description: 保存到用户角色对应表
     * 
     *@param userBo void
     *
     * @see
     */
    void saveUser(UserBo userBo);
    
    VerificationLogDo getVerificationLog(@Param("recipient")String recipient);
}
