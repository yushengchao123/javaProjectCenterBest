package com.cwc.business.login.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cwc.business.login.model.VerificationLogDo;

@Repository
public interface VerificationLogDao {
    void saveVerificationLog(VerificationLogDo verificationLog);
    
    VerificationLogDo getVerificationLog(@Param("recipient")String recipient);
}
