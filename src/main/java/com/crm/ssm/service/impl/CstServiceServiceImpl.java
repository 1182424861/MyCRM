package com.crm.ssm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.CstService;
import com.crm.ssm.entity.CstServiceExample;
import com.crm.ssm.entity.CstServiceExample.Criteria;
import com.crm.ssm.mapper.CstServiceMapper;
import com.crm.ssm.service.CstServiceService;

@Service
public class CstServiceServiceImpl implements CstServiceService{

	//�������ݲ�
	@Autowired
	private CstServiceMapper cstServiceMapper;
	
	//ͳ��ÿ���������͵ķ�������
	public List<CstService> selectSvrCountNum(String svrCreateDate){
		return cstServiceMapper.findSvrCountNum(svrCreateDate);
	}
	
	
	//�½�����
	public int insertCstService(CstService cstService) {
		return cstServiceMapper.insertSelective(cstService);
	}
	
	//��ѯ���з���
	public List<CstService> findAllCstService() {
		return cstServiceMapper.selectByExample(null);
	}
	
	//ģ����ѯ+������ѯ����
	public List<CstService> findLikeCstService(String svrCustName,String svrTitle
			,String svrType,String createDate1,String createDate2,String svrStatus) {
		CstServiceExample svrExample = new CstServiceExample();
		Criteria createCriteria = svrExample.createCriteria();
		createCriteria.andSvrCustNameLike("%"+svrCustName+"%")
					  .andSvrTitleLike("%"+svrTitle+"%");
		if(svrType!="") {
			createCriteria.andSvrTypeEqualTo(svrType);	
		}
		if(createDate1!="" && createDate2!="") {
			try {
				createCriteria.andSvrCreateDateBetween(new SimpleDateFormat("yyyy-MM-dd").parse(createDate1),new SimpleDateFormat("yyyy-MM-dd").parse(createDate2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(svrStatus!="") {
			createCriteria.andSvrStatusEqualTo(svrStatus);
		}
		return cstServiceMapper.selectByExample(svrExample);
	}
	
	//�޸ķ���
	public int updateCstService(CstService cstService) {
		return cstServiceMapper.updateByPrimaryKeySelective(cstService);
	}
	
	//ɾ������
	public int deleteCstService(Integer svrId) {
		return cstServiceMapper.deleteByPrimaryKey(svrId);
	}
	
	//����svrId��ѯ�ͻ�������Ϣ
	public CstService findCstServiceBySvrId(int svrId) {
		return cstServiceMapper.selectByPrimaryKey(svrId);
	}
	
	
}
