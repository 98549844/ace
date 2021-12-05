package com.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname: DataBaseMapper
 * @Date: 6/12/2021 3:53 上午
 * @Author: garlam
 * @Description:
 */

@Component
public interface DataBaseMapper {

    List<String> getAllTableName(String schema);

}

