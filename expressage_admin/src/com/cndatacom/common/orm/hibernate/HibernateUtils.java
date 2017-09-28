/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: HibernateUtils.java,v 1.1 2013/05/20 03:46:18 py Exp $
 */
package com.cndatacom.common.orm.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.utils.ReflectionUtils;
import com.cndatacom.common.web.ServletUtils;

/**
 * Hibernate���WebӦ�õĹ�����.
 * 
 * @author calvin
 */
public class HibernateUtils {

	private HibernateUtils() {
	}

	/**
	 * ���ݶ���ID����,�����ϲ�����.
	 * 
	 * Ĭ�϶���������������Ϊ"id".
	 * 
	 * @see #mergeByCheckedIds(Collection, Collection, Class, String)
	 */
	public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects, final Collection<ID> checkedIds,
			final Class<T> clazz) {
		mergeByCheckedIds(srcObjects, checkedIds, clazz, "id");
	}

	/**
	 * ���ݶ���ID����,�����ϲ�����.
	 * 
	 * ҳ�淢�ͱ������Ӷ���id�б�ʱ,ɾ��ԭ�����Ӷ��󼯺��ٸ���ҳ��id�б�����һ��ȫ�µļ������ֿ�����򵥵������ǲ��е�.
	 * ��˲�����˵������㷨����Դ������ɾ��id����Ŀ�꼯���еĶ���,����Ŀ�꼯���е�id�����������ӵ�Դ������.
	 * ��Ϊ�½�����ֻ��ID����ֵ, ��˱��������ʺ���cascade-save-or-update�Զ��־û��Ӷ��������.
	 * 
	 * @param srcObjects Դ����,Ԫ��Ϊ����.
	 * @param checkedIds  Ŀ�꼯��,Ԫ��ΪID.
	 * @param clazz  �����ж��������
	 * @param idName ��������������
	 */
	public static <T, ID> void mergeByCheckedIds(final Collection<T> srcObjects, final Collection<ID> checkedIds,
			final Class<T> clazz, final String idName) {

		//����У��
		Assert.notNull(srcObjects, "scrObjects����Ϊ��");
		Assert.hasText(idName, "idName����Ϊ��");
		Assert.notNull(clazz, "clazz����Ϊ��");

		//Ŀ�꼯��Ϊ��, ɾ��Դ���������ж����ֱ�ӷ���.
		if (checkedIds == null) {
			srcObjects.clear();
			return;
		}

		//����Դ����,�����id����Ŀ��ID�����еĶ���,����ɾ��.
		//ͬʱ,��Ŀ�꼯����ɾ������Դ�����е�id,ʹ��Ŀ�꼯����ʣ�µ�id��ΪԴ������û�е�id.
		Iterator<T> srcIterator = srcObjects.iterator();
		try {

			while (srcIterator.hasNext()) {
				T element = srcIterator.next();
				Object id;
				id = PropertyUtils.getProperty(element, idName);

				if (!checkedIds.contains(id)) {
					srcIterator.remove();
				} else {
					checkedIds.remove(id);
				}
			}

			//ID����Ŀǰʣ���id������Դ������,��������,Ϊid���Ը�ֵ�����ӵ�Դ������.
			for (ID id : checkedIds) {
				T obj = clazz.newInstance();
				PropertyUtils.setProperty(obj, idName, id);
				srcObjects.add(obj);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * ���ݰ�PropertyFilter���������Request����,����PropertyFilter�б�.
	 * Ĭ��Filter������ǰ׺Ϊfilter_.
	 * 
	 * @see #buildPropertyFilters(HttpServletRequest, String)
	 */
	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request) {
		return buildPropertyFilters(request, "filter_");
	}

	/**
	 * ���ݰ�PropertyFilter���������Request����,����PropertyFilter�б�.
	 * PropertyFilter��������ΪFilter����ǰ׺_�Ƚ�������������_������.
	 * 
	 * eg.
	 * filter_EQS_name
	 * filter_LIKES_name_OR_email
	 */
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		//��request�л�ȡ������ǰ׺���Ĳ���,����ȥ��ǰ׺����Ĳ���Map.
		Map<String, String> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix);

		//��������Map,����PropertyFilter�б�
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();
			//���valueֵΪ��,����Դ�filter.
			if (StringUtils.isNotBlank(value)) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return filterList;
	}
}