package com.sys.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {
	private static Log log = LogFactory.getLog(PropertiesUtil.class);

	private static Properties prop = new Properties();
	static {

		InputStream in = PropertiesUtil.class
				.getResourceAsStream("config.properties");

		try {
			prop.load(in);
		} catch (IOException e) {
			log.info("config.properties is error", e);
		}
	}

	public static String getProperty(String a) {
		return prop.getProperty(a);
	}

}
