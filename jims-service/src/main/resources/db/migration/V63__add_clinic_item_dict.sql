-- Create table
create table CLINIC_ITEM_DICT
(
  ID            VARCHAR2(64) not null,
  ITEM_CLASS    VARCHAR2(1) not null,
  ITEM_CODE     VARCHAR2(20) not null,
  ITEM_NAME     VARCHAR2(100),
  INPUT_CODE    VARCHAR2(8),
  INPUT_CODE_WB VARCHAR2(8),
  EXPAND1       VARCHAR2(100),
  EXPAND2       VARCHAR2(100),
  EXPAND3       VARCHAR2(100),
  EXPAND4       VARCHAR2(100),
  EXPAND5       VARCHAR2(100),
  ITEM_STATUS   VARCHAR2(1) default '0',
  MEMO          VARCHAR2(60),
  REMARKS       VARCHAR2(2000),
  UPDATE_BY     VARCHAR2(64),
  CREATE_BY     VARCHAR2(64),
  UPDATE_DATE   TIMESTAMP(6),
  DEL_FLAG      VARCHAR2(2),
  CREATE_DATE   TIMESTAMP(6),
  ORG_ID        VARCHAR2(64)
);

-- Add comments to the table
comment on table CLINIC_ITEM_DICT
  is '诊疗项目字典表';
-- Add comments to the columns
comment on column CLINIC_ITEM_DICT.ID
  is '主键';
comment on column CLINIC_ITEM_DICT.ITEM_CLASS
  is '项目分类';
comment on column CLINIC_ITEM_DICT.ITEM_CODE
  is '项目代码';
comment on column CLINIC_ITEM_DICT.ITEM_NAME
  is '项目名称';
comment on column CLINIC_ITEM_DICT.INPUT_CODE
  is '输入码';
comment on column CLINIC_ITEM_DICT.EXPAND1
  is '标本';
comment on column CLINIC_ITEM_DICT.EXPAND2
  is '类别';
comment on column CLINIC_ITEM_DICT.EXPAND3
  is '执行科室';
comment on column CLINIC_ITEM_DICT.EXPAND4
  is '频次';
comment on column CLINIC_ITEM_DICT.EXPAND5
  is '长期临时（1 长期，2 临时）';
comment on column CLINIC_ITEM_DICT.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table CLINIC_ITEM_DICT
  add constraint CLINIC_ITEM_DICT_PK primary key (ID);

-- Create/Recreate primary, unique and foreign key constraints
alter table CLINIC_ITEM_DICT
  add constraint CLINIC_ITEM_DICT_UK unique (ITEM_CODE, ORG_ID);


