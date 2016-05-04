-- drop table LAB_TEST_ITEMS cascade constraints;

/*==============================================================*/
/* Table: LAB_TEST_ITEMS     检验项目                                 */
/*==============================================================*/
-- Create table
create table LAB_TEST_ITEMS
(
  ID                VARCHAR2(64 CHAR) not null,
  TEST_NO           VARCHAR2(10 CHAR),
  ITEM_NO           NUMBER(2),
  ITEM_NAME         VARCHAR2(100 CHAR),
  ITEM_CODE         VARCHAR2(20 CHAR),
  BILLING_INDICATOR NUMBER(1) default 0,
  TEST_BY           VARCHAR2(10 CHAR),
  RCPT_NO           VARCHAR2(20 CHAR),
  EXPLANATION       VARCHAR2(100 CHAR),
  DEL_FLAG          NUMBER(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table LAB_TEST_ITEMS
  is '检验项目';
-- Add comments to the columns
comment on column LAB_TEST_ITEMS.EXPLANATION
  is '退费说明';
-- Create/Recreate primary, unique and foreign key constraints
alter table LAB_TEST_ITEMS
  add constraint PK_LAB_TEST_ITEMS primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
