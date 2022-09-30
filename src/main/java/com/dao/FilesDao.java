package com.dao;

import com.models.entity.dao.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: FilesDao
 * @Date: 2022/9/27 下午 12:34
 * @Author: kalam_au
 * @Description:
 */


@Repository
@Transactional
public interface FilesDao extends JpaRepository<Files, Long>, JpaSpecificationExecutor<Files> {

    //  void deleteByFileName(String fileName);
    List<Files> findFilesByOriginationName(String fileName);

    List<Files> findFilesByFileNameNotIn(List<String> filesName);

}
