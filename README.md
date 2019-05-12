# simple-gis
# v1.0.0

This is a simple realization of a geo information system.

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
N - open localhost:8080/simple-gis/swagger-ui.html for a rest controller (with swagger)