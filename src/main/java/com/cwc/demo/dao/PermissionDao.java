package com.cwc.demo.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cwc.demo.model.Permission;

@Repository
public interface PermissionDao {
	
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(@Param("userName")String userId);

}
