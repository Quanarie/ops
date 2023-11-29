# Order Processing System

## Overview
This Java-based Order Processing System is designed to 
facilitate the interaction between Sellers and Buyers. 
It allows Buyers to create orders from available offers,
while Sellers have the capability to create offers. 
The system ensures data validation and security, 
subsequently writing user requests to a PostgreSQL database.

## Technologies Used
- **Spring Boot**: For building stand-alone, production-grade Spring based applications.
- **Spring Security**: Provides authentication and authorization functionalities.
- **RESTful Services**: Handling HTTP requests in a RESTful manner.
- **JUnit**: Used for unit testing various components.
- **Hibernate**: ORM tool for database operations.
- **PostgreSQL**: The database system used.
- **Gradle**: For building and managing dependencies.

## Setup and Installation
- **Prerequisite**: Ensure you have Java and Gradle installed on your system.
- **Building the Project**: Simply clone the repository and run `gradle build` to build the project.

## Testing
- The project includes unit tests for User, Offer, and Order requests. Run these tests to ensure the reliability and integrity of these components.

## Note
- This project is designed as a showcase of skills and understanding in Spring framework and related technologies listed above.