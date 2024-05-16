package com.ace.dao;

import com.ace.models.entity.AceLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AceLogsDao extends JpaRepository<AceLogs, Long>, JpaSpecificationExecutor<AceLogs> {


}
