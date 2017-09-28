package com.cndatacom.rbac.system.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.BaseJsonPrint;
import com.cndatacom.common.utils.Configure;
import com.cndatacom.common.utils.DateUtil;
import com.cndatacom.common.utils.GetIpUtils;
import com.cndatacom.common.utils.MD5Service;
import com.cndatacom.common.utils.MathRandom;
import com.cndatacom.common.utils.MiSenderUtils;
import com.cndatacom.common.utils.MiSenderUtils2;
import com.cndatacom.common.utils.PushUtil;
import com.cndatacom.common.utils.SmsTools;
import com.cndatacom.common.utils.UploadFile;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.common.weixin.util.Sha1;
import com.cndatacom.common.weixin.util.TextFileUtil;
import com.cndatacom.rbac.common.expressage.FormatType;
import com.cndatacom.rbac.common.expressage.SdkHttpResult;
import com.cndatacom.rbac.common.web.action.ApiHttpClient;
import com.cndatacom.rbac.pojo.CourierModel;
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.pojo.ExpressageComCourier;
import com.cndatacom.rbac.pojo.ExpressageComment;
import com.cndatacom.rbac.pojo.ExpressageCommon;
import com.cndatacom.rbac.pojo.ExpressageCompany;
import com.cndatacom.rbac.pojo.ExpressageCourier;
import com.cndatacom.rbac.pojo.ExpressageDistrict;
import com.cndatacom.rbac.pojo.ExpressageF;
import com.cndatacom.rbac.pojo.ExpressageFavorable;
import com.cndatacom.rbac.pojo.ExpressageIdea;
import com.cndatacom.rbac.pojo.ExpressageIntegral;
import com.cndatacom.rbac.pojo.ExpressageOrder;
import com.cndatacom.rbac.pojo.ExpressageOrderLog;
import com.cndatacom.rbac.pojo.ExpressageProvincial;
import com.cndatacom.rbac.pojo.ExpressageText;
import com.cndatacom.rbac.pojo.ExpressageToken;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.pojo.ExpressageVersion;
import com.cndatacom.rbac.pojo.FavorableModel;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageComCourierService;
import com.cndatacom.rbac.system.service.ExpressageCommentService;
import com.cndatacom.rbac.system.service.ExpressageCommonService;
import com.cndatacom.rbac.system.service.ExpressageCompanyService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageDistrictService;
import com.cndatacom.rbac.system.service.ExpressageFService;
import com.cndatacom.rbac.system.service.ExpressageFavorableService;
import com.cndatacom.rbac.system.service.ExpressageIdeaService;
import com.cndatacom.rbac.system.service.ExpressageIntegralService;
import com.cndatacom.rbac.system.service.ExpressageOrderLogService;
import com.cndatacom.rbac.system.service.ExpressageOrderService;
import com.cndatacom.rbac.system.service.ExpressageProvincialService;
import com.cndatacom.rbac.system.service.ExpressageTextService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;
import com.cndatacom.rbac.system.service.ExpressageVersionService;

/**
 * 
 * 类名: ExpressageUserAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 用户action类</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-9-30
 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
 */
@Controller
@Action("expressageUser")
@Scope("prototype")
@Namespace("/rbac/sys")
public class ExpressageUserAction extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageUserService expressageUserService;
	@Resource
    private ExpressageTokenService expressageTokenService;
	@Resource
    private ExpressageCourierService expressageCourierService;
	@Resource
    private ExpressageCommonService expressageCommonService;
	@Resource
    private ExpressageFavorableService expressageFavorableService;
	@Resource
    private ExpressageCommentService expressageCommentService;
	@Resource
    private ExpressageOrderService expressageOrderService;
	@Resource
    private ExpressageOrderLogService expressageOrderLogService;
	@Resource
    private ExpressageComCourierService expressageComCourierService;
	@Resource
    private ExpressageVersionService expressageVersionService;
	@Resource
    private ExpressageProvincialService expressageProvincialService;
	@Resource
    private ExpressageCityService expressageCityService;
	@Resource
    private ExpressageDistrictService expressageDistrictService;
	@Resource
    private ExpressageCompanyService expressageCompanyService;
	@Resource
    private ExpressageIdeaService expressageIdeaService;
	@Resource
    private ExpressageTextService expressageTextService;
	@Resource
    private ExpressageIntegralService expressageIntegralService;
	@Resource
    private ExpressageFService expressageFService;

	private ExpressageUser expressageUser;
	private File avatar;//上传文件
	Page<ExpressageIntegral> pageIntegral = new Page<ExpressageIntegral>();
	private static Map cacheConfMap = new HashMap();
	private static Map<String,String> randMap = new HashMap<>();
	
	/**
	 * 
	 * 方法名: String</br>
	 * 详述: 测试</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-7-1</br>
	 */
	@Action(value = "/test")
	public String test() throws Exception{
		
//		ExpressageIntegral ei = new ExpressageIntegral();
//		ei.setContent("登录");
//		ei.setCreateDate(new Date());
//		ei.setIntegralId("+5");
//		ei.setStatus("1");
//		ei.setType("1");
//		ei.setUserId("11");
//		expressageIntegralService.save(ei);
		
		int code= (int) (Math.random()*9000+1000);
//		String str = SmsTools.checkNum("18122101240",code+"","30058");
//		System.out.println("短信下发==========="+str);
		
//		String code1 = (String)cacheConfMap.get("18122101240");
//		System.out.println(code1);
		
		List<ExpressageF> efLiset = expressageFService.find(" from ExpressageF order by fvalue asc");
		System.out.println(efLiset.get(0).getFname());
		System.out.println(efLiset.get(1).getFname());
		System.out.println(efLiset.get(2).getFname());
		
		getRequest().getSession().setAttribute("u_id", "8aaf0d6f57c22e990157c23b66aa003f");
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: userLogin</br>
	 * 详述: 登录加积分</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/userLogin")
	public String userLogin() throws Exception{
		String token = getRequest().getParameter("token");
//		isLogin(token,"1");
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: saveUser</br>
	 * 详述: 注册</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/saveUser")
	public String saveUser() throws Exception{
		String phone = getRequest().getParameter("phone");
		String token = getRequest().getParameter("token");
		String password = getRequest().getParameter("password");
		String code = getRequest().getParameter("code");
		JSONObject obj = new JSONObject();
		try {
			//条件过滤
			if(StringUtils.isBlank(phone)||StringUtils.isBlank(token)||StringUtils.isBlank(password)||StringUtils.isBlank(code)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			
			String code1 = (String)cacheConfMap.get(phone);
			if(!code.equals(code1)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码有误", null);
				writerMessageForJson(json);
				return null;
			}
			cacheConfMap.remove(phone);
			//注册成功，创建账号
			expressageUser = new ExpressageUser();
			expressageUser.setPassword(password.toUpperCase());
			expressageUser.setUserName(phone);
			expressageUser.setAvatar("/upload/userAvatar/default.jpg");
			expressageUser.setCreateDate(new Date());
			expressageUser.setPhone(phone);
			expressageUser.setGender("1");
			expressageUser.setIsLock("1");
			expressageUser.setPhoneNumber(phone);
			expressageUser.setCouponNum("0");
			expressageUser.setValidIntegral("0");
			expressageUser.setIntegral("0");
			expressageUser.setReputation("5");
			expressageUser.setClientNumber("0");
			expressageUser.setClientPwd("0");
			expressageUserService.save(expressageUser);
			
			//注册后登录
			ExpressageToken mt = expressageTokenService.findUnique(" from ExpressageToken where tokenName = ? and type = '1' ", token);
			if(mt!=null){
				expressageTokenService.delete(mt);
			}
			mt = new ExpressageToken();
			mt.setUserId(expressageUser.getUserId());
			mt.setCreateDate(new Date());
			mt.setTokenName(token);
			mt.setType("1");
			expressageTokenService.save(mt);
			
			
			obj.accumulate("userId", expressageUser.getUserId());
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
			writerMessageForJson(json);
			return null;
		} catch (Exception e) {
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
	}
	
	@Action(value = "/getOpenId")
	public String getOpenId() throws Exception{
		String code = getRequest().getParameter("code");
		String postStr="appid=wxe8a02469a0ebe29a&secret=a3c4dbef33115978b4aaf3b02284d6bc&code="+code+"&grant_type=authorization_code";
		//String postStr="appid=wxe8a02469a0ebe29a&secret=a3c4dbef33115978b4aaf3b02284d6bc&code="+code+"&grant_type=authorization_code";
		String postUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
		 
		String str = PushUtil.invokingSMS(postStr, postUrl);
		System.out.println("========================================="+str);
		
		JSONObject jsonobject = JSONObject.fromObject(str);
		String errcode = jsonobject.getString("errcode");
		String sid = jsonobject.getString("id");
		
		
		JSONObject obj = JSONObject.fromObject(str);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: saveUserWX</br>
	 * 详述: 微信注册</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2017-5-9</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/saveUserWX")
	public String saveUserWX() throws Exception{
		String phone = getRequest().getParameter("phone");
		String password = getRequest().getParameter("password");
		String code = getRequest().getParameter("code");
		String wxcode = getRequest().getParameter("wxcode");
		JSONObject obj = new JSONObject();
		try {
			//条件过滤
			if(StringUtils.isBlank(phone)||StringUtils.isBlank(password)||StringUtils.isBlank(code)||StringUtils.isBlank(wxcode)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			
			String code1 = (String)cacheConfMap.get(phone);
			if(!code.equals(code1)){
				System.out.println("code:"+code);
				System.out.println("code1:"+code1);
				JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码有误", null);
				writerMessageForJson(json);
				return null;
			}
			ExpressageUser eu = expressageUserService.findUniqueBy("phone", phone);
			
			if(eu!=null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100015", "该号码已经注册过了", null);
				writerMessageForJson(json);
				return null;
			}
			
			String postStr="appid=wxb29f8c9e9415156f&secret=b8c6f0635376b1dfead52faecfe9dfee&code="+wxcode+"&grant_type=authorization_code";
			String postUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
			 
			String str = PushUtil.invokingSMS(postStr, postUrl);
			System.out.println("========================================="+str);
			
			JSONObject jsonobject = JSONObject.fromObject(str);
			String openid = jsonobject.getString("openid");
			System.out.println("======================openid============"+openid);
			
			cacheConfMap.remove(phone);
			//注册成功，创建账号
			expressageUser = new ExpressageUser();
			expressageUser.setPassword(password.toUpperCase());
			expressageUser.setUserName(phone);
			expressageUser.setAvatar("/upload/userAvatar/default.jpg");
			expressageUser.setCreateDate(new Date());
			expressageUser.setPhone(phone);
			expressageUser.setGender("1");
			expressageUser.setIsLock("1");
			expressageUser.setPhoneNumber(phone);
			expressageUser.setCouponNum("0");
			expressageUser.setValidIntegral("0");
			expressageUser.setIntegral("0");
			expressageUser.setReputation("5");
			expressageUser.setClientNumber("0");
			expressageUser.setClientPwd("0");
			expressageUser.setOpenid(openid);
			expressageUserService.save(expressageUser);
			
			//注册后登录
			getRequest().getSession().setAttribute("u_id", expressageUser.getUserId());
			
			//obj.accumulate("userId", expressageUser.getUserId());
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
	
	
	
	/**
	 * 
	 * 方法名: loginUser</br>
	 * 详述: user登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/loginUser")
	public String loginUser() throws Exception {

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
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where password=? and phone = ?", pwd.toUpperCase(),phone);
			if(expressageUser==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100009", "账号或密码有误", null);
				writerMessageForJson(json);
				return null;
			}else{
				//登录成功，更新数据库中的token记录
				//ExpressageToken expressageToken = expressageTokenService.findUniqueBy("userId", expressageUser.getUserId());
				ExpressageToken expressageToken = expressageTokenService.findUnique(" from ExpressageToken where userId = ? and type = '1' ", expressageUser.getUserId());
				if(expressageToken == null){
					expressageToken = new ExpressageToken();
				}else{
					if(!expressageToken.getTokenName().equals(token)){
						//新机器登录，推送下线通知
						MiSenderUtils.sendMessage(expressageToken.getTokenName(),"100001",null,"100001");
						MiSenderUtils2.sendMessage(expressageToken.getTokenName(),null,null,"100001");
					}
				}
				ExpressageToken mt = expressageTokenService.findUnique(" from ExpressageToken where tokenName = ? and type = '1' ", token);
				if(null!=mt){
					expressageTokenService.delete(mt);
				}
				expressageToken.setCreateDate(new Date());
				expressageToken.setTokenName(token);
				expressageToken.setType("1");
				expressageToken.setUserId(expressageUser.getUserId());
				expressageTokenService.save(expressageToken);
				
				JSONObject obj = new JSONObject();
				obj.accumulate("userId", expressageUser.getUserId());
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
				writerMessageForJson(json);
				return null;
			}
					
		} catch (Exception e) {
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * 方法名: loginUserWX</br>
	 * 详述:微信登录 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2017-5-9</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/loginUserWX")
	public String loginUserWX() throws Exception {

		String phone = getRequest().getParameter("phone");
		String pwd = getRequest().getParameter("password");
		String wxcode = getRequest().getParameter("wxcode");
		try {
			if(StringUtils.isBlank(phone)||StringUtils.isBlank(pwd)||StringUtils.isBlank(wxcode)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where password=? and phone = ?", pwd.toUpperCase(),phone);
			if(expressageUser==null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100009", "账号或密码有误", null);
				writerMessageForJson(json);
				return null;
			}else{
				if(StringUtils.isBlank(expressageUser.getOpenid())){
					String postStr="appid=wxb29f8c9e9415156f&secret=b8c6f0635376b1dfead52faecfe9dfee&code="+wxcode+"&grant_type=authorization_code";
					String postUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
					 
					String str = PushUtil.invokingSMS(postStr, postUrl);
					System.out.println("========================================="+str);
					
					JSONObject jsonobject = JSONObject.fromObject(str);
					String openid = jsonobject.getString("openid");
					System.out.println("======================openid============"+openid);
					expressageUser.setOpenid(openid);
				}
				
				
				//登录成功，保存session记录
				getRequest().getSession().setAttribute("u_id", expressageUser.getUserId());
				
				//加积分
				expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId = ? and isLock = '1' ", expressageUser.getUserId());
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
					}
				}
				expressageUserService.save(expressageUser);
				
				JSONObject obj = new JSONObject();
				obj.accumulate("userId", expressageUser.getUserId());
				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
				writerMessageForJson(json);
				return null;
			}
					
		} catch (Exception e) {
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * 方法名: logout</br>
	 * 详述:注销登录 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/logout")
	public String logout() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		//条件过滤
		if(StringUtils.isBlank(token)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		ExpressageToken expressageToken = expressageTokenService.findUnique(" from ExpressageToken where type = ? and tokenName = ?", type,token);
		if(expressageToken!=null){
			expressageTokenService.delete(expressageToken);
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: logoutWX</br>
	 * 详述: 微信注销登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2017-5-19</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/logoutWX")
	public String logoutWX() throws Exception{
		
		getRequest().getSession().removeAttribute("u_id");
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: eText</br>
	 * 详述:获取图文信息 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/eText")
	public String eText() throws Exception{
		String type = getRequest().getParameter("type");
		//条件过滤
		if(StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		List<ExpressageText> expressageTexts = expressageTextService.find(" from ExpressageText where status='1' and type = ? order by rank*1 asc  ",type);
		
		JSONArray jsonArray = JSONArray.fromObject(expressageTexts);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getUserIntegrals</br>
	 * 详述: 积分列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getUserIntegrals")
	public String getUserIntegrals() throws Exception{
		String token = getRequest().getParameter("token");
		String pageNo=getRequest().getParameter("pageNo");
		String pageSize=getRequest().getParameter("pageSize");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}
		
		
		if(expressageUser.getIsLock().equals("2")){
//			ExpressageToken expressageToken = expressageTokenService.findUniqueBy("userId", expressageUser);
//			if(expressageToken!=null){
//				expressageTokenService.delete(expressageToken);
//			}
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账户已经被禁，请联系管理员", null);
			writerMessageForJson(json);
			return null;
		}
		pageIntegral.setStart(Integer.parseInt(pageNo)*Integer.parseInt(pageSize));
		pageIntegral.setLimit(Integer.parseInt(pageSize));
		
		expressageIntegralService.findPage(pageIntegral, " from ExpressageIntegral where status = '1' and userId = ? order by createDate desc ) ",expressageUser.getUserId());
		
		List<ExpressageIntegral> eoList =  pageIntegral.getResult();
		
		JSONArray jsonArray = JSONArray.fromObject(eoList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: changePassward</br>
	 * 详述: 忘记密码</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/changePassward")
	public String changePassward() throws Exception{
		System.out.println("1");
		String phone = getRequest().getParameter("phone");
		String password = getRequest().getParameter("password");
		String code = getRequest().getParameter("code");
		String type = getRequest().getParameter("type");
		System.out.println("2");
		//条件过滤
		if(StringUtils.isBlank(phone)||StringUtils.isBlank(password)||StringUtils.isBlank(code)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		System.out.println("3");
		String code1 = (String)cacheConfMap.get(phone);
		if(!code.equals(code1)){
			System.out.println("code:"+code);
			System.out.println("code1:"+code1);
			JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码有误", null);
			writerMessageForJson(json);
			return null;
		}
		cacheConfMap.remove(phone);
		if(type.equals("1")){
			expressageUser = expressageUserService.findUniqueBy("phone", phone);
			expressageUser.setPassword(password.toUpperCase());
			expressageUserService.save(expressageUser);
		}else if(type.equals("2")){
			ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("phone", phone);
			expressageCourier.setPassword(password.toUpperCase());
			expressageCourierService.save(expressageCourier);
		}
		
		//添加用户日志
		
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updatePassword</br>
	 * 详述: 修改密码</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updatePassword")
	public String updatePassword() throws Exception {
		String token = getRequest().getParameter("token");
		String password = getRequest().getParameter("password");
		String newPassword = getRequest().getParameter("newPassword");
		String type = getRequest().getParameter("type");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(password)||StringUtils.isBlank(newPassword)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(password)||StringUtils.isBlank(newPassword)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		
		
		
		if(type.equals("1")){//用户修改密码
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				String userId = getUserIdByToken(token,"1");
				expressageUser = expressageUserService.findUniqueBy("userId", userId);
			}else{
				String userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
				expressageUser = expressageUserService.findUniqueBy("userId", userId);
			}
			
			if(!expressageUser.getPassword().equals(password.toUpperCase())){
				JSONObject json = BaseJsonPrint.SynthesisJson("100016", "原始密码有误", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser.setPassword(newPassword.toUpperCase());
			expressageUserService.save(expressageUser);
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
			
		}else if(type.equals("2")){//快递员修改密码
			isLogin(token,"2");
			String userId = getUserIdByToken(token,"2");
			ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", userId);
			if(!expressageCourier.getPassword().equals(password.toUpperCase())){
				JSONObject json = BaseJsonPrint.SynthesisJson("100016", "原始密码有误", null);
				writerMessageForJson(json);
				return null;
			}
			expressageCourier.setPassword(newPassword.toUpperCase());
			expressageCourierService.save(expressageCourier);
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
			
		}else{
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数有误", null);
			writerMessageForJson(json);
			return null;
		}
		
	}
	
	/**
	 * 
	 * 方法名:  </br>
	 * 详述: 下发验证码</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/smsCode")
	public String smsCode() throws Exception {
		String phone = getRequest().getParameter("phone");
		String type = getRequest().getParameter("type");
		String status = getRequest().getParameter("status");
		String randomStr = getRequest().getParameter("randomStr");
		String randVal = getRequest().getParameter("rand");
		String rand = randMap.get(randomStr);
		randMap.remove(randomStr);
		if(null==rand){
			JSONObject json = BaseJsonPrint.SynthesisJson("100015", "验证码过期,请重新获取验证码", null);
			writerMessageForJson(json);
			return null;
		}
		if(!rand.equalsIgnoreCase(randVal)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100014", "图形验证码错误", null);
			writerMessageForJson(json);
			return null;
		}
		try {
			if(StringUtils.isBlank(phone)||StringUtils.isBlank(type)||StringUtils.isBlank(status)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}else{
				if(type.equals("1")){//短信下发类型，1为注册类，2为忘记密码
					String s = checkPhone(phone,status);
					if(s!=null&&s.equals("1")){
						JSONObject json = BaseJsonPrint.SynthesisJson("100007", "该手机号码已经注册过了", null);
						writerMessageForJson(json);
						return null;
					}
					int code= (int) (Math.random()*9000+1000);
					System.out.println(phone+"验证码:"+code);//TODO
					//String str = SmsTools.checkNum(phone,code+"","30058");
					JSONObject resultJson = SmsTools.singleSend("【快递快滴】您的验证码是"+code, phone);
					System.out.println("短信下发==========="+resultJson);
					if(0!=resultJson.getInt("code")){//短信下发失败
						JSONObject json = BaseJsonPrint.SynthesisJson("100010", resultJson.getString("msg"), null);
						writerMessageForJson(json);
						return null;
					}else{
						cacheConfMap.put(phone, code+"");
						JSONObject json = BaseJsonPrint.SynthesisJson("000000", "短信下发成功", null);
						writerMessageForJson(json);
						return null;
					}
				}else if(type.equals("2")){
					String s = checkPhone(phone,status);
					if(s!=null&&s.equals("0")){
						JSONObject json = BaseJsonPrint.SynthesisJson("100008", "您的号码还没注册过", null);
						writerMessageForJson(json);
						return null;
					}
					
					int code= (int) (Math.random()*9000+1000);
					//String str = SmsTools.checkNum(phone,code+"","30058");
					JSONObject resultJson = SmsTools.singleSend("【快递快滴】您的验证码是"+code, phone);
					System.out.println("短信下发==========="+resultJson);
					
					if(0!=resultJson.getInt("code")){//短信下发失败
						JSONObject json = BaseJsonPrint.SynthesisJson("100010", resultJson.getString("msg"), null);
						writerMessageForJson(json);
						return null;
					}else{
						cacheConfMap.put(phone, code+"");
						JSONObject json = BaseJsonPrint.SynthesisJson("000000", "短信下发成功", null);
						writerMessageForJson(json);
						return null;
					}
				}
			}
		} catch (Exception e) {
			JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
			writerMessageForJson(json);
			return null;
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: updateCommon</br>
	 * 详述: 更新用户常用收发件地址</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateCommon")
	public String updateCommon() throws Exception{
		String phone = getRequest().getParameter("phone");
		String address = getRequest().getParameter("address");
		String name = getRequest().getParameter("name");
		String provincialId = getRequest().getParameter("provincialId");
		String cityId = getRequest().getParameter("cityId");
		String districtId = getRequest().getParameter("districtId");
		String type = getRequest().getParameter("type");
		String commonId = getRequest().getParameter("commonId");
		String token = getRequest().getParameter("token");
		String status = getRequest().getParameter("status");
		String weixinType = getRequest().getParameter("weixinType");
		
		//条件过滤
		if(StringUtils.isBlank(districtId)||StringUtils.isBlank(token)||StringUtils.isBlank(phone)||StringUtils.isBlank(address)||StringUtils.isBlank(name)||StringUtils.isBlank(provincialId)||StringUtils.isBlank(cityId)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		String userId;
		if(StringUtils.isNotBlank(weixinType)){
			isLogin(token,"3");
			userId = getUserIdByToken(token,"3");
		}else{
			isLogin(token,"1");
			userId = getUserIdByToken(token,"1");
		}
		ExpressageCommon expressageCommon = new ExpressageCommon();
		if(StringUtils.isNotBlank(commonId)){
			expressageCommon = expressageCommonService.findUniqueBy("commonId", commonId);
		}
		expressageCommon.setAddress(address);
		expressageCommon.setCityId(cityId);
		expressageCommon.setCreateDate(new Date());
		expressageCommon.setName(name);
		expressageCommon.setPhone(phone);
		expressageCommon.setProvincialId(provincialId);
		expressageCommon.setDistrictId(districtId);
		expressageCommon.setType(type);
		expressageCommon.setUserId(userId);
		if(StringUtils.isNotBlank(status)&&status.equals("1")){
			//设置默认地址
			expressageCommon.setStatus("1");
			//修改原默认地址状态
			ExpressageCommon ec = expressageCommonService.findUnique(" from ExpressageCommon where status = 1 and type = ? and userId = ? ",type, userId);
			if(ec != null){
				ec.setStatus("2");
				expressageCommonService.save(ec);
			}
		}else{
			expressageCommon.setStatus("2");
		}
		expressageCommonService.save(expressageCommon);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		
		return null;
	}
	
	/**
	 * 
	 * 方法名: deleteCommonByid</br>
	 * 详述: 批量删除常用收发件地址</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/deleteCommonByid")
	public String deleteCommonByid() throws Exception{
		String token = getRequest().getParameter("token");
		String commonIds = getRequest().getParameter("commonIds");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(commonIds)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		if(StringUtils.isNotBlank(type)){
			isLogin(token,"3");
		}else{
			isLogin(token,"1");
		}
		
		String [] str = commonIds.split(",");
		for (int i = 0; i < str.length; i++) {
			expressageCommonService.delete(str[i]);
		}
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getDefaultCommon</br>
	 * 详述: 默认收发件地址</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getDefaultCommon")
	public String getDefaultCommon() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		isLogin(token,"1");
		String userId = getUserIdByToken(token,"1");
		ExpressageCommon ec = expressageCommonService.findUnique(" from ExpressageCommon where status = 1 and type = ? and userId = ? ",type, userId);
		if(ec ==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
		}
		ExpressageProvincial expressageProvincial = expressageProvincialService.findUniqueBy("provincialId", ec.getProvincialId());
		ExpressageCity expressageCity = expressageCityService.findUniqueBy("cityId",ec.getCityId());
		ExpressageDistrict expressageDistrict = expressageDistrictService.findUniqueBy("districtId",ec.getDistrictId());
		
		JSONObject j = BaseJsonPrint.JstrTdate(ec, ec.getCreateDate());
		j.accumulate("provincialName", expressageProvincial.getName());
		j.accumulate("cityName", expressageCity.getName());
		j.accumulate("districtName", expressageDistrict.getName());
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", j);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getUserInformation</br>
	 * 详述: 获取用户资料</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getUserInformation")
	public String getUserInformation() throws Exception{
		String userId = getRequest().getParameter("userId");
		if(StringUtils.isBlank(userId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}	
		expressageUser = expressageUserService.findUniqueBy("userId", userId);
		if(expressageUser==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100001", "用户不存在", null);
			writerMessageForJson(json);
			return null;
		}
		
		if(expressageUser.getIsLock().equals("2")){
			ExpressageToken expressageToken = expressageTokenService.findUniqueBy("userId", userId);
			if(expressageToken!=null){
				expressageTokenService.delete(expressageToken);
			}
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账户已经被禁，请联系管理员", null);
			writerMessageForJson(json);
			return null;
		}
		
		JSONObject obj = new JSONObject();
		obj.accumulate("avatar", GetIpUtils.SplicePath(getRequest(), expressageUser.getAvatar()));
		obj.accumulate("userId", expressageUser.getUserId());
		obj.accumulate("userName", expressageUser.getUserName());
		obj.accumulate("phone", expressageUser.getPhoneNumber());
		obj.accumulate("validIntegral", expressageUser.getValidIntegral());
		obj.accumulate("reputation", expressageUser.getReputation());
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: updateUserInformation</br>
	 * 详述: 更新用户资料</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/updateUserInformation")
	public String updateUserInformation() throws Exception{
		String userName = getRequest().getParameter("userName");
		String phoneNumber = getRequest().getParameter("phoneNumber");
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
		
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}
		
		if(StringUtils.isNotBlank(userName)){
			expressageUser.setUserName(userName);
		}
		if(StringUtils.isNotBlank(phoneNumber)){
			expressageUser.setPhoneNumber(phoneNumber);
		}
		
		UploadFile up = new UploadFile();
		// 上传文件路径
		String realpath = getRequest().getRealPath("/");
		// 如果图片不等于空
		if (null != avatar) {
			String imageFileName = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmssSSS")+expressageUser.getPhone()+".png";
			// 上传文件
			String pa = up.uploadExcelParserReport(avatar, imageFileName,
					realpath + "/upload/userAvatar");
			System.out.println(pa);
			//数据库存网络地址
			String  str="/upload/userAvatar/"+imageFileName; 
			System.out.println("str=="+str);
			expressageUser.setAvatar(str);
		}
		expressageUserService.save(expressageUser);
		JSONObject obj = new JSONObject();
		obj.accumulate("avatar", GetIpUtils.SplicePath(getRequest(), expressageUser.getAvatar()));
		obj.accumulate("userId", expressageUser.getUserId());
		obj.accumulate("userName", expressageUser.getUserName());
		obj.accumulate("phone", expressageUser.getPhoneNumber());
		obj.accumulate("validIntegral", expressageUser.getValidIntegral());
		obj.accumulate("reputation", expressageUser.getReputation());
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: addComCourier</br>
	 * 详述:设置常用快递员 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/addComCourier")
	public String addComCourier() throws Exception{
		String token = getRequest().getParameter("token");
		String courierId = getRequest().getParameter("courierId");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(courierId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		String userId;
		if(StringUtils.isNotBlank(type)){
			isLogin(token,"3");
			userId = getUserIdByToken(token,"3");
		}else{
			isLogin(token,"1");
			userId = getUserIdByToken(token,"1");
		}
		
		
		ExpressageComCourier expressageComCourier = expressageComCourierService.findUnique(" from ExpressageComCourier where courierId = ? and userId = ?  ", courierId,userId);
		if(expressageComCourier!=null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100023", "已经是你的常用快递员", null);
			writerMessageForJson(json);
			return null;
		}
		
		expressageComCourier = new ExpressageComCourier();
		expressageComCourier.setCourierId(courierId);
		expressageComCourier.setUserId(userId);
		expressageComCourierService.save(expressageComCourier);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: deleteComCourierByid</br>
	 * 详述: 批量删除用户常用快递员</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/deleteComCourierByid")
	public String deleteComCourierByid() throws Exception{
		String token = getRequest().getParameter("token");
		String comCourierIds = getRequest().getParameter("comCourierIds");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(comCourierIds)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		if(StringUtils.isNotBlank(type)){
			isLogin(token,"3");
		}else{
			isLogin(token,"1");
		}
		
		String [] str = comCourierIds.split(",");
		for (int i = 0; i < str.length; i++) {
			expressageComCourierService.delete(str[i]);
		}
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: getAddressList</br>
	 * 详述:历史地址列表 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getAddressList")
	public String getAddressList() throws Exception{
		String type = getRequest().getParameter("type");
		String token = getRequest().getParameter("token");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(type)||StringUtils.isBlank(token)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}
		
		
		
		StringBuffer sb = new StringBuffer("select ");
		if(type.equals("1")){//寄
			sb.append(" distinct(jAddress) from  ExpressageOrder where userId = ? and type = '1' and status = '3'  order by createDate desc ");
		}else{//收
			sb.append(" distinct(sAddress) from  ExpressageOrder where userId = ? and type = '1' and status = '3'  order by createDate desc  ");
		}
		
		List<String> meList=expressageOrderService.find(sb.toString(),expressageUser.getUserId());
		
		JSONArray jsonArray = JSONArray.fromObject(meList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: getUserIdByOpenid</br>
	 * 详述:获取用户id </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 */
	@Action(value = "/getUserIdByOpenid")
	public String getUserIdByOpenid() throws Exception{
		String openId = getRequest().getParameter("openId");
		if(StringUtils.isBlank(openId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		isLogin(openId,"3");
		String userId = getUserIdByToken(openId,"3");
		
		if(StringUtils.isBlank(userId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		JSONObject obj = new JSONObject();
		obj.accumulate("userId", userId);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: evaluateByToken</br>
	 * 详述: 评价</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/evaluateByToken")
	public String evaluateByToken() throws Exception{
		String token = getRequest().getParameter("token");
		String orderId = getRequest().getParameter("orderId");
		String rank = getRequest().getParameter("rank");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(orderId)||StringUtils.isBlank(rank)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		isLogin(token,"1");
		String userId = getUserIdByToken(token,"1");
		
		ExpressageOrder expressageOrder = expressageOrderService.findUnique(" from ExpressageOrder where orderId = ? and userId = ?", orderId,userId);
		if(expressageOrder==null||!expressageOrder.getStatus().equals("3")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100021", "该单不满足评价条件", null);
			writerMessageForJson(json);
			return null;
		}
		ExpressageComment expressageComment = expressageCommentService.findUnique(" from ExpressageComment where orderId = ? ", orderId);
		if(expressageComment!=null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100022", "已经评价过了", null);
			writerMessageForJson(json);
			return null;
		}
		expressageComment = new ExpressageComment();
		expressageComment.setCourierId(expressageOrder.getCourierId());
		expressageComment.setCreateDate(new Date());
		expressageComment.setOrderId(orderId);
		expressageComment.setRank(rank);
		expressageComment.setRemark("1");
		expressageCommentService.save(expressageComment);
		
		expressageOrder.setStatus("4");
		expressageOrderService.save(expressageOrder);
		
		ExpressageOrderLog expressageOrderLog =new ExpressageOrderLog();
		expressageOrderLog.setContent("评价订单："+expressageOrder.getOrderId());
		expressageOrderLog.setCreateDate(new Date());
		expressageOrderLog.setMethod("evaluateByToken");
		expressageOrderLog.setOrderId(expressageOrder.getOrderId());
		expressageOrderLog.setType("1");
		expressageOrderLog.setUserId(userId);
		expressageOrderLogService.save(expressageOrderLog);
		
		if(rank.equals("3")){
			List<ExpressageComment> ecList = expressageCommentService.find(" from ExpressageComment where courierId = ? and rank='3' and remark = '1' ", expressageOrder.getCourierId());
			if(ecList.size()>=3){
				ExpressageCourier ecc = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
				if(ecc!=null){
					if(Integer.parseInt(ecc.getRank())>2){
						ecc.setRank(String.valueOf(Integer.parseInt(ecc.getRank())-1));
						expressageCourierService.save(ecc);
						for (int i = 0; i < ecList.size(); i++) {
							ExpressageComment ec = ecList.get(i);
							ec.setRemark("0");
							expressageCommentService.save(ec);
						}
						
					}
				}
			}
		}else if(rank.equals("5")){
			List<ExpressageComment> ecList = expressageCommentService.find(" from ExpressageComment where courierId = ? and rank='5' and remark = '1' ", expressageOrder.getCourierId());
			if(ecList.size()>=20){
				ExpressageCourier ecc = expressageCourierService.findUniqueBy("courierId", expressageOrder.getCourierId());
				if(ecc!=null){
					if(Integer.parseInt(ecc.getRank())<5){
						ecc.setRank(String.valueOf(Integer.parseInt(ecc.getRank())+1));
						expressageCourierService.save(ecc);
						for (int i = 0; i < ecList.size(); i++) {
							ExpressageComment ec = ecList.get(i);
							ec.setRemark("0");
							expressageCommentService.save(ec);
						}
						
					}
				}
			}
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: addIdea</br>
	 * 详述: 意见反馈</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/addIdea")
	public String addIdea() throws Exception{
		String token = getRequest().getParameter("token");
		String content = getRequest().getParameter("content");
		String type = getRequest().getParameter("type");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(content)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(content)||StringUtils.isBlank(type)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		if(type.equals("1")){//用户
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				String userId = getUserIdByToken(token,"1");
				expressageUser = expressageUserService.findUniqueBy("userId", userId);
			}else{
				String userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
				expressageUser = expressageUserService.findUniqueBy("userId", userId);
			}
			
			if(expressageUser.getIsLock().equals("2")){
				JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
				writerMessageForJson(json);
				return null;
			}
			
			ExpressageIdea ei = new ExpressageIdea();
			ei.setContent(content);
			ei.setCreateDate(new Date());
			ei.setStatus("0");
			ei.setType("1");
			ei.setUserId(expressageUser.getUserId());
			expressageIdeaService.save(ei);
			
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
			
		}else if(type.equals("2")){//快递员
			isLogin(token,"2");
			String userId = getUserIdByToken(token,"2");
			
			ExpressageIdea ei = new ExpressageIdea();
			ei.setContent(content);
			ei.setCreateDate(new Date());
			ei.setStatus("0");
			ei.setType("4");
			ei.setCourierId(userId);
			expressageIdeaService.save(ei);
			
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
			
		}else{
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数有误", null);
			writerMessageForJson(json);
			return null;
		}
	}
	
	
	/**
	 * 
	 * 方法名: addComplain</br>
	 * 详述: 投诉</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/addComplain")
	public String addComplain() throws Exception{
		String token = getRequest().getParameter("token");
		String content = getRequest().getParameter("content");
		String orderNo = getRequest().getParameter("orderNo");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(content)||StringUtils.isBlank(orderNo)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(content)||StringUtils.isBlank(orderNo)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		if(StringUtils.isBlank(channel)){
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}else{
			String userId = (String)getRequest().getSession().getAttribute("u_id");
			if(StringUtils.isBlank(userId)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
				writerMessageForJson(json);
				return null;
			}
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
		}
		
		if(expressageUser.getIsLock().equals("2")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		ExpressageOrder eo = expressageOrderService.findUnique(" from ExpressageOrder where type = '1'  and orderNo = ? ",orderNo);
		
		if(eo==null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "请输入正确的快递单号", null);
			writerMessageForJson(json);
			return null;
		}
		if(eo.getStatus().equals("1")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您不能投诉该订单", null);
			writerMessageForJson(json);
			return null;
		}
		if(!eo.getUserId().equals(expressageUser.getUserId())){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您不能投诉该订单", null);
			writerMessageForJson(json);
			return null;
		}
		
		ExpressageIdea ei = expressageIdeaService.findUnique(" from ExpressageIdea where type = '2' and orderNo = ?  ", orderNo);
		if(ei!=null){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "不能重复投诉该订单", null);
			writerMessageForJson(json);
			return null;
		}else{
			ei = new ExpressageIdea();
		}
		
		ei.setContent(content);
		ei.setCreateDate(new Date());
		ei.setStatus("0");
		ei.setType("2");
		ei.setOrderNo(orderNo);
		ei.setUserId(expressageUser.getUserId());
		expressageIdeaService.save(ei);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getVersionByType</br>
	 * 详述:获取最新版本信息 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-8-5</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getVersionByType")
	public String getVersionByType() throws Exception{
		String type = getRequest().getParameter("type");
		String versionCode = getRequest().getParameter("versionCode");
		if(StringUtils.isBlank(type)||StringUtils.isBlank(versionCode)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		List<ExpressageVersion> evList = expressageVersionService.find(" from ExpressageVersion where typeId = ? and versionCode>? order by versionCode desc ", type,versionCode);
		if(evList!=null&&evList.size()>0){
			ExpressageVersion ev = evList.get(0);
			JSONObject obj = new JSONObject();
			obj.accumulate("passageway", ev.getPassageway());
			obj.accumulate("url", ev.getUrl());
			obj.accumulate("versionCode", ev.getVersionCode());
			obj.accumulate("versionIntroduction", ev.getVersionIntroduction());
			obj.accumulate("versionName", ev.getVersionName());
			obj.accumulate("updateTime", DateUtil.format(ev.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
			writerMessageForJson(json);
			return null;
		}else{
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "没有新版本", null);
			writerMessageForJson(json);
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * 方法名: getFavorables</br>
	 * 详述: 获取用户优惠券列表</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2017-6-1</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getFavorables")
	public String getFavorables() throws Exception{
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String userId = (String)getRequest().getSession().getAttribute("u_id");
		if(StringUtils.isBlank(userId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
			writerMessageForJson(json);
			return null;
		}
		expressageUser = expressageUserService.findUniqueBy("userId", userId);
		
		if(expressageUser==null||expressageUser.getIsLock().equals("2")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100002", "您的账号被封，请与管理员联系", null);
			writerMessageForJson(json);
			return null;
		}
		
		String strSql = "";
		if(type.equals("1")){
			strSql = " from ExpressageFavorable where status = '1' and userId = ?  order by favorableName*1 desc ";
		}else{
			strSql = " from ExpressageFavorable where  userId = ?  order by status asc ";
		}
		
		List<ExpressageFavorable> efList = expressageFavorableService.find(strSql,userId);
		List<FavorableModel> fmList = new ArrayList<FavorableModel>();
		if(efList!=null&&efList.size()>0){
			
			for (int i = 0; i < efList.size(); i++) {
				ExpressageFavorable ev = efList.get(i);
				FavorableModel fm =new FavorableModel();
				BeanUtils.copyProperties(fm, ev);
				fm.setCreateDate(DateUtil.format(ev.getCreateDate(),"yyyy-MM-dd"));
				fm.setPastDue(DateUtil.format(ev.getPastDue(),"yyyy-MM-dd"));
				fmList.add(fm);
			}
			
		}
		
		JSONArray jsonArray = JSONArray.fromObject(fmList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
		
	}
	
	
	/**
	 * 
	 * 方法名: getLogisticsByOrderNum</br>
	 * 详述: 查询物流单号</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-10-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getLogisticsByOrderNum")
	public String getLogisticsByOrderNum() throws Exception{
		String logisticsJc = getRequest().getParameter("logisticsJc");
		String logisticsOrder = getRequest().getParameter("logisticsOrder");
		if(StringUtils.isBlank(logisticsJc)||StringUtils.isBlank(logisticsOrder)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String postStr="uid=33980&id="+logisticsJc+"&order="+logisticsOrder+"&key=66b4ef4b16cf4165bc8c92788b7f68c2";
		String postUrl="http://www.kuaidiapi.cn/rest.aspx";
		System.out.println("postStr=========================="+postStr);
		String str = PushUtil.invokingSMS(postStr, postUrl);
		System.out.println("str==============================="+str);
		
		JSONObject jsonobject = JSONObject.fromObject(str);
		String errcode = jsonobject.getString("errcode");
		String sid = jsonobject.getString("id");
		String name = jsonobject.getString("name");
		String order = jsonobject.getString("order");
		String message = jsonobject.getString("message");
		String status = jsonobject.getString("status").toString();
		String data = jsonobject.getString("data");
		System.out.println("jsonobject====================="+jsonobject);
		System.out.println("errcode====================="+errcode);
		if(!errcode.equals("0000")){
			JSONObject json = BaseJsonPrint.SynthesisJson("100019", "查询订单异常", null);
			writerMessageForJson(json);
			return null;
		}
		
		JSONObject obj = new JSONObject();
		obj.accumulate("logisticsJc", sid);
		obj.accumulate("logisticsName", name);
		obj.accumulate("logisticsOrder", order);
		obj.accumulate("logisticsMessage", message);
		obj.accumulate("logisticsStatus", status);
		obj.accumulate("createDate", DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(data);
		obj.accumulate("logisticsData", jsonArray);
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
//	/**
//	 * 
//	 * 方法名: getLogisticsByOrderNumWeb</br>
//	 * 详述: 官网查询物流单号</br>
//	 * 开发人员：朱恋青</br>
//	 * 创建时间：2015-7-30</br>
//	 * @return
//	 * @throws Exception
//	 */
//	@Action(value = "/getLogisticsByOrderNumWeb")
//	public String getLogisticsByOrderNumWeb() throws Exception{
//		String logisticsJc = getRequest().getParameter("logisticsJc");
//		String logisticsOrder = getRequest().getParameter("logisticsOrder");
//		String userId = getRequest().getParameter("userId");
//		if(StringUtils.isBlank(logisticsJc)||StringUtils.isBlank(logisticsOrder)){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		ExpressageLogistics el = expressageLogisticsService.findUnique(" from ExpressageLogistics where logisticsJc = ? and logisticsOrder = ? ", logisticsJc,logisticsOrder);
//		
//		if(el!=null){
//			long x = new Date().getTime()-el.getCreateDate().getTime();
//			if(x/1000/60 <10){
//				JSONObject obj = new JSONObject();
//				obj.accumulate("logisticsJc", el.getLogisticsJc());
//				obj.accumulate("logisticsName", el.getLogisticsName());
//				obj.accumulate("logisticsOrder", el.getLogisticsOrder());
//				obj.accumulate("logisticsMessage", el.getLogisticsMessage());
//				obj.accumulate("logisticsStatus", el.getLogisticsStatus());
//				obj.accumulate("createDate", DateUtil.parseDateToStr(el.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
//				JSONArray jsonArray = JSONArray.fromObject(el.getLogisticsData());
//				obj.accumulate("logisticsData", jsonArray);
//				JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
//				writerMessageForJson(json);
//				return null;
//			}
//		}
//		if(el==null){
//			el = new ExpressageLogistics();
//		}
//		String postStr="uid=33980&id="+logisticsJc+"&order="+logisticsOrder+"&key=66b4ef4b16cf4165bc8c92788b7f68c2";
//		String postUrl="http://www.kuaidiapi.cn/rest.aspx";
//		 
//		String str = PushUtil.invokingSMS(postStr, postUrl);
//		System.out.println(str);
//		
//		JSONObject jsonobject = JSONObject.fromObject(str);
//		String errcode = jsonobject.getString("errcode");
//		String sid = jsonobject.getString("id");
//		String name = jsonobject.getString("name");
//		String order = jsonobject.getString("order");
//		String message = jsonobject.getString("message");
//		String status = jsonobject.getString("status").toString();
//		String data = jsonobject.getString("data");
//		
//		if(!errcode.equals("0000")){
//			JSONObject json = BaseJsonPrint.SynthesisJson("100019", "查询订单异常", null);
//			writerMessageForJson(json);
//			return null;
//		}
//		el.setCreateDate(new Date());
//		el.setLogisticsData(data);
//		el.setLogisticsJc(sid);
//		el.setLogisticsMessage(message);
//		el.setLogisticsName(name);
//		el.setLogisticsOrder(order);
//		el.setLogisticsStatus(status);
//		expressageLogisticsService.save(el);
//		
//		if(StringUtils.isNotBlank(userId)){
//			expressageUser = expressageUserService.findUniqueBy("userId", userId);
//			if(expressageUser.getClientPwd()!=null){
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-");
//				String datastr = sdf.format(new Date());
//				List<ExpressageMinutes> euaList = expressageMinutesService.find(" from ExpressageMinutes where userId = ? " +
//						"and minutesNum = '5' and createDate like '"+datastr+"%' ", expressageUser.getPhone());
//				if(euaList.size()<30){
//					ExpressageUserAction eua = new ExpressageUserAction();
//					String strs = eua.changeMinutes(expressageUser.getClientNumber(), "5");
//					ExpressageMinutes ems = new ExpressageMinutes();
//					ems.setUserId(expressageUser.getPhone());
//					ems.setCreateDate(new Date());
//					ems.setMesage(strs);
//					ems.setMinutesNum("5");
//					ems.setType("1");
//					expressageMinutesService.save(ems);
//				}
//			}
//		}
//		
//		JSONObject obj = new JSONObject();
//		obj.accumulate("logisticsJc", el.getLogisticsJc());
//		obj.accumulate("logisticsName", el.getLogisticsName());
//		obj.accumulate("logisticsOrder", el.getLogisticsOrder());
//		obj.accumulate("logisticsMessage", el.getLogisticsMessage());
//		obj.accumulate("logisticsStatus", el.getLogisticsStatus());
//		obj.accumulate("createDate", DateUtil.parseDateToStr(el.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
//		JSONArray jsonArray = JSONArray.fromObject(el.getLogisticsData());
//		obj.accumulate("logisticsData", jsonArray);
//		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
//		writerMessageForJson(json);
//		return null;
//	}
	
	
	/**
	 * 
	 * 方法名: getCouriersByNearby</br>
	 * 详述:查询附近上班状态快递员 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-9-7</br>
	 * @return
	 */
	@Action(value = "/getCouriersByNearby")
	public String getCouriersByNearby() throws Exception{
		try {
			String token = getRequest().getParameter("token");
			String latitude = getRequest().getParameter("latitude");
			String longitude = getRequest().getParameter("longitude");
			String type = getRequest().getParameter("type");
			if(StringUtils.isBlank(token)||StringUtils.isBlank(latitude)||StringUtils.isBlank(longitude)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
				writerMessageForJson(json);
				return null;
			}
			String userId;
			if(StringUtils.isNotBlank(type)&&type.equals("3")){
				isLogin(token,"3");
				userId = getUserIdByToken(token,"3");
			}else{
				isLogin(token,"1");
				userId = getUserIdByToken(token,"1");
			}
			
			List<Map<String,Object>> ll =expressageCourierService.getCourierIdsByjw1(latitude, longitude);
			List<CourierModel>  cmList = new ArrayList<CourierModel>();
			for (int i = 0; i < ll.size(); i++) {
				//ExpressageCourier ec = ll.get(i);
				System.out.println("============================="+ll.get(i));
				CourierModel cm = ecToCm(ll.get(i));
				ExpressageComCourier ecc = expressageComCourierService.findUnique(" from ExpressageComCourier where userId = ? and courierId = ? ", userId,cm.getCourierId());
				if(ecc!=null){
					cm.setIsCommon("1");
				}
				
				cmList.add(cm);
			}
			
			JSONArray jsonArray = JSONArray.fromObject(cmList);
			JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
			writerMessageForJson(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public CourierModel ecToCm(Map<String,Object> ec){
		CourierModel cm = new CourierModel();
		if(StringUtils.isNotBlank(ec.get("company_id").toString())){
			ExpressageCompany ecom =expressageCompanyService.findUniqueBy("companyId", ec.get("company_id"));
			if(ecom!=null){
				cm.setCompanyName(ecom.getCompanyName());
			}
		}
		
		cm.setCourierId(ec.get("courier_id").toString());
		cm.setCourierName(ec.get("courier_name").toString());
		cm.setLatitude(ec.get("latitude").toString());
		cm.setLongitude(ec.get("longitude").toString());
		cm.setPhone(ec.get("phone").toString());
		System.out.println("111111111111111111111111111===="+ec.get("juli"));
		if(ec.get("juli")==null){
			cm.setJuli("0");
		}else{
			cm.setJuli(ec.get("juli").toString());
		}
		
		return cm;
	}
	
	/**
	 * 
	 * 方法名: getRYToken</br>
	 * 详述: 获取融云token</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-6-16</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getRYToken")
	public String getRYToken() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "token不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String strUserId ="";
		String strName ="";
		String strAvatar ="";
		if(type.equals("1")){
			//登录验证
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			expressageUser = expressageUserService.findUniqueBy("userId", userId);
			strUserId = userId;
			strName=expressageUser.getCouponNum();
			strAvatar=expressageUser.getAvatar();
			
		}else if(type.equals("2")){
			//登录验证
			isLogin(token,"2");
			String courierId = getUserIdByToken(token,"2");
			ExpressageCourier expressageCourier = expressageCourierService.findUniqueBy("courierId", courierId);
			strUserId = courierId;
			strName=expressageCourier.getCourierName();
			strAvatar=expressageCourier.getAvatar();
		}
		
		FormatType ft = FormatType.json;
		SdkHttpResult shr = ApiHttpClient.getToken("e0x9wycfxn1vq", "EBXduuRUSZWs", strUserId, strName, strAvatar, ft);
		
		JSONObject object = JSONObject.fromObject(shr.getResult());//json化对象
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", object);
		writerMessageForJson(json);
		return null;
	}
	
	
	
	/**
	 * 
	 * 方法名: checkPhone</br>
	 * 详述: 验证手机号码是否注册</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-7-1</br>
	 * @param phone
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String checkPhone(String phone,String type) throws Exception{
		if(type.equals("1")){
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where phone=? ", phone);
			if(expressageUser==null){
				return "0";
			}else{
				return "1";
			}
		}else{
			ExpressageCourier expressageCourier = expressageCourierService.findUnique(" from ExpressageCourier where phone = ?", phone);
			if(expressageCourier==null){
				return "0";
			}else{
				return "1";
			}
		}
	}
	
	/**
	 * 
	 * 方法名: isLogin</br>
	 * 详述: 验证登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-7-2</br>
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
			expressageUser = expressageUserService.findUnique(" from ExpressageUser where userId = ? and isLock = '1' ", expressageToken.getUserId());
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
	
	
	@Action(value = "/setFavorable")
	public String setFavorable() throws Exception{
		String orderId = getRequest().getParameter("orderId");
		if(StringUtils.isBlank(orderId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String userId = (String)getRequest().getSession().getAttribute("u_id");
		if(StringUtils.isBlank(userId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
			writerMessageForJson(json);
			return null;
		}
		
		ExpressageOrder expressageOrder =expressageOrderService.findUnique(" from ExpressageOrder where orderId = ?  ", orderId);
		if(expressageOrder!=null&&StringUtils.isBlank(expressageOrder.getLatitude())){
			expressageOrder.setLatitude("1");
			expressageOrderService.save(expressageOrder);
			System.out.println("产生优惠券===========================================");
			//随机产生优惠券
			ExpressageFavorable ef = new ExpressageFavorable();
			ef.setCreateDate(new Date());
			ef.setStatus("1");
			ef.setUserId(userId);
			ef.setPastDue(DateUtil.addMonth(new Date(),1));
			
			List<ExpressageF> efLiset = expressageFService.find(" from ExpressageF order by fvalue desc ");
			
			MathRandom a = new MathRandom();
			int s = a.PercentageRandom(Double.valueOf(efLiset.get(0).getFvalue()),Integer.valueOf(efLiset.get(0).getFname()),Double.valueOf(efLiset.get(1).getFvalue()),Integer.valueOf(efLiset.get(1).getFname())
					,Double.valueOf(efLiset.get(2).getFvalue()),Integer.valueOf(efLiset.get(2).getFname()),Double.valueOf(efLiset.get(3).getFvalue()),Integer.valueOf(efLiset.get(3).getFname())
					,Double.valueOf(efLiset.get(4).getFvalue()),Integer.valueOf(efLiset.get(4).getFname()),Double.valueOf(efLiset.get(5).getFvalue()),Integer.valueOf(efLiset.get(5).getFname()));
			
			
			if(s==0||s==-1){
				JSONObject json = BaseJsonPrint.SynthesisJson("100024", "很遗憾，谢谢！", null);
				writerMessageForJson(json);
				return null;
			}else{
				ef.setFavorableName(s+"");
			}
			expressageFavorableService.save(ef);
			JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
			writerMessageForJson(json);
			return null;
		}
		JSONObject json = BaseJsonPrint.SynthesisJson("100024", "很遗憾，谢谢！", null);
		writerMessageForJson(json);
		return null;
	}
	
	
	
	
	@Action(value = "/wxConfig1")
	public String wxConfig1() throws Exception{
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String wxStr =TextFileUtil.read(Configure.getSingleton().values("wx_jsapi_ticket"));
		System.out.println("wxStr================"+wxStr.trim());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("noncestr", MD5Service.encryptString(String.valueOf(new Date().getTime()/1000).toString()));
		signParams.put("jsapi_ticket", wxStr.trim());
		signParams.put("timestamp", new Date().getTime()/1000);
		
		if(type.equals("1")){
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/index.html");
		}else if(type.equals("2")){
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/test.html");
		}else{
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/test2.html");
		}
		
		signParams.put("sign", createSign1(signParams));
		System.out.println(signParams.get("sign"));
		
		JSONObject obj = new JSONObject();
		obj.accumulate("noncestr", signParams.get("noncestr"));
		obj.accumulate("timestamp", signParams.get("timestamp"));
		obj.accumulate("sign", signParams.get("sign"));
		obj.accumulate("appId", "wxb29f8c9e9415156f");
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	@Action(value = "/getRand")
	public void getRand() throws Exception{
		String randomStr = getRequest().getParameter("randomStr");
		if(null!=randomStr&&!"".equals(randomStr)){
			int w = 200, h = 80;
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			randMap.put(randomStr, verifyCode);
			VerifyCodeUtils.outputImage(w, h, getResponse().getOutputStream(), verifyCode);
		}
	}
	
	/**
	 * 
	 * 方法名: wxConfig</br>
	 * 详述:生成js sdk需要的配置信息 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-10-16</br>
	 * @return
	 */
	@Action(value = "/wxConfig")
	public String wxConfig() throws Exception{
		String type = getRequest().getParameter("type");
		if(StringUtils.isBlank(type)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String wxStr =TextFileUtil.read(Configure.getSingleton().values("wx_jsapi_ticket"));
		System.out.println("wxStr================"+wxStr.trim());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("noncestr", MD5Service.encryptString(String.valueOf(new Date().getTime()/1000).toString()));
		signParams.put("jsapi_ticket", wxStr.trim());
		signParams.put("timestamp", new Date().getTime()/1000);
		
		if(type.equals("1")){
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/index.html");
		}else if(type.equals("2")){
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/test.html");
		}else{
			signParams.put("url", "http://www.kuaidikd.com/expressage_api/code/test.html");
		}
		
		signParams.put("sign", createSign1(signParams));
		System.out.println(signParams.get("sign"));
		
		JSONObject obj = new JSONObject();
		obj.accumulate("noncestr", signParams.get("noncestr"));
		obj.accumulate("timestamp", signParams.get("timestamp"));
		obj.accumulate("sign", signParams.get("sign"));
		obj.accumulate("appId", "wxb29f8c9e9415156f");
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	public static String createSign1(SortedMap packageParams) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<Entry> es = packageParams.entrySet();
		Iterator<Entry> it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = entry.getValue() != null ? entry.getValue().toString() : null;
	
			if (null != v && !"".equals(v)&& !"sign".equals(k)&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		return Sha1.encode(sb.toString().substring(0,sb.toString().length()-1));
	}
	
	
	/**
	 * 
	 * 方法名: getUserIdByToken</br>
	 * 详述: 根据token获取用户id或快递员id</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015-7-2</br>
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
		//System.out.println("createCommonParam=="+createCommonParam());
//		String url = "https://api.qingmayun.com/20150822/SMS/templateSMS";
//		String body = "accountSid=1744f9d3f3464f6c8190e0e187201e02&appId=bda48d2835df4057b2bd855db282c7e3&templateId=3840469&to=18588726514"
//				+ "&param=测试信息，马上收！"+ createCommonParam();
//		String str = PushUtil.invokingSMS(body, url);
//		System.out.println(url+"?"+body);
//		System.out.println(str);
		
		
//		SimpleDateFormat sdf=new SimpleDateFormat("ssSSS");
//		String datastr = sdf.format(new Date());
//		System.out.println(datastr);
//		//System.out.println(MD5Service.encryptString("012400909150912"));
//		StringBuilder str=new StringBuilder();//定义变长字符串
//		Random random=new Random();
//		//随机生成数字，并添加到字符串
//		for(int i=0;i<8;i++){
//		    str.append(random.nextInt(10));
//		}
//		
//		System.out.println(str.toString());
		
		
//		String str = SmsTools.checkNum("18122101240","朱哥好","30058");
//
//		System.out.println(str);
		
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
	
	
	
	
	
	@Override
	protected Object createNewInstance() {
		return expressageUser;
	}

	@Override
	protected IBaseService getManager() {
		return expressageUserService;
	}

	@Override
	public Object getModel() {
		return getExpressageUser();
	}

	@Override
	public void setModel(Object obj) {
		setExpressageUser((ExpressageUser) obj);
	}

	public ExpressageUser getExpressageUser() {
		return expressageUser;
	}

	public void setExpressageUser(ExpressageUser expressageUser) {
		this.expressageUser = expressageUser;
	}
 
	public static Map getCacheConfMap() {
		return cacheConfMap;
	}

	public static void setCacheConfMap(Map cacheConfMap) {
		ExpressageUserAction.cacheConfMap = cacheConfMap;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	
	
}
