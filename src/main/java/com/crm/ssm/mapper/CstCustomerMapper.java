package com.crm.ssm.mapper;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.entity.CstCustomerExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CstCustomerMapper {
    long countByExample(CstCustomerExample example);

    int deleteByExample(CstCustomerExample example);

    int deleteByPrimaryKey(String custNo);

    int insert(CstCustomer record);

    int insertSelective(CstCustomer record);

    List<CstCustomer> selectByExample(CstCustomerExample example);

    CstCustomer selectByPrimaryKey(String custNo);

    int updateByExampleSelective(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByExample(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByPrimaryKeySelective(CstCustomer record);

    int updateByPrimaryKey(CstCustomer record);
    
    //��������
    List<CstCustomer> findCstSvrAll ();
    
    //ģ����ѯ+������ѯ+�����ѯ
    List<CstCustomer> findCstSvrLike(@Param("custName")String custName,@Param("custNo")String custNo,@Param("custRegion")String custRegion,@Param("custManagerName")String custManagerName,@Param("custLevel")Integer custLevel);
    
    //���ͻ����Ʒ��鲢���ÿ���ͻ��Ķ����ܶ�,���ó����к���ʾ���������顿
    List<CstCustomer> findCount(@Param("custName")String custName,@Param("svrCreateDate")String svrCreateDate);
    
    //���ͻ��ȼ��������öȻ��������ͳ�ƿͻ�������level=1��ʾ���ͻ��ȼ���level=2��ʾ���ͻ����öȣ�level=3��ʾ���ͻ�����ȡ�
    List<CstCustomer> findCountNum(@Param("level")Integer level);
    
}