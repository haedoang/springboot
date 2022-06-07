## logging
- 스프링부트는 내부 로깅에 `Commons Logging`을 사용한다
- 로그 구현체를 선택할 수 있다
  - `java.util.logging`, `log4j2`, `Logback` 
- 스타터 팩을 사용하는 경우 `Logback` 으로 로깅한다
- Logback 라우팅을 포함하기 떄문에 `java.util.logging`, `Commons Logging`, `Log4j`, `Slf4j` 등의 의존 라이브러리 모두 잘 동작한다
- 애플리케이션을 서블릿 컨테이너나 애플리케이션 서버 배포 시 `java.util.logging` API 로그는 라우팅되지 않는다


### Log Format
  ```text
  2022-06-07 10:02:07.932  INFO 10908 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
  ```
- 날짜와 시간
- 로그 레빌 : `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`
- 프로세스 ID
- 로그 메시지 시작 구분자: `---`
- 스레드 명
- 로거 이름
- 로그 메시지

### Console Output
- 디폴트 로그 설정에선 로그를 작성하면 `콘솔`에 메시지를 출력한다
  - `ERROR`, `WARN`, `INFO` 레벨의 메시지를 남긴다
- `--debug` 플래그로 모드를 활성화 할 수 있다
  > java -jar app.jar --debug
- 디버그 모드를 활성화하면 몇가지 코어 로거들(임베디드 컨테이너, Hibernate, 스프링부트) 설정의 더 많은 정보를 출력한다
- `trace` 모드를 활성화해도 된다 

#### Color-coded Output
- ANSI 지원 터미널 색상 출력을 통해 가독성을 개선할 수 있다

### File Output
- 기본값은 콘솔이기 때문에 파일 작성 시 `logging.file.name`, `logging.file.path` 프로퍼티를 설정해야 한다
- `logging.file.name`, `logging.file.path` 둘 다 없는 경우 => 콘솔 기록
- `logging.file.name` 만 있는 경우 => 현재 디렉토리를 기준으로 파일을 생성한다
- `logging.file.path` 만 있는 경우 => `spring.log` 파일이 해당 경로에 생성된다
- 기본 설정은 로그 파일이 `10MB`에 도달하면 로테이트되며, `ERROR`, `WARN`, `INFO` 레벨의 메시지를 남긴다

### File Rotation
- Logback 사용 시 `application.properties`, `application.yml` 에서 로그 로테이션 설정을 변경할 수 있다.
- [Core Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.core)

### Log Levels
- 지원하는 로깅 시스템 모두 `logging.level.<logger-name>=<level>`을 통해 스프링 `Environment`에 로거 레벨을 설정할 수 있다
  ```properties
  logging.level.root=warn
  logging.level.org.springframework.web=debug
  logging.level.org.hibernate=error
  ```

### Log Groups 
- 스프링 부트에서 스프링 `Envoronment` 에 로그 그룹을 정의할 수 있다
  ```properties
  logging.group.tomcat=org.apache.catalina,org.apache.coyote,org.apache.tomcat
  
  logging.level.tomcat=trace
  ```
- 스프링 부트에서 정의된 로그 그룹
  - web: 
    - `org.springframework.core.codec`
    - `org.springframework.http`
    - `org.springframework.web`
    - `org.springframework.boot.web.servlet.ServletContextInitializerBeans`
  - sql:
    - `org.springframework.jdbc.core`
    - `org.hibernate.SQL`
    - `org.jooq.tools.LoggerListener`

### Using a Log Shutdown Hook
- 애플리케이션 종료 시 로깅 리소스를 반환할 수 있도록  JVM 종료 시 셧다운 훅을 통해 로그 시스템 정리를 트리거한다
- `war` 배포하지 않으면 자동으로 등록된다
  ```properties
  logging.register-shutdown-hook=false
  ```

### Custom Log Configuration
- 클래스패스에 적절한 라이브러리를 추가하는 것으로 로깅 시스템을 활성화할 수 있다
- 클래스패스 루트 또는 `Environment` 프로퍼티 `logging.config`에 지정한 위치에 설정 파일을 제공할 수 있다 
- `org.springframework.boot.logging.LoggingSystem` 사용하면 로깅 시스템을 강제할 수 있다
  - `LoggingSystem` 구현체의 풀 네임을 사용한다
  - `none`은 스프링 부트의 로깅 설정을 완전히 비활성화한다 
- 로깅 시스템은 `ApplicationContext`를 생성하기 전에 초기화하기 때문에 스프링 `@Configuration` 파일의 `@PropertiySource` 로는 제어할 수 없다

#### 로깅 시스템 파일 로드
- `Logback`: `logback-spring.xml`, `logback.spring.groovy`, `logback.xml`, `logback.groovy`
- `Log4j2`: `log4j2-spring.xml`, `log4j2.xml`
- `java.util.logging`: `logging.properties`
  - `runnable jar` 에서 사용 시 클래스 로딩 이슈가 발생하므로 사용하지 말 것 
 


### Logback Configuration
- base.xml
  ```xml
  <included>
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <property name="LOG_FILE" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/spring.log}"/>
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
    <include resource="org/springframework/boot/logging/logback/file-appender.xml" />
    <root level="INFO">
      <appender-ref ref="CONSOLE" />
      <appender-ref ref="FILE" />
    </root>
  </included>
  ```
  - `org/springframework/boot/logging/logback/base.xml`
  - `file-appender.xml`, `console-appender.xml` 제공 
  - root log level: INFO
