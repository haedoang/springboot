
## Spring Security Oauth2


### Spring Security initialization
```text
    SpringWebMvcImportSelector
                |
  SecurityFilterAutoConfiguration   //DelegatingFilterProxyRegistrationBean
                |
    WebMvcSecurityConfiguration
                |
    HttpSecurityConfiguration       //HttpSecurity(protoType)
                |
SpringBootWebSecurityConfiguration  //defaultSecurityFilterChain -> Custom 생성 시 제외 
                |
     WebSecurityConfiguration       //WebSecurity(SecurityFilterChains)
```


### AuthenticationEntryPoint
- ExceptionHandlingConfigurer
  - FormLoginConfigurer(LoginUrlAuthenticationEntryPoint) -- default
  - HttpBasicConfigurer(BasicAuthenticationEntryPoint) -- default
  - CustomAuthenticationEntryPoint => High Priority
- ExceptionTranslationFilter: 최종 Entrypoint 전달
  - DefaultEntryPointMappings 개수가 여러 개인  경우 Security가 요청을 구분하여 알아서 전달한다

