package com.cwc.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;







import com.cwc.common.utils.page.Pagination;
import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.model.ActivityProvPo;
import com.cwc.demo.model.GeneralModel;
import com.cwc.demo.model.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class TestService {
	
	@Autowired
	private MyBatisDao dao;
	private ActivityProvPo po;
	public List<UserInfo> getUserList() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map = dao.getUserMap();
		System.out.println(map);
		return dao.getUserList();
	}
	
	
	
	
	/**
	 * 获取成功标准产品列表
	 */
	public Pagination getActivityList2(ActivityProvPo po, Pagination page) {

		if (po == null) {
			po = new ActivityProvPo();
		}
		List<Map<String, String>> list = dao.getActivityList(po);
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		System.out.println("list+++++++++"+list);
		PageInfo<Map<String, String>> pageinfo = new PageInfo<Map<String, String>>(list);
		System.out.println("info+++++++++"+pageinfo);
		page.setList(list);
		page.setTotalCount(pageinfo.getTotal());
		return page;
		// System.out.println(bo.getSuccessStandardPo().getRst1());

	}
	
	
	
	
	
	public List<Map<String, String>> getActivityList(ActivityProvPo po) {
		// TODO Auto-generated method stub
	
		
		return dao.getActivityList(po);
	}
	
	
	@Transactional
	public Boolean save(GeneralModel model, MultipartFile file,
			HttpServletRequest request) {
			File file2 = null;
			Boolean b = false;
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
					model.getFile().setLastModifyDate(new Date());
					dao.saveFile(model.getFile());
					b = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (file2 != null) {
				if (file2.exists()) {//把临时生成的文件删除。
					file2.delete();
				}
			}
			return b;
	}
	public Boolean validLogin(UserInfo u) {
		// TODO Auto-generated method stub
		Boolean b = null;
		try {
			String count = dao.isExist(u);
			if(count.equals("1")){
				String password = dao.validLogin(u);
				if(password==null){
					password = "";
				}
				if(password.equals(u.getPassword())){
					b = true;
				}else{
					b = false;
				}
			}else{
				b = false;
			}			
		} catch (Exception e) {
			b = false;
			// TODO: handle exception
		}	
		return b;
	}

}
