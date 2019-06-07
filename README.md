Warehouse Transfer App

App for managing item transfer logistics between warehouses. App is split in two microservices:
  one for communicating with the database and preparing data
  one for front-end views and forms
 
Both apps are designed to be run in separate Docker containters.

Developed as a final project for Java Bootcamp. 

Uses: Spring Boot, Hibernate, JPA, Spring Security, Docker

TO RUN:
 mvn clean install in "warehouse-transer" and "warehouse-transfer-client" folders
 docker build & docker run both apps
 exposed ports: 
  backend: 8081
  frontend: 8080
