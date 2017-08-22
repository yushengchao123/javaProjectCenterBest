package com.cwc.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.model.FileModel;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.UserInfo;

@Component
public class TestService {
	
	@Autowired
	private MyBatisDao dao;

	public List<UserInfo> getUserList() {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}
	@Transactional
	public void save(GeneralModel model, MultipartFile file,
			HttpServletRequest request) {
			File file2 = null;
			System.out.println(file);
			if (!file.isEmpty() && file != null) {
				System.out.println("进入文件解析");
				BufferedOutputStream out = null;
				try {
					byte[] content = file.getBytes();
					file2 = new File(file.getOriginalFilename());
					file2.createNewFile();
					out = new BufferedOutputStream(new FileOutputStream(file2));
					out.write(file.getBytes());
					out.flush();
					out.close();
					model.getFile().setUploadFile(content);
					model.getFile().setFileName(file.getOriginalFilename());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				model.getFile().setLastModifyDate(new Date());
				dao.saveFile(model);
			if (file2 != null) {
				if (file2.exists()) {//把临时生成的文件删除。
					file2.delete();
				}
			}
	}

}
