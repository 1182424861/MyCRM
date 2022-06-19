package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Storage;

public interface StorageService {

	/**
	 * 模糊查询产品库存
	 * @param prodName
	 * @param stkWarehouse
	 * @return
	 */
	List<Storage> findStorageLike(String prodName,String stkWarehouse);
	
}
