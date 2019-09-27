# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
FROM openjdk:8-jre-alpine

USER root

#中文乱码问题
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

# Prepare by downloading dependencies
COPY deepexi-test-provider/target/demo.jar /home/