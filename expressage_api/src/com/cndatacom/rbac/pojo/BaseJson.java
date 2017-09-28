package com.cndatacom.rbac.pojo;

import net.sf.json.JSONObject;

/**
 * 
 * 类名: BaseJson</br> 
 * 包名：com.cndatacom.rbac.pojo </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015-6-1
 */
public class BaseJson {
	private String message;
	private String code;
	private JSONObject response;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public JSONObject getResponse() {
		return response;
	}
	public void setResponse(JSONObject response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "BaseJson [message=" + message + ", code=" + code + "]";
	}
	
}
