/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Page.java,v 1.1 2013/05/20 03:46:38 py Exp $
 */
package com.cndatacom.common.orm;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * �����ORMʵ���޹صķ�ҳ��������ѯ�����װ.
 * ע��������Ŵ�1��ʼ.
 * 
 * @param <T> Page�м�¼������.
 * 
 * @author calvin
 */
public class Page<T> {
	//-- �������� --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	//-- ��ҳ���� --//
	protected int pageNo = 1;
	protected int pageSize = 1;
	protected int start;
	protected int limit;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	//-- ���ؽ�� --//
	protected List<T> result = Lists.newArrayList();
	protected long totalCount = -1;
	
	private boolean success=true;

	//-- ���캯�� --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	//-- ���ʲ�ѯ�������� --//
	/**
	 * ��õ�ǰҳ��ҳ��,��Ŵ�1��ʼ,Ĭ��Ϊ1.
	 */
	public int getPageNo() {
		return start/limit+1;
	}

	/**
	 * ���õ�ǰҳ��ҳ��,��Ŵ�1��ʼ,����1ʱ�Զ�����Ϊ1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	/**
	 * ���õ�ǰҳ��ҳ��,��Ŵ�1��ʼ,����1ʱ�Զ�����Ϊ1.
	 */
	public void setPageStart(final int pageStart) {
		this.pageNo = pageStart / pageSize + 1;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * ���ÿҳ�ļ�¼����,Ĭ��Ϊ1.
	 */
	public int getPageSize() {
		return limit;
	}

	/**
	 * ����ÿҳ�ļ�¼����,����1ʱ�Զ�����Ϊ1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * ����pageNo��pageSize���㵱ǰҳ��һ����¼���ܽ�����е�λ��,��Ŵ�1��ʼ.
	 */
	public int getFirst() {
		//((pageNo - 1) * pageSize) + 1;
		return start;
	}

	/**
	 * ��������ֶ�,��Ĭ��ֵ.��������ֶ�ʱ��','�ָ�.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * ���������ֶ�,��������ֶ�ʱ��','�ָ�.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * ���������.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * ��������ʽ��.
	 * 
	 * @param order ��ѡֵΪdesc��asc,��������ֶ�ʱ��','�ָ�.
	 */
	public void setOrder(final String order) {
		//���order�ַ����ĺϷ�ֵ
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("������" + orderStr + "���ǺϷ�ֵ");
			}
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * �Ƿ������������ֶ�,��Ĭ��ֵ.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * ��ѯ����ʱ�Ƿ��Զ�����ִ��count��ѯ��ȡ�ܼ�¼��, Ĭ��Ϊfalse.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * ��ѯ����ʱ�Ƿ��Զ�����ִ��count��ѯ��ȡ�ܼ�¼��.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	//-- ���ʲ�ѯ������� --//

	/**
	 * ȡ��ҳ�ڵļ�¼�б�.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * ����ҳ�ڵļ�¼�б�.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * ȡ���ܼ�¼��, Ĭ��ֵΪ-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * �����ܼ�¼��.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * ����pageSize��totalCount������ҳ��, Ĭ��ֵΪ-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		/*long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;*/
		
		long count = totalCount / this.getPageSize();
		if (totalCount % this.getPageSize() > 0) {
			count++;
		}
		return count;
	}

	/**
	 * �Ƿ�����һҳ.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * ȡ����ҳ��ҳ��, ��Ŵ�1��ʼ.
	 * ��ǰҳΪβҳʱ�Է���βҳ���.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * �Ƿ�����һҳ.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * ȡ����ҳ��ҳ��, ��Ŵ�1��ʼ.
	 * ��ǰҳΪ��ҳʱ������ҳ���.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
