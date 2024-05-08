package com.sda.medicalspring.config;

import com.sda.medicalspring.entities.Role;
import com.sda.medicalspring.repositories.RoleRepository;
import com.sda.medicalspring.security.UserDetailServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Value(value = "${frontend.url}")
    private String frontendUrl;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        // Spring boot 2.x
//        httpSecurity.authorizeRequests().requestMatchers("/user/register").permitAll()
//                        .anyRequest().authenticated();
        httpSecurity.authorizeHttpRequests(request -> {
           request.requestMatchers("/user/register", "/user/login").permitAll()
                   .anyRequest().authenticated();
        })
                .authenticationManager(authenticationManager)
                //session managment per te konfiguruar jetegjatesine e loginit
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(frontendUrl);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @PostConstruct
    public void insertRoles(){
        if (!roleRepository.existsById("ROLE_ADMIN")){
            Role role = new Role();
            role.setRole("ROLE_ADMIN");
            roleRepository.save(role);
        }
        if (!roleRepository.existsById("ROLE_EMPLOYEE")){
            Role role = new Role();
            role.setRole("ROLE_EMPLOYEE");
            roleRepository.save(role);
        }
    }
}
