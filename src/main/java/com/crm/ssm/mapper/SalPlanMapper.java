package com.crm.ssm.mapper;

import com.crm.ssm.entity.SalPlan;
import com.crm.ssm.entity.SalPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalPlanMapper {
    long countByExample(SalPlanExample example);

    int deleteByExample(SalPlanExample example);

    int deleteByPrimaryKey(Integer plaId);

    int insert(SalPlan record);

    int insertSelective(SalPlan record);

    List<SalPlan> selectByExample(SalPlanExample example);

    SalPlan selectByPrimaryKey(Integer plaId);

    int updateByExampleSelective(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByExample(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByPrimaryKeySelective(SalPlan record);

    int updateByPrimaryKey(SalPlan record);
}