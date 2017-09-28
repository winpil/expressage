package com.cndatacom.rbac.system.web.action;

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
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.pojo.ExpressageIdea;
import com.cndatacom.rbac.pojo.ExpressageOrder;
import com.cndatacom.rbac.pojo.ExpressagePayLog;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageIdeaService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;

/**
 * 
 * 类名: ExpressageIdeaAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 举报投诉管理</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-8 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageIdea")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list1",  location = "/rbac/sys/expressage/idea/expressageIdeaList1.jsp", type = "dispatcher"),
	  	@Result(name = "list2",  location = "/rbac/sys/expressage/idea/expressageIdeaList2.jsp", type = "dispatcher"),
	  	@Result(name = "list3",  location = "/rbac/sys/expressage/idea/expressageIdeaList3.jsp", type = "dispatcher"),
	  	@Result(name = "input1", location = "/rbac/sys/expressage/idea/expressageIdeaEdit1.jsp", type = "dispatcher"),
	  	@Result(name = "input2", location = "/rbac/sys/expressage/idea/expressageIdeaEdit2.jsp", type = "dispatcher"),
	  	@Result(name = "input3", location = "/rbac/sys/expressage/idea/expressageIdeaEdit3.jsp", type = "dispatcher"),
		@Result(name = "reload2", location = "expressageIdea!list2.action", type = "redirect"),
		@Result(name = "reload3", location = "expressageIdea!list3.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class ExpressageIdeaAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageIdeaService expressageIdeaService;
	@Resource
    private ExpressageOrderService expressageOrderService;
	@Resource
    private ExpressageCourierService expressageCourierService;
	@Resource
    private ExpressagePayLogService expressagePayLogService;
	
	private ExpressageIdea expressageIdea;
	private String ideaId;
	Page<ExpressageIdea> page = new Page<ExpressageIdea>();
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String type;//
	private String status;//
	private String types;//
	private String ideaStatus;//
	/**
     * 查询意见反馈列表
     * @return "list"
     * @throws Exception
     */
	public String list1() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageIdea where 1=1 and type in ('1','4') ");
		
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
		page = expressageIdeaService.findPage(page, sb.toString());
		setPage(page);
		return "list1";
	}
	
	
	/**
     * 查询用户投诉列表
     * @return "list"
     * @throws Exception
     */
	public String list2() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageIdea where 1=1 and type ='2' ");
		
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
		page = expressageIdeaService.findPage(page, sb.toString());
		setPage(page);
		return "list2";
	}
	
	/**
     * 查询快递举报列表
     * @return "list"
     * @throws Exception
     */
	public String list3() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageIdea where 1=1 and type ='3' ");
		
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
		page = expressageIdeaService.findPage(page, sb.toString());
		setPage(page);
		return "list3";
	}
	
	/**
     * 保存、增加实体
     * @return "reload"
     * @throws Exception
     */
	public String save2() throws Exception {
		
		
		String st = expressageIdea.getStatus();
		System.out.println(ideaStatus);
		
		
		if(st.equals("1")){
			if(ideaStatus!=null&&ideaStatus.equals("0")){
				//记录对应快递员不良记录次数
				ExpressageOrder eo = expressageOrderService.findUniqueBy("orderNo", expressageIdea.getOrderNo());
				if(eo!=null&&eo.getType().equals("1")&&eo.getCourierId()!=null&&!eo.getCourierId().equals("")){
					ExpressageCourier ec = expressageCourierService.findUniqueBy("courierId", eo.getCourierId().getCourierId());
					if(Integer.valueOf(ec.getRank())>=2){
						if(Integer.valueOf(ec.getClientPwd())>=1){
							ec.setRank(String.valueOf(Integer.valueOf(ec.getRank())-1));
							ec.setClientPwd(String.valueOf(Integer.valueOf(ec.getClientPwd())-1));
						}else{
							ec.setClientPwd(String.valueOf(Integer.valueOf(ec.getClientPwd())+1));
						}
					}else{
						ec.setClientPwd(String.valueOf(Integer.valueOf(ec.getClientPwd())+1));
					}
					expressageCourierService.save(ec);
				}
				expressageIdea.setUpdateDate(new Date());
			}
		}else if(st.equals("2")){
			if(ideaStatus.equals("0")){
				expressageIdea.setUpdateDate(new Date());
			}
		}
		expressageIdeaService.save(expressageIdea);
		return "reload2";
	}
	
	public String save3() throws Exception {
		
		String st = expressageIdea.getStatus();
		System.out.println(ideaStatus);
		
		
		if(st.equals("1")){
			if(ideaStatus.equals("0")){
				ExpressageOrder eo = expressageOrderService.findUniqueBy("orderNo", expressageIdea.getOrderNo());
				if(eo!=null){
					ExpressagePayLog epl = expressagePayLogService.findUnique(" from ExpressagePayLog where type = '7' and status = '2' and orderId = ?  ", eo.getOrderId());
					if(epl!=null){//
						epl.setStatus("1");
						expressagePayLogService.save(epl);
						ExpressageCourier ec = expressageCourierService.findUniqueBy("courierId", eo.getCourierId().getCourierId());
						ec.setBalance(String.valueOf(Double.valueOf(ec.getBalance())+Double.valueOf(epl.getMoneyNum().replace("+", "").replace("-", ""))));
						expressageCourierService.save(ec);
					}
				}
				expressageIdea.setUpdateDate(new Date());
			}
		}else if(st.equals("2")){
			if(ideaStatus.equals("0")){
				ExpressageOrder eo = expressageOrderService.findUniqueBy("orderNo", expressageIdea.getOrderNo());
				if(eo!=null){
					ExpressagePayLog epl = expressagePayLogService.findUnique(" from ExpressagePayLog where type = '7' and status = '2' and orderId = ?  ", eo.getOrderId());
					if(epl!=null){//
						epl.setStatus("0");
						expressagePayLogService.save(epl);
					}
				}
				expressageIdea.setUpdateDate(new Date());
			}
		}
		expressageIdeaService.save(expressageIdea);
		return "reload3";
	}
	

	/**
     * 删除实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		super.delete();
		return RELOAD;
	}

		
	
	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(ideaId != null){
			expressageIdea = expressageIdeaService.findUniqueBy("id", ideaId);
		}
		if(types.equals("1")){
			return "input1";
		}else if(types.equals("2")){
			return "input2";
		}else{
			return "input3";
		}
		
	}
	
	
	
	@Override
	protected Object createNewInstance() {
		return expressageIdea;
	}

	@Override
	protected IBaseService getManager() {
		return expressageIdeaService;
	}

	@Override
	public Object getModel() {
		return getExpressageIdea();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageIdea((ExpressageIdea) obj);
	}

	public ExpressageIdea getExpressageIdea() {
		return expressageIdea;
	}

	public void setExpressageIdea(ExpressageIdea expressageIdea) {
		this.expressageIdea = expressageIdea;
	}

	public String getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(String ideaId) {
		this.ideaId = ideaId;
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


	public String getTypes() {
		return types;
	}


	public void setTypes(String types) {
		this.types = types;
	}


	public String getIdeaStatus() {
		return ideaStatus;
	}


	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}
	
	
	
	
}
