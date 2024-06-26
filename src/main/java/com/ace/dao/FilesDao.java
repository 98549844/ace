package com.ace.dao;

import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //  List<Files> findFilesByOriginationName(String fileName);

    Files findFilesByFileName(String fileName);

    List<Files> findFilesByFileNameLike(String fileName);

    List<Files> findFilesByFileNameIn(List<String> fileName);

    List<Files> findFilesByFileNameInAndVersionGreaterThan(List<String> fileName, int version);

    List<Files> findFilesByFileNameInAndStatus(List<String> fileName, String status);

    List<Files> findFilesByFileNameInAndStatusOrderByCreatedDateDesc(List<String> fileName, String status, Pageable pageable);

    List<Files> findFilesByStatusOrderByCreatedDateDesc(String status, Pageable pageable);

    List<Files> findFilesByStatusOrderByOwnerAscCreatedDateDesc(String status, Pageable pageable);

    List<Files> findFilesByFileNameInAndStatusOrderByCreatedDateDesc(List<String> fileName, String status);

    List<Files> findFilesByFileNameInAndStatusInOrderByCreatedDateDesc(List<String> fileName, List<String> status);

    List<Files> findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(List<String> fileName, String status, String ownerId);

    List<Files> findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(List<String> fileName, String status, String ownerId, Pageable pageable);

    List<Files> findFilesByStatusAndOwnerOrderByCreatedDateDesc(String status, String ownerId, Pageable pageable);

    List<Files> findFilesByFileNameInAndStatusInAndOwnerOrderByCreatedDateDesc(List<String> fileName, List<String> status, String ownerId);

    List<Files> findFilesByFileNameNotInOrderByLastUpdateDateDesc(List<String> filesName);

    List<Files> findFilesByPathAndFileNameNotIn(String path, Collection<String> fileName);

    List<Files> findFilesByOwner(String owner);

    List<Files> findFilesByOwnerAndPathOrderByCreatedDate(String owner,String  videoPath);

    List<Files> findFilesByPathOrderByCreatedDate(String  videoPath);


    int countByLocation(String location);

    @Query(nativeQuery = true, value = "select distinct owner from files order by owner asc")
    List<String> getAllDistinctOwner();

    @Modifying
    @Transactional
    @Query("delete from Files fs where fs.owner = :#{#owner}")
    void deleteFilesByOwner(@Param("owner") String owner);


}
