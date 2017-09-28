package com.cndatacom.rbac.system.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.BaseJsonPrint;
import com.cndatacom.common.utils.DateUtil;
import com.cndatacom.common.utils.GetIpUtils;
import com.cndatacom.common.utils.MD5Service;
import com.cndatacom.common.utils.MiSenderUtils1;
import com.cndatacom.common.utils.MiSenderUtils2;
import com.cndatacom.common.utils.MiSenderUtils3;
import com.cndatacom.common.utils.PushUtil;
import com.cndatacom.common.utils.UploadFile;
import com.cndatacom.common.utils.WxUtil;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.alipay.config.AlipayConfig;
import com.cndatacom.rbac.alipay.sign.RSA;
import com.cndatacom.rbac.alipay.util.AlipayNotify;
import com.cndatacom.rbac.pojo.ExpressageBank;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.pojo.ExpressageCourierDl;
import com.cndatacom.rbac.pojo.ExpressageDeposit;
import com.cndatacom.rbac.pojo.ExpressageIdea;
import com.cndatacom.rbac.pojo.ExpressageIntegral;
import com.cndatacom.rbac.pojo.ExpressageMessage;
import com.cndatacom.rbac.pojo.ExpressageNote;
import com.cndatacom.rbac.pojo.ExpressageNoteTemp;
import com.cndatacom.rbac.pojo.ExpressageOrder;
import com.cndatacom.rbac.pojo.ExpressagePayLog;
import com.cndatacom.rbac.pojo.ExpressageRecord;
import com.cndatacom.rbac.pojo.ExpressageSend;
import com.cndatacom.rbac.pojo.ExpressageStage;
import com.cndatacom.rbac.pojo.ExpressageToken;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.pojo.NoteExpressage;
import com.cndatacom.rbac.pojo.PayLogExpressage;
import com.cndatacom.rbac.pojo.StageExpressage;
import com.cndatacom.rbac.system.service.ExpressageBankService;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;
import com.cndatacom.rbac.system.service.ExpressageCourierDlService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageDepositService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageIdeaService;
import com.cndatacom.rbac.system.service.ExpressageIntegralService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageNoteService;
import com.cndatacom.rbac.system.service.ExpressageNoteTempService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressagePayLogService;
import com.cndatacom.rbac.system.service.ExpressageRecordService;
import com.cndatacom.rbac.system.service.ExpressageSendService;
import com.cndatacom.rbac.system.service.ExpressageStageService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;

/**
 * 
 * 类名: ExpressageCourierAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 快递员action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-10-7
 */
@Controller
@Action("expressageCourier")
@Scope("prototype")
@Namespace("/rbac/sys")
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
    private ExpressageSendService expressageSendService;
	@Resource
    private ExpressageBankService expressageBankService;
	@Resource
    private ExpressagePayLogService expressagePayLogService;
	@Resource
    private ExpressageMessageService expressageMessageService;
	@Resource
    private ExpressageRecordService expressageRecordService;
	@Resource
    private ExpressageStageService expressageStageService;
	@Resource
    private ExpressageDepositService expressageDepositService;
	@Resource
	private ExpressageNoteService expressageNoteService;
	@Resource
	private ExpressageNoteTempService expressageNoteTempService;
	@Resource
	private ExpressageCompanyService expressageCompanyService;
	@Resource
	private ExpressageCourierDlService expressageCourierDlService;
	@Resource
    private ExpressageIntegralService expressageIntegralService;
	@Resource
    private ExpressageIdeaService expressageIdeaService;
	private ExpressageCourier expressageCourier;
	private File avatar;//上传文件
	private File img1;//上传文件
	private File img2;//上传文件
	private File img3;//上传文件
	
	Page<ExpressagePayLog> pagePayLog = new Page<ExpressagePayLog>();
	
	@Action(value = "/xiaozhu")
	public String xiaozhu(){
//		String phone = getRequest().getParameter("phone");
//		String code1 = (String)ExpressageUserAction.getCacheConfMap().get(phone);
//		
//		System.out.println(code1);
		
		String dataStr = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
		Object obj = expressagePayLogService.find("select count(*) from ExpressagePayLog where userId = ? and status = '1' and type in ('2','3') and" +
				" createDate like '%"+dataStr+"%' ","8aaf0d6f576aeb0a01576b0ef1a30027");
		
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法名: saveCourier</br>
	 * 详述: 快递员注册</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/saveCourier")
	public String saveCourier() throws Exception{
		String phone = getRequest().getParameter("phone");
		String courierName = getRequest().getParameter("courierName");
		String token = getRequest().getParameter("token");
		String password = getRequest().getParameter("password");
		String code = getRequest().getParameter("code");
		JSONObject obj = new JSONObject();
		try {
			//条件过滤
			if(StringUtils.isBlank(courierName)||StringUtils.isBlank(phone)||StringUtils.isBlank(token)||StringUtils.isBlank(password)||StringUtils.isBlank(code)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			String code1 = (String)ExpressageUserAction.getCacheConfMap().get(phone);
			if(!code.equals(code1)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码有误", null);
				writerMessageForJson(json);
				return null;
			}
			//注册成功，创建账号
			expressageCourier = new ExpressageCourier();
			expressageCourier.setPassword(password.toUpperCase());
			expressageCourier.setAvatar("/upload/courierAvatar/default.jpg");
			expressageCourier.setBankCard("0");
			expressageCourier.setCourierName(courierName);
			expressageCourier.setGender("1");
			expressageCourier.setIsPayp("0");
			expressageCourier.setIsWork("2");
			expressageCourier.setPhone(phone);
			expressageCourier.setStatus("1");
			expressageCourier.setRank("5");
			expressageCourier.setPhoneNumber(phone);
			expressageCourier.setBalance("0");
			expressageCourier.setIsAuth("3");
			expressageCourier.setClientNumber("0");
			expressageCourier.setClientPwd("0");
			ExpressageUserAction.getCacheConfMap().remove(phone);
			
			expressageCourierService.save(expressageCourier);
			//注册后登录
			ExpressageToken mt = expressageTokenService.findUnique(" from ExpressageToken where tokenName = ? and type = '2' ", token);
			if(mt!=null){
				expressageTokenService.delete(mt);
			}
			mt = new ExpressageToken();
			mt.setUserId(expressageCourier.getCourierId());
			mt.setCreateDate(new Date());
			mt.setTokenName(token);
			mt.setType("2");
			expressageTokenService.save(mt);
			
			
			//生成系统消息
			ExpressageMessage em = new ExpressageMessage();
			em.setContent("尊敬的业务员，您好！感谢您的注册成功，该平台为有偿服务，业务员需冲值才可进行抢单，业务员每抢单一次，需扣除一元服，为本平台服务费用。客户体验第三方支付时，业务员可在本平台做出提现申请费用。为有效防范非法风险，请提高风险防范意识，警惕广告陷阱，谨防上当受骗。祝您使用愉快！");
			em.setCourierId(expressageCourier.getCourierId());
			em.setCreateDate(new Date());
			em.setTitle("快递快滴");
			em.setStatus("4");
			expressageMessageService.save(em);
			
			
			obj.accumulate("courierId", expressageCourier.getCourierId());
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
			writerMessageForJson(json);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
	}
	
	
	
	
	
	public static String createCommonParam()
	{
		// 时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());

		// 签名
		String sig = DigestUtils.md5Hex("1744f9d3f3464f6c8190e0e187201e02b442973d639142a5af0f964672148c2c"+ timestamp);

		return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=JSON";
	}
	
	/**
	 * 
	 * 方法名: loginCourier</br>
	 * 详述: 快递员登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/loginCourier")
	public String loginCourier() throws Exception {

		//getRequest().setCharacterEncoding("gbk");
		String token = getRequest().getParameter("token");
		String phone = getRequest().getParameter("phone");
		String pwd = getRequest().getParameter("password");
		try {
			if(StringUtils.isBlank(phone)||StringUtils.isBlank(pwd)||StringUtils.isBlank(token)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			expressageCourier = expressageCourierService.findUnique(" from ExpressageCourier where password=? and phone = ?", pwd.toUpperCase(),phone);
			if(expressageCourier==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100009", "账号或密码有误", null);
				writerMessageForJson(json);
				return null;
			}else{
				if(StringUtils.isBlank(expressageCourier.getStatus())||expressageCourier.getStatus().equals("0")){
					JSONObject json = BaseJsonPrint.SynthesisJson("100002", "账号被封，请与管理员联系", null);
					writerMessageForJson(json);
					return null;
				}
				//登录成功，更新数据库中的token记录
				ExpressageToken expressageToken = expressageTokenService.findUniqueBy("userId", expressageCourier.getCourierId());
				if(expressageToken == null){
					expressageToken = new ExpressageToken();
				}else{
					if(!expressageToken.getTokenName().equals(token)){
						//新机器登录，推送下线通知
						MiSenderUtils1.sendMessage(expressageToken.getTokenName(),"100001",null,"100001");
						MiSenderUtils3.sendMessage(expressageToken.getTokenName(),null,null,"100001");
					}
				}
				expressageToken.setCreateDate(new Date());
				expressageToken.setTokenName(token);
				expressageToken.setType("2");
				expressageToken.setUserId(expressageCourier.getCourierId());
				expressageTokenService.save(expressageToken);
				
				JSONObject obj = new JSONObject();
				obj.accumulate("courierId", expressageCourier.getCourierId());
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
				writerMessageForJson(json);
				return null;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
		
	}
	
	/**
	 * 
	 * 方法名: getCourierInformation</br>
	 * 详述: 查询快递员资料</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-5</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getCourierInformation")
	public String getCourierInformation() throws Exception{
		String courierId = getRequest().getParameter("courierId");
		if(StringUtils.isBlank(courierId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		
		if(expressageCourier==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100001", "用户不存在", null);
			writerMessageForJson(json);
			return null;
		}
		
		if(expressageCourier.getStatus().equals("0")){
			ExpressageToken expressageToken = expressageTokenService.findUniqueBy("userId", courierId);
			if(expressageToken!=null){
				expressageTokenService.delete(expressageToken);
			}
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账户已经被禁，请联系管理员", null);
			writerMessageForJson(json);
			return null;
		}
		
		
		JSONObject obj = new JSONObject();
		obj.accumulate("avatar", GetIpUtils.SplicePath(getRequest(), expressageCourier.getAvatar()));
		obj.accumulate("courierId", expressageCourier.getCourierId());
		obj.accumulate("courierName", expressageCourier.getCourierName());
		obj.accumulate("phone", expressageCourier.getPhone());
		obj.accumulate("companyId", expressageCourier.getCompanyId());

		if(StringUtils.isNotBlank(expressageCourier.getCompanyId())){
			ExpressageCompany ec = expressageCompanyService.findUniqueBy("companyId", expressageCourier.getCompanyId());
			obj.accumulate("companyName", ec.getCompanyName());
		}else{
			obj.accumulate("companyName", "");
		}
		obj.accumulate("icode", expressageCourier.getIcode());
		obj.accumulate("img1", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg1()));
		obj.accumulate("img2", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg2()));
		obj.accumulate("img3", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg3()));
		obj.accumulate("isAuth", expressageCourier.getIsAuth());
		obj.accumulate("balance", expressageCourier.getBalance());
		obj.accumulate("rank", expressageCourier.getRank());
		obj.accumulate("area", expressageCourier.getArea());
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateCourierInformation</br>
	 * 详述: 更新快递员资料</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateCourierInformation")
	public String updateCourierInformation() throws Exception{
		String courierName = getRequest().getParameter("courierName");
		String companyId = getRequest().getParameter("companyId");
		String icode = getRequest().getParameter("icode");
		String token = getRequest().getParameter("token");
		String area = getRequest().getParameter("area");
		if(StringUtils.isBlank(token)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");	
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(StringUtils.isNotBlank(courierName)){
			expressageCourier.setCourierName(courierName);
		}
		if(StringUtils.isNotBlank(companyId)){
			expressageCourier.setCompanyId(companyId);
		}
		if(StringUtils.isNotBlank(icode)){
			expressageCourier.setIcode(icode);
		}
		if(StringUtils.isNotBlank(area)){
			expressageCourier.setArea(area);
		}
		
		UploadFile up = new UploadFile();
		// 上传文件路径
		String realpath = getRequest().getRealPath("/");
		// 如果图片不等于空
		if (null != avatar) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageCourier.getPhone()+".png";
			// 上传文件
			String pa = up.uploadExcelParserReport(avatar, imageFileName,
					realpath + "/upload/userAvatar");
			System.out.println(pa);
			//数据库存网络地址
			String  str="/upload/userAvatar/"+imageFileName; 
			System.out.println("str=="+str);
			expressageCourier.setAvatar(str);
		}
		if (null != img1) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageCourier.getPhone()+"1.png";
			// 上传文件
			String pa = up.uploadExcelParserReport(img1, imageFileName,
					realpath + "/upload/userAvatar");
			System.out.println(pa);
			//数据库存网络地址
			String  str="/upload/userAvatar/"+imageFileName; 
			System.out.println("str=="+str);
			expressageCourier.setImg1(str);
		}
		if (null != img2) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageCourier.getPhone()+"2.png";
			// 上传文件
			String pa = up.uploadExcelParserReport(img2, imageFileName,
					realpath + "/upload/userAvatar");
			System.out.println(pa);
			//数据库存网络地址
			String  str="/upload/userAvatar/"+imageFileName; 
			System.out.println("str=="+str);
			expressageCourier.setImg2(str);
		}
		if (null != img3) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageCourier.getPhone()+"3.png";
			// 上传文件
			String pa = up.uploadExcelParserReport(img3, imageFileName,
					realpath + "/upload/userAvatar");
			System.out.println(pa);
			//数据库存网络地址
			String  str="/upload/userAvatar/"+imageFileName; 
			System.out.println("str=="+str);
			expressageCourier.setImg3(str);
		}
		expressageCourier.setIsAuth("0");
		expressageCourierService.save(expressageCourier);
		JSONObject obj = new JSONObject();
		obj.accumulate("avatar", GetIpUtils.SplicePath(getRequest(), expressageCourier.getAvatar()));
		obj.accumulate("courierId", expressageCourier.getCourierId());
		obj.accumulate("courierName", expressageCourier.getCourierName());
		obj.accumulate("phone", expressageCourier.getPhone());
		obj.accumulate("companyId", expressageCourier.getCompanyId());
		
		if(StringUtils.isNotBlank(expressageCourier.getCompanyId())){
			ExpressageCompany ec = expressageCompanyService.findUniqueBy("companyId", expressageCourier.getCompanyId());
			obj.accumulate("companyName", ec.getCompanyName());
		}else{
			obj.accumulate("companyName", "");
		}
		obj.accumulate("icode", expressageCourier.getIcode());
		obj.accumulate("img1", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg1()));
		obj.accumulate("img2", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg2()));
		obj.accumulate("img3", GetIpUtils.SplicePath(getRequest(), expressageCourier.getImg3()));
		obj.accumulate("isAuth", expressageCourier.getIsAuth());
		obj.accumulate("area", expressageCourier.getArea());
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: getCompanys</br>
	 * 详述: 公司列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 */
	@Action(value = "/getCompanys")
	public String getCompanys() throws Exception{
		
		List<ExpressageCompany> esList = expressageCompanyService.getAll();
		
		JSONArray jsonArray = JSONArray.fromObject(esList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateWork</br>
	 * 详述: 快递员上下班切换</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateWork")
	public String updateWork() throws Exception{
		String token = getRequest().getParameter("token");
		String isWork = getRequest().getParameter("isWork");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(isWork)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(expressageCourier==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100012", "账号不存在", null);
			writerMessageForJson(json);
			return null;
		}
		expressageCourier.setIsWork(isWork);
		expressageCourierService.save(expressageCourier);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateSendById</br>
	 * 详述: 更新派件范围</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateSendById")
	public String updateSendById() throws Exception{
		String token = getRequest().getParameter("token");
//		String provincialId = getRequest().getParameter("provincialId");
//		String cityId = getRequest().getParameter("cityId");
		String address = getRequest().getParameter("address");
		String sendId = getRequest().getParameter("sendId");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(address)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		ExpressageSend expressageSend =expressageSendService.findUniqueBy("sendId", sendId);
		if(expressageSend==null){
			expressageSend = new ExpressageSend();
		}
		expressageSend.setAddress(address);
		//expressageSend.setCityId(cityId);
		expressageSend.setCourierId(courierId);
		//expressageSend.setProvincialId(provincialId);
		expressageSendService.save(expressageSend);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateBankById</br>
	 * 详述: 更新银行卡</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateBankById")
	public String updateBankById() throws Exception{
		String token = getRequest().getParameter("token");
		String codeNumber = getRequest().getParameter("codeNumber");
		String bankName = getRequest().getParameter("bankName");
		String codeId = getRequest().getParameter("codeId");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(codeNumber)||StringUtils.isBlank(bankName)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		ExpressageBank expressageBank = expressageBankService.findUniqueBy("codeId", codeId);
		if(expressageBank==null){
			expressageBank = new ExpressageBank();
		}
		expressageBank.setBankName(bankName);
		expressageBank.setCodeNumber(codeNumber);
		expressageBank.setCourierId(courierId);
		expressageBankService.save(expressageBank);
		
		List<ExpressageBank> ebList = expressageBankService.findBy("courierId", courierId);
		
		ExpressageCourier ec = expressageCourierService.findUniqueBy("courierId", courierId);
		if(ec!=null){
			ec.setBankCard(ebList.size()+"");
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateLonLatByCourier</br>
	 * 详述: 更新快递员经纬度</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateLonLatByCourier")
	public String updateLonLatByCourier() throws Exception{
		String courierId = getRequest().getParameter("courierId");
		String latitude = getRequest().getParameter("latitude");
		String longitude = getRequest().getParameter("longitude");
		if(StringUtils.isBlank(courierId)||StringUtils.isBlank(latitude)||StringUtils.isBlank(longitude)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(expressageCourier==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100012", "账号不存在", null);
			writerMessageForJson(json);
			return null;
		}
		expressageCourier.setLatitude(latitude);
		expressageCourier.setLongitude(longitude);
		expressageCourier.setLlDate(new Date());
		expressageCourierService.save(expressageCourier);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: withdrawDeposit</br>
	 * 详述: 提现</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/withdrawDeposit")
	public String withdrawDeposit() throws Exception{
		String token = getRequest().getParameter("token");
		String bankName = getRequest().getParameter("bankName");
		String bankCode = getRequest().getParameter("bankCode");
		String realName = getRequest().getParameter("realName");
		String phone = getRequest().getParameter("phone");
		String sum = getRequest().getParameter("sum");
		String payPassword = getRequest().getParameter("payPassword");
		if(StringUtils.isBlank(sum)||StringUtils.isBlank(token)||StringUtils.isBlank(bankName)||StringUtils.isBlank(bankCode)||StringUtils.isBlank(realName)||StringUtils.isBlank(phone)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		try {
			//验证是否登录
			isLogin(token,"2");
			String courierId = getUserIdByToken(token,"2");
			
			expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
			if(expressageCourier==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100001", "账号不存在", null);
				writerMessageForJson(json);
				return null;
			}
//			if(!expressageCourier.getPayPassword().equals(payPassword.toUpperCase())){
//				JSONObject json = BaseJsonPrint.SynthesisJson("100025", "提现密码错误", null);
//				writerMessageForJson(json);
//				return null;
//			}
			if(Double.parseDouble(expressageCourier.getBalance())<Double.parseDouble(sum)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100012", "余额不足", null);
				writerMessageForJson(json);
				return null;
			}
			
			ExpressagePayLog epl = new ExpressagePayLog();
			epl.setBankCode(bankCode);
			epl.setBankName(bankName);
			epl.setCourierId(courierId);
			epl.setCreateDate(new Date());
			epl.setMoneyNum("-"+sum);
			epl.setStatus("2");
			epl.setType("4");
			epl.setRemark("提现");
			
			expressagePayLogService.save(epl);
			
			expressageCourier.setBalance(String.valueOf(Double.parseDouble(expressageCourier.getBalance())-Double.parseDouble(sum)));
			expressageCourierService.save(expressageCourier);
			
			//加系统消息
			ExpressageMessage expressageMessage =new ExpressageMessage();
			expressageMessage.setContent("您的提现已申请，请等待审核！");
			expressageMessage.setCourierId(courierId);
			expressageMessage.setCreateDate(new Date());
			expressageMessage.setStatus("4");
			expressageMessage.setTitle("提现");
			expressageMessageService.save(expressageMessage);
			
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法名: addInform</br>
	 * 详述: 举报</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/addInform")
	public String addInform() throws Exception{
		String token = getRequest().getParameter("token");
		String content = getRequest().getParameter("content");
		String orderNo = getRequest().getParameter("orderNo");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(content)||StringUtils.isBlank(orderNo)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(expressageCourier==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100001", "账号不存在", null);
			writerMessageForJson(json);
			return null;
		}
		
		
		ExpressageIdea ei = new ExpressageIdea();
		ei.setContent(content);
		ei.setCreateDate(new Date());
		ei.setStatus("0");
		ei.setType("3");
		ei.setOrderNo(orderNo);
		ei.setUserId(courierId);
		expressageIdeaService.save(ei);
		
		ExpressageOrder eo = expressageOrderService.findUnique(" from ExpressageOrder where type = '1' and status = '4' and orderNo = ? ",orderNo);
		if(eo!=null){
			ExpressageUser eu = expressageUserService.findUniqueBy("userId", eo.getUserId());
			if(eu!=null){
				if(Integer.valueOf(eu.getReputation())<1){
					eu.setClientPwd(String.valueOf(Integer.valueOf(eu.getClientPwd())+1));
				}else{
					if(Integer.valueOf(eu.getClientPwd())>1){
						eu.setReputation(String.valueOf(Integer.valueOf(eu.getReputation())-1));
					}else{
						eu.setClientPwd(String.valueOf(Integer.valueOf(eu.getClientPwd())+1));
					}
				}
				expressageUserService.save(eu);
			}
			eo.setStatus("5");
			expressageOrderService.save(eo);
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: addRecharge</br>
	 * 详述: 充值</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/addRecharge")
	public String addRecharge() throws Exception{
		String token = getRequest().getParameter("token");
		String orderPrice = getRequest().getParameter("orderPrice");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(type)||StringUtils.isBlank(orderPrice)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		if(expressageCourier==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100001", "账号不存在", null);
			writerMessageForJson(json);
			return null;
		}
		String ip = getRequest().getRemoteAddr();
		if(type.equals("1")){//微信充值
			
//			expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(orderPrice)));
			//生成支付日志
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setRemark("微信充值");
			expressagePayLog.setStatus("2");
			expressagePayLog.setType("6");
			expressagePayLog.setCourierId(courierId);
			expressagePayLogService.save(expressagePayLog);
			
			SortedMap<String, Object> sp = new TreeMap<String, Object>();
			sp.put("appid", "wx88b863d71fc3db44");
			sp.put("mch_id", "1395655802");
			sp.put("nonce_str", MD5Service.encryptString(String.valueOf(new Date().getTime()/1000).toString()));
			sp.put("body", "快递员充值");
			sp.put("out_trade_no", expressagePayLog.getPayId());
			sp.put("total_fee",(int)(Float.parseFloat(orderPrice)*100));
			//			sp.put("total_fee",1);
			sp.put("spbill_create_ip", ip);
			sp.put("notify_url", "http://120.76.194.20/expressage_api/rbac/sys/expressageCourier/weixinInform");
			sp.put("trade_type", "APP");
			sp.put("sign", WxUtil.createSign(sp,"FHiYjo585s5D55df695w9fdfSF6DW696"));
			
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
				sp1.put("appid", "wx88b863d71fc3db44");
				sp1.put("partnerid", "1395655802");
				sp1.put("noncestr", sp.get("nonce_str"));
				sp1.put("prepayid", prepay_id);
				sp1.put("timestamp", String.valueOf(new Date().getTime()/1000).toString());
				sp1.put("package", "Sign=WXPay");
				sp1.put("sign", WxUtil.createSign(sp1,"FHiYjo585s5D55df695w9fdfSF6DW696"));
				
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
			
		}else if(type.equals("2")){//支付宝充值
			
			//生成支付日志
			ExpressagePayLog expressagePayLog = new ExpressagePayLog();
			expressagePayLog.setCreateDate(new Date());
			expressagePayLog.setMoneyNum("+"+orderPrice);
			expressagePayLog.setRemark("支付宝充值");
			expressagePayLog.setStatus("2");
			expressagePayLog.setType("8");
			expressagePayLog.setCourierId(courierId);
			expressagePayLogService.save(expressagePayLog);
			
			String str = getOrderInfo(AlipayConfig.partner, "13826373331", expressagePayLog.getPayId(), "快递费充值", "快递费充值："+orderPrice, orderPrice);
			String sing = RSA.sign(str, AlipayConfig.private_key, AlipayConfig.input_charset);
			
			JSONObject obj = new JSONObject();
			obj.accumulate("alipayStr", str + "&sign=\"" + URLEncoder.encode(sing, "UTF-8") + "\"&sign_type="  + "\""+ AlipayConfig.sign_type + "\"");
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
			writerMessageForJson(json);
			return null;
			
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
	 * 方法名: weixinInform</br>
	 * 详述: 微信充值异步通知回调</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-9</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/weixinInform")
	public String weixinInform() throws Exception{
		System.out.println("微信异步通知回调开始================================");
		Document document = WxUtil.getDocumentBuilder().parse(getRequest().getInputStream());
		String return_code = document.getElementsByTagName("return_code").item(0).getFirstChild().getNodeValue();
		System.out.println("=======1="+return_code);
		
		if(return_code.equals("SUCCESS")){
			String result_code = document.getElementsByTagName("result_code").item(0).getFirstChild().getNodeValue();
			if(result_code.equals("SUCCESS")){
				System.out.println("微信异步通知回调业务处理开始=================================");
				String out_trade_no = document.getElementsByTagName("out_trade_no").item(0).getFirstChild().getNodeValue();
				System.out.println("out_trade_no========================="+out_trade_no);
				ExpressagePayLog expressagePayLog =expressagePayLogService.findUniqueBy("payId", out_trade_no);
				if(expressagePayLog!=null&&expressagePayLog.getStatus().equals("2")){
					//签名验证
					
					String transaction_id = document.getElementsByTagName("transaction_id").item(0).getFirstChild().getNodeValue();
					String total_fee = document.getElementsByTagName("total_fee").item(0).getFirstChild().getNodeValue();
					expressagePayLog.setStatus("1");
					expressagePayLog.setOrderId(transaction_id);
					expressagePayLogService.save(expressagePayLog);
					
					expressageCourier = expressageCourierService.findUniqueBy("courierId", expressagePayLog.getCourierId());
					if(expressageCourier!=null){
						DecimalFormat formater = new DecimalFormat();
				        formater.setMaximumFractionDigits(2);
				        formater.setGroupingSize(0);
				        formater.setRoundingMode(RoundingMode.FLOOR);
				        
						expressageCourier.setBalance(formater.format(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(total_fee)/100));
						expressageCourierService.save(expressageCourier);
					}
					
					//生成系统消息
					ExpressageMessage em = new ExpressageMessage();
					em.setContent("您好，您成功充值"+Double.valueOf(total_fee)/100+"元！");
					em.setCourierId(expressagePayLog.getCourierId());
					em.setCreateDate(new Date());
					em.setTitle("充值提醒");
					em.setStatus("4");
					expressageMessageService.save(em);
					
					System.out.println("微信异步通知回调业务处理完成================================");
				}
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
		System.out.println("微信异步通知回调结束=======================");
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
	@Action(value = "/alipayInform")
	public void alipayInform() throws Exception {
		System.out.println("支付宝充值回调开始==================================================");
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
					expressagePayLog.setStatus("1");
					expressagePayLog.setOrderId(trade_no);
					expressagePayLogService.save(expressagePayLog);
					
					expressageCourier = expressageCourierService.findUniqueBy("courierId", expressagePayLog.getCourierId());
					if(expressageCourier!=null){
						System.out.println("555555555555555555555555555555555555555");
						expressageCourier.setBalance(String.valueOf(Double.valueOf(expressageCourier.getBalance())+Double.valueOf(total_fee)));
						System.out.println("666666666666666666666666666666666666666666");
						expressageCourierService.save(expressageCourier);
						System.out.println("777777777777777777777777777777777777777777");
					}
					System.out.println("44444444444444444444444444444444444444444444");
					//生成系统消息
					ExpressageMessage em = new ExpressageMessage();
					em.setContent("您好，您成功充值"+total_fee+"元！");
					em.setCourierId(expressagePayLog.getCourierId());
					em.setCreateDate(new Date());
					em.setTitle("充值提醒");
					em.setStatus("4");
					expressageMessageService.save(em);
					
					System.out.println("微信异步通知回调业务处理完成================================");
				}
				
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
	 * 方法名: getAlipay</br>
	 * 详述: 支付宝授权回调地址</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getAlipay")
	public String getAlipay() throws Exception{
		
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
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
	private String getOrderInfo(String partner, String seller_id, String getOutTradeNo, String subject, String body, String price) {

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
		orderInfo += "&notify_url=" + "\"" + "http://120.76.194.20/expressage_api/rbac/sys/expressageCourier/alipayInform" + "\"";

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
	 * 方法名: getPayLogByCourier</br>
	 * 详述: 快递员收支记录列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getPayLogByCourier")
	public String getPayLogByCourier() throws Exception{
		String token = getRequest().getParameter("token");
		String pageN = getRequest().getParameter("pageNo");
		String pageSize = getRequest().getParameter("pageSize");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(pageN)||StringUtils.isBlank(pageSize)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		
		pagePayLog.setStart(Integer.parseInt(pageN)*Integer.parseInt(pageSize));
		pagePayLog.setLimit(Integer.parseInt(pageSize));
		
		//List<ExpressagePayLog> eplLis=expressagePayLogService.find(" from ExpressagePayLog where  courierId = ? and status <> '0' ", courierId);
		expressagePayLogService.findPage(pagePayLog, "  from ExpressagePayLog where status = '1' and courierId = '"+courierId+"' order by createDate desc ");
		List<ExpressagePayLog> eplLis = pagePayLog.getResult();
		List<PayLogExpressage> pleList = new ArrayList<PayLogExpressage>();
		for (int i = 0; i < eplLis.size(); i++) {
			ExpressagePayLog epl = eplLis.get(i);
			PayLogExpressage ple = new PayLogExpressage();
			ple.setBankCode(epl.getBankCode());
			ple.setBankName(epl.getBankName());
			ple.setCourierId(epl.getCourierId());
			ple.setCreateDate(DateUtil.parseDateToStr(epl.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			ple.setMoneyNum(epl.getMoneyNum());
			ple.setOrderId(epl.getOrderId());
			ple.setPayId(epl.getPayId());
			ple.setRemark(epl.getRemark());
			ple.setStatus(epl.getStatus());
			ple.setType(epl.getType());
			pleList.add(ple);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(pleList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		
		return null;
	}
	
	/**
	 * 
	 * 方法名: recordByToken</br>
	 * 详述: 录音</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/recordByToken")
	public String recordByToken() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		ExpressageOrder expressageOrder = expressageOrderService.findUniqueBy("orderId", orderId);
		if(expressageOrder == null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100018", "订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		
		ExpressageRecord er = new ExpressageRecord();
		er.setCourierId(courierId);
		er.setCreateDate(new Date());
		er.setOrderId(orderId);
		er.setUserId(expressageOrder.getUserId());
		
		UploadFile up = new UploadFile();
		// 上传文件路径
		String realpath = getRequest().getRealPath("/");
		// 如果图片不等于空
		if (null != avatar) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageOrder.getjPhone()+".3gp";
			// 上传文件
			String pa = up.uploadExcelParserReport(avatar, imageFileName,
					realpath + "/upload/recird");
			//数据库存网络地址
			String  str="/upload/recird/"+imageFileName; 
			System.out.println("str=="+str);
			er.setRecordUrl(str);
		}
		expressageRecordService.save(er);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}



	/**
	 * 方法名：getMessagesRecorder</br>
	 * 详述: 根据用户ID和起始日期，结束日期 获取短信记录</br>
	 * 开发人员：陈协</br>
	 */
	@Action(value = "/getMessagesRecorder")
	public String getMessagesRecorder() throws Exception{

		String courierId = getRequest().getParameter("courierId");
		String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");

		if(StringUtils.isBlank(courierId)||StringUtils.isBlank(startDate)||StringUtils.isBlank(endDate)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		Date s = DateUtil.strToDate(startDate);
		Date e = DateUtil.strToDate(endDate);
		
		if(s.after(e)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "起始日期不能大于结束日期", null);
			writerMessageForJson(json);
			return null;
		}
		
		startDate+=" 00:00:00:";
		endDate+=" 23:59:59";
		
		List<ExpressageNote> list = expressageNoteService.getMessagesRecorder(courierId, startDate, endDate);
		
		List<NoteExpressage> pklist = new ArrayList<NoteExpressage>();
		
		for(int i=0;i<list.size();i++){
			ExpressageNote en = list.get(i);
			NoteExpressage ne = new NoteExpressage();
			ne.setNoteId(en.getNoteId());
			ne.setOrderNumber(en.getOrderNumber());
			ne.setPhone(en.getPhone());
			ne.setNoteContent(en.getNoteContent());
			ne.setNoteTime(DateUtil.parseDateToStr(en.getNoteTime(), "yyyy-MM-dd HH:mm:ss"));
			pklist.add(ne);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(pklist);
		
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	

	/**
	 * 
	 * 方法名: getSmsTemp</br>
	 * 详述: 获取短信模版</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getSmsTemp")
	public String getSmsTemp() throws Exception{
		List<ExpressageNoteTemp> expressageNoteTemps = expressageNoteTempService.getAll();
		JSONArray jsonArray = JSONArray.fromObject(expressageNoteTemps);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: smsByUserIds</br>
	 * 详述:群发短信 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/smsByUserIds")
	public String smsByUserIds() throws Exception{
		String token = getRequest().getParameter("token");
		String content = getRequest().getParameter("content");
		String phones = getRequest().getParameter("phones");
		String orderNumber = getRequest().getParameter("orderNumber");
		String courierId = getUserIdByToken(token,"2");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(content)||StringUtils.isBlank(phones)
				||StringUtils.isBlank(courierId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String [] phone = phones.split(",");
		 
		isLogin(token,"2");
		String strContent=content;
		if(StringUtils.isBlank(orderNumber)){
			orderNumber="";
		}else{
			strContent = content+"快递单号:"+orderNumber;
		}
		 for(int i=0;i<phone.length;i++){
				
			 	
				String send_content=URLEncoder.encode(strContent.replaceAll("<br/>", " "), "GBK");//发送内容
				String postStr="CorpID=GZLKJ0004604&Pwd=slyd@668&Mobile="+phone[i]+"&Content="+send_content+"&Cell=&SendTime=";
				String postUrl="http://sdk.zhongguowuxian.com:98/ws/BatchSend.aspx";
				 
				String str = PushUtil.invokingSMS(postStr, postUrl);
				String ss = str.replaceAll("[\\s*]+", "");
				
				if(!ss.equals("1")&&!ss.equals("0")){//短信下发失败
					JSONObject json = BaseJsonPrint.SynthesisJson("100010", "网络异常，短信下发失败，请稍后再试", null);
					writerMessageForJson(json);
				}else{
					if(StringUtils.isBlank(orderNumber)){
						orderNumber="";
					}
					ExpressageNote expressageNote = new ExpressageNote(phone[i], content, new Date(), 1, courierId,orderNumber);
					expressageNoteService.save(expressageNote);
					JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
					writerMessageForJson(json);
				}
		 }

		return null;
		
	}
	
	
	/**
	 * 
	 * 方法名: deleteAddressByids</br>
	 * 详述: 批量删除派送范围</br>
	 * 开发人员：莫志明</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/deleteAddressByid")
	public String deleteAddressByid() throws Exception{
		String token = getRequest().getParameter("token");
		String sendIds = getRequest().getParameter("sendIds");
		if(StringUtils.isBlank(sendIds)||StringUtils.isBlank(token)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空",null);
			writerMessageForJson(json);
			return null;	
		}
		isLogin(token, "2");
		String[] str = sendIds.split(",");
		for(int i = 0;i < str.length;i++){
			expressageSendService.delete(str[i]);
		}		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json); 
		return null;
		
	}
	
	/**
	 * 
	 * 方法名: deleteBankByid</br>
	 * 详述: 批量删除快递员银行卡</br>
	 * 开发人员：莫志明</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/deleteBankByid")
	public String deleteBankByid() throws Exception{
		
		String token = getRequest().getParameter("token");
		String bankIds = getRequest().getParameter("bankIds");
		if(StringUtils.isBlank(bankIds)||StringUtils.isBlank(token)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空",null);
			writerMessageForJson(json);
			return null;	
		}
		isLogin(token, "2");
		String[] str = bankIds.split(",");
		for(int i = 0; i<str.length;i++){
			expressageBankService.delete(str[i]);
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json); 
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法名: updatePayPassword</br>
	 * 详述: 修改支付密码</br>
	 * 开发人员：莫志明</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updatePayPassword")
	public String updatePayPassword() throws Exception{
		String token = getRequest().getParameter("token");
		String password = getRequest().getParameter("password");
		String newPassword = getRequest().getParameter("newPassword");
		//条件过滤
		if(StringUtils.isBlank(token)||StringUtils.isBlank(password)||StringUtils.isBlank(newPassword)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		System.out.println("password=========="+password);
		System.out.println("newPassword=========="+newPassword);
		System.out.println("expressageCourier.getPassword()=========="+expressageCourier.getPayPassword());
		if(!expressageCourier.getPayPassword().equals(password.toUpperCase())){
			JSONObject json = BaseJsonPrint.SynthesisJson("100016", "原始密码有误", null);
			writerMessageForJson(json);
			return null;
		}
		expressageCourier.setPayPassword(newPassword.toUpperCase());
		expressageCourier.setIsPayp("1");
		expressageCourierService.save(expressageCourier);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
		
	}
	
	/**
	 * 
	 * 方法名: deletePayPassword</br>
	 * 详述: 重置支付密码</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
//	@Action(value = "/deletePayPassword")
//	public String deletePayPassword() throws Exception{
//		String token = getRequest().getParameter("token");
//		String code = getRequest().getParameter("code");
//		
//		//条件过滤
//		if(StringUtils.isBlank(token)||StringUtils.isBlank(code)){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		isLogin(token,"2");
//		String courierId = getUserIdByToken(token,"2");
//		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
//		
//		String code1 = (String)cacheConfMap.get(expressageCourier.getPhone());
//		if(!code.equals(code1)){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码有误", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		cacheConfMap.remove(expressageCourier.getPhone());
//		
//		expressageCourier.setIsPayp("0");
//		expressageCourier.setPayPassword(null);
//		expressageCourierService.save(expressageCourier);
//		
//		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
//		writerMessageForJson(json);
//		
//		return null;
//	}
	 
	
	
	/**
	 * 
	 * 方法名: setPayPassword</br>
	 * 详述: 设置支付密码</br>
	 * 开发人员：莫志明</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	
	@Action(value = "/setPayPassword")
	public String setPayPassword() throws Exception{
		String token = getRequest().getParameter("token");
		String password1 = getRequest().getParameter("password1");
		String password2 = getRequest().getParameter("password2");
		//条件过滤
		if(StringUtils.isBlank(token)||StringUtils.isBlank(password1)||StringUtils.isBlank(password2)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		
		if(!password1.equals(password2)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100016", "两次密码不一致", null);
			writerMessageForJson(json);
			return null;
		}
		expressageCourier.setPayPassword(password1.toUpperCase());
		expressageCourier.setIsPayp("1");
		expressageCourierService.save(expressageCourier);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
		
	}
	
//	/**
//	 * 
//	 * 方法名: getOrdersByToken</br>
//	 * 详述: 揽收管理</br>
//	 * 开发人员：朱恋青</br>
//	 * 创建时间：2015-8-20</br>
//	 * @return
//	 * @throws Exception
//	 */
//	@Action(value = "/getOrdersByToken")
//	public String getOrdersByToken() throws Exception{
//		String token = getRequest().getParameter("token");
//		String startTime = getRequest().getParameter("startTime");
//		String endTime = getRequest().getParameter("endTime");
//		//条件过滤
//		if(StringUtils.isBlank(token)){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		//验证是否登录
//		isLogin(token,"2");
//		String courierId = getUserIdByToken(token,"2");
//		//List<ExpressageOrder>
//		List<ExpressageOrder> eoList = expressageOrderService.getOrdersByToken(courierId, startTime, endTime);
//		
//		JSONArray jsonArray = JSONArray.fromObject(eoList);
//		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
//		writerMessageForJson(json);
//		return null;
//	}
	
	/**
	 * 
	 * 方法名: getStagesByToken</br>
	 * 详述: 驿站列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 */
	@Action(value = "/getStagesByToken")
	public String getStagesByToken() throws Exception{
		String token = getRequest().getParameter("token");
		//条件过滤
		if(StringUtils.isBlank(token)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		
		List<StageExpressage> esList = expressageStageService.getCourierIdsByjw(expressageCourier.getLatitude(), expressageCourier.getLongitude());
		
		JSONArray jsonArray = JSONArray.fromObject(esList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getDepositOrder</br>
	 * 详述: 存放驿站</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getDepositOrder")
	public String getDepositOrder() throws Exception{
		String token = getRequest().getParameter("token");
		String orderNumber = getRequest().getParameter("orderNumber");
		String phone = getRequest().getParameter("phone");
		String stageId = getRequest().getParameter("stageId");
		String yzName = getRequest().getParameter("yzName");
		
		//条件过滤
		if(StringUtils.isBlank(token)||StringUtils.isBlank(orderNumber)||StringUtils.isBlank(phone)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		if(StringUtils.isBlank(stageId)){
			if(StringUtils.isBlank(yzName)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		//验证是否登录
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
		
		ExpressageDeposit expressageDeposit = expressageDepositService.findUniqueBy("orderNumber", orderNumber);
		if(expressageDeposit!=null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100026", "不能重复存放", null);
			writerMessageForJson(json);
			return null;
		}
		expressageDeposit = new ExpressageDeposit();
		expressageDeposit.setCourierId(courierId);
		expressageDeposit.setCreateDate(new Date());
		expressageDeposit.setOrderNumber(orderNumber);
		expressageDeposit.setPhone(phone);
		expressageDeposit.setStageId(stageId);
		expressageDeposit.setYzName(yzName);
		
		
		String content = null;
		ExpressageCompany ec = expressageCompanyService.findUniqueBy("companyId", expressageCourier.getCompanyId());
		if(StringUtils.isBlank(stageId)){
			if(ec!=null){
				content = "亲，"+ec.getCompanyName()+"(电话:"+expressageCourier.getPhone()+")已根据您的吩咐将快件（运单号："+orderNumber+"）放在"+yzName+"。记得要去哦。";
			}else{
				content = "亲，快递小哥(电话:"+expressageCourier.getPhone()+")已根据您的吩咐将快件（运单号："+orderNumber+"）放在"+yzName+"。记得要去哦。";
			}
			
		}else{
			ExpressageStage expressageStage = expressageStageService.findUniqueBy("stageId", stageId);
			if(expressageStage==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100027", "驿站不存在", null);
				writerMessageForJson(json);
				return null;
			}
			if(ec!=null){
				content = "亲，"+ec.getCompanyName()+"(电话:"+expressageCourier.getPhone()+")已经将您的快递(运单号："+orderNumber+")放在马上收驿站："+expressageStage.getStageName()+"("+expressageStage.getStageAddress()+")，记得来取件哦！代收费：1元/普通件，1.5元/大件。";
			}else{
				content = "亲，快递小哥(电话:"+expressageCourier.getPhone()+")已经将您的快递(运单号："+orderNumber+")放在马上收驿站："+expressageStage.getStageName()+"("+expressageStage.getStageAddress()+")，记得来取件哦！代收费：1元/普通件，1.5元/大件。";
			}
		}
		
		
		
		String send_content=URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");//发送内容
		String postStr="CorpID=GZLKJ0004604&Pwd=slyd@668&Mobile="+phone+"&Content="+send_content+"&Cell=&SendTime=";
		String postUrl="http://sdk.zhongguowuxian.com:98/ws/BatchSend.aspx";
		 
		String str = PushUtil.invokingSMS(postStr, postUrl);
		String ss = str.replaceAll("[\\s*]+", "");
		if(!ss.equals("1")&&!ss.equals("0")){//短信下发失败
			JSONObject json = BaseJsonPrint.SynthesisJson("100010", "网络异常，短信下发失败，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}else{
			expressageDepositService.save(expressageDeposit);
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
		}
	}
	
//	/**
//	 * 
//	 * 方法名: getDeposits</br>
//	 * 详述:存放驿站列表 </br>
//	 * 开发人员：朱恋青</br>
//	 * 创建时间：2015-8-24</br>
//	 * @return
//	 * @throws Exception
//	 */
//	@Action(value = "/getDeposits")
//	public String getDeposits() throws Exception{
//		String token = getRequest().getParameter("token");
//		String stageId = getRequest().getParameter("stageId");
//		//条件过滤
//		if(StringUtils.isBlank(token)||StringUtils.isBlank(stageId)){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		//验证是否登录
//		isLogin(token,"2");
//		String courierId = getUserIdByToken(token,"2");
//		
//		
//		
//		return null;
//	}
	
	@Action(value = "/callAuth")
	public String callAuth() throws Exception{
		
		System.out.println("1向轻云码回写信息开始========callAuth17210564");
		
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)getRequest().getInputStream()));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        try {
			while((line = br.readLine())!=null){  
			    sb.append(line);  
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			 br.close();
		}
        
        System.out.println("sb1==="+sb);
        JSONObject objJson =  JSONObject.fromObject(sb.toString());
        System.out.println("sssss=="+objJson);
        
        System.out.println("fromSerNum1==="+objJson.get("called"));
		System.out.println("toSerNum1==="+objJson.get("toSerNum"));
		
        String toSerNum = objJson.get("toSerNum").toString();
        String fromSerNum = objJson.get("called").toString();
        //String fromSerNum = (String)objJson.get("fromSerNum");
        System.out.println("fromSerNum==="+fromSerNum);
		System.out.println("toSerNum==="+toSerNum);
        
       
		
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		obj.accumulate("respCode", "00000");
		obj.accumulate("fromSerNum", fromSerNum);
		obj.accumulate("toSerNum", toSerNum);
		obj.accumulate("allowedCallTime", "300");
		
		getResponse().setHeader("ContentType", "application/json;charset=utf-8");
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);
		try {
		writer = getResponse().getWriter();
		writer.flush();

		writer.print(obj);
		//   writer.print(JSONObject.fromObject(signParams).toString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("1向轻云码回写信息结束========callAuth1");
		return null;
	}
	
	@Action(value = "/callEstablish")
	public String callEstablish() throws Exception{
		System.out.println("向轻云码回写信息开始========callEstablish");
		String accountId = getRequest().getParameter("accountId");
		String appId = getRequest().getParameter("appId");
		String caller = getRequest().getParameter("caller");
		String called = getRequest().getParameter("called");
		String fromSerNum = getRequest().getParameter("fromSerNum");
		String toSerNum = getRequest().getParameter("toSerNum");
		String callId = getRequest().getParameter("callId");
		String timestamp = getRequest().getParameter("timestamp");
		String sig = getRequest().getParameter("sig");
		
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		obj.accumulate("respCode", "00000");
		
		getResponse().setHeader("ContentType", "application/json;charset=utf-8");
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);
		try {
		writer = getResponse().getWriter();
		writer.flush();

		writer.print(obj);
		//   writer.print(JSONObject.fromObject(signParams).toString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("向轻云码回写信息结束========callEstablish1");
		return null;
	}
	
	@Action(value = "/hangup")
	public String hangup() throws Exception{
		System.out.println("向轻云码回写信息开始========hangup1");
//		String accountId = getRequest().getParameter("accountId");
//		String appId = getRequest().getParameter("appId");
//		String clientNumber = getRequest().getParameter("clientNumber");
//		String callType = getRequest().getParameter("callType");
//		String caller = getRequest().getParameter("caller");
//		String called = getRequest().getParameter("called");
//		String fromSerNum = getRequest().getParameter("fromSerNum");
//		String toSerNum = getRequest().getParameter("toSerNum");
//		String callId = getRequest().getParameter("callId");
//		String timestamp = getRequest().getParameter("timestamp");
//		String sig = getRequest().getParameter("sig");
		
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		obj.accumulate("respCode", "00000");
		
		getResponse().setHeader("ContentType", "application/json;charset=utf-8");
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);
		try {
		writer = getResponse().getWriter();
		writer.flush();

		writer.print(obj);
		//   writer.print(JSONObject.fromObject(signParams).toString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		System.out.println("向轻云码回写信息结束========hangup");
		return null;
	}
	
	/**
	 * 
	 * 方法名: getOrdersNum</br>
	 * 详述:抢单次数 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getOrdersNum")
	public String getOrdersNum() throws Exception{
		String token = getRequest().getParameter("token");
		if(StringUtils.isBlank(token)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		Object obj = "0";
		//登录验证
		isLogin(token,"2");
		String courierId = getUserIdByToken(token,"2");
		obj = expressageMessageService.find("select count(*) from ExpressageOrder where courierId = ? and type = '1'  ",courierId);
		
		JSONObject obj1 = new JSONObject();
		obj1.accumulate("messageNum", String.valueOf(obj).replace("[", "").replace("]", ""));
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj1);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:快递员多条件查询 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-12-9</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/findByCourierDl")
	public String findByCourierDl() throws Exception{
		String pageSize = getRequest().getParameter("pageSize");
		String pageNum = getRequest().getParameter("pageNum");
		String send = getRequest().getParameter("send");
		String provinceName = getRequest().getParameter("provinceName");
		String cityName = getRequest().getParameter("cityName");
		String districtsName = getRequest().getParameter("districtsName");
		String company = getRequest().getParameter("company");
		StringBuffer sb = new StringBuffer(" from ExpressageCourierDl where 1=1 ");
		if(StringUtils.isNotBlank(provinceName)){
			sb.append(" and provinceName = '"+provinceName.replace("省", "").replace("市", "")+"' ");
		}
		if(StringUtils.isNotBlank(cityName)){
			sb.append(" and cityName = '"+cityName.replace("市", "")+"' ");
		}
		if(StringUtils.isNotBlank(districtsName)){
			sb.append(" and districtsName = '"+districtsName.replace("市", "").replace("区", "").replace("县", "").replace("/", "")+"' ");
		}
		if(StringUtils.isNotBlank(company)){
			sb.append(" and company like '%"+company+"%' ");
		}
		if(StringUtils.isNotBlank(send)){
			sb.append(" and send like '%"+send+"%' ");
		}
		System.out.println("sb=========="+sb.toString());
		//List<ExpressageCourierDl> ecdList = expressageCourierDlService.find(sb.toString());
		List<ExpressageCourierDl> ecdList = expressageCourierDlService.getEPList(sb.toString(), pageNum, pageSize);
		
		JSONArray jsonArray = JSONArray.fromObject(ecdList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: isLogin</br>
	 * 详述: 验证登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-7</br>
	 * @param token
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public void isLogin(String token,String type) throws Exception{
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
	 * 创建时间：2016-10-7</br>
	 * @param token
	 * @param type
	 * @return
	 */
	public String getUserIdByToken(String token,String type){
		ExpressageToken expressageToken = expressageTokenService.findUnique(" from ExpressageToken where type = ? and tokenName = ?", type,token);
		String str = expressageToken.getUserId();
		return str;
	}
	
	public static void main(String[] args) throws Exception{
		//MiSenderUtils.sendMessage("d//igwEhgBGCI2TG6lWqlFAVzn8YQrCI9pe/edtBOgJkX5RG/tCqBpWpgmOaWpAGN/GlkV+sEsHbfM43n9KWtjWqz171RZyp5bVP1eMvbGw=",null,"你好");
		//MiSenderUtils1.sendMessage("+yhMO7w5xp/iIvLj+OGBXvIQO8jDqjgNkz3rAGT7B5Q=","你好111","标题","100001");
		MiSenderUtils2.sendMessage("jmr8xMfyJ4i7hfWeWzbmGBrZu0GY7lR/QZaTj9sf7Ak=","12123","45216","100001");
		MiSenderUtils3.sendMessage("jmr8xMfyJ4i7hfWeWzbmGBrZu0GY7lR/QZaTj9sf7Ak=","12312","45126","100001");
		
		
		
		
//		String sb = "{'accountid':'1744f9d3f3464f6c8190e0e187201e02','appId':'bda48d2835df4057b2bd855db282c7e3','callId':'31c2d53c104943579a36bb9475c92e55','callType':'1','called':'18588726514','clientNumber':'64279506819269','sig':'97cd1aa4716e2c9e23fd31705b629333','timestamp':'20151024133333','toSerNum':'13696947436'}";
//		JSONObject objJson =  JSONObject.fromObject(sb);
//		System.out.println(objJson);
	
	}
	
	
	@Override
	protected Object createNewInstance() {
		return expressageCourier;
	}
	@Override
	protected IBaseService getManager() {
		return expressageCourierService;
	}
	@Override
	public Object getModel() {
		return getExpressageCourier();
	}
	@Override
	public void setModel(Object obj) {
		setExpressageCourier((ExpressageCourier) obj);
	}
	public ExpressageCourier getExpressageCourier() {
		return expressageCourier;
	}
	public void setExpressageCourier(ExpressageCourier expressageCourier) {
		this.expressageCourier = expressageCourier;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public File getImg1() {
		return img1;
	}

	public void setImg1(File img1) {
		this.img1 = img1;
	}

	public File getImg2() {
		return img2;
	}

	public void setImg2(File img2) {
		this.img2 = img2;
	}

	public File getImg3() {
		return img3;
	}

	public void setImg3(File img3) {
		this.img3 = img3;
	}
	
	
}
