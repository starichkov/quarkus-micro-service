FROM alpine:3.14 AS alpine-jre-11
RUN apk --no-cache add openjdk11-jre-headless --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community

FROM alpine-jre-11 AS alpine-jre-11-fresh
RUN apk update && apk upgrade

FROM alpine-jre-11-fresh

ENV RUN_USER=java-runner
ENV RUN_GROUP=${RUN_USER}
ENV RUN_DIR=/var/empty/${RUN_USER}
ENV PATH=$PATH:${RUN_DIR}
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=8080"

RUN mkdir -p ${RUN_DIR} &&\
    /usr/sbin/addgroup ${RUN_GROUP} &&\
    /usr/sbin/adduser --home /var/empty/${RUN_USER} --shell /usr/sbin/nologin \
    --disabled-password --no-create-home  --ingroup ${RUN_GROUP} \
    --gecos "User running the Java process" ${RUN_USER} &&\
    chown ${RUN_USER}:${RUN_GROUP} /var/log &&\
    printf "#!/bin/sh\nexec java \${JAVA_OPTIONS} -jar quarkus-micro-service-*[!cs].jar \${@}\n" > ${RUN_DIR}/entrypoint.sh && chmod 755 ${RUN_DIR}/entrypoint.sh

WORKDIR ${RUN_DIR}
ADD target/*.jar ${RUN_DIR}/

USER ${RUN_USER}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]