package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstActivity;
import com.crm.ssm.entity.CstActivityExample;
import com.crm.ssm.mapper.CstActivityMapper;
import com.crm.ssm.service.CstActivityService;

@Service
public class CstActivityServiceImpl implements CstActivityService{

	//调用数据层
	@Autowired
	private CstActivityMapper cstActivityMapper;
	
	//根据客户编号查询活动信息
	@Override
	public List<CstActivity> findCstActivityByNo(String custNo) {
		CstActivityExample atvExample = new CstActivityExample();
		atvExample.createCriteria().andAtvCustNoEqualTo(custNo);
		return cstActivityMapper.selectByExample(atvExample);
	}

	//根据atvId查询活动信息
	@Override
	public CstActivity findCstActivityById(int atvId) {
		return cstActivityMapper.selectByPrimaryKey(atvId);
	}
	
	//编辑活动信息
	@Override
	public int updateActivity(CstActivity cstActivity) {
		return cstActivityMapper.updateByPrimaryKeySelective(cstActivity);
	}

	//删除活动信息
	@Override
	public int deleteActivity(int atvId) {
		return cstActivityMapper.deleteByPrimaryKey(atvId);
	}

	//添加活动
	@Override
	public int insertActivity(CstActivity cstActivity) {
		return cstActivityMapper.insert(cstActivity);
	}
	
	
	
}
