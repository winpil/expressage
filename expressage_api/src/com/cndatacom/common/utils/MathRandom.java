package com.cndatacom.common.utils;

public class MathRandom {
	/** 
     * 0出现的概率为%5 
     */  
	 public static double rate0 = 0.05;  
	 /** 
	     * 1出现的概率为%25 
	     */  
	 public static double rate1 = 0.25;  
	 /** 
	     * 2出现的概率为%65 
	     */  
	 public static double rate2 = 0.65;  
	 /** 
	     * 3出现的概率为%10 
	     */  
	 public static double rate3 = 0.02;  
	 /** 
	     * 4出现的概率为%4 
	     */  
	 public static double rate4 = 0.02;  
	 /** 
	     * 5出现的概率为%1 
	     */  
	 public static double rate5 = 0.01;  
	  
	 /** 
	  * Math.random()产生一个double型的随机数，判断一下 
	  * 例如0出现的概率为%50，则介于0到0.50中间的返回0 
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
	  * 测试主程序 
	     * @param agrs 
	     */  
	 public static void main(String[] agrs)  
	 {  
	  int i = 0;  
	  MathRandom a = new MathRandom();  
	  for (i = 0; i <= 100; i++)//打印100个测试概率的准确性  
	  {  
	   System.out.println(a.PercentageRandom(0.2,2,0.3,3,0.4,4,0.05,5,0.01,6,0.01,7));  
	  }  
	 } 
}
