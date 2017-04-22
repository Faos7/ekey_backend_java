# E-Key
From Paper To Web

## Run App
```sh
$ mvn spring-boot:start
```
## Technologies list
Java8

Spring boot

Spring security

PostgresQL

HickariCP

Hibernate

Java mail

## About

This is backend for Ekey project which won in student hackaton provided by Noosphere Engeneering school;

The project is electronic simulation of students library ticket. This project was invited to decrease paper documentation and create a single base of books for all Universities, which would use this product.
Therefor student from any university would be able to come to any other university, and, using his(her) studentID, take/read/return book. 

DB simulates students and librarians in university

Authorization is made using CRSF token stored in cookies

API can send password into Email.

## Info
server starts at http://127.0.0.1:9000

##Controller
University controller is at http://127.0.0.1:9000/university

http://127.0.0.1:9000/university/ <-- get all universities

http://127.0.0.1:9000/university/{id} <-- get university with id

Creating university through POST, deleting through DELETE mapping;

All other REST API in this project works the same way

##Have fun with this stuff!
