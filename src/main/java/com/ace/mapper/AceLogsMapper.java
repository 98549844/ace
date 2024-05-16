package com.ace.mapper;

import com.ace.models.entity.AceLogs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AceLogsMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(AceLogs record);

    int insertSelective(AceLogs record);

    AceLogs selectByPrimaryKey(Long logId);

    List<AceLogs> selectAll();

    int updateByPrimaryKeySelective(AceLogs record);

    Long updateByPrimaryKey(AceLogs record);
}
