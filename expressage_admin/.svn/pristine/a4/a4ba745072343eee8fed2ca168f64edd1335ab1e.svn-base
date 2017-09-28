package com.cndatacom.rbac.system.web.action;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.cndatacom.common.utils.UploadFile;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.ExpressageText;
import com.cndatacom.rbac.system.service.ExpressageBankService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageTextService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;


/**
 * 
 * 类名: ExpressageTextAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 关于我们、常见问题action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-13 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageText")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/text/expressageTextList.jsp", type = "dispatcher"),
	  	@Result(name = "list1", location = "/rbac/sys/expressage/text/expressageTextList1.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/text/expressageTextEdit.jsp", type = "dispatcher"),
	  	@Result(name = "input1", location = "/rbac/sys/expressage/text/expressageTextEdit1.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageText!list.action", type = "redirect"),
		@Result(name = "reload1", location = "expressageText!list1.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageTextAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageTextService expressageTextService;
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
	
	private ExpressageText expressageText;
	private String textId;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String status;
	Page<ExpressageText> page = new Page<ExpressageText>();
	
	private File image1;//上传文件
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageText where 1=1 and type not in ('5') ");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		
		if(StringUtils.isNotBlank(status)){
			sb.append("and status ="+status);
		}
		sb.append(" order by rank*1 asc ");
		page = expressageTextService.findPage(page, sb.toString());
		setPage(page);
		return "list";
	}
	
	
	public String list1() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageText where 1=1 and type ='5' ");
		page = expressageTextService.findPage(page, sb.toString());
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
		HttpServletRequest request = ServletActionContext.getRequest();
    	UploadFile up = new UploadFile();
		// 上传文件路径
		String realpath = request.getRealPath("/");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		// 如果图片不等于空
		if (null != image1) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+"image1.png";
			// 上传文件
			String pa = up.uploadExcelParserReport(image1, imageFileName,
					realpath + "/upload/text");
			System.out.println(pa);
			//数据库存网络地址
			String  str=basePath+"/upload/text/"+imageFileName; 
			System.out.println("str=="+str);
			expressageText.setImg(str);
		}
		expressageText.setCreateDate(new Date());
      super.save();
		 return RELOAD;
	}
	
	public String save1() throws Exception {
		
		 expressageText.setCreateDate(new Date());
         super.save();
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
		return RELOAD;
	}

	/**
     * 编辑实体
     * @return "input"
     * @throws Exception
     */
	@Override
	public String input() throws Exception {
		if(textId != null){
			expressageText = expressageTextService.findUniqueBy("textId", textId);
	
		}
		return INPUT;
	}
	
	public String input1() throws Exception {
		if(textId != null){
			expressageText = expressageTextService.findUniqueBy("textId", textId);
	
		}
		return "input1";
	}
	
	
	

	@Override
	protected IBaseService getManager() {
		return expressageTextService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageText;
	}

	@Override
	public Object getModel() {
		
		return getExpressageText();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageText((ExpressageText)obj);
		
	}

	public ExpressageText getExpressageText() {
		return expressageText;
	}

	public void setExpressageText(ExpressageText expressageText) {
		this.expressageText = expressageText;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public File getImage1() {
		return image1;
	}

	public void setImage1(File image1) {
		this.image1 = image1;
	}

}
