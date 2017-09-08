package com.cwc.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cwc.demo.model.FileModel;
import com.cwc.demo.model.UserInfo;

@Repository
public interface MyBatisDao {

	List<UserInfo> getUserList();

	List<FileModel> queryFile();

	void saveFile(FileModel file);

	Map<String, Object> getUserMap();

	String validLogin(UserInfo u);

	String isExist(UserInfo u);


}
