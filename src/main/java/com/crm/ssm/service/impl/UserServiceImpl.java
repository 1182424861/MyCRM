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
	
	//�������ݲ�
	@Autowired
	private SysUserMapper sysUserMapper;
	
	//��¼
	@Override
	public SysUser login(String name,String pwd) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserNameEqualTo(name)
								.andUserPasswordEqualTo(pwd);
		return sysUserMapper.selectByExample(example).size()>0?sysUserMapper.selectByExample(example).get(0):null;
	}

	//��ѯ�ɷ����˭����ǰ�û����ɷ������ǰ�û���
	@Override
	public List<SysUser> findSalChanceUser(Integer userId) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserIdNotEqualTo(userId);
		return sysUserMapper.selectByExample(example);
	}

	//����UserName��ѯUserId
	@Override
	public SysUser findByName(String userName) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserNameEqualTo(userName);
		return sysUserMapper.selectByExample(example).get(0);
	}

	
}
