package com.cndatacom.common.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


import com.opensymphony.xwork2.ActionContext;

/**
 * 上传文件
 * 
 * @author add by py 2013-7-11 9:20:03
 * 
 */
public class UploadFile {

	public String uploadExcelParserReport(File image,String imageFileName,String realpath){
		String result="";
		// String realpath =
		// ServletActionContext.getServletContext().getRealPath("/images");
		// D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
		System.out.println("realpath: " + realpath);
		if (image != null) {
			System.out.println(imageFileName);
			File savefile = new File(new File(realpath), imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			result =savefile.toString();
			try {
				FileUtils.copyFile(image, savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ActionContext.getContext().put("message", "文件上传成功");
		}
		return result;
	}

}
