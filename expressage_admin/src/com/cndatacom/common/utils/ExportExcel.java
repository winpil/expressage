package com.cndatacom.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {

	/**     
	* @author ½�ನ
	* @see 2012-03-06 ����       
	* @param file �ļ�����       
	* @param objData ������������       
	* @param sheetName ���������������       
	* @param columns ����Excel�ı�ͷ����       
	* @return       
	*/       
	public static int exportToExcel(File file, Object[] objData, String sheetName, String[] columns) {           
		int flag = 0;           
		//����������jxl.write.WritableWorkbook           
		WritableWorkbook wwb;           
		try {               
			//���ݴ�������file���󴴽���д���Excel������               
			wwb = Workbook.createWorkbook(file);                  
			/*               
			* ����һ��������sheetNameΪ����������ơ�"0"Ϊ��һ��������               
			* ��Excel��ʱ��ῴ�����½�Ĭ����3��sheet��"sheet1��sheet2��sheet3"����               
			* �����е�"0"����sheet1��������һһ��Ӧ��               
			* createSheet(sheetName, 0)һ���ǹ���������ƣ���һ���ǹ������ڹ������е�λ��               
			*/               
			WritableSheet ws = wwb.createSheet(sheetName, 0);                  
			//������Ԫ����ʽ               
			WritableCellFormat wcf = new WritableCellFormat();                  
			//������ɫ����Ϊ"��ʲô"ɫ               
			wcf.setBackground(Colour.YELLOW);                  
			/*               
			* ����ǵ�Ԫ�����ݾ�����ʾ               
			* ���кܶ�ܶ���ʽ               
			*/               
			wcf.setAlignment(Alignment.CENTRE);                  
			//�ж�һ�±�ͷ�����Ƿ�������               
			if (columns != null && columns.length > 0) {                      
				//ѭ��д���ͷ                   
				for (int i = 0; i < columns.length; i++) {                                              
					/*                       
					* ��ӵ�Ԫ��(Cell)����addCell()                       
					* ���Label����Label()                       
					* ���ݵ������кܶ��֡�����������Ҫʲô���;͵���ʲô����                       
					* �磺jxl.write.DateTime ��jxl.write.Number��jxl.write.Label                       
					* Label(i, 0, columns[i], wcf)                       
					* ����iΪ�С�0Ϊ�С�columns[i]Ϊ���ݡ�wcfΪ��ʽ                       
					* ����������˵��columns[i]��ӵ���һ��(�С����±궼�Ǵ�0��ʼ)��i�С���ʽΪʲô"ɫ"���ݾ���                       
					*/                       
					ws.addCell(new Label(i, 0, columns[i], wcf));                   
				}                      
				//�жϱ����Ƿ�������                   
				if (objData != null && objData.length > 0) {
					//������Ԫ����ʽ               
					WritableCellFormat wcf2 = new WritableCellFormat();                  
					//����ǵ�Ԫ�����ݾ�����ʾ               
					wcf2.setAlignment(Alignment.CENTRE);
					//ѭ��д���������                       
					for (int i = 0; i < objData.length; i++) {                              
						//������ֱ����Object�������ˡ���Ϊ�������û��javaBean��ʹ�ࡢת������õ�һ����¼                           
						Object obj[] = (Object[]) objData[i];                              
						//���õ��ļ�¼д��Cell(��Ԫ��)��                           
						for (int j = 0; j < obj.length; j++) {                                                              
							//���ﲻ������ʽ�ˡ�jΪ�С�(i+1)Ϊ�С���Ϊ��ͷռȥ��һ�С����Ժ���ľ�+1                               
							ws.addCell(new Label(j, i + 1, String.valueOf(obj[j]), wcf2));                           
						}                       
					}                   
				}                      
				//д��Exel������                   
				wwb.write();                      
				//�ر�Excel����������                    
				wwb.close();               
			}           
		} catch (Exception ex) {               
			ex.printStackTrace();               
			System.out.println(ex.getMessage());               
			flag = 1;           
		}              
		return flag;       
	}                     
	/**       
	* ����excel       
	* @author ½�ನ
	* @see 2012-03-06 ����       
	* @param response       
	* @param url �ļ����·�����磺 request.getRealPath("/");       
	* @param filename �ļ��� ,��:20110808.xls       
	* @param listData ����Դ       
	* @param sheetName ��ͷ����       
	* @param columns �����Ƽ���,�磺{��Ʒ���ƣ�����������}       
	*/       
	public static void exportExcle(HttpServletResponse response,String url,String filename,Object[] listData,String sheetName,String[] columns){           
		//���ݴ��������ļ�·���������ļ�           
		File file = new File(url + filename);
		//��������ķ���������Excel�ļ�           
		exportToExcel(file, listData, sheetName, columns);   
		//����һ��file����           
		File f=null;           
		try {               
			//���ݸոյ��ļ���ַ������һ��file����               
			f = new File(url + filename);
			//����ļ�������               
			if (!f.exists()) {                   
				response.sendError(404, "File not found!");               
			}                  			
			//����һ����������������               
			BufferedInputStream br = new BufferedInputStream(                       
			new FileInputStream(f));               
			byte[] buf = new byte[1024];               
			int len = 0;
			response.reset(); // �ǳ���Ҫ               
			response.setContentType("application/x-msdownload");               
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(f.getName().getBytes("gb2312"), "ISO8859-1" ));                              
			//�������������               
			OutputStream outStream = response.getOutputStream();                  
			//��ʼ���               
			while ((len = br.read(buf)) > 0)                   
				outStream.write(buf, 0, len);                  
			//�ر�������               
			br.close();               
			outStream.close();
		} catch (FileNotFoundException e) {               
			e.printStackTrace();           
		} catch (IOException e) {               
			e.printStackTrace();           
		}           
		if (f.exists()) {//�������ɾ���ļ�               
			f.delete();           
		}       
	}  
}

	
	
