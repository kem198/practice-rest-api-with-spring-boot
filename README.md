# Practice - REST API with Spring Boot

This project is designed to practice the following:

- Building a REST API
- Using Spring Boot
- Applying Test-Driven Development (TDD)
- Implementing Continuous Integration (CI) for Java applications

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

### Build

```shell
./gradlew build
```

### Run

```shell
java -jar build/libs/practice-rest-api-with-spring-boot-0.0.1-SNAPSHOT.jar
```

## Example Usage

### Setup

```shell
# Example Environment: Ubuntu 24.04 on WSL
$ lsb_release -a
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 24.04.2 LTS
Release:        24.04
Codename:       noble

# Install SDKMAN!
$ curl -s "https://get.sdkman.io" | bash
$ sdk version
SDKMAN!
script: 5.19.0
native: 0.7.4 (linux x86_64)

# Install OpenJDK (Eclipse Temurin Java 21.0.6)
$ sdk install java 21.0.6-tem
$ java --version
openjdk 21.0.6 2025-01-21 LTS
OpenJDK Runtime Environment Temurin-21.0.6+7 (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.6+7 (build 21.0.6+7-LTS, mixed mode, sharing)

# Clone This repository
$ cd /path/to/your/repo/
$ git clone https://github.com/kem198/practice-rest-api-with-spring-boot.git
```

### Build & Run

```shell
# Move repository root
$ cd practice-rest-api-with-spring-boot
$ pwd
/path/to/your/repo/practice-rest-api-with-spring-boot

# Build the application
$ ./gradlew build

# Run the application
$ java -jar build/libs/practice-rest-api-with-spring-boot-0.0.1-SNAPSHOT.jar
```

### Call API

```sh
# Call /greeting
$ curl 'http://localhost:8080/greeting'
{"id":1,"content":"Hello, World!"}%

$ curl 'http://localhost:8080/greeting?name=kem198'
{"id":2,"content":"Hello, kem198!"}%
```

```sh
# Call /fizzbuzz
$ curl -i "http://localhost:8080/fizzbuzz?num=3"
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 26 Apr 2025 01:36:35 GMT

{"result":"Fizz"}%

$ curl -i "http://localhost:8080/fizzbuzz"
HTTP/1.1 400
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 26 Apr 2025 01:35:36 GMT
Connection: close

{"error":"Missing required parameter","message":"The 'num' query parameter is required."}%

$ curl -i "http://localhost:8080/fizzbuzz?num=abc"
HTTP/1.1 400
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 26 Apr 2025 01:35:48 GMT
Connection: close

{"error":"Invalid number format","message":"The 'num' query parameter must be a valid integer."}%
```

## References

### Installation

- [SDK Installation Candidates \| SDKMAN! the Software Development Kit Manager](https://sdkman.io/sdks/)
- [Spring Boot in Visual Studio Code](https://code.visualstudio.com/docs/java/java-spring-boot)

### Getting Started

- [Spring Boot REST API の作成 - 公式サンプルコード](https://spring.pleiades.io/guides/gs/rest-service)
