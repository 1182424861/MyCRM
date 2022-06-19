package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.BaseDict;

public interface BaseDictService {

	/**
	 * ��ѯ�����ֵ����
	 * 
	 */
	List<BaseDict> findBaseDict();
	
	/**
	 * ģ����ѯ�����ֵ������Ϣ
	 * @param dictType
	 * @param dictItem
	 * @param dictValue
	 * @return
	 */
	List<BaseDict> findBaseDictLike(String dictType,String dictItem,String dictValue);
	
	/**
	 * �½������ֵ������Ϣ
	 * @param baseDict
	 * @return
	 */
	int insertBaseDict(BaseDict baseDict);
	
	/**
	 * ����dictId��ѯ�����ֵ����
	 * @param dictId ���
	 * @return
	 */
	BaseDict findBaseDictById(int dictId);
	
	/**
	 * �޸������ֵ������Ϣ
	 * @param baseDict
	 * @return
	 */
	int updateBaseDictById(BaseDict baseDict);
	
	/**
	 * ����dictIdɾ�������ֵ������Ϣ
	 * @param dictId
	 * @return
	 */
	int deleteBaseDictById(Integer dictId);
	
	
}
