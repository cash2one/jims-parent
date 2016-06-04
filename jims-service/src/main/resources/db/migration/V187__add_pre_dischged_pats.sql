
-- Create table
create table PRE_DISCHGED_PATS
(
  ID                          VARCHAR2(64) not null,
  PATIENT_ID                 VARCHAR2(64) not null,
  ORG_ID                 VARCHAR2(64 CHAR),
  VISIT_ID               NUMBER(2),
  DISCHARGE_DATE_EXPCTED     TIMESTAMP,
  CREATE_DATE_TIME           DATE,
  ORDER_NO                   VARCHAR2(64),
  DISCHARGE_DISPOSITION_NAME VARCHAR2(100),
  CREATE_BY                  VARCHAR2(64 char),
  UPDATE_BY                  VARCHAR2(64 char),
  UPDATE_DATE                TIMESTAMP,
  REMARKS                    VARCHAR2(200 char),
  DEL_FLAG                   NUMBER(1) default 0,
  constraint PK_PRE_DISCHGED_PATS primary key (ID)
);
-- Add comments to the table
comment on table PRE_DISCHGED_PATS
  is '准备出院病人记录表';
-- Add comments to the columns
comment on column PRE_DISCHGED_PATS.ID
  is '主键';
  comment on column PRE_DISCHGED_PATS.ORG_ID
  is '机构id';
  comment on column PRE_DISCHGED_PATS.VISIT_ID
  is '住院标识';
comment on column PRE_DISCHGED_PATS.PATIENT_ID
  is '病人标识号';
comment on column PRE_DISCHGED_PATS.DISCHARGE_DATE_EXPCTED
  is '预计出院日期';
comment on column PRE_DISCHGED_PATS.CREATE_DATE_TIME
  is '做出预计的时间';
  comment on column PRE_DISCHGED_PATS.ORDER_NO
  is '医嘱序号';
    comment on column PRE_DISCHGED_PATS.DISCHARGE_DISPOSITION_NAME
  is '出院方式';
