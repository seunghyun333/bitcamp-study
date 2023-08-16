-- 일기
DROP TABLE IF EXISTS dia_diary RESTRICT;

-- 회원
DROP TABLE IF EXISTS dia_member RESTRICT;

-- 댓글
DROP TABLE IF EXISTS dia_comment RESTRICT;

-- 대표사진
DROP TABLE IF EXISTS dia_pic RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS dia_notice RESTRICT;

-- 콘텐트
DROP TABLE IF EXISTS dia_content RESTRICT;

-- 자유게시판
DROP TABLE IF EXISTS dia_free RESTRICT;

-- 일기
CREATE TABLE dia_diary (
  cno     INTEGER      NOT NULL COMMENT '콘텐트번호', -- 콘텐트번호
  weather VARCHAR(255) NULL     COMMENT '날씨' -- 날씨
)
COMMENT '일기';

-- 일기
ALTER TABLE dia_diary
  ADD CONSTRAINT PK_dia_diary -- 일기 기본키
  PRIMARY KEY (
  cno -- 콘텐트번호
  );

-- 회원
CREATE TABLE dia_member (
  mno    INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  name   VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
  email  VARCHAR(40) NOT NULL COMMENT '이메일', -- 이메일
  pw     VARCHAR(50) NOT NULL COMMENT '암호', -- 암호
  tel    VARCHAR(30) NOT NULL COMMENT '전화번호', -- 전화번호
  w_date DATETIME    NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '회원';

-- 회원
ALTER TABLE dia_member
  ADD CONSTRAINT PK_dia_member -- 회원 기본키
  PRIMARY KEY (
  mno -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_dia_member
  ON dia_member ( -- 회원
    tel ASC -- 전화번호
  );

-- 회원 인덱스
CREATE INDEX IX_dia_member
  ON dia_member( -- 회원
    name ASC -- 이름
  );

-- 회원 인덱스2
CREATE INDEX IX_dia_member2
  ON dia_member( -- 회원
    email ASC -- 이메일
  );

ALTER TABLE dia_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 댓글
CREATE TABLE dia_comment (
  cmno    INTEGER    NOT NULL COMMENT '댓글번호', -- 댓글번호
  cno     INTEGER    NOT NULL COMMENT '콘텐트번호', -- 콘텐트번호
  mno     INTEGER    NOT NULL COMMENT '회원번호', -- 회원번호
  content MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  w_date  DATETIME   NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '댓글';

-- 댓글
ALTER TABLE dia_comment
  ADD CONSTRAINT PK_dia_comment -- 댓글 기본키
  PRIMARY KEY (
  cmno -- 댓글번호
  );

-- 댓글 인덱스
CREATE INDEX IX_dia_comment
  ON dia_comment( -- 댓글
  );

-- 댓글 인덱스2
CREATE INDEX IX_dia_comment2
  ON dia_comment( -- 댓글
  );

ALTER TABLE dia_comment
  MODIFY COLUMN cmno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 대표사진
CREATE TABLE dia_pic (
  pno      INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  cno      INTEGER      NOT NULL COMMENT '콘텐트번호', -- 콘텐트번호
  pic_file VARCHAR(255) NULL     COMMENT '사진파일이름' -- 사진파일이름
)
COMMENT '대표사진';

-- 대표사진
ALTER TABLE dia_pic
  ADD CONSTRAINT PK_dia_pic -- 대표사진 기본키
  PRIMARY KEY (
  pno -- 사진번호
  );

ALTER TABLE dia_pic
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 공지사항
CREATE TABLE dia_notice (
  nno     INTEGER      NOT NULL COMMENT '공지번호', -- 공지번호
  title   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content MEDIUMTEXT   NOT NULL COMMENT '내용' -- 내용
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE dia_notice
  ADD CONSTRAINT PK_dia_notice -- 공지사항 기본키
  PRIMARY KEY (
  nno -- 공지번호
  );

-- 공지사항 인덱스
CREATE INDEX IX_dia_notice
  ON dia_notice( -- 공지사항
    title ASC -- 제목
  );

-- 공지사항 인덱스2
CREATE INDEX IX_dia_notice2
  ON dia_notice( -- 공지사항
  );

ALTER TABLE dia_notice
  MODIFY COLUMN nno INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지번호';

-- 콘텐트
CREATE TABLE dia_content (
  cno     INTEGER      NOT NULL COMMENT '콘텐트번호', -- 콘텐트번호
  mno     INTEGER      NOT NULL COMMENT '작성자번호', -- 작성자번호
  title   VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content MEDIUMTEXT   NOT NULL COMMENT '내용(사진포함)', -- 내용(사진포함)
  w_date  DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  v_count INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  secret  BOOLEAN      NOT NULL COMMENT '비밀여부' -- 비밀여부
)
COMMENT '콘텐트';

-- 콘텐트
ALTER TABLE dia_content
  ADD CONSTRAINT PK_dia_content -- 콘텐트 기본키
  PRIMARY KEY (
  cno -- 콘텐트번호
  );

-- 콘텐트 인덱스
CREATE INDEX IX_dia_content
  ON dia_content( -- 콘텐트
    title ASC -- 제목
  );

ALTER TABLE dia_content
  MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '콘텐트번호';

-- 자유게시판
CREATE TABLE dia_free (
  cno INTEGER NOT NULL COMMENT '콘텐트번호' -- 콘텐트번호
)
COMMENT '자유게시판';

-- 자유게시판
ALTER TABLE dia_free
  ADD CONSTRAINT PK_dia_free -- 자유게시판 기본키
  PRIMARY KEY (
  cno -- 콘텐트번호
  );

-- 일기
ALTER TABLE dia_diary
  ADD CONSTRAINT FK_dia_content_TO_dia_diary -- 콘텐트 -> 일기
  FOREIGN KEY (
  cno -- 콘텐트번호
  )
  REFERENCES dia_content ( -- 콘텐트
  cno -- 콘텐트번호
  );

-- 댓글
ALTER TABLE dia_comment
  ADD CONSTRAINT FK_dia_member_TO_dia_comment -- 회원 -> 댓글
  FOREIGN KEY (
  mno -- 회원번호
  )
  REFERENCES dia_member ( -- 회원
  mno -- 회원번호
  );

-- 댓글
ALTER TABLE dia_comment
  ADD CONSTRAINT FK_dia_content_TO_dia_comment -- 콘텐트 -> 댓글
  FOREIGN KEY (
  cno -- 콘텐트번호
  )
  REFERENCES dia_content ( -- 콘텐트
  cno -- 콘텐트번호
  );

-- 대표사진
ALTER TABLE dia_pic
  ADD CONSTRAINT FK_dia_content_TO_dia_pic -- 콘텐트 -> 대표사진
  FOREIGN KEY (
  cno -- 콘텐트번호
  )
  REFERENCES dia_content ( -- 콘텐트
  cno -- 콘텐트번호
  );

-- 콘텐트
ALTER TABLE dia_content
  ADD CONSTRAINT FK_dia_member_TO_dia_content -- 회원 -> 콘텐트
  FOREIGN KEY (
  mno -- 작성자번호
  )
  REFERENCES dia_member ( -- 회원
  mno -- 회원번호
  );

-- 자유게시판
ALTER TABLE dia_free
  ADD CONSTRAINT FK_dia_content_TO_dia_free -- 콘텐트 -> 자유게시판
  FOREIGN KEY (
  cno -- 콘텐트번호
  )
  REFERENCES dia_content ( -- 콘텐트
  cno -- 콘텐트번호
  );