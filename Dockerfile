FROM unitfinance/jdk17-sbt-scala:latest
#MAINTAINER author-info
#VOLUME /tmp
ADD target/ace-1.2.jar app.jar
# cypher.jar 与上面的pom对应
EXPOSE 8088
# 暴露端口 与配置文件对应
#ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=dev"]
# 激活名为 appliction-docker.yml 或 appliction-docker.properties 的配置