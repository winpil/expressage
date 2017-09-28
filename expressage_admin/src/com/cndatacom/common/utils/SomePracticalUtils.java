package com.cndatacom.common.utils;


import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class SomePracticalUtils {
	
	/**
	 * 获取中文首字母，展现到DWR上
	 *@author:zhaowenqiang
	 *@date:2010-6-23
	 * @param chinese
	 * @return
	 */
	public String getChineseFirstLetter(String chinese) {
	  
	  try {
		  if(chinese!=null && !chinese.trim().equals("")){
			  return SpellConvertUtils.getInstance().getFirstChar(chinese);
		  }else{
			  return "";
		  }
	  } catch (BadHanyuPinyinOutputFormatCombination e) {
		  e.printStackTrace();
	  }
	  return null;
	}

	/**
	 * 转换字符编码
	 *@author:zhaowenqiang
	 *@date:2010-6-24
	 * @param s
	 * @return
	 */
	public static String convert(String s){
        String result = null;
        byte[] temp ;
        try{
            temp = s.getBytes("iso-8859-1");
            result =  new String(temp,"gb2312");
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return result;

    }

}
