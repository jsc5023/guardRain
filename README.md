# guardRain
실시간 재해 보험 처리 시스템 입니다.

disaster-monitoring-service
기상청 API 연동
단순화된 재해 정보 수집
MySQL DB 사용

area-analysis-service
기본적인 지역 매핑
행정구역 기준 피해 현황
MySQL DB 사용

claim-calculation-service
간단한 보험금 계산 로직
REST API 제공

공통 구성:
Spring Cloud Gateway
Eureka Service Discovery
AWS EC2에 배포
Docker 컨테이너화
를 목표로 합니다.
