package com.ace.util;

import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;


/**
 * @Classname: ResourceUtil
 * @Date: 16/3/2024 3:35 pm
 * @Author: garlam
 * @Description:
 */


public class ResourceUtil extends CommonController {
    private static final Logger log = LogManager.getLogger(ResourceUtil.class.getName());


    private static final String MAC_TMP = "/Users/garlam/ace/tmp";
    private static final String LINUX_TMP = "/opt/workspace/ace/tmp/";

    /**
     * 同时支持IDEA和jar文件
     * <p>
     * 返回resource下的文件的路径
     * <p>
     * 判断路径=>把文件复制到tmp文件=>返回tmp文件路径
     *
     * @param resource
     * @return
     */
    public String getResourcePath(String resource) throws Exception {
        // ResourceUtil为当前类名
        URI uri = ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        // tempPath: 文件保存路径
        String tempPath;
        if (OsUtil.getOsName().equals(OsUtil.MAC)) {
            tempPath = MAC_TMP;
        } else if (OsUtil.getOsName().equals(OsUtil.LINUX)) {
            tempPath = LINUX_TMP;
        } else if (OsUtil.getOsName().equals(OsUtil.WINDOWS)) {
            log.error("没配windows temp文件夹");
            throw new Exception("WINDOWS 系统, temp文件夹未配置");
        } else {
            throw new Exception("未知系统, 找不到文件");
        }

        if (uri.toString().startsWith("file")) {
            // IDEA运行时，进行资源复制
            copyLocalResourcesFileToTemp(resource + FileUtil.separator, "*", tempPath + FileUtil.separator + resource);
        } else {
            // 获取jar包所在路径
            String jarPath = uri.toString();
            uri = URI.create(jarPath.substring(jarPath.indexOf("file:"), jarPath.indexOf(".jar") + 4));
            // 打成jar包后，进行资源复制
            copyJarResourcesFileToTemp(uri, tempPath, "BOOT-INF/classes/" + resource);
        }
        return tempPath + FileUtil.separator + resource;
    }

    /**
     * 复制本地资源文件到指定目录
     *
     * @param fileRoot   需要复制的资源目录文件夹
     * @param regExpStr  资源文件匹配正则，*表示匹配所有, 不支持文件夹内包含文件夹
     * @param tempParent 保存地址
     */
    private void copyLocalResourcesFileToTemp(String fileRoot, String regExpStr, String tempParent) {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(fileRoot);
            for (Resource resource : resources) {
                File newFile = new File(tempParent, resource.getFilename());
                if (newFile.exists()) {
                    newFile.delete();
                }
                InputStream stream = null;
                try {
                    stream = resource.getInputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 如果resource为文件夹时，会报异常，这里直接忽略这个异常
                }
                if (stream == null) {
                    newFile.mkdirs();
                    copyLocalResourcesFileToTemp(fileRoot + resource.getFilename() + "/", regExpStr, tempParent + "/" + resource.getFilename());
                } else {
                    if (!newFile.getParentFile().exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    org.apache.commons.io.FileUtils.copyInputStreamToFile(stream, newFile);
                }
            }
        } catch (Exception e) {
            log.error("failed to copy local source template", e);
        }
    }


    /**
     * 复制jar包中的资源文件到指定目录
     *
     * @param path       jar包所在路径
     * @param tempPath   保存目录
     * @param filePrefix 需要进行复制的资源文件目录：以BOOT-INF/classes/开头
     */
    private void copyJarResourcesFileToTemp(URI path, String tempPath, String filePrefix) {
        try {
            List<Map.Entry<ZipEntry, InputStream>> collect = readJarFile(new JarFile(path.getPath()), filePrefix).collect(Collectors.toList());
            for (Map.Entry<ZipEntry, InputStream> entry : collect) {
                // 文件相对路径
                String key = entry.getKey().getName();
                // 文件流
                InputStream stream = entry.getValue();
                File newFile = new File(tempPath + key.replaceAll("BOOT-INF/classes", ""));
                if (!newFile.getParentFile().exists()) {
                    newFile.getParentFile().mkdirs();
                }
                org.apache.commons.io.FileUtils.copyInputStreamToFile(stream, newFile);
            }
        } catch (IOException e) {
            log.error("failed to copy jar source template", e);
        }
    }

    private Stream<Map.Entry<ZipEntry, InputStream>> readJarFile(JarFile jarFile, String prefix) {
        Stream<Map.Entry<ZipEntry, InputStream>> readingStream = jarFile.stream().filter(entry -> !entry.isDirectory() && entry.getName().startsWith(prefix)).map(entry -> {
            try {
                return new AbstractMap.SimpleEntry<>(entry, jarFile.getInputStream(entry));
            } catch (IOException e) {
                e.printStackTrace();
                return new AbstractMap.SimpleEntry<>(entry, null);
            }
        });
        return readingStream.onClose(() -> {
            try {
                jarFile.close();
            } catch (IOException e) {
                log.error("failed to close jarFile", e);
            }
        });
    }

}

