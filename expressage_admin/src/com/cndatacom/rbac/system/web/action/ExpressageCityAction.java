package com.cndatacom.rbac.system.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageProvincialService;


/**
 * 
 * 类名: ExpressageProductAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 城市配置action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-13 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageCity")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/city/expressageCityList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/city/expressageCityEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageCity!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageCityAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageCityService expressageCityService;
	@Resource
	private ExpressageProvincialService expressageProvincialService;
	
	private ExpressageCity expressageCity;
	private String cityId;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String isAuth;//
	private String status;
	Page<ExpressageCity> page = new Page<ExpressageCity>();
	private List provincialList = new ArrayList<>();
	private List<ExpressageCity> cityList;
	private String provincialId;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageCity where cityNo = '101010' ");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		if(StringUtils.isNotBlank(isAuth)){
			sb.append("and isAuth ="+isAuth);
		}
		
		if(StringUtils.isNotBlank(status)){
			sb.append("and status ="+status);
		}
		
		page = expressageCityService.findPage(page, sb.toString());
		setPage(page);
		return "list";
	}

	/**
     * 保存、增加实体
     * @return "reload"
     * @throws Exception
     */
	
	@Override
	public String save() throws Exception {
		expressageCity.setCityNo("101010");
      super.save();
		 return RELOAD;
	}

	/**
     * 删除实体
     * @return "reload"
     * @throws Exception
     */
	@Override
	public String delete() throws Exception {
		List<String> list=getPksByIds();
		 for(String id : list){
			  if(null!=id){
				  expressageCityService.delete(id);
			  }
		 }
		return RELOAD;
	}

	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(cityId != null){
			expressageCity = expressageCityService.findUniqueBy("cityId", cityId);
		}
		provincialList = expressageProvincialService.getAll();
		return INPUT;
	}
	
	
	public void getInfoByIDS(){
		
		StringBuffer sb = new StringBuffer("  from ExpressageCity where 1=1 ");
		if(provincialId !=null){
			sb.append(" and provincialId.provincialId = " + provincialId);
		}
		cityList = expressageProvincialService.find(sb.toString());
		
		List list = new ArrayList<ExpressageCity>();
		for (int i = 0; i < cityList.size(); i++) {
			ExpressageCity a =new ExpressageCity();
			a.setCityId(cityList.get(i).getCityId());
			a.setName(cityList.get(i).getName());
			list.add(a);
		}
		
		try {
			String json=JSONArray.fromObject(list).toString();
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	protected IBaseService getManager() {
		return expressageCityService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageCity;
	}

	@Override
	public Object getModel() {
		
		return getExpressageCity();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageCity((ExpressageCity)obj);
		
	}

	public ExpressageCity getExpressageCity() {
		return expressageCity;
	}

	public void setExpressageCity(ExpressageCity expressageCity) {
		this.expressageCity = expressageCity;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

	public List getProvincialList() {
		return provincialList;
	}

	public void setProvincialList(List provincialList) {
		this.provincialList = provincialList;
	}

	public List getCityList() {
		return cityList;
	}

	public void setCityList(List cityList) {
		this.cityList = cityList;
	}

	public String getProvincialId() {
		return provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	
}
