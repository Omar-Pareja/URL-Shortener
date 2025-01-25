# URL Shortener

A Spring Boot application for shortening URLs, managing redirections, and viewing statistics.

## Features

- **Shorten URLs**: Generate a unique token for any long URL.
- **Redirect**: Use the token to access the original URL.
- **Statistics**: View mappings between long URLs and their short tokens.
- **Lightweight**: Uses in-memory storage for quick URL lookups.
- **RESTful API**: Easily integrate with other applications.

## Requirements

This project is written in Java using Spring Boot. Ensure you have the following installed:

- Java 11 or higher
- Maven

## Files

- `UrlShortenerApp.java`: The main class for running the application.
- `UrlController.java`: Handles RESTful endpoints for shortening, redirecting, and statistics.
- `UrlInput.java`: Represents the input structure for URL shortening requests.
- `UrlStats.java`: Provides URL mapping statistics.
- `application.properties`: Configuration file for Spring Boot.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/url-shortener.git
Navigate to the project directory:

bash
Copy
Edit
cd url-shortener
Build the project:

bash
Copy
Edit
mvn clean install
Run the application:

bash
Copy
Edit
mvn spring-boot:run
Access the API at http://localhost:8080.

API Endpoints
1. Generate a Short URL
Endpoint: /service/generate
Method: POST
Request Body:
json
Copy
Edit
{
  "originalUrl": "https://example.com"
}
Response:
json
Copy
Edit
{
  "shortUrl": "http://localhost:8080/service/abc123"
}
2. Redirect to the Original URL
Endpoint: /service/{token}
Method: GET
Behavior: Redirects to the original URL associated with the token.
3. View URL Statistics
Endpoint: /service/overview
Method: GET
Response:
json
Copy
Edit
{
  "longToShortMap": {
    "https://example.com": "abc123"
  },
  "shortToLongMap": {
    "abc123": "https://example.com"
  }
}
Example Usage
Send a POST request to /service/generate with a long URL to receive a shortened URL.
Use the shortened URL in your browser or application to be redirected to the original URL.
Access /service/overview to see the stored mappings.
