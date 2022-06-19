package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.SysUser;
import com.crm.ssm.entity.SysUserExample;
import com.crm.ssm.mapper.SysUserMapper;
import com.crm.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	//调用数据层
	@Autowired
	private SysUserMapper sysUserMapper;
	
	//登录
	@Override
	public SysUser login(String name,String pwd) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserNameEqualTo(name)
								.andUserPasswordEqualTo(pwd);
		return sysUserMapper.selectByExample(example).size()>0?sysUserMapper.selectByExample(example).get(0):null;
	}

	//查询可分配给谁【当前用户不可分配给当前用户】
	@Override
	public List<SysUser> findSalChanceUser(Integer userId) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserIdNotEqualTo(userId);
		return sysUserMapper.selectByExample(example);
	}

	//根据UserName查询UserId
	@Override
	public SysUser findByName(String userName) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserNameEqualTo(userName);
		return sysUserMapper.selectByExample(example).get(0);
	}

	
}
