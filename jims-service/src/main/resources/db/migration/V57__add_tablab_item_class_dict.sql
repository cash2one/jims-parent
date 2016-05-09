-- Create table
create table LAB_ITEM_CLASS_DICT
(
  ID          VARCHAR2(64) not null,
  CLASS_CODE  VARCHAR2(8) not null,
  CLASS_NAME  VARCHAR2(20),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE TIMESTAMP(6),
  DEL_FLAG    VARCHAR2(2),
  CREATE_DATE TIMESTAMP(6),
  ORG_ID      VARCHAR2(64)
);
-- Add comments to the table 
comment on table LAB_ITEM_CLASS_DICT
  is '检验项目分类';
-- Add comments to the columns 
comment on column LAB_ITEM_CLASS_DICT.ID
  is '主键';
comment on column LAB_ITEM_CLASS_DICT.CLASS_CODE
  is '类别代码';
comment on column LAB_ITEM_CLASS_DICT.CLASS_NAME
  is '类别名称';
comment on column LAB_ITEM_CLASS_DICT.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table LAB_ITEM_CLASS_DICT
  add constraint LAB_ITEM_CLASS_DICT primary key (ID);

--不同组织机构不的检验分类，需进行区分