package com.cndatacom.rbac.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��־��¼Annotation,�����ڷ������棬���ڴ���Serivce������ģ������
 * @author yab
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleName {
	String value();
}
