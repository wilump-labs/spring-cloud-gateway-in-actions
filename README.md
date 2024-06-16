# Spring Cloud Gateway in actions

## Overview
- profile: `props`
  - 기본 yml 설정을 사용하는 프로젝트
- profile: `bean`
  - yml 설정을 사용하지 않고, 빈을 사용(코드 내 포함)하여 라우트 정보를 설정하는 프로젝트
- profile: `dynamic`
  - R2DBC를 사용하여 DB에 저장된 라우트 정보를 반영하는 프로젝트
  - refresh 및 동기화 방법
    - `POST` `/routes/refresh` 요청을 보내면, DB에 저장된 라우트 정보를 갱신한다.
      - 단일 인스턴스 기준으로 갱신되기 때문에 전체 replica 에 이벤트를 전파하기 위한 추가 인프라 필요
      - 혹은 개별 인스턴스에서 주기적 polling 해서 최신 버전을 확인 및 비교하여 주기적으로 갱신하는 방법도 가능
    - 다만 gateway 특성상 라우팅을 단 번에 바꿔야 하는 경우도 있을 수 있고, 비율을 조정해야 하는 경우도 있을 수 있음
      - 이런 경우에 모니터링하는 기준에서는 버전이 확실히 다른 게 관제하는 관점에서는 편리할 수도 있을 듯

## Reference
- spring cloud gateway
  - https://spring.io/projects/spring-cloud-gateway
  - https://saramin.github.io/2022-01-20-spring-cloud-gateway-api-gateway/
  - https://anjireddy-kata.medium.com/spring-cloud-gateway-dynamic-route-configuration-and-loading-from-the-datastore-a0637e6bd9b4
- R2DBC
  - https://gompangs.tistory.com/entry/Spring-R2DBC-MySQL
  - https://hiphopddori.tistory.com/127
  - https://velog.io/@armton/ERR-r2dbc-saveAll-%EC%8B%9C-isNew-%ED%8C%90%EB%8B%A8-%EB%AA%BB%ED%95%98%EB%8A%94-%EB%AC%B8%EC%A0%9C