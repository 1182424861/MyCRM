package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstActivity;

public interface CstActivityService {

	/**
	 * 根据客户编号查询活动信息
	 * 
	 */
	List<CstActivity> findCstActivityByNo(String custNo);
	
	/**
	 * 根据atvId查询活动信息
	 * 
	 */
	CstActivity findCstActivityById(int lkmId);
	
	/**
	 * 编辑活动信息
	 * 
	 */
	int updateActivity(CstActivity cstActivity);
	
	/**
	 * 删除活动信息
	 * 
	 */
	int deleteActivity(int lkmId);
	
	/**
	 * 添加活动
	 * 
	 */
	int insertActivity(CstActivity cstActivity);
	
}
