package com.dao;

import com.models.entity.dao.Menus;
import com.models.entity.dao.Users;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname: MenuDao
 * @Date: 13/11/2021 12:53 上午
 * @Author: garlam
 * @Description:
 */


@Repository
@Transactional
public interface MenuDao extends JpaRepository<Menus, Long>, JpaSpecificationExecutor<Menus> {


    @Modifying
    List<Menus> getDistinctByParentId(Long rootParentId);


}
