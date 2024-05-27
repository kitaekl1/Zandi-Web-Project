create table cf_member(
m_id  varchar2(20) not null,
m_pw varchar2(20),
m_nickname varchar2(20),
m_name varchar2(50),
m_phone varchar2(20),
m_mail varchar2(40),
m_post varchar2(10),
m_address varchar2(200),
m_saddress varchar2(200),
constraint cf_member_pk primary key(m_id)
);

create table cf_project(
pr_code varchar2(10) not null,
pr_name varchar2(50),
pr_description  varchar2(2000),
pr_team  varchar2(500),
pr_id  varchar2(20),
pr_category  varchar2(20),
pr_goal  varchar2(10),
pr_current  varchar2(20) default 0,
pr_likecount  varchar2(20) default 0,
pr_startdate  date default sysdate,
pr_enddate  date,
constraint cf_project_pk primary key(pr_code),
foreign key(pr_id) references cf_member(m_id)
);

create table cf_likelist(
m_id varchar2(20) not null,
pr_code varchar2(10) not null,
FOREIGN key(m_id) references cf_member(m_id),
FOREIGN key(pr_code) references cf_project(pr_code)
);

create table cf_patment(

);

create table cf_announcement(
a_num varchar2(15) not null,
a_title varchar2(15),
a_date date default sysdate,
a_announce varchar2(15),
pr_code varchar2(10) not null,
constraint cf_announcement_pk primary key(a_num),
FOREIGN key(pr_code) references cf_project(pr_code)
);

create table cf_comment(
c_number varchar2(15) not null,
a_num varchar(15) not null,
m_id varchar(20) not null, 
c_comment varchar2(500),
c_writedate timestamp default current_timestamp,
c_update timestamp default current_timestamp not null
);

ALTER TABLE cf_announcement
MODIFY (a_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
select pr_startdate::timestamp;
drop table cf_member;
drop table cf_project;
drop table cf_announcement;
drop table cf_likelist;
drop table cf_comment;
commit;