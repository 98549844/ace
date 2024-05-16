package com.ace.service;

import com.ace.dao.AceLogsDao;
import com.ace.models.entity.AceLogs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Classname: AceLogsService
 * @Date: 16/5/24 AM1:07
 * @Author: garlam
 * @Description:
 */


@Service
public class AceLogsService {
    private static final Logger log = LogManager.getLogger(AceLogsService.class.getName());

    private final AceLogsDao aceLogsDao;

    public AceLogsService(AceLogsDao aceLogsDao) {
        this.aceLogsDao = aceLogsDao;
    }

    public void save(AceLogs aceLogs) {
        aceLogsDao.save(aceLogs);
    }

    public List<AceLogs> find(AceLogs aceLogs) {
         return aceLogsDao.findAll((Specification<AceLogs>) aceLogs);
    }

}

