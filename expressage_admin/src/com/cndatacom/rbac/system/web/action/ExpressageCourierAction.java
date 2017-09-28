package com.cndatacom.rbac.system.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageBank;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.system.service.ExpressageBankService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;


/**
 * 
 * ����: ExpressageCourierAction.java</br> 
 * ������com.cndatacom.rbac.system.web.action</br> 
 * ����: ���Աaction</br> 
 * �����汾�ţ�</br> 
 * ������Ա��Ī־��</br>  
 * ����ʱ�䣺 2015-7-28
 */
@Controller
@Action("expressageCourier")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/courier/expressageCourierList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/courier/expressageCourierEdit.jsp", type = "dispatcher"),
	  	@Result(name = "show", location = "/rbac/sys/expressage/courier/expressageCourierBankCard.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageCourier!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageCourierAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageUserService expressageUserService;
	@Resource
    private ExpressageTokenService expressageTokenService;
	@Resource
    private ExpressageCourierService expressageCourierService;
	@Resource
    private ExpressageOrderService expressageOrderService;
	@Resource
    private ExpressageOrderLogService expressageOrderLogService;
	@Resource
    private ExpressageFavorableService expressageFavorableService;
	@Resource
    private ExpressageMessageService expressageMessageService;
	@Resource
    private ExpressagePayLogService expressagePayLogService;
	@Resource
	private ExpressageBankService expressageBankService;
	
	private ExpressageCourier expressageCourier;
	private String courierId;
	private String auName;//����������
	private String searchName;//������
	private String isAuth;//
	private String status;
	Page<ExpressageCourier> page = new Page<ExpressageCourier>();
	Page<ExpressageBank> page2 = new Page<ExpressageBank>();
	
	public String show(){
		String hql = "from ExpressageBank where courierId = ?";
		page2 = expressageBankService.findPage(page2, hql, courierId);
		setPage(page2);
		return "show";
	}

	/**
     * ��ѯ�б�
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageCourier where 1=1");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		if(StringUtils.isNotBlank(isAuth)){
			sb.append("and isAuth ="+isAuth);
		}
		
		if(StringUtils.isNotBlank(status)){
			sb.append("and status ="+status);
		}
		
		page = expressageCourierService.findPage(page, sb.toString());
		List<ExpressageCourier> ecList = page.getResult();
		List<ExpressageCourier> ecList1 = new ArrayList<ExpressageCourier>();
		for (int i = 0; i < ecList.size(); i++) {
			expressageCourier = ecList.get(i);
			Object obj = "0";
			obj = expressageOrderService.find("select count(*) from ExpressageOrder where courierId = '"+expressageCourier.getCourierId()+"' and type = '1'  ");
			
			expressageCourier.setIsWork(String.valueOf(obj).replace("[", "").replace("]", ""));
			ecList1.add(expressageCourier);
		}
		
		page.setResult(ecList1);
		setPage(page);
		return "list";
	}

	/**
     * ���桢����ʵ��
     * @return "reload"
     * @throws Exception
     */
	
	@Override
	public String save() throws Exception {
		super.save();
		return RELOAD;
	}

	/**
     * ɾ��ʵ��
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		super.delete();
		return RELOAD;
	}

	/**
     * �༭ʵ��
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(courierId != null){
			expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
	
		}
		return INPUT;
	}
	
	
	
//  	public void exportCourier(){
//  		
//  		StringBuffer sb = new StringBuffer("from ExpressageCourier where 1=1");
//		if(StringUtils.isNotBlank(searchName)){
//			sb.append(" and "+searchName+" like '%"+auName+"%'");
//		}
//		if(StringUtils.isNotBlank(isAuth)){
//			sb.append("and isAuth ="+isAuth);
//		}
//		
//		if(StringUtils.isNotBlank(status)){
//			sb.append("and status ="+status);
//		}
//		
//		List<ExpressageCourier> userList = expressageCourierService.find(sb.toString());
//  		if(userList!=null){
//  			try {
//  				HttpServletResponse response = ServletActionContext.getResponse();
//  				String fileName = "���Ա�б�";
//  				response.addHeader("Content-Disposition","attachment;filename="+ new String(fileName.getBytes("GB2312"),"iso-8859-1") + ".xls");
//  				response.setContentType("application/vnd.ms-excel");
//  
//  				// ���ļ�
//  				WritableWorkbook wbook = Workbook.createWorkbook(response.getOutputStream());
//  				// //������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
//  				WritableSheet wsheet = wbook.createSheet("result", 0);
//  				WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
//  						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
//  				
//  				String[] titles = {"���","����","�˺�״̬","�ֻ�(��¼����)","���","����","��֤״̬","�Ƽ����Ա�ֻ�"}; // �������
//  				WritableCellFormat titleFormat = new WritableCellFormat(wfont);
//  			//		DownLoadUtils.downLoadExcel(allBusinessList, titles, wsheet,titleFormat);
//  				for (int i = 0; i < titles.length; i++) {
//  					Label excelTitle = new Label(i, 0, titles[i], titleFormat);
//  					wsheet.addCell(excelTitle);
//  				}
//  				int cell = 1;
//  				Iterator<ExpressageCourier> it = userList.iterator();
//  				
//  				while (it.hasNext()) {
//  					ExpressageCourier or = (ExpressageCourier) it.next();
//  					Label con1 = new Label(0, cell, cell+ "");
//  					Label con2 = new Label(1, cell, or.getCourierName());
//  					
//  					Label con3 = null;
//  					if(or.getGender().equals("1")){
//  						con3 = new Label(2, cell, "��");
//  					}else{
//  						con3 = new Label(2, cell, "Ů");
//  					}
//  					Label con4 = new Label(3, cell, or.getPhone());
//  					Label con5 = new Label(4, cell, or.getBankCard());
//  					Label con6 = new Label(5, cell, or.getRank());
//  					Label con7 = null;
//  					if(or.getIsWork().equals("1")){
//  						con7 = new Label(6, cell, "�ϰ�");
//  					}else{
//  						con7 = new Label(6, cell, "�°�");
//  					}
//  					Label con8 = null;
//  					if(or.getReferrerId()==null||or.getReferrerId().equals("")){
//  						con8 = new Label(7, cell, "");
//  					}else{
//  						con8 = new Label(7, cell, or.getReferrerId().getPhone());
//  					}
//  					
//  					
//  					wsheet.addCell(con1);
//  					wsheet.addCell(con2);
//  					wsheet.addCell(con3);
//  					wsheet.addCell(con4);
//  					wsheet.addCell(con5);
//  					wsheet.addCell(con6);
//  					wsheet.addCell(con7);
//  					wsheet.addCell(con8);
//  					cell++;
//  				}
//  				wbook.write();
//  				wbook.close();
//  				response.getOutputStream().close();
//  			}catch (Exception e) {
//  				e.printStackTrace();
//  			}
//  		}
//  	}
	
	

	@Override
	protected IBaseService getManager() {
		return expressageCourierService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageCourier;
	}

	@Override
	public Object getModel() {
		
		return getExpressageCourier();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageCourier((ExpressageCourier)obj);
		
	}

	public ExpressageCourier getExpressageCourier() {
		return expressageCourier;
	}

	public void setExpressageCourier(ExpressageCourier expressageCourier) {
		this.expressageCourier = expressageCourier;
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

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
