package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SysUser;

public interface UserService {

	/**
	 * ��¼
	 * @return �û�����
	 */
	SysUser login(String name,String pwd);
	
	
	/**
	 * ��ѯ�ɷ����˭
	 * 
	 */
	List<SysUser> findSalChanceUser(Integer userId);
	
	/**
	 * ����UserName��ѯUserId
	 * 
	 */
	SysUser findByName(String userName);
	
}
