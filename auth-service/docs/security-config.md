# 보안 설정

## JWT 설정
- Access Token 만료시간: 30분
- Refresh Token 만료시간: 7일
- Token Secret: 환경변수로 관리 (JWT_SECRET)
- Token 알고리즘: HS512

## 비밀번호 정책
- 최소 8자 이상
- 영문 대/소문자, 숫자, 특수문자 포함
- BCrypt 암호화 (strength = 10)

## API 보안 정책
```
| 엔드포인트                | 접근 권한        | Rate Limit |
|------------------------|----------------|------------|
| /api/auth/**          | 모두 허용        | 1분당 20회  |
| /api/users/me         | 인증된 사용자     | 1분당 30회  |
| /api/admin/**         | ROLE_ADMIN     | 1분당 50회  |
| /api/manager/**       | ROLE_MANAGER   | 1분당 50회  |
```

## CORS 설정
```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://guardrain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

## Spring Security Filter Chain
1. JwtAuthenticationFilter
2. ExceptionTranslationFilter
3. SessionManagementFilter

## 계정 보안
- 로그인 실패 5회시 계정 잠금 (30분)
- 비밀번호 변경 강제: 90일
- 세션 동시 접속: 최대 1개