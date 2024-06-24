create table cf_member(
memId  varchar2(20) not null,
memPw varchar2(20),
memNickname varchar2(20),
memName varchar2(50),
memPhone varchar2(20),
memMail varchar2(40),
memPost varchar2(10),
memAddress varchar2(200),
memSaddress varchar2(200),
constraint cf_member_pk primary key(memId)
);

create table cf_project(
prCode NUMBER(10) not null,
prName varchar2(50),
prDescription  varchar2(2000),
prTeam  varchar2(500),
prId  varchar2(20),
prCategory  varchar2(20),
prGoal  number(12),
prCurrent  number(12) default 0,
prLikecount  number(8) default 0,
prStartdate  TIMESTAMP(6),
prEnddate  TIMESTAMP(6),
constraint cf_project_pk primary key(prCode),
foreign key(prId) references cf_member(memId)
);


select * from cf_project;
create table cf_category(
    seeAll number(1) not null,
    prCategoryName varchar(30) not null,
    prCategoryCode varchar(30) not null,
    prCategoryParent varchar(30),
   primary key(prCategoryCode),
    foreign key(prCategoryParent) references cf_category(prCategoryCode)
);






create sequence cf_project_seq start with 1 
increment by 1 
NOMAXVALUE 
nocache nocycle; 

SELECT cf_project_seq.CURRVAL FROM dual;
SELECT cf_project_seq.NEXTVAL FROM dual;

create table cf_likelist(
memId varchar2(20) not null,
prCode number(10) not null,
FOREIGN key(memId) references cf_member(memId),
FOREIGN key(prCode) references cf_project(prCode)
);



create table cf_announcement(
annNum varchar2(15) not null,
annTitle varchar2(15),
annDate date default sysdate,
annAnnounce varchar2(15),
prCode number(10) not null,
constraint cf_announcement_pk primary key(annNum),
FOREIGN key(prCode) references cf_project(prCode)
);

create table cf_comment(
comNumber varchar2(15) not null,
annNum varchar(15) not null,
memId varchar(20) not null, 
comComment varchar2(500),
comWritedate timestamp default current_timestamp,
comUpdate timestamp default current_timestamp not null
);



create table cf_category(
    
    prCategoryName varchar(30) not null,
    prCategoryCode varchar(30) not null,
    prCategoryParent varchar(30),
   primary key(prCategoryCode),
    foreign key(prCategoryParent) references cf_category(prCategoryCode)
);


insert into cf_category(prCategoryName) values('전체보기' );
insert into cf_category(prCategoryName) values('음악' );
insert into cf_category(prCategoryName) values('게임' );
insert into cf_category(prCategoryName) values('도서' );
insert into cf_category(prCategoryName) values('영화' );
insert into cf_category(prCategoryName) values('애니메이션' );

select * from cf_category;




commit;
