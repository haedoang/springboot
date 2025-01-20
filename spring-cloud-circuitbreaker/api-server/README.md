## default config
- InMemoryTimeLimiterRegistry
  - `TimeLimiterConfig.ofDefault()`
    - PT1S
      - [P] eriod
      - [T] ime
      - 1 
      - [S] econd


## resilience4j features

### Circuit Breaker
- Status
  - CLOSED : 서킷 미동작
  - HALF_OPEN : 일부 요청에 대해 유입을 허용
  - OPEN : 서킷 가동으로 구현된 fallback 으로 요청 처리됨
- circuitBreaker name slow 를 테스트한다
- slidingWindowSize 만큼 실패율을 계산 후 서킷브레이커를 가동한다
- waitDurationInOpenState 만큼 대기 후 재 요청 HALF_OPEN 상태로 변하는지 확인하기
```sh
 for i in {1..20}; do curl localhost:8080/delay2/15; echo; done
```
```text
{"result":"fallback!","status":"CLOSED"}
{"result":"fallback!","status":"CLOSED"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
{"result":"fallback!","status":"OPEN"}
```

```sh
curl localhost:8080/circuit/slow
```
- 서킷 오픈 전/후의 상태를 확인하기 

### Bulkhead

### Rate Limiter

### Retry

### Time Limiter

### Cache

