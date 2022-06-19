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
	//�������ݲ�
	@Autowired
	private CstCustomerMapper customerMapper;
	
	//��ѯ���пͻ���Ϣ
	@Override
	public List<CstCustomer> findAllCustomer() {
		List<CstCustomer> list = customerMapper.findCstSvrAll();
		return list;
	}

	
	//ģ����ѯ+������ѯ�ͻ���Ϣ��customerPage.html��
	@Override
	public List<CstCustomer> findLikeCustomer(String custName,String custNo,String custManagerName,String custRegion,Integer custLevel){
		 List<CstCustomer> list = customerMapper.findCstSvrLike(custName, custNo, custRegion, custManagerName, custLevel);
		 return list;
	}

	//����custNo��ѯ�ͻ���Ϣ
	@Override
	public CstCustomer findCstCustomerById(String custNo) {
		return customerMapper.selectByPrimaryKey(custNo);
	}

	//�޸Ŀͻ���Ϣ
	@Override
	public int updateCstCustomer(CstCustomer cstCustomer) {
		return customerMapper.updateByPrimaryKeySelective(cstCustomer);
	}

	//ɾ��
	@Override
	public int deleteCstCustomerById(String custNo) {
		return customerMapper.deleteByPrimaryKey(custNo);
	}

	//�������Ʋ�ѯ���
	@Override
	public List<CstCustomer> findCustNoByName(String custName) {
		CstCustomerExample cstExample = new CstCustomerExample();
		cstExample.createCriteria().andCustNameEqualTo(custName);
		return customerMapper.selectByExample(cstExample);
	}

	//���ͻ����Ʒ��鲢���ÿ���ͻ��Ķ����ܶ�,���ó����к���ʾ
	@Override
	public List<CstCustomer> findCustNameCount(String custName,String svrCreateDate) {
		return customerMapper.findCount(custName,svrCreateDate);
	}

	//���ͻ��ȼ��������öȻ��������ͳ�ƿͻ�������level=1��ʾ���ͻ��ȼ���level=2��ʾ���ͻ����öȣ�level=3��ʾ���ͻ�����ȡ�
	public List<CstCustomer> findCustNameCountNum(Integer level) {
		return customerMapper.findCountNum(level);
	}
	

	
	
}
