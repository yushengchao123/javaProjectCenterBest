package com.cwc.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cwc.demo.model.FileModel;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.UserInfo;

@Repository
public interface MyBatisDao {

	List<UserInfo> getUserList();

	List<FileModel> queryFile();

	void saveFile(GeneralModel model);

}
