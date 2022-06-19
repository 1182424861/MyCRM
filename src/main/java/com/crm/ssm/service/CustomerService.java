package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstCustomer;

public interface CustomerService {

	/**
	 * ��ѯ���пͻ���Ϣ
	 * 
	 */
	List<CstCustomer> findAllCustomer();
	
	/**
	 * ģ��+������ѯ�ͻ���Ϣ��customerPage.html��
	 * @param custName �ͻ�����,custNo �ͻ����,custRegion ����,custManagerName �ͻ�����,custLevel �ͻ��ȼ�
	 */
	List<CstCustomer> findLikeCustomer(String custName,String custNo,String custRegion,String custManagerName,Integer custLevel);
	
	/**
	 * ����custNo��ѯ�ͻ���Ϣ
	 * 
	 */
	CstCustomer findCstCustomerById(String custNo);
	
	/**
	 * �޸Ŀͻ���Ϣ
	 * 
	 */
	int updateCstCustomer(CstCustomer cstCustomer);
	
	/**
	 * ɾ��
	 * 
	 */
	int deleteCstCustomerById(String custNo);
	
	/**
	 * �������Ʋ�ѯ���
	 */
	List<CstCustomer> findCustNoByName(String custName);
	
	/**
	 * ���ͻ����Ʒ��鲢���ÿ���ͻ��Ķ����ܶ�,���ó����к���ʾ
	 */
	List<CstCustomer> findCustNameCount(String custName,String svrCreateDate);
	
	/**
	 * ���ͻ��ȼ��������öȻ��������ͳ�ƿͻ�������level=1��ʾ���ͻ��ȼ���level=2��ʾ���ͻ����öȣ�level=3��ʾ���ͻ�����ȡ�
	 * @param level
	 * @return
	 */
	List<CstCustomer> findCustNameCountNum(Integer level);
	
}
