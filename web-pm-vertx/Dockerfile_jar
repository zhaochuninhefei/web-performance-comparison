FROM 172.17.4.86:5000/brood/base-openjdk17:jdk-17.0.3_001

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=80"

# 复制jar包与配置文件
ADD target/web-pm-*-fat.jar /app.jar
COPY conf/application.json /

# 指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar run cn.yjl.vertx.MainVerticle -conf /application.json
