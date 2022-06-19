package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.SalChance;

public interface SalChanceService {

	/**
	 * �����������ۻ����б�
	 * @return
	 */
	List<SalChance> findSalChance();
	
	/**
	 * ����chcId��ѯ���ۻ���
	 * 
	 */
	SalChance findSalChanceById(int chcId);
	
	/**
	 * ������ۻ���
	 * 
	 */
	boolean addSalChance(SalChance salChance);
	
	/**
	 * ɾ�����ۻ���
	 * 
	 */
	boolean delSalChance(int chcId);
	
	/**
	 * �޸����ۻ���
	 * 
	 */
	boolean updateSalChance(SalChance salChance);
	
	/**
	 * ģ����ѯ���ۻ���
	 * @param chcCustName �ͻ�����,chcLinkman ��ϵ��,chcTitle ��Ҫ,chcStatus ״̬
	 */
	List<SalChance> findLikeSalChance(String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus);
	
	/**
	 * ��ѯ����״̬
	 * 
	 */
	int findSalStatus(Integer chcId);
	
}
