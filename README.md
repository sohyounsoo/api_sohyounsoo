


헤더에 key Authentication 설정 후 value Bearer {로그인시 반환되는 토큰값} 설정하시면 됩니다.

로그인은 현재 테스트 계정
{
   "accountId":"tester@gmail.com",
   "password":"1234"
}

글작성시, 수정시
{
   "content":"test"
}

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
  
