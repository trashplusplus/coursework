package com.coursework.config;

import com.coursework.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/settings/admin").hasAuthority("ADMIN")
                        .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN", "COURIER", "ASSEMBLER", "OPERATOR")
                       // .requestMatchers("/passwordEdit").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/main", "/", "/top").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register", "/login").anonymous()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/catalog/**").permitAll()
                        .requestMatchers("/settings/admin/", "/settings/admin/role").hasAuthority("ADMIN")
                        .requestMatchers("/settings").hasAnyAuthority("USER", "ADMIN", "COURIER", "ASSEMBLER", "OPERATOR")
                        .anyRequest().authenticated());
        http.userDetailsService(userDetailsService);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
