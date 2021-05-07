package com.dao.hibernate;

import com.entity.dao.hibernate.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TestDao extends JpaRepository<TestEntity, Long>, JpaSpecificationExecutor<TestEntity> {

    @Query("select t from TestEntity t where t.id = :id")
    TestEntity findById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("update TestEntity t set t.userName = :#{#testEntity.userName} ,t.userId=:#{#testEntity.userId} , t.email =:#{#testEntity.email}  where t.id=:#{#testEntity.id}")
    int update(@Param("testEntity") TestEntity testEntity);

    @Modifying
    @Transactional
    @Query("delete from TestEntity t where t.id=:id")
    int insert(@Param("id") Integer id);

    @Modifying
    @Transactional
    <S extends TestEntity> List<S> saveAll(Iterable<S> testList);

    @Modifying
    @Transactional
    <S extends TestEntity> S save(S testEntity);


}
