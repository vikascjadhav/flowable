## Flowable example

### Run main class
### URLS
H2Console
http://localhost:8080/h2-console

Flowable actuator
http://localhost:8080/atoms/actuator/flowable OR
http://localhost:8080/actuator/flowable

http://localhost:8080/actuator - spring actuator


- Api docs which can be integrated with swagger
- http://localhost:8080/v3/api-docs
- http://localhost:8080/swagger-ui/index.html

### IMP Commands for testing 

Windows Curl commands for testing
 - `curl -H "Content-Type: application/json" -d "{ \"assignee\": \"trademakers\" }" "http://localhost:8080/process"`
 - - `curl -H "Content-Type: application/json" -d "{ \"assignee\": \"jbarrez\" }" "http://localhost:8080/process"`
 - 
 - `curl http://localhost:8080/tasks?assignee=1`   
## Issues
- Sometimes actuator will not work in Idea just invalidate cache and reload project again
- 