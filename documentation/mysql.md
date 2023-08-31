MySQL
=

## Docker

MySQL official images could be found [here](https://hub.docker.com/_/mysql).

### How to run it locally in Docker

```shell
docker run -d --name mysql8 \
  --network=localnet \
  -e MYSQL_ROOT_PASSWORD=REPLACE_WITH_ROOT_PASSWORD \
  -p 3306:3306 \
  mysql:8.0.34
```

### Prepare database and user

Assuming we want to create a user named `quark` with some password (do not forget to replace `<<password>>` with desired one):

```mysql
CREATE SCHEMA quarkus_db COLLATE utf8mb4_general_ci;
CREATE USER IF NOT EXISTS 'quark';
ALTER USER 'quark'@'%' IDENTIFIED BY '<<password>>';
GRANT INSERT, SELECT, DELETE, UPDATE ON quarkus_db.* TO 'quark'@'%';
GRANT CREATE ON quarkus_db.* TO 'quark'@'%';
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
