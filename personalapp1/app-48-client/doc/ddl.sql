create table personalapp_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table personalapp_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
create table personalapp_diary(
  diary_no int not null,
  writedate varchar(20) not null,
  title varchar(50) not null,
  weather varchar(100) not null,
  content text null,
  coffee char(1) not null
);

alter table personalapp_diary
  add constraint primary key (diary_no),
  modify column diary_no int not null auto_increment;
  
  
create table personalapp_visit(
  visit_no int not null,
  name varchar(255) not null,
  created_date datetime default now()
  );

alter table personalapp_visit
  add constraint primary key (visit_no),
  modify column visit_no int not null auto_increment;
  
 --게시판 작성자에 대해 외부키 설정
alter table personalapp_board
 add constraint personalapp_board_fk foreign key(writer) references personalapp_visit (visit_no);
 
  