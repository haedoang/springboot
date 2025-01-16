## spring cloud gateway

### zuul vs gateway
  | 특징 | Spring Cloud Zuul | Spring Cloud Gateway |
  |------|------------------|---------------------|
  | 아키텍처 | 서블릿 기반 (동기/블로킹) | 리액티브 스택 기반 (비동기/논블로킹) |
  | 성능 | 상대적으로 낮음 | Netty 기반으로 높은 성능 |
  | JDK 요구사항 | JDK 8 이상 | JDK 8 이상 |
  | 유지보수 상태 | Zuul 1.x는 유지보수 모드 | 활발한 개발 및 지원 중 |
  | 필터 체인 | Pre, Route, Post 필터 | Pre, Route, Post 필터 + 더 유연한 설정 |
  | 설정 방식 | Java 설정 또는 properties 파일 | Java DSL 또는 properties 파일 |
  | 라우팅 | URL 패턴 매칭 | 경로, 헤더, 호스트 등 다양한 조건 기반 |
  | 웹플럭스 지원 | 미지원 | 지원 |
  | 로드밸런싱 | Ribbon 기반 | LoadBalancerClient 기반 |
  | 서킷브레이커 | Hystrix 통합 | Resilience4j 통합 |
  | 메모리 사용량 | 상대적으로 높음 | 효율적인 메모리 사용 |

### starter 
- spring-cloud-starter-gateway
  - reactive(webflux)
  - netty
  - async/non-blocking
  - 높은 성능, 확장성
  - reactor 기반
- spring cloud starter-gateway-mvc
  - 서블릿 기반
  - tomcat
  - sync/blocking
  - spring mvc 호환성이 좋음
  - webflux 의존성 필요없음