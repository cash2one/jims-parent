-- drop table LAB_TEST_ITEMS cascade constraints;

/*==============================================================*/
/* Table: LAB_TEST_ITEMS     检验项目                                 */
/*==============================================================*/
-- Create table
create table LAB_TEST_ITEMS
(
  ID                VARCHAR2(64 CHAR) not null,
  TEST_NO           VARCHAR2(20 CHAR),
  ITEM_NO           NUMBER(2),
  ITEM_NAME         VARCHAR2(100 CHAR),
  ITEM_CODE         VARCHAR2(20 CHAR),
  BILLING_INDICATOR NUMBER(1) default 0,
  TEST_BY           VARCHAR2(10 CHAR),
  RCPT_NO           VARCHAR2(20 CHAR),
  EXPLANATION       VARCHAR2(100 CHAR),
  Lab_master        VARCHAR2(64 CHAR),
  PRICE             NUMBER(4,2),
  ORG_ID            VARCHAR2(64 CHAR),
  DEL_FLAG          NUMBER(1),
  REMARKS VARCHAR2(2000  CHAR),
  UPDATE_BY VARCHAR2(64  CHAR),
  CREATE_BY VARCHAR2(64  CHAR),
  UPDATE_DATE TIMESTAMP(6),
  CREATE_DATE TIMESTAMP(6),
  constraint "PK_LAB_TEST_ITEMS" primary key (ID)
);

-- Add comments to the table
comment on table LAB_TEST_ITEMS
  is '检验项目';
-- Add comments to the columns
comment on column LAB_TEST_ITEMS.EXPLANATION
  is '退费说明';
