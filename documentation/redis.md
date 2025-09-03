Redis
=

## Docker

Redis official images could be found [here](https://hub.docker.com/_/redis).

### How to run it locally in Docker

```shell
docker run -d --name redis72 \
  --network=localnet \
  -p 6379:6379 \
  redis:7.2.10-alpine3.21
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
