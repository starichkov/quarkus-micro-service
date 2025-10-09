FROM eclipse-temurin:21.0.8_9-jre-ubi9-minimal

ENV RUN_USER=java-runner
ENV RUN_GROUP=${RUN_USER}
ENV RUN_DIR=/var/empty/${RUN_USER}
ENV PATH=$PATH:${RUN_DIR}
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=8080"

RUN microdnf install -y shadow-utils && \
    mkdir -p ${RUN_DIR} && \
    groupadd ${RUN_GROUP} && \
    useradd --home-dir /var/empty/${RUN_USER} --shell /sbin/nologin \
    --gid ${RUN_GROUP} --system ${RUN_USER} && \
    chown ${RUN_USER}:${RUN_GROUP} /var/log && \
    printf "#!/bin/sh\nexec java \${JAVA_OPTIONS} -jar app.jar \"\$@\"\n" > ${RUN_DIR}/entrypoint.sh && \
    chmod +x ${RUN_DIR}/entrypoint.sh && \
    microdnf clean all

WORKDIR ${RUN_DIR}
ADD target/*-runner.jar ${RUN_DIR}/app.jar

USER ${RUN_USER}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]