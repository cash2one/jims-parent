-- Create table
-- Create table
create table OUTP_PAYMENTS_MONEY
(
  ID              VARCHAR2(64 CHAR),
  RCPT_NO         VARCHAR2(64 CHAR),
  PAYMENT_NO      NUMBER(2),
  MONEY_TYPE      VARCHAR2(8 CHAR),
  PAYMENT_AMOUNT  NUMBER(8,2),
  REFUNDED_AMOUNT NUMBER(8,2),
  PREPAY_NO       VARCHAR2(64 CHAR),
  CREATE_DATE     TIMESTAMP(6),
  CREATE_BY       VARCHAR2(64 CHAR),
  UPDATE_DATE     TIMESTAMP(6),
  UPDATE_BY       VARCHAR2(64 CHAR),
  REMARKS         VARCHAR2(200 CHAR),
  DEL_FLAG        NUMBER(1)
);
-- Add comments to the table
comment on table OUTP_PAYMENTS_MONEY
  is '门诊病人支付方式记录';
-- Add comments to the columns
comment on column OUTP_PAYMENTS_MONEY.RCPT_NO
  is '收据号';
comment on column OUTP_PAYMENTS_MONEY.PAYMENT_NO
  is '支付序号';
comment on column OUTP_PAYMENTS_MONEY.MONEY_TYPE
  is '金额类型';
comment on column OUTP_PAYMENTS_MONEY.PAYMENT_AMOUNT
  is '数额';
comment on column OUTP_PAYMENTS_MONEY.REFUNDED_AMOUNT
  is '退数额';

