
## Spring Security Oauth2


### Spring Security initialization
```text
    SpringWebMvcImportSelector
                |
  SecurityFilterAutoConfiguration
                |
    WebMvcSecurityConfiguration
                |
    HttpSecurityConfiguration       //HttpSecurity(protoType)
                |
SpringBootWebSecurityConfiguration  //defaultSecurityFilterChain
                |
     WebSecurityConfiguration       //WebSecurity(SecurityFilterChains)
```