package com.service;

import com.dao.UsersDao;
import com.entity.dao.Test;
import com.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Classname: usersService
 * @Date: 1/7/2021 10:00 下午
 * @Author: garlam
 * @Description:
 */


public class usersService {
    private static Logger log = LogManager.getLogger(usersService.class.getName());

    private UsersDao usersDao;

    @Autowired
    public usersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    /**
     * @return all result
     */
    public List<Users> getAll() {
        List<Users> list = null;
        try {
            list = usersDao.findAll();
        } catch (Exception e) {
            log.error(e);
        }
        return list;
    }

    public List<Users> getTestEntities(Users users) {
        List<Users> list = null;
        try {
            Specification<Users> sp = toPredicate(users);
            list = usersDao.findAll(sp);
        } catch (Exception e) {
            log.error(e);
        }
        return list;
    }

    public Optional<Users> getUsersById(long id) {
        Optional<Users> users = usersDao.findById(id);
        if (users == null) {
            return null;
        }
        return users;
    }

    public boolean save(Users users) {
        try {
            usersDao.save(users);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }


    public boolean saveAll(Iterable<Users> usersIterable) {
        try {
            usersDao.saveAll(usersIterable);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    public boolean update(Users users) {
        try {
            if (users != null) {
                usersDao.update(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }

    public boolean delete(Users users) {
        try {
            List<Users> list = getTestEntities(users);
            for (Users user : list) {
                delete(user.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            usersDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Specification<Users> toPredicate(Users users) {
        Specification<Users> sp = (Specification<Users>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();

            if (users.getEmail() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + users.getEmail() + "%");
                predicatesList.add(predicate);
            }
            if (users.getUserName() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("userName"), users.getUserName());
                predicatesList.add(predicate);
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        return sp;
    }
}

