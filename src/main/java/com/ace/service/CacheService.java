package com.ace.service;

import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: CacheService
 * @Date: 2023/5/17 下午 04:36
 * @Author: kalam_au
 * @Description:
 */


@Service
public class CacheService {
    private static final Logger log = LogManager.getLogger(CacheService.class.getName());

    private Map<String, Object> map;

    public CacheService() {
        this.map = new HashMap();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }



    public Object get(String key) {
        return this.map.get(key);
    }


    public String getString(String key) {
        return (String)this.map.get(key);
    }

    public boolean put(String key, Object obj) {
        try {
            this.map.put(key, obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean delete(String key) {
        try {
            this.map.remove(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object flush() {
        try {
            this.map = new HashMap<>();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

}

