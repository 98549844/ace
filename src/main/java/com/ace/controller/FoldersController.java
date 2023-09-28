package com.ace.controller;

import com.ace.controller.common.CommonController;
import com.ace.service.FoldersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Classname: FileController
 * @Date: 2022/9/23 下午 05:12
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class FoldersController extends CommonController {
    private static final Logger log = LogManager.getLogger(FoldersController.class.getName());

    private final FoldersService foldersService;


    public FoldersController(FoldersService foldersService) {
        this.foldersService = foldersService;
    }

    @RequestMapping(value = "/folder/create/{currentPath}/{newFolderName}", method = RequestMethod.GET)
    public ModelAndView createNewFolder(@PathVariable String currentPath, @PathVariable String newFolderName) {
        log.info("access createNewFolder");
        Map<String, String> status = foldersService.create(currentPath, newFolderName, getCurrentUser());

        return super.page("ace/modules/files/file");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView testCreateFolder() {
        log.info("access createNewFolder");
        String currentPath = "C:\\ace\\users\\garlam\\";
        String newFolderName = "AAA";
        String newFolderNameB = "BBB";
        Map<String, String> status = foldersService.create(currentPath, newFolderName, getCurrentUser());
        Map<String, String> status1 = foldersService.create(currentPath, newFolderNameB, getCurrentUser());
        System.out.println(status.get("msg"));
        System.out.println(status1.get("msg"));

        String currentPath1 = "C:\\ace\\users\\garlam\\AAA\\";
        String currentPath2 = "C:\\ace\\users\\garlam\\BBB\\";
        String newFolderNameA = "C1";
        String newFolderNameBB = "C2";
        String newFolderNameBC = "C3";

        Map<String, String> status4 = foldersService.create(currentPath1, newFolderNameA, getCurrentUser());
        Map<String, String> status5 = foldersService.create(currentPath2, newFolderNameBB, getCurrentUser());
        Map<String, String> status6 = foldersService.create(currentPath2, newFolderNameBC, getCurrentUser());

        System.out.println(status4.get("msg"));
        System.out.println(status5.get("msg"));
        System.out.println(status6.get("msg"));

        return super.page("ace/modules/files/file");

    }
}

