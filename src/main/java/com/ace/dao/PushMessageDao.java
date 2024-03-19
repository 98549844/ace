package com.ace.dao;

import com.ace.models.entity.PushMessage;
import com.ace.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Classname: PushMessageDao
 * @Date: 19/3/2024 8:47 pm
 * @Author: garlam
 * @Description:
 */


public interface PushMessageDao extends JpaRepository<PushMessage, Long>, JpaSpecificationExecutor<PushMessage> {


}

