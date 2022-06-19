package com.crm.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	
	/**
	 * 在控制器执行之前会自动调用的方法
	 * 返回ture就表示放行
	 * 返回false就表示拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle run...");
		
		//判断是否登录
		if(request.getSession().getAttribute("user") != null){
			//登录成功，放行
			return true;
		}else{
			//登录失败，拦截，并回到登录页面
			response.sendRedirect("login.html");
			return false;
		}
	}
	
//	/**
//	 * 在控制器执行之后会自动调用的方法
//	 */
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle run...");
//	}
//	
//	/**
//	 * 在控制器全部执行完成会自动调用的方法
//	 */
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("afterCompletion run...");
//	}
}
