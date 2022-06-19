package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SalChance;

public interface SalChanceService {

	/**
	 * 查找所有销售机会列表
	 * @return
	 */
	List<SalChance> findSalChance();
	
	/**
	 * 根据chcId查询销售机会
	 * 
	 */
	SalChance findSalChanceById(int chcId);
	
	/**
	 * 添加销售机会
	 * 
	 */
	boolean addSalChance(SalChance salChance);
	
	/**
	 * 删除销售机会
	 * 
	 */
	boolean delSalChance(int chcId);
	
	/**
	 * 修改销售机会
	 * 
	 */
	boolean updateSalChance(SalChance salChance);
	
	/**
	 * 模糊查询销售机会
	 * @param chcCustName 客户名称,chcLinkman 联系人,chcTitle 概要,chcStatus 状态
	 */
	List<SalChance> findLikeSalChance(String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus);
	
	/**
	 * 查询开发状态
	 * 
	 */
	int findSalStatus(Integer chcId);
	
}
