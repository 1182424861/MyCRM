package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SalPlan;

public interface SalPlanService {

	/**
	 * ����plaChcId��ѯ���ۻ�����Ϣ
	 * 
	 */
	List<SalPlan> findSalPlanById(Integer plaChcId);
	
	/**
	 * �޸ļƻ���
	 * 
	 */
	boolean updateSalPlanTodo(SalPlan salPlan);
	
	/**
	 * ɾ���ƻ�
	 * 
	 */
	boolean deleteSalPlan(int plaId);
	
	/**
	 * ��Ӽƻ�
	 * 
	 */
	boolean addSalPlan(SalPlan salPlan);
	
	
	
}
