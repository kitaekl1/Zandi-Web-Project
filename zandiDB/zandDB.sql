create table cf_member(
mId  varchar2(20) not null,
mPw varchar2(20),
mNickname varchar2(20),
mName varchar2(50),
mPhone varchar2(20),
mMail varchar2(40),
mPost varchar2(10),
mAddress varchar2(200),
mSaddress varchar2(200),
constraint cf_member_pk primary key(mId)
);

SELECT * FROM cf_member;

select * from table;
select * from cf_member;
select * from cf_project;
select * from cf_likelist;
drop SEQUENCE cf_Project_seq;


create table cf_project(
prCode NUMBER(10) not null,
prName varchar2(50),
prDescription  varchar2(2000),
prTeam  varchar2(500),
prId  varchar2(20),
prCategory  varchar2(20),
prGoal  varchar2(10),
prCurrent  varchar2(20) default 0,
prLikecount  number(20) default 0,
prStartdate  TIMESTAMP(6),
prEnddate  TIMESTAMP(6),
constraint cf_project_pk primary key(prCode),
foreign key(prId) references cf_member(mId)
);
select * from cf_project;

INSERT INTO cf_project (prCode, prName, prDescription, prTeam, prId, prCategory, prGoal, prCurrent, prLikecount, prStartdate, prEnddate)
VALUES (12, '프로젝트1', '프로젝트 설명', '팀1', '아이디1', '카테고리1', '목표1', '현재1', 10, TIMESTAMP '2024-05-31 00:00:00', TIMESTAMP '2024-06-30 00:00:00');


create sequence cf_project_seq start with 1 
increment by 1 
NOMAXVALUE 
nocache nocycle; 

SELECT cf_project_seq.CURRVAL FROM dual;
SELECT cf_project_seq.NEXTVAL FROM dual;

create table cf_likelist(
mId varchar2(20) not null,
prCode number(10) not null,
FOREIGN key(mId) references cf_member(mId),
FOREIGN key(prCode) references cf_project(prCode)
);

create table cf_payment(

);

create table cf_announcement(
aNum varchar2(15) not null,
aTitle varchar2(15),
aDate date default sysdate,
aAnnounce varchar2(15),
prCode number(10) not null,
constraint cf_announcement_pk primary key(aNum),
FOREIGN key(prCode) references cf_project(prCode)
);

create table cf_comment(
cNumber varchar2(15) not null,
aNum varchar(15) not null,
mId varchar(20) not null, 
cComment varchar2(500),
cWritedate timestamp default current_timestamp,
cUpdate timestamp default current_timestamp not null
);


create table cf_category(
    seeAll number(1) not null,
    prCategoryName varchar(30) not null,
    prCategoryCode varchar(30) not null,
    prCategoryParent varchar(30),
   primary key(prCategoryCode),
    foreign key(prCategoryParent) references cf_category(prCategoryCode)
);


insert into cf_category(seeAll,prCategoryName,prCategoryCode) values(1, '전체보기' , '100');
    insert into cf_category(seeAll,prCategoryName,prCategoryCode,prCategoryParent) values(2, '게임' , '101','100');
    insert into cf_category(seeAll,prCategoryName,prCategoryCode,prCategoryParent) values(2, '음악' , '102','100');
    insert into cf_category(seeAll,prCategoryName,prCategoryCode,prCategoryParent) values(2, '도서' , '103','100');
    insert into cf_category(seeAll,prCategoryName,prCategoryCode,prCategoryParent) values(2, '영화' , '104','100');
    insert into cf_category(seeAll,prCategoryName,prCategoryCode,prCategoryParent) values(2, '애니메이션' , '105','100');

select * from cf_category;

ALTER TABLE cf_announcement
MODIFY (a_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
select pr_startdate::timestamp;
drop table cf_category;
drop table cf_Project;
drop table cf_Announcement;
drop table cf_Likelist;
drop table cf_Comment;
SELECT * FROM tabs;
drop table dept01;
commit;

alter table cf_project add prCategoryCode varchar(20) ;
