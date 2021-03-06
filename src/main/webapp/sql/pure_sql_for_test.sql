 select * from tab;
-- select * from useraccount;

 drop table comment7 purge;
 drop sequence comment_no_seq;
-- drop table useraccount purge;
-- drop table m_t_relation purge;
-- drop table node purge;
-- drop table category purge;
-- drop table node purge;
-- drop table t_n_relation purge;
-- drop table choice_list purge;
-- drop table c_c_relation purge;
-- drop table forum_board purge;
-- drop table qna_board purge;

--alter table node ADD li_attr varchar2(200) CONSTRAINT defaultType DEFAULT 'default' WITH VALUES;
-- ALTER TABLE node DROP COLUMN li_attr;

---------------수정용----------------------------------
create sequence tree_no_seq
start with 1
increment by 1
nocache;
--drop sequence tree_no_seq;
--alter table node ADD li_attr varchar2(200) ;
--alter table node MODIFY li_attr DEFAULT 'default';

----------필요 시퀀스-----------------

/* 사용자계정정보 */
CREATE TABLE useraccount (
	email VARCHAR2(320) NOT NULL, /* 이메일 */
	PASS VARCHAR2(20) NOT NULL, /* 암호 */
	payment_type VARCHAR2(20) /* 유료결제유형 */
);

COMMENT ON TABLE useraccount IS '사용자계정정보';
COMMENT ON COLUMN useraccount.email IS '이메일';
COMMENT ON COLUMN useraccount.PASS IS '암호';
COMMENT ON COLUMN useraccount.payment_type IS '유료결제유형';

CREATE UNIQUE INDEX PK_useraccount
	ON useraccount (
		email ASC
	);

ALTER TABLE useraccount
	ADD
		CONSTRAINT PK_useraccount
		PRIMARY KEY (
			email
		);

		
create table board53(
    board_num number(38) primary key
  , board_name varchar2(50) not null
  , board_pass varchar2(30) not null
  , board_subject varchar2(100) not null
  , board_content varchar2(4000) not null
  , board_re_ref number /*占쏙옙 占쌓뤄옙占싫�:占썰변占쏙옙 */
  , board_re_lev number /*占썰변占쏙옙 占쏙옙占쏙옙 占쏙옙 */
  , board_re_seq number /*占썰변占쏙옙 화占썽에 占쏙옙占싱댐옙 占쏙옙치 */
  , board_readcount number /*占쏙옙회占쏙옙*/
  , board_date date /*占쏙옙毬占승�*/
);
--
create sequence board53_num_seq
                increment by 1 start with 1 nocache;
--占쏙옙 캐占쏙옙占쏙옙 占쏙옙占쏙옙玖占� 캐占쏙옙占쏙옙 占쏙옙호占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십는댐옙.
--increment by 1 start 占실미댐옙 1占쏙옙占쏙옙 占쏙옙占쏙옙占쌔쇽옙 1占쏙옙占쏙옙占쏙옙 
                
select * from board53; 

--select * from tab;
commit;

-- 댓글 테이블
 create table comment7(
  comment_no number(20) primary key /*댓글 번호*/
  ,comment_name varchar2(100) /*댓글이름*/
  ,comment_cont varchar2(4000) not null /*댓글 내용*/
  ,comment_date date /*댓글 등록날짜*/
  ,g_no number(20) not null
  references guest10(g_no) /*guest10 테이블의 g_no 필드를 주인키로
  가리키고 있다. 외래키(foreign key) 설정 */
 );
 
 create sequence comment_no_seq
 increment by 1 start with 1 nocache;
 
 
 -- 원문 테이블
  create table guest10(
   g_no number(20) primary key
   ,g_name varchar2(50)
   ,g_title varchar2(100)
   ,g_pwd varchar2(20)
   ,g_cont varchar2(4000)
   ,g_image varchar2(4000)
   ,g_hit number(20) default 0
   ,g_date date
  );
  
  create sequence g_no_seq10
  increment by 1 start with 1 nocache;
		
/* QnA게시판 */
CREATE TABLE qna_board (
	board_Num NUMBER NOT NULL, /* 게시물번호 */
	email VARCHAR2(320), /* 이메일 */
	board_name VARCHAR2(20), /* 이름 */
	board_pass VARCHAR2(20), /* 게시물암호 */
	board_subject VARCHAR2(40), /* 제목 */
	board_content VARCHAR2(1000), /* 본문 */
	board_file VARCHAR2(100), /* 첨부파일 */
	board_readcount INTEGER, /* 조회수 */
	board_date DATE /* 게시시간 */
);

COMMENT ON TABLE qna_board IS 'QnA게시판';
COMMENT ON COLUMN qna_board.board_Num IS '게시물번호';
COMMENT ON COLUMN qna_board.email IS '이메일';
COMMENT ON COLUMN qna_board.board_name IS '이름';
COMMENT ON COLUMN qna_board.board_pass IS '게시물암호';
COMMENT ON COLUMN qna_board.board_subject IS '제목';
COMMENT ON COLUMN qna_board.board_content IS '본문';
COMMENT ON COLUMN qna_board.board_file IS '첨부파일';
COMMENT ON COLUMN qna_board.board_readcount IS '조회수';
COMMENT ON COLUMN qna_board.board_date IS '게시시간';

CREATE UNIQUE INDEX PK_qna_board
	ON qna_board (
		board_Num ASC
	);

ALTER TABLE qna_board
	ADD
		CONSTRAINT PK_qna_board
		PRIMARY KEY (
			board_Num
		);

/* 노드 */
CREATE TABLE node (
	id VARCHAR2(2000), /* 노드번호 */
	parent VARCHAR2(2000), /* 부모 노드번호 */
	state VARCHAR(12), /* 활성 정보 */
	text VARCHAR2(2000) /* 선택지 */
);

COMMENT ON TABLE node IS '노드';
COMMENT ON COLUMN node.id IS '노드번호';
COMMENT ON COLUMN node.parent IS '부모 노드번호';
COMMENT ON COLUMN node.state IS '활성 정보';
COMMENT ON COLUMN node.text IS '선택지';

CREATE UNIQUE INDEX PK_node
	ON node (
		id ASC
	);

/* 포럼게시판 */
CREATE TABLE forum_board (
	board_Num NUMBER NOT NULL, /* 게시물번호 */
	board_name VARCHAR2(20), /* 이름 */
	board_pass VARCHAR2(20), /* 게시물암호 */
	board_subject VARCHAR2(40), /* 제목 */
	board_content VARCHAR2(1000), /* 본문 */
	board_file VARCHAR2(100), /* 첨부파일 */
	board_readcount INTEGER, /* 조회수 */
	board_date DATE, /* 게시시간 */
	tree_no INTEGER, /* 트리 번호 */
	recommend_cnt INTEGER, /* 트리 추천수 */
	email VARCHAR2(320) /* 이메일 */
);

COMMENT ON TABLE forum_board IS '포럼게시판';
COMMENT ON COLUMN forum_board.board_Num IS '게시물번호';
COMMENT ON COLUMN forum_board.board_name IS '이름';
COMMENT ON COLUMN forum_board.board_pass IS '게시물암호';
COMMENT ON COLUMN forum_board.board_subject IS '제목';
COMMENT ON COLUMN forum_board.board_content IS '본문';
COMMENT ON COLUMN forum_board.board_file IS '첨부파일';
COMMENT ON COLUMN forum_board.board_readcount IS '조회수';
COMMENT ON COLUMN forum_board.board_date IS '게시시간';
COMMENT ON COLUMN forum_board.tree_no IS '트리 번호';
COMMENT ON COLUMN forum_board.recommend_cnt IS '트리 추천수';

COMMENT ON COLUMN forum_board.email IS '이메일';

CREATE UNIQUE INDEX PK_forum_board
	ON forum_board (
		board_Num ASC
	);

ALTER TABLE forum_board
	ADD
		CONSTRAINT PK_forum_board
		PRIMARY KEY (
			board_Num
		);

/* 사용자-트리 관계 */
CREATE TABLE m_t_relation (
	tree_no INTEGER NOT NULL, /* 트리 번호 */
	recommend_cnt INTEGER NOT NULL, /* 트리 추천수 */
	category VARCHAR2(500) NOT NULL, /* 카테고리 */
	email VARCHAR2(320) NOT NULL /* 이메일 */
);

COMMENT ON TABLE m_t_relation IS '사용자-트리 관계';
COMMENT ON COLUMN m_t_relation.tree_no IS '트리 번호';
COMMENT ON COLUMN m_t_relation.recommend_cnt IS '트리 추천수';
COMMENT ON COLUMN m_t_relation.category IS '카테고리';
COMMENT ON COLUMN m_t_relation.email IS '이메일';

CREATE UNIQUE INDEX PK_m_t_relation
	ON m_t_relation (
		tree_no ASC
	);

ALTER TABLE m_t_relation
	ADD
		CONSTRAINT PK_m_t_relation
		PRIMARY KEY (
			tree_no
		);

/* 선택지 목록 */
CREATE TABLE choice_list (
	text VARCHAR2(2000) NOT NULL, /* 선택지 */
	code_piece LONG /* 코드 조각 */
);

COMMENT ON TABLE choice_list IS '선택지 목록';
COMMENT ON COLUMN choice_list.text IS '선택지';
COMMENT ON COLUMN choice_list.code_piece IS '코드 조각';

CREATE UNIQUE INDEX PK_choice_list
	ON choice_list (
		text ASC
	);

ALTER TABLE choice_list
	ADD
		CONSTRAINT PK_choice_list
		PRIMARY KEY (
			text
		);

/* 카테고리-선택지 관계 */
CREATE TABLE c_c_relation (
	category VARCHAR2(500) NOT NULL, /* 카테고리 */
	text VARCHAR2(2000), /* 선택지 */
	pre_choice VARCHAR2(2000), /* 이전 선택지 */
	choice_pick_freq INTEGER, /* 선택 횟수 */
	choice_weight NUMBER /* 선택 가중치 */
);

COMMENT ON TABLE c_c_relation IS '카테고리-선택지 관계';
COMMENT ON COLUMN c_c_relation.category IS '카테고리';
COMMENT ON COLUMN c_c_relation.text IS '선택지';
COMMENT ON COLUMN c_c_relation.pre_choice IS '이전 선택지';
COMMENT ON COLUMN c_c_relation.choice_pick_freq IS '선택 횟수';
COMMENT ON COLUMN c_c_relation.choice_weight IS '선택 가중치';

/* 트리-노드 관계 */
CREATE TABLE t_n_relation (
	tree_no INTEGER NOT NULL, /* 트리 번호 */
	id VARCHAR2(2000) /* 노드번호 */
);

COMMENT ON TABLE t_n_relation IS '트리-노드 관계';
COMMENT ON COLUMN t_n_relation.tree_no IS '트리 번호';
COMMENT ON COLUMN t_n_relation.id IS '노드번호';

/* 카테고리 */
CREATE TABLE category (
	category VARCHAR2(500) NOT NULL /* 카테고리 */
);

COMMENT ON TABLE category IS '카테고리';
COMMENT ON COLUMN category.category IS '카테고리';

CREATE UNIQUE INDEX PK_category
	ON category (
		category ASC
	);

ALTER TABLE category
	ADD
		CONSTRAINT PK_category
		PRIMARY KEY (
			category
		);

ALTER TABLE qna_board
	ADD
		CONSTRAINT FK_useraccount_TO_qna_board
		FOREIGN KEY (
			email
		)
		REFERENCES useraccount (
			email
		);

ALTER TABLE node
	ADD
		CONSTRAINT FK_choice_list_TO_node
		FOREIGN KEY (
			text
		)
		REFERENCES choice_list (
			text
		);

ALTER TABLE forum_board
	ADD
		CONSTRAINT FK_useraccount_TO_forum_board
		FOREIGN KEY (
			email
		)
		REFERENCES useraccount (
			email
		);

ALTER TABLE forum_board
	ADD
		CONSTRAINT FK_m_t_relation_TO_forum_board
		FOREIGN KEY (
			tree_no
		)
		REFERENCES m_t_relation (
			tree_no
		);

ALTER TABLE m_t_relation
	ADD
		CONSTRAINT FK_useraccount_TO_m_t_relation
		FOREIGN KEY (
			email
		)
		REFERENCES useraccount (
			email
		);

ALTER TABLE m_t_relation
	ADD
		CONSTRAINT FK_category_TO_m_t_relation
		FOREIGN KEY (
			category
		)
		REFERENCES category (
			category
		);

ALTER TABLE c_c_relation
	ADD
		CONSTRAINT FK_choice_list_TO_c_c_relation
		FOREIGN KEY (
			text
		)
		REFERENCES choice_list (
			text
		);

ALTER TABLE c_c_relation
	ADD
		CONSTRAINT FK_category_TO_c_c_relation
		FOREIGN KEY (
			category
		)
		REFERENCES category (
			category
		);

ALTER TABLE t_n_relation
	ADD
		CONSTRAINT FK_m_t_relationTOt_n_relation
		FOREIGN KEY (
			tree_no
		)
		REFERENCES m_t_relation (
			tree_no
		);

ALTER TABLE t_n_relation
	ADD
		CONSTRAINT FK_node_TO_t_n_relation
		FOREIGN KEY (
			id
		)
		REFERENCES node (
			id
		);
----------------------pre data setting----------------

---------------------- editing after data setting----------------------
ALTER TABLE node MODIFY (state  DEFAULT 'undetermined');
alter table m_t_relation ADD dataFileName varchar2(3000);
		
insert into category values('ImageNet Object Detection from Video Challenge');
insert into category values('Dog Breed Identification');
insert into category values('Plant Seedlings Classification');
insert into category values('Digit Recognizer');
insert into category values('Titanic: Machine Learning from Disaster');
insert into category values('ImageNet Object Localization Challenge');
insert into category values('ImageNet Object Detection Challenge');
insert into category values('Nomad2018 Predicting Transparent Conductors');
insert into category values('Santa Gift Matching Challenge');
insert into category values('TensorFlow Speech Recognition Challenge');
insert into category values('Recruit Restaurant Visitor Forecasting');
--insert into category(category) values("IEEE's Signal Processing Society - Camera Model Identification");--너무 길다. 500byte로 했는데도.
insert into category values('Corporación Favorita Grocery Sales Forecasting');
insert into category values('Toxic Comment Classification Challenge');
insert into category values('Statoil/C-CORE Iceberg Classifier Challenge');
insert into category values('Mercari Price Suggestion Challenge');
insert into category values('Zillow Prize: Zillow’s Home Value Prediction (Zestimate)');
insert into category values('House Prices: Advanced Regression Techniques');

		
insert into useraccount values('admin@breadcrumbs.com', '12345678', 'payed');		


insert into choice_list(text, code_piece) values('New node', '#type this nodes code');

alter session set ddl_lock_timeout = 600;
ALTER TABLE node DROP PRIMARY KEY;
ALTER TABLE node ADD CONSTRAINT node_uk_id UNIQUE (id);




