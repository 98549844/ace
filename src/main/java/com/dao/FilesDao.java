package com.dao;

import com.models.entity.dao.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

    List<Files> findFilesByOriginationName(String fileName);

    Files findFilesByFileName(String fileName);

    List<Files> findFilesByFileNameIn(List<String> fileName);

    List<Files> findFilesByFileNameInAndVersionGreaterThan(List<String> fileName, int version);

    List<Files> findFilesByFileNameInAndStatus(List<String> fileName, String status);

    List<Files> findFilesByFileNameInAndStatusOrderByCreatedDateDesc(List<String> fileName, String status);

    List<Files> findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(List<String> fileName, String status, String ownerId);

    List<Files> findFilesByFileNameNotInOrderByLastUpdateDateDesc(List<String> filesName);

    List<Files> findFilesByFileNameNotInAndPathNotIn(Collection<String> fileName, Collection<String> path);

    List<Files> findFilesByPathAndFileNameNotIn(String path, Collection<String> fileName);


}
