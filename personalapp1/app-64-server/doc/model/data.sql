-- diary 테이블 예제 데이터


-- 회원  dia_member 
insert into dia_member(name, email, pw, tel) values('박승현', 'psh@test.com', sha1('1111'), '01033334444');
insert into dia_member(name, email, pw, tel) values('우세림', 'wsr@test.com', sha1('1111'), '01033354444');
insert into dia_member(name, email, pw, tel) values('권태환', 'kth@test.com', sha1('1111'), '01033364444');
insert into dia_member(name, email, pw, tel) values('정희은', 'jhe@test.com', sha1('1111'), '01033384444');

-- 공지사항 dia_notice
insert into dia_notice(title, content) values('환영합니다.', '가입해주셔서 감사합니다 :)');

-- 콘텐트  dia_content
insert into dia_content(mno, title, content, secret) values(1, '니뽕내뽕', '짬뽕이랑 피자랑 먹었다 배부르다', 0);
insert into dia_content(mno, title, content, secret) values(2, '휴일 다음 날은', '너무 피곤하다,, 집에 가고 싶어!', 1);
insert into dia_content(mno, title, content, secret) values(3, '이번 주 금요일', '이번 주 금요일에 놀 사람?', 0);

-- 일기 dia_diary
insert into dia_diary(cno, weather) values(1, '후덥지근');
insert into dia_diary(cno, weather) values(2, '숨막히는!');

-- 자유게시판 dia_free
insert into dia_free(cno) values(3);

-- 댓글  dia_comment
insert into dia_comment(cno, mno, content) values(1, 3, '헉 부럽다,,');

-- 대표사진 dia_pic 







