package com.cndatacom.common.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.cndatacom.common.utils.MD5Service;
import com.cndatacom.rbac.pojo.BaseJson;

public class BaseJsonPrint {


	/**
	 * 方法名: SynthesisJson</br>
	 * 详述: 重組json 参数： 状态码 + 提示信息  + 返回数据</br>
	 * 开发人员： lvyq Justin</br>
	 * 创建时间：2015-5-22</br>
	 * @param code  结果
	 * @param message 提示
	 * @param Response 信息 
 	 * @return
	 */
	public static JSONObject SynthesisJson(String code,String message,JSONObject Response){
		BaseJson bj = new BaseJson();
		
		bj.setResponse(Response);
		bj.setCode(code);
		bj.setMessage(message);
		
		JSONObject object = JSONObject.fromObject(bj);//json化对象
		
		return object;
	}
	
	public static JSONObject SynthesisJsonArray(String code,String message,JSONArray Response){
		JSONObject obj = new JSONObject(); 
		
		obj.accumulate("response", Response);
		obj.accumulate("code", code);
		obj.accumulate("message", message);
		
		
		return obj;
	}
	
	
	/**
	 * 
	 * 方法名: CreateToken</br>
	 * 详述: 创建token = 用户名+时间戳 -> md5加密</br>
	 * 开发人员： lvyq Justin</br>
	 * 创建时间：2015-5-25</br>
	 * @param name
	 * @param date1
	 * @return
	 */
	public static String CreateToken(String name, Date date1) throws Exception {
		 SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = dd.format(date1);
		String ctoken = name + time;
		System.out.println("ctoken = " + ctoken);
		String token = MD5Service.encryptString(ctoken);
		System.out.println("token = " + token);

		// System.out.println(MDUtils.decode(token));
		return token;
	}
	 /**  
     * 将java对象转换成json字符串  
     *
     * @param bean  
     * @return  
     */
    public static String beanToJson(Object bean) {
        JSONObject json = JSONObject.fromObject(bean);
        return json.toString();
 
    }
    /**
     * 方法名: beanToJson</br>
     * 详述: 将java对象转换成json字符串  </br>
     * 开发人员： lvyq Justin</br>
     * 创建时间：2015-5-25</br>
     * @param bean
     * @param _nory_changes
     * @param nory
     * @return
     */
    public static String beanToJson(Object bean, String[] _nory_changes, boolean nory) {
 
        JSONObject json = null;
         
        if(nory){//转换_nory_changes里的属性
             
            Field[] fields = bean.getClass().getDeclaredFields();
            String str = "";
            for(Field field : fields){
//              System.out.println(field.getName());
                str+=(":"+field.getName());
            }
            fields = bean.getClass().getSuperclass().getDeclaredFields();
            for(Field field : fields){
//              System.out.println(field.getName());
                str+=(":"+field.getName());
            }
            str+=":";
            for(String s : _nory_changes){
                str = str.replace(":"+s+":", ":");
            }
            json = JSONObject.fromObject(bean,configJson(str.split(":")));
             
        }else{//转换除了_nory_changes里的属性
             
 
             
            json = JSONObject.fromObject(bean,configJson(_nory_changes));
        }
         
        return json.toString();
 
    }
    private static JsonConfig configJson(String[] excludes) {   
        JsonConfig jsonConfig = new JsonConfig();   
        jsonConfig.setExcludes(excludes);   
        jsonConfig.setIgnoreDefaultExcludes(false);   
        return jsonConfig;   
    }   
    /**
     * 将java对象List集合转换成json字符串  
     * @param beans
     * @return
     */
    public static String beanListToJson(List beans) {
        StringBuffer rest = new StringBuffer();
         
        rest.append("[");
         
        int size = beans.size();
         
        for (int i = 0; i < size; i++) {
             
            rest.append(beanToJson(beans.get(i))+((i<size-1)?",":""));
 
        }
         
        rest.append("]");
         
        return rest.toString();
    }
    /**
     * 
     * 方法名:dateToString </br>
     * 详述:date类型转换为String类型 
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒</br>
     * 开发人员： lvyq Justin</br>
     * 创建时间：2015-5-25</br>
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
 		return new SimpleDateFormat(formatType).format(data);
 	}
    /**
     * 方法名: </br>
     * 详述: </br>
     * 开发人员： lvyq Justin</br>
     * 创建时间：2015-5-25</br>
     * @param args
     * @throws Exception
     */
    public static JSONObject JstrTdate(Object obj, Date date) {
		//定义时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String str = sdf.format(date);//格式化时间
		
		JSONObject object = JSONObject.fromObject(obj);//json化对象
		
		object.put("createDate", str);//替换json中的时间
		
		//System.out.println("jsonobject = " + object);
		
		return object;
	}
    
    public static String DateAddTime(int date){
    	
    	
    	Calendar now=Calendar.getInstance();
    	 
    	  now.add(Calendar.DAY_OF_MONTH,date);
    	 
    	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 
    	  String dateStr=sdf.format(now.getTimeInMillis());
    	 
    	  System.out.println(dateStr);
    	
    	return dateStr;
    }
    /**
     * 
     * 方法名:  Grealprice
     * 详述:  打折价格
     * 开发人员： lvyq Justin 
     * 创建时间：2015-5-29 
     * @param price
     * @param code
     * @return
     */
    public static String Grealprice(String price,String code){
    	int realprice =0;
    	if(code.equals("dancige")){
    		realprice = (int) (Integer.parseInt(price)*0.9);
    		
    		return String.valueOf(realprice);
    	}else{
    		realprice = (int) (Integer.parseInt(price)*0.8);
    	}
    	return String.valueOf(realprice);
    }
    
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 

	public static void main(String[] args) throws Exception {
		/*System.out.println(BaseJsonPrint.CreateToken("WEERREWE", new Date()));
		System.out.println(Grealprice("100","dsssancige"));
		System.out.println(BaseJsonPrint.DateAddTime(150));*/
		for(int i = 0 ; i<10 ; i++){
			System.out.println(getUUID());
		}
	}

}
