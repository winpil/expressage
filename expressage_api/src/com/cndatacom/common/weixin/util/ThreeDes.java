package com.cndatacom.common.weixin.util;

import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDes {
	
	private static final String Algorithm = "DESede"; //定义加密算法,可用
	// DES,DESede,Blowfish
	
	private static final byte[] keyBytes = { 0x10, 0x21, 0x4F, 0x58, (byte) 0x88, 0x10,
			0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
			0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2 }; // 24字节的密�?双方约定部分)

	// keybyte为加密密钥，长度�?4字节
	// src为被加密的数据缓冲区（源�?
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			//System.out.print(deskey);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度�?4字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，�?��字符串的长度是数组长度的两�?
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数�?��在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		// �?��128�?
		String result = sb.toString();
		//if(result.length()>128){
		//result = result.substring(0,result.length()-1);
		//}
		return result;
	}
	
	/** 
	  * 将两个ASCII字符合成�?��字节�?
	  * 如："EF"--> 0xEF 
	  * @param src0 byte 
	  * @param src1 byte 
	  * @return byte 
	  */ 
	public static byte uniteBytes(byte src0, byte src1) { 
	   byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue(); 
	   _b0 = (byte)(_b0 << 4); 
	   byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue(); 
	   byte ret = (byte)(_b0 ^ _b1); 
	   return ret; 
	} 
	
	/** 
	  * 将指定字符串src，以每两个字符分割转换为16进制形式 
	  * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9} 
	  * @param src String 
	  * @return byte[] 
	  */ 
	public static byte[] HexString2Bytes(String src){ 
	   byte[] ret = new byte[8]; 
	   byte[] tmp = src.getBytes(); 
	   for(int i=0; i<8; i++){ 
	     ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]); 
	   } 
	   return ret; 
	}
	
	public static String printHexString( byte[] b) { 
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < b.length; i++) { 
	   	String hex = Integer.toHexString(b[i] & 0xFF); 
	   	if (hex.length() == 1) { 
	   		hex = '0' + hex; 
	   	} 
	   	sf.append(hex.toUpperCase()); 
	   	}
		return sf.toString();
	}
	
	/** 16进制数字字符�? */ 
	private static String hexString= "0123456789ABCDEF"; 
	
	/**将每2�?6进制整数组装成一个字�?
	 * 
	 * @param bytes
	 * @return
	 */ 
	public static byte[] hex2byte(String hex) { 
		ByteArrayOutputStream baos=new ByteArrayOutputStream(hex.length()/2); 
		for(int i=0;i<hex.length();i+=2) 
			baos.write((hexString.indexOf(hex.charAt(i)) <<4 | hexString.indexOf(hex.charAt(i+1)))); 
		return baos.toByteArray(); 
	}
	
	/**
	 * 加密方法
	 * @Title: 
	 * @Description: TODO 
	 * @param @param srcStr
	 * @param @return
	 * @return 
	 * @throws
	 */
	public static String getDes(String srcStr){
		String desStr = null;
		try{
			if(srcStr==null || "".equals(srcStr)) return null;
			
			byte[] encoded = encryptMode(keyBytes, srcStr.getBytes());
			desStr = byteArr2HexStr(encoded);
			
			if(srcStr==null || "".equals(srcStr)) return null;
			else return desStr.toUpperCase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return desStr;
	}
	
	/**
	 * 解密方法
	 * @Title: 
	 * @Description: TODO 
	 * @param @param desStr
	 * @param @return
	 * @return 
	 * @throws
	 */
	public static String getUnDes(String desStr){
		String unDesStr = null;
		try{
			if(desStr==null || "".equals(desStr)) return null;
			
			byte[] srcBytes = decryptMode(keyBytes, hex2byte(desStr));
			unDesStr = new String(srcBytes);
			
			if(unDesStr==null || "".equals(unDesStr)) return null;
			else return unDesStr;
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return unDesStr;
	}
	
	public static void main(String[] args) throws Exception {
	
//		//添加新安全算�?如果用JCE就要把它添加进去
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		
//		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
//		0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
//		0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
//		(byte) 0xE2 }; // 24字节的密�?
		
//		System.out.println(printHexString(keyBytes));
//		String key = "22rtecsortec33rtecsore44";
//		System.out.println(ThreeDes.byteArr2HexStr(key.getBytes()));
//		
//		String szSrc = "51586914#13348811109";
//		System.out.println("加密前的字符�?" + szSrc);
//		
//		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
//		System.out.println("加密后的字符�?" + ThreeDes.byteArr2HexStr(encoded));
//		
//		byte[] srcBytes = decryptMode(keyBytes, encoded);
//		System.out.println("解密后的字符�?" + (new String(srcBytes)));
		
		System.out.println("加密："+getDes("zh123#18680503648"));
		System.out.println("解密："+getUnDes("AFB57B085E71D2C0B986DA397F40095D0B7D2E068D0B0D980DF9F25AA103F1D9DAEFE769FFA8BF0AA89E6A43D64D127E1EC0D5B89F93EE2C6F2DAFF39B78CEEE0899C41372A427289ABC8B9435E984AE"));
	}
	
}
