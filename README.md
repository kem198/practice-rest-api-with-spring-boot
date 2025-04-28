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
    - [Run for Development](#run-for-development)
    - [Build](#build)
    - [Run](#run)
- [Examples](#examples)
    - [Setup](#setup)
    - [Edit](#edit)
    - [Build and Run](#build-and-run)
    - [Request to the APIs](#request-to-the-apis)
- [References](#references)
    - [Installation](#installation)
    - [Getting Started](#getting-started)
    - [API](#api)
    - [curl](#curl)

## Requirements

### Required

- [JDK 21](https://openjdk.org/projects/jdk/21/)

### Recommended

- [SDKMAN!](https://sdkman.io/)
- [Visual Studio Code](https://azure.microsoft.com/ja-jp/products/visual-studio-code)

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

## Examples

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

### Edit

```shell
# Move to repository root
$ cd practice-rest-api-with-spring-boot

# Open in VS Code
$ code .
```

### Build and Run

```shell
# Here is repository root
$ pwd
/path/to/your/repo/practice-rest-api-with-spring-boot

# Build the application
$ ./gradlew build

# Run the application
$ java -jar build/libs/practice-rest-api-with-spring-boot-0.0.1-SNAPSHOT.jar
```

### Request to the APIs

Request `/api/v1/greeting` :

```sh
$ curl -i 'http://localhost:8080/api/v1/greeting'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:32:49 GMT

{"id":1,"content":"Hello, World!"}%

$ curl -i 'http://localhost:8080/api/v1/greeting?name=kem198'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:33:03 GMT

{"id":2,"content":"Hello, kem198!"}%
```

Request `/api/v1/fizzbuzz` :

```sh
$ curl -s -D /dev/stderr 'http://localhost:8080/api/v1/fizzbuzz?num=3' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:43:50 GMT

{
  "result": "Fizz"
}

$ curl -s -D /dev/stderr 'http://localhost:8080/api/v1/fizzbuzz' | jq
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
  "instance": "/api/v1/fizzbuzz"
}

$ curl -s -D /dev/stderr 'http://localhost:8080/api/v1/fizzbuzz?num=abc' | jq
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
  "instance": "/api/v1/fizzbuzz"
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

### curl

- [curl -s -f -D /dev/stderr が優勝 – 株式会社ルーター](https://rooter.jp/web-crawling/curl-s-f-d-dev-stderr-is-the-winner/)
