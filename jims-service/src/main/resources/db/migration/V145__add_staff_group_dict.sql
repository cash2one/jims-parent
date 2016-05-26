-- Create table
create table STAFF_GROUP_DICT
(
  GROUP_CLASS_ID VARCHAR2(64) not null,
  GROUP_CODE     VARCHAR2(8) not null,
  GROUP_NAME     VARCHAR2(20),
  INPUT_CODE     VARCHAR2(8),
  DEPT_NAME      VARCHAR2(20),
  DEPT_CODE      VARCHAR2(8),
  GROUP_MANAGER  VARCHAR2(20),
  REMARKS        VARCHAR2(2000),
  UPDATE_BY      VARCHAR2(64),
  CREATE_BY      VARCHAR2(64),
  UPDATE_DATE    DATE,
  DEL_FLAG       VARCHAR2(100),
  CREATE_DATE    DATE,
  ID             VARCHAR2(64) not null
);
-- Add comments to the table
comment on table STAFF_GROUP_DICT
  is '用户分组';
-- Add comments to the columns
comment on column STAFF_GROUP_DICT.GROUP_CLASS_ID
  is '所属组类';
comment on column STAFF_GROUP_DICT.GROUP_CODE
  is '组代码，默认为组织机构';
comment on column STAFF_GROUP_DICT.GROUP_NAME
  is '组名';
comment on column STAFF_GROUP_DICT.INPUT_CODE
  is '输入码';
comment on column STAFF_GROUP_DICT.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table STAFF_GROUP_DICT
  add constraint STAFF_GROUP_DICT_PK primary key (ID);
alter table STAFF_GROUP_DICT
  add constraint STAFF_GROUP_DICT_UK unique (GROUP_CLASS_ID, GROUP_CODE);
