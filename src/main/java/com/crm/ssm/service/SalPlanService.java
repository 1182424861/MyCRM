package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SalPlan;

public interface SalPlanService {

	/**
	 * 根据plaChcId查询销售机会信息
	 * 
	 */
	List<SalPlan> findSalPlanById(Integer plaChcId);
	
	/**
	 * 修改计划项
	 * 
	 */
	boolean updateSalPlanTodo(SalPlan salPlan);
	
	/**
	 * 删除计划
	 * 
	 */
	boolean deleteSalPlan(int plaId);
	
	/**
	 * 添加计划
	 * 
	 */
	boolean addSalPlan(SalPlan salPlan);
	
	
	
}
