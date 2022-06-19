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

	//�������ݲ�
	@Autowired
	private CstLostMapper cstLostMapper;

	//��ѯ���пͻ���ʧ��Ϣ
	@Override
	public List<CstLost> findAllLost() {
		return cstLostMapper.selectByExample(null);
	}

	//ģ����ѯ�ͻ���ʧ��Ϣ
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
	
	//�ж��Ƿ���ȷ����ʧ
	public CstLost findLostByNoAndStatus(Integer lstId){
		CstLostExample clExample = new CstLostExample();
		clExample.createCriteria().andLstIdEqualTo(lstId)
								  .andLstStatusEqualTo("3");
		return cstLostMapper.selectByExample(clExample).get(0);
	}

	//����lstId��ѯ�ͻ���ʧ��Ϣ
	@Override
	public CstLost findCstLostByLstId(int lstId) {
		return cstLostMapper.selectByPrimaryKey(lstId);
	}

	//׷���ݻ���ʩ
	@Override
	public int insertLstDelay(CstLost cstLost) {
		return cstLostMapper.updateByPrimaryKeySelective(cstLost);
	}

	//�޸�״̬�������ʧԭ�򣬼�ȷ����ʧʱ�䡾�޸ġ�
	@Override
	public int updateLstReason(CstLost cstLost) {
		return cstLostMapper.updateByPrimaryKeySelective(cstLost);
	}
	
	//���ͻ����ƺͿͻ���������ģ����ѯ�ͻ���ʧ��Ϣ
	@Override
	public List<CstLost> selectCstLostLike(String lstCustName,String lstCustManagerName) {
		CstLostExample lostExample = new CstLostExample();
		lostExample.createCriteria().andLstCustNameLike("%"+lstCustName+"%")
									.andLstCustManagerNameLike("%"+lstCustManagerName+"%")
									.andLstStatusEqualTo("3");
		return cstLostMapper.selectByExample(lostExample);
	}
	
}
