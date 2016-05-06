drop table UNIT_IN_CONTRACT cascade constraints;

/*==============================================================*/
/* Table: UNIT_IN_CONTRACT    合同单位记录                      */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table UNIT_IN_CONTRACT 
(
   ID                   VARCHAR2(64 char)    not null,
   UNIT_CODE            CHAR(11),
   UNIT_NAME            CHAR(40),
   INPUT_CODE           CHAR(8),
   ADDRESS              CHAR(40),
   UNIT_TYPE            CHAR,
   SUBORDINATE_TO       CHAR(11),
   DISTANCE_TO_HOSPITAL NUMBER(4,2),
   REGULAR_NUM          NUMBER(4,2),
   TEMP_NUM             NUMBER(4,2),
   RETIRED_NUM          NUMBER(4,2),
   INPUT_CODE_WB        NUMBER(8,2),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_UNIT_IN_CONTRACT primary key (ID)

);
comment on table UNIT_IN_CONTRACT is
'合同单位记录';

comment on column UNIT_IN_CONTRACT.ID is
'主键';

comment on column UNIT_IN_CONTRACT.UNIT_CODE is
'合同单位代码';

comment on column UNIT_IN_CONTRACT.UNIT_NAME is
'合同单位名称';

comment on column UNIT_IN_CONTRACT.INPUT_CODE is
'输入码';

comment on column UNIT_IN_CONTRACT.ADDRESS is
'单位地址';

comment on column UNIT_IN_CONTRACT.UNIT_TYPE is
'单位性质';

comment on column UNIT_IN_CONTRACT.SUBORDINATE_TO is
'隶属单位';

comment on column UNIT_IN_CONTRACT.DISTANCE_TO_HOSPITAL is
'就医距离';

comment on column UNIT_IN_CONTRACT.REGULAR_NUM is
'在编人数';

comment on column UNIT_IN_CONTRACT.TEMP_NUM is
'非编人数';

comment on column UNIT_IN_CONTRACT.RETIRED_NUM is
'离退休人数';

comment on column UNIT_IN_CONTRACT.INPUT_CODE_WB is
'五笔码';

comment on column UNIT_IN_CONTRACT.CREATE_BY is
'创建人';

comment on column UNIT_IN_CONTRACT.CREATE_DATE is
'创建时间';

comment on column UNIT_IN_CONTRACT.UPDATE_BY is
'修改人';

comment on column UNIT_IN_CONTRACT.UPDATE_DATE is
'修改时间';

comment on column UNIT_IN_CONTRACT.DEL_FLAG is
'删除标记';

comment on column UNIT_IN_CONTRACT.REMARKS is
'备注信息';
