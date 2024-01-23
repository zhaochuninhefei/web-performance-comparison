FROM 172.17.4.86:5000/brood/base-openjdk21:jdk-21.0.1_12

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=80"

# 复制上下文目录下的target/*.jar 到容器里
ADD target/web-pm-sbt-vt-*.jar app.jar

# 指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT java ${JAVA_OPTS} -jar /app.jar
