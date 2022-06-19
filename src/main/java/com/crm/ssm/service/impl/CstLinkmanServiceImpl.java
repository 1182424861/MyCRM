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

	//�������ݲ�
	@Autowired
	private CstLinkmanMapper cstLinkmanMapper;
	
	//���ݿͻ���Ų�ѯ�ͻ���ϵ��
	@Override
	public List<CstLinkman> findCstLinkmanByNo(String custNo) {
		CstLinkmanExample lkmExample = new CstLinkmanExample();
		lkmExample.createCriteria().andLkmCustNoEqualTo(custNo);
		return cstLinkmanMapper.selectByExample(lkmExample);
	}

	//����lkmId��ѯ��ϵ��
	@Override
	public CstLinkman findCstLkmById(int lkmId) {
		return cstLinkmanMapper.selectByPrimaryKey(lkmId);
	}

	//�༭��ϵ��
	@Override
	public int updateLinkman(CstLinkman cstLinkman) {
		return cstLinkmanMapper.updateByPrimaryKeySelective(cstLinkman);
	}

	//ɾ����ϵ��
	@Override
	public int deleteLinkman(int lkmId) {
		return cstLinkmanMapper.deleteByPrimaryKey(lkmId);
	}

	//�����ϵ��
	@Override
	public int insertLinkman(CstLinkman cstLinkman) {
		return cstLinkmanMapper.insert(cstLinkman);
	}

	
	
	
	
}
