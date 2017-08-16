package com.cwc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.model.UserInfo;

@Component
public class TestService {
	
	@Autowired
	private MyBatisDao dao;

	public List<UserInfo> getUserList() {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}

}
