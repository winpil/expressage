package com.cndatacom.rbac.system.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.BaseJsonPrint;
import com.cndatacom.common.utils.DateUtil;
import com.cndatacom.common.utils.GetIpUtils;
import com.cndatacom.common.utils.MD5Service;
import com.cndatacom.common.utils.MiSenderUtils;
import com.cndatacom.common.utils.MiSenderUtils1;
import com.cndatacom.common.utils.MiSenderUtils2;
import com.cndatacom.common.utils.MiSenderUtils3;
import com.cndatacom.common.utils.PushUtil;
import com.cndatacom.common.utils.WxUtil;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.alipay.config.AlipayConfig;
import com.cndatacom.rbac.alipay.sign.RSA;
import com.cndatacom.rbac.alipay.util.AlipayNotify;
import com.cndatacom.rbac.pojo.CourierExpressageMode;
import com.cndatacom.rbac.pojo.ExpressageCommon;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.pojo.ExpressageFavorable;
import com.cndatacom.rbac.pojo.ExpressageIntegral;
import com.cndatacom.rbac.pojo.ExpressageMessage;
import com.cndatacom.rbac.pojo.ExpressageOrder;
import com.cndatacom.rbac.pojo.ExpressageOrderLog;
import com.cndatacom.rbac.pojo.ExpressagePayLog;
import com.cndatacom.rbac.pojo.ExpressageProduct;
import com.cndatacom.rbac.pojo.ExpressageText;
import com.cndatacom.rbac.pojo.ExpressageToken;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.pojo.OrderModel;
import com.cndatacom.rbac.pojo.ProductModel;
import com.cndatacom.rbac.pojo.WeixinOrder;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageCommonService;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageDistrictService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageIntegralService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageProductService;
import com.cndatacom.rbac.system.service.ExpressageProvincialService;
import com.cndatacom.rbac.system.service.ExpressageTextService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * 类名: ExpressageOrderAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 订单action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-9-7
 */
@Controller
@Action("expressageOrder")
@Scope("prototype")
@Namespace("/rbac/sys")
@Results( {
	  	@Result(name = "order1", location = "/express/dingdanxq.jsp", type = "dispatcher"),
	  	@Result(name = "order2", location = "/express/kdydata.html", type = "dispatcher"),
	  	@Result(name = "order3", location = "/express/person_send.jsp", type = "dispatcher"),
	  	@Result(name = "order4", location = "/express/person_s.jsp", type = "dispatcher"),
	  	@Result(name = "order5", location = "/express/youhuiquan.jsp", type = "dispatcher"),
	  	@Result(name = "order6", location = "/express/kkxd.jsp", type = "dispatcher"),
		@Result(name = "status", type = "json", params = { "root","validateInfo" }),
})
public class ExpressageOrderAction extends SimpleActionSupport{
	
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
    private ExpressageCompanyService expressageCompanyService;
	@Resource
    private ExpressageCommonService expressageCommonService;
	@Resource
    private ExpressageProvincialService expressageProvincialService;
	@Resource
    private ExpressageCityService expressageCityService;
	@Resource
    private ExpressageDistrictService expressageDistrictService;
	@Resource
    private ExpressageTextService expressageTextService;
	@Resource
    private ExpressageProductService expressageProductService;
	@Resource
    private ExpressageIntegralService expressageIntegralService;
	
	Page<ExpressageOrder> pageOrder = new Page<ExpressageOrder>();
	Page<ExpressageProduct> pageProduct = new Page<ExpressageProduct>();
	
	private ExpressageOrder expressageOrder;
	private ExpressageCourier expressageCourier;
	private ExpressageFavorable expressageFavorable;
	private String companyName;
	private ExpressageCommon ec1;
	private ExpressageCommon ec2;
	private String ja;
	private String sa;
	private String userId;
	private WeixinOrder weixinOrder;
	private String favorableStr;
	private String sta;
	private String oId;
	
	@Action(value = "/xz")
	public String xiaozhu(){
//		List<String> ll =expressageCourierService.getCourierIdsByjw("23.0754338105", "113.3027209671");
//		System.out.println(ll);
//		
//		for (int i = 0; i < ll.size(); i++) {
//			MiSenderUtils.sendMessage(mt.getTokenName(),"","用户 "+modelUser.getName()+" 申请加您为好友，请注意查看！");
//		}
		List<Map<String,String>> ll =expressageCourierService.getCourierIdsByjw("23.0754338105", "113.3027209671");
		String token = getRequest().getParameter("token");
		System.out.println(token);
		
		return null;
	}
	
	/**
	 * 
	 * 方法名: createOrder</br>
	 * 详述: 用户下单</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/createOrder")
	public String createOrder() throws Exception{
		String token = getRequest().getParameter("token");
		String packType = getRequest().getParameter("packType");
		String packWeight = getRequest().getParameter("packWeight");
		String companyIds = getRequest().getParameter("companyIds");
		String gatherDate = getRequest().getParameter("gatherDate");
		
		String sPhone = getRequest().getParameter("sPhone");
		String sName = getRequest().getParameter("sName");
		String sAddress = getRequest().getParameter("sAddress");
		String jPhone = getRequest().getParameter("jPhone");
		String jName = getRequest().getParameter("jName");
		String jAddress = getRequest().getParameter("jAddress");
		String area = getRequest().getParameter("area");
		String channel = getRequest().getParameter("channel");
		
		try {
			//条件过滤
			if(StringUtils.isBlank(channel)){
				if(StringUtils.isBlank(jName)||StringUtils.isBlank(jAddress)||StringUtils.isBlank(gatherDate)||StringUtils.isBlank(sPhone)||StringUtils.isBlank(sName)||StringUtils.isBlank(sAddress)||StringUtils.isBlank(jPhone)||StringUtils.isBlank(token)||StringUtils.isBlank(packType)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
					writerMessageForJson(json);
					return null;
				}
			}else{
				if(StringUtils.isBlank(jName)||StringUtils.isBlank(jAddress)||StringUtils.isBlank(gatherDate)||StringUtils.isBlank(sPhone)||StringUtils.isBlank(sName)||StringUtils.isBlank(sAddress)||StringUtils.isBlank(jPhone)||StringUtils.isBlank(packType)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
					writerMessageForJson(json);
					return null;
				}
			}
			
			//验证是否登录
			ExpressageUser expressageUser = null;
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				String userId = getUserIdByToken(token,"1");
				expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
				System.out.println("");
			}else{
				String userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
				expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
			}
			
			if(expressageUser==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
				writerMessageForJson(json);
				return null;
			}
			
			
			//生成订单
			expressageOrder = new ExpressageOrder();
			expressageOrder.setType("1");
			expressageOrder.setCreateDate(new Date());
			expressageOrder.setGatherDate(gatherDate);
			expressageOrder.setjAddress(jAddress);
			expressageOrder.setjName(jName);
			expressageOrder.setjPhone(jPhone);
			expressageOrder.setPackType(packType);
			expressageOrder.setPackWeight(packWeight);
			expressageOrder.setsAddress(sAddress);
			expressageOrder.setsName(sName);
			expressageOrder.setsPhone(sPhone);
			expressageOrder.setStatus("1");
			expressageOrder.setUserId(expressageUser.getUserId());
			expressageOrder.setFavorableId(companyIds);
			if(StringUtils.isBlank(area)){
				expressageOrder.setArea("韶关市");
			}else{
				expressageOrder.setArea(area);
			}
			
			
			SimpleDateFormat sdf=new SimpleDateFormat("ssSSS");
			String datastr = sdf.format(new Date());
			StringBuilder str=new StringBuilder();//定义变长字符串
			Random random=new Random();
			//随机生成数字，并添加到字符串
			for(int i=0;i<8;i++){
			    str.append(random.nextInt(10));
			}
			
			expressageOrder.setOrderNo(str.toString()+datastr);
			expressageOrderService.save(expressageOrder);
			
			//推送所有符合要求的快递员
			List<String> strList = new ArrayList<String>();
			if(StringUtils.isBlank(companyIds)||companyIds.equals("全部")){
				strList = expressageCourierService.find(" select courierId from ExpressageCourier where status = '1' and area = '"+area+"' ");
			}else{
				strList = expressageCourierService.find(" select courierId from ExpressageCourier where status = '1' and companyId = '"+companyName+"' and area = '"+area+"'  ");
			}
			for (int i = 0; i < strList.size(); i++) {
				try {
					String cStr = strList.get(i);
					ExpressageToken et =  expressageTokenService.findUnique(" from ExpressageToken where type = '2' and userId = '"+cStr+"' ");
					if(et!=null&&et.getTokenName()!=null){
						MiSenderUtils1.sendMessage(et.getTokenName(),"100002","亲，您附近有人要发快递，赶紧抢单吧。","100002");
						MiSenderUtils3.sendMessage(et.getTokenName(),"亲，您附近有人要发快递，赶紧抢单吧。","亲，您附近有人要发快递，赶紧抢单吧。","100002");
					}
				} catch (Exception e) {
					continue;
				}
			}
			
			//记录订单日志
			ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
			expressageOrderLog.setContent("用户 "+expressageUser.getUserName()+" 下单成功,生成订单号："+expressageOrder.getOrderNo());
			expressageOrderLog.setCreateDate(new Date());
			expressageOrderLog.setMethod("createOrder");
			expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
			expressageOrderLog.setType("1");
			expressageOrderLog.setUserId(userId);
			expressageOrderLogService.save(expressageOrderLog);
			
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法名: createOrderByIntegral</br>
	 * 详述:用户积分商城下单 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/createOrderByIntegral")
	public String createOrderByIntegral() throws Exception{
		String token = getRequest().getParameter("token");
		String productId = getRequest().getParameter("productId");
		
		String sPhone = getRequest().getParameter("sPhone");
		String sName = getRequest().getParameter("sName");
		String sAddress = getRequest().getParameter("sAddress");
		String channel = getRequest().getParameter("channel");
		
		try {
			//条件过滤
			if(StringUtils.isBlank(channel)){
				if(StringUtils.isBlank(sPhone)||StringUtils.isBlank(sName)||StringUtils.isBlank(sAddress)||StringUtils.isBlank(token)||StringUtils.isBlank(productId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
					writerMessageForJson(json);
					return null;
				}
			}else{
				if(StringUtils.isBlank(sPhone)||StringUtils.isBlank(sName)||StringUtils.isBlank(sAddress)||StringUtils.isBlank(productId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
					writerMessageForJson(json);
					return null;
				}
			}
			
			//验证是否登录
			ExpressageUser expressageUser = null;
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				String userId = getUserIdByToken(token,"1");
				expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
			}else{
				String userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
				expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
			}
			if(expressageUser==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
				writerMessageForJson(json);
				return null;
			}
			
			ExpressageProduct ep = expressageProductService.findUniqueBy("productId", productId);
			if(ep==null||ep.getStatus().equals("0")){
				JSONObject json = BaseJsonPrint.SynthesisJson("100003", "商品不存在或已下架", null);
				writerMessageForJson(json);
				return null;
			}
			
			if(Integer.valueOf(expressageUser.getValidIntegral())<Integer.valueOf(ep.getIntegral())){
				JSONObject json = BaseJsonPrint.SynthesisJson("100013", "您的积分不够", null);
				writerMessageForJson(json);
				return null;
			}
			
			
			//生成订单
			expressageOrder = new ExpressageOrder();
			expressageOrder.setType("2");
			expressageOrder.setCreateDate(new Date());
			expressageOrder.setsAddress(sAddress);
			expressageOrder.setsName(sName);
			expressageOrder.setsPhone(sPhone);
			expressageOrder.setStatus("1");
			expressageOrder.setUserId(expressageUser.getUserId());
			expressageOrder.setProductId(productId);
			expressageOrder.setIntegralNum(ep.getIntegral());
			expressageOrder.setFavorableId(companyName);
			
			SimpleDateFormat sdf=new SimpleDateFormat("ssSSS");
			String datastr = sdf.format(new Date());
			StringBuilder str=new StringBuilder();//定义变长字符串
			Random random=new Random();
			//随机生成数字，并添加到字符串
			for(int i=0;i<8;i++){
			    str.append(random.nextInt(10));
			}
			
			expressageOrder.setOrderNo(str.toString()+datastr);
			expressageOrderService.save(expressageOrder);
			
			expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())-Integer.valueOf(ep.getIntegral())));
			expressageUserService.save(expressageUser);
			
			ExpressageIntegral ei = new ExpressageIntegral();
			ei = new ExpressageIntegral();
			ei.setContent("积分兑换");
			ei.setCreateDate(new Date());
			ei.setIntegralNum("-"+ep.getIntegral());
			ei.setStatus("1");
			ei.setType("3");
			ei.setUserId(expressageUser.getUserId());
			expressageIntegralService.save(ei);
			
			
			//记录订单日志
			ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
			expressageOrderLog.setContent("用户 "+expressageUser.getUserName()+" 下单成功,生成订单号："+expressageOrder.getOrderNo());
			expressageOrderLog.setCreateDate(new Date());
			expressageOrderLog.setMethod("createOrder");
			expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
			expressageOrderLog.setType("1");
			expressageOrderLog.setUserId(expressageUser.getUserId());
			expressageOrderLogService.save(expressageOrderLog);
			
			//生成系统消息
			ExpressageMessage em = new ExpressageMessage();
			em.setContent("待发货！您的积分兑换已成功，待发货。");
			em.setUserId(expressageOrder.getUserId());
			em.setCreateDate(new Date());
			em.setTitle("积分下单");
			em.setStatus("2");
			expressageMessageService.save(em);
			
			
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: getOrdersByCourier</br>
	 * 详述: 快递员待抢单列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getOrdersByCourier")
	public String getOrdersByCourier() throws Exception{
		String token = getRequest().getParameter("token");
		String pageNo=getRequest().getParameter("pageNo");
		String pageSize=getRequest().getParameter("pageSize");
		String type=getRequest().getParameter("type");
		
		//条件过滤
		if(StringUtils.isBlank(token)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(expressageCourier.getStatus().equals("0")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账户已经被禁，请联系管理员", null);
			writerMessageForJson(json);
			return null;
		}
		pageOrder.setStart(Integer.parseInt(pageNo)*Integer.parseInt(pageSize));
		pageOrder.setLimit(Integer.parseInt(pageSize));
		
		String sqlStr = "";
		if(type.equals("1")){
			sqlStr =" from ExpressageOrder where status = '1' and type = '1' and area = '"+expressageCourier.getArea()+"' and (favorableId = '全部' or favorableId like '%"+expressageCourier.getCompanyId()+"%'  ) order by createDate desc ";
		}else if(type.equals("2")){
			sqlStr =" from ExpressageOrder where  type = '1' and courierId = '"+courierId+"'  order by createDate desc ";
		}
		System.out.println(sqlStr);
		expressageOrderService.findPage(pageOrder,sqlStr );
		
		List<OrderModel> omList = new ArrayList<OrderModel>();
		List<ExpressageOrder> eoList =  pageOrder.getResult();
		System.out.println(eoList.size());
		for (int i = 0; i < eoList.size(); i++) {
			ExpressageOrder eo = eoList.get(i);
			OrderModel om = new OrderModel();
			BeanUtils.copyProperties(om, eo);
			om.setCreateDate(DateUtil.format(eo.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			om.setOrderDate(DateUtil.format(eo.getOrderDate(),"yyyy-MM-dd HH:mm:ss"));
			ExpressageUser eu = expressageUserService.findUniqueBy("userId", eo.getUserId());
			if(eu!=null){
				om.setReputation(eu.getReputation());
			}
			
			if(eo.getFavorableId()!=null&&!eo.getFavorableId().equals("全部")){
				String[] str = eo.getFavorableId().split(",");
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < str.length; j++) {
					ExpressageCompany ec = expressageCompanyService.findUniqueBy("companyId", str[j]);
					if(ec!=null){
						if(j==0){
							sb.append(ec.getCompanyName());
						}else{
							sb.append(","+ec.getCompanyName());
						}
					}
				}
				om.setFavorableName(sb.toString());
			}else{
				om.setFavorableName("全部");
			}
			
			omList.add(om);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(omList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getOrdersByUser</br>
	 * 详述: 用户订单列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-9</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getOrdersByUser")
	public String getOrdersByUser() throws Exception{
		String token = getRequest().getParameter("token");
		String pageNo=getRequest().getParameter("pageNo");
		String pageSize=getRequest().getParameter("pageSize");
		String type=getRequest().getParameter("type");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		//验证是否登录
		ExpressageUser expressageUser = null;
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}else{
			userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}
		
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		
		pageOrder.setStart(Integer.parseInt(pageNo)*Integer.parseInt(pageSize));
		pageOrder.setLimit(Integer.parseInt(pageSize));
		
		StringBuffer sb = new StringBuffer("  from ExpressageOrder where  userId = ? ");
		//type1待接单2待付款3待签收4完成
		if(type.equals("1")){//待接单
			sb.append(" and status = '1' and type = '1' ");
		}else if(type.equals("2")){
			sb.append(" and status = '2' and type = '1' ");
		}else if(type.equals("3")){//商城订单2为待签收
			sb.append(" and status = '2' and type = '2' ");
		}else if(type.equals("4")){
			sb.append(" and status = '3'  ");
		}
		
		sb.append(" order by createDate desc ");
		
		
		expressageOrderService.findPage(pageOrder, sb.toString(), userId);
		
		List<OrderModel> omList = new ArrayList<OrderModel>();
		List<ExpressageOrder> eoList =  pageOrder.getResult();
		for (int i = 0; i < eoList.size(); i++) {
			ExpressageOrder eo = eoList.get(i);
			OrderModel om = new OrderModel();
			
			BeanUtils.copyProperties(om, eo);
			om.setCreateDate(DateUtil.format(eo.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			om.setOrderDate(DateUtil.format(eo.getOrderDate(),"yyyy-MM-dd HH:mm:ss"));
			if(eo.getType().equals("2")&&eo.getProductId()!=null){
				ExpressageProduct expressageProduct = expressageProductService.findUniqueBy("productId", eo.getProductId());
				if(expressageProduct!=null){
					om.setProductName(expressageProduct.getProductName());
					om.setImg1(expressageProduct.getImg1());
					om.setIntegral(expressageProduct.getIntegral());
				}
			}
			if(StringUtils.isNotBlank(eo.getCourierId())){
				ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", eo.getCourierId());
				CourierExpressageMode cem = new CourierExpressageMode();
				cem.setAvatar(GetIpUtils.SplicePath(getRequest(), expressageCourier.getAvatar()));
				cem.setCourierName(expressageCourier.getCourierName());
				cem.setPhone(expressageCourier.getPhone());
				cem.setGender(expressageCourier.getGender());
				
				if(expressageCourier.getCompanyId()!=null){
					ExpressageCompany expressageCompany = expressageCompanyService.findUniqueBy("companyId", expressageCourier.getCompanyId());
					if(expressageCompany!=null){
						cem.setCompanyName(expressageCompany.getCompanyName());
					}else{
						cem.setCompanyName("");
					}
				}
				om.setCem(cem);
			}
			
			if(eo.getFavorableId()!=null&&!eo.getFavorableId().equals("全部")){
				String[] str = eo.getFavorableId().split(",");
				StringBuffer sb1 = new StringBuffer();
				for (int j = 0; j < str.length; j++) {
					ExpressageCompany ec = expressageCompanyService.findUniqueBy("companyId", str[j]);
					if(ec!=null){
						if(j==0){
							sb1.append(ec.getCompanyName());
						}else{
							sb1.append(","+ec.getCompanyName());
						}
					}
				}
				om.setFavorableName(sb1.toString());
			}else{
				om.setFavorableName("全部");
			}
			
			omList.add(om);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(omList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	@Action(value = "/getOrderNumByType")
	public String getOrderNumByType() throws Exception{
		String token = getRequest().getParameter("token");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		Object obj1 = "0";
		Object obj2 = "0";
		Object obj3 = "0";
		//验证是否登录
		ExpressageUser expressageUser = null;
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}else{
			userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}
		
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		obj1 = expressageOrderService.find("select count(*) from ExpressageOrder where  userId = ? and status = '1' and type = '1'  ",userId);
		obj2 = expressageOrderService.find("select count(*) from ExpressageOrder where  userId = ? and status = '2' and type = '1'  ",userId);
		obj3 = expressageOrderService.find("select count(*) from ExpressageOrder where  userId = ? and status = '2' and type = '2'  ",userId);
		
		JSONObject obj = new JSONObject();
		obj.accumulate("obj1", String.valueOf(obj1).replace("[", "").replace("]", ""));
		obj.accumulate("obj2", String.valueOf(obj2).replace("[", "").replace("]", ""));
		obj.accumulate("obj3", String.valueOf(obj3).replace("[", "").replace("]", ""));
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法名: getProducts</br>
	 * 详述:积分商品列表 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getProducts")
	public String getProducts() throws Exception{
		String productName = getRequest().getParameter("productName");
		String pageNo = getRequest().getParameter("pageNo");
		String pageSize = getRequest().getParameter("pageSize");
		
		pageProduct.setStart(Integer.parseInt(pageNo)*Integer.parseInt(pageSize));
		pageProduct.setLimit(Integer.parseInt(pageSize));
		List<ExpressageProduct> meList= new ArrayList<ExpressageProduct>();
		if(StringUtils.isNotBlank(productName)){
			expressageProductService.findPage(pageProduct," from ExpressageProduct where productName like '%"+productName+"%' and status = '1'  order by createDate desc ");
		}else{
			expressageProductService.findPage(pageProduct," from ExpressageProduct where status = '1' order by createDate desc ");
		}
		
		meList = pageProduct.getResult();
		List<ProductModel> pmList = new ArrayList<ProductModel>();
		for (int i = 0; i < meList.size(); i++) {
			ProductModel pm = new ProductModel();
			ExpressageProduct ep = meList.get(i);
			BeanUtils.copyProperties(pm, ep);
			pm.setCreateDate(DateUtil.format(ep.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			List<String> sList = new ArrayList<>();
			if(StringUtils.isNotBlank(ep.getImg2())){
				sList.add(ep.getImg2());
			}
			if(StringUtils.isNotBlank(ep.getImg3())){
				sList.add(ep.getImg3());
			}
			if(StringUtils.isNotBlank(ep.getImg4())){
				sList.add(ep.getImg4());
			}
			pm.setImgList(sList);
			pmList.add(pm);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(pmList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	public   String tokName = "";
	public   String content = "";
	
	/**
	 * 
	 * 方法名: receiveKKOrderByCourier</br>
	 * 详述: 抢单</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/receiveKKOrderByCourier")
	public String receiveKKOrderByCourier() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		
		if(expressageCourier.getIsAuth().equals("0")||expressageCourier.getIsAuth().equals("2")||expressageCourier.getIsAuth().equals("3")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100018", "个人资料需要完善并审核通过后，才能抢单", null);
			writerMessageForJson(json);
			return null;
		}
		
		if(Double.valueOf(expressageCourier.getBalance())<2){
			JSONObject json = BaseJsonPrint.SynthesisJson("100022", "余额不足，不能抢单", null);
			writerMessageForJson(json);
			return null;
		}
		if(expressageCourier.getRank().equals("1")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100022", "信誉度太低，不能抢单", null);
			writerMessageForJson(json);
			return null;
		}
		
		expressageOrder = receiveKKOrderByCourierMain(orderId,expressageOrderService,courierId);
		if(expressageOrder==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100017", "On no~~,您下手慢了点！", null);
			writerMessageForJson(json);
			return null;
		}else{
			//加订单日志
			ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
			expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 被快递员："+expressageCourier.getCourierName()+" 抢单成功");
			expressageOrderLog.setCreateDate(new Date());
			expressageOrderLog.setMethod("receiveOrderByCourier");
			expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
			expressageOrderLog.setType("2");
			expressageOrderLog.setUserId(courierId);
			expressageOrderLogService.save(expressageOrderLog);
			
			//扣费日志
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setOrderId(orderId);
			expressagePayLog.setRemark("抢单扣费");
			expressagePayLog.setStatus("1");
			expressagePayLog.setType("5");
			expressagePayLog.setCourierId(courierId);
			expressagePayLog.setUserId(expressageOrder.getUserId());
			
			if(expressageCourier.getRank().equals("4")||expressageCourier.getRank().equals("5")){
				ExpressageText et = expressageTextService.findUnique(" from ExpressageText where type = '5' and status = '1' ");
				expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())-Double.valueOf(et.getRank())));
				expressagePayLog.setMoneyNum("-"+et.getRank());
			}else if(expressageCourier.getRank().equals("3")){
				ExpressageText et = expressageTextService.findUnique(" from ExpressageText where type = '5' and status = '2' ");
				expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())-Double.valueOf(et.getRank())));
				expressagePayLog.setMoneyNum("-"+et.getRank());
			}else if(expressageCourier.getRank().equals("2")){
				ExpressageText et = expressageTextService.findUnique(" from ExpressageText where type = '5' and status = '3' ");
				expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())-Double.valueOf(et.getRank())));
				expressagePayLog.setMoneyNum("-"+et.getRank());
			}
			
			if(expressageCourier.getRank().equals("5")){
				expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())+1));
			}else{
				if(Integer.valueOf(expressageCourier.getClientNumber())>=9){
					expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())-9));
					expressageCourier.setRank(String.valueOf(Integer.valueOf(expressageCourier.getRank())+1));
				}else{
					expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())+1));
				}
			}
			
			expressageCourierService.save(expressageCourier);
			expressagePayLogService.save(expressagePayLog);
			
			ExpressageCompany ecompany = expressageCompanyService.findUniqueBy("companyId", expressageCourier.getCompanyId());
			DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
			//加用户系统信息
			ExpressageMessage em = new ExpressageMessage();
			if(ecompany!=null){
				em.setContent("待揽件！您"+DateUtil.parseDateToStr(expressageOrder.getCreateDate(), "yyyy-MM-dd")+"寄出的快递已被"+ecompany.getCompanyName()+expressageCourier.getCourierName()+"接单啦，请等待快递员上门揽件。 ");
			}else{
				em.setContent("待揽件！您"+DateUtil.parseDateToStr(expressageOrder.getCreateDate(), "yyyy-MM-dd")+"寄出的快递已被"+expressageCourier.getCourierName()+"接单啦，请等待快递员上门揽件。");
			}
			em.setCourierId(courierId);
			em.setUserId(expressageOrder.getUserId());
			em.setCreateDate(new Date());
			em.setTitle("接单提醒");
			em.setStatus("2");
			expressageMessageService.save(em);
			//推送用户
			ExpressageToken et = expressageTokenService.findUnique(" from ExpressageToken where type = '1' and userId = ?", expressageOrder.getUserId());
			
			if(et!=null){
				tokName = et.getTokenName();
				if(ecompany!=null){
					content = "待揽件！您"+DateUtil.parseDateToStr(expressageOrder.getCreateDate(), "yyyy-MM-dd")+"寄出的快递已被"+ecompany.getCompanyName()+expressageCourier.getCourierName()+"接单啦，请等待快递员上门揽件。";
				}else{
					content = "待揽件！您"+DateUtil.parseDateToStr(expressageOrder.getCreateDate(), "yyyy-MM-dd")+"寄出的快递已被"+expressageCourier.getCourierName()+"接单啦，请等待快递员上门揽件。";
				}
				Thread t = new Thread(){
					public void run(){
						try {
							MiSenderUtils.sendMessage(tokName,"100003",content,"100003");
							MiSenderUtils2.sendMessage(tokName,content,content,"100003");
						} catch (Exception e) {
						}
					}
				};
				t.start();
				
			}
			
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: receiveKKOrderByCourierMain</br>
	 * 详述: 抢单核心方法，加代码锁，防止同一单被多人抢</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @param orderId
	 * @param expressageOrderService
	 * @param eo
	 * @return
	 */
	public static synchronized ExpressageOrder receiveKKOrderByCourierMain(String orderId,ExpressageOrderService expressageOrderService,String courierId){
		ExpressageOrder eo = expressageOrderService.findUnique(" from ExpressageOrder where type = '1' and status = 1 and orderId = ? ", orderId);
		if(eo!=null){
			eo.setStatus("2");
			eo.setCourierId(courierId);
			eo.setOrderDate(new Date());
			expressageOrderService.save(eo);
		}
		return eo;
	}
	
	/**
	 * 
	 * 方法名: abolishOrder</br>
	 * 详述:取消寄件 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/abolishOrder")
	public String abolishOrder() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(orderId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		//验证是否登录
		ExpressageUser expressageUser = null;
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		
		
		expressageOrder = expressageOrderService.findUniqueBy("orderId", orderId);
		if(expressageOrder==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100020", "订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		
		if(expressageOrder.getStatus().equals("3")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100020", "订单已支付不能取消", null);
			writerMessageForJson(json);
			return null;
		}else if(expressageOrder.getStatus().equals("1")){
			expressageOrder.setStatus("4");
			expressageOrderService.save(expressageOrder);
		}else if(expressageOrder.getStatus().equals("2")){//快递已接单，取消扣信誉度
			expressageOrder.setStatus("4");
			expressageOrderService.save(expressageOrder);
			
			//快递员加系统信息
			if(expressageOrder.getCourierId()!=null){
				ExpressageMessage expressageMessage =new ExpressageMessage();
				expressageMessage.setContent("用户："+expressageUser.getUserName()+" 取消了寄件，请知晓。");
				expressageMessage.setCourierId(expressageOrder.getCourierId());
				expressageMessage.setCreateDate(new Date());
				expressageMessage.setStatus("4");
				expressageMessage.setTitle("取消寄件");
				expressageMessageService.save(expressageMessage);
				
				ExpressageToken et = expressageTokenService.findUnique(" from ExpressageToken where type = 2 and userId = ?", expressageOrder.getCourierId());
				try {
					MiSenderUtils1.sendMessage(et.getTokenName(),"100004","用户‘"+expressageUser.getUserName()+"’已取消寄件订单，请知晓。","100004");
					MiSenderUtils3.sendMessage(et.getTokenName(),"用户‘"+expressageUser.getUserName()+"’已取消寄件订单，请知晓。","用户‘"+expressageUser.getUserName()+"’已取消寄件订单，请知晓。","100004");
				} catch (Exception e) {
				}
				
				
				//抢单费回退，表示进行中
				ExpressagePayLog epl = expressagePayLogService.findUnique(" from ExpressagePayLog where orderId = ? and status = '1' and type = '5'  ", orderId);
				
				ExpressagePayLog expressagePayLog = new ExpressagePayLog();
				expressagePayLog.setCreateDate(new Date());
				expressagePayLog.setOrderId(orderId);
				expressagePayLog.setRemark("扣费回退");
				expressagePayLog.setStatus("2");
				expressagePayLog.setType("7");
				if(epl!=null){
					expressagePayLog.setMoneyNum("+"+epl.getMoneyNum().replaceAll("-", ""));
				}else{
					expressagePayLog.setMoneyNum("+0");
				}
				
				expressagePayLog.setCourierId(expressageOrder.getCourierId());
				expressagePayLog.setUserId(expressageOrder.getUserId());
				
				expressagePayLogService.save(expressagePayLog);
			}
			
		}else{
			JSONObject json = BaseJsonPrint.SynthesisJson("100020", "订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		
		ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
		expressageOrderLog.setContent("取消订单："+expressageOrder.getOrderNo());
		expressageOrderLog.setCreateDate(new Date());
		expressageOrderLog.setMethod("abolishOrder");
		expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
		expressageOrderLog.setType("1");
		expressageOrderLog.setUserId(userId);
		expressageOrderLogService.save(expressageOrderLog);
		
		
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: payOrder</br>
	 * 详述:支付 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-11</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/payOrder")
	public String payOrder() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		String type = getRequest().getParameter("type");
		String orderPrice = getRequest().getParameter("orderPrice");
		String favorableId = getRequest().getParameter("favorableId");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)||StringUtils.isBlank(type)||StringUtils.isBlank(orderPrice)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(orderId)||StringUtils.isBlank(type)||StringUtils.isBlank(orderPrice)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		//验证是否登录
		ExpressageUser expressageUser = null;
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}else{
			userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		expressageOrder = expressageOrderService.findUniqueBy("orderId", orderId);
		if(expressageOrder==null||!expressageOrder.getStatus().equals("2")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100020", "订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		double dd = Double.valueOf(orderPrice);
		int   is   =   (new   Double(dd)).intValue(); 
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("###.00"); 
		ExpressageIntegral ei = new ExpressageIntegral();
		if(type.equals("1")){//现金支付
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			String sss= "0";
			if(StringUtils.isNotBlank(favorableId)){
				ExpressageFavorable ef = expressageFavorableService.findUniqueBy("favorableId", favorableId);
				if(ef==null||!ef.getStatus().equals("1")){
					JSONObject json = BaseJsonPrint.SynthesisJson("100023", "优惠券不存在或已过期", null);
					writerMessageForJson(json);
					return null;
				}
				ef.setStatus("2");
				ef.setCondit(orderId);
				sss = ef.getFavorableName();
				if(dd-Double.valueOf(sss)<=0){
					sss = dd+"";
				}
				expressagePayLog.setBankCode(ef.getFavorableName());
				expressageFavorableService.save(ef);
				expressageOrder.setLongitude(favorableId);
			}
			
			
			//生成支付日志
			
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setOrderId(orderId);
			expressagePayLog.setRemark("现金支付");
			expressagePayLog.setStatus("1");
			expressagePayLog.setType(type);
			expressagePayLog.setUserId(expressageOrder.getUserId());
			expressagePayLog.setCourierId(expressageOrder.getCourierId());
			expressagePayLogService.save(expressagePayLog);
			
			
			//修改订单状态
			expressageOrder.setOrderPrice(orderPrice);
			expressageOrder.setStatus("3");
			expressageOrderService.save(expressageOrder);
			
			//生成订单日志
			ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
			ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
			expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 被快递员："+expressageCourier.getCourierName()+" 揽收成功,用户付款成功");
			expressageOrderLog.setCreateDate(new Date());
			expressageOrderLog.setMethod("payOrder");
			expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
			expressageOrderLog.setType("2");
			expressageOrderLogService.save(expressageOrderLog);
			
			//生成系统消息
			ExpressageMessage em = new ExpressageMessage();
			em.setContent("揽收成功！您 "+expressageOrder.getCreateDate()+" 下的单，已经被快递员"+expressageCourier.getCourierName()+" 揽件成功。");
			em.setUserId(expressageOrder.getUserId());
			em.setCreateDate(new Date());
			em.setTitle("揽收提醒");
			em.setStatus("2");
			expressageMessageService.save(em);
			
			//快递员加金额，并清算信誉度
			if(Integer.valueOf(expressageCourier.getRank())==5){
				expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())+1));
			}else{
				if(Integer.valueOf(expressageCourier.getClientNumber())>=9){
					expressageCourier.setRank(String.valueOf(Integer.valueOf(expressageCourier.getRank())+1));
					expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())-9));
				}else{
					expressageCourier.setClientNumber(String.valueOf(Integer.valueOf(expressageCourier.getClientNumber())+1));
				}
			}
			
			//快递余额增加
			if(!sss.equals("0")){
				expressageCourier.setBalance(df.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(sss)));
			}
			expressageCourierService.save(expressageCourier);
			
			//用户加积分清算信誉度
			if(Integer.valueOf(expressageUser.getReputation())>=5){
				expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
			}else{
				if(Integer.valueOf(expressageUser.getClientNumber())>=9){
					expressageUser.setReputation(String.valueOf(Integer.valueOf(expressageUser.getReputation())+1));
					expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())-9));
				}else{
					expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
				}
			}
			
			expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
			expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
			
			
			expressageUserService.save(expressageUser);
			
			//加积分记录
			ei.setIntegralNum("+10");
			ei.setContent("快递下单");
			ei.setCreateDate(new Date());
			
			ei.setStatus("1");
			ei.setType("2");
			ei.setUserId(expressageUser.getUserId());
			expressageIntegralService.save(ei);
			
		}else if(type.equals("2")){//微信支付
			String ip = getRequest().getRemoteAddr();
			//生成支付日志
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			expressagePayLog.setCourierId(expressageOrder.getCourierId());
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setOrderId(orderId);
			expressagePayLog.setRemark("微信支付");
			expressagePayLog.setStatus("2");
			expressagePayLog.setType(type);
			expressagePayLog.setUserId(expressageOrder.getUserId());
			expressagePayLogService.save(expressagePayLog);
			
			SortedMap<String, Object> sp = new TreeMap<String, Object>();
			sp.put("appid", "wx93ce205b839c8848");
			sp.put("mch_id", "1403087602");
			sp.put("nonce_str", MD5Service.encryptString(String.valueOf(new Date().getTime()/1000).toString()));
			sp.put("body", "快递费支付");
			sp.put("out_trade_no", expressagePayLog.getPayId());
			sp.put("total_fee",(int)(Float.parseFloat(orderPrice)*100));
			sp.put("spbill_create_ip", ip);
			sp.put("notify_url", "http://120.76.194.20/expressage_api/rbac/sys/expressageOrder/weixinInform1");
			sp.put("trade_type", "APP");
			sp.put("sign", WxUtil.createSign(sp,"KD8Yjo5G5s5D95df665w9fdfSF6DW686"));
			
			System.out.println("调统一下单接口开始=======================================");
			String postStr="<xml><appid>"+sp.get("appid")+"</appid><mch_id>"+sp.get("mch_id")+"</mch_id>" +
					"<nonce_str>"+sp.get("nonce_str")+"</nonce_str><sign>"+sp.get("sign")+"</sign>" +
					"<body>"+sp.get("body")+"</body><out_trade_no>"+sp.get("out_trade_no")+"</out_trade_no><total_fee>"+sp.get("total_fee")+"</total_fee>" +
					"<spbill_create_ip>"+sp.get("spbill_create_ip")+"</spbill_create_ip>" +
					"<notify_url>"+sp.get("notify_url")+"</notify_url>" +
					"<trade_type>"+sp.get("trade_type")+"</trade_type></xml>";
			System.out.println("postStr===="+postStr);
			String postUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
			 
			String str = PushUtil.invokingSMS(postStr, postUrl);
			System.out.println(str);
			
			System.out.println("调统一下单接口结束=========================================");
			Document  d  =  WxUtil.parseXMLDocument(str);
			System.out.println("dddd==="+d);
			String prepay_id = d.getElementsByTagName("prepay_id").item(0).getFirstChild().getNodeValue();
			if(StringUtils.isNotBlank(prepay_id)){
				System.out.println("prepay_id==================="+prepay_id);
				
				SortedMap<String, Object> sp1 = new TreeMap<String, Object>();
				sp1.put("appid", "wx93ce205b839c8848");
				sp1.put("partnerid", "1403087602");
				sp1.put("noncestr", sp.get("nonce_str"));
				sp1.put("prepayid", prepay_id);
				sp1.put("timestamp", String.valueOf(new Date().getTime()/1000).toString());
				sp1.put("package", "Sign=WXPay");
				sp1.put("sign", WxUtil.createSign(sp1,"KD8Yjo5G5s5D95df665w9fdfSF6DW686"));
				
				JSONObject obj = new JSONObject();
				obj.accumulate("appid", sp1.get("appid"));
				obj.accumulate("partnerid", sp1.get("partnerid"));
				obj.accumulate("noncestr", sp1.get("noncestr"));
				obj.accumulate("prepayid", sp1.get("prepayid"));
				obj.accumulate("timestamp", sp1.get("timestamp"));
				obj.accumulate("package", sp1.get("package"));
				obj.accumulate("sign", sp1.get("sign"));
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
				writerMessageForJson(json);
				return null;
			}
			
//			if(Double.valueOf(orderPrice)>=90){
//				expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+100));
//				expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+100));
//				ei.setIntegralNum("+100");
//			}else{
//				expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+is));
//				expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+is));
//				ei.setIntegralNum("+"+String.valueOf(10+is));
//			}
			
		}else if(type.equals("3")){//支付宝支付
			//生成支付日志
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			expressagePayLog.setCourierId(expressageOrder.getCourierId());
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setOrderId(orderId);
			expressagePayLog.setRemark("支付宝支付");
			expressagePayLog.setStatus("2");
			expressagePayLog.setType(type);
			expressagePayLog.setUserId(expressageOrder.getUserId());
			expressagePayLogService.save(expressagePayLog);
			
			String str = getOrderInfo1(AlipayConfig.partner, "13826373331", expressagePayLog.getPayId(), "快递费支付", "快递费支付："+orderPrice, orderPrice);
			String sing = RSA.sign(str, AlipayConfig.private_key, AlipayConfig.input_charset);
			
			JSONObject obj = new JSONObject();
			obj.accumulate("alipayStr", str + "&sign=\"" + URLEncoder.encode(sing, "UTF-8") + "\"&sign_type="  + "\""+ AlipayConfig.sign_type + "\"");
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
			writerMessageForJson(json);
			return null;
			
		}else if(type.equals("4")){//微信公众号支付
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			String sss =orderPrice;
			if(StringUtils.isNotBlank(favorableId)){
				ExpressageFavorable ef = expressageFavorableService.findUniqueBy("favorableId", favorableId);
				if(ef==null||!ef.getStatus().equals("1")){
					JSONObject json = BaseJsonPrint.SynthesisJson("100023", "优惠券不存在或已过期", null);
					writerMessageForJson(json);
					return null;
				}
				sss = ef.getFavorableName();
				System.out.println("11111111111111111111111======"+sss);
				System.out.println("22222222222222222222222======"+dd);
				if(dd-Double.valueOf(sss)>0){
					sss = df.format(dd-Double.valueOf(sss));
					System.out.println("33333333333333333333======"+df.format(dd-Double.valueOf(sss)));
					System.out.println("44444444444444444444======"+sss);
				}
				expressagePayLog.setBankCode(ef.getFavorableName());
				expressageOrder.setLongitude(favorableId);
				expressageOrder.setOrderPrice(orderPrice);
				expressageOrderService.save(expressageOrder);
			}
			
			String ip = getRequest().getRemoteAddr();
			//生成支付日志
			
			expressagePayLog.setCourierId(expressageOrder.getCourierId());
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setOrderId(orderId);
			expressagePayLog.setRemark("微信公众号支付");
			expressagePayLog.setStatus("2");
			expressagePayLog.setType("2");
			expressagePayLog.setUserId(expressageOrder.getUserId());
			expressagePayLogService.save(expressagePayLog);
			
			SortedMap<String, Object> sp = new TreeMap<String, Object>();
			sp.put("appid", "wxb29f8c9e9415156f");
			sp.put("mch_id", "1455383802");
			sp.put("nonce_str", MD5Service.encryptString(String.valueOf(new Date().getTime()/1000).toString()));
			sp.put("body", "快递费支付");
			sp.put("out_trade_no", expressagePayLog.getPayId());
			sp.put("total_fee",(int)(Float.parseFloat(sss)*100));
			sp.put("spbill_create_ip", ip);
			sp.put("notify_url", "http://120.76.194.20/expressage_api/rbac/sys/expressageOrder/weixinInform2");
			sp.put("trade_type", "JSAPI");
			sp.put("openid", expressageUser.getOpenid());
			sp.put("sign", WxUtil.createSign(sp,"HJ89FjGTJG54GTEE569HYHYT5dF6DW66"));
			
			System.out.println("调统一下单接口开始=======================================");
			String postStr="<xml><appid>"+sp.get("appid")+"</appid><mch_id>"+sp.get("mch_id")+"</mch_id>" +
					"<nonce_str>"+sp.get("nonce_str")+"</nonce_str><sign>"+sp.get("sign")+"</sign>" +
					"<body>"+sp.get("body")+"</body><out_trade_no>"+sp.get("out_trade_no")+"</out_trade_no><total_fee>"+sp.get("total_fee")+"</total_fee>" +
					"<spbill_create_ip>"+sp.get("spbill_create_ip")+"</spbill_create_ip>" +
					"<notify_url>"+sp.get("notify_url")+"</notify_url>" +
					"<trade_type>"+sp.get("trade_type")+"</trade_type><openid>"+sp.get("openid")+"</openid></xml>";
			System.out.println("postStr===="+postStr);
			String postUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
			 
			String str = PushUtil.invokingSMS(postStr, postUrl);
			System.out.println(str);
			
			System.out.println("调统一下单接口结束=========================================");
			Document  d  =  WxUtil.parseXMLDocument(str);
			System.out.println("dddd==="+d);
			String prepay_id = d.getElementsByTagName("prepay_id").item(0).getFirstChild().getNodeValue();
			if(StringUtils.isNotBlank(prepay_id)){
				System.out.println("prepay_id==================="+prepay_id);
				
				SortedMap<String, Object> sp1 = new TreeMap<String, Object>();
				sp1.put("appId", "wxb29f8c9e9415156f");
				sp1.put("timeStamp", String.valueOf(new Date().getTime()/1000).toString());
				sp1.put("nonceStr", sp.get("nonce_str"));
				sp1.put("package", "prepay_id="+prepay_id);
				sp1.put("signType", "MD5");
				sp1.put("sign", WxUtil.createSign(sp1,"HJ89FjGTJG54GTEE569HYHYT5dF6DW66"));
				
				JSONObject obj = new JSONObject();
				obj.accumulate("appId", sp1.get("appId"));
				obj.accumulate("timeStamp", sp1.get("timeStamp"));
				obj.accumulate("nonceStr", sp1.get("nonceStr"));
				obj.accumulate("packages", sp1.get("package"));
				obj.accumulate("signType", sp1.get("signType"));
				obj.accumulate("paySign", sp1.get("sign"));
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
				writerMessageForJson(json);
				return null;
			}
			
			
		}else{
			JSONObject json = BaseJsonPrint.SynthesisJson("100021", "支付方式有误", null);
			writerMessageForJson(json);
			return null;
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: weixinInform1</br>
	 * 详述: 微信支付异步通知回调</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-9</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/weixinInform1")
	public String weixinInform1() throws Exception{
		System.out.println("微信异步通知回调开始================================");
		Document document = WxUtil.getDocumentBuilder().parse(getRequest().getInputStream());
		String return_code = document.getElementsByTagName("return_code").item(0).getFirstChild().getNodeValue();
		System.out.println("=======1="+return_code);
		
		if(return_code.equals("SUCCESS")){
			String result_code = document.getElementsByTagName("result_code").item(0).getFirstChild().getNodeValue();
			if(result_code.equals("SUCCESS")){
				System.out.println("微信支付异步通知回调业务处理开始=================================");
				String out_trade_no = document.getElementsByTagName("out_trade_no").item(0).getFirstChild().getNodeValue();
				System.out.println("111111111111111111111111111111111111111111111111");
				ExpressagePayLog expressagePayLog =expressagePayLogService.findUniqueBy("payId", out_trade_no);
				System.out.println("222222222222222222222222222222222222222222222222");
				if(expressagePayLog!=null&&expressagePayLog.getStatus().equals("2")){
					System.out.println("333333333333333333333333333333333333333333333");
					ExpressageOrder expressageOrder =expressageOrderService.findUniqueBy("orderId", expressagePayLog.getOrderId());
					if(expressageOrder!=null&&expressageOrder.getType().equals("1")&&expressageOrder.getStatus().equals("2")){
						String total_fee = document.getElementsByTagName("total_fee").item(0).getFirstChild().getNodeValue();
						//支付状态修改
						expressagePayLog.setStatus("1");
						expressagePayLogService.save(expressagePayLog);
						System.out.println("44444444444444444444444444444444444444444444");
						//订单状态修改
						expressageOrder.setStatus("3");
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("###.00"); 
						expressageOrder.setOrderPrice(df.format(Double.valueOf(total_fee)/100));
						expressageOrder.setPayId(out_trade_no);
						expressageOrderService.save(expressageOrder);
						System.out.println("5555555555555555555555555555555555555555555555");
						//生成订单日志
						ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
						ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
						expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 被快递员："+expressageCourier.getCourierName()+" 揽收成功,用户付款成功");
						expressageOrderLog.setCreateDate(new Date());
						expressageOrderLog.setMethod("alipayInform1");
						expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
						expressageOrderLog.setType("2");
						expressageOrderLogService.save(expressageOrderLog);
						System.out.println("666666666666666666666666666666666666666666666");
						//生成系统消息
						ExpressageMessage em = new ExpressageMessage();
						em.setContent("揽收成功！您 "+expressageOrder.getCreateDate()+" 下的单，已经被快递员"+expressageCourier.getCourierName()+" 揽件成功。");
						em.setUserId(expressageOrder.getUserId());
						em.setCreateDate(new Date());
						em.setTitle("揽收提醒");
						em.setStatus("2");
						expressageMessageService.save(em);
						System.out.println("77777777777777777777777777777777777777777777");
						
						//快递余额增加
						expressageCourier.setBalance(df.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(total_fee)/100));
						expressageCourierService.save(expressageCourier);
						System.out.println("8888888888888888888888888888888888888888888888888");
					
						ExpressageUser expressageUser = expressageUserService.findUniqueBy("userId", expressageOrder.getUserId());
						ExpressageIntegral ei = new ExpressageIntegral();
						//用户加积分清算信誉度
						double dd = Double.valueOf(total_fee)/100;
						int   is   =   (new   Double(dd)).intValue(); 
						
						String dataStr = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
						Object obj = expressagePayLogService.find("select count(*) from ExpressagePayLog where userId = ? and status = '1' and type in ('1','2','3') and" +
								" createDate like '%"+dataStr+"%' ",expressageOrder.getUserId());
						String orderNum = String.valueOf(obj).replace("[", "").replace("]", "");
						if(Integer.valueOf(orderNum)<4){//积分正常规则
							if(Double.valueOf(total_fee)/100>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+110));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+110));
								ei.setIntegralNum("+110");
							}else if(Double.valueOf(total_fee)/100<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
							}else{
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+is));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+is));
								ei.setIntegralNum("+"+String.valueOf(10+is));
							}
						}else if(Integer.valueOf(orderNum)>=4&&Integer.valueOf(orderNum)<6){//积分减半
							if(Double.valueOf(total_fee)/100>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+60));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+60));
								ei.setIntegralNum("+60");
							}else if(Double.valueOf(total_fee)/100<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
							}else{
								int   iis   =   (new   Double(dd*0.5)).intValue(); 
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+iis));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+iis));
								ei.setIntegralNum("+"+String.valueOf(10+iis));
							}
						}else{//积分10加2
							expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+12));
							expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+12));
							ei.setIntegralNum("+12");
						}
						
						
						if(Integer.valueOf(expressageUser.getReputation())>=5){
							expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
						}else{
							if(Integer.valueOf(expressageUser.getClientNumber())>=9){
								expressageUser.setReputation(String.valueOf(Integer.valueOf(expressageUser.getReputation())+1));
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())-9));
							}else{
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
							}
						}
						
						expressageUserService.save(expressageUser);
						System.out.println("9999999999999999999999999999999999999999999");
						
						ei.setContent("快递下单");
						ei.setCreateDate(new Date());
						
						ei.setStatus("1");
						ei.setType("2");
						ei.setUserId(expressageUser.getUserId());
						expressageIntegralService.save(ei);
						System.out.println("1212222222222222222222222222222222222222");
					}
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					
				}
				System.out.println("微信支付通知回调业务处理完成================================");
			}
		}
		
		
		SortedMap<String, Object> resMap = new TreeMap<String, Object>();
		resMap.put("return_code", return_code);
		resMap.put("return_msg", "OK");
		PrintWriter writer = null;

		getResponse().setHeader("ContentType", "text/xml");
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);

		try {
		writer = getResponse().getWriter();
		writer.flush();
		writer.print(WxUtil.callMapToXML(resMap));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("微信支付异步通知回调结束=======================");
		return null;
	}
	
	
	@Action(value = "/weixinInform2")
	public String weixinInform2() throws Exception{
		System.out.println("微信公众号异步通知回调开始================================");
		Document document = WxUtil.getDocumentBuilder().parse(getRequest().getInputStream());
		String return_code = document.getElementsByTagName("return_code").item(0).getFirstChild().getNodeValue();
		System.out.println("=======1="+return_code);
		
		if(return_code.equals("SUCCESS")){
			String result_code = document.getElementsByTagName("result_code").item(0).getFirstChild().getNodeValue();
			if(result_code.equals("SUCCESS")){
				System.out.println("微信公众号支付异步通知回调业务处理开始=================================");
				String out_trade_no = document.getElementsByTagName("out_trade_no").item(0).getFirstChild().getNodeValue();
				System.out.println("111111111111111111111111111111111111111111111111");
				ExpressagePayLog expressagePayLog =expressagePayLogService.findUniqueBy("payId", out_trade_no);
				System.out.println("222222222222222222222222222222222222222222222222");
				if(expressagePayLog!=null&&expressagePayLog.getStatus().equals("2")){
					System.out.println("333333333333333333333333333333333333333333333");
					ExpressageOrder expressageOrder =expressageOrderService.findUniqueBy("orderId", expressagePayLog.getOrderId());
					if(expressageOrder!=null&&expressageOrder.getType().equals("1")&&expressageOrder.getStatus().equals("2")){
						String total_fee = document.getElementsByTagName("total_fee").item(0).getFirstChild().getNodeValue();
						//支付状态修改
						expressagePayLog.setStatus("1");
						expressagePayLogService.save(expressagePayLog);
						System.out.println("44444444444444444444444444444444444444444444");
						//订单状态修改
						expressageOrder.setStatus("3");
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("###.00"); 
//						expressageOrder.setOrderPrice(String.valueOf(Double.valueOf(total_fee)/100));
						expressageOrder.setPayId(out_trade_no);
						expressageOrderService.save(expressageOrder);
						System.out.println("5555555555555555555555555555555555555555555555");
						//生成订单日志
						ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
						ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
						expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 被快递员："+expressageCourier.getCourierName()+" 揽收成功,用户付款成功");
						expressageOrderLog.setCreateDate(new Date());
						expressageOrderLog.setMethod("alipayInform1");
						expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
						expressageOrderLog.setType("2");
						expressageOrderLogService.save(expressageOrderLog);
						//生成系统消息
						ExpressageMessage em = new ExpressageMessage();
						em.setContent("揽收成功！您 "+expressageOrder.getCreateDate()+" 下的单，已经被快递员"+expressageCourier.getCourierName()+" 揽件成功。");
						em.setUserId(expressageOrder.getUserId());
						em.setCreateDate(new Date());
						em.setTitle("揽收提醒");
						em.setStatus("2");
						expressageMessageService.save(em);
						
						String efss = "0";
						//修改优惠券状态
						if(StringUtils.isNotBlank(expressageOrder.getLongitude())){
							System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
							ExpressageFavorable ef = expressageFavorableService.findUniqueBy("favorableId", expressageOrder.getLongitude());
							if(ef!=null){
								System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
								ef.setStatus("2");
								efss = ef.getFavorableName();
								expressageFavorableService.save(ef);
							}
						}
						
						//快递余额增加
						expressageCourier.setBalance(df.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(expressageOrder.getOrderPrice())));
//						if(!efss.equals("0")){
//							System.out.println("ddddddddddddddddddddddddddddddddddddddddddd===="+efss);
//							expressageCourier.setBalance(df.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(efss)));
//						}else{
//							System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee===="+efss);
//							expressageCourier.setBalance(df.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(total_fee)/100));
//						}
						expressageCourierService.save(expressageCourier);
					
						ExpressageUser expressageUser = expressageUserService.findUniqueBy("userId", expressageOrder.getUserId());
						ExpressageIntegral ei = new ExpressageIntegral();
						//用户加积分清算信誉度
						double dd = Double.valueOf(total_fee)/100;
						int   is   =   (new   Double(dd)).intValue(); 
						
						String dataStr = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
						Object obj = expressagePayLogService.find("select count(*) from ExpressagePayLog where userId = ? and status = '1' and type in ('1','2','3') and" +
								" createDate like '%"+dataStr+"%' ",expressageOrder.getUserId());
						String orderNum = String.valueOf(obj).replace("[", "").replace("]", "");
						if(Integer.valueOf(orderNum)<4){//积分正常规则
							if(Double.valueOf(total_fee)/100>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+110));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+110));
								ei.setIntegralNum("+110");
							}else if(Double.valueOf(total_fee)/100<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
							}else{
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+is));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+is));
								ei.setIntegralNum("+"+String.valueOf(10+is));
							}
						}else if(Integer.valueOf(orderNum)>=4&&Integer.valueOf(orderNum)<6){//积分减半
							if(Double.valueOf(total_fee)/100>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+60));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+60));
								ei.setIntegralNum("+60");
							}else if(Double.valueOf(total_fee)/100<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
							}else{
								int   iis   =   (new   Double(dd*0.5)).intValue(); 
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+iis));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+iis));
								ei.setIntegralNum("+"+String.valueOf(10+iis));
							}
						}else{//积分10加2
							expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+12));
							expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+12));
							ei.setIntegralNum("+12");
						}
						
						
						if(Integer.valueOf(expressageUser.getReputation())>=5){
							expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
						}else{
							if(Integer.valueOf(expressageUser.getClientNumber())>=9){
								expressageUser.setReputation(String.valueOf(Integer.valueOf(expressageUser.getReputation())+1));
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())-9));
							}else{
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
							}
						}
						
						expressageUserService.save(expressageUser);
						System.out.println("9999999999999999999999999999999999999999999");
						
						ei.setContent("快递下单");
						ei.setCreateDate(new Date());
						
						ei.setStatus("1");
						ei.setType("2");
						ei.setUserId(expressageUser.getUserId());
						expressageIntegralService.save(ei);
						
						System.out.println("1212222222222222222222222222222222222222");
					}
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					
				}
				System.out.println("微信公众号支付通知回调业务处理完成================================");
			}
		}
		
		
		SortedMap<String, Object> resMap = new TreeMap<String, Object>();
		resMap.put("return_code", return_code);
		resMap.put("return_msg", "OK");
		PrintWriter writer = null;

		getResponse().setHeader("ContentType", "text/xml");
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);

		try {
		writer = getResponse().getWriter();
		writer.flush();
		writer.print(WxUtil.callMapToXML(resMap));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("微信支付异步通知回调结束=======================");
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: alipayInform</br>
	 * 详述:支付宝充值回调 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-10</br>
	 * @param request
	 * @param response
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/alipayInform1")
	public void alipayInform1() throws Exception {
		System.out.println("支付宝充值回调开始1==================================================");
		getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out=getResponse().getWriter();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = getRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		String trade_no = getRequest().getParameter("trade_no");				//支付宝交易号
		System.out.println("trade_no==================================="+trade_no);
		String order_no = getRequest().getParameter("out_trade_no");	        //获取订单号
		System.out.println("order_no==================================="+order_no);
		String total_fee = getRequest().getParameter("total_fee");	        //获取总金额
		System.out.println("total_fee==================================="+total_fee);
		String subject = new String(getRequest().getParameter("subject").getBytes("ISO-8859-1"),"gbk");//商品名称、订单名称
	
		String body = "";
		System.out.println(AlipayNotify.verify(params));
		System.out.println("————————————支付宝回调开始——————————————————"+order_no);
		if(getRequest().getParameter("body") != null){
			body = new String(getRequest().getParameter("body").getBytes("ISO-8859-1"), "gbk");//商品描述、订单备注、描述
			System.out.println("————————————支付宝返回产品名称——————————————————"+subject);
		}
		String buyer_email = getRequest().getParameter("buyer_email");		//买家支付宝账号
		String trade_status = getRequest().getParameter("trade_status");		//交易状态
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		if(AlipayNotify.verify(params)){//验证成功
			System.out.println("验证成功===================================================");
			//请在这里加上商户的业务逻辑程序代码
			
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				System.out.println("111111111111111111111111111111111111111111111111");
				ExpressagePayLog expressagePayLog =expressagePayLogService.findUniqueBy("payId", order_no);
				System.out.println("222222222222222222222222222222222222222222222222");
				if(expressagePayLog!=null&&expressagePayLog.getStatus().equals("2")){
					System.out.println("333333333333333333333333333333333333333333333");
					ExpressageOrder expressageOrder =expressageOrderService.findUniqueBy("orderId", expressagePayLog.getOrderId());
					if(expressageOrder!=null&&expressageOrder.getType().equals("1")&&expressageOrder.getStatus().equals("2")){
						//支付状态修改
						expressagePayLog.setStatus("1");
						expressagePayLogService.save(expressagePayLog);
						System.out.println("44444444444444444444444444444444444444444444");
						//订单状态修改
						expressageOrder.setStatus("3");
						expressageOrder.setPayId(trade_no);
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("###.00"); 
						expressageOrder.setOrderPrice(df.format(Double.valueOf(total_fee)));
						expressageOrderService.save(expressageOrder);
						System.out.println("5555555555555555555555555555555555555555555555");
						//生成订单日志
						ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
						ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
						expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 被快递员："+expressageCourier.getCourierName()+" 揽收成功,用户付款成功");
						expressageOrderLog.setCreateDate(new Date());
						expressageOrderLog.setMethod("alipayInform1");
						expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
						expressageOrderLog.setType("2");
						expressageOrderLogService.save(expressageOrderLog);
						System.out.println("666666666666666666666666666666666666666666666");
						//生成系统消息
						ExpressageMessage em = new ExpressageMessage();
						em.setContent("揽收成功！您 "+expressageOrder.getCreateDate()+" 下的单，已经被快递员"+expressageCourier.getCourierName()+" 揽件成功。");
						em.setUserId(expressageOrder.getUserId());
						em.setCreateDate(new Date());
						em.setTitle("揽收提醒");
						em.setStatus("2");
						expressageMessageService.save(em);
						System.out.println("77777777777777777777777777777777777777777777");
						//快递余额增加
						expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(total_fee)));
						expressageCourierService.save(expressageCourier);
						System.out.println("8888888888888888888888888888888888888888888888888");
					
						ExpressageUser expressageUser = expressageUserService.findUniqueBy("userId", expressageOrder.getUserId());
						ExpressageIntegral ei = new ExpressageIntegral();
						//用户加积分清算信誉度
						double dd = Double.valueOf(total_fee);
						int   is   =   (new   Double(dd)).intValue(); 
						
						String dataStr = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
						Object obj = expressagePayLogService.find("select count(*) from ExpressagePayLog where userId = ? and status = '1' and type in ('1','2','3') and" +
								" createDate like '%"+dataStr+"%' ",expressageOrder.getUserId());
						String orderNum = String.valueOf(obj).replace("[", "").replace("]", "");
						if(Integer.valueOf(orderNum)<4){//积分正常规则
							if(Double.valueOf(total_fee)>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+110));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+110));
								ei.setIntegralNum("+110");
							}else if(Double.valueOf(total_fee)<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
								
							}else{
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+is));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+is));
								ei.setIntegralNum("+"+String.valueOf(10+is));
							}
						}else if(Integer.valueOf(orderNum)>=4&&Integer.valueOf(orderNum)<6){//积分减半
							if(Double.valueOf(total_fee)>=100){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+60));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+60));
								ei.setIntegralNum("+60");
							}else if(Double.valueOf(total_fee)<=10){
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10));
								ei.setIntegralNum("+10");
							}else{
								int   iis   =   (new   Double(dd*0.5)).intValue(); 
								expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+10+iis));
								expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+10+iis));
								ei.setIntegralNum("+"+String.valueOf(10+iis));
							}
						}else{//积分10加2
							expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+12));
							expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+12));
							ei.setIntegralNum("+12");
						}
						
						
						if(Integer.valueOf(expressageUser.getReputation())>=5){
							expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
						}else{
							if(Integer.valueOf(expressageUser.getClientNumber())>=9){
								expressageUser.setReputation(String.valueOf(Integer.valueOf(expressageUser.getReputation())+1));
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())-9));
							}else{
								expressageUser.setClientNumber(String.valueOf(Integer.valueOf(expressageUser.getClientNumber())+1));
							}
						}
						
						expressageUserService.save(expressageUser);
						System.out.println("9999999999999999999999999999999999999999999");
						
						ei.setContent("快递下单");
						ei.setCreateDate(new Date());
						
						ei.setStatus("1");
						ei.setType("2");
						ei.setUserId(expressageUser.getUserId());
						expressageIntegralService.save(ei);
						System.out.println("1212222222222222222222222222222222222222");
					}
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					
				}
				System.out.println("支付宝通知回调业务处理完成================================");
				out.println("success");	//请不要修改或删除
			} else {
				out.println("success");	//请不要修改或删除
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.println("错误！！！！！！！");
			out.println("fail");
		}
		out.close();
	}
	
	/**
	 * 
	 * 方法名: getOrderInfo</br>
	 * 详述:拼接支付宝参数字符串 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-9</br>
	 * @param partner
	 * @param seller_id
	 * @param getOutTradeNo
	 * @param subject
	 * @param body
	 * @param price
	 * @return
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	private String getOrderInfo1(String partner, String seller_id, String getOutTradeNo, String subject, String body, String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://120.76.194.20/expressage_api/rbac/sys/expressageOrder/alipayInform1" + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		
		return orderInfo;
	}
	
	
	
	/**
	 * 
	 * 方法名: updateOrderByCourier</br>
	 * 详述: 确认收货</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-22</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateOrderByCourier")
	public String updateOrderByCourier() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}	
		}else{
			if(StringUtils.isBlank(orderId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}	
		}
		
		//验证是否登录
		ExpressageUser expressageUser = null;
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId=? and isLock = '1' ", userId);
		}
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		expressageOrder = expressageOrderService.findUnique(" from ExpressageOrder where orderId = ? and userId = ? and status = '2'  ", orderId,userId);
		if(expressageOrder==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100020", "订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		
		
		//修改订单状态
		expressageOrder.setStatus("3");
		expressageOrderService.save(expressageOrder);
		
		//生成订单日志
		ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
		expressageOrderLog.setContent("订单:"+expressageOrder.getOrderNo()+" 完成");
		expressageOrderLog.setCreateDate(new Date());
		expressageOrderLog.setMethod("updateOrderByCourier");
		expressageOrderLog.setOrderId(expressageOrder.getOrderNo());
		expressageOrderLog.setType("2");
		expressageOrderLog.setUserId(userId);
		expressageOrderLogService.save(expressageOrderLog);
		
		//生成系统消息
		ExpressageMessage em = new ExpressageMessage();
		em.setContent("您好，您的积分兑换商品已签收成功。");
		em.setUserId(expressageOrder.getUserId());
		em.setCreateDate(new Date());
		em.setTitle("收货完成");
		em.setStatus("2");
		expressageMessageService.save(em);
		
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
//		String postStr="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxe8a02469a0ebe29a]]></appid><mch_id><![CDATA[1249327201]]></mch_id><nonce_str><![CDATA[Mn7PQyN1Y6jotrIB]]></nonce_str><sign><![CDATA[24BDF9EFA0A37317BA640E8123B4694D]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2015082217510526e2c9cbdb0203582047]]></prepay_id><trade_type><![CDATA[NATIVE]]></trade_type><code_url><![CDATA[weixin://wxpay/bizpayurl?pr=q9fmo3D]]></code_url></xml>";
//		
//		Document  d  =  parseXMLDocument(postStr);
//		System.out.println("dddd==="+d);
//		String prepay_id = d.getElementsByTagName("return_code").item(0).getFirstChild().getNodeValue();
//		System.out.println("prepay_id===="+prepay_id);
		
//		Integer i = 4;
//		System.out.println(i-1.5);
		
		
//		SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
//		packageParams.put("appid", "wx28519044fb3556eb");    
//        packageParams.put("attach", "10020");   
//        packageParams.put("body", "测试");    
//        packageParams.put("mch_id", "1324732501");      
//        packageParams.put("nonce_str", "0958001571");    
//        packageParams.put("notify_url", "http://www.sharebey.com/order/order!wxPayCallBack");    
//        packageParams.put("out_trade_no", "20160809095800423");      
//        packageParams.put("spbill_create_ip", "113.111.69.7");   
//        packageParams.put("total_fee", "1");  
//        packageParams.put("trade_type", "APP");    
//        packageParams.put("sign", createSign(packageParams));
//		System.out.println(packageParams.get("sign"));
//		Float f = 1.5f;
//		System.out.println((int)(f*100));
		
//		double dd = Double.valueOf("5");
//		int   is   =   (new   Double(dd*0.5)).intValue(); 
//		System.out.println(is);
		
		Double str = 234.435436464;
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("####.00"); 
		System.out.println(Double.valueOf("1")/100);
//		System.out.println(df.format(0.49));
		
		
		
		
		//MiSenderUtils.sendMessage("","","您好！");
//		MiSenderUtils2.sendMessage("g2Bw4QZPStTR9vZTC5OuaHNbMOp1JKZ/xV6BpknMKUM=","您好","您好！");
//		MiSenderUtils3.sendMessage("VhhNX2u4/lIteIQazR2goAy0JyH7MdurIgW+T7v5nkI=","您好","您好！11");
	}
	
		
	
	/**
	 * 
	 * 方法名: isLogin</br>
	 * 详述: 验证登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-2</br>
	 * @param token
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public void isLogin(String token,String type) throws Exception{
		System.out.println("-------------token---------------------:"+token);
		System.out.println("-------------type---------------------:"+type);
		ExpressageToken expressageToken = expressageTokenService.findUnique(" from ExpressageToken where type = ? and tokenName = ?", type,token);
		if(expressageToken==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100005", "请先登录", null);
			writerMessageForJson(json);
		}else{
			//加积分
			ExpressageUser expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId = ? and isLock = '1' ", expressageToken.getUserId());
			if(expressageUser!=null){
				String dataStr = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
				ExpressageIntegral ei = expressageIntegralService.findUnique(" from ExpressageIntegral where type = '1' and userId = ? and createDate like '"+dataStr+"%'  ", expressageUser.getUserId());
				if(ei==null){
					ei = new ExpressageIntegral();
					ei.setContent("登录");
					ei.setCreateDate(new Date());
					ei.setIntegralNum("+5");
					ei.setStatus("1");
					ei.setType("1");
					ei.setUserId(expressageUser.getUserId());
					expressageIntegralService.save(ei);
					
					expressageUser.setValidIntegral(String.valueOf(Integer.valueOf(expressageUser.getValidIntegral())+5));
					expressageUser.setIntegral(String.valueOf(Integer.valueOf(expressageUser.getIntegral())+5));
					expressageUserService.save(expressageUser);
				}
			}
		}
	}
	
	/**
	 * 
	 * 方法名: getUserIdByToken</br>
	 * 详述: 根据token获取用户id或快递员id</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-2</br>
	 * @param token
	 * @param type
	 * @return
	 */
	public String getUserIdByToken(String token,String type){
		ExpressageToken expressageToken = expressageTokenService.findUnique(" from ExpressageToken where type = ? and tokenName = ?", type,token);
		String str = expressageToken.getUserId();
		return str;
	}
	
	
	@Override
	protected Object createNewInstance() {
		return expressageOrder;
	}

	@Override
	protected IBaseService getManager() {
		return expressageOrderService;
	}

	@Override
	public Object getModel() {
		return getExpressageOrder();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageOrder((ExpressageOrder) obj);
	}

	public ExpressageOrder getExpressageOrder() {
		return expressageOrder;
	}

	public void setExpressageOrder(ExpressageOrder expressageOrder) {
		this.expressageOrder = expressageOrder;
	}

	public ExpressageCourier getExpressageCourier() {
		return expressageCourier;
	}

	public void setExpressageCourier(ExpressageCourier expressageCourier) {
		this.expressageCourier = expressageCourier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public ExpressageFavorable getExpressageFavorable() {
		return expressageFavorable;
	}

	public void setExpressageFavorable(ExpressageFavorable expressageFavorable) {
		this.expressageFavorable = expressageFavorable;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ExpressageCommon getEc1() {
		return ec1;
	}

	public void setEc1(ExpressageCommon ec1) {
		this.ec1 = ec1;
	}

	public ExpressageCommon getEc2() {
		return ec2;
	}

	public void setEc2(ExpressageCommon ec2) {
		this.ec2 = ec2;
	}

	public String getJa() {
		return ja;
	}

	public void setJa(String ja) {
		this.ja = ja;
	}

	public String getSa() {
		return sa;
	}

	public void setSa(String sa) {
		this.sa = sa;
	}

	public WeixinOrder getWeixinOrder() {
		return weixinOrder;
	}

	public void setWeixinOrder(WeixinOrder weixinOrder) {
		this.weixinOrder = weixinOrder;
	}

	public String getFavorableStr() {
		return favorableStr;
	}

	public void setFavorableStr(String favorableStr) {
		this.favorableStr = favorableStr;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}
	
	
}
