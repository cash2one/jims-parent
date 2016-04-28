-- drop table ELECTRON_GROUP_CONSULTATION cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_GROUP_CONSULTATION     会诊主表                      */
/*==============================================================*/
create table ELECTRON_GROUP_CONSULTATION 
(
     ID              VARCHAR2(64 CHAR) not null,
  ZHUYUAN_ID      VARCHAR2(64 CHAR),
  PATIENT_ID      VARCHAR2(64 CHAR),
  DOCTOR_ID       VARCHAR2(64 CHAR),
  GROUPTYPE       VARCHAR2(1 CHAR),
  SHENQINGSHIJIAN TIMESTAMP(6),
  BINGQINGZHAIYAO CLOB,
  HUIZHENLIYOU    CLOB,
  HUIZHENYIJIAN   CLOB,
  CREATE_DATE     TIMESTAMP(6),
  UPDATE_DATE     TIMESTAMP(6),
  CREATE_BY       VARCHAR2(255 CHAR),
  UPDATE_BY       VARCHAR2(255 CHAR),
  FABUFLAG        NUMBER (1),
  DEL_FLAG        NUMBER (1),
  IDEA_FLAG       NUMBER (1),
   constraint "PK_electron_group" primary key (ID)
   );
-- Add comments to the table
comment on table ELECTRON_GROUP_CONSULTATION
  is '会诊记录';
-- Add comments to the columns
comment on column ELECTRON_GROUP_CONSULTATION.ZHUYUAN_ID
  is '住院号';
comment on column ELECTRON_GROUP_CONSULTATION.PATIENT_ID
  is '患者id';
comment on column ELECTRON_GROUP_CONSULTATION.DOCTOR_ID
  is '医生ID';
comment on column ELECTRON_GROUP_CONSULTATION.GROUPTYPE
  is '类型：紧急、一般';
comment on column ELECTRON_GROUP_CONSULTATION.SHENQINGSHIJIAN
  is '申请时间';
comment on column ELECTRON_GROUP_CONSULTATION.BINGQINGZHAIYAO
  is '病情摘要';
comment on column ELECTRON_GROUP_CONSULTATION.HUIZHENLIYOU
  is '会诊理由';
comment on column ELECTRON_GROUP_CONSULTATION.HUIZHENYIJIAN
  is '会诊意见';
comment on column ELECTRON_GROUP_CONSULTATION.FABUFLAG
  is '发布标志';
comment on column ELECTRON_GROUP_CONSULTATION.IDEA_FLAG
  is '发表意见标志';