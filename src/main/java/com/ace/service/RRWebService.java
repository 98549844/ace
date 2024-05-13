package com.ace.service;

import com.ace.dao.RRWebDao;
import com.ace.models.entity.RRWebEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @Classname: RRWebService
 * @Date: 12/5/24 PM10:51
 * @Author: garlam
 * @Description:
 */

@Service
public class RRWebService {
    private static final Logger log = LogManager.getLogger(RRWebService.class.getName());

    private RRWebDao rrWebDao;

    public RRWebService(RRWebDao rrWebDao) {
        this.rrWebDao = rrWebDao;
    }


    public void save(RRWebEvents rrWebEvents) {
        rrWebDao.save(rrWebEvents);
    }

    public List<RRWebEvents> getByUserAccountAndUuidOrderByCreatedByAsc(String userAccount, String uuid) {
        return rrWebDao.getByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
    }


    public List<RRWebEvents> getByHeads() {
        return rrWebDao.getBySerialOrderByCreatedByAsc();
    }

}

