package com.ace.service;

import com.ace.dao.RRWebDao;
import com.ace.models.entity.RRWebEvents;
import com.ace.utilities.DateTimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    /** 包含大数据eventData
     * @param userAccount
     * @param uuid
     * @return
     */
    public List<RRWebEvents> findByUserAccountAndUuidOrderByCreatedByAsc(String userAccount, String uuid) {
       return rrWebDao.findByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
    }

    /** 不包含大数据eventData
     * @param userAccount
     * @param uuid
     * @return
     */
    public List<RRWebEvents> getByUserAccountAndUuidOrderByCreatedByAsc(String userAccount, String uuid) {
        List<Map<String, Object>> objecList = rrWebDao.getByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
        return getRRWebWithOutEventData(objecList);
    }


    /** 不包含大数据eventData
     * @return
     */
    public List<RRWebEvents> getByHeads() {
        List<Map<String, Object>> objecList = rrWebDao.getBySerialOrderByCreatedByAsc();
        return getRRWebWithOutEventData(objecList);
    }


    /**
     * List<Map<String, Object>> 转换成 List<RRWebEvents> , 不包含EventData
     *
     * @param objectList
     * @return events
     */
    private List<RRWebEvents> getRRWebWithOutEventData(List<Map<String, Object>> objectList) {
        List<RRWebEvents> events = new ArrayList<>();
        for (Map<String, Object> obj : objectList) {
            RRWebEvents event = new RRWebEvents();
            event.setEventId((Long) obj.get("eventId"));
            event.setCreatedBy((Long) obj.get("createdBy"));
            event.setCreatedDate(DateTimeUtil.toLocalDateTime((Timestamp) obj.get("createdDate")));
            event.setLastUpdateDate(DateTimeUtil.toLocalDateTime((Timestamp) obj.get("lastUpdateDate")));
            event.setLastUpdatedBy((Long) obj.get("lastUpdatedBy"));
            event.setVersion((Integer) obj.get("version"));
            event.setUserAccount((String) obj.get("userAccount"));
            event.setUserName((String) obj.get("userName"));
            event.setUserId((Long) obj.get("userId"));
            event.setUuid((String) obj.get("uuid"));
            event.setSerial((Integer) obj.get("serial"));
            event.setRecorder((String) obj.get("recorder"));
            events.add(event);
        }
        return events;
    }

}

