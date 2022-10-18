package io.haedoang.springsecurityoauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * author : haedoang
 * date : 2022/10/18
 * description :
 */
public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

    private boolean isSecure;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
        System.out.println("CustomSecurityConfigurer init method invoked");
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
        System.out.println("CustomSecurityConfigurer configure method invoked");
        if (isSecure) {
            System.out.println("https is required");
        } else {
            System.out.println("https is optional");
        }
    }

    public CustomSecurityConfigurer setFlag(boolean isSecure) {
        this.isSecure = isSecure;
        return this;
    }
}
