FROM alpine:3.14

WORKDIR /src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go

COPY ["config/app.yaml", "web-pm-go", "start.sh", "/src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/"]

ENV TIME_ZONE=Asia/Shanghai LANG=en_US.UTF-8 LANGUAGE=en_US.UTF-8 LC_ALL=en_US.UTF-8

RUN sed -i "s?dl-cdn.alpinelinux.org?mirrors.aliyun.com?g" /etc/apk/repositories \
    && apk add --no-cache tzdata bash \
	&& echo "${TIME_ZONE}" > /etc/timezone \
	&& ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime \
    && ls -l /src/github.com/zhaochuninhefei/web-performance-comparison/web-pm-go

EXPOSE 18080

ENTRYPOINT ["./start.sh"]
