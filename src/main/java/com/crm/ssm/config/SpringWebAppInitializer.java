package com.crm.ssm.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	//��ȡ��SpringMVC��ص�����������
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SpringConfig.class};
	}

	@Override
	//��ȡSpringMVC��ص����������� ����DispathcerServlect
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}

	
	@Override
	//�൱��url-param
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		//�����������������
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
		//����
		return new Filter[]{encodingFilter};
	}

}
