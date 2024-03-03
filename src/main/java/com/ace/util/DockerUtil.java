package com.ace.util;

import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmd;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Version;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;


/**
 * @Classname: DockerUtil
 * @Date: 3/3/2024 1:56 pm
 * @Author: garlam
 * @Description:
 */


public class DockerUtil {
    private static final Logger log = LogManager.getLogger(DockerUtil.class.getName());


/*
    1. cd到docker安装路径
    cd /Users/garlam/.docker/daemon.json

    2.加入config
    {
        "hosts": [ "unix:///var/run/docker.sock","tcp://0.0.0.0:2375"],
    }

    3.重启docker服务
    docker desktop restart
*/
    //需要暴路端口2375
    private final static String LOCAL_DOCKER ="unix:///var/run/docker.sock";



    public static void main(String[] args) throws IOException {
        // 配置Docker客户端
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(LOCAL_DOCKER)
                .withDockerTlsVerify(false)
                .build();
        // 创建DockerHTTP客户端
        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .build();
        //创建Docker客户端
        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerHttpClient(httpClient)
                .build();

        //在此执行Docker容器命令
        //版本
        Version version = dockerClient.versionCmd().exec();
        System.out.println("版本信息" + JSONUtil.toJsonStr(version));
        //已有镜像
        List<Image> images = dockerClient.listImagesCmd().exec();
        System.out.println("已有镜像" + JSONUtil.toJsonStr(images));

        // 创建 ExecCreateCmd 对象，指定要执行的命令和容器 ID
        String containerId = "ffmpeg";
        String command = "ffmpeg -version";
        ExecCreateCmd execCreateCmd = dockerClient.execCreateCmd(containerId)
                .withCmd(command.split(" "));


        // 关闭 Docker 客户端连接
        dockerClient.close();
    }

}

