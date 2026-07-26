package com.example.springsecuritydemo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) {
         security
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                 .httpBasic(Customizer.withDefaults())
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired PasswordEncoder passwordEncoder){

        UserDetails user1 = User.withUsername("user1").password(passwordEncoder.encode("user1")).roles("USER").build();
        UserDetails user2 = User.withUsername("user2").password(passwordEncoder.encode("user2")).roles("USER").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
