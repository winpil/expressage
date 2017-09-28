package com.cndatacom.common.weixin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReadUtils {
	/**
	 * * 方法名: 
	    * 详述: 爱赚钱公众号 自动回复机器人
	    * 开发人员：刘锐
	    * 创建时间：May 22, 2014
	    * @return
	 */
	public List<String> getReplyAZQTextList() {
		//获得具体的dom解析器
		try {
			File file = new File("C:/cxdocument/aizhuanqian_replay.txt");
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String lineTxt = null;
			List<String> list = new ArrayList<String>();
			while ((lineTxt = br.readLine())!=null) {
				list.add(lineTxt);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * * 方法名: 
	    * 详述: 翼得利 公众号 自动回复机器人
	    * 开发人员：刘锐
	    * 创建时间：May 23, 2014
	    * @return
	 */
	public List<String> getReplyYDLTextList() {
		//获得具体的dom解析器
		try {
			File file = new File("C:/cxdocument/yideli_replay.txt");
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String lineTxt = null;
			List<String> list = new ArrayList<String>();
			while ((lineTxt = br.readLine())!=null) {
				list.add(lineTxt);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
