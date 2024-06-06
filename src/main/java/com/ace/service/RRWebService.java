package com.ace.service;

import com.ace.dao.RRWebDao;
import com.ace.models.entity.RRWebEvents;
import com.ace.utilities.DateTimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    private final RRWebDao rrWebDao;

    public RRWebService(RRWebDao rrWebDao) {
        this.rrWebDao = rrWebDao;
    }


    public void save(RRWebEvents rrWebEvents) {
        rrWebDao.save(rrWebEvents);
    }

    /**
     * 包含大数据eventData
     *
     * @param userAccount
     * @param uuid
     * @return
     */
    public List<RRWebEvents> findByUserAccountAndUuidOrderByCreatedByAsc(String userAccount, String uuid) {
        return rrWebDao.findByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
    }

    /**
     * 不包含大数据eventData
     *
     * @param userAccount
     * @param uuid
     * @return
     */
    public List<RRWebEvents> getByUserAccountAndUuidOrderByCreatedByAsc(String userAccount, String uuid) {
        List<Map<String, Object>> objecList = rrWebDao.getByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
        return getRRWebWithOutEventData(objecList);
    }


    /**
     * 不包含大数据eventData
     *
     * @return
     */
    public List<RRWebEvents> getByHeads() {
        List<Map<String, Object>> objecList = rrWebDao.getBySerialOrderByCreatedByAsc();
        return getRRWebWithOutEventData(objecList);
    }

    public boolean deleteByUuid(String uuid) {
        try {
            rrWebDao.deleteByUuid(uuid);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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


    public String appendEventData(List<RRWebEvents> events) {
        StringBuilder data = new StringBuilder();
        for (RRWebEvents ev : events) {
            String eventData = ev.getEventData();
            //eventData 不大过2,表示只有中括号, 而且不含数据, 跳过拼接数据
            if (eventData.length() > 2) {
                //拆中括号"[" "]", 拼入逗号 ","
                String sub = eventData.substring(1, eventData.length() - 1) + ',';
                data.append(sub);
            }
        }
        //拼接中括号"[ ]"
        String result = "[" + data.substring(0, data.length() - 1) + "]";
        return result;
    }
}

