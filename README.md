# 소현수 callbus 백엔드 과제


##  사용법
스프링부트 실행시 자동으로 h2로 테이블 자동으로 생성되게했했습니다.

로그인은 현재 테스트 계정

`{
   "accountId":"tester@gmail.com",
   "password":"1234"
}`
`{
   "accountId":"so5663@naver.com",
   "password":"1234"
}`

둘중 하나 선택하시면 됩니다.

로그인 성공시 토큰값으로 인증하시면 됩니다.

요청 헤더에 key `Authentication`, value는 `Bearer {로그인시 반환되는 토큰값}` 설정하시면 됩니다.

글작성시 또는 수정시 -> Request Body `{ "content" : "글작성 내용" }`

- 공개용 API
  * 로그인: POST /api/users/login
  * 게시판 조회: GET /api/board
  
- 인증 사용자용 API
  * 내 정보 조회: GET /api/users/me
  * 게시판 작성: POST /api/board/write
  * 게시판 수정: PATCH /api/board/{board_id}/update
  * 게시판 삭제: DELETE /api/board/{board_id}/delete
  * 게시판 좋아요: POST /api/board/{board_id}/like
  * 게시판 좋아요 기록확인: GET /api/board/{board_id}/hisotry

## 개발 환경 
toll IntelliJ IDEA

db h2

Java 8 

Spring Boot 2.7.8

maven

## 구현 방식
JdbcTemplate으로 db 데이터를 가지고오는 방법을 채택했습니다.

org.apache.commons.lang3.builder.ToStringBuilder 사용하여 json형태의 문자열로 객체 정보 반환에 사용했습니다.

spring-security 기반으로 JWT 인증을 구현 했습니다.

## 기타 사항
문제 중 'HTTP Header 중 Authentication 의 Value Prefix 에 따라 사용해 구분 할 수 있어야 합니다' 이 부분을 구현 하지 못했습니다.
Authentication으로 구분할 방법을 생각하지 못했습니다.

