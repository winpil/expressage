package com.cndatacom.common.utils;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SmsTools {
    
    public static final String accountsid = "04c2fd36d81c9eceeaa180187c633c73";
    public static final String authtoken = "0080a45247ecc67884ae090a70944da0";
    public static final String resturl = "https://api.ucpaas.com";
    public static final String appId = "d641c799d41d4c35903ca77a897d9a0d";
    public static final String version = "2014-06-30";
    public static final String apikey = "9d8289dd04864c6b2f83cc1d167ba026";
    public static final String smsUrl = "https://sms.yunpian.com/v2/sms/single_send.json";
    
    public static  HttpResponse post(DefaultHttpClient client, String uri, String body)
            throws Exception {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        String authorization = getAuthorization();
        httpPost.setHeader("Authorization", authorization);
        if (body != null && body.length() > 0) {
            BasicHttpEntity entity = new BasicHttpEntity();
            entity.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
            entity.setContentLength(body.getBytes().length);
            httpPost.setEntity(entity);
        }
        HttpResponse response = client.execute(httpPost);
        return response;

    }
    
    public static String checkNum(String to,String content,String templeteId) {
        String resp = "fuck me";
        DefaultHttpClient client = new DefaultHttpClient();
        String uri = getSmsRestUri();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", appId);
        map.put("templateId", templeteId);
        map.put("to", to);
        map.put("param", content);

        String body = BaseJsonPrint.beanToJson(map);
        
        body = "{\"templateSMS\":" + body + "}";
        System.out.println("body================="+body);
        try {
            HttpResponse response = post(client, uri, body);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                resp = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }

        return resp;
    }
    
    public static JSONObject singleSend(String text, String mobile) {
        List<BasicNameValuePair> pairList = new ArrayList<>();
        pairList.add(new BasicNameValuePair("apikey", apikey));
        pairList.add(new BasicNameValuePair("text", text));
        pairList.add(new BasicNameValuePair("mobile", mobile));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(60000));
        httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(60000));
        HttpPost method = new HttpPost(smsUrl);
        String str = "";
        JSONObject jsonResult = null;
        try {
          method.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
          HttpResponse result = httpClient.execute(method);

          str = EntityUtils.toString(result.getEntity());

          jsonResult = JSONObject.fromObject(str);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          httpClient.getConnectionManager().shutdown();
        }
        return jsonResult;
      }

    private String getparam() {
        return param;
    }

    private String param;

    private void setParam() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        sb.append(",").append("2");
        param = sb.toString();

    }

    /**
     * * POST/2014-06-30/Accounts/e03bc9106c6ed0eaebfce8c368fdcd48/Messages
     * /templateSMS?sig=769190B9A223549407D2164CAE92152E //
     * /{SoftVersion}/Accounts/{accountSid}/Messages/templateSMS
     * 
     * @return
     */
    private static String getSmsRestUri() {
        StringBuffer sb = new StringBuffer();
        String url = sb.append(resturl).append("/").append(version).append("/")
                .append("Accounts").append("/").append(accountsid)
                .append("/Messages/templateSMS").append("?sig=")
                .append(getSigParameter()).toString();
        return url;
    }
	
    String SigParameter = getSigParameter();

    /**
     * <pre>
     *   SigParameter是REST API 验证参数
     *  ◾ URL后必须带有sig参数，sig= MD5（账户Id + 账户授权令牌 + 时间戳），共32位(注:转成大写)
     *   ◾ 使用MD5加密（账户Id + 账户授权令牌 + 时间戳），共32位
     *   ◾ 时间戳是当前系统时间（24小时制），格式“yyyyMMddHHmmss”。时间戳有效时间为50分钟。
     * </pre>
     * 
     * @return
     */
    private static String getSigParameter() {
        String timeStamp = DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmss");
        String sig="";
        try {
            sig = MD5Service.encryptString(accountsid + authtoken + timeStamp).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sig.toUpperCase();// 转大写
    }
    
    /**
     * <pre>
     * 3. Authorization是包头验证信息
     * ◾ 使用Base64编码（账户Id + 冒号 + 时间戳） 
     * ◾ 冒号为英文冒号
     * ◾ 时间戳是当前系统时间（24小时制），格式“yyyyMMddHHmmss”，需与SigParameter中时间戳相同。
     * </pre>
     * 
     * @return
     * @throws Exception
     */
    private static String getAuthorization() throws Exception {
        String src = accountsid + ":" + DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmss");
        String authorization = PushUtil.getBase64(src);

        return authorization;
    }
    
    
    
}
