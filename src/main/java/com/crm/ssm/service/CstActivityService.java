package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstActivity;

public interface CstActivityService {

	/**
	 * ���ݿͻ���Ų�ѯ���Ϣ
	 * 
	 */
	List<CstActivity> findCstActivityByNo(String custNo);
	
	/**
	 * ����atvId��ѯ���Ϣ
	 * 
	 */
	CstActivity findCstActivityById(int lkmId);
	
	/**
	 * �༭���Ϣ
	 * 
	 */
	int updateActivity(CstActivity cstActivity);
	
	/**
	 * ɾ�����Ϣ
	 * 
	 */
	int deleteActivity(int lkmId);
	
	/**
	 * ��ӻ
	 * 
	 */
	int insertActivity(CstActivity cstActivity);
	
}
