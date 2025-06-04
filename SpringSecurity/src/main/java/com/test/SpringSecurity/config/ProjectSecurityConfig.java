package com.test.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF for simplicity (enable it in production with proper configuration)
//                .csrf(csrf -> csrf.disable())
//                // Authorization configuration
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/user/register").permitAll()
//                        .requestMatchers("/user/welcome").authenticated()
//                )
//
////                // Login configuration
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .defaultSuccessUrl("/dashboard", true)
////                        .permitAll()
////                ); // Enable basic authentication
//
//        return http.build();
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   http.authorizeHttpRequests((auth)->auth
           .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/user/register").permitAll()
           .requestMatchers(HttpMethod.GET, "/user/welcome").hasAuthority("admin")

   ).csrf().disable()
           .httpBasic();
   return http.build();
}


//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
//        // Create users with encoded passwords
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("12345"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user123"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//}

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for secure password encoding
        return new BCryptPasswordEncoder();
    }
}


