package com.cndatacom.rbac.system.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.orm.Page;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.utils.BaseJsonPrint;
import com.cndatacom.common.utils.DateUtil;
import com.cndatacom.common.utils.PushUtil;
import com.cndatacom.common.web.action.SimpleActionSupport;
import com.cndatacom.rbac.pojo.Chuchang;
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.pojo.ExpressageIntegral;
import com.cndatacom.rbac.pojo.ExpressageMessage;
import com.cndatacom.rbac.pojo.ExpressageToken;
import com.cndatacom.rbac.pojo.ExpressageUser;
import com.cndatacom.rbac.pojo.MessageExpressage;
import com.cndatacom.rbac.pojo.Ruchang;
import com.cndatacom.rbac.system.service.ChuchangService;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageCourierService;
import com.cndatacom.rbac.system.service.ExpressageIntegralService;
import com.cndatacom.rbac.system.service.ExpressageMessageService;
import com.cndatacom.rbac.system.service.ExpressageTokenService;
import com.cndatacom.rbac.system.service.ExpressageUserService;
import com.cndatacom.rbac.system.service.PpDetailService;
import com.cndatacom.rbac.system.service.RuchangService;

/**
 * 
 * 类名: ExpressageMessageAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 系统消息action</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2016-7-21
 */
@Controller
@Action("expressageMessage")
@Scope("prototype")
@Namespace("/rbac/sys")
public class ExpressageMessageAction  extends SimpleActionSupport{
	
	private static final long serialVersionUID = 1L;
	@Resource
    private ExpressageUserService expressageUserService;
	@Resource
    private ExpressageTokenService expressageTokenService;
	@Resource
    private ExpressageCourierService expressageCourierService;
	@Resource
    private ExpressageCityService expressageCityService;
	@Resource
    private ExpressageMessageService expressageMessageService;
	@Resource
    private ExpressageIntegralService expressageIntegralService;
	
	@Resource
    private ChuchangService chuchangService;
	@Resource
    private RuchangService ruchangService;
	@Resource
    private PpDetailService ppDetailService;
	
	private ExpressageMessage expressageMessage;
	Page<ExpressageMessage> pageMessage = new Page<ExpressageMessage>();
	
	
	@Action(value = "/xiaozhu22")
	public String xiaozhu22(){
		
		for (int i = 26; i < 27; i++) {
			Document doc = getDocument("https://cn.konest.com/contents/food_search.html?cp="+i);
			for (int j = 0; j < 15; j++) {
				try {
					String hname = doc.select("a[href~=^/contents/food_detail.html]a[class=text]").get(j).attr("href").toString();
					String urls = "https://cn.konest.com"+hname.replaceAll("detail", "map");
					
					Ruchang ruchang = new Ruchang();
					ruchang.setUrls(urls);
					ruchangService.save(ruchang);
				} catch (Exception e) {
					System.out.println("https://cn.konest.com/contents/food_search.html?cp="+i+"  j="+j);
					continue;
				}
			}
			
		}
		
		return null;
	}
	
	
	
	@Action(value = "/xiaozhu11")
	public String xiaozhu() throws Exception{
		
		List<Ruchang> rList = ruchangService.findBy("type1", "0");
		//		List<Ruchang> rList = ruchangService.getAll();
		
		for (int i = 0; i < rList.size(); i++) {
			try {
				Ruchang r = rList.get(i);
				Document doc = getDocument(r.getUrls());
				Chuchang cc = new Chuchang();
				
				Elements elements4 = doc.select("div[class=bot]");
				String cn = elements4.select("a").get(0).text().toString();
				String qn = elements4.select("a").get(1).text().toString();
				
				// 获取目标HTML代码
				Elements elements1 = doc.select("div[class=detail_taxi]");
				String hname = elements1.select("p[class=tit1]").text().toString();
				String zname = elements1.select("p[class=tit2]").text().toString();
				System.out.println("韩文店名："+hname);
				System.out.println("中文店名："+zname);
//            System.out.println("电话："+elements1.html().toString());
				
				Elements elements2 = elements1.select("div[class=con_area]");
				String address = elements2.select("p").get(2).text().toString().split("/")[0].trim();
				String haddress = elements2.select("p").get(0).text().toString();
				System.out.println("韩文地址："+haddress);
				System.out.println("中文地址："+address);
				
				Elements elements3 = elements2.select("div[class=info2]");
				String yytime = elements3.select("li[class=b1]").text().toString();
				String xxr = elements3.select("li[class=b2]").text().toString();
				String jt = elements3.select("li[class=b3]").text().toString();
				System.out.println("营业时间："+yytime);
				System.out.println("休息日："+xxr);
				System.out.println("交通："+jt);
				
				cc.setAddress(address);
				cc.setHaddress(haddress);
				cc.setHname(hname);
				cc.setJt(jt);
				cc.setType("1");
				cc.setXxr(xxr);
				cc.setYytime(yytime);
				cc.setZname(zname);
				cc.setCn(cn);
				cc.setQn(qn);
				chuchangService.save(cc);
				r.setType1("1");
				ruchangService.save(r);
			} catch (Exception e) {
				continue;
			}
		}
		
		return null;
	}
	
	
	@Action(value = "/xiaozhu33")
	public String xiaozhu33() throws Exception{
		
		List<Chuchang> result = chuchangService.findBy("cn","首尔");
		
		List<Chuchang> clist = chuchangService.find("  from Chuchang where cn =  '首尔'  group by qn");
		
      //创建文件本地文件   
        String fileName = "F:\\sfData.xls";

        //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象      
        WritableWorkbook wwb = Workbook.createWorkbook(new File(fileName));
        File dbfFile = new File(fileName);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }

        for (int i = 0; i < clist.size(); i++) {
        	Chuchang c = clist.get(i);
            WritableSheet ws = wwb.createSheet(c.getQn(), i);  //创建一个可写入的工作表  

            //添加excel表头
            ws.addCell(new Label(0, 0, "序号")); 
            ws.addCell(new Label(1, 0, "中文店名"));
            ws.addCell(new Label(2, 0, "韩文店名"));
            ws.addCell(new Label(3, 0, "中文地址"));
            ws.addCell(new Label(4, 0, "韩文地址"));
            ws.addCell(new Label(5, 0, "营业时间"));
            ws.addCell(new Label(6, 0, "休息日"));
            ws.addCell(new Label(7, 0, "交通"));
            
            List<Chuchang> clists = chuchangService.find("  from Chuchang where cn =  '首尔' and qu = '"+c.getQn()+"' " );
            for (int j = 0; j < clists.size(); j++) {
                Chuchang chu = result.get(j);

				//将生成的单元格添加到工作表中 
				//（这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行）     
                ws.addCell(new Label(0, j+1, ""+j));
                ws.addCell(new Label(1, j+1, chu.getZname()));
                ws.addCell(new Label(2, j+1, chu.getHname()));
                ws.addCell(new Label(3, j+1, chu.getAddress()));
                ws.addCell(new Label(4, j+1, chu.getHaddress()));
                ws.addCell(new Label(5, j+1, chu.getYytime()));
                ws.addCell(new Label(6, j+1, chu.getXxr()));
                ws.addCell(new Label(7, j+1, chu.getJt()));
            }
        }
        wwb.write();//从内存中写入文件中   
        wwb.close();//关闭资源，释放内存      
		
		
		return null;
	}
	
	public static  Document getDocument (String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	/**
	 * 
	 * 方法名: getMessageByType</br>
	 * 详述:根据类型、id获取系统消息列表 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-7-21</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getMessageByType")
	public String getMessageByType() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		String pageNo=getRequest().getParameter("pageNo");
		String pageSize=getRequest().getParameter("pageSize");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(type)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}else{
			if(StringUtils.isBlank(type)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
				JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
				writerMessageForJson(json);
				return null;
			}
		}
		
		
		List<ExpressageMessage> emList = new ArrayList<ExpressageMessage>();
		pageMessage.setStart(Integer.parseInt(pageNo)*Integer.parseInt(pageSize));
		pageMessage.setLimit(Integer.parseInt(pageSize));
		if(type.equals("1")){
			String userId = null;
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				userId = getUserIdByToken(token,"1");
			}else{
				userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
			}
			
			expressageMessageService.findPage(pageMessage," from ExpressageMessage where userId = ? and status in (1,2) order by createDate desc", userId);
			emList = pageMessage.getResult();
			
		}else if(type.equals("2")){
			//登录验证
			isLogin(token,"2");
			String courierId = getUserIdByToken(token,"2");
			expressageMessageService.findPage(pageMessage," from ExpressageMessage where courierId = ? and status in (3,4) order by createDate desc", courierId);
			emList = pageMessage.getResult();
		}
		List<MessageExpressage> meList=new ArrayList<MessageExpressage>();
		MessageExpressage me;
		for (int i = 0; i < emList.size(); i++) {
			ExpressageMessage em = emList.get(i);
			if(em.getStatus().equals("2")){
				em.setStatus("1");
				expressageMessageService.save(em);
			}else if(em.getStatus().equals("4")){
				em.setStatus("3");
				expressageMessageService.save(em);
			}
			
			me = expressageMessageToMessageExpressage(em);
			meList.add(me);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(meList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getMessageNumByType</br>
	 * 详述:未读消息数量 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-9-10</br>
	 * @return
	 * @throws Exception
	 * 公司 ：广州亚乐恒网络科技有限公司(http://www.aleven.cn/)</br>
	 */
	@Action(value = "/getMessageNumByType")
	public String getMessageNumByType() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		String channel = getRequest().getParameter("channel");
		
		//条件过滤
		if(StringUtils.isBlank(channel)){
			if(StringUtils.isBlank(token)||StringUtils.isBlank(type)){
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
		
		
		Object obj = "0";
		if(type.equals("1")){
			String userId = null;
			if(StringUtils.isBlank(channel)){
				isLogin(token,"1");
				userId = getUserIdByToken(token,"1");
			}else{
				userId = (String)getRequest().getSession().getAttribute("u_id");
				if(StringUtils.isBlank(userId)){
					JSONObject json = BaseJsonPrint.SynthesisJson("100022", "账号登录过期，请重新登录", null);
					writerMessageForJson(json);
					return null;
				}
			}
			
			//登录验证
			obj = expressageMessageService.find("select count(*) from ExpressageMessage where userId = ? and status = '2' ",userId);
			
		}else if(type.equals("2")){
			//登录验证
			isLogin(token,"2");
			String courierId = getUserIdByToken(token,"2");
			obj = expressageMessageService.find("select count(*) from ExpressageMessage where courierId = ? and status = '4' ",courierId);
		}
		
		JSONObject obj1 = new JSONObject();
		obj1.accumulate("messageNum", String.valueOf(obj).replace("[", "").replace("]", ""));
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj1);
		writerMessageForJson(json);
		return null;
	}
	
	
	/**
	 * 
	 * 方法名: deleteMessageById</br>
	 * 详述: 删除系统消息</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-8-3</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/deleteMessageById")
	public String deleteMessageById() throws Exception{
		String token = getRequest().getParameter("token");
		String type = getRequest().getParameter("type");
		String messageId = getRequest().getParameter("messageId");
		if(StringUtils.isBlank(token)||StringUtils.isBlank(type)||StringUtils.isBlank(messageId)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		if(type.equals("1")){
			//登录验证
			isLogin(token,"1");
			String userId = getUserIdByToken(token,"1");
			ExpressageMessage em = expressageMessageService.findUnique(" from ExpressageMessage where messageId = ? and userId = ? ", messageId,userId);
			if(em == null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
				writerMessageForJson(json);
				return null;
			}
			expressageMessageService.delete(em);
		}else if(type.equals("2")){
			//登录验证
			isLogin(token,"2");
			String courierId = getUserIdByToken(token,"2");
			ExpressageMessage em = expressageMessageService.findUnique(" from ExpressageMessage where messageId = ? and courierId = ? ", messageId,courierId);
			if(em == null){
				JSONObject json = BaseJsonPrint.SynthesisJson("100006", "网络异常，请稍后再试", null);
				writerMessageForJson(json);
				return null;
			}
			expressageMessageService.delete(em);
		}
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", null);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: getCitys</br>
	 * 详述:定位城市列表 </br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-7-21</br>
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/getCitys")
	public String getCitys() throws Exception{
		
		List<ExpressageCity> meList=expressageCityService.find(" from ExpressageCity where cityNo = '101010' ");
		
		JSONArray jsonArray = JSONArray.fromObject(meList);
		JSONObject json = BaseJsonPrint.SynthesisJsonArray("000000", "成功", jsonArray);
		writerMessageForJson(json);
		return null;
	}
	
	@Action(value = "/baiduApi")
	public String baiduApi() throws Exception{
		String latitude = getRequest().getParameter("latitude");
		String longitude = getRequest().getParameter("longitude");
		if(StringUtils.isBlank(longitude)||StringUtils.isBlank(latitude)){
			JSONObject json = BaseJsonPrint.SynthesisJson("100004", "参数不能为空", null);
			writerMessageForJson(json);
			return null;
		}
		
		String url = "http://api.map.baidu.com/geoconv/v1/";
		String body = "coords="+longitude+","+latitude+"&from=1&to=5&ak=V8Ip2ilrtSfIf8y4mof2xv1R0o2NQThG";
		String str = PushUtil.invokingSMS(body, url);
		
		JSONObject jsonobject = JSONObject.fromObject(str);
		String status = jsonobject.getString("status");
		if(!status.equals("0")){
			JSONObject json = BaseJsonPrint.SynthesisJson("000001", "定位失败", null);
			writerMessageForJson(json);
			return null;
		}
		JSONArray result = jsonobject.getJSONArray("result");
		System.out.println(result);
		JSONObject addressComponent = (JSONObject)result.get(0);
		
		String x = addressComponent.getString("x");
		String y = addressComponent.getString("y");
		
		String location = y+","+x;
		String url1 = "http://api.map.baidu.com/geocoder/v2/";
		String body1 = "ak=V8Ip2ilrtSfIf8y4mof2xv1R0o2NQThG&callback=renderReverse&location="+location+"&output=json&pois=0";
		String str1 = PushUtil.invokingSMS(body1, url1);
		
		JSONObject jsonobject1 = JSONObject.fromObject(str1);
		
		String status1 = jsonobject1.getString("status");
		if(!status1.equals("0")){
			JSONObject json = BaseJsonPrint.SynthesisJson("000001", "定位失败", null);
			writerMessageForJson(json);
			return null;
		}
		JSONObject result1 = jsonobject1.getJSONObject("result");
		JSONObject addressComponent1 = result1.getJSONObject("addressComponent");
		String province = addressComponent1.getString("province");
		String city = addressComponent1.getString("city");
		String district = addressComponent1.getString("district");
		String street = addressComponent1.getString("street");
		
		JSONObject obj = new JSONObject();
		obj.accumulate("province", province.replace("省", ""));
		obj.accumulate("city", city.replace("市", ""));
		obj.accumulate("district", district.replace("区", "").replace("县", ""));
		obj.accumulate("street", street);
		
		JSONObject json = BaseJsonPrint.SynthesisJson("000000", "成功", obj);
		writerMessageForJson(json);
		return null;
	}
	
	/**
	 * 
	 * 方法名: expressageMessageToMessageExpressage</br>
	 * 详述: 模型转换</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-7-21</br>
	 * @return
	 */
	public MessageExpressage expressageMessageToMessageExpressage(ExpressageMessage em){
		MessageExpressage me = new MessageExpressage();
		me.setContent(em.getContent());
		me.setCourierId(em.getCourierId());
		me.setMessageId(em.getMessageId());
		me.setStatus(em.getStatus());
		me.setTitle(em.getTitle());
		me.setUserId(em.getUserId());
		
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		me.setCreateDate(dd.format(em.getCreateDate()));
		
		return me;
	}
	
	
	/**
	 * 
	 * 方法名: isLogin</br>
	 * 详述: 验证登录</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-7-2</br>
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
	
	
	public static void main(String[] args)  throws Exception{
		int a = 8;
		int b = 3;
		int c = a/b;
		
		System.out.println(Math.ceil(2.6666666666666665*100)/100);
	}
	
	/**
	 * 
	 * 方法名: getUserIdByToken</br>
	 * 详述: 根据token获取用户id或快递员id</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2016-7-2</br>
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
		return expressageMessage;
	}
	@Override
	protected IBaseService getManager() {
		return expressageMessageService;
	}
	@Override
	public Object getModel() {
		return getExpressageMessage();
	}
	@Override
	public void setModel(Object obj) {
		setExpressageMessage((ExpressageMessage) obj);
	}
	public ExpressageMessage getExpressageMessage() {
		return expressageMessage;
	}
	public void setExpressageMessage(ExpressageMessage expressageMessage) {
		this.expressageMessage = expressageMessage;
	}
	
	
	
	
}
