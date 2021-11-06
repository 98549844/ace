package com.dao;

import com.models.entity.dao.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogDao extends JpaRepository<AccessLog, Long>, JpaSpecificationExecutor<AccessLog> {


}
