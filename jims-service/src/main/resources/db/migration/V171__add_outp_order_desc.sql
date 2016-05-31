-- Create table
create table OUTP_ORDER_DESC
(
  ID                VARCHAR(64 CHAR) not null,
  VISIT_DATE        TIMESTAMP(6),
  VISIT_NO          NUMBER(5),
  PATIENT_ID        VARCHAR2(64 CHAR),
  PRESC_INDICATOR   NUMBER(1),
  ORDERED_BY_DEPT   VARCHAR2(64 CHAR),
  ORDERED_BY_DOCTOR VARCHAR2(20 CHAR),
  RCPT_NO           VARCHAR2(20 CHAR),
  VISIT_ID          NUMBER(4),
  PRINTED_RCPT_NO   VARCHAR2(20 CHAR),
  PRESC_ATTR        VARCHAR2(64 CHAR),
  CLINIC_NO         VARCHAR2(15 CHAR),
  ORDER_GROUP       VARCHAR2(64 CHAR),
  CREATE_DATE       TIMESTAMP(6),
  CREATE_BY         VARCHAR2(64 CHAR),
  UPDATE_DATE       TIMESTAMP(6),
  UPDATE_BY         VARCHAR2(64 CHAR),
  REMARKS           VARCHAR2(200 CHAR),
  DEL_FLAG          NUMBER(1)
);
-- Add comments to the table
comment on table OUTP_ORDER_DESC
  is '开单记录';
-- Add comments to the columns
comment on column OUTP_ORDER_DESC.VISIT_DATE
  is '就诊时间';
comment on column OUTP_ORDER_DESC.VISIT_NO
  is '就诊序号';
comment on column OUTP_ORDER_DESC.PATIENT_ID
  is '病人基本信息';
comment on column OUTP_ORDER_DESC.PRESC_INDICATOR
  is '药品处方标志';
comment on column OUTP_ORDER_DESC.ORDERED_BY_DEPT
  is '开单科室';
comment on column OUTP_ORDER_DESC.ORDERED_BY_DOCTOR
  is '开单医生';
comment on column OUTP_ORDER_DESC.RCPT_NO
  is '收据号';
comment on column OUTP_ORDER_DESC.VISIT_ID
  is '就诊ID';
comment on column OUTP_ORDER_DESC.PRINTED_RCPT_NO
  is '打印数据号';
comment on column OUTP_ORDER_DESC.CLINIC_NO
  is '门诊号';
comment on column OUTP_ORDER_DESC.ORDER_GROUP
  is '医生核算组代码';
comment on column OUTP_ORDER_DESC.PRESC_ATTR
  is '处方属性';

