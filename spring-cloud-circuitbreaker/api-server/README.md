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

### Bulkhead

### Rate Limiter

### Retry

### Time Limiter

### Cache


## test 

> curl localhost:8080/get

- fallback test
> curl localhost:8080/delay/3
