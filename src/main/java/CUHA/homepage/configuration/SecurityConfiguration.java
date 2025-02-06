package CUHA.homepage.configuration;

import CUHA.homepage.model.UserRole;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    //////

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api","/home","/swagger-ui.html","/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/admin").hasAnyRole("admin", "staff")
                        // 로그인된 사용자만 게시글 작성, 수정, 삭제
                        .requestMatchers(POST,"/api/board").hasAnyRole("admin", "staff", "user")
                        .requestMatchers(PUT,"/api/board").hasAnyRole("admin", "staff", "user")
                        .requestMatchers(DELETE,"/api/board").hasAnyRole("admin", "staff", "user")
                        // 로그인된 사용자만 댓글 작성, 수정, 삭제
                        .requestMatchers(POST,"/api/comment").hasAnyRole("admin", "staff", "user")
                        .requestMatchers(POST,"/api/cocomment").hasAnyRole("admin", "staff", "user")
                        .requestMatchers(PUT,"/api/comment").hasAnyRole("admin", "staff", "user")
                        .requestMatchers(DELETE,"/api/comment").hasAnyRole("admin", "staff", "user")
                        .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();
    }

    @Bean//password 인코더 단방향이라 암호화 해서 맞춰주어야 함. 복호화 불가능
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String passwordDecoder(){
        return new BCryptPasswordEncoder().encode("password");
    }

    

}
