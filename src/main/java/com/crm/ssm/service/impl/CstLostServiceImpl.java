package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstLost;
import com.crm.ssm.entity.CstLostExample;
import com.crm.ssm.entity.CstLostExample.Criteria;
import com.crm.ssm.mapper.CstLostMapper;
import com.crm.ssm.service.CstLostService;

@Service
public class CstLostServiceImpl implements CstLostService{

	//调用数据层
	@Autowired
	private CstLostMapper cstLostMapper;

	//查询所有客户流失信息
	@Override
	public List<CstLost> findAllLost() {
		return cstLostMapper.selectByExample(null);
	}

	//模糊查询客户流失信息
	@Override
	public List<CstLost> findLikeCstLost(String lstCustName, String lstCustManagerName, String lstStatus) {
		CstLostExample lostExample = new CstLostExample();
		Criteria createCriteria = lostExample.createCriteria();
		if(lstCustName!="") {
			createCriteria.andLstCustNameLike("%"+lstCustName+"%");
		}
		if(lstCustManagerName!="") {
			createCriteria.andLstCustManagerNameLike("%"+lstCustManagerName+"%");
		}
		if(lstStatus!="") {
			createCriteria.andLstStatusEqualTo(lstStatus);
		}
		List<CstLost> list = cstLostMapper.selectByExample(lostExample);
		return list;
	}
	
	//判断是否已确认流失
	public CstLost findLostByNoAndStatus(Integer lstId){
		CstLostExample clExample = new CstLostExample();
		clExample.createCriteria().andLstIdEqualTo(lstId)
								  .andLstStatusEqualTo("3");
		return cstLostMapper.selectByExample(clExample).get(0);
	}

	//根据lstId查询客户流失信息
	@Override
	public CstLost findCstLostByLstId(int lstId) {
		return cstLostMapper.selectByPrimaryKey(lstId);
	}

	//追加暂缓措施
	@Override
	public int insertLstDelay(CstLost cstLost) {
		return cstLostMapper.updateByPrimaryKeySelective(cstLost);
	}

	//修改状态，添加流失原因，及确定流失时间【修改】
	@Override
	public int updateLstReason(CstLost cstLost) {
		return cstLostMapper.updateByPrimaryKeySelective(cstLost);
	}
	
	//按客户名称和客户经理名称模糊查询客户流失信息
	@Override
	public List<CstLost> selectCstLostLike(String lstCustName,String lstCustManagerName) {
		CstLostExample lostExample = new CstLostExample();
		lostExample.createCriteria().andLstCustNameLike("%"+lstCustName+"%")
									.andLstCustManagerNameLike("%"+lstCustManagerName+"%")
									.andLstStatusEqualTo("3");
		return cstLostMapper.selectByExample(lostExample);
	}
	
}
