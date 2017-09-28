<%@ page language="java" pageEncoding="GBK"%>
<%@page
	import="java.util.*,java.io.*,
	org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper,
	java.util.concurrent.locks.*"%>
<%@ page import="org.json.simple.*" %>
<%
	//Struts2  ���� ��װ������
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	// ����ϴ����ļ��� 
	String fileName = wrapper.getFileNames("imgFile")[0];
	//���δ�������� 
	File file = wrapper.getFiles("imgFile")[0];
	//----------- ���¹����ϴ��ļ���----------------------
	final Lock lock = new ReentrantLock();
	String newName = null;
	lock.lock();
	try {
		//����Ϊ��ֹ�ļ����ظ� 
		newName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
	}finally {
		lock.unlock();
	}
	//------------ ������ -------------
	//��ȡ�ļ������ 
	FileOutputStream fos = new FileOutputStream(request.getSession()
			.getServletContext().getRealPath("/")
			+ "userupload\\attached\\" + newName);
	//���� KE �е�ͼƬ�ļ���ַ 
	String newFileName = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/userupload/attached/" + newName;
	byte[] buffer = new byte[1024];
	//��ȡ�ڴ��е�ǰ�ļ������� 
	InputStream in = new FileInputStream(file);
	try {
		int num = 0;
		while ((num = in.read(buffer)) > 0) {
			fos.write(buffer, 0, num);
		}
	} catch (Exception e) {
		e.printStackTrace(System.err);
	} finally {
		in.close();
		fos.close();
	}
	//���͸�KE 

	
	JSONObject obj = new JSONObject();
	obj.put("error", 0);
	obj.put("url", newFileName);
	out.println(obj.toJSONString());
	
	/**
	
	//���͸�KE
	out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");
	out.println("<script type='text/javascript'>");
	out.println("parent.parent.KE.plugin['image'].insert('"
			+ wrapper.getParameter("id") + "','" + newFileName + "','"
			+ wrapper.getParameter("imgTitle") + "','"
			+ wrapper.getParameter("imgWidth") + "','"
			+ wrapper.getParameter("imgHeight") + "','"
			+ wrapper.getParameter("imgBorder") + "','"
			+ wrapper.getParameter("align") + "');</script>");
	out.println("</body></html>");**/
%>