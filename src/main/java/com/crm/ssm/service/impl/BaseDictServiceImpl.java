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
	
	//�������ݲ�
	@Autowired
	private  BaseDictMapper baseDictMapper;

	//��ѯ���������ֵ����
	@Override
	public List<BaseDict> findBaseDict() {
		return baseDictMapper.selectByExample(null);
	}
	
	//ģ����ѯ�����ֵ������Ϣ
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
	
	//�½������ֵ������Ϣ
	@Override
	public int insertBaseDict(BaseDict baseDict) {
		return baseDictMapper.insertSelective(baseDict);
	}
	
	//����dictId��ѯ�����ֵ����
	@Override
	public BaseDict findBaseDictById(int dictId) {
		return baseDictMapper.selectByPrimaryKey(dictId);
	}
	
	//�޸������ֵ������Ϣ
	@Override
	public int updateBaseDictById(BaseDict baseDict) {
		return baseDictMapper.updateByPrimaryKeySelective(baseDict);
	}
	
	//����dictIdɾ�������ֵ������Ϣ
	@Override
	public int deleteBaseDictById(Integer dictId) {
		return baseDictMapper.deleteByPrimaryKey(dictId);
	}
	
	
	
}
