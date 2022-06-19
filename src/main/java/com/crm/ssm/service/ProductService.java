package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Product;

public interface ProductService {

	/**
	 * 查询所有产品
	 * @return
	 */
	List<Product> findProduct();
	
	/**
	 * 模糊查询产品
	 * @param prodName
	 * @param prodType
	 * @param prodBatch
	 * @return
	 */
	List<Product> findProductLikes(String prodName,String prodType,String prodBatch);
	
}
