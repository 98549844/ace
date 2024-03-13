用容器间相互访问的方法实现springboot与mysql数据库的连接。

1.docker 创建网络

docker network create mynet（mynet是自定义的网络名）

查看上面命令创建的网络

docker network list

![img.png](img%2Fimg.png)

2.拉取官方mysql5.6镜像

docker pull mysql:5.6

查看下载下来的官方mysql5.6的镜像

docker images

![img_1.png](img%2Fimg_1.png)


3.在指定的网络上启动mysql镜像，注意mysql镜像在启动时要指定root账号的密码，格式如下

docker run -it -p 3306:3306 -e MYSQL_ROOT_PASSWORD="123456" --name mysqlserver --network mynet --network-alias mysqlserver mysql镜像id
docker run -it -p 3306:3306 -e MYSQL_ROOT_PASSWORD="123456" --name mysqlserver --network mynet --network-alias mysqlserver a3a2968869cf080dbbd2adaac9e4075cc358b50a1451ff5e2b9ae90551a4735f

上面的启动命令中mysql在mynet上的别名是mysqlserver， 这样，需要使用这个mysql上数据库的

springboot的yml配置文件中应该按如下的格式配置

![img_2.png](img%2Fimg_2.png)

注意上面的datasource的url部分是

url: jdbc:mysql://mysqlserver:3306/testdatabase

以服务镜像名加端口的方式访问mysql数据库

这个springboot镜像启动时也要指定网络是mynet ，如果制作的springboot镜像是名是 myboot 则启动方式如下：

docker run -it -p 8088:8088 --name ace --network mynet --network-alias ace 3540358869e9a881c5b4bc838a0822182e0e252b3a6c9b145694fce80cab156d

这样springboot就可以成功连接上面的数据库了，注意要先在启动好的mysqlserver中创建好具体的数据库。