# simple-gis
# v1.6.0

This is a simple realization of a geo information system via microservice arhitecture:
1 - api - service consists of 1 controller:
    1.1 - SearchController - allows to get address objects via request string
2 - online - service allows to find address object via web interface & see it on a map

# Used technologies:
1 - Spring-Boot 2.x
2 - Spring-Cloud Feign
3 - Spring Data Jpa Repositorues & Hibernate 5.x
4 - Thymeleaf
6 - Swagger 2.x    
7 - JSR-313 POJO validation
8 - Lombok

Before start:
! import db into mysql ({project}/doc/gis2_novosib/gis2_novosib.sql)

For running service via Tomcat:
! require tomcat v9+
1 - go to project directory via terminal
2 - run command - mvn package
3 - deploy and run war file on tomcat or (if docker is exist) run command in terminal - docker run -it -p 8888:8080 -v $(pwd)/target/org.group.projects.simple.gis-0.0.0-SNAPSHOT.war:/usr/local/tomcat/webapps/simple-gis.war tomcat:latest

For running service via IDE:
1 - create maven configuration with param - tomcat7:run
2 - run project

For open service:
1 - open localhost:8080/simple-gis on a browser for a main page activation
2 - autocomplete is activated after 3+ chars on input field
3 - for open address object on map press search ("-->") or key "enter"
***
N - open localhost:8081/simple-gis/api/swagger-ui.html for a rest controller (with swagger)
N+1 - open localhost:8082/simple-gis/ for a web site use