package com.ace.util;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DockerContainerCommandExecutor {


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


        // 获取要执行的命令和容器ID
        String command = "ffmpeg -version";
        String containerId = "490b4fed08ea786a4c1927ec9dcfec3e5e4072bbb3596336ec5c65f6ede851b0";

        // 创建执行命令的请求
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                .withCmd("sh", "-c", command) //sh 是指定要使用的 shell, -c 是告诉 shell 后面的参数是要执行的命令
                .withAttachStdout(true)
                .withAttachStderr(true)
                .exec();

        // 启动命令的执行
        ExecStartCmd execStartCmd = dockerClient.execStartCmd(execCreateCmdResponse.getId())
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

        // 输出命令的版本号
        String output = outputBuilder.toString();
        String version = parseVersion(output);
        System.out.println("版本号：" + version);

        // 关闭 Docker 客户端连接
        dockerClient.close();
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
}
