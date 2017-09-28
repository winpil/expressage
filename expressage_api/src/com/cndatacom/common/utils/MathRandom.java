package com.cndatacom.common.utils;

public class MathRandom {
	/** 
     * 0���ֵĸ���Ϊ%5 
     */  
	 public static double rate0 = 0.05;  
	 /** 
	     * 1���ֵĸ���Ϊ%25 
	     */  
	 public static double rate1 = 0.25;  
	 /** 
	     * 2���ֵĸ���Ϊ%65 
	     */  
	 public static double rate2 = 0.65;  
	 /** 
	     * 3���ֵĸ���Ϊ%10 
	     */  
	 public static double rate3 = 0.02;  
	 /** 
	     * 4���ֵĸ���Ϊ%4 
	     */  
	 public static double rate4 = 0.02;  
	 /** 
	     * 5���ֵĸ���Ϊ%1 
	     */  
	 public static double rate5 = 0.01;  
	  
	 /** 
	  * Math.random()����һ��double�͵���������ж�һ�� 
	  * ����0���ֵĸ���Ϊ%50�������0��0.50�м�ķ���0 
	     * @return int 
	     * 
	     */  
	 public  static  int PercentageRandom(double d0,int i0,double d1,int i1,double d2,int i2,double d3,int i3,double d4,int i4,double d5,int i5)  
	 {  
	  double randomNumber;  
	  randomNumber = Math.random();  
	  if (randomNumber >= 0 && randomNumber <= d0)  
	  {  
	   return i0;  
	  }  
	  else if (randomNumber >= d0 / 100 && randomNumber <= d0 + d1)  
	  {  
	   return i1;  
	  }  
	  else if (randomNumber >= d0 + d1  
	    && randomNumber <= d0 + d1 + d2)  
	  {  
	   return i2;  
	  }  
	  else if (randomNumber >= d0 + d1 + d2  
	    && randomNumber <= d0 + d1 + d2 + d3)  
	  {  
	   return i3;  
	  }  
	  else if (randomNumber >= d0 + d1 + d2 + d3  
	    && randomNumber <= d0 + d1 + d2 + d3 + d4)  
	  {  
	   return i4;  
	  }  
	  else if (randomNumber >= d0 + d1 + d2 + d3 + d4  
	    && randomNumber <= d0 + d1 + d2 + d3 + d4  
	      + d5)  
	  {  
	   return i5;  
	  }  
	  return -1;  
	 }  
	  
	 /** 
	  * ���������� 
	     * @param agrs 
	     */  
	 public static void main(String[] agrs)  
	 {  
	  int i = 0;  
	  MathRandom a = new MathRandom();  
	  for (i = 0; i <= 100; i++)//��ӡ100�����Ը��ʵ�׼ȷ��  
	  {  
	   System.out.println(a.PercentageRandom(0.2,2,0.3,3,0.4,4,0.05,5,0.01,6,0.01,7));  
	  }  
	 } 
}
