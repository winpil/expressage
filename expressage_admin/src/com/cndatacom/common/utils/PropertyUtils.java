/*
 * $HeadURL: https://springside.googlecode.com/svn/springside3/trunk/modules/core/src/main/java/org/springside/modules/utils/PropertyUtils.java $
 * $Id: PropertyUtils.java,v 1.1 2013/05/20 03:46:37 py Exp $
 * Copyright (c) 2010 by Ericsson, all rights reserved.
 */

package com.cndatacom.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties Util����.
 * 
 * @author calvin
 */
public class PropertyUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * ������properties�ļ�, ��ͬ���������������ļ����Ḳ��֮ǰ������.
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... locations) throws IOException {
		Properties props = new Properties();

		for (String location : locations) {

			logger.debug("Loading properties file from classpath:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}
}
