package com.cndatacom.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 通过输入的中文或英文名称获得对应的拼音码信息
 * @author yab
 * 2010-06-20
 */
public class SpellConvertUtils {
	
	private static SpellConvertUtils instance = new SpellConvertUtils();
	
	private SpellConvertUtils(){
	}
	
	public static SpellConvertUtils getInstance(){
		return instance;
	}
	
	/**
	 * 取得名称的首字符
	 * @param chines 中文名英文名称
	 * @return 首字符的String形式
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public String getFirstChar(String chines) throws BadHanyuPinyinOutputFormatCombination{
		return converterToSpell(chines).substring(0,1);
	}
	
	/**
	 * 转换全部名称
	 * @param chines 中文或英文名称
	 */
	public  String converterToSpell(String chines) throws BadHanyuPinyinOutputFormatCombination {
		StringBuilder sb  = new StringBuilder();
		
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		for (char chine : chines.toCharArray()) {
			//可以传的字符范围为unicode编码中汉字的存储区域
			if (chine >= '\u4e00' && chine <= '\u9fa5') {
				try {
					sb.append(PinyinHelper.toHanyuPinyinStringArray(
							chine, format)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					throw e;
				}
			} else {
				sb.append(chine);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(SpellConvertUtils.getInstance().getFirstChar("刘德华"));
	}
}
