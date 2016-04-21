drop table OUTP_TREAT_REC cascade constraints;

/*==============================================================*/
/* Table: OUTP_TREAT_REC      检查治疗医嘱明细记录                                */
/*==============================================================*/
create table OUTP_TREAT_REC
(
  ID               VARCHAR2(64 CHAR) not null,
  VISIT_DATE       TIMESTAMP,
  VISIT_NO         NUMBER(5),
  SERIAL_NO        VARCHAR2(10 CHAR),
  ITEM_NO          NUMBER(4),
  ITEM_CLASS       VARCHAR2(64 CHAR),
  ITEM_CODE        VARCHAR2(20),
  ITEM_NAME        VARCHAR2(100),
  ITEM_SPEC        VARCHAR2(50),
  UNITS            VARCHAR2(8),
  AMOUNT           NUMBER(8,2),
  FREQUENCY        VARCHAR2(16),
  PERFORMED_BY     VARCHAR2(8),
  COSTS            NUMBER(8,2),
  CHARGES          NUMBER(8,2),
  CHARGE_INDICATOR NUMBER(1),
  APPOINT_NO       VARCHAR2(10),
  APPOINT_ITEM_NO  NUMBER(2),
  FREQ_DETAIL      VARCHAR2(80),
  WARD_CODE        VARCHAR2(20),
  EXPLANATION      VARCHAR2(100),
  RCPT_NO          VARCHAR2(20),
  BILL_VISIT_NO    NUMBER(5),
  BILL_VISIT_DATE  TIMESTAMP,
  OPERATOR_TRTURN  VARCHAR2(20),
  DATE_TRTURN      TIMESTAMP
);

-- Add comments to the table 
comment on table OUTP_TREAT_REC
  is '检查治疗医嘱明细记录';
-- Add comments to the columns 
comment on column OUTP_TREAT_REC.VISIT_DATE
  is '就诊日期';
comment on column OUTP_TREAT_REC.VISIT_NO
  is '就诊序号';
comment on column OUTP_TREAT_REC.SERIAL_NO
  is '流水号';
comment on column OUTP_TREAT_REC.ITEM_NO
  is '项目序号';
comment on column OUTP_TREAT_REC.ITEM_CLASS
  is '项目类别';
comment on column OUTP_TREAT_REC.ITEM_CODE
  is '项目编码';
comment on column OUTP_TREAT_REC.ITEM_NAME
  is '项目名称';
comment on column OUTP_TREAT_REC.ITEM_SPEC
  is '项目规格';
comment on column OUTP_TREAT_REC.UNITS
  is '单位';
comment on column OUTP_TREAT_REC.AMOUNT
  is '数量';
comment on column OUTP_TREAT_REC.FREQUENCY
  is '频次';
comment on column OUTP_TREAT_REC.PERFORMED_BY
  is '执行科室';
comment on column OUTP_TREAT_REC.COSTS
  is '计价费用';
comment on column OUTP_TREAT_REC.CHARGES
  is '实收费用';
comment on column OUTP_TREAT_REC.CHARGE_INDICATOR
  is '收费标记';
comment on column OUTP_TREAT_REC.APPOINT_NO
  is '申请号';
comment on column OUTP_TREAT_REC.APPOINT_ITEM_NO
  is '申请明细号';
comment on column OUTP_TREAT_REC.FREQ_DETAIL
  is '执行时间详细描述';
comment on column OUTP_TREAT_REC.WARD_CODE
  is '护理单元';
comment on column OUTP_TREAT_REC.EXPLANATION
  is '申请退费理由';
comment on column OUTP_TREAT_REC.RCPT_NO
  is '收费单据号';
comment on column OUTP_TREAT_REC.BILL_VISIT_NO
  is '收费序号';
comment on column OUTP_TREAT_REC.BILL_VISIT_DATE
  is '收费日期';
comment on column OUTP_TREAT_REC.OPERATOR_TRTURN
  is '退费医生';
comment on column OUTP_TREAT_REC.DATE_TRTURN
  is '退费日期';