package com.cndatacom.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.cndatacom.common.web.struts2.Struts2Utils;

public class SysFileUtils {

	static String imgFormat = Configure.getSingleton().values("picture.format");

	static String filePath = Configure.getSingleton()
			.values("file.virtualpath");

	private static String LINE_SEPARATOR;

	static {
		StringWriter sw = new StringWriter(4);
		PrintWriter out = new PrintWriter(sw);
		out.println();
		LINE_SEPARATOR = sw.toString();
	}

	/**
	 * bathpath:��һ��Ŀ¼��filename:�ļ�����
	 * 
	 * @author:zhaowenqiang
	 *@date:2010-7-27
	 * @param basePath
	 * @param filename
	 * @return String ����������ļ����ƣ�����·����
	 */
	public static String makeFile(String basePath) {

		String path = basePath + File.separator
				+ new SimpleDateFormat("yyyyMMdd").format(new Date());

		File dir = new File(path);

		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new IllegalStateException("����Ŀ¼ʧ��:" + dir.getPath());
			}
		}

		return path;
	}

	/**
	 * ����Ŀ��Ŀ¼�´���ָ���㼶��Ŀ¼
	 * 
	 * @author yab
	 */
	public static String makeDir(String basePath, ServletContext context)
			throws Exception {
		String path = context.getRealPath("/");
		path += basePath;

		File dir = new File(path);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new IllegalStateException("����Ŀ¼ʧ��:" + dir.getPath());
			}
		}

		return path;
	}

	public static void smbMakeDir(String smbBasePath) throws Exception {
		Assert.notNull(smbBasePath, "smbBasePath����Ϊ�գ�");

		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(Constants.SMB_PASSWORD);
		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);

		smbPath += smbBasePath;

		NtlmPasswordAuthentication nt = new NtlmPasswordAuthentication(domain,username, password);

		SmbFileUtils.mkdirs(smbPath, nt);
	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @author yab
	 */
	public static File uploadFileByName(String basePath,
			ServletContext context, String fileName, File uploadFile)
			throws Exception {

		String path = makeDir(basePath, context);

		File file = new File(path, makeFileName(fileName, uploadFile));

		FileUtils.copyFile(uploadFile, file);

		return file;
	}

	public static String uploadFileByName(String contentPath, String fileName,
			File uploadFile) throws Exception {

		String fname = makeFileName(fileName, uploadFile);
		fname = contentPath + fname;

		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);
		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(
				Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(
				Constants.SMB_PASSWORD);

		smbPath += fname;
		SmbFileUtils.smbFileWrite(new FileInputStream(uploadFile), smbPath,
				domain, username, password);

		return fname;
	}

	public static String uploadFileByName(String contentPath, String fileName,
			InputStream uploadInputStream) throws Exception {

		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);
		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(
				Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(
				Constants.SMB_PASSWORD);

		contentPath += fileName;
		smbPath += contentPath;
		SmbFileUtils.smbFileWrite(uploadInputStream, smbPath, domain, username,
				password);

		return contentPath;
	}

	public static void deleteFile(String contentPath) throws Exception {

		Assert.notNull(contentPath, "contentPath����Ϊ�գ�");

		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(
				Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(
				Constants.SMB_PASSWORD);
		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);

		smbPath += contentPath;
		SmbFileUtils.smbFileDelete(smbPath, domain, username, password);
	}

	public static InputStream smbFileRead(String contentPath) throws Exception {
		Assert.notNull(contentPath, "contentPath����Ϊ�գ�");

		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(Constants.SMB_PASSWORD);
		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);
		smbPath += contentPath;
		return SmbFileUtils.smbFileRead(smbPath, domain, username, password);
	}

	public static boolean checkFilePath(String contentPath) throws Exception {
		Assert.notNull(contentPath, "contentPath����Ϊ�գ�");

		String domain = Configure.getSingleton().values(Constants.SMB_DOMAIN);
		String username = Configure.getSingleton().values(Constants.SMB_USERNAME);
		String password = Configure.getSingleton().values(Constants.SMB_PASSWORD);
		String smbPath = Configure.getSingleton().values(Constants.SMB_PATH);
		smbPath += contentPath;

		NtlmPasswordAuthentication nt = new NtlmPasswordAuthentication(domain,
				username, password);

		SmbFile smbFile = new SmbFile(smbPath, nt);

		if (null == smbFile || !smbFile.exists()) {
			return false;
		}

		return true;
	}

	/**
	 * �����ļ�����
	 * 
	 * @author:zhaowenqiang
	 *@date:2010-8-9
	 * @param fileName
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String makeFileName(String fileName, File file)
			throws Exception {

		StringBuffer imgNameBuffer = new StringBuffer(new SimpleDateFormat(
				"yyyyMMddHHmmss").format(new Date())
				+ "_");

		imgNameBuffer.append(RandomStringUtils.randomAlphanumeric(3) + "_");

		//String extension = FilenameUtils.getExtension(fileName);

		FileInputStream fis = new FileInputStream(file);

		BufferedImage bufferedImg = ImageIO.read(fis);

		if (null != bufferedImg) {
			imgNameBuffer.append(bufferedImg.getWidth() + "x"
					+ bufferedImg.getHeight());
		}

		//return imgNameBuffer.toString() + "." + extension;
		return fileName;
	}
	
	public static String makePicFileName(String fileName, File file)
	throws Exception {
		
		StringBuffer imgNameBuffer = new StringBuffer(new SimpleDateFormat(
		"yyyyMMddHHmmss").format(new Date())
		+ "_");
		
		imgNameBuffer.append(RandomStringUtils.randomAlphanumeric(3) + "_");
		
		String extension = FilenameUtils.getExtension(fileName);
		
		FileInputStream fis = new FileInputStream(file);
		
		BufferedImage bufferedImg = ImageIO.read(fis);
		
		if (null != bufferedImg) {
			imgNameBuffer.append(bufferedImg.getWidth() + "x"
					+ bufferedImg.getHeight());
		}
		
		return imgNameBuffer.toString() + "." + extension;
	}

	/**
	 * ����ͼƬ�ļ�������Ҫ�ļ�����·��ȥ��ǰ׺��
	 * 
	 * @author:zhaowenqiang
	 *@date:2010-8-9
	 * @return
	 * @throws IOException
	 */
	public static File makeImgFileByName(String basePath, String fileName,
			File file) throws Exception {

		File imgFile = new File(SysFileUtils.makeFile(basePath), SysFileUtils
				.makeFileName(fileName, file));

		FileUtils.copyFile(file, imgFile);

		return imgFile;

	}

	/**
	 *��ȡҪ�󱣴浽���ݿ��е�·��
	 * 
	 * @author:zhaowenqiang
	 *@date:2010-8-9
	 *@param file
	 */
	public static String getRelativePath(File file) {

		return StringUtils.substringAfter(StringUtils.substringAfter(file
				.toURI().getPath(), "/"), "/");
	}

	public static String getFilePath() {
		return filePath;
	}

	public static String getSavePath(String fieldName, Object ob) {
		if (ob != null && fieldName != null) {
			return Configure.getSingleton().values(
					ob.getClass().getName() + "." + fieldName);
		} else {
			return "";
		}
	}

	/**
	 * ��һ������д�뵽�ļ��������
	 * 
	 * @param out�ļ������
	 * @param lineҪд���һ������
	 * @param lineSeparator�еĽ�����
	 */
	public static void writeLine(OutputStream out, String line,String lineSeparator) throws IOException {

		if(null == out || null == line) {
			return;
		}

		out.write(line.toString().getBytes());
		
		if(null == lineSeparator) {
			lineSeparator = LINE_SEPARATOR;
		}

		out.write(lineSeparator.getBytes());
	}
	
	/**
	 * �������е���������д�뵽�ļ��������
	 * 
	 * @param out �ļ������
	 */
	public static void writeLine(OutputStream out, Collection<String> lines,String lineSeparator) throws IOException {

		if(null == out || null == lines) {
			return;
		}

		for(String line : lines) {
			out.write(line.toString().getBytes());
			
			if(null == lineSeparator) {
				lineSeparator = LINE_SEPARATOR;
			}
			
			out.write(lineSeparator.getBytes());
		}
	}

	/**
	 * ��ָ����ģʽ��һ������д�뵽ָ�����ļ�·����
	 * 
	 * @param filePath �ļ�·��
	 * @param line �����
	 * @param lineSeparator �н�����
	 * @param isAppend �Ƿ���׷�ӵķ�ʽ
	 * @throws FileNotFoundException
	 *             �ļ�δ�ҵ��쳣
	 */
	public static void writeLine(String filePath, String line,String lineSeparator, Boolean isAppend)
			throws FileNotFoundException,IOException {
		OutputStream out = new FileOutputStream(filePath, isAppend);
		try {
			writeLine(out ,line ,lineSeparator);
		}finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	/**
	 * ��ָ����ģʽ�������е�����д�뵽ָ�����ļ�·����
	 * 
	 * @param filePath �ļ�·��
	 * @param lines ��������
	 * @param lineSeparator �н�����
	 * @param isAppend �Ƿ���׷�ӵķ�ʽ
	 * @throws FileNotFoundException �ļ�δ�ҵ��쳣
	 */
	public static void writeLine(String filePath, Collection<String> lines,String lineSeparator, Boolean isAppend)
			throws FileNotFoundException,IOException {
		OutputStream out = new FileOutputStream(filePath, isAppend);
		try {
			writeLine(out ,lines ,lineSeparator);
		}finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	public static String uploadFile(String fileName,File file) throws Exception {
		Assert.notNull(fileName,"�ϴ��ļ����Ʋ���Ϊ�գ�");
		Assert.notNull(file,"�ϴ��ļ�����Ϊ�գ�");
		String appPath = Struts2Utils.getSession().getServletContext().getRealPath("/");
		
		String basePath = "/userupload";
		
		try{
			basePath = Configure.getSingleton().values(Constants.UPLOAD_PATH);
		}catch(Exception e){
		} 
		
		makeDir(basePath,Struts2Utils.getSession().getServletContext());
		
		String savePath = appPath + basePath;
		
		String saveFileName = makeFileName(fileName,file);
		
		File saveFile = new File(savePath,saveFileName);
		
		FileUtils.copyFile(file, saveFile);
		
		return basePath + "/" + saveFile.getName();
	}
	
	
	public static String uploadPicFile(String fileName,File file) throws Exception {
		Assert.notNull(fileName,"�ϴ��ļ����Ʋ���Ϊ�գ�");
		Assert.notNull(file,"�ϴ��ļ�����Ϊ�գ�");
		String appPath = Struts2Utils.getSession().getServletContext().getRealPath("/");
		
		String basePath = "/userupload";
		
		try{
			basePath = Configure.getSingleton().values(Constants.UPLOAD_PATH);
		}catch(Exception e){
		} 
		
		makeDir(basePath,Struts2Utils.getSession().getServletContext());
		
		String savePath = appPath + basePath;
		
		String saveFileName = makePicFileName(fileName,file);
		
		File saveFile = new File(savePath,saveFileName);
		
		FileUtils.copyFile(file, saveFile);
		
		return basePath + "/" + saveFile.getName();
	}

}
