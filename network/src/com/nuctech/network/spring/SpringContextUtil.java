/*
 * @(#)SpringContextUtil.java 2019-4-15
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**<p><br/>
 * @className SpringContextUtil.java<br/>
 * @packageName com.nuctech.network.spring<br/>
 * @date 2019-4-15 上午9:53:35<br/>
 * </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-15
 * @version v1.0.0
 */
public class SpringContextUtil {
	
	private static ApplicationContext app = null;
	public static void launch() {
		String[] contextPaths = new String[] { "application.xml" };
		app = new ClassPathXmlApplicationContext(contextPaths);
	}
	public static ApplicationContext getApplicationContext() {
		if (app == null) {
			SpringContextUtil.launch();
			return app;
		} else {
			return app;
		}
	}
	
}
