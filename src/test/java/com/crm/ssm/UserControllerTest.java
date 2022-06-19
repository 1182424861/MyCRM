package com.crm.ssm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.crm.ssm.config.SpringConfig;
import com.crm.ssm.service.UserService;
import com.crm.ssm.entity.SysUser;


/*
 * ≤‚ ‘¿‡
 */

public class UserControllerTest {

	@SuppressWarnings("resource")
	@Test
	public void testFindById() {
		
		 ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class); 
		 UserService userService = ctx.getBean(UserService.class);
		 SysUser user = userService.login("hsy","1024");
		 System.out.println(user);
		 
	}
	
}
