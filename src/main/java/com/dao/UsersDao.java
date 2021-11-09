package com.dao;

import com.models.entity.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersDao extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {


    @Modifying
    @Query("update Users t set t.userName = :#{#users.userName} , t.email =:#{#users.email}, t.mobile = :#{#users.mobile}  where t.userId=:#{#users.userId}")
    int update(@Param("users") Users users);
}
