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
 * 配置spring模板
 * @author Rq丶Max
 *
 */
@Configuration
@ComponentScan("com.crm.ssm.controller")
@EnableWebMvc//开启Mvc注解
public class WebConfig implements WebMvcConfigurer{
	//开启静态访问
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	//模板解析器
	@Bean
	public ITemplateResolver templateResolver(ApplicationContext ctx){
		//创建Spring模板解析器
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		//设置上下文
		templateResolver.setApplicationContext(ctx);
		//设置前缀
		templateResolver.setPrefix("/");
		//设置后缀
		templateResolver.setSuffix(".html");
		//设置模板类型
		templateResolver.setTemplateMode(TemplateMode.HTML);
		//设置缓存【开发：false；生产：true】
		templateResolver.setCacheable(true);
		//设置编码
		templateResolver.setCharacterEncoding("UTF-8");
		//返回
		return templateResolver;
	}

	//模板引擎
	@Bean
	public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
		//创建Spring模板引擎
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		//支持spEL(Spring的EL表达式)
		templateEngine.setEnableSpringELCompiler(false);
		//设置模板解析器
		templateEngine.setTemplateResolver(templateResolver);
		//返回
		return templateEngine;
	}

	//thymeleaf视图解析器
	@Bean
	public ViewResolver viewResolver(ISpringTemplateEngine templateEngine){
		//Thymeleaf的视图解析器
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		//给Tymeleaf设置模板引擎
		viewResolver.setTemplateEngine(templateEngine);
		//设置编码
		viewResolver.setCharacterEncoding("UTF-8");
		//返回
		return viewResolver;
	}
}
