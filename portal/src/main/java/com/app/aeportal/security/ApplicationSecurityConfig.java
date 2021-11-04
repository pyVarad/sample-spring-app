package com.app.aeportal.security;


import com.app.aeportal.security.filter.CustomAuthenticationFilter;
import com.app.aeportal.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .antMatchers(
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**"
                )
                .permitAll();
        http
                .authorizeRequests()
                .antMatchers("/api/v1/login/**", "/api/v1/tokenRefresh/**")
                .permitAll();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**")
                .hasAnyAuthority("admin", "superadmin", "user");
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/**")
                .hasAnyAuthority("admin", "superadmin");
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/**")
                .hasAnyAuthority("superadmin");
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/v1/**")
                .hasAnyAuthority("admin", "superadmin");
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
