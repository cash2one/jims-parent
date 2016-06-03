-- Create table
-- Create table
create table OUTP_BILL_ITEMS
(
  ID                  VARCHAR2(64 CHAR),
  VISIT_DATE          TIMESTAMP,
  VISIT_NO            NUMBER(5),
  RCPT_NO             VARCHAR2(64 CHAR),
  ITEM_NO             NUMBER(2),
  ITEM_CLASS          VARCHAR2(1),
  CLASS_ON_RCPT       VARCHAR2(1),
  ITEM_CODE           VARCHAR2(64 CHAR),
  ITEM_NAME           VARCHAR2(100 CHAR),
  ITEM_SPEC           VARCHAR2(50 CHAR),
  AMOUNT              NUMBER(6,2),
  UNITS               VARCHAR2(64 CHAR),
  PERFORMED_BY        VARCHAR2(64 CHAR),
  COSTS               NUMBER(12,4),
  CHARGES             NUMBER(12,4),
  CONFIRMED_OPERATOR  VARCHAR2(64 CHAR),
  CONFIRMED_DATETIME  DATE,
  INVOICE_NO          VARCHAR2(64 CHAR),
  FLAG                NUMBER(8),
  REPETITION          NUMBER(2),
  CLASS_ON_RECKONING  VARCHAR2(64 CHAR),
  SUBJ_CODE           VARCHAR2(64 CHAR),
  PRICE_QUOTIETY      NUMBER(7,4),
  ITEM_PRICE          NUMBER(10,4),
  ORDER_NO            NUMBER(3),
  SUB_ORDER_NO        NUMBER(2),
  PRINTED_RCPT_NO     VARCHAR2(64 CHAR),
  SUB_ITEM_NO         VARCHAR2(64 CHAR),
  FREQ_DETAIL         VARCHAR2(80 CHAR),
  ADMINISTRATION      VARCHAR2(100 CHAR),
  PATTERN_NAME        VARCHAR2(40 CHAR),
  FREQUENCY           VARCHAR2(64 CHAR),
  CONFIRMED_AMOUNT    NUMBER(10,3),
  REFUNDED_FLAG       VARCHAR2(4 CHAR),
  REFUNDED_OPERATOR   VARCHAR2(64 CHAR),
  REFUNDED_DATETIME   TIMESTAMP,
  ORDER_GROUP         VARCHAR2(64 CHAR),
  ORDER_DOCTOR        VARCHAR2(20 CHAR),
  ORDER_DEPT          VARCHAR2(64 CHAR),
  PERFORMED_GROUP     VARCHAR2(64 CHAR),
  PERFORMED_DOCTOR    VARCHAR2(64 CHAR),
  DOCUMENT_NO         VARCHAR2(64 CHAR),
  APPOINT_NO          VARCHAR2(64 CHAR),
  PERFORMED_BY_DOCTOR VARCHAR2(64 CHAR),
  CWTJ_CODE           VARCHAR2(64 CHAR),
  WARD_CODE           VARCHAR2(64 CHAR),
  PERFORMED_BY_SUB    VARCHAR2(64 CHAR),
  CREATE_DATE         TIMESTAMP(6),
  CREATE_BY           VARCHAR2(64 CHAR),
  UPDATE_DATE         TIMESTAMP(6),
  UPDATE_BY           VARCHAR2(64 CHAR),
  REMARKS             VARCHAR2(200 CHAR),
  DEL_FLAG            NUMBER(1)
);
-- Add comments to the table 
comment on table OUTP_BILL_ITEMS
  is '门诊病人诊疗费用项目';
-- Add comments to the columns 
comment on column OUTP_BILL_ITEMS.VISIT_DATE
  is '就诊日期';
comment on column OUTP_BILL_ITEMS.VISIT_NO
  is '就诊序号';
comment on column OUTP_BILL_ITEMS.RCPT_NO
  is '收据号';
comment on column OUTP_BILL_ITEMS.ITEM_NO
  is '序号';
comment on column OUTP_BILL_ITEMS.ITEM_CLASS
  is '价表项目分类';
comment on column OUTP_BILL_ITEMS.CLASS_ON_RCPT
  is '收据项目分类';
comment on column OUTP_BILL_ITEMS.ITEM_CODE
  is '项目代码';
comment on column OUTP_BILL_ITEMS.ITEM_NAME
  is '项目名称';
comment on column OUTP_BILL_ITEMS.ITEM_SPEC
  is '项目规格';
comment on column OUTP_BILL_ITEMS.AMOUNT
  is '数量';
comment on column OUTP_BILL_ITEMS.UNITS
  is '单位';
comment on column OUTP_BILL_ITEMS.PERFORMED_BY
  is '执行科室';
comment on column OUTP_BILL_ITEMS.COSTS
  is '费用';
comment on column OUTP_BILL_ITEMS.CHARGES
  is '应付费用';
comment on column OUTP_BILL_ITEMS.WARD_CODE
  is '护理单元';
comment on column OUTP_BILL_ITEMS.PERFORMED_BY_SUB
  is '药局子库房';

