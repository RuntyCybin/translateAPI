# translateAPI

REST API for translating text from Estonian to English, built with **Java 21** and **Spring Boot 3**, following **Hexagonal Architecture** (Ports & Adapters).

---

## Architecture

The project is structured as a Maven multi-module build that enforces strict separation of concerns:

```
translateAPI/          ← parent POM (aggregator)
├── domain/            ← core entities and business rules (no dependencies)
├── application/       ← use cases, ports (interfaces), and services
└── infrastructure/    ← adapters: HTTP controllers, external API clients, config
```

### Dependency rule

```
infrastructure  →  application  →  domain
```

`domain` knows nothing about the outside world. `application` defines what it needs via ports. `infrastructure` wires everything together.

---

## Modules

### `domain`
Contains the core model with zero framework dependencies.

| Class         | Description                                  |
|---------------|----------------------------------------------|
| `Translation` | Record holding `text`, `fromLg`, and `toLg`  |

### `application`
Contains the business logic and the port interfaces that decouple the core from delivery/persistence mechanisms.

```
port/in/    ← driving ports  (use-case interfaces called by adapters)
port/out/   ← driven ports   (interfaces the core needs implemented externally)
service/    ← use-case implementations
```

### `infrastructure`
Wires the application to the real world.

```
adapter/in/web/   ← HTTP adapters (Spring MVC controllers)
adapter/out/      ← outbound adapters (translation provider clients, etc.)
Main.java         ← Spring Boot entry point
```

---

## Tech stack

| Layer        | Technology                      |
|--------------|---------------------------------|
| Language     | Java 21                         |
| Framework    | Spring Boot 3.4                 |
| Build        | Maven 3 (multi-module)          |
| Mapping      | MapStruct                       |
| Boilerplate  | Lombok                          |
| API docs     | SpringDoc OpenAPI (Swagger UI)  |

---

## Getting started

### Prerequisites
- Java 21+
- Maven 3.9+

### Build

```bash
mvn clean install
```

### Run

```bash
java -jar infrastructure/target/infrastructure-*.jar
```
o
```bash
mvn spring-boot:run -pl infrastructure
```

Swagger UI is available at `http://localhost:8080/swagger-ui/index.html` once the application is running.

---

## API

| Method   | Path             | Description                                |
|----------|------------------|--------------------------------------------|
| `POST`   | `/translations`  | Translate a text from Estonian to English  |

Request body example:

```json
{
  "text": "Tere maailm",
  "fromLg": "et",
  "toLg": "en"
}
```

---

## Project structure

```
translateAPI/
├── pom.xml                          ← parent POM
├── domain/
│   └── src/main/java/com/cybindev/
│       └── Translation.java
├── application/
│   └── src/main/java/com/cybindev/
│       ├── port/
│       │   ├── in/
│       │   └── out/
│       └── service/
└── infrastructure/
    └── src/main/java/com/cybindev/
        ├── Main.java
        └── adapter/
            ├── in/web/
            │   └── TranslationHttpAdapter.java
            └── out/
```