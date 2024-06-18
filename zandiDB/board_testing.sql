create table cf_announcement(
aNum varchar2(15) not null,
aTitle varchar2(15),
aDate date default sysdate,
aAnnounce varchar2(15),
pass varchar2(10),
readcount int
--prCode number(10) not null,
--constraint cf_announcement_pk primary key(aNum),
--FOREIGN key(prCode) references cf_project(prCode)
);

create sequence cf_announcement_seq start with 1 
increment by 1 
NOMAXVALUE 
nocache nocycle; 

select * from cf_announcement;

drop table cf_announcement;