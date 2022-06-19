package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstLost;

public interface CstLostService {

	/**
	 * ��ѯ���пͻ���ʧ��Ϣ
	 * 
	 */
	List<CstLost> findAllLost();
	
	/**
	 * ģ����ѯ�ͻ���ʧ��Ϣ
	 * @param lstCustName �ͻ�����,lstCustManagerName ��������,lstStatus ״̬
	 */
	List<CstLost> findLikeCstLost(String lstCustName,String lstCustManagerName,String lstStatus);
	
	/**
	 * �ж��Ƿ���ȷ����ʧ
	 * 
	 */
	CstLost findLostByNoAndStatus(Integer lstId);
	
	/**
	 * ����lstId��ѯ�ͻ���ʧ��Ϣ
	 * 
	 */
	CstLost findCstLostByLstId(int lstId);
	
	/**
	 * ׷���ݻ���ʩ�����ӡ�
	 * 
	 */
	int insertLstDelay(CstLost cstLost);
	
	/**
	 * �޸�״̬�������ʧԭ�򣬼�ȷ����ʧʱ�䡾�޸ġ�
	 * 
	 */
	int updateLstReason(CstLost cstLost);
	
	/**
	 * ���ͻ����ƺͿͻ���������ģ����ѯ�ͻ���ʧ��Ϣ
	 * @param lstCustName
	 * @param lstCustManagerName
	 * @return
	 */
	List<CstLost> selectCstLostLike(String lstCustName,String lstCustManagerName);
	
}
