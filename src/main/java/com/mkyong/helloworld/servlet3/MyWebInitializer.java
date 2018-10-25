package com.mkyong.helloworld.servlet3;

import com.mkyong.helloworld.config.HashicorpConfig;
import com.mkyong.helloworld.config.RefreshConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mkyong.helloworld.config.SpringRootConfig;
import com.mkyong.helloworld.config.SpringWebConfig;

public class MyWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {  HashicorpConfig.class, SpringRootConfig.class, RefreshConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}