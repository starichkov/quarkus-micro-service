Redis
=

## Docker

Redis official images could be found [here](https://hub.docker.com/_/redis).

### How to run it locally in Docker

```shell
docker run -d --name redis6 \
  --network=localnet \
  -p 6379:6379 \
  redis:6.2.12-alpine
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
