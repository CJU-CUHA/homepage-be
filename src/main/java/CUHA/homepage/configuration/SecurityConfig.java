package CUHA.homepage.configuration;

import CUHA.homepage.security.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    // AuthenticationManager를 빈으로 등록하는 메서드
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();  // Spring Security에서 인증 매니저를 가져옵니다.
    }
    // BCryptPasswordEncoder를 빈으로 등록하는 메서드 (비밀번호 암호화용)
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호를 암호화하는 데 사용되는 빈을 생성합니다.
    }

    // HTTP 보안 설정을 담당하는 필터 체인을 설정합니다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 보호 비활성화 (API에서 사용하는 경우에 보안상 CSRF를 비활성화하는 경우가 많습니다)
        http.csrf((auth) -> auth.disable());
        // 기본 로그인 방식 비활성화 (form-login 사용을 비활성화)
        http.formLogin((auth) -> auth.disable());
        http.cors(auth -> auth.disable());
        // 기본 HTTP Basic 인증 방식을 비활성화
        http.logout((auth) -> auth.disable());

        // 커스텀 로그인 필터를 UsernamePasswordAuthenticationFilter 앞에 추가합니다.
        // LoginFilter는 인증 처리를 위한 커스텀 필터로, AuthenticationManager를 인자로 받습니다.
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);

        // 경로별 접근 권한 설정
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login", "/", "/join",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()  // /login, /, /join 경로는 모든 사용자에게 허용
                .anyRequest().authenticated());  // 나머지 모든 요청은 인증된 사용자만 접근 가능

        // 세션 관리 설정: Stateless로 설정하여 세션을 사용하지 않도록 합니다.
        // JWT 인증 방식에서 세션을 사용하지 않도록 하기 위해 STATELESS로 설정합니다.
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 설정한 보안 체인을 반환하여 적용합니다.
        return http.build();
    }
    // 🔹 CORS 설정을 위한 Bean 등록
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // 또는 특정 origin 설정 ex) List.of("http://localhost:3000")
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false); // true로 설정하면 AllowedOrigins에 * 못 씀

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
