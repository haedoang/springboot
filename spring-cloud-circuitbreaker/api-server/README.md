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
- 시스템 안정성 제어를 위한 패턴으로 한 작업에 대한 동시 사용을 제한한다
- 장애를 격리할 수 있음
- 특정 API 가 시스템 전체의 자원을 독점하는 것을 방지함
- `BulkheadFullException`
```text
~/Desktop/repo/springboot/spring-cloud-circuitbreaker/k6 (master ✗) k6 run --vus 5 --duration 10s bulkhead.js

         /\      Grafana   /‾‾/
    /\  /  \     |\  __   /  /
   /  \/    \    | |/ /  /   ‾‾\
  /          \   |   (  |  (‾)  |
 / __________ \  |_|\_\  \_____/

     execution: local
        script: bulkhead.js
        output: -

     scenarios: (100.00%) 1 scenario, 5 max VUs, 40s max duration (incl. graceful stop):
              * default: 5 looping VUs for 10s (gracefulStop: 30s)


     ✗ is status 200
      ↳  97% — ✓ 240 / ✗ 5
     ✗ body contains "it works!"
      ↳  97% — ✓ 240 / ✗ 5

     checks.........................: 97.95% 480 out of 490
     data_received..................: 31 kB  3.0 kB/s
     data_sent......................: 22 kB  2.2 kB/s
     http_req_blocked...............: avg=39.34µs  min=3µs      med=6µs      max=1.35ms   p(90)=10µs     p(95)=23.39µs
     http_req_connecting............: avg=10.35µs  min=0s       med=0s       max=305µs    p(90)=0s       p(95)=0s
     http_req_duration..............: avg=3.41ms   min=600µs    med=1.17ms   max=129.9ms  p(90)=1.5ms    p(95)=1.86ms
       { expected_response:true }...: avg=1.82ms   min=600µs    med=1.16ms   max=81.01ms  p(90)=1.45ms   p(95)=1.74ms
     http_req_failed................: 2.04%  5 out of 245
     http_req_receiving.............: avg=57.18µs  min=19µs     med=44µs     max=588µs    p(90)=72.59µs  p(95)=90.99µs
     http_req_sending...............: avg=34.83µs  min=7µs      med=16µs     max=881µs    p(90)=36µs     p(95)=41µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=3.32ms   min=569µs    med=1.09ms   max=128.61ms p(90)=1.44ms   p(95)=1.8ms
     http_reqs......................: 245    24.434913/s
     iteration_duration.............: avg=204.48ms min=200.75ms med=201.82ms max=334.98ms p(90)=202.66ms p(95)=203.61ms
     iterations.....................: 245    24.434913/s
     vus............................: 5      min=5          max=5
     vus_max........................: 5      min=5          max=5
```
### Rate Limiter

### Retry

### Time Limiter

### Cache

