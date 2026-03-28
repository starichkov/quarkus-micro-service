FROM eclipse-temurin:25-jre-ubi10-minimal

ENV RUN_USER=java-runner
ENV RUN_GROUP=${RUN_USER}
ENV RUN_DIR=/app
ENV PATH=$PATH:${RUN_DIR}
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=8080"

RUN microdnf install -y shadow-utils && \
    mkdir -p ${RUN_DIR} && \
    groupadd ${RUN_GROUP} && \
    useradd --home-dir /var/empty/${RUN_USER} --shell /sbin/nologin \
    --gid ${RUN_GROUP} --system ${RUN_USER} && \
    chown ${RUN_USER}:${RUN_GROUP} /var/log && \
    printf "#!/bin/sh\nexec java \${JAVA_OPTIONS} -jar quarkus-run.jar \"\$@\"\n" > ${RUN_DIR}/entrypoint.sh && \
    chmod +x ${RUN_DIR}/entrypoint.sh && \
    chown -R ${RUN_USER}:${RUN_GROUP} ${RUN_DIR} && \
    microdnf clean all

WORKDIR ${RUN_DIR}
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/*.jar ${RUN_DIR}/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/app/ ${RUN_DIR}/app/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/lib/ ${RUN_DIR}/lib/
COPY --chown=${RUN_USER}:${RUN_GROUP} target/quarkus-app/quarkus/ ${RUN_DIR}/quarkus/

USER ${RUN_USER}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]