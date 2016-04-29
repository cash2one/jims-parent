-- Create table
create table EXAM_RPT_PATTERN
(
  EXAM_CLASS       VARCHAR2(10) not null,
  EXAM_SUB_CLASS   VARCHAR2(12) not null,
  DESC_ITEM        VARCHAR2(20) not null,
  DESC_NAME        VARCHAR2(20) not null,
  DESCRIPTION      VARCHAR2(800),
  DESCRIPTION_CODE VARCHAR2(20),
  INPUT_CODE       VARCHAR2(8),
  DOCTOR_TESHU     VARCHAR2(50),
  ID               VARCHAR2(64) not null,
  ORG_ID           VARCHAR2(64),
  REMARKS          VARCHAR2(2000),
  UPDATE_BY        VARCHAR2(64),
  CREATE_BY        VARCHAR2(64),
  UPDATE_DATE      TIMESTAMP(6),
  DEL_FLAG         VARCHAR2(2),
  CREATE_DATE      TIMESTAMP(6)
);
-- Add comments to the table
comment on table EXAM_RPT_PATTERN
  is '检查项目';
-- Add comments to the columns
comment on column EXAM_RPT_PATTERN.EXAM_CLASS
  is '检查类别';
comment on column EXAM_RPT_PATTERN.EXAM_SUB_CLASS
  is '检查子类';
comment on column EXAM_RPT_PATTERN.DESC_ITEM
  is '描述项目';
comment on column EXAM_RPT_PATTERN.DESC_NAME
  is '描述名称';
comment on column EXAM_RPT_PATTERN.DESCRIPTION
  is '描述';
comment on column EXAM_RPT_PATTERN.DESCRIPTION_CODE
  is '描述代码';
comment on column EXAM_RPT_PATTERN.INPUT_CODE
  is '描述名称输入码';
comment on column EXAM_RPT_PATTERN.DOCTOR_TESHU
  is '指定医生，指定项目';
comment on column EXAM_RPT_PATTERN.ID
  is '主键';
comment on column EXAM_RPT_PATTERN.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXAM_RPT_PATTERN
  add constraint EXAM_RPT_PATTERN_PK primary key (ID)
  using index 
  tablespace JIMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;
