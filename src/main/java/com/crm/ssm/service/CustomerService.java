package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstCustomer;

public interface CustomerService {

	/**
	 * 查询所有客户信息
	 * 
	 */
	List<CstCustomer> findAllCustomer();
	
	/**
	 * 模糊+条件查询客户信息【customerPage.html】
	 * @param custName 客户名称,custNo 客户编号,custRegion 地区,custManagerName 客户经理,custLevel 客户等级
	 */
	List<CstCustomer> findLikeCustomer(String custName,String custNo,String custRegion,String custManagerName,Integer custLevel);
	
	/**
	 * 根据custNo查询客户信息
	 * 
	 */
	CstCustomer findCstCustomerById(String custNo);
	
	/**
	 * 修改客户信息
	 * 
	 */
	int updateCstCustomer(CstCustomer cstCustomer);
	
	/**
	 * 删除
	 * 
	 */
	int deleteCstCustomerById(String custNo);
	
	/**
	 * 根据名称查询编号
	 */
	List<CstCustomer> findCustNoByName(String custName);
	
	/**
	 * 按客户名称分组并求和每个客户的订单总额,并得出序列号显示
	 */
	List<CstCustomer> findCustNameCount(String custName,String svrCreateDate);
	
	/**
	 * 按客户等级或者信用度或者满意度统计客户数量【level=1表示按客户等级，level=2表示按客户信用度，level=3表示按客户满意度】
	 * @param level
	 * @return
	 */
	List<CstCustomer> findCustNameCountNum(Integer level);
	
}
