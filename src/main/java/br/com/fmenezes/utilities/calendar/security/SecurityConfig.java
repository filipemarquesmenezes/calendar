package br.com.fmenezes.utilities.calendar.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("admin")
//                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
