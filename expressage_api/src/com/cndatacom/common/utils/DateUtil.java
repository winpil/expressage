/**
 * 
 */
package com.cndatacom.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class DateUtil
{
    // ���ڸ�ʽ����ݣ����磺2004��2008
	public static final String DATE_FORMAT_YYYY = "yyyy";

	// ���ڸ�ʽ����ݺ��·ݣ����磺200707��200808
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	// ���ڸ�ʽ����ݺ��·ݣ����磺200707��2008-08
	public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	// ���ڸ�ʽ�������գ����磺20050630��20080808
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	//
	public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

	// ���ڸ�ʽ�������գ��ú�ֿܷ������磺2006-12-25��2008-08-08
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	/// ���ڸ�ʽ�������գ��ú�ֿܷ������磺06-12-25��08-08-08
	public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

	// ���ڸ�ʽ��������ʱ�֣����磺20001230 12:00��20080808 20:08
	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

	// ���ڸ�ʽ��������ʱ�֣����磺2000-12-30 12:00��2008-08-08 20:08
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";

	// ���ڸ�ʽ��������ʱ���룬���磺20001230120000��20080808200808
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

	// ���ڸ�ʽ��������ʱ���룬�������ú�ֿܷ���ʱ������ð�ŷֿ���
	// ���磺2005-05-10 23��20��00��2008-08-08 20:08:08
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	// ���ڸ�ʽ��������ʱ������룬���磺20001230120000123��20080808200808456
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";
    

    /**
	 * ��Dateת��Ϊָ����ʽ��ʱ���ַ�������YYYYMMDDhhmmss��yyyy-MM-dd HH:mm
	 * 
	 * @param time
	 * @return
	 */
    public static String parseDateToStr(Date time, String timeFromat)
    {
    	DateFormat dateFormat=new SimpleDateFormat(timeFromat);
    	return dateFormat.format(time);
    }
    
    /**
     * 
     * @param time
     * @param timeFromat
     * @param defaultValue Ĭ��ֵ,���Ϊ�շ��ص�ǰʱ��Ķ�Ӧ��ʽ���ڡ�
     * @author zhenhui
     * @return
     */
    public static String parseDateToStr(Date time, String timeFromat, final Date defaultValue)
    {
    	try{
    		DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        	return dateFormat.format(time);
    	}catch (Exception e){
    		if(defaultValue!=null)
				return parseDateToStr(defaultValue, timeFromat);
			else
				return parseDateToStr(new Date(), timeFromat);
    	}
    }
    /**
     * 
     * @param time
     * @param timeFromat
     * @param defaultValue
     * @author zhenhui
     * @return
     */
    public static String parseDateToStr(Date time, String timeFromat, final String defaultValue)
    {
    	try{
    		DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        	return dateFormat.format(time);
    	}catch (Exception e){
    		return defaultValue;
    	}
    }
    
    public static String dateToStr(Date time, String timeFromat,
			final String defaultValue) {
    	try{
    		DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        	return dateFormat.format(time);
    	}catch (Exception e){
    		return defaultValue;
    	}
    }
    
    /**
     * ��ָ��ʱ���ʽ���ַ���ת��ΪDate����yyyy-MM-dd HH:mm
     * @����:
     * @����:
     * @param time
     * @param timeFromat
     * @return
     * @throws ParseException
     * @��˾:�㶫����ͨ���������޹�˾
     * @����:ljt
     * @����:2007-10-23
     */
    public static Date parseStrToDate(String time, String timeFromat) throws ParseException
    {
    	if (time == null || time.equals("")) {
    		return null;
    	}
    	DateFormat dateFormat=new SimpleDateFormat(timeFromat);
    	return dateFormat.parse(time);
    }
    
    
    public static Date strToDate(String strTime, String timeFromat) {
    	return strToDate(strTime, timeFromat, null);
	}
    
    public static Date strToDate(String strTime, String timeFromat,
			Date defaultValue) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.parse(strTime);
		} catch (Exception e) {
			return defaultValue;
		}
	}
    
    /**
     * ��strTimeΪ2008-9ʱ����Ϊ2008-9-1 00:00������ʱ��
     * @����:
     * @����:�޷�ת������null.
     * @param strTime
     * @return
     * @��˾:�㶫����ͨ���������޹�˾
     * @����:lxm
     * @����:2009-11-25
     */
    public static Date strToDate(String strTime) {
    	if(strTime==null || strTime.trim().length()<=0)
    		return null;
    	
		Date date = null;
		List<String> list = new ArrayList<String>(0);
		
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		list.add(DATE_FORMAT_YYYY_MM_DD);
		//list.add(DATE_FORMAT_YY_MM_DD);
		list.add(DATE_FORMAT_YYYYMMDD);
		list.add(DATE_FORMAT_YYYY_MM);
		list.add(DATE_FORMAT_YYYYMM);
		list.add(DATE_FORMAT_YYYY);
		
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String format = (String) iter.next();
			if(strTime.indexOf("-")>0 && format.indexOf("-")<0)
				continue;
			if(strTime.indexOf("-")<0 && format.indexOf("-")>0)
				continue;
			if(strTime.length()>format.length())
				continue;
			date = strToDate(strTime, format);
			if (date != null)
				break;
		}

		return date;
	}
    
    /**
	 * ����һ������֮��������·�
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */  
    public static List<String> getMonthList(String beginDateStr, String endDateStr) {  
        // ָ��Ҫ������ʱ���ʽ  
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");  
        // ���ص��·��б�  
        String sRet = "";  
  
        // ����һЩ����  
        Date beginDate = null;  
        Date endDate = null;  
  
        GregorianCalendar beginGC = null;  
        GregorianCalendar endGC = null;  
        List<String> list = new ArrayList<String>();  
  
        try {  
            // ���ַ���parse������  
            beginDate = f.parse(beginDateStr);  
            endDate = f.parse(endDateStr);  
  
            // ��������  
            beginGC = new GregorianCalendar();  
            beginGC.setTime(beginDate);  
  
            endGC = new GregorianCalendar();  
            endGC.setTime(endDate);  
  
            // ֱ������ʱ����ͬ  
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {  
                sRet = beginGC.get(Calendar.YEAR) + "-"  
                        + (beginGC.get(Calendar.MONTH) + 1);  
                list.add(sRet);  
                // ����Ϊ��λ������ʱ��  
                beginGC.add(Calendar.MONTH, 1);  
            }  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * ����һ�����ڶ�֮����������� 
     *  
     * @param beginDateStr 
     *            ��ʼ���� 
     * @param endDateStr 
     *            �������� 
     * @return 
     */  
    public static ArrayList getDayList(String beginDateStr, String endDateStr) {  
        // ָ��Ҫ������ʱ���ʽ  
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
  
        // ����һЩ����  
        Date beginDate = null;  
        Date endDate = null;  
  
        Calendar beginGC = null;  
        Calendar endGC = null;  
        ArrayList list = new ArrayList();  
  
        try {  
            // ���ַ���parse������  
            beginDate = f.parse(beginDateStr);  
            endDate = f.parse(endDateStr);  
  
            // ��������  
            beginGC = Calendar.getInstance();  
            beginGC.setTime(beginDate);  
  
            endGC = Calendar.getInstance();  
            endGC.setTime(endDate);  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
  
            // ֱ������ʱ����ͬ  
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {  
  
                list.add(sdf.format(beginGC.getTime()));  
                // ����Ϊ��λ������ʱ��  
                beginGC.add(Calendar.DAY_OF_MONTH, 1);  
            }  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    public static List getYearList() {  
        List<Integer> list = new ArrayList<Integer>();  
        Calendar c = null;  
        c = Calendar.getInstance();  
        c.setTime(new Date());  
        int currYear = Calendar.getInstance().get(Calendar.YEAR);  
  
        int startYear = currYear - 5;  
        int endYear = currYear + 10;  
        for (int i = startYear; i < endYear; i++) {  
            list.add(new Integer(i));  
        }  
        return list;  
    }

	public static int getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}  
  
    /**
	 * �õ�ĳһ���ܵ�����
	 * 
	 * @param year
	 * @return
	 */  
    public static LinkedHashMap getWeekList(int year) {  
        LinkedHashMap map = new LinkedHashMap();  
        Calendar c = new GregorianCalendar();  
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);  
        int count = getWeekOfYear(c.getTime());  
  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dayOfWeekStart = "";  
        String dayOfWeekEnd = "";  
        for (int i = 1; i <= count; i++) {  
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));  
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));  
            map.put(new Integer(i), "��"+i+"��(��"+dayOfWeekStart + "��" + dayOfWeekEnd+")");  
        }  
        return map;  
  
    }  
      
    /** 
     * �õ�һ��������� 
     * @param year 
     * @return 
     */  
    public static int getWeekCountInYear(int year){  
        Calendar c = new GregorianCalendar();  
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);  
        int count = getWeekOfYear(c.getTime());  
        return count;  
    }  
  
    /** 
     * ȡ�õ�ǰ�����Ƕ����� 
     *  
     * @param date 
     * @return 
     */  
    public static int getWeekOfYear(Date date) {  
        Calendar c = new GregorianCalendar();  
        c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.setMinimalDaysInFirstWeek(7);  
        c.setTime(date);  
  
        return c.get(Calendar.WEEK_OF_YEAR);  
    }  
  
    /** 
     * �õ�ĳ��ĳ�ܵĵ�һ�� 
     *  
     * @param year 
     * @param week 
     * @return 
     */  
    public static Date getFirstDayOfWeek(int year, int week) {  
        Calendar c = new GregorianCalendar();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, Calendar.JANUARY);  
        c.set(Calendar.DATE, 1);  
  
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);  
  
        return getFirstDayOfWeek(cal.getTime());  
    }  
  
    /** 
     * �õ�ĳ��ĳ�ܵ����һ�� 
     *  
     * @param year 
     * @param week 
     * @return 
     */  
    public static Date getLastDayOfWeek(int year, int week) {  
        Calendar c = new GregorianCalendar();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, Calendar.JANUARY);  
        c.set(Calendar.DATE, 1);  
  
        Calendar cal = (GregorianCalendar) c.clone();  
        cal.add(Calendar.DATE, week * 7);  
  
        return getLastDayOfWeek(cal.getTime());  
    }  
      
    /** 
     * �õ�ĳ��ĳ�µĵ�һ�� 
     * @param year 
     * @param month 
     * @return 
     */  
    public static Date getFirstDayOfMonth(int year,int month){  
        month = month-1;  
        Calendar   c   =   Calendar.getInstance();     
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, month);  
          
        int day = c.getActualMinimum(c.DAY_OF_MONTH);  
  
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }  
      
    /** 
     * �ᵽĳ��ĳ�µ����һ�� 
     * @param year 
     * @param month 
     * @return 
     */  
    public static Date getLastDayOfMonth(int year,int month){  
        month = month-1;  
        Calendar   c   =   Calendar.getInstance();     
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, month);  
        int day = c.getActualMaximum(c.DAY_OF_MONTH);  
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();  
    }  
  
    /** 
     * ȡ�õ�ǰ���������ܵĵ�һ�� 
     *  
     * @param date 
     * @return 
     */  
    public static Date getFirstDayOfWeek(Date date) {  
        Calendar c = new GregorianCalendar();  
        c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday  
        return c.getTime();  
    }  
  
    /** 
     * ȡ�õ�ǰ���������ܵ����һ�� 
     *  
     * @param date 
     * @return 
     */  
    public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	
    public static void main(String [] args){
//    	Date date = strToDate("20099");
    	Date date = new Date();
//    	addMonth(date,1);
    	System.out.println(parseDateToStr(addMonth(date,1), "yyyy-MM-dd"));
    }
    
	public static String defaultDatePattern = "yyyy-MM-dd";

	public static String dateTimePattern = "yyyy-MM-dd HH:mm";

	/*
	 * static { //�����Դ�messages_zh_Cn.properties�л�ȡdefaultDatePattner. try {
	 * Locale locale = LocaleContextHolder.getLocale(); defaultDatePattern =
	 * ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_KEY,
	 * locale).getString("date.default_format"); } catch
	 * (MissingResourceException mse) { do nothing } }
	 */
	/**
	 * ���Ĭ�ϵ� date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * ������Date,ֻ��Ϊ�˴���ͳһ����
	 * @return
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * ����Ԥ��Format�ĵ�ǰ�����ַ���
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * ����Ԥ��Format�ĵ�ǰ�����ַ���
	 */
	public static String getTodayTime() {
		Date today = new Date();
		return format(today, dateTimePattern);
	}

	/**
	 * �����Զ��嵱ǰpattern�ĵ�ǰ�����ַ���
	 */
	public static String getToday(String pattern) {
		Date today = new Date();
		return format(today, pattern);
	}

	/**
	 * ʹ��Ԥ��Format��ʽ��Date���ַ���
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	/**
	 * ʹ�ò���Format��ʽ��Date���ַ���
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * ʹ��Ԥ���ʽ���ַ���תΪDate
	 */
	public static Date parse(String strDate) throws ParseException {
		 return (strDate != null && !"".equals(strDate)) ? parse(strDate, getDatePattern()) : null;
	}

	/**
	 * ʹ�ò���Format���ַ���תΪDate
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return (strDate != null && !"".equals(strDate)) ? new SimpleDateFormat(pattern).parse(strDate) : null;
	}

	/**
	 * ��������������������
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
    /** 
     * ��������ȡ������ 
     *  add by wds
     * @param date 
     * @return 
     */  
    public static String getDayBydate(Date date) {  
    	 String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);

         int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
         if (w < 0)
             w = 0;
         
         return weekDays[w];
    }  
    /** 
     * ĳ��������һ��
     *  add by wds
     * @param date 
     * @return 
     */
	public static int getMyYear(Date date) {
   	 	Calendar cal = Calendar.getInstance();
   	 	cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}  
	
	public static int getMyMonth(Date date) {
		Calendar cal = Calendar.getInstance();
   	 	cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static Date getTomorrow(Date date) {
		 Calendar cld = Calendar.getInstance();
		  
		 cld.setTime(date);
		  
		 cld.add(Calendar.DAY_OF_MONTH, 1);
		 
		 return cld.getTime();
	}
	
	/**
	 * ��֤str�Ƿ�Ϊ����
	 * @����:
	 * @����:��֤��ʽ:YYYYMMDD��YYYY_MM_DD��YYYYMMDDHHMISS��YYYYMMDD_HH_MI��YYYY_MM_DD_HH_MI��YYYYMMDDHHMISSSSS��YYYY_MM_DD_HH_MI_SS
	 * @param strTime
	 * @return nullʱ����false;trueΪ���ڣ�false��Ϊ����
	 * @��˾:�㶫����ͨ���������޹�˾
	 * @����:lxm
	 * @����:2009-11-24
	 */
	public static boolean strValidateDate(String strTime) {
    	if (strTime == null || strTime.trim().length() <= 0)
    		return false;
    	
		Date date = null;
		List<String> list = new ArrayList<String>(0);
		
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		list.add(DATE_FORMAT_YYYY_MM_DD);
		//list.add(DATE_FORMAT_YY_MM_DD);
		list.add(DATE_FORMAT_YYYYMMDD);
		//list.add(DATE_FORMAT_YYYY_MM);
		//list.add(DATE_FORMAT_YYYYMM);
		//list.add(DATE_FORMAT_YYYY);
		
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String format = (String) iter.next();
			if(strTime.indexOf("-")>0 && format.indexOf("-")<0)
				continue;
			if(strTime.indexOf("-")<0 && format.indexOf("-")>0)
				continue;
			if(strTime.length()>format.length())
				continue;
			date = strToDate(strTime.trim(), format);
			if (date != null)
				break;
		}
		
		if (date != null) {
			System.out.println("���ɵ�����:"+DateUtil.dateToStr(date, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS, "--null--"));
			return true;
		}
		return false;
	}
	
	/**
	 * ��֤�ַ��Ƿ����int����
	 * @����:
	 * @����:
	 * @param strInt
	 * @return
	 * @��˾:�㶫����ͨ���������޹�˾
	 * @����:lxm
	 * @����:2009-11-24
	 */
	public static boolean strValidateInt(String strInt){
		boolean rtn = false;
		
		if (strInt == null || strInt.trim().length() <= 0)
    		return false;
		
		try{
			Integer.parseInt(strInt.trim());
			rtn = true;
		}catch(Exception e){
			return false;
		}
		
		return rtn;
	}
	
	/**
	 * ��֤�ַ��Ƿ����float����
	 * @����:
	 * @����:
	 * @param strInt
	 * @return
	 * @��˾:�㶫����ͨ���������޹�˾
	 * @����:lxm
	 * @����:2009-11-24
	 */
	public static boolean strValidateFloat(String strFloat){
		boolean rtn = false;
		
		if (strFloat == null || strFloat.trim().length() <= 0)
    		return false;
		
		try{
			Float.parseFloat(strFloat.trim());
			rtn = true;
		}catch(Exception e){
			return false;
		}
		
		return rtn;
	}
	
	/**
	 * ��õ�ǰ����,ʱ���벿��Ϊ��
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * ����ϸ��·ݵĽ��죬ʱ���벿��Ϊ��
	 * @return
	 */
	public static Date getPreDate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
}
