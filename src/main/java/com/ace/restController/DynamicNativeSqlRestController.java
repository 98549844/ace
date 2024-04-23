package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.service.ReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Classname: DynamicNativeSqlRestController
 * @Date: 23/4/2024 9:32 pm
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/DynamicNativeSql")
@Tag(name = "Dynamic Native SQL")
public class DynamicNativeSqlRestController {
    private static final Logger log = LogManager.getLogger(DynamicNativeSqlRestController.class.getName());

    private final EntityManager entityManager;
    private final ReportsService reportsService;

    public DynamicNativeSqlRestController(EntityManager entityManager, ReportsService reportsService) {
        this.entityManager = entityManager;
        this.reportsService = reportsService;
    }


    /** 执行原生sql: select * from users
     * @param sql
     * @return
     */
    @Operation(summary = "执行原生sql")
    @GetMapping(value = "/execute.html")
    public AjaxResponse executeNativeSql(String sql) {
        log.info("sql: {}", sql);
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        return AjaxResponse.success(results);
    }

    //@Operation(summary = "执行report sql")
    //@GetMapping(value = "/report.html")
    //public AjaxResponse executeNativeSql() {
    //    Reports reports = new Reports();
    //    reports.setLevel("SUGGESTION");
    //    return AjaxResponse.success(reportsService.search(reports, "aaaaaa css"));
    //}
}

