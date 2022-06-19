package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SysUser;

public interface UserService {

	/**
	 * 登录
	 * @return 用户对象
	 */
	SysUser login(String name,String pwd);
	
	
	/**
	 * 查询可分配给谁
	 * 
	 */
	List<SysUser> findSalChanceUser(Integer userId);
	
	/**
	 * 根据UserName查询UserId
	 * 
	 */
	SysUser findByName(String userName);
	
}
