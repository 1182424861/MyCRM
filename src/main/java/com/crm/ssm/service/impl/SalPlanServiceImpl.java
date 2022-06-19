package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.SalPlan;
import com.crm.ssm.entity.SalPlanExample;
import com.crm.ssm.mapper.SalPlanMapper;
import com.crm.ssm.service.SalPlanService;

@Service
public class SalPlanServiceImpl implements SalPlanService{

	//�������ݲ�
	@Autowired
	private SalPlanMapper salPlanMapper;
	
	//����plaChcId��ѯ���ۻ�����Ϣ
	@Override
	public List<SalPlan> findSalPlanById(Integer plaChcId) {
		SalPlanExample example = new SalPlanExample();
		example.createCriteria().andPlaChcIdEqualTo(plaChcId);
		return salPlanMapper.selectByExample(example);
	}

	//�޸ļƻ���
	@Override
	public boolean updateSalPlanTodo(SalPlan salPlan) {
		int updateNum = salPlanMapper.updateByPrimaryKeySelective(salPlan);
		if(updateNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//�޸ļƻ���
	@Override
	public boolean deleteSalPlan(int plaId) {
		int deleteNum = salPlanMapper.deleteByPrimaryKey(plaId);
		if(deleteNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//��Ӽƻ�
	@Override
	public boolean addSalPlan(SalPlan salPlan) {
		int insertNum = salPlanMapper.insert(salPlan);
		if(insertNum>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
