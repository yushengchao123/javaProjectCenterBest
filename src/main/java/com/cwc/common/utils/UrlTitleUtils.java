package com.cwc.common.utils;

public class UrlTitleUtils {
	private static PropertiesHelper pHelper;
	static{
		pHelper = new PropertiesHelper("/application.properties");
    }
	
	public String getUrl(){
		String url = pHelper.getValue("server.context-path");
		return url;
	}
}
