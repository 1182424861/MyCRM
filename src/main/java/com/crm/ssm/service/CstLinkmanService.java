package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstLinkman;

public interface CstLinkmanService {

	/**
	 * 根据客户编号查询客户联系人
	 * 
	 */
	List<CstLinkman> findCstLinkmanByNo(String custNo);
	
	/**
	 * 根据lkmId查询联系人
	 * 
	 */
	CstLinkman findCstLkmById(int lkmId);
	
	/**
	 * 编辑联系人
	 * 
	 */
	int updateLinkman(CstLinkman cstLinkman);
	
	/**
	 * 删除联系人
	 * 
	 */
	int deleteLinkman(int lkmId);
	
	/**
	 * 添加联系人
	 * 
	 */
	int insertLinkman(CstLinkman cstLinkman);
	
	
}
