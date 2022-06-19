package com.crm.ssm.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	//获取非SpringMVC相关的配置类类型
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SpringConfig.class};
	}

	@Override
	//获取SpringMVC相关的配置类类型 例如DispathcerServlect
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}

	
	@Override
	//相当于url-param
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		//创建编码过滤器对象
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
		//返回
		return new Filter[]{encodingFilter};
	}

}
