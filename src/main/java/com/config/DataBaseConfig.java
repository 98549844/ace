package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: DataBaseConfig
 * @Date: 6/12/2021 3:44 上午
 * @Author: garlam
 * @Description:
 */

@Configuration
public class DataBaseConfig {
    private static Logger log = LogManager.getLogger(DataBaseConfig.class.getName());
//      读取所有table 名称
//    @Bean
//    public Map metaMap(EntityManagerFactory factory) throws ClassNotFoundException {
//        if (factory.unwrap(SessionFactory.class) == null) {
//            throw new NullPointerException("factory is not a hibernate factory");
//        }
//        SessionFactory sessionFactory = factory.unwrap(SessionFactory.class);
//        Map<String, ClassMetadata> metaMap = sessionFactory.getAllClassMetadata();
//        Map<String, Class> map = new HashMap<>();
//        for (String key : metaMap.keySet()) {
//            AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap.get(key);
//            String tableName = classMetadata.getTableName().toLowerCase();
//            int index = tableName.indexOf(".");
//            if (index >= 0) {
//                tableName = tableName.substring(index + 1);
//            }
//            map.put(tableName, Class.forName(key));
//        }
//        return map;
//    }

}

