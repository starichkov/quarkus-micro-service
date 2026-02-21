FROM eclipse-temurin:25-jre-alpine-3.23

ENV RUN_USER=java-runner
ENV RUN_GROUP=${RUN_USER}
ENV RUN_DIR=/app
ENV PATH=$PATH:${RUN_DIR}
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=8080"

RUN mkdir -p ${RUN_DIR} &&\
    /usr/sbin/addgroup ${RUN_GROUP} &&\
    /usr/sbin/adduser --home /var/empty/${RUN_USER} --shell /usr/sbin/nologin \
    --disabled-password --no-create-home  --ingroup ${RUN_GROUP} \
    --gecos "User running the Java process" ${RUN_USER} &&\
    chown ${RUN_USER}:${RUN_GROUP} /var/log &&\
    printf "#!/bin/sh\nexec java \${JAVA_OPTIONS} -jar quarkus-run.jar \"\$@\"\n" > ${RUN_DIR}/entrypoint.sh && \
    chmod 755 ${RUN_DIR}/entrypoint.sh && \
    chown -R ${RUN_USER}:${RUN_GROUP} ${RUN_DIR}

WORKDIR ${RUN_DIR}
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/*.jar ${RUN_DIR}/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/app/ ${RUN_DIR}/app/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/lib/ ${RUN_DIR}/lib/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/quarkus/ ${RUN_DIR}/quarkus/

USER ${RUN_USER}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]