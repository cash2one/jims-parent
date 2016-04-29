   create table DEPT_DICT
(
   ID                   VARCHAR2(64)         not null,
   PARENT_ID            VARCHAR2(64),
   DEPT_NAME            VARCHAR2(100),
   DEPT_CODE            VARCHAR(20),
   DEPT_PROPERTITY      VARCHAR(100),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_DEPT_DICT primary key (ID)
);

comment on table DEPT_DICT is
'部门字典表';

comment on column DEPT_DICT.ID is
'主键';

comment on column DEPT_DICT.PAREN_IDT is
'父部门';

comment on column DEPT_DICT.DEPT_NAME is
'部门名称';

comment on column DEPT_DICT.DEPT_CODE is
'部门代码';

comment on column DEPT_DICT.DEPT_PROPERTITY is
'科室属性';



insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('5a23e97261fd4d41b25eb90f49bbb7ed', null, '门诊', '0101011', '2;0;', null, null, null, to_date('29-04-2016 11:18:21', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:18:21', 'dd-mm-yyyy hh24:mi:ss'));

insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('70c139ee2a2c42ae9ad6e8aa1937f3bf', null, '住院部', '02020220', '8;1;', null, null, null, to_date('29-04-2016 11:49:39', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:19:26', 'dd-mm-yyyy hh24:mi:ss'));

insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('a5c16628451342f180c5baa9dfb7c6cd', '5a23e97261fd4d41b25eb90f49bbb7ed', '住院', '030303', ';;', null, null, null, to_date('29-04-2016 11:51:51', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:51:42', 'dd-mm-yyyy hh24:mi:ss'));

insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('fb244be624a44528bf124b4f1016d184', '70c139ee2a2c42ae9ad6e8aa1937f3bf', '1111', '1111', '2;;', null, null, null, to_date('29-04-2016 12:04:09', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:52:56', 'dd-mm-yyyy hh24:mi:ss'));

insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('131f053495e840c693383b64550333d2', 'fb244be624a44528bf124b4f1016d184', '临床', '010101', '8;;', null, null, null, to_date('29-04-2016 12:00:28', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 12:00:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into DEPT_DICT (ID, PARENT_ID, DEPT_NAME, DEPT_CODE, DEPT_PROPERTITY, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('fe4813e675cb47539e9f9e99e491142e', '5a23e97261fd4d41b25eb90f49bbb7ed', '门诊', '535', '3;0;', null, null, null, to_date('29-04-2016 12:05:19', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 12:05:19', 'dd-mm-yyyy hh24:mi:ss'));
