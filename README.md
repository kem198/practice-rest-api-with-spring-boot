<!-- omit in toc -->
# Practice - REST API with Spring Boot

This project is designed to practice the following:

- Building a **REST API**
- Using **Spring Boot**
- Applying **Test-Driven Development** (TDD)
- Implementing **Continuous Integration** (CI) for Java applications

<!-- omit in toc -->
## TOC

- [Requirements](#requirements)
    - [Required](#required)
    - [Recommended](#recommended)
- [Commands](#commands)
    - [Test](#test)
    - [Run for Development](#run-for-development)
    - [Build](#build)
    - [Run](#run)
- [Examples](#examples)
    - [Setup environment](#setup-environment)
    - [Run Application Server](#run-application-server)
    - [Run DB Server on Docker Container](#run-db-server-on-docker-container)
    - [Demo APIs](#demo-apis)
        - [`/api/greeting/v1`](#apigreetingv1)
        - [`/api/fizzbuzz/v1`](#apifizzbuzzv1)
- [References](#references)
    - [Installation](#installation)
    - [Getting Started](#getting-started)
    - [API](#api)
    - [curl](#curl)

## Requirements

### Required

- [JDK 21](https://openjdk.org/projects/jdk/21/)
- [Docker](https://www.docker.com/)

### Recommended

- [SDKMAN!](https://sdkman.io/)
- [Visual Studio Code](https://azure.microsoft.com/ja-jp/products/visual-studio-code)

## Commands

### Test

```shell
./gradlew test
```

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

## Examples

### Setup environment

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

# Install OpenJDK 21 (Eclipse Temurin Java 21.0.6)
$ sdk install java 21.0.6-tem
$ java --version
openjdk 21.0.6 2025-01-21 LTS
OpenJDK Runtime Environment Temurin-21.0.6+7 (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.6+7 (build 21.0.6+7-LTS, mixed mode, sharing)

# Clone This repository
$ cd /path/to/your/repo/
$ git clone https://github.com/kem198/practice-rest-api-with-spring-boot.git
```

### Run Application Server

```shell
# Move to repository root
$ cd practice-rest-api-with-spring-boot
$ pwd
/path/to/your/repo/practice-rest-api-with-spring-boot

# Build and Run application
$ ./gradlew build
$ java -jar build/libs/practice-rest-api-with-spring-boot-0.0.1-SNAPSHOT.jar

# Request to API
$ curl 'http://localhost:8080/api/v1/greeting'
{"id":1,"content":"Hello, World!"}%
```

### Run DB Server on Docker Container

```sh
# Current directory is the repository root
$ pwd
/path/to/your/repo/practice-rest-api-with-spring-boot

# Copy and rename .env.example to .env
$ cp .env.example .env

# (Optional) Edit .env for your settings
$ vim .env

# Start the database service
$ docker compose up -d

# Connect to the database and execute queries
$ docker compose exec db psql -U postgres -d practice_rest_api_with_spring_boot_db
psql (17.4 (Debian 17.4-1.pgdg120+2))
Type "help" for help.

practice_rest_api_with_spring_boot_db=# \d
                  List of relations
 Schema |         Name         |   Type   |  Owner
--------+----------------------+----------+----------
 public | example_table        | table    | postgres
 public | example_table_id_seq | sequence | postgres
(2 rows)

practice_rest_api_with_spring_boot_db=# SELECT * FROM example_table;
 id |   name    |         created_at
----+-----------+----------------------------
  1 | Example 1 | 2025-05-01 20:49:44.679373
  2 | Example 2 | 2025-05-01 20:49:44.679373
  3 | Example 3 | 2025-05-01 20:49:44.679373
(3 rows)

practice_rest_api_with_spring_boot_db=# exit
```

### Demo APIs

#### `/api/greeting/v1`

```sh
$ curl -i -X GET 'http://localhost:8080/api/greeting/v1/hello'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:32:49 GMT

{"id":1,"content":"Hello, World!"}%

$ curl -i -X GET 'http://localhost:8080/api/greeting/v1/hello?name=kem198'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:33:03 GMT

{"id":2,"content":"Hello, kem198!"}%
```

#### `/api/fizzbuzz/v1`

```sh
$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/api/fizzbuzz/v1/convert?num=3' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:43:50 GMT

{
  "result": "Fizz"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/api/fizzbuzz/v1/convert' | jq
HTTP/1.1 400
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:44:28 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Missing Parameter",
  "status": 400,
  "detail": "The 'num' query parameter is required.",
  "instance": "/api/fizzbuzz/v1"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/api/fizzbuzz/v1/convert?num=abc' | jq
HTTP/1.1 400
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:44:49 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Invalid Number Format",
  "status": 400,
  "detail": "The 'num' query parameter must be a valid integer.",
  "instance": "/api/fizzbuzz/v1"
}
```

## References

### Installation

- [SDK Installation Candidates \| SDKMAN! the Software Development Kit Manager](https://sdkman.io/sdks/)
- [Spring Boot in Visual Studio Code](https://code.visualstudio.com/docs/java/java-spring-boot)

### Getting Started

- [Spring Boot REST API の作成 - 公式サンプルコード](https://spring.pleiades.io/guides/gs/rest-service)

### API

- [エラーレスポンス :: Spring Framework - リファレンス](https://spring.pleiades.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html)
    - [RFC 9457 - Problem Details for HTTP APIs](https://datatracker.ietf.org/doc/html/rfc9457)
    - [RFC 9457 - Problem Details for HTTP APIs 日本語訳](https://tex2e.github.io/rfc-translater/html/rfc9457.html)
    - [REST API Common Spec としての HTTP Status Code と Error の提案 - Affamative Way](https://cos31.hatenablog.jp/entry/2023/12/14/093435)
- [Google Tasks API  \|  Google for Developers](https://developers.google.com/workspace/tasks/reference/rest?hl=ja)

### curl

- [curl -s -f -D /dev/stderr が優勝 – 株式会社ルーター](https://rooter.jp/web-crawling/curl-s-f-d-dev-stderr-is-the-winner/)
