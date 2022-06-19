package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.BaseDict;

public interface BaseDictService {

	/**
	 * 查询数据字典管理
	 * 
	 */
	List<BaseDict> findBaseDict();
	
	/**
	 * 模糊查询数据字典管理信息
	 * @param dictType
	 * @param dictItem
	 * @param dictValue
	 * @return
	 */
	List<BaseDict> findBaseDictLike(String dictType,String dictItem,String dictValue);
	
	/**
	 * 新建数据字典管理信息
	 * @param baseDict
	 * @return
	 */
	int insertBaseDict(BaseDict baseDict);
	
	/**
	 * 根据dictId查询数据字典管理
	 * @param dictId 编号
	 * @return
	 */
	BaseDict findBaseDictById(int dictId);
	
	/**
	 * 修改数据字典管理信息
	 * @param baseDict
	 * @return
	 */
	int updateBaseDictById(BaseDict baseDict);
	
	/**
	 * 根据dictId删除数据字典管理信息
	 * @param dictId
	 * @return
	 */
	int deleteBaseDictById(Integer dictId);
	
	
}
