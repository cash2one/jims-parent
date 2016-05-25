   create table DEPT_DICT
(
   ID                   VARCHAR2(64)         not null,
   PARENT_ID            VARCHAR2(64),
   DEPT_NAME            VARCHAR2(100),
   DEPT_CODE            VARCHAR(20),
   DEPT_PROPERTITY      VARCHAR(100),
   ORG_ID               VARCHAR(64),
   INPUT_CODE           VARCHAR(64),
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

comment on column DEPT_DICT.PARENT_ID is
'父部门';

comment on column DEPT_DICT.DEPT_NAME is
'部门名称';

comment on column DEPT_DICT.DEPT_CODE is
'部门代码';

comment on column DEPT_DICT.DEPT_PROPERTITY is
'科室属性';
comment on column DEPT_DICT.ORG_ID is
'组织机构ID';
comment on column DEPT_DICT.INPUT_CODE is
'拼音码';



