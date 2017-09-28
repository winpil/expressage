package com.cndatacom.common.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented  
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.FIELD)  
public @interface Status {
	com.cndatacom.common.utils.ElementType value() default 	com.cndatacom.common.utils.ElementType.STATUS;  
}
