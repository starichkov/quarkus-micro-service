![GitHub](https://img.shields.io/github/license/starichkovva/quarkus-rest-example?label=LICENSE&style=for-the-badge)
[![codecov](https://img.shields.io/codecov/c/github/starichkovva/quarkus-rest-example?style=for-the-badge)](https://codecov.io/gh/starichkovva/quarkus-rest-example)

Quarkus micro-framework example project
=
This project is a working, ready-to-copy-and-use, Quarkus framework based micro-service.

## Features already implemented in this example

### Quarkus own features (extension-based)

- REST controller with multiple endpoints
- JSON serialisation using Jackson
- YAML-based configuration
- Database with Flyway migration support
- H2 database for test scope
- [Health check](https://quarkus.io/guides/smallrye-health) endpoints
- [Scheduled](https://quarkus.io/guides/scheduler-reference) tasks configured to use Unix [Crontab](https://crontab.guru/) syntax

#### Health check

These endpoints are available via following URLs:

```
GET http://localhost:8080/q/health
GET http://localhost:8080/q/health/live
GET http://localhost:8080/q/health/ready
```

Also, there is Health UI provided too:

```
http://localhost:8080/q/health-ui/
```

### 3rd party libraries features

- Lombok + Mapstruct (with Mapstruct's CDI mode)

## Planned features

Here is the list of features I am planning to add and use soon:

- Redis client (with health indicator)

## Docker support

There are 2 ways to build Quarkus app, both leads to different Docker images to be build too.

### Quarkus standard Jar

This is a default Quarkus mode - it will create following structure in the `target/` folder (in addition to Maven's build):

```
target/quarkus/
target/quarkus-app/
target/quarkus-app/app/
target/quarkus-app/lib/
target/quarkus-app/quarkus/
```

In `target/quarkus-app` there will be `quarkus-runner.jar` for launching your app which is placed under `target/quarkus-app/app/`.

Quarkus 'Docker extension' awaits `Dockerfile.jvm` to be under `src/main/docker`.

To build such standard app and Docker image for it, use following command:

```shell
mvn clean package -U \
  -Dquarkus.package.type=jar \
  -Dquarkus.container-image.build=true \
  -Dquarkus.container-image.name=quarkus-rest-example \
  -Dquarkus.container-image.tag=1.0.0
```

### 2. Quarkus uber Jar

This is default mode for this specific project. Standard `mvn clean package` in this project is equivalent to:

```shell
mvn clean package -U \
  -Dquarkus.package.type=uber-jar
```

That's why there is also Dockerfile exists in the project root - it will expect uber jar to be copied to the container instead of multiple folders structure.

## Official documentation mistakes

- Quarkus documentation says that minimum version of Maven supported is `3.6.1`. But on deal `add-extension` is not working even on Maven `3.6.3`. 
  In fact, it works fine only with Maven `3.8.1`.

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
