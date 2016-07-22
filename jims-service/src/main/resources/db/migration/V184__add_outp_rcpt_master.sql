-- Create table
create table OUTP_RCPT_MASTER
(
  ID                  VARCHAR2(64 CHAR) not null,
  RCPT_NO             VARCHAR2(64 CHAR),
  PATIENTID           VARCHAR2(64 CHAR),
  NAME                VARCHAR2(60 CHAR),
  NAME_PHONETIC       VARCHAR2(16 CHAR),
  IDENTITY            VARCHAR2(64 CHAR),
  CHARGE_TYPE         VARCHAR2(64 CHAR),
  UNIT_IN_CONTRACT    VARCHAR2(100 CHAR),
  VISIT_DATE          TIMESTAMP(6),
  TOTAL_COSTS         NUMBER(8,2),
  TOTAL_CHARGES       NUMBER(8,2),
  OPERATOR_NO         VARCHAR2(64 CHAR),
  CHARGE_INDICATOR    NUMBER(1),
  REFUNDED_RCPT_NO    VARCHAR2(50 CHAR),
  ACCT_NO             VARCHAR2(10 CHAR),
  PRINTED_OPERATOR_NO VARCHAR2(64 CHAR),
  PRINTED_DATE        TIMESTAMP(6),
  CARD_FLAG           CHAR(1),
  PRINTED_RCPT_NO     VARCHAR2(64 CHAR),
  FLAG                VARCHAR2(64 CHAR),
  INVOICE_NO          VARCHAR2(64 CHAR),
  BZ                  VARCHAR2(64 CHAR),
  INSURANCE_NO        VARCHAR2(64 CHAR),
  ORD_INVOICE_NO      VARCHAR2(64 CHAR),
  RECKON_NO           VARCHAR2(64 CHAR),
  RCPT_PRINT          VARCHAR2(64 CHAR),
  ORDERED_BY_DEPT    VARCHAR2(64 CHAR),
  ORDERED_BY_DOCTOR  VARCHAR2(64 CHAR),
   CREATE_DATE          TIMESTAMP(6),
   CREATE_BY            VARCHAR2(64 CHAR),
   UPDATE_DATE          TIMESTAMP(6),
   UPDATE_BY            VARCHAR2(64 CHAR),
   REMARKS              VARCHAR2(200 CHAR),
   DEL_FLAG             NUMBER(1)
);
-- Add comments to the table 
comment on table OUTP_RCPT_MASTER
  is '门诊医疗收据记录';
-- Add comments to the columns 
comment on column OUTP_RCPT_MASTER.RCPT_NO
  is '收据号';
comment on column OUTP_RCPT_MASTER.PATIENTID
  is '病人基本信息';
comment on column OUTP_RCPT_MASTER.NAME
  is '姓名';
comment on column OUTP_RCPT_MASTER.NAME_PHONETIC
  is '姓名拼音';
comment on column OUTP_RCPT_MASTER.IDENTITY
  is '身份';
comment on column OUTP_RCPT_MASTER.CHARGE_TYPE
  is '费别';
comment on column OUTP_RCPT_MASTER.UNIT_IN_CONTRACT
  is '合同单位';
comment on column OUTP_RCPT_MASTER.VISIT_DATE
  is '就诊日期';
comment on column OUTP_RCPT_MASTER.TOTAL_COSTS
  is '总费用';
comment on column OUTP_RCPT_MASTER.TOTAL_CHARGES
  is '应收费';
comment on column OUTP_RCPT_MASTER.OPERATOR_NO
  is '收款员';
comment on column OUTP_RCPT_MASTER.CHARGE_INDICATOR
  is '交费标志';
comment on column OUTP_RCPT_MASTER.REFUNDED_RCPT_NO
  is '退费收据号';
comment on column OUTP_RCPT_MASTER.ACCT_NO
  is '结帐序号';
comment on column OUTP_RCPT_MASTER.RECKON_NO
  is '银海医保清算流水号';
comment on column OUTP_RCPT_MASTER.RCPT_PRINT
  is '序列号';