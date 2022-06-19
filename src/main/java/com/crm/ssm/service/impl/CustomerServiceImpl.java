package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.entity.CstCustomerExample;
import com.crm.ssm.mapper.CstCustomerMapper;
import com.crm.ssm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	//调用数据层
	@Autowired
	private CstCustomerMapper customerMapper;
	
	//查询所有客户信息
	@Override
	public List<CstCustomer> findAllCustomer() {
		List<CstCustomer> list = customerMapper.findCstSvrAll();
		return list;
	}

	
	//模糊查询+条件查询客户信息【customerPage.html】
	@Override
	public List<CstCustomer> findLikeCustomer(String custName,String custNo,String custManagerName,String custRegion,Integer custLevel){
		 List<CstCustomer> list = customerMapper.findCstSvrLike(custName, custNo, custRegion, custManagerName, custLevel);
		 return list;
	}

	//根据custNo查询客户信息
	@Override
	public CstCustomer findCstCustomerById(String custNo) {
		return customerMapper.selectByPrimaryKey(custNo);
	}

	//修改客户信息
	@Override
	public int updateCstCustomer(CstCustomer cstCustomer) {
		return customerMapper.updateByPrimaryKeySelective(cstCustomer);
	}

	//删除
	@Override
	public int deleteCstCustomerById(String custNo) {
		return customerMapper.deleteByPrimaryKey(custNo);
	}

	//根据名称查询编号
	@Override
	public List<CstCustomer> findCustNoByName(String custName) {
		CstCustomerExample cstExample = new CstCustomerExample();
		cstExample.createCriteria().andCustNameEqualTo(custName);
		return customerMapper.selectByExample(cstExample);
	}

	//按客户名称分组并求和每个客户的订单总额,并得出序列号显示
	@Override
	public List<CstCustomer> findCustNameCount(String custName,String svrCreateDate) {
		return customerMapper.findCount(custName,svrCreateDate);
	}

	//按客户等级或者信用度或者满意度统计客户数量【level=1表示按客户等级，level=2表示按客户信用度，level=3表示按客户满意度】
	public List<CstCustomer> findCustNameCountNum(Integer level) {
		return customerMapper.findCountNum(level);
	}
	

	
	
}
