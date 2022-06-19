package com.crm.ssm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan(basePackages={"com.crm.ssm"},excludeFilters={
		@Filter(type=FilterType.ANNOTATION,value={
				EnableWebMvc.class,Controller.class
		})
})
@MapperScan("com.crm.ssm.mapper")
@ImportResource(locations="classpath:spring.xml")
@EnableTransactionManagement //����������ע���ʶ��
public class SpringConfig {

	//��������Դ
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		//������Ҫ��
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/mycrm");
		ds.setUsername("root");
		ds.setPassword("123456");
		//��������
		ds.setMaxIdle(20);
		ds.setMinIdle(10);
		ds.setInitialSize(30);
		ds.setMaxTotal(50);
		ds.setMaxWaitMillis(10000);
		return ds;
	}

	//����Mybatis
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dateSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//��������Դ
		bean.setDataSource(dateSource);
		//����ȫ�ֱ���
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		//��������
		config.setLazyLoadingEnabled(true);
		config.setAggressiveLazyLoading(false);
		config.setMapUnderscoreToCamelCase(true);
		//������־���
		config.setLogImpl(Log4j2Impl.class);
		//���ð�ɨ��
		config.getTypeAliasRegistry().registerAliases("com.crm.entity");
		//����ȫ������
		bean.setConfiguration(config);
		//���÷�ҳ��������
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		//�������ݿⷽ�� Ϊmysql
		properties.setProperty("helperDialect", "mysql");
		//���÷�ҳ�ĺ�������
		properties.setProperty("reasonable", "true");
		pageInterceptor.setProperties(properties);
		bean.setPlugins(pageInterceptor);
		return bean.getObject();
	}
}
