package com.springboot.myapp.config;

import com.springboot.myapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

//    This is Phase I spring security using inMemory Management roles
//    @Bean
//    public UserDetailsService users() {
//        UserDetails customer = User.builder()
//                .username("harry")
//                .password("{noop}potter")
//                .authorities("CUSTOMER")
//                .build();
//        UserDetails executive = User.builder()
//                .username("ronald")
//                .password("{noop}weasly")
//                .authorities("EXECUTIVE")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin")
//                .authorities("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(customer,executive, admin);
//    }

    @Bean
    public SecurityFilterChain bankingSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.OPTIONS,"/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/sign-up")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/get-all")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/get/{id}")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/ticket/customer/{customerId}/v1")
                        .hasAnyRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/ticket/add/{customerId}")
                        .hasAnyAuthority("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/ticket/assign-executive/{ticketId}/{executiveId}")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/customer/add")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/customer/plan/add/{customerId}/{planId}")
                        .hasAnyAuthority("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/customer/plan/get-all")
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/executive/add")
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/executive/get-all")
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/executive/get/")
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/executive/get/{id}")
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/ticket/*")
                        .hasAnyAuthority("ADMIN","CUSTOMER")

                        .anyRequest().authenticated()

                );
        http.httpBasic(Customizer.withDefaults());  //Spring understand that i am using this technique
        return http.build();
    }

    @Bean
    public PasswordEncoder getPassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
}
