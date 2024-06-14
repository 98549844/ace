package com.ace.restController;

import com.ace.models.common.RespResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Classname: ApiRestController
 * @Date: 9/5/2021 10:04 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/datasource")
@Tag(name = "DataSource")
public class DataSourceRestController {
    private static final Logger log = LogManager.getLogger(DataSourceRestController.class.getName());

    private final DataSource dataSource;

    public DataSourceRestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @GetMapping("/get")
    public RespResult getDatasource() throws SQLException {
        Connection connection = dataSource.getConnection();
        String url = connection.getMetaData().getURL();
        log.info("access to get datasource: {}", url);

        return RespResult.success("datasource: " + url);
    }


}

