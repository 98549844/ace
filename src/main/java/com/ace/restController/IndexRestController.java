package com.ace.restController;

import com.ace.models.common.RespResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/index")
@Tag(name = "Index")
public class IndexRestController {
    private final static Logger log = LogManager.getLogger(IndexRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public RespResult getIndex() {
        String get = "Welcome Index ! GET";
        return RespResult.success(get);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public RespResult getUserName(@PathVariable String userName) {
        log.info(userName);

        String get = "Welcome Index !" + userName + " GET";
        return RespResult.success(get);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RespResult postIndex(@RequestBody String IndexVO) {
        String post = "Welcome Index ! POST";
        System.out.println(post);
        IndexVO = post;
        return RespResult.success(IndexVO);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RespResult putIndex() {
        String put = "Welcome Index ! PUT";
        return RespResult.success(put);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public RespResult deleteIndex() {
        String delete = "Hello World ! DELETE";
        return RespResult.success(delete);

    }
}
