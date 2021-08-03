Quarkus micro-framework example project
=

### Project

This project already contains working REST service with JSON serialisation (using Jackson), and even more!

#### Quarkus' extensions -based additions

- YAML-based configuration
- Database with Flyway migration support
- H2 database for test scope

#### 3rd party libraries additions

- Lombok with Mapstruct (with Mapstruct's CDI mode)

### Next steps

- Usage of Quarkus 'Docker extension' to generate Docker image for the app (instead of manually created one)

### Official documentation errors found

- Quarkus documentation says that minimum version of Maven supported is `3.6.1`. But on deal `add-extension` is not working even on Maven `3.6.3`. 
  In fact, it works fine only with Maven `3.8.1`.