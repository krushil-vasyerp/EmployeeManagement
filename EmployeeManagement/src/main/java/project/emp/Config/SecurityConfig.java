package project.emp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.emp.Jwt.JwtFilter;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean // for rest api comment out second filter to enable it . second is for jpa response
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable());
//
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/salary/**").hasAnyRole("HR", "ADMIN")
//                .requestMatchers("/desk/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        );
//
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth

                .requestMatchers("/", "/login", "/logout").permitAll()
                .requestMatchers("/dashboard").permitAll()
                .requestMatchers("/departments/**").permitAll()
                .requestMatchers("/employees/**").permitAll()
                .requestMatchers("/desks/**").permitAll()
                .requestMatchers("/tasks/**").permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/salary").permitAll()
                .anyRequest().permitAll()
        );

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
