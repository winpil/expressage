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
import com.cndatacom.rbac.pojo.ExpressageBank;
import com.cndatacom.rbac.pojo.ExpressageProduct;
import com.cndatacom.rbac.system.service.ExpressageBankService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageProductService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;


/**
 * 
 * 类名: ExpressageProductAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 积分产品action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-13 
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageProduct")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "list", location = "/rbac/sys/expressage/product/expressageProductList.jsp", type = "dispatcher"),
	  	@Result(name = "input", location = "/rbac/sys/expressage/product/expressageProductEdit.jsp", type = "dispatcher"),
		@Result(name = "reload", location = "expressageProduct!list.action", type = "redirect"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})

public class ExpressageProductAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageProductService expressageProductService;
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
	
	private ExpressageProduct expressageProduct;
	private String productId;
	private String auName;//搜索框内容
	private String searchName;//搜索名
	private String isAuth;//
	private String status;
	Page<ExpressageProduct> page = new Page<ExpressageProduct>();
	Page<ExpressageBank> page2 = new Page<ExpressageBank>();
	
	private File image1;//上传文件
	private File image2;//上传文件
	private File image3;//上传文件
	private File image4;//上传文件
	
	/**
     * 查询列表
     * @return "list"
     * @throws Exception
     */
	@Override
	public String list() throws Exception {
		page.setStart(start);
		page.setLimit(limit);
		StringBuffer sb = new StringBuffer("from ExpressageProduct where 1=1");
		if(StringUtils.isNotBlank(searchName)){
			sb.append(" and "+searchName+" like '%"+auName+"%'");
		}
		if(StringUtils.isNotBlank(isAuth)){
			sb.append("and isAuth ="+isAuth);
		}
		
		if(StringUtils.isNotBlank(status)){
			sb.append("and status ="+status);
		}
		
		page = expressageProductService.findPage(page, sb.toString());
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
						realpath + "/upload/product");
				System.out.println(pa);
				//数据库存网络地址
				String  str=basePath+"/upload/product/"+imageFileName; 
				System.out.println("str=="+str);
				expressageProduct.setImg1(str);
			}
			
			if (null != image2) {
				String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+"image2.png";
				// 上传文件
				String pa = up.uploadExcelParserReport(image2, imageFileName,
						realpath + "/upload/product");
				System.out.println(pa);
				//数据库存网络地址
				String  str=basePath+"/upload/product/"+imageFileName; 
				System.out.println("str=="+str);
				expressageProduct.setImg2(str);
			}
			
			if (null != image3) {
				String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+"image3.png";
				// 上传文件
				String pa = up.uploadExcelParserReport(image3, imageFileName,
						realpath + "/upload/product");
				System.out.println(pa);
				//数据库存网络地址
				String  str=basePath+"/upload/product/"+imageFileName; 
				System.out.println("str=="+str);
				expressageProduct.setImg3(str);
			}
			
			if (null != image4) {
				String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+"image4.png";
				// 上传文件
				String pa = up.uploadExcelParserReport(image4, imageFileName,
						realpath + "/upload/product");
				System.out.println(pa);
				//数据库存网络地址
				String  str=basePath+"/upload/product/"+imageFileName; 
				System.out.println("str=="+str);
				expressageProduct.setImg4(str);
			}
			expressageProduct.setCreateDate(new Date());
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
		if(productId != null){
			expressageProduct = expressageProductService.findUniqueBy("productId", productId);
	
		}
		return INPUT;
	}
	
	
	
	
	

	@Override
	protected IBaseService getManager() {
		return expressageProductService;
	}

	@Override
	protected Object createNewInstance() {
		
		return expressageProduct;
	}

	@Override
	public Object getModel() {
		
		return getExpressageProduct();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageProduct((ExpressageProduct)obj);
		
	}

	public ExpressageProduct getExpressageProduct() {
		return expressageProduct;
	}

	public void setExpressageProduct(ExpressageProduct expressageProduct) {
		this.expressageProduct = expressageProduct;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public File getImage1() {
		return image1;
	}

	public void setImage1(File image1) {
		this.image1 = image1;
	}

	public File getImage2() {
		return image2;
	}

	public void setImage2(File image2) {
		this.image2 = image2;
	}

	public File getImage3() {
		return image3;
	}

	public void setImage3(File image3) {
		this.image3 = image3;
	}

	public File getImage4() {
		return image4;
	}

	public void setImage4(File image4) {
		this.image4 = image4;
	}

	

}
