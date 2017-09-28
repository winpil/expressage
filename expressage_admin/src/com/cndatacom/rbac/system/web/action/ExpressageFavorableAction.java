package com.cndatacom.rbac.system.web.action;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.cndatacom.rbac.system.service.ExpressageFService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;

/**
 * 
 * ����: ExpressageFAction</br> 
 * ������com.cndatacom.rbac.system.web.action </br> 
 * ����: </br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2017-6-27 
 * ��˾ ���������ֺ�����Ƽ����޹�˾(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageFavorable")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/user/expressageFavorableList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/user/expressageFavorableEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageFavorable!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class ExpressageFavorableAction extends SimpleActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageFavorableService expressageFavorableService;
	@Resource
    private ExpressageFService expressageFService;
	private ExpressageFavorable expressageFavorable;
	private File avatar;//�ϴ��ļ�
	private String auName;//����������
	private String searchName;//������
	private String isLock;//�˺�״̬
	private String gender;
	private String fId;
	private String type;
	private String ids;
	Page<ExpressageFavorable> page = new Page<ExpressageFavorable>();
	
	/**
     * ��ѯ�б�
     * @return "list"
     * @throws Exception
     */
	
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		
		StringBuffer sb = new StringBuffer("from ExpressageFavorable where 1=1 ");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(isLock)){
			sb.append("and status = "+isLock);
		}
		
		sb.append(" order by createDate desc ");
		
		
		page = expressageFavorableService.findPage(page, sb.toString());
		setPage(page);
		return "list";
	}

	
	/**
     * �༭ʵ��
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		
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
		 for(String id : str){
			  if(null!=id){
				  ExpressageFavorable ef = new ExpressageFavorable();
					ef.setCreateDate(new Date());
					ef.setFavorableName("15");
					ef.setPastDue(DateUtil.addMonth(new Date(),1));
					ef.setStatus("1");
//					ef.setUserId(id);
					
					if(type.equals("1")){
						ef.setFavorableName("15");
						expressageFavorableService.save(ef);
					}else if(type.equals("2")){
						ef.setFavorableName("8");
						expressageFavorableService.save(ef);
					}else if(type.equals("3")){
						ef.setFavorableName("6");
						expressageFavorableService.save(ef);
					}
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
	
	
	
	
	
	
	@Override
	protected IBaseService getManager() {
		return expressageFavorableService;
	}

	@Override
	protected Object createNewInstance() {
		return expressageFavorable;
	}

	@Override
	public Object getModel() {
		return getExpressageFavorable();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageFavorable((ExpressageFavorable)obj);
		
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

	


	public String getfId() {
		return fId;
	}


	public void setfId(String fId) {
		this.fId = fId;
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


	public ExpressageFavorable getExpressageFavorable() {
		return expressageFavorable;
	}


	public void setExpressageFavorable(ExpressageFavorable expressageFavorable) {
		this.expressageFavorable = expressageFavorable;
	}
	
	
}
