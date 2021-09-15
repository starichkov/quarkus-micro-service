MySQL
=

## Docker

MySQL official images could be found [here](https://hub.docker.com/_/mysql).

### How to run it locally in Docker

```shell
docker run -d --name mysql5 \
  --network=localnet \
  -e MYSQL_ROOT_PASSWORD=REPLACE_WITH_ROOT_PASSWORD \
  -p 3306:3306 \
  mysql:5.7
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
