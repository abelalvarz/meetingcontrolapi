# Instructions
## Test application options.
### Option #1.
1. Open project in any IDE,
2. You need to have Java 17 installed,
3. Run the applications, it will run on port 8080 by default
4. Next, go to any browser and go to any of next urls,
- Get times with at least 3 users available: http://localhost:8080/api/times/available
- Get Times with users on meetings: http://localhost:8080/api/times/busy
Get Times Catalog: http://localhost:8080/api/times/slots
or you can use POSTMAN and make use GET method for any of the provided urls.

### Option #2.
1. Docker should be installed and running.
2. Open a terminal
3. Execute command [ docker pull abelalvarzdev/meetingcontrol ]
4. Execute command [ docker run -p 8080:8080 abelalvarzdev/meetingcontrol ]
5. Next, go to any browser and go to any of next urls,
- Get times with at least 3 users available: http://localhost:8080/api/times/available
- Get Times with users on meetings: http://localhost:8080/api/times/busy
- Get Times Catalog: http://localhost:8080/api/times/slots
  or you can use POSTMAN and make use GET method for any of the provided urls.

## API Documentation
This API uses Swagger to be documented, once application is running, go to http://localhost:8080/swagger-ui.html
to check swagger documentation.

## H2 Database on Memory Access 

This API uses H2 on Memory Database, once application is running, go to http://localhost:8080/h2-console
to check database and enter next information on Login Form.
JDBC URL: jdbc:h2:mem:taskeasy
User Name: sa
Password: password
