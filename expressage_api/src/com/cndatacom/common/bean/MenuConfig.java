package com.cndatacom.common.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ≤Àµ•≈‰÷√¿‡
 * @author yab
 */
public class MenuConfig {
	
	private String id;
	
	private String text;
	
	private List<MenuConfig> children = new ArrayList<MenuConfig>();
	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MenuConfig> getChildren() {
		return children;
	}

	public void setChildren(List<MenuConfig> children) {
		this.children = children;
	}

	public boolean getLeaf() {
        return children.size() == 0;
    }
	
	public String toString() {
		return new ReflectionToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE){
			protected boolean accept(Field field) {
				return super.accept(field);
			}
		}.toString();
	}

	
}
