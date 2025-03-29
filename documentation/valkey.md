Valkey
=

## Docker

Valkey official images could be found [here](https://hub.docker.com/r/valkey/valkey).

### How to run it locally in Docker

```shell
docker run -d --name valkey72a \
  --network=localnet \
  -p 6379:6379 \
  valkey/valkey:7.2.8-alpine3.21
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
