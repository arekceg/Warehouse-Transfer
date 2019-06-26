# Warehouse Transfer App

App for managing item transfer logistics between warehouses. App consits of two elements:
  - REST API 
  - Client application with simple frontend functionality
 
Both apps are designed to be run in separate Docker containters. Main database is hosted on Google Cloud Services. Github app version uses local databases for easier Docker deployment.

Developed as a final project for Java Bootcamp. 

**Uses: Spring Boot, Hibernate, JPA, Spring Security, Docker, Google Cloud**

# To run:
  - mvn clean install in client and api modules
  - docker-compose up
  
Client port exposed: 8080

Stock logins are: admin@admin; aa@aa; bb@bb; ff@ff
