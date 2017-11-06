package com.cwc.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;





import com.cwc.demo.model.ActivityProvPo;
import com.cwc.demo.model.ChatTalk;
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

	UserInfo findUserByName(@Param("userName")String userName);

	/**
	 * 获取活动列表的数据
	 * @param bo
	 * @return
	 */
	 List<Map<String, String>> getActivityList(ActivityProvPo po);
	public void savechatComment(Map<String, String> requestMap);
	public List<Map<String, String>> selectList(Map<String, String> requestMap);

}
