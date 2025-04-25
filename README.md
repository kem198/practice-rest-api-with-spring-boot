# Practice - Spring Boot & REST API

A simple project to practice creating a REST API with Spring Boot.

## Requirements

### Required

- [JDK 21](https://openjdk.org/projects/jdk/21/)

### Optional

- [SDKMAN! the Software Development Kit Manager](https://sdkman.io/)

## Commands

### Run for Development

```shell
./gradlew bootRun
```

### Build the Application

```shell
./gradlew build
```

### Run the Application

```shell
java -jar build/libs/practice-spring-boot-rest-api-0.0.1-SNAPSHOT.jar
```

## Example Usage

### Environment

```shell
$ lsb_release -a
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 24.04.2 LTS
Release:        24.04
Codename:       noble

$ sdk version
SDKMAN!
script: 5.19.0
native: 0.7.4 (linux x86_64)

$ java --version
openjdk 21.0.6 2025-01-21 LTS
OpenJDK Runtime Environment Temurin-21.0.6+7 (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.6+7 (build 21.0.6+7-LTS, mixed mode, sharing)
```

### Build & Run & Call

```shell
# Currently here:
$ pwd
/path/to/your/repo/practice-spring-boot-rest-api

# Build the application
$ ./gradlew build

# Run the application
$ java -jar build/libs/practice-spring-boot-rest-api-0.0.1-SNAPSHOT.jar

# Call the default greeting
$ curl 'http://localhost:8080/greeting'
{"id":1,"content":"Hello, World!"}%

# Call the personalized greeting
$ curl 'http://localhost:8080/greeting?name=kem198'
{"id":2,"content":"Hello, kem198!"}%
```

## References

### Installation

- [SDK Installation Candidates \| SDKMAN! the Software Development Kit Manager](https://sdkman.io/sdks/)
- [Spring Boot in Visual Studio Code](https://code.visualstudio.com/docs/java/java-spring-boot)

### Getting Started

- [Spring Boot REST API の作成 - 公式サンプルコード](https://spring.pleiades.io/guides/gs/rest-service)
