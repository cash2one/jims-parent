-- Create table
create table CLINIC_ITEM_NAME_DICT
(
  ITEM_CLASS    VARCHAR2(1) not null,
  ITEM_NAME     VARCHAR2(100) not null,
  ITEM_CODE     VARCHAR2(20) not null,
  STD_INDICATOR NUMBER(1),
  INPUT_CODE    VARCHAR2(8),
  INPUT_CODE_WB VARCHAR2(8),
  EXPAND1       VARCHAR2(100),
  EXPAND2       VARCHAR2(100),
  EXPAND3       VARCHAR2(100),
  EXPAND4       VARCHAR2(100),
  EXPAND5       VARCHAR2(100),
  ITEM_STATUS   VARCHAR2(1) default '0',
  BBSM          VARCHAR2(100),
  USER_GRANT    VARCHAR2(20),
  ID            VARCHAR2(64) not null,
  ORG_ID        VARCHAR2(64),
  REMARKS       VARCHAR2(2000),
  UPDATE_BY     VARCHAR2(64),
  CREATE_BY     VARCHAR2(64),
  UPDATE_DATE   TIMESTAMP(6),
  DEL_FLAG      VARCHAR2(2),
  CREATE_DATE   TIMESTAMP(6)
);
comment on table CLINIC_ITEM_NAME_DICT
  is '诊疗项目名称表';
-- Add comments to the columns 
comment on column CLINIC_ITEM_NAME_DICT.ITEM_CLASS
  is '项目分类';
comment on column CLINIC_ITEM_NAME_DICT.ITEM_NAME
  is '项目名称';
comment on column CLINIC_ITEM_NAME_DICT.ITEM_CODE
  is '项目代码';
comment on column CLINIC_ITEM_NAME_DICT.STD_INDICATOR
  is '正名标志（1 正名，2 别名）';
comment on column CLINIC_ITEM_NAME_DICT.INPUT_CODE
  is '输入码';
comment on column CLINIC_ITEM_NAME_DICT.INPUT_CODE_WB
  is '五笔码';
comment on column CLINIC_ITEM_NAME_DICT.ID
  is '主键';
comment on column CLINIC_ITEM_NAME_DICT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CLINIC_ITEM_NAME_DICT
  add constraint CLINIC_ITEM_NAME_DICT_PK primary key (ID);
