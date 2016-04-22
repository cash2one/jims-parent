drop table ELECTRON_GROUP_CONSULTATION cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_GROUP_CONSULTATION     会诊主表                      */
/*==============================================================*/
create table ELECTRON_GROUP_CONSULTATION 
(
   ID                   VARCHAR2(64)         not null,
   ZHUYUAN_ID           VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   DOCTOR_ID            VARCHAR2(64),
   GROUPTYPE            VARCHAR2(1),
   SHENQINGSHIJIAN      TIMESTAMP,
   BINGQINGZHAIYAO      CLOB,
   HUIZHENLIYOU         CLOB,
   HUIZHENYIJIAN        CLOB,
   CREATE_DATE          TIMESTAMP,
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   FABUFLAG             VARCHAR2(10),
   DEL_FLAG             VARCHAR2(1),
   constraint "PK_electron_group_consultation" primary key (ID)
);

comment on table ELECTRON_GROUP_CONSULTATION is
'会诊记录';

comment on column ELECTRON_GROUP_CONSULTATION.ZHUYUAN_ID is
'住院号';

comment on column ELECTRON_GROUP_CONSULTATION.PATIENT_ID is
'患者id';

comment on column ELECTRON_GROUP_CONSULTATION.DOCTOR_ID is
'医生ID';

comment on column ELECTRON_GROUP_CONSULTATION.GROUPTYPE is
'类型：紧急、一般';

comment on column ELECTRON_GROUP_CONSULTATION.SHENQINGSHIJIAN is
'申请时间';

comment on column ELECTRON_GROUP_CONSULTATION.BINGQINGZHAIYAO is
'病情摘要';

comment on column ELECTRON_GROUP_CONSULTATION.HUIZHENLIYOU is
'会诊理由';

comment on column ELECTRON_GROUP_CONSULTATION.HUIZHENYIJIAN is
'会诊意见';

comment on column ELECTRON_GROUP_CONSULTATION.FABUFLAG is
'发布标志';