

## client 


### 3개의 properties 값을 읽어온다
```text
foo-development.properties, foo-development-db.properties, foo.properties
```


config health indicate
> curl localhost:8080/actuator/health


actuator refresh
- @RefreshScope 빈 내 @Value 값을 갱신함 (@ConfigurationProperties 는 불필요)
> curl localhost:8080/actuator/refresh

- 요청이 번거로울 있음, spring-cloud-bus 로 refresh 가능하다고 한다