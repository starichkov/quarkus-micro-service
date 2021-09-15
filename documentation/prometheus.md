Prometheus metrics
=

## Docker

Prometheus images could be found [here](https://hub.docker.com/r/prom/prometheus).

### How to run it locally in Docker

We need custom config for Prometheus to fetch our metrics from the service. That's why this time we'll use separate
Dockerfile.

```shell
docker build -f ./configuration/prometheus/Dockerfile \
  -t prometheus \
  ./configuration/prometheus

docker run -d --name=prometheus \
  --network=localnet \
  -p 9090:9090 \
  prometheus
```

Prometheus UI will be available at http://localhost:9090/graph

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
