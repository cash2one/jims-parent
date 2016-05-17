-- Create table
create table BLOOD_APPLY
(
  APPLY_NUM       VARCHAR2(60) not null,
  INP_NO          VARCHAR2(100),
  ID_NO      VARCHAR2(100),
  DEPT_CODE       VARCHAR2(8),
  PAT_NAME        VARCHAR2(20),
  PAT_SEX         VARCHAR2(40),
  BIRTHDAY        DATE,
  FEE_TYPE        VARCHAR2(80),
  PAT_SOURCE      VARCHAR2(90),
  BLOOD_PAPER     VARCHAR2(80),
  BLOOD_INUSE     VARCHAR2(40),
  BLOOD_DIAGNOSE  VARCHAR2(80),
  BLOOD_TABOO     VARCHAR2(40),
  HEMATIN         NUMBER(38),
  PLATELET        NUMBER(38),
  LEUCOCYTE       NUMBER(38),
  PAT_BLOOD_GROUP VARCHAR2(40),
  RH              VARCHAR2(40),
  BLOOD_SUM       NUMBER(6),
  APPLY_DATE      DATE,
  GATHER_DATE     DATE,
  DIRECTOR        VARCHAR2(200),
  PHYSICIAN       VARCHAR2(200),
  DOCTOR          VARCHAR2(200),
  PRICE           VARCHAR2(100),
  HCT             NUMBER(6,2),
  ALT             NUMBER(6,2),
  HBSAG           VARCHAR2(100),
  HCV             VARCHAR2(100),
  HIV             VARCHAR2(100),
  ANTI_MD         VARCHAR2(100),
  SHINE_BLOOD     VARCHAR2(100),
  PRE_BLOOD_TYPE  VARCHAR2(100),
  ID              VARCHAR2(64) not null,
  ORG_ID          VARCHAR2(64),
  REMARKS         VARCHAR2(2000),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     date,
  DEL_FLAG        VARCHAR2(100),
  CREATE_DATE     DATE
);
-- Add comments to the table 
comment on table BLOOD_APPLY
  is '用血申请';
-- Add comments to the columns 
comment on column BLOOD_APPLY.APPLY_NUM
  is '申请单号';
comment on column BLOOD_APPLY.INP_NO
  is '住院号';
comment on column BLOOD_APPLY.ID_NO
  is '病人身份证号';
comment on column BLOOD_APPLY.DEPT_CODE
  is '科室代码';
comment on column BLOOD_APPLY.PAT_NAME
  is '受血者姓名';
comment on column BLOOD_APPLY.PAT_SEX
  is '性别';
comment on column BLOOD_APPLY.BIRTHDAY
  is '出生年月';
comment on column BLOOD_APPLY.FEE_TYPE
  is '费别';
comment on column BLOOD_APPLY.PAT_SOURCE
  is '病人来源，市区：1；郊县：2，外省市：3；港澳台：4';
comment on column BLOOD_APPLY.BLOOD_PAPER
  is '献血证类型';
comment on column BLOOD_APPLY.BLOOD_INUSE
  is '用血方式：血库，自体，互助';
comment on column BLOOD_APPLY.BLOOD_DIAGNOSE
  is '诊断及输血适应症';
comment on column BLOOD_APPLY.BLOOD_TABOO
  is '输血反应及输血禁忌症';
comment on column BLOOD_APPLY.HEMATIN
  is '血色素';
comment on column BLOOD_APPLY.PLATELET
  is '血小板';
comment on column BLOOD_APPLY.LEUCOCYTE
  is '白血球';
comment on column BLOOD_APPLY.PAT_BLOOD_GROUP
  is '受血者血型';
comment on column BLOOD_APPLY.RH
  is 'Rh血型';
comment on column BLOOD_APPLY.BLOOD_SUM
  is '输血总量';
comment on column BLOOD_APPLY.APPLY_DATE
  is '申请填写时间';
comment on column BLOOD_APPLY.GATHER_DATE
  is '血库收到时间';
comment on column BLOOD_APPLY.DIRECTOR
  is '科主任';
comment on column BLOOD_APPLY.PHYSICIAN
  is '主治军医';
comment on column BLOOD_APPLY.DOCTOR
  is '军医';
comment on column BLOOD_APPLY.PRICE
  is '划价标志';
comment on column BLOOD_APPLY.ANTI_MD
  is '梅毒';
comment on column BLOOD_APPLY.SHINE_BLOOD
  is '辐照血';
comment on column BLOOD_APPLY.PRE_BLOOD_TYPE
  is '预输血型';
comment on column BLOOD_APPLY.ID
  is '主键';
comment on column BLOOD_APPLY.ORG_ID
  is '所属结构';