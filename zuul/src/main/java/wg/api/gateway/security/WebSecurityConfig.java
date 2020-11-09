package wg.api.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Environment env;

    public WebSecurityConfig(Environment env) {
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, env.getProperty("api.registration.urlPath")).permitAll()
                .antMatchers(HttpMethod.POST, env.getProperty("api.login.urlPath")).permitAll()
                .antMatchers(env.getProperty("api.zuul.actuator.urlPath")).permitAll()
                .antMatchers(env.getProperty("api.user.actuator.urlPath")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), env));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
