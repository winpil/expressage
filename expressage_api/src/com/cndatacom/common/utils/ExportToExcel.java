/**
 * 
 */
package com.cndatacom.common.utils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.cndatacom.common.orm.Page;


/**
 * @author Administrator
 * 
 */
public class ExportToExcel {
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private static int rowIndex = 0;

    /** 
     * 导出数据到EXCEL 
     * @param page 
     * @param out 
     * @param sheetName 
     * @param colName
     * @param colKey
     * @param colCount
     * @return 
     */  
	public static void export(Page page, OutputStream out, String sheetName, String[] colName, String[] colKey, int colCount, Date startTime, Date endTime) {
		try {

			// 初始化excel参数
			initExcelConfig(sheetName, colCount);
			// 输出”抬头“
			writeTitle(sheetName, colCount);
			// 输出”查询条件“
			writeSearchCondition(startTime, endTime, colCount);
			// 输出”表头“
			writeDataHeader(colName, colCount);
			// 输出”数据列表“
			writeDataList(page, colKey, colCount);

			workbook.write(out);
			out.flush();
			// 操作结束，关闭文件
			// out.close();
			System.out.println("文件生成...");

		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}
	}
	
    /** 
     * 导出数据到EXCEL 
     * @param page 
     * @param out 
     * @param sheetName 
     * @param colName
     * @param colKey
     * @param colCount
     * @return 
     */  
	public static void export(Page page, OutputStream out, String sheetName, String[] colName, String[] colKey, int colCount, String condition) {
		try {

			// 初始化excel参数
			initExcelConfig(sheetName, colCount);
			// 输出”抬头“
			writeTitle(sheetName, colCount);
			// 输出”查询条件“
			writeSearchCondition(condition, colCount);
			// 输出”表头“
			writeDataHeader(colName, colCount);
			// 输出”数据列表“
			writeDataList(page, colKey, colCount);

			workbook.write(out);
			out.flush();
			// 操作结束，关闭文件
			// out.close();
			System.out.println("文件生成...");

		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}
	}

	/**
	 * 初始化excel 文件参数
	 */
	private static void initExcelConfig(String sheetName, int colCount) {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet(sheetName);
		for(int i=0;i<colCount;i++){
			sheet.setColumnWidth(i, 30 * 256);
		}
		rowIndex = 0;
	}

	/**
	 * 输出标题
	 */
	private static void writeTitle(String sheetName, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 14);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 在索引0的位置创建单元格（左上端）
		HSSFCell cell = row.createCell((short) 0);
		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入一些内容
		cell.setCellValue(sheetName + "报表");
		cell.setCellStyle(cellStyle);

		rowIndex++;

		// /合并前面栏目的一级栏目名称单元格
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (colCount-1)));
	}

	/**
	 * 输出查询条件
	 */
	private static void writeSearchCondition(Date startTime, Date endTime, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuffer searchCondition=new StringBuffer();
		searchCondition.append("查询时间：");
		searchCondition.append(sdf.format(startTime));
		searchCondition.append("~");
		searchCondition.append(sdf.format(endTime));
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// 在索引0的位置创建单元格（左上端）
		HSSFCell cell = row.createCell((short) 0);
		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入一些内容
		cell.setCellValue(searchCondition.toString());
		cell.setCellStyle(cellStyle);
		
		// /合并前面栏目的一级栏目名称单元格
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (colCount-1)));

		rowIndex++;
	}
	
	/**
	 * 输出查询条件
	 */
	private static void writeSearchCondition(String condition, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// 在索引0的位置创建单元格（左上端）
		HSSFCell cell = row.createCell((short) 0);
		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 在单元格中输入一些内容
		cell.setCellValue(condition);
		cell.setCellStyle(cellStyle);
		
		// /合并前面栏目的一级栏目名称单元格
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (colCount-1)));

		rowIndex++;
	}

	/**
	 * 输出表头
	 */
	private static void writeDataHeader(String[] colName, int colCount) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 设置一个单元格边框颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置一个单元格边框颜色
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

		HSSFRow row = sheet.createRow((short) rowIndex);

		for(int i=0;i<colCount;i++){
			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell((short) i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(colName[i]);
			cell.setCellStyle(cellStyle);
		}
		
		rowIndex++;
	}

	/**
	 * 输出统计数据
	 */
	private static void writeDataList(Page page, String[] colKey, int colCount) {
		// 在索引0的位置创建行（最顶端的行）
		List mapData = page.getResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置一个单元格边框颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置一个单元格边框颜色
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (Iterator iterator = mapData.iterator(); iterator.hasNext();) {
			HashMap<String,Object> hashMap = (HashMap<String,Object>) iterator.next();

			HSSFRow titleRrow = sheet.createRow((short) rowIndex);
			
			for(int i=0;i<colCount;i++){
				// 在索引0的位置创建单元格（左上端）
				HSSFCell titleCell = titleRrow.createCell((short) i);
				
				// 定义单元格为字符串类型
				titleCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容,KEY中包含“time”是时间
				if(colKey[i].toUpperCase().contains("TIME")){
					titleCell.setCellValue(sdf.format(hashMap.get(colKey[i])));
				} else {
					titleCell.setCellValue(hashMap.get(colKey[i]).toString());
				}
				titleCell.setCellStyle(cellStyle);
			}

			rowIndex++;
		}
	}
}
