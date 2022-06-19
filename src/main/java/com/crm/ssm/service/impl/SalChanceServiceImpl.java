package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.SalChance;
import com.crm.ssm.entity.SalChanceExample;
import com.crm.ssm.entity.SalChanceExample.Criteria;
import com.crm.ssm.mapper.SalChanceMapper;
import com.crm.ssm.service.SalChanceService;

@Service
public class SalChanceServiceImpl implements SalChanceService {
	//�������ݲ�
	@Autowired
	private SalChanceMapper salChanceMapper;
	
	//�����������ۻ����б�
	@Override
	public List<SalChance> findSalChance() {
		return salChanceMapper.selectByExample(null);
	}
	
	//����chcId��ѯ���ۻ���
	@Override
	public SalChance findSalChanceById(int chcId) {
		return salChanceMapper.selectByPrimaryKey(chcId);
	}
	
	//������ۻ���
	@Override
	public boolean addSalChance(SalChance salChance) {
		int insertNum = salChanceMapper.insert(salChance);
		if(insertNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//ɾ�����ۻ���
	@Override
	public boolean delSalChance(int chcId) {
		int deleteNum = salChanceMapper.deleteByPrimaryKey(chcId);
		if(deleteNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//�޸����ۻ���
	@Override
	public boolean updateSalChance(SalChance salChance) {
		int updateNum = salChanceMapper.updateByPrimaryKeySelective(salChance);
		if(updateNum>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//ģ��+������ѯ�ͻ���������
	@Override
	public List<SalChance> findLikeSalChance(String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus) {
		SalChanceExample salChanceExample = new SalChanceExample();
		Criteria createCriteria = salChanceExample.createCriteria();
		createCriteria.andChcCustNameLike("%"+chcCustName+"%")
					  .andChcLinkmanLike("%"+chcLinkman+"%")
					  .andChcTitleLike("%"+chcTitle+"%");
		 if(chcStatus!=null) {
			 createCriteria.andChcStatusEqualTo(chcStatus);
		 }
		List<SalChance> list = salChanceMapper.selectByExample(salChanceExample);
		return list;
	}
	
	
	//��ѯ����״̬
	@Override
	public int findSalStatus(Integer chcId) {
		SalChance salChance = salChanceMapper.selectByPrimaryKey(chcId);
		return salChance.getChcStatus();
	}
	
	
}
