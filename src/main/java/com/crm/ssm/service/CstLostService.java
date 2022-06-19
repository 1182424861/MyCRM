package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstLost;

public interface CstLostService {

	/**
	 * 查询所有客户流失信息
	 * 
	 */
	List<CstLost> findAllLost();
	
	/**
	 * 模糊查询客户流失信息
	 * @param lstCustName 客户名称,lstCustManagerName 经理名称,lstStatus 状态
	 */
	List<CstLost> findLikeCstLost(String lstCustName,String lstCustManagerName,String lstStatus);
	
	/**
	 * 判断是否已确认流失
	 * 
	 */
	CstLost findLostByNoAndStatus(Integer lstId);
	
	/**
	 * 根据lstId查询客户流失信息
	 * 
	 */
	CstLost findCstLostByLstId(int lstId);
	
	/**
	 * 追加暂缓措施【增加】
	 * 
	 */
	int insertLstDelay(CstLost cstLost);
	
	/**
	 * 修改状态，添加流失原因，及确定流失时间【修改】
	 * 
	 */
	int updateLstReason(CstLost cstLost);
	
	/**
	 * 按客户名称和客户经理名称模糊查询客户流失信息
	 * @param lstCustName
	 * @param lstCustManagerName
	 * @return
	 */
	List<CstLost> selectCstLostLike(String lstCustName,String lstCustManagerName);
	
}
