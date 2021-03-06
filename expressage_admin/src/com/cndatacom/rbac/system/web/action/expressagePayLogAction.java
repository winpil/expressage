package com.cndatacom.rbac.system.web.action;

import java.text.SimpleDateFormat;
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
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.pojo.ExpressagePayLog;
import com.cndatacom.rbac.pojo.ExpressageUser;
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
 * 类名: expressagePayLoyAction.java</br> 
 * 包名：com.cndatacom.rbac.system.web.action</br> 
 * 描述: 支付action</br> 
 * 发布版本号：</br> 
 * 开发人员：莫志明</br>  
 * 创建时间： 2015-8-4
 */
@Controller
@Action("expressagePayLog")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/payLog/expressagePayLogList.jsp", type = "dispatcher"),
	  	@Result(name = "list1", location = "/rbac/sys/expressage/payLog/expressagePayLogList1.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/payLog/expressagePayLogEdit.jsp", type = "dispatcher"),
	  	@Result(name = "input1", location = "/rbac/sys/expressage/payLog/expressagePayLogEdit1.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressagePayLog!list.action", type = "redirect"),
		@Result(name = "reload1", location = "expressagePayLog!list1.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class expressagePayLogAction extends SimpleActionSupport{
	
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
	
	Page<ExpressagePayLog> page = new Page<ExpressagePayLog>();
	private ExpressagePayLog expressagePayLog;
	private String payId;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String type;//支付方式
	private String status;//交易状态

	
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		
		StringBuffer sb = new StringBuffer("from ExpressagePayLog where type <> '4' ");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(type)){
			sb.append("and type = "+type);
		}
		if(StringUtils.isNotBlank(status)){
			sb.append("and status = "+status);
		}
		sb.append(" order by createDate desc");
		page = expressagePayLogService.findPage(page, sb.toString());
		setPage(page);
		return "list";
	}
	
	public String list1() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		
		StringBuffer sb = new StringBuffer("from ExpressagePayLog where type = '4' ");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(type)){
			sb.append("and type = "+type);
		}
		if(StringUtils.isNotBlank(status)){
			sb.append("and status = "+status);
		}
		sb.append(" order by createDate desc");
		page = expressagePayLogService.findPage(page, sb.toString());
		setPage(page);
		return "list1";
	}
	
	/**
     * 保存、增加实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String save() throws Exception {
		super.save();
		if(expressagePayLog.getStatus().equals("0")){
			ExpressageCourier expressageCourier = expressageCourierService.get(expressagePayLog.getCourierId().getCourierId());
			double result = Double.parseDouble(expressageCourier.getBalance())+Double.parseDouble(expressagePayLog.getMoneyNum().replace("+", "").replace("-", ""));
			expressageCourier.setBalance(String.valueOf(result));
			expressageCourierService.save(expressageCourier);
		}
		return "reload";
	}
	
	public String save1() throws Exception {
		super.save();
		if(expressagePayLog.getStatus().equals("0")){
			ExpressageCourier expressageCourier = expressageCourierService.get(expressagePayLog.getCourierId().getCourierId());
			double result = Double.parseDouble(expressageCourier.getBalance())+Double.parseDouble(expressagePayLog.getMoneyNum().replace("+", "").replace("-", ""));
			expressageCourier.setBalance(String.valueOf(result));
			expressageCourierService.save(expressageCourier);
		}
		return "reload1";
	}

	
	/**
     * 删除实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		super.delete();
		return "reload";
	}
	
	public String delete1() throws Exception {
		super.delete();
		return "reload1";
	}

	
	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(payId != null){
			expressagePayLog = expressagePayLogService.findUniqueBy("payId", payId);
		}
		return "input";
	}
	
	public String input1() throws Exception {
		if(payId != null){
			expressagePayLog = expressagePayLogService.findUniqueBy("payId", payId);
		}
		return "input1";
	}
	
	
	/**
	 * 
	 * 方法名: exportUsers</br>
	 * 详述: 导出</br>
	 * 开发人员：莫志明</br>
	 * 创建时间：2015-8-4</br>
	 */
	
  	public void exportUsers(){
  		
  		StringBuffer sb = new StringBuffer("from ExpressagePayLog where 1=1");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(type)){
			sb.append("and type = "+type);
		}
		if(StringUtils.isNotBlank(status)){
			sb.append("and status = "+status);
		}
		
  		List<ExpressagePayLog> userList = expressagePayLogService.find(sb.toString());
  		if(userList!=null){
  			try {
  				HttpServletResponse response = ServletActionContext.getResponse();
  				String fileName = "用户列表";
  				response.addHeader("Content-Disposition","attachment;filename="+ new String(fileName.getBytes("GB2312"),"iso-8859-1") + ".xls");
  				response.setContentType("application/vnd.ms-excel");
  
  				// 打开文件
  				WritableWorkbook wbook = Workbook.createWorkbook(response.getOutputStream());
  				// //生成名为“第一页”的工作表，参数0表示这是第一页
  				WritableSheet wsheet = wbook.createSheet("result", 0);
  				WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
  						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
  				
  				String[] titles = {"序号","用户名称","快递员名称","支付方式","金额","交易状态","银行名称","银行帐号","创建时间"}; // 表格列名
  				WritableCellFormat titleFormat = new WritableCellFormat(wfont);
  			//		DownLoadUtils.downLoadExcel(allBusinessList, titles, wsheet,titleFormat);
  				for (int i = 0; i < titles.length; i++) {
  					Label excelTitle = new Label(i, 0, titles[i], titleFormat);
  					wsheet.addCell(excelTitle);
  				}
  				int cell = 1;
  				Iterator<ExpressagePayLog> it = userList.iterator();
  				
  				while (it.hasNext()) {
  					ExpressagePayLog or = (ExpressagePayLog) it.next();
  					Label con1 = new Label(0, cell, cell+ "");
  					Label con2 = new Label(1, cell, or.getUserId().getUserName());
  					Label con3 = new Label(2, cell, or.getCourierId().getCourierName());
  					
  					Label con4 = null;
  					if(or.getType().equals("1")){
  						con4 = new Label(3, cell, "现金支付");
  						
  					}else if(or.getType().equals("2")){
  						con4 = new Label(3, cell, "微信支付");
  						
  					}else if(or.getType().equals("3")){
  						con4 = new Label(3, cell, "支付宝支付");
  						
  					}else if(or.getType().equals("4")){
  						con4 = new Label(3, cell, "提现");
  						
  					}else {
  						con4 = new Label(3, cell, "奖励");
  					}
  					
  					Label con5 = new Label(4, cell, or.getMoneyNum());
  					Label con6 = null;
  					if(or.getStatus().equals("1")){
  						con6 = new Label(5, cell, "成功");
  						
  					}else if(or.getStatus().equals("0")){
  						con6 = new Label(5, cell, "失败");
  						
  					}else{
  						con6 = new Label(5, cell, "进行中");
  					}
  					
  					
  					Label con7 = new Label(6, cell, or.getBankName());
  					Label con8 = new Label(7, cell, or.getBankCode());
  					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  					Label con9 = null;
  					if(or.getCreateDate()!=null){
  						con9 = new Label(8, cell, df.format(or.getCreateDate()));
  					}else {
  						con9 = new Label(8, cell, "");
					}
  					
  					
  					wsheet.addCell(con1);
  					wsheet.addCell(con2);
  					wsheet.addCell(con3);
  					wsheet.addCell(con4);
  					wsheet.addCell(con5);
  					wsheet.addCell(con6);
  					wsheet.addCell(con7);
  					wsheet.addCell(con8);
  					wsheet.addCell(con9);
  					cell++;
  				}
  				wbook.write();
  				wbook.close();
  				response.getOutputStream().close();
  			}catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  	}
	
	

	@Override
	protected IBaseService getManager() {
		return expressagePayLogService;
	}

	@Override
	protected Object createNewInstance() {
		return expressagePayLog;
	}

	@Override
	public Object getModel() {
		return getExpressagePayLog();
	}

	@Override
	public void setModel(Object obj) {
		setExpressagePayLog((ExpressagePayLog)obj);
	}

	public ExpressagePayLog getExpressagePayLog() {
		return expressagePayLog;
	}

	public void setExpressagePayLog(ExpressagePayLog expressagePayLog) {
		this.expressagePayLog = expressagePayLog;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
