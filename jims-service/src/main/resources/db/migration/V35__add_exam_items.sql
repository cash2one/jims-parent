drop table EXAM_ITEMS cascade constraints;

/*==============================================================*/
/* Table: EXAM_ITEMS      检查项目                                */
/*==============================================================*/
create table EXAM_ITEMS
(
  ID                VARCHAR2(64 CHAR) not null,
  EXAM_NO           VARCHAR2(10 CHAR),
  EXAM_ITEM_NO      NUMBER(2),
  EXAM_ITEM         VARCHAR2(100 CHAR),
  EXAM_ITEM_CODE    VARCHAR2(20 CHAR),
  COSTS             NUMBER(8,2),
  BILLING_INDICATOR NUMBER(1) default 0,
  RCPT_NO           VARCHAR2(20 CHAR),
  EXPLANATION       VARCHAR2(100 CHAR),
 constraint PK_EXAM_ITEMS primary key (ID)
);
-- Add comments to the table
comment on table EXAM_ITEMS
  is '检查项目记录';
-- Add comments to the columns
comment on column EXAM_ITEMS.EXAM_NO
  is '申请序号';
comment on column EXAM_ITEMS.EXAM_ITEM_NO
  is '项目序号';
comment on column EXAM_ITEMS.EXAM_ITEM
  is '检查项目';
comment on column EXAM_ITEMS.EXAM_ITEM_CODE
  is '项目代码';
comment on column EXAM_ITEMS.COSTS
  is '费用';
comment on column EXAM_ITEMS.RCPT_NO
  is '收据号';
comment on column EXAM_ITEMS.EXPLANATION
  is '退费说明';