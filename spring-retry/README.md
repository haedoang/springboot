# spring-retry 

- `spring-retry`는 실패 작업을 자동으로 다시 호출하는 기능을 제공한다
- 오류가 일시적인 경우 유용하게 사용할 수 있다 
  - ex) 일시적인 네트워크 결함
  

### @EnableRetry
- 실패 시 재시도를 하도록 `spring-retry` 를 활성화한다

### @Retryable(value="Exception.class")
- 예외 발생 시 재시도할 메서드에 선언한다
- 해당 예외가 발생할 경우 메서드를 재시도(기본값 3)하고 그래도 실패할 경우 예외를 반환한다
- `maxAttempts` 로 재시도 횟수를 지정할 수 있다 => 기본값 3
- `backOff` 로 retry 에 지연을 줄 수 있다 => 기본값 1000 (1초)

### @Recover
- 예외 발생 후 처리를 담당하는 메서드에 선언한다
- 주의해야할 점은 예외발생 메서드에서 예외를 위로 던지는 경우 `@Recover` 가 선언된 메서드가 캐치하는 점에 있다
  - 코드를 읽을 때 헷갈릴 수 있을 것 같으며, 불필요한 예외처리가 발생될 수 있을 것 같다


### 주의사항

- 현재 최신 버전인 1.3.3 버전이 취약점 이슈에 등록되어 있어서 사용에 문제가 있어 보인다
  - [https://mvnrepository.com/artifact/org.springframework.retry/spring-retry/1.3.3](https://mvnrepository.com/artifact/org.springframework.retry/spring-retry/1.3.3)