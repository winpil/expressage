package com.cndatacom.common.advice;

import java.sql.Types;

import org.hibernate.Hibernate;

/**
 * Ŀ�ģ����org.hibernate.MappingException: No Dialect mapping for JDBC type: -1�쳣
 * ԭ�򣺳�����������ԭ���� ͨ�� Hibernate  createSQLQuery() �������в�ѯ����Ӧ���е����� text���͵ģ����Ե��µġ�
 * ������������ѽ�һ�����ԣ��̳���MySQLDialect ������ registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
 * @author DENGLONG
 *
 */
public class MySybaseDialect extends org.hibernate.dialect.SybaseDialect{
	public MySybaseDialect(){
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}
