package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstActivity;
import com.crm.ssm.entity.CstActivityExample;
import com.crm.ssm.mapper.CstActivityMapper;
import com.crm.ssm.service.CstActivityService;

@Service
public class CstActivityServiceImpl implements CstActivityService{

	//�������ݲ�
	@Autowired
	private CstActivityMapper cstActivityMapper;
	
	//���ݿͻ���Ų�ѯ���Ϣ
	@Override
	public List<CstActivity> findCstActivityByNo(String custNo) {
		CstActivityExample atvExample = new CstActivityExample();
		atvExample.createCriteria().andAtvCustNoEqualTo(custNo);
		return cstActivityMapper.selectByExample(atvExample);
	}

	//����atvId��ѯ���Ϣ
	@Override
	public CstActivity findCstActivityById(int atvId) {
		return cstActivityMapper.selectByPrimaryKey(atvId);
	}
	
	//�༭���Ϣ
	@Override
	public int updateActivity(CstActivity cstActivity) {
		return cstActivityMapper.updateByPrimaryKeySelective(cstActivity);
	}

	//ɾ�����Ϣ
	@Override
	public int deleteActivity(int atvId) {
		return cstActivityMapper.deleteByPrimaryKey(atvId);
	}

	//��ӻ
	@Override
	public int insertActivity(CstActivity cstActivity) {
		return cstActivityMapper.insert(cstActivity);
	}
	
	
	
}
