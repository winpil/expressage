package com.cndatacom.common.log4j;

import org.apache.log4j.PatternLayout;

public class MailPatternLayout extends PatternLayout {
	public String getContentType() { 
		return "text/plain; charset=UTF-8"; 
	} 
}
