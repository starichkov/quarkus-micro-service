[![codecov](https://img.shields.io/codecov/c/github/starichkovva/quarkus-micro-service?style=for-the-badge)](https://codecov.io/gh/starichkovva/quarkus-micro-service)
![GitHub](https://img.shields.io/github/license/starichkovva/quarkus-micro-service?label=LICENSE&style=for-the-badge)

Quarkus micro-service
=
This project is a ready-to-copy-and-use, Quarkus framework based micro-service.

## What's inside?

### Quarkus extensions based features

- REST controller with multiple endpoints
- JSON serialisation using Jackson
- YAML-based configuration
- Database with Flyway migration support
- H2 database for test scope
- [Health check](https://quarkus.io/guides/smallrye-health) endpoints
- [Scheduled](https://quarkus.io/guides/scheduler-reference) tasks configured to use Unix [Crontab](https://crontab.guru/) syntax
- [Redis client](https://quarkus.io/guides/redis) (with health indicator)

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

- OpenTracing support
- Hibernate ORM with Panache (more on the [official guide](https://quarkus.io/guides/hibernate-orm-panache))
- Qute templating engine (special-for-Quarkus alternative to Freemarker or Mustache engines)
- EventBus (alternative to Spring's ApplicationEvent system)
- WebSocket support
- AMPQ support
- Native Build with GraalVM

## Limitations found

Quarkus does not support/provide:

- Redis as a persistence layer for caching extension. It means you can't use Quarkus `@Cache**` annotations with underlying Redis.
  And Quarkus itself will not allow to implement such layer easily - caching extension does not accept custom `CacheManager` or cache types.

- AOP (AspectJ) as Spring does. Partially similar behavior could be achieved via Interceptors, but it is still quite a different thing...

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
