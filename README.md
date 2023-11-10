# Spring Data JDBC Sandbox 

Simple test to observe null value binding behavior in pgJDBC.

(Java 17+ required, OpenJDK compatible)           

To build and run against localhost db named 'test':

```shell
./mvnw clean install
java -jar target/jdbc-sandbox-1.0.0-SNAPSHOT.jar --ignore
```

The `--ignore` is short for setting `-Dspring.jdbc.getParameterType.ignore=true`

To run against another host/db:

```shell
./mvnw clean install
java -jar target/jdbc-sandbox-1.0.0-SNAPSHOT.jar \
"--Dspring.datasource.url=jdbc:postgresql://domain.com:26257/testXYZ?sslmode=disable" \
"--Dspring.datasource.user=root"
--ignore
```