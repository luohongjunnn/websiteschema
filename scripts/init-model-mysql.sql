/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011-9-13 10:01:59                           */
/*==============================================================*/


drop table if exists Site;

drop table if exists User;

/*==============================================================*/
/* Table: Site                                                  */
/*==============================================================*/
create table Site
(
   id                   bigint not null auto_increment,
   siteId               varchar(100),
   siteDomain           varchar(100),
   siteName             varchar(100),
   siteType             varchar(30),
   parentId             bigint,
   url                  varchar(120),
   createTime           datetime,
   createUser           varchar(30),
   updateTime           datetime,
   lastUpdateUser       varchar(30),
   primary key (id)
);

insert into Site(siteId,siteDomain,siteName,siteType,parentId,url,createTime,createUser,updateTime,lastUpdateUser) 
values ('www_163_com_1','www.163.com','netease','general','0','http://www.163.com/',now(),'admin',now(),'admin');

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   USER_ID              bigint not null auto_increment,
   ID                   varchar(30),
   NAME                 varchar(30),
   PASSWD               char(32),
   EMAIL                varchar(30),
   ROLE                 varchar(100),
   primary key (USER_ID)
);

insert into User(id,name,passwd,email,role) values ('rod','rod','a564de63c2d0da68cf47586ee05984d7','rod@test.com','ROLE_USER, ROLE_CRAWLER');
insert into User(id,name,passwd,email,role) values ('admin','yingrui','21232f297a57a5a743894a0e4a801fc3','yingrui.f@gmail.com','ROLE_ADMIN, ROLE_USER, ROLE_CRAWLER');

