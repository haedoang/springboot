## Caching

### Cache 설정 하기
- 특정한 캐시 라이브러리를 추가하지 않았다면 스프링부트는 in-memory에서 concurrent Map을 사용하는 simple provider를 설정한다
- `@EnableCaching`: 캐싱 활성화.
- `ConcurrentMapCacheManager`: CacheManager 구현체로 기본값.
    - `ConcurrentMapCacheManager`는 동적 캐시 생성, 정적 캐시 생성 방식을 지원한다



### CacheManager 
- 캐시 추상화
- `cacheManager.getCacheNames()`: 캐시 조회 

### Cache 적용하기
- 메서드 레벨에 설정.
- `@Cacheable`의 `name`으로 구분한다
  ```java
  @Component
  public class UserService {
      @Cacheable("user")
      public User getCachedUser(String name) {
      }
  }
  ```

### 성능 비교 부하 테스트
- con1) 사전 요청 x
- cond2) vuser 100, 요청의 99% 이상이 1500mm 이내에 들어야 한다
- cond3) k6 
```text
  ✓ users/user1 successfully
  
       checks.........................: 100.00% ✓ 2900      ✗ 0
       data_received..................: 432 kB  14 kB/s
       data_sent......................: 284 kB  9.4 kB/s
       http_req_blocked...............: avg=438.16µs min=0s      med=0s      max=17.32ms p(90)=0s      p(95)=0s
       http_req_connecting............: avg=281.05µs min=0s      med=0s      max=12.97ms p(90)=0s      p(95)=0s
     ✓ http_req_duration..............: avg=41.91ms  min=40.02ms med=41.34ms max=61.75ms p(90)=43.47ms p(95)=46.1ms
         { expected_response:true }...: avg=41.91ms  min=40.02ms med=41.34ms max=61.75ms p(90)=43.47ms p(95)=46.1ms
       http_req_failed................: 0.00%   ✓ 0         ✗ 2900
       http_req_receiving.............: avg=55.65µs  min=0s      med=0s      max=4.91ms  p(90)=211.5µs p(95)=501.02µs
       http_req_sending...............: avg=32.21µs  min=0s      med=0s      max=2.52ms  p(90)=0s      p(95)=27.42µs
       http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s      p(90)=0s      p(95)=0s
       http_req_waiting...............: avg=41.82ms  min=40.02ms med=41.25ms max=60.76ms p(90)=43.2ms  p(95)=45.97ms
       http_reqs......................: 2900    95.803963/s
       iteration_duration.............: avg=1.04s    min=1.04s   med=1.04s   max=1.06s   p(90)=1.04s   p(95)=1.05s
       iterations.....................: 2900    95.803963/s
       vus............................: 100     min=100     max=100
       vus_max........................: 100     min=100     max=100
       
       
  ✓ users/user1/cached successfully
  
       checks.........................: 100.00% ✓ 3000      ✗ 0
       data_received..................: 447 kB  15 kB/s
       data_sent......................: 315 kB  11 kB/s
       http_req_blocked...............: avg=488.45µs min=0s med=0s      max=27.53ms p(90)=0s       p(95)=0s
       http_req_connecting............: avg=326.57µs min=0s med=0s      max=20.95ms p(90)=0s       p(95)=0s
     ✓ http_req_duration..............: avg=1.63ms   min=0s med=1.1ms   max=19ms    p(90)=2.46ms   p(95)=3.9ms
         { expected_response:true }...: avg=1.63ms   min=0s med=1.1ms   max=19ms    p(90)=2.46ms   p(95)=3.9ms
       http_req_failed................: 0.00%   ✓ 0         ✗ 3000
       http_req_receiving.............: avg=131.72µs min=0s med=0s      max=14.77ms p(90)=527.84µs p(95)=600.5µs
       http_req_sending...............: avg=46.29µs  min=0s med=0s      max=5.46ms  p(90)=0s       p(95)=179.35µs
       http_req_tls_handshaking.......: avg=0s       min=0s med=0s      max=0s      p(90)=0s       p(95)=0s
       http_req_waiting...............: avg=1.45ms   min=0s med=999.8µs max=17.5ms  p(90)=2.08ms   p(95)=3.63ms
       http_reqs......................: 3000    99.623118/s
       iteration_duration.............: avg=1s       min=1s med=1s      max=1.03s   p(90)=1s       p(95)=1s
       iterations.....................: 3000    99.623118/s
       vus............................: 100     min=100     max=100
       vus_max........................: 100     min=100     max=100
```
