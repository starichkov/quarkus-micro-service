Jaeger
=

Jaeger 1.x has been deprecated. This documentation page now covers Jaeger 2.x only.

## Docker

Jaeger images could be found [here](https://hub.docker.com/r/jaegertracing/jaeger).

### How to run it locally in Docker

Jaeger have very good manual of how to do it, could be found [here](https://www.jaegertracing.io/docs/1.25/getting-started/) (external link).

Below I will only re-post a part of their documentation.

```shell
docker run -d --name jaeger \
  --network=localnet \
  -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 \
  -p 4317:4317 \
  -p 4318:4318 \
  -p 5778:5778 \
  -p 9411:9411 \
  -p 16686:16686 \
  jaegertracing/jaeger:2.8.0
```

Ports:
- `4317` - OTLP gRPC receiver
- `4318` - OTLP HTTP receiver
- `5778` - HTTP endpoint for dynamic sampling strategies - this allows the agent to fetch sampling configurations from the Jaeger collector
- `9411` - Zipkin compatibility
- `16686` - Jaeger UI

Jaeger UI will become accessible via `http://localhost:16686`.

Fresh Docker images of Jaeger Tracing could be found in [Docker Hub](https://hub.docker.com/r/jaegertracing/all-in-one/tags?page=1&ordering=last_updated).

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
