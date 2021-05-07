package com.service;

import com.controller.AceRestController;
import com.dao.hibernate.TestDao;
import com.entity.dao.hibernate.TestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    private static Logger log = LogManager.getLogger(AceRestController.class.getName());

    @Autowired
    private TestDao testDao;

    /**
     * @return all result
     */
    public List<TestEntity> getAll() {
        List<TestEntity> list = null;
        try {
            list = testDao.findAll();
        } catch (Exception e) {
            log.error(e);
        }
        return list;
    }

    /**
     * @param testEntity obj
     * @return accord object return list
     */
    public List<TestEntity> getTestEntities(TestEntity testEntity) {
        List<TestEntity> list = null;
        try {
            Specification<TestEntity> sp = toPredicate(testEntity);
            list = testDao.findAll(sp);
        } catch (Exception e) {
            log.error(e);
        }
        return list;
    }

    public TestEntity getTestEntityById(int id) {
        TestEntity testEntity = testDao.findById(id);
        if (testEntity == null) {
            return null;
        }
        return testEntity;
    }

    /**
     * @param testEntity
     * @return
     */
    public boolean save(TestEntity testEntity) {
        try {
            testDao.save(testEntity);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * @param testEntities
     * @return
     */
    public boolean saveAll(Iterable<TestEntity> testEntities) {
        try {
            testDao.saveAll(testEntities);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    public boolean update(TestEntity testEntity) {
        try {
            if (testEntity != null) {
                testDao.update(testEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }

    /**
     * @param testEntity del by object
     * @return boolean
     */
    public boolean delete(TestEntity testEntity) {
        try {
            List<TestEntity> list = getTestEntities(testEntity);
            for (TestEntity t : list) {
                delete(t.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            testDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    private Specification<TestEntity> toPredicate(TestEntity testEntity) {
        Specification<TestEntity> sp = (Specification<TestEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();

            if (testEntity.getEmail() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + testEntity.getEmail() + "%");
                predicatesList.add(predicate);
            }
            if (testEntity.getUserId() != null) {
                Predicate predicate = criteriaBuilder.greaterThan(root.get("userId"), testEntity.getUserId());
                predicatesList.add(predicate);
            }
            if (testEntity.getUserName() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("userName"), testEntity.getUserName());
                predicatesList.add(predicate);
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        return sp;
    }
}
