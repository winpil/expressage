package com.cndatacom.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NongLiUtils {

	final static long[] lunarInfo=new long[] {0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
		0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
		0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
		0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
		0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
		0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
		0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
		0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
		0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
		0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
		0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
		0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
		0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
		0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
		0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0
	};
	
	//====== ����ũ�� y���������
	public final  static int lYearDays(int y){
	        int i,sum = 348;
	        for(i=0x8000; i>0x8; i>>=1)
	        {
	                if ((lunarInfo[y-1900] & i)!=0) sum+=1;
	        }
	        return(sum+leapDays(y));
	}
	
	//====== ����ũ�� y�����µ�����
	public final  static int leapDays(int y)
	{
	        if(leapMonth(y)!=0)
	        {
	                if ((lunarInfo[y-1900] & 0x10000)!=0) return 30;
	                else return 29;
	        }
	        else  return 0;
	}
	
	//====== ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
	public final static int leapMonth(int y){
	        return (int)(lunarInfo[y-1900] & 0xf);
	}
	
	//====== ����ũ�� y��m�µ�������
	public final static int monthDays(int y,int m){
	        if ((lunarInfo[y-1900] & (0x10000>>m))==0) return 29;
	        else return 30;
	}
	
	//====== ����ũ�� y�����Ф
	public final static String AnimalsYear(int y){
	        final String[] Animals=new String[] {"��","ţ","��","��","��","��","��","��","��","��","��","��"};
	        return Animals[(y-4)%12];
	}
	
	//====== ���� ���յ�offset ���ظ�֧, 0=����
	public final static String cyclicalm(int num){
	        final String[] Gan=new String[] {"��","��","��","��","��","��","��","��","��","��"};
	        final String[] Zhi=new String[] {"��","��","��","î","��","��","��","δ","��","��","��","��"};
	        return (Gan[num%10]+Zhi[num%12]);
	}
	
	//====== ���� offset ���ظ�֧, 0=����
	public final static String cyclical(int y){
	        int num=y-1900+36;
	        return (cyclicalm(num));
	}
	
	//����ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4
    //.dayCyl5 .isLeap6
	public final long[] Lunar(int y,int m){
	        final int[] year20=new int[]{1,4,1,2,1,2,1,1,2,1,2,1};
	        final int[] year19=new int[]{0,3,0,1,0,1,0,0,1,0,1,0};
	        final int[] year2000=new int[]{0,3,1,2,1,2,1,1,2,1,2,1};
	        long[] nongDate = new long[7];
	        int i=0,temp=0,leap=0;
	        Date baseDate =new Date(1900,1,31);
	        Date objDate  =new Date(y,m,1);
	        long offset = (objDate.getTime()-baseDate.getTime())/86400000L;
	        if (y<2000) offset+=year19[m-1];
	        if (y>2000) offset+=year20[m-1];
	        if (y==2000) offset+=year2000[m-1];
	        nongDate[5] = offset + 40;
	        nongDate[4] = 14;
	        for(i=1900; i<2050 && offset>0; i++) {
	                temp = lYearDays(i);
	                offset -= temp;
	                nongDate[4] += 12;
	        }
	        if(offset<0) {
	                offset += temp;
	                i--;
	                nongDate[4] -= 12;
	        }
	        nongDate[0] = i;
	        nongDate[3] = i-1864;
	        leap = leapMonth(i); //���ĸ���
	        nongDate[6] = 0;
	        for(i=1; i<13 && offset>0; i++) {
	                //����
	                if(leap>0 && i==(leap+1) && nongDate[6]==0)
	                { --i; nongDate[6] = 1; temp = leapDays((int) nongDate[0]); }
	                else
	                { temp = monthDays((int) nongDate[0],i); }
	                //�������
	                if(nongDate[6]==1 && i==(leap+1)) nongDate[6] = 0;
	                offset -= temp;
	                if(nongDate[6] == 0) nongDate[4] ++;
	        }
	        if(offset==0 && leap>0 && i==leap+1){
	                if(nongDate[6]==1)
	                { nongDate[6] = 0; }
	                else
	                { nongDate[6] = 1; --i; --nongDate[4];}
	        }
	        if(offset<0){ offset += temp; --i; --nongDate[4]; }
	        nongDate[1] = i;
	        nongDate[2] = offset + 1;
	        return nongDate;
	}
	
	//����y��m��d�ն�Ӧ��ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	public final  static long[] calElement(int y,int m,int d){
	        long[] nongDate = new long[7];
	        int i=0,temp=0,leap=0;
	        Date baseDate = new Date(0,0,31);
	        Date objDate  = new Date(y-1900,m-1,d);
	        long offset = (objDate.getTime()-baseDate.getTime())/86400000L;
	        nongDate[5] = offset + 40;
	        nongDate[4] = 14;
	        for(i=1900; i<2050 && offset>0; i++) {
	                temp = lYearDays(i);
	                offset -= temp;
	                nongDate[4] += 12;
	        }
	        if(offset<0) {
	                offset += temp;
	                i--;
	                nongDate[4] -= 12;
	        }
	        nongDate[0] = i;
	        nongDate[3] = i-1864;
	        leap = leapMonth(i); //���ĸ���
	        nongDate[6] = 0;
	        for(i=1; i<13 && offset>0; i++) {
	                //����
	                if(leap>0 && i==(leap+1) && nongDate[6]==0)
	                { --i; nongDate[6] = 1; temp = leapDays((int)nongDate[0]); }
	                else
	                { temp = monthDays((int)nongDate[0],i); }
	                //�������
	                if(nongDate[6]==1 && i==(leap+1)) nongDate[6] = 0;
	                offset -= temp;
	                if(nongDate[6] == 0) nongDate[4] ++;
	        }
	        if(offset==0 && leap>0 && i==leap+1){
	                if(nongDate[6]==1)
	                { nongDate[6] = 0; }
	                else
	                { nongDate[6] = 1; --i; --nongDate[4];}
	        }
	        if(offset<0){ offset += temp; --i; --nongDate[4]; }
	        nongDate[1] = i;
	        nongDate[2] = offset + 1;
	        return nongDate;
	}
	  public static String getchina(int day){
	    String a = "";
	    if(day==10)
	      return "��ʮ";
	    int two = (int)((day)/10);
	    if(two==0)
	      a = "��";
	    if(two==1)
	      a = "ʮ";
	    if(two==2)
	      a = "إ";
	    if(two==3)
	      a = "ئ";
	    int one = (int)(day%10);
	    switch(one){
	      case 1:a += "һ";break;
	      case 2:a += "��";break;
	      case 3:a += "��";break;
	      case 4:a += "��";break;
	      case 5:a += "��";break;
	      case 6:a += "��";break;
	      case 7:a += "��";break;
	      case 8:a += "��";break;
	      case 9:a += "��";break;
	    }
	    return a;
	  }
	  
	  public static String getNoLiByDate(Date date){
		  
		  	Calendar cld = Calendar.getInstance();
		  	
		  	cld.setTime(date);
		  	
		    int year = cld.get(Calendar.YEAR);
		    int month = cld.get(Calendar.MONTH)+1;
		    int day = cld.get(Calendar.DAY_OF_MONTH);

		    long[] l = calElement(year,month,day);
		    
		    String n = "";
		    
		    switch((int)(l[1])){
		      case 1:n="һ";break;
		      case 2:n="��";break;
		      case 3:n="��";break;
		      case 4:n="��";break;
		      case 5:n="��";break;
		      case 6:n="��";break;
		      case 7:n="��";break;
		      case 8:n="��";break;
		      case 9:n="��";break;
		      case 10:n="ʮ";break;
		      case 11:n="ʮһ";break;
		      case 12:n="ʮ��";break;
		    }
		    
		    return "ũ��"+n+"��"+getchina((int)(l[2]));
		  
	  }
	  
	  public static String getTodayNongLi(){
		  return getNoLiByDate(new Date());
	  }
	  
	  //ȡ�õ�ǰ���ڵ����
	  public static String getYearByDate(Date date){
		  Calendar cld = Calendar.getInstance();
		  
		  cld.setTime(date);
		  
		  return String.valueOf(cld.get(Calendar.YEAR));
	  }
	  
	  //ȡ��ָ�����ڵ��·�
	  public static String getMonthByDate(Date date){
		  Calendar cld = Calendar.getInstance();
		  
		  cld.setTime(date);
		  
		  return String.valueOf(cld.get(Calendar.MONTH)+1);
	  }
	  
	  public static String getDayByDate(Date date){
		  Calendar cld = Calendar.getInstance();
		  
		  cld.setTime(date);
		  
		  return String.valueOf(cld.get(Calendar.DAY_OF_MONTH));
	  }
	  
	  public static String getTomorrow(Date date){
		  Calendar cld = Calendar.getInstance();
		  
		  cld.setTime(date);
		  
		  cld.add(Calendar.DAY_OF_MONTH, 1);
		  
		 return new SimpleDateFormat("yyyy-MM-dd").format(cld.getTime());
	  }
	  
	  public static String getWeekByDate(Date date){
		  Calendar cld = Calendar.getInstance();
		  
		  cld.setTime(date);
		  
		  String week = "";
		    
	    switch(cld.get(Calendar.DAY_OF_WEEK)){
	      case 1:week = "��";break;
	      case 2:week = "һ";break;
	      case 3:week = "��";break;
	      case 4:week = "��";break;
	      case 5:week = "��";break;
	      case 6:week = "��";break;
	      case 7:week = "��";break;
	    }
	    
	    return week;
	  }
	  
	  //����y��m��d�ն�Ӧ��ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	  public static void main(String[] args){
	    /*Calendar cld = Calendar.getInstance();
	    int year = cld.get(Calendar.YEAR);
	    int month = cld.get(Calendar.MONTH)+1;
	    int day = cld.get(Calendar.DAY_OF_MONTH);
	    String week = "";
	    long[] l = calElement(year,month,day);
	    
	    switch(cld.get(Calendar.DAY_OF_WEEK)){
	      case 1:week = "��";break;
	      case 2:week = "һ";break;
	      case 3:week = "��";break;
	      case 4:week = "��";break;
	      case 5:week = "��";break;
	      case 6:week = "��";break;
	      case 7:week = "��";break;
	    }
	    String n = "";
	    
	    switch((int)(l[1])){
	      case 1:n="һ";break;
	      case 2:n="��";break;
	      case 3:n="��";break;
	      case 4:n="��";break;
	      case 5:n="��";break;
	      case 6:n="��";break;
	      case 7:n="��";break;
	      case 8:n="��";break;
	      case 9:n="��";break;
	      case 10:n="ʮ";break;
	      case 11:n="ʮһ";break;
	      case 12:n="ʮ��";break;
	    }
	    
	    try {
	      String a = "����ʱ�䣺 "+year+"��"+month+"��"+day+"��           ����"+week+"��ũ��"+n+"��"+getchina((int)(l[2]));
	      String b = "����ʱ�䣺 "+year+"��"+month+"��"+day+"�ա�����"+week+"��ũ��"+n+"��"+getchina((int)(l[2]));
	      BufferedWriter outout = new BufferedWriter(new FileWriter("rili.html",false));
	      outout.write(a);
	      System.out.println(a);
	      outout.close();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }*/
		  System.out.println(getTomorrow(new Date()));;
	  }
	}

