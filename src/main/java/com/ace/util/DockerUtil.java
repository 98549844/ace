package com.ace.util;

import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Version;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    private final static String LOCAL_DOCKER = "unix:///var/run/docker.sock";

    private final DockerClient dockerClient = getDockerClient(); //init docker client
    private String remoteUrl;

    public DockerUtil(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public DockerUtil() {
    }


    public static void main(String[] args) throws Exception {

        DockerUtil dockerUtil = new DockerUtil();
        DockerClient dockerClient = dockerUtil.getDockerClient();
        // dockerUtil.dockerVersion(dockerClient);
        // dockerUtil.dockerImages();
        // dockerUtil.dockerContainers();
        dockerUtil.execute(dockerUtil.getContainerId("ffmpeg"), "ffmpeg -version");
        //关闭Docker客户端连接
        dockerUtil.close(dockerClient);
    }

    public void execute(String containerId, String... command) throws IOException {
        // 创建执行命令的请求
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                                                        .execCreateCmd(containerId).withCmd("sh", "-c", command[0]) //sh 是指定要使用的 shell, -c 是告诉 shell 后面的参数是要执行的命令
                                                        .withAttachStdout(true)
                                                        .withAttachStderr(true)
                                                        .exec();
        // 启动命令的执行
        ExecStartCmd execStartCmd = dockerClient
                                    .execStartCmd(execCreateCmdResponse.getId())
                                    .withDetach(false);

        // 处理命令的输出并获取版本号
        StringBuilder outputBuilder = new StringBuilder();
        ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback() {
            @Override
            public void onNext(Frame frame) {
                outputBuilder.append(new String(frame.getPayload(), StandardCharsets.UTF_8));
                super.onNext(frame);
            }
        };
        execStartCmd.exec(execStartResultCallback);
        try {
            execStartResultCallback.awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String output = outputBuilder.toString();
        System.out.println(output);
    }

    private static String parseVersion(String output) {
        // 解析版本号逻辑，这里只是一个示例
        String version = "";
        if (output.contains("version")) {
            int index = output.indexOf("version");
            version = output.substring(index + 8, index + 13);
        }
        return version;
    }

    public DockerClient getDockerClient() {
        DockerClientConfig config = dockerClientConfig();
        ApacheDockerHttpClient httpClient = apacheDockerHttpClient();
        //创建Docker客户端
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).withDockerHttpClient(httpClient).build();
        return dockerClient;
    }

    private DockerClientConfig dockerClientConfig() {
        String url = LOCAL_DOCKER;
        if (!NullUtil.isNull(remoteUrl)) {
            log.info("remote docker url: " + remoteUrl);
            url = remoteUrl;
        }
        // 配置Docker客户端
        DockerClientConfig config = DefaultDockerClientConfig
                                    .createDefaultConfigBuilder()
                                    .withDockerHost(url)
                                    .withDockerTlsVerify(false)
                                    .build();
        return config;
    }

    private ApacheDockerHttpClient apacheDockerHttpClient() {
        DockerClientConfig config = dockerClientConfig();
        // 创建DockerHTTP客户端
        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient
                                                    .Builder()
                                                    .dockerHost(config.getDockerHost())
                                                    .sslConfig(config.getSSLConfig())
                                                    .build();
        return httpClient;
    }

    public void close(DockerClient dockerClient) throws IOException {
        // 关闭 Docker 客户端连接
        dockerClient.close();
    }


    public String getVersion() {
        //创建Docker客户端
        // DockerClient dockerClient = dockerClient();
        //在此执行Docker容器命令
        //版本
        Version version = new Version();
        try {
            version = dockerClient.versionCmd().exec();
            System.out.println("版本信息=>" + JSONUtil.toJsonStr(version));
            System.out.println("docker version:" + version.getVersion());
            System.out.println("apiVersion:" + version.getApiVersion());
            System.out.println("goVersion:" + version.getGoVersion());
            System.out.println("kernelVersion:" + version.getKernelVersion());
            System.out.println("platform version:" + version.getPlatform());
            System.out.println("Arch version:" + version.getArch());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSONUtil.toJsonStr(version);
    }

    public String getImages() {
        //创建Docker客户端
        //DockerClient dockerClient = dockerClient();
        //已有镜像
        List<Image> images = new ArrayList<>();
        try {
            images = dockerClient.listImagesCmd().exec();
            System.out.println("images size: " + images.size());
            for (Image image : images) {
                //打印image名和imageId
                System.out.println("imageName=>" + image.getRepoDigests()[0].split("@")[0]);
                System.out.println("ImageId=>" + image.getId().split(":")[1]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSONUtil.toJsonStr(images);

    }

    public String getContainers() {
        //创建Docker客户端
        //DockerClient dockerClient = dockerClient();
        //已有容器
        List<Container> containers = new ArrayList<>();
        try {
            containers = dockerClient.listContainersCmd().exec();
            System.out.println("containers size: " + containers.size());
            for (Container container : containers) {
                //打印image名和imageId
                System.out.println("containerName=>" + container.getNames()[0]);
                System.out.println("containerId=>" + container.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSONUtil.toJsonStr(containers);
    }

    public String getContainerId(String containerName) throws Exception {
        List<Container> containers;
        List<String> containerIdList = new ArrayList<>();
        try {
            containers = dockerClient.listContainersCmd().exec();
            for (Container container : containers) {
                if (container.getNames()[0].contains(containerName)) {
                    // containerId = container.getId();
                    containerIdList.add(container.getId());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (containerIdList.size() == 1) {
            return containerIdList.get(0);
        } else {
            throw new Exception("容器名称: " + containerName + " 找到多个容器id");
        }
    }


}

