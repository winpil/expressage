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
     * �������ݵ�EXCEL 
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

			// ��ʼ��excel����
			initExcelConfig(sheetName, colCount);
			// �����̧ͷ��
			writeTitle(sheetName, colCount);
			// �������ѯ������
			writeSearchCondition(startTime, endTime, colCount);
			// �������ͷ��
			writeDataHeader(colName, colCount);
			// ����������б�
			writeDataList(page, colKey, colCount);

			workbook.write(out);
			out.flush();
			// �����������ر��ļ�
			// out.close();
			System.out.println("�ļ�����...");

		} catch (Exception e) {
			System.out.println("������ xlCreate() : " + e);
		}
	}
	
    /** 
     * �������ݵ�EXCEL 
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

			// ��ʼ��excel����
			initExcelConfig(sheetName, colCount);
			// �����̧ͷ��
			writeTitle(sheetName, colCount);
			// �������ѯ������
			writeSearchCondition(condition, colCount);
			// �������ͷ��
			writeDataHeader(colName, colCount);
			// ����������б�
			writeDataList(page, colKey, colCount);

			workbook.write(out);
			out.flush();
			// �����������ر��ļ�
			// out.close();
			System.out.println("�ļ�����...");

		} catch (Exception e) {
			System.out.println("������ xlCreate() : " + e);
		}
	}

	/**
	 * ��ʼ��excel �ļ�����
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
	 * �������
	 */
	private static void writeTitle(String sheetName, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 14);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ������0��λ�ô�����Ԫ�����϶ˣ�
		HSSFCell cell = row.createCell((short) 0);
		// ���嵥Ԫ��Ϊ�ַ�������
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// �ڵ�Ԫ��������һЩ����
		cell.setCellValue(sheetName + "����");
		cell.setCellStyle(cellStyle);

		rowIndex++;

		// /�ϲ�ǰ����Ŀ��һ����Ŀ���Ƶ�Ԫ��
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (colCount-1)));
	}

	/**
	 * �����ѯ����
	 */
	private static void writeSearchCondition(Date startTime, Date endTime, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuffer searchCondition=new StringBuffer();
		searchCondition.append("��ѯʱ�䣺");
		searchCondition.append(sdf.format(startTime));
		searchCondition.append("~");
		searchCondition.append(sdf.format(endTime));
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// ������0��λ�ô�����Ԫ�����϶ˣ�
		HSSFCell cell = row.createCell((short) 0);
		// ���嵥Ԫ��Ϊ�ַ�������
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// �ڵ�Ԫ��������һЩ����
		cell.setCellValue(searchCondition.toString());
		cell.setCellStyle(cellStyle);
		
		// /�ϲ�ǰ����Ŀ��һ����Ŀ���Ƶ�Ԫ��
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (colCount-1)));

		rowIndex++;
	}
	
	/**
	 * �����ѯ����
	 */
	private static void writeSearchCondition(String condition, int colCount) {

		HSSFRow row = sheet.createRow((short) rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// ������0��λ�ô�����Ԫ�����϶ˣ�
		HSSFCell cell = row.createCell((short) 0);
		// ���嵥Ԫ��Ϊ�ַ�������
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// �ڵ�Ԫ��������һЩ����
		cell.setCellValue(condition);
		cell.setCellStyle(cellStyle);
		
		// /�ϲ�ǰ����Ŀ��һ����Ŀ���Ƶ�Ԫ��
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (colCount-1)));

		rowIndex++;
	}

	/**
	 * �����ͷ
	 */
	private static void writeDataHeader(String[] colName, int colCount) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// ����һ����Ԫ��߿���ɫ
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// ����һ����Ԫ��߿���ɫ
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

		HSSFRow row = sheet.createRow((short) rowIndex);

		for(int i=0;i<colCount;i++){
			// ������0��λ�ô�����Ԫ�����϶ˣ�
			HSSFCell cell = row.createCell((short) i);
			// ���嵥Ԫ��Ϊ�ַ�������
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// �ڵ�Ԫ��������һЩ����
			cell.setCellValue(colName[i]);
			cell.setCellStyle(cellStyle);
		}
		
		rowIndex++;
	}

	/**
	 * ���ͳ������
	 */
	private static void writeDataList(Page page, String[] colKey, int colCount) {
		// ������0��λ�ô����У���˵��У�
		List mapData = page.getResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// ����һ����Ԫ��߿���ɫ
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// ����һ����Ԫ��߿���ɫ
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (Iterator iterator = mapData.iterator(); iterator.hasNext();) {
			HashMap<String,Object> hashMap = (HashMap<String,Object>) iterator.next();

			HSSFRow titleRrow = sheet.createRow((short) rowIndex);
			
			for(int i=0;i<colCount;i++){
				// ������0��λ�ô�����Ԫ�����϶ˣ�
				HSSFCell titleCell = titleRrow.createCell((short) i);
				
				// ���嵥Ԫ��Ϊ�ַ�������
				titleCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// �ڵ�Ԫ��������һЩ����,KEY�а�����time����ʱ��
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
