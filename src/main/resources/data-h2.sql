-- User 데이터 생성
INSERT INTO TB_USERS(id, nickname, account_id, passwd, account_type)
VALUES (null,'tester','tester@gmail.com','$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.', 'Realtor');

-- User 데이터 생성
INSERT INTO TB_USERS(id, nickname, account_id, passwd, account_type)
VALUES (null,'tester2','so5663@naver.com','$2a$10$mzF7/rMylsnxxwNcTsJTEOFhh1iaHv3xVox.vpf6JQybEhE4jDZI.', 'Realtor');

INSERT INTO TB_BOARD ("USERS_ID", "CONTENT", "CREATE_AT") VALUES (1, '테스트 내용', now());
INSERT INTO TB_BOARD ("USERS_ID", "CONTENT", "CREATE_AT") VALUES (1, '테스트 내용2', now());
INSERT INTO TB_BOARD ("USERS_ID", "CONTENT", "CREATE_AT") VALUES (2, '테스트 내용3', now());

INSERT INTO TB_LIKE("BOARD_ID", "USERS_ID", "CREATE_AT") VALUES (1, 1, now());
INSERT INTO TB_LIKE("BOARD_ID", "USERS_ID", "CREATE_AT") VALUES (1, 2, now());