# TodoWebApp

This is a basic Todo-List Web Aplication which consists of Add, Edit and Remove options for the todo items.

# Technologies
## Back-end
* JAX-RS
* Jersey
* JPA
* Hibernate
* H2 Database - Since it is JPA implementation database can be replaced with any RDBMS but by default it uses inmemory H2 database

## Front-end
* Backbone
* Bootstrap
* JQuery

# Instructions to run the app
Make sure you install Maven. You can verify it by running "mvn --version" on terminal.

Run:  
mvn clean install  
mvn tomcat7:run

When you see `INFO: Starting ProtocolHandler ["http-bio-8080"]` in the logs, open browser and point to http://localhost:8080/todowebapp/
