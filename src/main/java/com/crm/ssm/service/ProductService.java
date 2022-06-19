package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Product;

public interface ProductService {

	/**
	 * ��ѯ���в�Ʒ
	 * @return
	 */
	List<Product> findProduct();
	
	/**
	 * ģ����ѯ��Ʒ
	 * @param prodName
	 * @param prodType
	 * @param prodBatch
	 * @return
	 */
	List<Product> findProductLikes(String prodName,String prodType,String prodBatch);
	
}
