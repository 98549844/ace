package com.ace.util;

import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
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
        DockerUtil dockerUtil = new DockerUtil();
        dockerUtil.dockerVersion();
       // dockerUtil.dockerImages();
       // dockerUtil.dockerContainers();
    }

    public void dockerVersion() throws IOException {
        //创建Docker客户端
        DockerClient dockerClient = dockerClient();
        //在此执行Docker容器命令
        //版本
        Version version = dockerClient.versionCmd().exec();
        System.out.println("版本信息=>" + JSONUtil.toJsonStr(version));
        System.out.println("docker version:"+ version.getVersion());
        System.out.println("apiVersion:"+ version.getApiVersion());
        System.out.println("goVersion:"+ version.getGoVersion());
        System.out.println("kernelVersion:"+ version.getKernelVersion());
        System.out.println("platform version:"+ version.getPlatform());
        System.out.println("Arch version:"+ version.getArch());
        close(dockerClient);
    }

    public void dockerImages() throws IOException {
        //创建Docker客户端
        DockerClient dockerClient = dockerClient();
        //已有镜像
        List<Image> images = dockerClient.listImagesCmd().exec();
        System.out.println("images size: "+images.size());
        for (Image image : images) {
            //打印image名和imageId
            System.out.println("imageName=>"+image.getRepoDigests()[0].split("@")[0]);
            System.out.println("ImageId=>"+image.getId().split(":")[1]);
        }
        close(dockerClient);
    }

    public void dockerContainers() throws IOException {
        //创建Docker客户端
        DockerClient dockerClient = dockerClient();
        //已有容器
        List<Container> containers = dockerClient.listContainersCmd().exec();
        System.out.println("containers size: "+containers.size());
        for (Container container : containers) {
            //打印image名和imageId
            System.out.println("containerName=>"+container.getNames()[0]);
            System.out.println("containerId=>"+container.getId());
        }
        close(dockerClient);
    }

    private DockerClientConfig dockerClientConfig(){
        // 配置Docker客户端
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(LOCAL_DOCKER)
                .withDockerTlsVerify(false)
                .build();
        return config;
    }

    private ApacheDockerHttpClient apacheDockerHttpClient(){
        DockerClientConfig config = dockerClientConfig();
        // 创建DockerHTTP客户端
        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .build();
        return httpClient;
    }

    private DockerClient dockerClient(){
        DockerClientConfig config = dockerClientConfig();
        ApacheDockerHttpClient httpClient = apacheDockerHttpClient();
        //创建Docker客户端
        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerHttpClient(httpClient)
                .build();
        return dockerClient;
    }

    private void close(DockerClient dockerClient) throws IOException {
        // 关闭 Docker 客户端连接
        dockerClient.close();
    }

}

