package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstLinkman;
import com.crm.ssm.entity.CstLinkmanExample;
import com.crm.ssm.mapper.CstLinkmanMapper;
import com.crm.ssm.service.CstLinkmanService;

@Service
public class CstLinkmanServiceImpl implements CstLinkmanService{

	//调用数据层
	@Autowired
	private CstLinkmanMapper cstLinkmanMapper;
	
	//根据客户编号查询客户联系人
	@Override
	public List<CstLinkman> findCstLinkmanByNo(String custNo) {
		CstLinkmanExample lkmExample = new CstLinkmanExample();
		lkmExample.createCriteria().andLkmCustNoEqualTo(custNo);
		return cstLinkmanMapper.selectByExample(lkmExample);
	}

	//根据lkmId查询联系人
	@Override
	public CstLinkman findCstLkmById(int lkmId) {
		return cstLinkmanMapper.selectByPrimaryKey(lkmId);
	}

	//编辑联系人
	@Override
	public int updateLinkman(CstLinkman cstLinkman) {
		return cstLinkmanMapper.updateByPrimaryKeySelective(cstLinkman);
	}

	//删除联系人
	@Override
	public int deleteLinkman(int lkmId) {
		return cstLinkmanMapper.deleteByPrimaryKey(lkmId);
	}

	//添加联系人
	@Override
	public int insertLinkman(CstLinkman cstLinkman) {
		return cstLinkmanMapper.insert(cstLinkman);
	}

	
	
	
	
}
