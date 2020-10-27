### How To Build

##### command line call:
```
./mvnw clean package
```

### How To Run

##### command line call:
```
java -jar target/fetchrewards-server-1.0.0-spring-boot.jar
```

### How To Test

##### command line call:
```
curl -s "http://localhost:7701/actuator/health"
curl -H 'Content-Type: application/json' -d '["test.email@gmail.com","test.email+spam@gmail.com","testemail@gmail.com","test.email@gmail.com","test.email@fetchrewards.com"]' -s "http://localhost:7701/api/email/unique-count"
```
