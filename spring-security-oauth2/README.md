
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
