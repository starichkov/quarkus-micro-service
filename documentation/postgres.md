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
  postgres:17.2-alpine3.21
```

### Prepare database and user

Assuming we want to create a user named `quark` with some password (do not forget to replace `<<password>>` with desired one):

```postgresql
-- Create the schema
CREATE SCHEMA quarkus_db;

-- Create the user if it doesn't exist and set their password
DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_catalog.pg_user
            WHERE usename = 'quark'
        ) THEN
            CREATE USER quark PASSWORD '<<password>>';
        END IF;
    END $$;

-- Grant privileges to the user
GRANT INSERT, SELECT, DELETE, UPDATE ON ALL TABLES IN SCHEMA quarkus_db TO quark;
GRANT CREATE ON SCHEMA quarkus_db TO quark;

-- Optional: Ensure future tables in the schema inherit the privileges
ALTER DEFAULT PRIVILEGES IN SCHEMA quarkus_db GRANT INSERT, SELECT, DELETE, UPDATE ON TABLES TO quark;
```

### Network

Parameter `--network=localnet` is needed if you will be using these containers withing same Docker network - to allow
containers communicate with each other. In my case this network named as `localnet`.
