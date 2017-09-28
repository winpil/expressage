package com.cndatacom.rbac.system.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;
import com.cndatacom.rbac.system.service.ExpressageCourierDlService;


/**
 * 
 * 类名: ExpressageCourierAction.java</br> 
 * 包名：com.cndatacom.rbac.system.web.action</br> 
 * 描述: 快递员action</br> 
 * 发布版本号：</br> 
 * 开发人员：莫志明</br>  
 * 创建时间： 2015-7-28
 */
@Controller
@Action("expressageCourierDl")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/courierDl/expressageCourierDlList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/courierDl/expressageCourierDlEdit.jsp", type = "dispatcher"),
	  	@Result(name = "input1", location = "/rbac/sys/expressage/courierDl/expressageCourierDlEdit1.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageCourierDl!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageCourierDlAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageCourierDlService expressageCourierDlService;
	
	private ExpressageCourierDl expressageCourierDl;
	private String courierId;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String isWork;//是否上班
	private String gender;
	Page<ExpressageCourierDl> page = new Page<ExpressageCourierDl>();
	private File avatar;//上传文件
	private String sbStr;

	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageCourierDl where 1=1");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		
		page = expressageCourierDlService.findPage(page, sb.toString());
		setPage(page);
		return "list";
	}
	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(courierId != null){
			expressageCourierDl = expressageCourierDlService.findUniqueBy("courierId", courierId);
		}
		return "input1";
	}
	
	@Override
	public String save() throws Exception {
		super.save();
		return RELOAD;
	}
	
	public String skipImport(){
		
		return "input";
	}
	
	public String importExl(){
		StringBuffer sb = new StringBuffer();
		try {
			Workbook rwb=Workbook.getWorkbook(avatar);
			Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
			int clos=rs.getColumns();//得到所有的列
			int rows=rs.getRows();//得到所有的行
			System.out.println(clos+" rows:"+rows);
			
			for (int i = 1; i < rows; i++) {
				try {
					//第一个是列数，第二个是行数
					String id=rs.getCell(1, i).getContents();//默认最左边编号也算一列 所以这里得j++
					String courierName=rs.getCell(1, i).getContents();
					String provinceName=rs.getCell(2, i).getContents();
					String cityName=rs.getCell(3, i).getContents();
					String districtsName=rs.getCell(4, i).getContents();
					String company=rs.getCell(5, i).getContents();
					String phone=rs.getCell(6, i).getContents();
					String send=rs.getCell(7, i).getContents();
					String remark=rs.getCell(8, i).getContents();
					if(StringUtils.isBlank(courierName)){
						sb.append("插入第"+i+"条数据：失败，快递员名字不能为空");
						sb.append("\n");
						continue;
					}
					if(StringUtils.isBlank(phone)){
						sb.append("插入第"+i+"条数据：失败，快递员号码不能为空");
						sb.append("\n");
						continue;
					}
					if(StringUtils.isBlank(company)){
						sb.append("插入第"+i+"条数据：失败，快递员公司不能为空");
						sb.append("\n");
						continue;
					}
					
					expressageCourierDl = new ExpressageCourierDl();
					expressageCourierDl.setCityName(cityName);
					expressageCourierDl.setCompany(company);
					expressageCourierDl.setCourierName(courierName);
					expressageCourierDl.setDistrictsName(districtsName);
					expressageCourierDl.setPhone(phone);
					expressageCourierDl.setProvinceName(provinceName);
					expressageCourierDl.setRemark(remark);
					expressageCourierDl.setSend(send);
					expressageCourierDlService.save(expressageCourierDl);
				} catch (Exception e) {
					sb.append("插入第"+i+"条数据：失败，数据有问题");
					sb.append("\n");
					continue;
				}
			    sb.append("插入第"+i+"条数据：成功");
			    sb.append("\n");
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		sbStr = sb.toString();
		System.out.println(sbStr);
		return "input";
	}



	@Override
	protected IBaseService getManager() {
		return expressageCourierDlService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageCourierDl;
	}

	@Override
	public Object getModel() {
		
		return getExpressageCourierDl();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageCourierDl((ExpressageCourierDl)obj);
		
	}

	public ExpressageCourierDl getExpressageCourierDl() {
		return expressageCourierDl;
	}

	public void setExpressageCourierDl(ExpressageCourierDl expressageCourierDl) {
		this.expressageCourierDl = expressageCourierDl;
	}

	public String getCourierId() {
		return courierId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	public String getAuName() {
		return auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public String getSbStr() {
		return sbStr;
	}

	public void setSbStr(String sbStr) {
		this.sbStr = sbStr;
	}

	

}
