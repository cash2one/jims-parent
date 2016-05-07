-- Create table
create table EXAM_SUBCLASS_DICT
(
  EXAM_CLASS_NAME    VARCHAR2(10) not null,
  EXAM_SUBCLASS_NAME VARCHAR2(12) not null,
  INPUT_CODE         VARCHAR2(8),
  ID                 VARCHAR2(64) not null,
  ORG_ID             VARCHAR2(64),
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        TIMESTAMP(6),
  DEL_FLAG           VARCHAR2(2),
  CREATE_DATE        TIMESTAMP(6)
);
-- Add comments to the table
comment on table EXAM_SUBCLASS_DICT
  is '检查项目子类';
-- Add comments to the columns
comment on column EXAM_SUBCLASS_DICT.EXAM_CLASS_NAME
  is '检查类别名称';
comment on column EXAM_SUBCLASS_DICT.EXAM_SUBCLASS_NAME
  is '检查子类名称';
comment on column EXAM_SUBCLASS_DICT.INPUT_CODE
  is '输入码';
comment on column EXAM_SUBCLASS_DICT.ID
  is '主键';
comment on column EXAM_SUBCLASS_DICT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table EXAM_SUBCLASS_DICT
  add constraint EXAM_SUBCLASS_DICT_PK primary key (ID)
  using index 

