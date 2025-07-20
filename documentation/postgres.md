PostgreSQL
=

## Docker

PostgreSQL official images could be found [here](https://hub.docker.com/_/postgres).

### How to run it locally in Docker

```shell
docker run -d --name postgres17a \
  --network=localnet \
  -e POSTGRES_PASSWORD=REPLACE_WITH_ROOT_PASSWORD \
  -p 5432:5432 \
  postgres:17.5-alpine3.22
```

### Prepare database and user

Assuming we want to create a user named `quark` with some password (do not forget to replace `<<password>>` with desired one):

```postgresql
-- Create a database
CREATE DATABASE quarkus_db;

-- Create the user if it doesn't exist and set their password
CREATE DATABASE quarkus_db;
CREATE USER quark WITH PASSWORD '<<password>>';
GRANT ALL PRIVILEGES ON DATABASE quarkus_db TO quark;
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
