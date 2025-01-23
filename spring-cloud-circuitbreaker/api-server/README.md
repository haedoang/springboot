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
- 특정 시간 단위당 허용되는 요청 수를 제한
- 초당/분당 시간단위로 허용할 요청 개수를 정의함 
- 제한된 요청을 대기하거나 거부 시킴
- 과도한 트래픽 요청 방지
- 테스트 시나리오
  - 초기 10개의 요청은 허용, 이후의 요청에 대해서 RequestNotPermitted 예외 발생 이후 return  429 body: fallback 
> k6 run rateLimiter.js
```text
➜  k6 git:(master) ✗ k6 run rateLimiter.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

     execution: local
        script: rateLimiter.js
        output: -

     scenarios: (100.00%) 1 scenario, 1 max VUs, 10m30s max duration (incl. graceful stop):
              * default: 20 iterations shared among 1 VUs (maxDuration: 10m0s, gracefulStop: 30s)


     ✓ is status 200
     ✓ body contains "it works!"
     ✓ is status 429
     ✓ body contains "fallback"

     checks.........................: 100.00% ✓ 40       ✗ 0
     data_received..................: 2.4 kB  48 B/s
     data_sent......................: 1.8 kB  36 B/s
     http_req_blocked...............: avg=38.44µs min=0s       med=2µs      max=695µs  p(90)=9.1µs   p(95)=44.24µs
     http_req_connecting............: avg=13µs    min=0s       med=0s       max=260µs  p(90)=0s      p(95)=12.99µs
     http_req_duration..............: avg=2.5s    min=590µs    med=2.53s    max=5.01s  p(90)=5.01s   p(95)=5.01s
       { expected_response:true }...: avg=7.22ms  min=590µs    med=740.49µs max=64.4ms p(90)=8.32ms  p(95)=36.36ms
     http_req_failed................: 50.00%  ✓ 10       ✗ 10
     http_req_receiving.............: avg=71.84µs min=7µs      med=60.5µs   max=221µs  p(90)=170.2µs p(95)=200.1µs
     http_req_sending...............: avg=21.45µs min=2µs      med=12.5µs   max=65µs   p(90)=49.3µs  p(95)=52.64µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s       max=0s     p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.5s    min=575µs    med=2.53s    max=5.01s  p(90)=5.01s   p(95)=5.01s
     http_reqs......................: 20      0.398639/s
     iteration_duration.............: avg=2.5s    min=618.04µs med=2.53s    max=5.01s  p(90)=5.01s   p(95)=5.01s
     iterations.....................: 20      0.398639/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```
### Retry
- 특정 요청에 대해 재시도 설정을 할 수 있ㅇ므
- 일시적 장애 대응 및 네트워크 관련 오류 처리
- 성능 오버헤드가 발생할 수 있으며, 부하가 발생할 수 있다(운영 시 경험함...)
### Time Limiter
- 긴 작업에 대해서 TimeOutException 예외를 발생시킨다.
- default 1s 미만인 경우 TimeOutException 발생 후 circuitBreaker 동작 확인
> curl localhost:8080/delay/{seconds}
### Cache

