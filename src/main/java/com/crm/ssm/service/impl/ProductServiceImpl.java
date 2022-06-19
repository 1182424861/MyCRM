package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.Product;
import com.crm.ssm.entity.ProductExample;
import com.crm.ssm.mapper.ProductMapper;
import com.crm.ssm.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	//调用数据层
	@Autowired
	private  ProductMapper productMapper;

	//查询所有产品
	@Override
	public List<Product> findProduct() {
		return productMapper.selectByExample(null);
	}
	
	//模糊查询产品
	@Override
	public List<Product> findProductLikes(String prodName,String prodType,String prodBatch){
		ProductExample prodExample = new ProductExample();
		prodExample.createCriteria().andProdNameLike("%"+prodName+"%")
									.andProdTypeLike("%"+prodType+"%")
									.andProdBatchLike("%"+prodBatch+"%");
		return productMapper.selectByExample(prodExample);
	}
	
}
