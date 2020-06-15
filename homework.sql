DROP SCHEMA HOMEWORK;

CREATE SCHEMA HOMEWORK DEFAULT CHARACTER SET utf8mb4;

USE HOMEWORK;

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/14 8:39:11                            */
/*==============================================================*/


drop table if exists Homework;

drop table if exists Student;

drop table if exists Submit;

drop table if exists Teacher;

/*==============================================================*/
/* Table: Homework                                              */
/*==============================================================*/
create table Homework
(
   homework_number      int not null,
   teacher_number       int not null,
   homework_title       char(50) not null,
   homework_content     char(255) not null,
   homework_state       int not null,
   create_time          date not null,
   update_time          date not null,
   end_time             date not null,
   primary key (homework_number)
);

/*==============================================================*/
/* Table: Student                                               */
/*==============================================================*/
create table Student
(
   student_number       int not null,
   student_name         char(30) not null,
   student_password     char(120) not null,
   primary key (student_number)
);

/*==============================================================*/
/* Table: Submit                                                */
/*==============================================================*/
create table Submit
(
   homework_number      int not null,
   student_number       int not null,
   submit_title         char(50) not null,
   submit_content       char(255) not null,
   grade                int,
   comment              char(120),
   create_time          date not null,
   update_time          date not null,
   primary key (homework_number, student_number)
);

/*==============================================================*/
/* Table: Teacher                                               */
/*==============================================================*/
create table Teacher
(
   teacher_number       int not null,
   teacher_name         char(30) not null,
   teacher_password     char(120) not null,
   primary key (teacher_number)
);

alter table Homework add constraint FK_publish foreign key (teacher_number)
      references Teacher (teacher_number) on delete restrict on update restrict;

alter table Submit add constraint FK_Submit foreign key (homework_number)
      references Homework (homework_number) on delete restrict on update restrict;

alter table Submit add constraint FK_Submit2 foreign key (student_number)
      references Student (student_number) on delete restrict on update restrict;

insert into Student
values (17301091, '崔超群', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Student
values (17301092, '符永乐', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Student
values (17301093, '郭佳华', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Teacher
values (10000001, '王凯民', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Teacher
values (10000002, '李宇', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Teacher
values (10000003, '袁岗', 'E10ADC3949BA59ABBE56E057F20F883E');

insert into Homework
values (1, 10000001, '自学spring cloud','结合课上所讲的内容自学spring cloud',
        0,DATE('2018-12-22 15:15:19'),DATE('2018-12-22 15:15:19'),DATE('2018-12-30 20:00:00'));

insert into Homework
values (2, 10000001, '复习spring mvc','复习spring mvc的基础知识',
        0,DATE('2018-11-12 14:15:29'),DATE('2018-11-12 14:15:29'),DATE('2018-11-20 18:00:00'));

insert into Homework
values (3, 10000002, '完成数据建模','完成小组项目的用例建模过程',
        0,DATE('2020-6-12 05:30:50'),DATE('2020-6-12 05:30:50'),DATE('2020-6-30 05:30:00'));

insert into Submit
values (1, 17301091, 'spring cloud自学笔记','基本完成了自学过程',90,'完成效果极佳',
        DATE('2018-12-25 15:30:00'),DATE('2018-12-18 15:30:00'));

insert into Submit
values (1, 17301092, 'spring cloud学习过程记录','通过网络基本完成了自学过程',98,'完成效果极佳，仍需改进',
        DATE('2018-12-27 10:35:10'),DATE('2018-12-27 10:35:10'));

insert into Submit
values (3, 17301093, '文档开发记录','已经完成了全部文档的编写',92,'文档完整，继续努力',
        DATE('2020-6-18 09:30:20'),DATE('2020-6-18 09:30:20'));