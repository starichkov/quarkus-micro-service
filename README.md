[![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/starichkov/quarkus-micro-service/maven.yml?style=for-the-badge)](https://github.com/starichkov/quarkus-micro-service/actions/workflows/maven.yml)
[![codecov](https://img.shields.io/codecov/c/github/starichkov/quarkus-micro-service?style=for-the-badge)](https://codecov.io/gh/starichkov/quarkus-micro-service)
[![GitHub license](https://img.shields.io/github/license/starichkov/quarkus-micro-service?style=for-the-badge)](https://github.com/starichkov/quarkus-micro-service/blob/main/LICENSE.md)

Quarkus microservice
=
This project is a Quarkus framework based, 'ready-to-play' micro-service.

| Name    | Version     |
|---------|-------------|
| Java    | 17          |
| Maven   | 3.8.1+      |
| Quarkus | 3.5.2 |

## What's inside?

### Quarkus extensions-based features

- REST controller with multiple endpoints ([official guide](https://quarkus.io/guides/rest-json))
- JSON serialisation using Jackson
- YAML-based configuration ([official guide](https://quarkus.io/guides/config-yaml))
- Database with Flyway migration support ([official guide](https://quarkus.io/guides/flyway))
- H2 database for test scope
- Health check endpoints ([official guide](https://quarkus.io/guides/smallrye-health))
- Scheduled tasks configured to use Unix [Crontab](https://crontab.guru/) syntax ([official guide](https://quarkus.io/guides/scheduler-reference))
- Redis client with health indicator ([official guide](https://quarkus.io/guides/redis))
- OpenTracing support ([official guide](https://quarkus.io/guides/opentracing))
- EventBus (alternative to Spring's ApplicationEvent system, [official guide](https://quarkus.io/guides/reactive-event-bus))
- Qute templating engine (special-for-Quarkus alternative to Freemarker or Mustache engines, [official guide](https://quarkus.io/guides/qute-reference))

This service contains multiple features which requires some specific servers to be available and resolvable:

| Feature     | Requirement   | How to run in Docker                       | How to disable                     |
|-------------|---------------|--------------------------------------------|------------------------------------|
| Database    | MySQL server  | [mysql.md](/documentation/mysql.md)        | - required -                       |
| Redis       | Redis server  | [redis.md](/documentation/redis.md)        |                                    |
| Opentracing | Jaeger server | [jaeger.md](/documentation/jaeger.md)      |                                    |
| Metrics     | Prometheus    | [metrics.md](/documentation/prometheus.md) | `quarkus.micrometer.enabled=false` |

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

### Docker

This service contains two Dockerfile-s, [more details](/documentation/DOCKER.md) on the separate page.

### 3rd party libraries features

- Lombok + Mapstruct (with Mapstruct's CDI mode)

## What's the plan?

List of features I am planning to add:

- Async controller endpoints
- Hibernate ORM with Panache ([official guide](https://quarkus.io/guides/hibernate-orm-panache))
- WebSocket support
- AMPQ support
- Migrate to multi-module Maven project
- Native Build with GraalVM

## Limitations found

Quarkus does not support/provide:

- Redis as a persistence layer for caching extension. It means you can't use Quarkus `@Cache**` annotations with underlying Redis.
  And Quarkus itself will not allow to implement such layer easily - caching extension does not accept custom `CacheManager` or cache types.

- AOP (AspectJ) as Spring does. Partially similar behavior could be achieved via Interceptors, but it is still quite a different thing...

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
