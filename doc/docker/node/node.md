pull node image into docker
docker pull node:14.18.1-alpine

运行容器
$ docker run -itd --name node node

检查版本
docker exec -it node /bin/bash
node -v


https://www.jianshu.com/p/a8a6d7006049