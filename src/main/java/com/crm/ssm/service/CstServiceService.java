package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.CstService;

public interface CstServiceService {
	
	/**
	 * 统计每个服务类型的服务数量
	 * @param svrCreateDate
	 * @return
	 */
	List<CstService> selectSvrCountNum(String svrCreateDate);

	/**
	 * 新建服务
	 * @param cstService
	 * @return
	 */
	int insertCstService(CstService cstService);
	
	/**
	 * 查询所有服务
	 * @return
	 */
	List<CstService> findAllCstService();
	
	/**
	 * 模糊查询+条件查询服务
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
	 * 修改服务
	 * @param cstService
	 * @return int
	 */
	int updateCstService(CstService cstService);
	
	/**
	 * 删除服务
	 * @param svrId
	 * @return
	 */
	int deleteCstService(Integer svrId);
	
	/**
	 * 根据svrId查询客户服务信息
	 * @param svrId
	 * @return
	 */
	CstService findCstServiceBySvrId(int svrId);
	
	
	
}
