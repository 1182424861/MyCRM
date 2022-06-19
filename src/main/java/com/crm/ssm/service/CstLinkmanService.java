package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstLinkman;

public interface CstLinkmanService {

	/**
	 * ���ݿͻ���Ų�ѯ�ͻ���ϵ��
	 * 
	 */
	List<CstLinkman> findCstLinkmanByNo(String custNo);
	
	/**
	 * ����lkmId��ѯ��ϵ��
	 * 
	 */
	CstLinkman findCstLkmById(int lkmId);
	
	/**
	 * �༭��ϵ��
	 * 
	 */
	int updateLinkman(CstLinkman cstLinkman);
	
	/**
	 * ɾ����ϵ��
	 * 
	 */
	int deleteLinkman(int lkmId);
	
	/**
	 * �����ϵ��
	 * 
	 */
	int insertLinkman(CstLinkman cstLinkman);
	
	
}
