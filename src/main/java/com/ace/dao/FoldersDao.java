package com.ace.dao;

import com.ace.models.entity.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FoldersDao extends JpaRepository<Folders, Long>, JpaSpecificationExecutor<Folders> {


    Folders findByPath(String folder);


    List<Folders> findByOwnerId(Long ownerId);
}
