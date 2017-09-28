/**
 * 
 */
package com.cndatacom.common.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 * 
 */
public class CommonUtil {
	private static Log log = LogFactory.getLog(CommonUtil.class);

	/**
	 * 打印Request的所有参数
	 * 
	 * @标题:
	 * @描述:
	 * @param request
	 * @公司:广东数据通信网络有限公司
	 * @作者:ljt
	 * @日期:2008-3-26
	 */
	public static void printRequestParameter(HttpServletRequest request) {
		Map parameterMap = request.getParameterMap();
		Set keyset = parameterMap.keySet();
		Iterator it = keyset.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = request.getParameter(key);
			log.debug("key=" + key + "---value=" + value);
		}
	}

	public static void printRequestParameter2Decode(HttpServletRequest request) {
		Map parameterMap = request.getParameterMap();
		Set keyset = parameterMap.keySet();
		Iterator it = keyset.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = request.getParameter(key);
			log.debug("前key=" + key + "---value=" + value);
			value = CharTools.Utf8URLdecode(value);
			log.debug("后key=" + key + "---value=" + value);
		}
	}

	/**
	 * 
	 * @标题:
	 * @描述:获取对象的内部字段名
	 * @param obj
	 *            对象
	 * @return
	 * @公司:广东数据通信网络有限公司
	 * @作者:ljt
	 * @日期:2008-3-26
	 */
	public static List getClassFieldNames(Object obj) {
		List list = new ArrayList();
		try {
			Field field[] = obj.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				String fieldName = field[i].getName();
				list.add(fieldName);
				log.debug(fieldName + "--" + field[i].get(obj));
				// Class typeClass = publicFields[i].getType();
				// String fieldType = typeClass.getName();
				// log.debug("Name: " + fieldName +", Type: " + fieldType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @标题:
	 * @描述:储存文件
	 * @param targetFile
	 *            目标文件全路径
	 * @param sourceFileInputStream
	 *            源文件流
	 * @return
	 * @公司:广东数据通信网络有限公司
	 * @作者:ljt
	 * @日期:2008-3-28
	 */
	public static boolean saveFile(String targetFile,
			InputStream sourceFileInputStream) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			byte[] buffer = new byte[4096];
			int n = 0;
			while ((n = sourceFileInputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, n);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				// if (sourceFileInputStream !=null)
				// sourceFileInputStream.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static void chineseComparator(String str1, String str2) {
		// Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		String[] arr = { str1, str2 };
		// 使根据指定比较器产生的顺序对指定对象数组进行排序。
		Arrays.sort(arr, cmp);
	}

	public static String getFileContentType(String fileType) {
		String contentType = "image/jpeg|image/jpeg|image/gif|image/vnd.wap.wbmp|"
				+ "image/png|audio/amr|"
				+ "audio/mid|audio/midi|"
				+ "audio/mpeg|text/plain|text/x-iMelody|"
				+ "application/smil|application/vnd.smaf|application/x-msmetafi|"
				+ "application/msword|application/vnd.ms-excel|application/vnd.ms-powerpoint";
		String[] contentTypeList = contentType.split("\\|");
		// int sPoint = fileName.lastIndexOf(".");
		// String fileType = fileName.substring(sPoint+1);//取出文件的后缀名
		if (fileType.toLowerCase().equals("jpeg")) {
			return contentTypeList[0];
		} else if (fileType.toLowerCase().equals("jpg")) {
			return contentTypeList[1];
		} else if (fileType.toLowerCase().equals("gif")) {
			return contentTypeList[2];
		} else if (fileType.toLowerCase().equals("wbmp")) {
			return contentTypeList[3];
		} else if (fileType.toLowerCase().equals("png")) {
			return contentTypeList[4];
		} else if (fileType.toLowerCase().equals("amr")) {
			return contentTypeList[5];
		} else if (fileType.toLowerCase().equals("mid")) {
			return contentTypeList[6];
		} else if (fileType.toLowerCase().equals("midi")) {
			return contentTypeList[7];
		} else if (fileType.toLowerCase().equals("mp3")
				|| fileType.toLowerCase().equals("mpeg")
				|| fileType.toLowerCase().equals("3gp")) {
			return contentTypeList[8];
		} else if (fileType.toLowerCase().equals("txt")) {
			return contentTypeList[9];
		} else if (fileType.toLowerCase().equals("imy")) {
			return contentTypeList[10];
		} else if (fileType.toLowerCase().equals("smil")) {
			return contentTypeList[11];
		} else if (fileType.toLowerCase().equals("mmf")) {
			return contentTypeList[12];
		} else if (fileType.toLowerCase().equals("wmf")) {
			return contentTypeList[13];
		} else if (fileType.toLowerCase().equals("doc")
				|| fileType.toLowerCase().equals("docx")) {
			return contentTypeList[14];
		} else if (fileType.toLowerCase().equals("xls")
				|| fileType.toLowerCase().equals("xlsx")) {
			return contentTypeList[15];
		} else if (fileType.toLowerCase().equals("ppt")
				|| fileType.toLowerCase().equals("pptx")) {
			return contentTypeList[16];
		} else {
			return "application/data";
		}
	}

	/**
	 * 
	 * @标题:
	 * @描述:
	 * @param fileType
	 *            扩展名(不带点)
	 * @return
	 * @公司:广东数据通信网络有限公司
	 * @作者:lxm
	 * @日期:2008-9-24
	 */
	public static String getFileContentType2(String fileType) {
		Map<String, String> contentTypeList = new HashMap<String, String>(0);
		contentTypeList.put("jpeg", "image/jpeg");

		contentTypeList.put("jpg", "image/jpeg");
		contentTypeList.put("gif", "image/gif");
		contentTypeList.put("wbmp", "image/vnd.wap.wbmp");
		contentTypeList.put("png", "image/png");
		contentTypeList.put("amr", "audio/amr");
		contentTypeList.put("mid", "audio/mid");
		contentTypeList.put("midi", "audio/midi");
		contentTypeList.put("mp3", "audio/mpeg");
		contentTypeList.put("mpeg", "audio/mpeg");
		contentTypeList.put("3gp", "audio/mpeg");
		contentTypeList.put("txt", "text/plain");
		contentTypeList.put("imy", "text/x-iMelody");
		contentTypeList.put("smil", "application/smil");
		contentTypeList.put("mmf", "application/vnd.smaf");
		contentTypeList.put("wmf", "application/x-msmetafi");
		contentTypeList.put("doc", "application/msword");
		contentTypeList.put("xls", "application/vnd.ms-excel");
		contentTypeList.put("ppt", "application/vnd.ms-powerpoint");

		String rtn = contentTypeList.get(fileType.toLowerCase());
		if (rtn == null || rtn.length() <= 0)
			rtn = "application/data";
		return rtn;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @标题:
	 * @描述:
	 * @param fc
	 * @param bc
	 * @return
	 * @公司:广东数据通信网络有限公司
	 * @作者:ljt
	 * @日期:2008-6-2
	 */
	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static String[] getStrArray(List<Integer> list) {
		String[] value = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Integer temp = list.get(i);
			value[i] = temp.intValue() + "";
		}
		return value;
	}

	public static List<Integer> getIntArray(String[] str) {
		List<Integer> list = new ArrayList<Integer>();
		for (String s : str) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}
}
