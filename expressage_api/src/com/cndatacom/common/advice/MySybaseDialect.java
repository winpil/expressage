package com.cndatacom.common.advice;

import java.sql.Types;

import org.hibernate.Hibernate;

/**
 * 目的：解决org.hibernate.MappingException: No Dialect mapping for JDBC type: -1异常
 * 原因：出现这个问题的原因是 通过 Hibernate  createSQLQuery() 方法进行查询，对应表中的列有 text类型的，方言导致的。
 * 解决方法：自已建一个方言，继承于MySQLDialect ，引入 registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
 * @author DENGLONG
 *
 */
public class MySybaseDialect extends org.hibernate.dialect.SybaseDialect{
	public MySybaseDialect(){
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}
