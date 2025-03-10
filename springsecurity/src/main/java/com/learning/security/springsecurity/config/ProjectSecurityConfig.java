package com.learning.security.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {


    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/myCards","/myAccount","/myLoans","/myBalance").authenticated()
                .requestMatchers("/myContact","/myNotice","/error","/registerUser").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    // inmemory user
   /* @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user= User.withUsername("user").password("{noop}BV@7693").roles("USER").build();
        UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$hX8V4j3Gt/iffYR6Rv7BBufP2CjKz5RJhDx3xU73TKe6d2nJcd/Cq").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }*/

    //database
   /* @Bean
    public UserDetailsService userDetailsService(DataSource datasource) {
               return new JdbcUserDetailsManager(datasource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
