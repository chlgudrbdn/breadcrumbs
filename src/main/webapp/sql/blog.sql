select * from tab;
select * from blogmember;

-- 회원 테이블
create table blogmember(
    email varchar2(50) primary key,
    pw varchar2(50),
    name varchar2(50),
    image varchar2(200),
    mobile varchar2(11),
    address1 varchar2(200),
    address2 varchar2(200),
    joinDate date,
    loginDate date,
    gubun number
);

-- gubun은 관리자와 일반 사용자 구분을 위한 컬럼으로 일반 사용자는 0 관리자는 1
insert into blogmember values('test@naver.com', '1234', '홍길동',null, '01011112222', '서울시 강남구','111',
to_date('20160101','yyyymmdd'), sysdate,0 );

insert into blogmember values('giduck23@naver.com', '1234', '안화수',null, '01011112222', '서울시 강남구','111',
to_date('20160101','yyyymmdd'), sysdate,0 );

insert into blogmember values('guardian23@nate.com', '1234', '안화수',null, '01011112222', '서울시 강남구','111',
to_date('20160101','yyyymmdd'), sysdate,0 );

insert into blogmember values('sinbumu@naver.com', '1234', '안화수',null, '01011112222', '서울시 강남구','111',
to_date('20160101','yyyymmdd'), sysdate,0 );

insert into blogmember values('admin@naver.com', '1234', '관리자',null, '01033337777', '서울시 강남구','111',
to_date('20160101','yyyymmdd'), sysdate,1 );




--게시판에 사용될 3개의 테이블 작성
-- 1.공지사항
create table blognotice(
	notice_num	number(10) primary key,
	notice_subject	varchar2(50),
	notice_content	varchar2(2000),
	notice_readcount  number,
	notice_date	date
);
  
insert into blognotice values(1, '공지사항 테스트', '테스트입니다', 0, sysdate); 


-- 2.게시판
create table blogboard(
	board_num	number(10) primary key,
	board_id	varchar2(20),
	board_subject	varchar2(50),
	board_content	varchar2(2000),
	board_file	varchar2(50),
	board_readcount	number,
	board_date	date,
  	board_IP varchar2(100),
  CONSTRAINT fk_board_email FOREIGN KEY(board_id) REFERENCES blogmember(email) on delete set null
);

insert into blogboard values(1, 'test@naver.com', '게시판 테스트','테스트입니다.' ,null, 0, sysdate, '127.0.0.1'); 


-- 3.댓글
create table blogreply(
  reply_num	number(10) primary key,
  board_num number(10),
  reply_content varchar2(2000),
  board_id	varchar2(20),
  CONSTRAINT pk_board_num FOREIGN KEY(board_num) REFERENCES blogboard(board_num) on delete cascade,
  CONSTRAINT fk_board_id FOREIGN KEY(board_id) REFERENCES blogmember(email) on delete set null
);

insert into blogreply values(1,1,'리플 테스트입니다.', 'test@naver.com');
-- on delete cascade   
-- (부모 레코드 삭제시, 자식 레코드도 삭제)

-- on delete set null
-- (부모 레코드 삭제시, 자식 레코드는 NULL값으로 채움)


