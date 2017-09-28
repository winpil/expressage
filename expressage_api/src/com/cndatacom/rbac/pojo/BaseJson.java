package com.cndatacom.rbac.pojo;

import net.sf.json.JSONObject;

/**
 * 
 * ����: BaseJson</br> 
 * ������com.cndatacom.rbac.pojo </br> 
 * ����: </br>
 * �����汾�ţ�</br>
 * ������Ա�� ������</br>
 * ����ʱ�䣺 2015-6-1
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
