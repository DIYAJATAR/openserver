# OpenServer Backend

A Spring Boot REST API for monitoring and controlling an OpenServer environment. The backend exposes system health, power, temperature, metrics, and event-management APIs.

## Features

* System health and online-status monitoring
* Power ON/OFF control
* System restart endpoint
* Temperature monitoring and simulation
* System metrics
* System event retrieval and checks
* RESTful API architecture
* Spring Boot backend
* Maven-based build

## Technology Stack

* **Java**
* **Spring Boot**
* **Spring Web / REST APIs**
* **Maven**
* **Git / GitHub**

## Project Structure

```text
openserver/
├── .mvn/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/diya/openserver/
│       │       ├── controller/
│       │       └── ...
│       └── resources/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Getting Started

### Prerequisites

* Java installed
* Git
* Maven Wrapper included with the project

### Run the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## API Endpoints

### System Status

```http
GET /api/system/status
```

Example:

```json
{
  "health": "HEALTHY",
  "power": "ON",
  "status": "ONLINE",
  "server": "OpenServer"
}
```

### System Metrics

```http
GET /api/system/metrics
```

### Power

```http
GET  /api/system/power
POST /api/system/power/on
POST /api/system/power/off
POST /api/system/power/restart
```

### Temperature

```http
GET  /api/system/temperature
POST /api/system/temperature/simulate
```

### Events

```http
GET  /api/system/events
POST /api/system/events/check
```

## Local Verification

The backend has been tested locally with:

```powershell
curl.exe http://localhost:8080/api/system/status
```

Response:

```json
{
  "health": "HEALTHY",
  "power": "ON",
  "status": "ONLINE",
  "server": "OpenServer"
}
```

This confirms that the Spring Boot application is running and the system-status API is responding successfully.

## Development

Build the project:

```powershell
.\mvnw.cmd clean package
```

Run tests:

```powershell
.\mvnw.cmd test
```

Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

## API Testing

The REST endpoints can be tested using:

* PowerShell / cURL
* Postman
* Any REST API client

Example:

```powershell
curl.exe http://localhost:8080/api/system/status
```

## Future Improvements

* Add persistent system metrics storage
* Add authentication and authorization
* Add automated integration tests
* Add API documentation with OpenAPI/Swagger
* Add Docker support
* Add CI/CD pipeline
* Add centralized monitoring and logging

## License

This project is intended for development, demonstration, and open-source contribution purposes.
