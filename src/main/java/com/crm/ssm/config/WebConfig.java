package com.crm.ssm.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * ����springģ��
 * @author RqؼMax
 *
 */
@Configuration
@ComponentScan("com.crm.ssm.controller")
@EnableWebMvc//����Mvcע��
public class WebConfig implements WebMvcConfigurer{
	//������̬����
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	//ģ�������
	@Bean
	public ITemplateResolver templateResolver(ApplicationContext ctx){
		//����Springģ�������
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		//����������
		templateResolver.setApplicationContext(ctx);
		//����ǰ׺
		templateResolver.setPrefix("/");
		//���ú�׺
		templateResolver.setSuffix(".html");
		//����ģ������
		templateResolver.setTemplateMode(TemplateMode.HTML);
		//���û��桾������false��������true��
		templateResolver.setCacheable(true);
		//���ñ���
		templateResolver.setCharacterEncoding("UTF-8");
		//����
		return templateResolver;
	}

	//ģ������
	@Bean
	public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
		//����Springģ������
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		//֧��spEL(Spring��EL���ʽ)
		templateEngine.setEnableSpringELCompiler(false);
		//����ģ�������
		templateEngine.setTemplateResolver(templateResolver);
		//����
		return templateEngine;
	}

	//thymeleaf��ͼ������
	@Bean
	public ViewResolver viewResolver(ISpringTemplateEngine templateEngine){
		//Thymeleaf����ͼ������
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		//��Tymeleaf����ģ������
		viewResolver.setTemplateEngine(templateEngine);
		//���ñ���
		viewResolver.setCharacterEncoding("UTF-8");
		//����
		return viewResolver;
	}
}
