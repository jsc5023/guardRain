# Auth Service

GuardRain 프로젝트의 인증/인가를 담당하는 마이크로서비스입니다.

## 프로젝트 개요
GuardRain의 사용자 인증 및 권한 관리를 처리하는 서비스로, JWT 기반의 안전한 인증 시스템을 제공합니다.

## 주요 기능
- 사용자 인증 (회원가입/로그인/로그아웃)
- JWT 기반 토큰 관리
- 사용자 권한 관리
- 보안 정책 적용

## 기술 스택
- Java 17
- Spring Boot 3.2.1
- Spring Security
- JWT
- MySQL 8.0
- Gradle

## 프로젝트 구조
```
auth-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── guardrain/
│   │   │           └── auth/
│   │   └── resources/
│   └── test/
├── docs/
│   ├── database-schema.md
│   ├── security-config.md
│   └── api-spec.md
└── README.md
```

## 시작하기

### 요구사항
- JDK 17 이상
- MySQL 8.0 이상
- Gradle 7.x 이상

### 빌드 및 실행
```bash
# 프로젝트 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun
```

## API 문서
- 개발 환경: http://localhost:8080/swagger-ui.html
- 상세 API 명세는 docs/api-spec.md 참고

## 문의
프로젝트에 대한 문의사항은 이슈 트래커를 통해 등록해주세요.