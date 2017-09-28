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
	 * ������: SynthesisJson</br>
	 * ����: �ؽMjson ������ ״̬�� + ��ʾ��Ϣ  + ��������</br>
	 * ������Ա�� lvyq Justin</br>
	 * ����ʱ�䣺2015-5-22</br>
	 * @param code  ���
	 * @param message ��ʾ
	 * @param Response ��Ϣ 
 	 * @return
	 */
	public static JSONObject SynthesisJson(String code,String message,JSONObject Response){
		BaseJson bj = new BaseJson();
		
		bj.setResponse(Response);
		bj.setCode(code);
		bj.setMessage(message);
		
		JSONObject object = JSONObject.fromObject(bj);//json������
		
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
	 * ������: CreateToken</br>
	 * ����: ����token = �û���+ʱ��� -> md5����</br>
	 * ������Ա�� lvyq Justin</br>
	 * ����ʱ�䣺2015-5-25</br>
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
     * ��java����ת����json�ַ���  
     *
     * @param bean  
     * @return  
     */
    public static String beanToJson(Object bean) {
        JSONObject json = JSONObject.fromObject(bean);
        return json.toString();
 
    }
    /**
     * ������: beanToJson</br>
     * ����: ��java����ת����json�ַ���  </br>
     * ������Ա�� lvyq Justin</br>
     * ����ʱ�䣺2015-5-25</br>
     * @param bean
     * @param _nory_changes
     * @param nory
     * @return
     */
    public static String beanToJson(Object bean, String[] _nory_changes, boolean nory) {
 
        JSONObject json = null;
         
        if(nory){//ת��_nory_changes�������
             
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
             
        }else{//ת������_nory_changes�������
             
 
             
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
     * ��java����List����ת����json�ַ���  
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
     * ������:dateToString </br>
     * ����:date����ת��ΪString���� 
     * formatType��ʽΪyyyy-MM-dd HH:mm:ss//yyyy��MM��dd�� HHʱmm��ss��</br>
     * ������Ա�� lvyq Justin</br>
     * ����ʱ�䣺2015-5-25</br>
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
 		return new SimpleDateFormat(formatType).format(data);
 	}
    /**
     * ������: </br>
     * ����: </br>
     * ������Ա�� lvyq Justin</br>
     * ����ʱ�䣺2015-5-25</br>
     * @param args
     * @throws Exception
     */
    public static JSONObject JstrTdate(Object obj, Date date) {
		//����ʱ���ʽ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String str = sdf.format(date);//��ʽ��ʱ��
		
		JSONObject object = JSONObject.fromObject(obj);//json������
		
		object.put("createDate", str);//�滻json�е�ʱ��
		
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
     * ������:  Grealprice
     * ����:  ���ۼ۸�
     * ������Ա�� lvyq Justin 
     * ����ʱ�䣺2015-5-29 
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
        //ȥ����-������ 
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
