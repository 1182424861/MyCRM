package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Storage;

public interface StorageService {

	/**
	 * ģ����ѯ��Ʒ���
	 * @param prodName
	 * @param stkWarehouse
	 * @return
	 */
	List<Storage> findStorageLike(String prodName,String stkWarehouse);
	
}
