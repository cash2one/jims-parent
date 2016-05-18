/*==============================================================*/
/* Table: CLINIC_INDEX              门诊号别定义       */
/* CREATE_BY :  zhangyao                                            */
/*==============================================================*/
create table CLINIC_INDEX
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   CLINIC_LABEL         VARCHAR2(100 char)   not null,
   CLINIC_DEPT          VARCHAR2(64),
   DOCTOR               VARCHAR2(64),
   DOCTOR_TITLE         VARCHAR2(64),
   CLINIC_TYPE          VARCHAR2(64),
   INPUT_CODE           VARCHAR2(100),
   CLINIC_POSITION      VARCHAR2(200 char),
   SERIAL_NO            NUMBER(4),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char                 default '0',
   constraint PK_CLINIC_INDEX primary key (ID)
);

comment on table CLINIC_INDEX is
'此表定义了医院所开设的门诊种类，一个种类的门诊需要设立一种号别;门诊号名称、输入码、门诊科室、号类、号别序号等信息';

comment on column CLINIC_INDEX.ID is
'主键';

comment on column CLINIC_INDEX.ORG_ID is
'所属组织';

comment on column CLINIC_INDEX.CLINIC_LABEL is
'号别名称';

comment on column CLINIC_INDEX.CLINIC_DEPT is
'门诊科室';

comment on column CLINIC_INDEX.DOCTOR is
'医生';

comment on column CLINIC_INDEX.DOCTOR_TITLE is
'医生职称';

comment on column CLINIC_INDEX.CLINIC_TYPE is
'号类';

comment on column CLINIC_INDEX.INPUT_CODE is
'输入码';

comment on column CLINIC_INDEX.CLINIC_POSITION is
'门诊科室地址';

comment on column CLINIC_INDEX.SERIAL_NO is
'排序号';

comment on column CLINIC_INDEX.CREATE_DATE is
'创建日期';

comment on column CLINIC_INDEX.CREATE_BY is
'创建人';

comment on column CLINIC_INDEX.UPDATE_DATE is
'更新日期';

comment on column CLINIC_INDEX.UPDATE_BY is
'更新人';

comment on column CLINIC_INDEX.REMARKS is
'备注信息';

comment on column CLINIC_INDEX.DEL_FLAG is
'删除标志';
