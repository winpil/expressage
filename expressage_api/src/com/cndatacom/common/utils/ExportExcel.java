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
	* @author 陆培波
	* @see 2012-03-06 创建       
	* @param file 文件对象       
	* @param objData 导出内容数组       
	* @param sheetName 导出工作表的名称       
	* @param columns 导出Excel的表头数组       
	* @return       
	*/       
	public static int exportToExcel(File file, Object[] objData, String sheetName, String[] columns) {           
		int flag = 0;           
		//声明工作簿jxl.write.WritableWorkbook           
		WritableWorkbook wwb;           
		try {               
			//根据传进来的file对象创建可写入的Excel工作薄               
			wwb = Workbook.createWorkbook(file);                  
			/*               
			* 创建一个工作表、sheetName为工作表的名称、"0"为第一个工作表               
			* 打开Excel的时候会看到左下角默认有3个sheet、"sheet1、sheet2、sheet3"这样               
			* 代码中的"0"就是sheet1、其它的一一对应。               
			* createSheet(sheetName, 0)一个是工作表的名称，另一个是工作表在工作薄中的位置               
			*/               
			WritableSheet ws = wwb.createSheet(sheetName, 0);                  
			//创建单元格样式               
			WritableCellFormat wcf = new WritableCellFormat();                  
			//背景颜色设置为"那什么"色               
			wcf.setBackground(Colour.YELLOW);                  
			/*               
			* 这个是单元格内容居中显示               
			* 还有很多很多样式               
			*/               
			wcf.setAlignment(Alignment.CENTRE);                  
			//判断一下表头数组是否有数据               
			if (columns != null && columns.length > 0) {                      
				//循环写入表头                   
				for (int i = 0; i < columns.length; i++) {                                              
					/*                       
					* 添加单元格(Cell)内容addCell()                       
					* 添加Label对象Label()                       
					* 数据的类型有很多种、在这里你需要什么类型就导入什么类型                       
					* 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label                       
					* Label(i, 0, columns[i], wcf)                       
					* 其中i为列、0为行、columns[i]为数据、wcf为样式                       
					* 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中                       
					*/                       
					ws.addCell(new Label(i, 0, columns[i], wcf));                   
				}                      
				//判断表中是否有数据                   
				if (objData != null && objData.length > 0) {
					//创建单元格样式               
					WritableCellFormat wcf2 = new WritableCellFormat();                  
					//这个是单元格内容居中显示               
					wcf2.setAlignment(Alignment.CENTRE);
					//循环写入表中数据                       
					for (int i = 0; i < objData.length; i++) {                              
						//我这里直接用Object来接收了、因为情况特殊没有javaBean很痛苦、转换过后得到一条记录                           
						Object obj[] = (Object[]) objData[i];                              
						//将得到的记录写入Cell(单元格)中                           
						for (int j = 0; j < obj.length; j++) {                                                              
							//这里不引用样式了、j为列、(i+1)为行、因为表头占去了一行、所以后面的就+1                               
							ws.addCell(new Label(j, i + 1, String.valueOf(obj[j]), wcf2));                           
						}                       
					}                   
				}                      
				//写入Exel工作表                   
				wwb.write();                      
				//关闭Excel工作薄对象                    
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
	* 下载excel       
	* @author 陆培波
	* @see 2012-03-06 创建       
	* @param response       
	* @param url 文件存放路径，如： request.getRealPath("/");       
	* @param filename 文件名 ,如:20110808.xls       
	* @param listData 数据源       
	* @param sheetName 表头名称       
	* @param columns 列名称集合,如：{物品名称，数量，单价}       
	*/       
	public static void exportExcle(HttpServletResponse response,String url,String filename,Object[] listData,String sheetName,String[] columns){           
		//根据传进来的文件路径、创建文件           
		File file = new File(url + filename);
		//调用上面的方法、生成Excel文件           
		exportToExcel(file, listData, sheetName, columns);   
		//声明一个file对象           
		File f=null;           
		try {               
			//根据刚刚的文件地址、创建一个file对象               
			f = new File(url + filename);
			//如果文件不存在               
			if (!f.exists()) {                   
				response.sendError(404, "File not found!");               
			}                  			
			//创建一个缓冲输入流对象               
			BufferedInputStream br = new BufferedInputStream(                       
			new FileInputStream(f));               
			byte[] buf = new byte[1024];               
			int len = 0;
			response.reset(); // 非常重要               
			response.setContentType("application/x-msdownload");               
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(f.getName().getBytes("gb2312"), "ISO8859-1" ));                              
			//创建输出流对象               
			OutputStream outStream = response.getOutputStream();                  
			//开始输出               
			while ((len = br.read(buf)) > 0)                   
				outStream.write(buf, 0, len);                  
			//关闭流对象               
			br.close();               
			outStream.close();
		} catch (FileNotFoundException e) {               
			e.printStackTrace();           
		} catch (IOException e) {               
			e.printStackTrace();           
		}           
		if (f.exists()) {//下载完毕删除文件               
			f.delete();           
		}       
	}  
}

	
	
