Quarkus micro-framework example project
=

## Project

This project already contains working REST service with JSON serialisation (using Jackson), and even more!

### Quarkus' extensions -based additions

- YAML-based configuration
- Database with Flyway migration support
- H2 database for test scope

### 3rd party libraries additions

- Lombok with Mapstruct (with Mapstruct's CDI mode)

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

## Next steps

- Usage of Quarkus 'Docker extension' to generate Docker image for the app (instead of manually created one)

## Official documentation errors found

- Quarkus documentation says that minimum version of Maven supported is `3.6.1`. But on deal `add-extension` is not working even on Maven `3.6.3`. 
  In fact, it works fine only with Maven `3.8.1`.