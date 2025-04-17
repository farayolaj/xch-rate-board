# Exchange Rate Board

## Overview

The Exchange Rate Board application provides a REST API for retrieving the latest exchange rates. It is built using Java and Spring Boot.

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- A database (H2 is used for testing purposes)

### Setup

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd xch-rate-board
   ```

2. Build the project:

   ```bash
   ./mvnw clean install
   ```

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will start on `http://localhost:8080` by default.

## API Endpoints

### GET /api/rates/latest

- **Description**: Retrieves the latest exchange rates.
- **Response**: A JSON array of exchange rates in the following format:
  ```json
  [
    {
      "currencyPair": "USD/EUR",
      "rate": 0.85
    },
    {
      "currencyPair": "GBP/USD",
      "rate": 1.39
    }
  ]
  ```

## Database

The application uses an H2 in-memory database for testing. The database schema is initialized using the SQL script located at `src/main/resources/db/migration/V1__.sql`.

## License

This project is licensed under the MIT License.
