package com.mapper;

import com.entity.dao.AccessLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccessLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(Long logId);

    List<AccessLog> selectAll();


    int updateByPrimaryKeySelective(AccessLog record);

    int updateByPrimaryKey(AccessLog record);
}