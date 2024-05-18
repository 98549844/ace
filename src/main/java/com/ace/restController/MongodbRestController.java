package com.ace.restController;

import com.ace.models.common.RespResult;
import com.ace.models.mongodb.Reports;
import com.ace.utilities.RandomUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @Classname: MongodbRestController
 * @Date: 5/5/24 PM4:31
 * @Author: garlam
 * @Description:
 */


//https://blog.csdn.net/qq_65142821/article/details/137652174
@RestController
@RequestMapping("/rest/mongodb")
@Tag(name = "Mongodb")
public class MongodbRestController {
    private static final Logger log = LogManager.getLogger(MongodbRestController.class.getName());

    private MongoTemplate mongoTemplate;

    public MongodbRestController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Operation(summary = "findAll")
    @GetMapping("/findAll.html")
    public RespResult findAll() {
        List<Reports> result = mongoTemplate.findAll(Reports.class);
        Map map = new LinkedHashMap();
        map.put("total", result.size());
        for (Reports reports : result) {
            map.put(reports.getReportId() + " " + reports.getSubject(), reports.getContent());
        }
        return RespResult.success(map);
    }

    @Operation(summary = "findById")
    @GetMapping("/find/{id}")
    public RespResult findById(@PathVariable String id) {
        Reports result = mongoTemplate.findById(id, Reports.class);
        return RespResult.success(result);
    }

    @Operation(summary = "findByCriteria")
    @GetMapping("/find/{key}/{criteria}")
    public RespResult updateByCriteria(@PathVariable String key, @PathVariable String criteria) {
        // 查询条件
        Query query = new Query(Criteria.where(key).is(criteria));
        List<Reports> result = mongoTemplate.find(query, Reports.class);
        return RespResult.success(result);
    }

    @Operation(summary = "updateSubjectById")
    @GetMapping("/updateSubject/{id}/{subject}")
    public RespResult updateSubjectById(@PathVariable Long id, @PathVariable String subject) {
        Reports result = mongoTemplate.findById(id, Reports.class);
        result.setSubject(subject);
        mongoTemplate.save(result);
        return RespResult.success(result);
    }

    @Operation(summary = "updateFieldById")
    @GetMapping("/updateFieldById/{id}/{key}/{newValue}")
    public RespResult updateFieldById(@PathVariable Long id, @PathVariable String key, @PathVariable String newValue) {
        Query query = new Query(Criteria.where("reportId").is(id));
        // 修改内容
        Update update = new Update().set(key, newValue);
        mongoTemplate.updateMulti(query, update, Reports.class);

        Reports result = mongoTemplate.findById(id, Reports.class);
        return RespResult.success(result);
    }


    @Operation(summary = "updateContentById")
    @GetMapping("/updateContentById/{id}/{content}")
    public RespResult updateContentById(@PathVariable Long id, @PathVariable String content) {
        Reports result = mongoTemplate.findById(id, Reports.class);
        result.setContent(content);
        mongoTemplate.save(result);
        return RespResult.success(result);
    }


    /**
     * 同时支持插入和修改数据
     * save不支持批量插入，如果需要批量插入，请使用insertAll方法
     *
     * @return
     */
    @Operation(summary = "save")
    @GetMapping("/save.html")
    public RespResult save() {
        List<Reports> reportsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Reports reports = new Reports();
            reports.setSubject("report subject: Ace-" + RandomUtil.getRangeInt(1, 999));
            reports.setContent("save content: Ace-Application :: " + RandomUtil.getRangeInt(1, 999));
            reportsList.add(reports);
            mongoTemplate.save(reports);
        }

        List<Reports> result = mongoTemplate.findAll(Reports.class);
        return RespResult.success(result);
    }

    /**
     * 插入数据
     * insert不支持批量插入，如果需要批量插入，请使用insertAll方法
     *
     * @return
     */
    @Operation(summary = "insert")
    @GetMapping("/insert.html")
    public RespResult insert() {

        List<Reports> reportsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Reports reports = new Reports();
            reports.setSubject("report subject: Ace" + RandomUtil.getRangeInt(1, 999));
            reports.setContent("save content: Ace-Application" + RandomUtil.getRangeInt(1, 999));
            reportsList.add(reports);
        }
        mongoTemplate.insertAll(reportsList);

        List<Reports> result = mongoTemplate.findAll(Reports.class);
        return RespResult.success(result);
    }


    @Operation(summary = "deleteById")
    @GetMapping("/delete/{id}")
    public RespResult deleteById(@PathVariable String id) {
        Query query = new Query(Criteria.where("reportId").is(id));
        mongoTemplate.remove(query, Reports.class);
        String result = "reportId: " + id + " deleted successfully";
        return RespResult.success(result);
    }

    @Operation(summary = "deleteAll")
    @GetMapping("/deleteAll.html")
    public RespResult deleteAll() {
        List<Reports> reports = mongoTemplate.findAll(Reports.class);
        Query query = new Query();
        mongoTemplate.remove(query, Reports.class);
        String result = "Total " + reports.size() + " reports deleted successfully";
        return RespResult.success(result);
    }

}

