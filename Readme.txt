# step5
server with database + University controller
## Run App
```sh
$ mvn spring-boot:start
```
## Info
server starts at http://127.0.0.1:8080
if there is alike "Server started succesfully!" it means that server is running now

##Controller
University controller is at http://127.0.0.1:8080/university
http://127.0.0.1:8080/university/ <-- get all universities
http://127.0.0.1:8080/university/{id} <-- get university with id
http://127.0.0.1:8080/university/delete?id={id} <-- delete university with id
http://127.0.0.1:8080/university/create?name=[name] <-- create university with name

##Have fun with this stuff!