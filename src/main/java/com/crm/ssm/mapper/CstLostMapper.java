package com.crm.ssm.mapper;

import com.crm.ssm.entity.CstLost;
import com.crm.ssm.entity.CstLostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CstLostMapper {
    long countByExample(CstLostExample example);

    int deleteByExample(CstLostExample example);

    int deleteByPrimaryKey(Integer lstId);

    int insert(CstLost record);

    int insertSelective(CstLost record);

    List<CstLost> selectByExample(CstLostExample example);

    CstLost selectByPrimaryKey(Integer lstId);

    int updateByExampleSelective(@Param("record") CstLost record, @Param("example") CstLostExample example);

    int updateByExample(@Param("record") CstLost record, @Param("example") CstLostExample example);

    int updateByPrimaryKeySelective(CstLost record);

    int updateByPrimaryKey(CstLost record);
}