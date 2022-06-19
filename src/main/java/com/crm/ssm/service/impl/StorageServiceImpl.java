package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.Storage;
import com.crm.ssm.mapper.StorageMapper;
import com.crm.ssm.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{
	
	//�������ݲ�
	@Autowired
	private  StorageMapper storageMapper;

	//ģ����ѯ��Ʒ���
	@Override
	public List<Storage> findStorageLike(String prodName, String stkWarehouse) {
		return storageMapper.findStoProdLike(prodName, stkWarehouse);
	}
	
}
