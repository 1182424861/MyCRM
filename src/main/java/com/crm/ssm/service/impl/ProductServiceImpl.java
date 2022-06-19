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

	//�������ݲ�
	@Autowired
	private  ProductMapper productMapper;

	//��ѯ���в�Ʒ
	@Override
	public List<Product> findProduct() {
		return productMapper.selectByExample(null);
	}
	
	//ģ����ѯ��Ʒ
	@Override
	public List<Product> findProductLikes(String prodName,String prodType,String prodBatch){
		ProductExample prodExample = new ProductExample();
		prodExample.createCriteria().andProdNameLike("%"+prodName+"%")
									.andProdTypeLike("%"+prodType+"%")
									.andProdBatchLike("%"+prodBatch+"%");
		return productMapper.selectByExample(prodExample);
	}
	
}
