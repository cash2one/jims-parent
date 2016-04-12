create table DEPT_DICT
(
   ID                   VARCHAR2(64)         not null,
   PAREN_IDT            VARCHAR2(64),
   DEPT_NAME            VARCHAR2(100),
   DEPT_CODE            VARCHAR(20),
   DEPT_PROPERTITY      VARCHAR(100),
   CAREATE_BY           VARCHAR2(64),
   REMARK               VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR(2),
   CREATE_TIME          DATE,
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
