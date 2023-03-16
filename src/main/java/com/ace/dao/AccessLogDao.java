package com.ace.dao;

import com.ace.models.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogDao extends JpaRepository<AccessLog, Long>, JpaSpecificationExecutor<AccessLog> {


}
