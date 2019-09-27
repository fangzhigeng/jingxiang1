#!/bin/bash
cd `dirname $0`

img_mvn="maven:3.3.3-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
img_output="deepexi/deepexi-test"         # output image tag

git pull  # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn clean package -U

sudo mv $proj_home/deepexi-test-provider/target/deepexi-test-provider-*.jar $proj_home/deepexi-test-provider/target/demo.jar # 兼容所有sh脚本
docker build -t $img_output .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

# 删除容器
docker rm -f deepexi-test &> /dev/null

version=`date "+%Y%m%d%H"`

spring_datasource_url=jdbc:mysql://localhost:3306/deepexi-test?useUnicode=true\&characterEncoding=utf-8\&useSSL=false

# 启动镜像
docker run -d --restart=always \
    --net=host \
    --dns 114.114.114.114 \
    --env 'TZ=Asia/Shanghai' \
    -w /home \
    -v /usr/share/zoneinfo/Asia/Shanghai:/etc/localtime:ro \
    -v $PWD/logs:/home/logs \
    --name deepexi-test deepexi/deepexi-test \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar \
          --spring.profiles.active=prod \
          --spring.datasource.url=$spring_datasource_url \
          --spring.datasource.username=root \
          --spring.datasource.password=my-secret-ab \
          --dubbo.registry.address=zookeeper://127.0.0.1:2181