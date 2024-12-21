# GuardRain

실시간 재해 보험 처리 마이크로서비스 시스템

## 프로젝트 개요

GuardRain은 실시간 기상 데이터를 활용하여 자연재해 보험금 청구를 자동화하는 마이크로서비스 기반 시스템입니다. 기상청 API를 통해 실시간으로 재해 정보를 수집하고, 행정구역별 피해 현황을 분석하여 보험금을 신속하게 산정합니다.

## 시스템 구성

### 마이크로서비스 구성

1. **재해 모니터링 서비스 (disaster-monitoring-service)**
   - 기상청 API 연동
   - 실시간 재해 정보 수집
   - 데이터베이스: MySQL
   - 주요 기능:
     - 기상 데이터 실시간 모니터링
     - 재해 데이터 정규화 및 저장
     - 재해 이벤트 발생 시 알림 생성

2. **지역 분석 서비스 (area-analysis-service)**
   - 행정구역 기준 피해 현황 분석
   - 지역 매핑 서비스
   - 데이터베이스: MySQL
   - 주요 기능:
     - 행정구역별 피해 데이터 집계
     - 지역 코드 매핑
     - 피해 통계 분석

3. **보험금 계산 서비스 (claim-calculation-service)**
   - 보험금 계산 및 처리
   - REST API 제공
   - 주요 기능:
     - 보험금 산정 로직 처리
     - 청구 이력 관리
     - 보험금 지급 요청 생성

### 인프라 구성

- **API 게이트웨이**
  - Spring Cloud Gateway 사용
  - 라우팅 및 부하 분산
  - 인증/인가 처리

- **서비스 디스커버리**
  - Netflix Eureka 사용
  - 서비스 등록 및 발견
  - 상태 확인

## 기술 스택

### 백엔드
- Java 17
- Spring Boot 3.x
- Spring Cloud
- Spring Data JPA
- MySQL

### 데브옵스
- Docker
- AWS EC2
- GitHub Actions (CI/CD)

## 시스템 요구사항

- Java 17 이상
- Docker Engine
- MySQL 8.0 이상

## API 문서

각 서비스의 API 문서는 Swagger UI를 통해 제공될 예정입니다:

- 재해 모니터링 서비스: (준비 중)
- 지역 분석 서비스: (준비 중)
- 보험금 계산 서비스: (준비 중)

## 모니터링 및 로깅

- Actuator 엔드포인트를 통한 서비스 상태 모니터링
- ELK 스택을 통한 중앙 집중식 로깅
- Prometheus + Grafana를 통한 메트릭 수집 및 시각화
