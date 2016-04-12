create table ORG_DEPT_PROPERTY_DICT
(
   ID                   VARCHAR(64)          not null,
   PROPERTY_TYPE        VARCHAR(100),
   PROPERTY_NAME        VARCHAR(100),
   PROPERTY_VALUE       VARCHAR(100),
   ORG_ID               VARCHAR(64),
   CAREATE_BY           VARCHAR2(64),
   REMARK               VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR(2),
   CREATE_TIME          DATE,
   SORT                 NUMBER,
   constraint PK_ORG_DEPT_PROPERTY_DICT primary key (ID)
);

comment on table ORG_DEPT_PROPERTY_DICT is
'科室属性字典表';

comment on column ORG_DEPT_PROPERTY_DICT.ID is
'主键';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_TYPE is
'属性类型';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_NAME is
'属性名称';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_VALUE is
'属性值';

comment on column ORG_DEPT_PROPERTY_DICT.ORG_ID is
'所属组织';
