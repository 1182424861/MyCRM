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
    
    //两表联查
    List<CstCustomer> findCstSvrAll ();
    
    //模糊查询+条件查询+两表查询
    List<CstCustomer> findCstSvrLike(@Param("custName")String custName,@Param("custNo")String custNo,@Param("custRegion")String custRegion,@Param("custManagerName")String custManagerName,@Param("custLevel")Integer custLevel);
    
    //按客户名称分组并求和每个客户的订单总额,并得出序列号显示【三表联查】
    List<CstCustomer> findCount(@Param("custName")String custName,@Param("svrCreateDate")String svrCreateDate);
    
    //按客户等级或者信用度或者满意度统计客户数量【level=1表示按客户等级，level=2表示按客户信用度，level=3表示按客户满意度】
    List<CstCustomer> findCountNum(@Param("level")Integer level);
    
}