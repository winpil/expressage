package com.cndatacom.rbac.system.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.cndatacom.common.utils.DateUtil;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageF;
import com.cndatacom.rbac.pojo.ExpressageFavorable;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * ����: ExpressageUesrAction.java</br> 
 * ������com.cndatacom.rbac.system.web.action</br> 
 * ����: �û�action</br> 
 * �����汾�ţ�</br> 
 * ������Ա��Ī־��</br>  
 * ����ʱ�䣺 2015-7-28
 */
@Controller
@Action("expressageUser")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/user/expressageUserList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/user/expressageUserEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageUser!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class ExpressageUserAction extends SimpleActionSupport{
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
    private ExpressageFService expressageFService;
	@Resource
    private ExpressageMessageService expressageMessageService;
	@Resource
    private ExpressagePayLogService expressagePayLogService;
	private ExpressageUser expressageUser;
	private File avatar;//�ϴ��ļ�
	private String auName;//����������
	private String searchName;//������
	private String isLock;//�˺�״̬
	private String gender;
	private String userId;
	private String type;
	private String ids;
	Page<ExpressageUser> page = new Page<ExpressageUser>();
	List<ExpressageF> efList = new ArrayList<ExpressageF>();
	
	/**
     * ��ѯ�б�
     * @return "list"
     * @throws Exception
     */
	
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		
		StringBuffer sb = new StringBuffer("from ExpressageUser where 1=1");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(isLock)){
			sb.append("and isLock = "+isLock);
		}
		if(StringUtils.isNotBlank(gender)){
			sb.append("and gender = "+gender);
		}
		page = expressageUserService.findPage(page, sb.toString());
		setPage(page);
		
		efList = expressageFService.find(" from ExpressageF where fname <> '0' group by fname order by fname*1 desc ");
		
		return "list";
	}

	
	/**
     * �༭ʵ��
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(userId != null){
			List<ExpressageUser> ll = expressageUserService.find("from ExpressageUser where userId =" + userId);
			expressageUser = ll.get(0);
			isLock = expressageUser.getIsLock();
			
		}
		
	    return INPUT;
	}
	
	
	/**
	 * 
	 * ������: setFavorable</br>
	 * ����: ���������Ż�ȯ</br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2017-6-6</br>
	 * @return
	 * @throws Exception
	 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
	 */
	public String setFavorable() throws Exception {
		String iis = getIds();
		String [] str = iis.split(",");
		ExpressageF ef1 =null;
		if(type != null){
			ef1 = expressageFService.findUniqueBy("fid", type);
		}
		 for(String id : str){
			  if(null!=id){
				  ExpressageFavorable ef = new ExpressageFavorable();
					ef.setCreateDate(new Date());
					ef.setPastDue(DateUtil.addMonth(new Date(),1));
					ef.setStatus("1");
					expressageUser = expressageUserService.findUniqueBy("userId", id);
					ef.setUserId(expressageUser);
					
					ef.setFavorableName(ef1.getFname());
					expressageFavorableService.save(ef);
			  }
		 }
		
	    return "reload";
	}

	
	/**
     * ����ʵ��
     * @return "reload"
     * @throws Exception
     */
	
	@Override
	public String save() throws Exception {
		 super.save();
		 return RELOAD;
	}
	
	
	
	/**
	 * 
	 * ������: exportUsers</br>
	 * ����: </br>
	 * ������Ա��������</br>
	 * ����ʱ�䣺2015-5-8</br>
	 */
  	public void exportUsers(){
  		
  		List<ExpressageUser> userList = expressageUserService.getAll();
  		if(userList!=null){
  			try {
  				HttpServletResponse response = ServletActionContext.getResponse();
  				String fileName = "�û��б�";
  				response.addHeader("Content-Disposition","attachment;filename="+ new String(fileName.getBytes("GB2312"),"iso-8859-1") + ".xls");
  				response.setContentType("application/vnd.ms-excel");
  
  				// ���ļ�
  				WritableWorkbook wbook = Workbook.createWorkbook(response.getOutputStream());
  				// //������Ϊ����һҳ���Ĺ�����������0��ʾ���ǵ�һҳ
  				WritableSheet wsheet = wbook.createSheet("result", 0);
  				WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
  						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
  				
  				String[] titles = {"���","����","�Ա�","�ֻ�","�Ż�ȯ����","�ʺ�״̬","����ʱ��"}; // ��������
  				WritableCellFormat titleFormat = new WritableCellFormat(wfont);
  			//		DownLoadUtils.downLoadExcel(allBusinessList, titles, wsheet,titleFormat);
  				for (int i = 0; i < titles.length; i++) {
  					Label excelTitle = new Label(i, 0, titles[i], titleFormat);
  					wsheet.addCell(excelTitle);
  				}
  				int cell = 1;
  				Iterator<ExpressageUser> it = userList.iterator();
  				
  				while (it.hasNext()) {
  					ExpressageUser or = (ExpressageUser) it.next();
  					Label con1 = new Label(0, cell, cell+ "");
  					Label con2 = new Label(1, cell, or.getUserName());
  					
  					Label con3 = null;
  					if(or.getGender()==Integer.toString(1)){
  						con3 = new Label(2, cell, "��");
  					}else{
  						con3 = new Label(2, cell, "Ů");
  					}
  					Label con4 = new Label(3, cell, or.getPhone());
  					Label con5 = new Label(4, cell, or.getCouponNum());
  					Label con6 = new Label(5, cell, or.getIsLock());
  					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  					Label con7 = null;
  					if(or.getCreateDate()!=null){
  						con7 = new Label(6, cell, df.format(or.getCreateDate()));
  					}else {
  						con7 = new Label(6, cell, "");
					}
  					
  					
  					wsheet.addCell(con1);
  					wsheet.addCell(con2);
  					wsheet.addCell(con3);
  					wsheet.addCell(con4);
  					wsheet.addCell(con5);
  					wsheet.addCell(con6);
  					wsheet.addCell(con7);
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
		return expressageUserService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageUser;
	}

	@Override
	public Object getModel() {
		return getExpressageUser();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageUser((ExpressageUser)obj);
		
	}

	public ExpressageUser getExpressageUser() {
		return expressageUser;
	}

	public void setExpressageUser(ExpressageUser expressageUser) {
		this.expressageUser = expressageUser;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
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

	
	
	
	public String getIsLock() {
		return isLock;
	}



	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public List<ExpressageF> getEfList() {
		return efList;
	}


	public void setEfList(List<ExpressageF> efList) {
		this.efList = efList;
	}

	
	

}