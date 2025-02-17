# 온라인 도서 관리 시스템

## 기술 스택

프레임 워크 : Spring Boot

언어 : Java

데이터베이스 : MySQL

ORM : Spring Data JPA

캐싱 : Redis

빌드 도구 : Gradle

API 문서화 : OpenAPI

테스트 프래임워크 : JUnit, Mockito

모니터링 : Prometheus, Grafana

CI/CD : github actions, AWS Ec2, Docker

## 기능
1. 사용자
    - 회원가입
    - 사용자 조회
2. 도서
    - 도서 정보 조회
    - 도서 정보 수정
    - 도서 정보 삭제
    - 도서 정보 추가
3. 대출
    - 대출
    - 반납
    - 대출 상태 확인

## 실행 방법
```
cd docker
docker-compose up -d
```
