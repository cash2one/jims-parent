-- Create table
create table AIRCONDITION_CLASS_DICT
(
  ID                      VARCHAR2(64) not null,
  AIRCONDITION_CLASS_CODE VARCHAR2(20) not null,
  AIRCONDITION_CLASS_NAME VARCHAR2(100),
  INPUT_CODE              VARCHAR2(8),
  ORG_ID                  VARCHAR2(64),
  REMARKS                 VARCHAR2(2000),
  UPDATE_BY               VARCHAR2(64),
  CREATE_BY               VARCHAR2(64),
  UPDATE_DATE             DATE,
  DEL_FLAG                VARCHAR2(100),
  CREATE_DATE             DATE
);
-- Add comments to the table
comment on table AIRCONDITION_CLASS_DICT
  is '床位空调等级';
-- Add comments to the columns
comment on column AIRCONDITION_CLASS_DICT.ID
  is '主键';
comment on column AIRCONDITION_CLASS_DICT.AIRCONDITION_CLASS_CODE
  is '空调等级代码(对应价表)';
comment on column AIRCONDITION_CLASS_DICT.AIRCONDITION_CLASS_NAME
  is '空调等级名称';
comment on column AIRCONDITION_CLASS_DICT.INPUT_CODE
  is '拼音码';
comment on column AIRCONDITION_CLASS_DICT.ORG_ID
  is '组织机构';
-- Create/Recreate primary, unique and foreign key constraints
alter table AIRCONDITION_CLASS_DICT
  add constraint AIRCONDITION_CLASS_DICT_PK primary key (ID);
