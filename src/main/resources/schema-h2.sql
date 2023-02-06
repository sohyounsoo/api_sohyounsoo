DROP TABLE IF EXISTS tb_like CASCADE;
DROP TABLE IF EXISTS tb_board CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE TB_USERS (
  id            bigint NOT NULL AUTO_INCREMENT, --사용자 PK
  account_id    varchar(30) NOT NULL,           --로그인 ID
  passwd        varchar(100) NOT NULL,           --로그인 비밀번호
  nickname      varchar(20) NOT NULL,           --사용자명
  login_count   int NOT NULL DEFAULT 0,         --로그인 횟수. 로그인시 마다 1 증가
  last_login_at datetime DEFAULT NULL,          --최종 로그인 일자
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  quit varchar(1) not null default 'N',             --탈퇴 여부
  account_type enum('Realtor', 'Lessor', 'Lessee') NOT NULL, --계정 타입
  PRIMARY KEY (id),
  CONSTRAINT unq_account_id UNIQUE (account_id)
);

CREATE TABLE tb_board (
  id          bigint NOT NULL AUTO_INCREMENT, -- PK
  users_id     bigint  NOT NULL, -- 글쓴이 user
  content      varchar(1000) NOT NULL, -- 내용
  create_at    datetime NOT NULL,
  update_at    datetime,
  delete_at    datetime,
  delete_yn   varchar(1) not null default 'N', -- 삭제여부
  PRIMARY KEY (id),
  foreign key (users_id) references tb_users(id)
);

CREATE TABLE tb_like(
  id          bigint NOT NULL AUTO_INCREMENT, -- PK
  board_id    bigint NOT NULL, -- 좋아요 누른 board_id
  users_id    bigint NOT NULL, -- 좋아요 누른 user_id
  create_at   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 좋아요 시간
  PRIMARY KEY (id),
  foreign key (board_id) references tb_board(id),
  foreign key (users_id) references tb_users(id)
);