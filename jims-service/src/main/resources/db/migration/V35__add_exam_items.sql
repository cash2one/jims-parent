-- drop table EXAM_ITEMS cascade constraints;

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
  CREATE_DATE         TIMESTAMP (6),
  CREATE_BY            VARCHAR2(64 CHAR),
  UPDATE_DATE          TIMESTAMP (6),
  UPDATE_BY            VARCHAR2(64 CHAR),
  REMARKS              VARCHAR2(255 CHAR),
  DEL_FLAG             NUMBER (1),
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
comment on column EXAM_ITEMS.CREATE_DATE
  is '创建日期';
comment on column EXAM_ITEMS.CREATE_BY
  is '创建人';
comment on column EXAM_ITEMS.UPDATE_DATE
  is '修改日期';
comment on column EXAM_ITEMS.UPDATE_BY
  is '修改人';
comment on column EXAM_ITEMS.REMARKS
  is '备注信息';
comment on column EXAM_ITEMS.DEL_FLAG
  is '删除标志';  