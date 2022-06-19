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
	//调用数据层
	@Autowired
	private SalChanceMapper salChanceMapper;
	
	//查找所有销售机会列表
	@Override
	public List<SalChance> findSalChance() {
		return salChanceMapper.selectByExample(null);
	}
	
	//根据chcId查询销售机会
	@Override
	public SalChance findSalChanceById(int chcId) {
		return salChanceMapper.selectByPrimaryKey(chcId);
	}
	
	//添加销售机会
	@Override
	public boolean addSalChance(SalChance salChance) {
		int insertNum = salChanceMapper.insert(salChance);
		if(insertNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//删除销售机会
	@Override
	public boolean delSalChance(int chcId) {
		int deleteNum = salChanceMapper.deleteByPrimaryKey(chcId);
		if(deleteNum>0) {
			return true;
		}else {
			return false;
		}
	}

	//修改销售机会
	@Override
	public boolean updateSalChance(SalChance salChance) {
		int updateNum = salChanceMapper.updateByPrimaryKeySelective(salChance);
		if(updateNum>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//模糊+条件查询客户开发机会
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
	
	
	//查询开发状态
	@Override
	public int findSalStatus(Integer chcId) {
		SalChance salChance = salChanceMapper.selectByPrimaryKey(chcId);
		return salChance.getChcStatus();
	}
	
	
}
