package com.cndatacom.rbac.system.web.action;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cndatacom.common.web.struts2.Struts2Utils;
import com.cndatacom.rbac.pojo.ExpressageCity;
import com.cndatacom.rbac.pojo.ExpressageDistrict;
import com.cndatacom.rbac.system.service.ExpressageCityService;
import com.cndatacom.rbac.system.service.ExpressageDistrictService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 类名: AreaQueryAction</br> 
 * 包名：com.cndatacom.rbac.system.web.action </br> 
 * 描述: 级联查询，此类只与ajax交互，无需继承CrudActionSupport类</br>
 * 发布版本号：</br>
 * 开发人员： 陈协</br>
 * 创建时间： 2015-10-13
 */


@Controller
@Action("areaQueryAction")
@Scope("prototype")
@Namespace("/rbac/sys")
@SuppressWarnings("serial")
public class AreaQueryAction extends ActionSupport {
	
	@Resource
	private ExpressageCityService expressageCityService;
	@Resource
	private ExpressageDistrictService expressageDistrictService;
	
	public void districtJson() {
		
		String cityId = Struts2Utils.getParameter("citylId");

		Iterator<ExpressageDistrict> iterator = expressageDistrictService.getDistrictByCityId(cityId).iterator();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"district\":[");
		while(iterator.hasNext()){
			ExpressageDistrict expressageDistrict = iterator.next();
			sb.append("{\"id\":\"").append(expressageDistrict.getDistrictId()).append("\" ,").append("\"name\":\"").append(expressageDistrict.getName()).append("\"},");
		}
		sb.deleteCharAt(sb.length()-1).append("]}");
		
//		System.out.println(sb.toString());
		
		Struts2Utils.renderJson(sb.toString(), "encoding:UTF-8");
		
	}
	
	public void cityJson() {
		
		String provincialId = Struts2Utils.getParameter("provincialId");
		
		
		Iterator<ExpressageCity> iterator = expressageCityService.getCityByProvincialId(provincialId).iterator();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"city\":[");
		while(iterator.hasNext()){
			ExpressageCity expressageCity = iterator.next();
			sb.append("{\"id\":\"").append(expressageCity.getCityId()).append("\" ,").append("\"name\":\"").append(expressageCity.getName()).append("\"},");
		}
		sb.deleteCharAt(sb.length()-1).append("]}");
		
//		System.out.println(sb.toString());
		
		Struts2Utils.renderJson(sb.toString(), "encoding:UTF-8");
		
	}
	
	//以下代码返回xml格式
	
	/*
	public void districtInfo(){
		
		String cityId = Struts2Utils.getParameter("cityId");
		
		Iterator<ExpressageDistrict> iterator = expressageDistrictService.getDistrictByCityId(cityId).iterator();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<city>");
		while(iterator.hasNext()){
			ExpressageDistrict expressageDistrict = iterator.next();
			sb.append("<districtId>");
			sb.append(expressageDistrict.getDistrictId());
			sb.append("</districtId>");
			sb.append("<districtName>");
			sb.append(expressageDistrict.getName());
			sb.append("</districtName>");
		}
		sb.append("</city>");
		
		Struts2Utils.renderXml(sb.toString(), "encoding:UTF-8");
		
	}

	public void cityInfo() {
		
		String provincialId = Struts2Utils.getParameter("provincialId");
		
		Iterator<ExpressageCity> iterator = expressageCityService.getCityByProvincialId(provincialId).iterator();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<province>");
		while(iterator.hasNext()){
			ExpressageCity expressageCity = iterator.next();
			sb.append("<cityId>");
			sb.append(expressageCity.getCityId());
			sb.append("</cityId>");
			sb.append("<cityName>");
			sb.append(expressageCity.getName());
			sb.append("</cityName>");
		}
		sb.append("</province>");
		
		Struts2Utils.renderXml(sb.toString(), "encoding:UTF-8");
	}
	*/

}
