# Reporter Project

A Spring Boot application that handles ISO8583 transaction reporting and organization API integration.

## Author

**Faraz Yazdani**
- Email: farazyazdanib@gmail.com

## Project Summary

This project is designed to:
- Process and store ISO8583 transaction data
- Generate reports based on transaction data
- Integrate with external organization APIs
- Provide RESTful APIs for data access and reporting
- Support automated data processing and reporting

## Technologies Used

### Core Technologies
- Java 8
- Spring Boot 2.6.3
- Spring Cloud 2021.0.0
- Spring Data JPA
- MySQL 8.0

### API Documentation
- Swagger/OpenAPI 3.0.0
- SpringFox

### Database
- MySQL 8.0
- Hibernate/JPA

### API Integration
- OpenFeign
- RSA Encryption

### Utilities
- Lombok
- MapStruct
- Log4j2
- Logstash GELF
- Commons CSV
- Commons VFS2
- JSch
- Commons Net
- Zip4j

## Prerequisites

- Java 8 JDK
- Maven 3.8+
- MySQL 8.0
- Docker and Docker Compose (for containerized deployment)

## Getting Started

### Local Development Setup

1. Clone the repository:
```bash
git clone https://github.com/farazyb/Insights_Project.git
cd reporter
```

2. Configure environment variables:
Create a `.env` file in the project root with the following content:
```properties
# Database Configuration
DB_HOST=localhost
DB_PORT=3306
DB_NAME=reporter
DB_USERNAME=reporter
DB_PASSWORD=reporter123

# Organization API Configuration
ORGANIZATION_API_KEY=your_api_key
ORGANIZATION_API_URL=your_api_url
ORGANIZATION_API_PUBLIC_KEY=your_public_key
ORGANIZATION_API_PRIVATE_KEY=your_private_key
```

3. Build the project:
```bash
mvn clean package
```

4. Run the application:
```bash
java -jar target/reporter-1.0.0-jar-with-dependencies.jar
```

### Docker Deployment

1. Build and run using Docker Compose:
```bash
docker-compose up --build
```

The application will be available at:
- Application: http://localhost:9100
- Swagger UI: http://localhost:9100/swagger-ui/
- API Documentation: http://localhost:9100/v2/api-docs

## API Documentation

### Available Endpoints

#### Report Generation
```http
GET /api/reports/by-date
```
Parameters:
- `fromDate`: Start date (yyyy-MM-dd)
- `toDate`: End date (yyyy-MM-dd)

Example:
```bash
curl -X GET "http://localhost:9100/api/reports/by-date?fromDate=2024-01-01&toDate=2024-01-31"
```

#### Transaction Data
```http
GET /api/transactions
```
Parameters:
- `from`: Start datetime (yyyy-MM-dd HH:mm:ss)
- `to`: End datetime (yyyy-MM-dd HH:mm:ss)

Example:
```bash
curl -X GET "http://localhost:9100/api/transactions?from=2024-01-01%2000:00:00&to=2024-01-31%2023:59:59"
```

#### Cut-off Time Management
```http
GET /api/cutoff-time
```
Get the current cut-off time configuration.

```http
POST /api/cutoff-time
```
Update the cut-off time configuration.

Example:
```bash
curl -X POST "http://localhost:9100/api/cutoff-time" \
     -H "Content-Type: application/json" \
     -d '{"cutoffTime": "2024-01-31T23:59:59"}'
```

## Database Schema

### Main Tables

1. `ISO8583Fields_V1987`
   - Stores ISO8583 transaction data
   - Contains all standard ISO8583 fields
   - Includes audit fields (created/modified timestamps)

2. `CutOffTime`
   - Manages system cut-off time configuration
   - Used for report generation scheduling

## Security

The application implements:
- RSA encryption for API communication
- Secure password storage
- API key authentication
- HTTPS support

## Logging

The application uses Log4j2 with:
- Console logging
- File logging
- Logstash integration
- GELF format support

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 
