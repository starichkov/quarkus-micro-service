Jaeger
=

## Docker

Jaeger images could be found [here](https://hub.docker.com/r/jaegertracing/all-in-one).

### How to run it locally in Docker

Jaeger have very good manual of how to do it, could be found [here](https://www.jaegertracing.io/docs/1.25/getting-started/) (external link).

Below I will only re-post a part of their documentation.

```shell
docker run -d --name jaeger \
  --network=localnet
  -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.25
```

Jaeger UI will become accessible via `http://localhost:16686`.

Fresh Docker images of Jaeger Tracing could be found in [Docker Hub](https://hub.docker.com/r/jaegertracing/all-in-one/tags?page=1&ordering=last_updated).

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
