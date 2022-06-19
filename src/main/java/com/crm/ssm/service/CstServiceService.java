package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstService;

public interface CstServiceService {
	
	/**
	 * ͳ��ÿ���������͵ķ�������
	 * @param svrCreateDate
	 * @return
	 */
	List<CstService> selectSvrCountNum(String svrCreateDate);

	/**
	 * �½�����
	 * @param cstService
	 * @return
	 */
	int insertCstService(CstService cstService);
	
	/**
	 * ��ѯ���з���
	 * @return
	 */
	List<CstService> findAllCstService();
	
	/**
	 * ģ����ѯ+������ѯ����
	 * @param svrCustName
	 * @param svrTitle
	 * @param svrType
	 * @param createDate1
	 * @param createDate2
	 * @param svrStatus
	 * @return
	 */
	List<CstService> findLikeCstService(String svrCustName,String svrTitle
			,String svrType,String createDate1,String createDate2,String svrStatus);
	
	/**
	 * �޸ķ���
	 * @param cstService
	 * @return int
	 */
	int updateCstService(CstService cstService);
	
	/**
	 * ɾ������
	 * @param svrId
	 * @return
	 */
	int deleteCstService(Integer svrId);
	
	/**
	 * ����svrId��ѯ�ͻ�������Ϣ
	 * @param svrId
	 * @return
	 */
	CstService findCstServiceBySvrId(int svrId);
	
	
	
}
