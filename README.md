# GuardRain

실시간 재해 보험 처리 마이크로서비스 시스템

## 프로젝트 개요

GuardRain은 실시간 기상 데이터를 활용하여 자연재해 보험금 청구를 자동화하는 마이크로서비스 기반 시스템입니다. 기상청 API를 통해 실시간으로 재해 정보를 수집하고, 행정구역별 피해 현황을 분석하여 보험금을 신속하게 산정합니다.

## 시스템 아키텍처

### 마이크로서비스 구성

1. **disaster-monitoring-service**
   - 기상청 API 연동
   - 실시간 재해 정보 수집
   - 데이터베이스: MySQL
   - 주요 기능:
     - 기상 데이터 실시간 모니터링
     - 재해 데이터 정규화 및 저장
     - 재해 이벤트 발생 시 알림 생성

2. **area-analysis-service**
   - 행정구역 기준 피해 현황 분석
   - 지역 매핑 서비스
   - 데이터베이스: MySQL
   - 주요 기능:
     - 행정구역별 피해 데이터 집계
     - 지역 코드 매핑
     - 피해 통계 분석

3. **claim-calculation-service**
   - 보험금 계산 및 처리
   - REST API 제공
   - 주요 기능:
     - 보험금 산정 로직 처리
     - 청구 이력 관리
     - 보험금 지급 요청 생성

### 인프라 구성

- **API Gateway**
  - Spring Cloud Gateway 사용
  - 라우팅 및 로드 밸런싱
  - 인증/인가 처리

- **Service Discovery**
  - Netflix Eureka 사용
  - 서비스 등록 및 발견
  - 헬스 체크

## 기술 스택

### Backend
- Java 17
- Spring Boot 3.x
- Spring Cloud
- Spring Data JPA
- MySQL

### DevOps
- Docker
- AWS EC2
- GitHub Actions (CI/CD)

## 시스템 요구사항

- Java 17 이상
- Docker Engine
- MySQL 8.0 이상

## 설치 및 실행 방법

1. 저장소 클론
```bash
git clone https://github.com/jsc5023/guardrain.git
cd guardrain
```

2. 환경 변수 설정
```bash
cp .env.example .env
# .env 파일 수정
```

3. 도커 컴포즈로 실행
```bash
docker-compose up -d
```

## API 문서

각 서비스의 API 문서는 Swagger UI를 통해 확인할 수 있습니다:

- Disaster Monitoring Service: http://localhost:8081/swagger-ui.html
- Area Analysis Service: http://localhost:8082/swagger-ui.html
- Claim Calculation Service: http://localhost:8083/swagger-ui.html

## 프로젝트 구조

```
guardrain/
├── disaster-monitoring-service/
├── area-analysis-service/
├── claim-calculation-service/
├── api-gateway/
├── eureka-server/
├── docker-compose.yml
└── README.md
```

## 모니터링 및 로깅

- Actuator 엔드포인트를 통한 서비스 상태 모니터링
- ELK 스택을 통한 중앙 집중식 로깅
- Prometheus + Grafana를 통한 메트릭 수집 및 시각화

## 기여 방법

1. 프로젝트를 Fork 합니다
2. 새로운 기능 브랜치를 생성합니다 (`git checkout -b feature/새로운기능`)
3. 변경사항을 커밋합니다 (`git commit -m '새로운 기능 추가'`)
4. 브랜치에 Push 합니다 (`git push origin feature/새로운기능`)
5. Pull Request를 생성합니다
