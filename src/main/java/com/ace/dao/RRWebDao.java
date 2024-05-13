package com.ace.dao;

import com.ace.models.entity.RRWebEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @Classname: RRWebDao
 * @Date: 12/5/24 PM10:51
 * @Author: garlam
 * @Description:
 */
public interface RRWebDao extends JpaRepository<RRWebEvents, Long>, JpaSpecificationExecutor<RRWebEvents> {

    @Query(nativeQuery = true, value = "select eventId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, userAccount, userName, userId, uuid, serial from rrweb_events where userAccount = :#{#userAccount} and uuid = :#{#uuid} order by createdDate asc")
    List<Map<String, Object>> getByUserAccountAndUuidOrderByCreatedByAsc(@Param("userAccount") String userAccount, @Param("uuid") String uuid);


    @Query(nativeQuery = true, value = "select eventId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, userAccount, userName, userId, uuid, serial from rrweb_events where serial = 1 order by createdDate asc")
    List<Map<String, Object>> getBySerialOrderByCreatedByAsc();

}
