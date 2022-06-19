package com.crm.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	
	/**
	 * �ڿ�����ִ��֮ǰ���Զ����õķ���
	 * ����ture�ͱ�ʾ����
	 * ����false�ͱ�ʾ����
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle run...");
		
		//�ж��Ƿ��¼
		if(request.getSession().getAttribute("user") != null){
			//��¼�ɹ�������
			return true;
		}else{
			//��¼ʧ�ܣ����أ����ص���¼ҳ��
			response.sendRedirect("login.html");
			return false;
		}
	}
	
//	/**
//	 * �ڿ�����ִ��֮����Զ����õķ���
//	 */
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle run...");
//	}
//	
//	/**
//	 * �ڿ�����ȫ��ִ����ɻ��Զ����õķ���
//	 */
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("afterCompletion run...");
//	}
}
