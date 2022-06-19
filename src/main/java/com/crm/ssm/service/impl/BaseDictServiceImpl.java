package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.BaseDict;
import com.crm.ssm.entity.BaseDictExample;
import com.crm.ssm.entity.BaseDictExample.Criteria;
import com.crm.ssm.mapper.BaseDictMapper;
import com.crm.ssm.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService{
	
	//调用数据层
	@Autowired
	private  BaseDictMapper baseDictMapper;

	//查询所有数据字典管理
	@Override
	public List<BaseDict> findBaseDict() {
		return baseDictMapper.selectByExample(null);
	}
	
	//模糊查询数据字典管理信息
	@Override
	public List<BaseDict> findBaseDictLike(String dictType,String dictItem,String dictValue) {
		BaseDictExample dictExample = new BaseDictExample();
		Criteria createCriteria = dictExample.createCriteria();
		if(dictType!="") {
			createCriteria.andDictTypeLike("%"+dictType+"%");
		}
		if(dictItem!="") {
			createCriteria.andDictItemLike("%"+dictItem+"%");
		}
		if(dictValue!="") {
			createCriteria.andDictValueLike("%"+dictValue+"%");
		}
		return baseDictMapper.selectByExample(dictExample);
	}
	
	//新建数据字典管理信息
	@Override
	public int insertBaseDict(BaseDict baseDict) {
		return baseDictMapper.insertSelective(baseDict);
	}
	
	//根据dictId查询数据字典管理
	@Override
	public BaseDict findBaseDictById(int dictId) {
		return baseDictMapper.selectByPrimaryKey(dictId);
	}
	
	//修改数据字典管理信息
	@Override
	public int updateBaseDictById(BaseDict baseDict) {
		return baseDictMapper.updateByPrimaryKeySelective(baseDict);
	}
	
	//根据dictId删除数据字典管理信息
	@Override
	public int deleteBaseDictById(Integer dictId) {
		return baseDictMapper.deleteByPrimaryKey(dictId);
	}
	
	
	
}
