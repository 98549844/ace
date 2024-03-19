package com.ace.service;

import com.ace.dao.PushMessageDao;
import com.ace.models.entity.PushMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname: NotificationService
 * @Date: 1/3/2024 9:09 am
 * @Author: garlam
 * @Description:
 */

@Service
public class NotificationService {
    private static final Logger log = LogManager.getLogger(NotificationService.class.getName());

    private PushMessageDao pushMessageDao;


    @Autowired
    public NotificationService(PushMessageDao pushMessageDao) {
        this.pushMessageDao = pushMessageDao;
    }

    public List<PushMessage> findAll() {
        return pushMessageDao.findAll();
    }

    public List<PushMessage> findByReceiver(String receiver) {
        return pushMessageDao.findByReceiver(receiver);
    }

    public void deleteByReceiver(String receiver) {
        pushMessageDao.deleteByReceiver(receiver);
    }

    public void deleteById(Long msgId) {
        pushMessageDao.deleteById(msgId);
    }


    public void saveAll(List<PushMessage> pushMessages) {
        pushMessageDao.saveAll(pushMessages);
    }

    public void save(PushMessage pushMessage) {
        pushMessageDao.save(pushMessage);
    }




}
