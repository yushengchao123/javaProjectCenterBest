package com.cwc.demo.model;

import java.util.Date;

public class FileModel {
	
	private String fileId;
	private String fileName;
	private String filePath;
	private String ord;
	private String fileType;
	/**
	 * 上传文件
	 */
	private byte[] uploadFile;
	private String uploadUser;
	private Date lastModifyDate;
	private String fileState;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(byte[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getFileState() {
		return fileState;
	}
	public void setFileState(String fileState) {
		this.fileState = fileState;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	
}
