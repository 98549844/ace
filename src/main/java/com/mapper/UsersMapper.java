package com.mapper;


import com.models.entity.dao.Users;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userid);

    List<Users> findAll();

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}