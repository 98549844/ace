package com.dao;

import com.entity.dao.AccessLog;
import com.entity.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {


}
