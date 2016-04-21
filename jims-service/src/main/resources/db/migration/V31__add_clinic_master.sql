drop table CLINIC_MASTER cascade constraints;

/*==============================================================*/
/* Table: CLINIC_MASTER   病人就诊记录信息                                      */
/*==============================================================*/
create table CLINIC_MASTER
(
  ID                    VARCHAR2(64 CHAR )not null,
  HOSID                 VARCHAR2(64 CHAR ),
  VISIT_DATE            DATE ,
  VISIT_NO              NUMBER(5),
  CLINIC_LABEL          VARCHAR2(64),
  VISIT_TIME_DESC       VARCHAR2(100 CHAR),
  SERIAL_NO             NUMBER(10),
  NAME                  VARCHAR2(40 CHAR),
  NAME_PHONETIC         VARCHAR2(20 CHAR),
  SEX                   VARCHAR2(64 CHAR),
  AGE                   NUMBER(3),
  IDENTITY              VARCHAR2(64 CHAR ),
  CHARGE_TYPE           VARCHAR2(64 CHAR ),
  INSURANCE_TYPE        VARCHAR2(64 CHAR),
  INSURANCE_NO          VARCHAR2(64 CHAR),
  UNIT_IN_CONTRACT      VARCHAR2(40 CHAR),
  CLINIC_TYPE           VARCHAR2(64 CHAR),
  FIRST_VISIT_INDICATOR NUMBER(1),
  VISIT_DEPT            VARCHAR2(64 CHAR),
  VISIT_SPECIAL_CLINIC  VARCHAR2(100 CHAR),
  DOCTOR                VARCHAR2(64 CHAR),
  MR_PROVIDE_INDICATOR  NUMBER(1),
  REGISTRATION_STATUS   NUMBER(1),
  REGISTERING_DATE      TIMESTAMP,
  SYMPTOM               VARCHAR2(40 CHAR ),
  REGIST_FEE            NUMBER(5,2),
  CLINIC_FEE            NUMBER(5,2),
  OTHER_FEE             NUMBER(5,2),
  CLINIC_CHARGE         NUMBER(5,2),
  OPERATOR              VARCHAR2(50 CHAR),
  RETURNED_DATE         TIMESTAMP,
  RETURNED_OPERATOR     VARCHAR2(50 CHAR),
  MODE_CODE             CHAR(1),
  CARD_NAME             VARCHAR2(30 CHAR),
  CARD_NO               VARCHAR2(20 CHAR),
  ACCT_DATE_TIME        TIMESTAMP,
  ACCT_NO               VARCHAR2(20 CHAR),
  PAY_WAY               VARCHAR2(64 CHAR),
  MR_PROVIDED_INDICATOR NUMBER(1),
  INVOICE_NO            VARCHAR2(20 CHAR),
  CLINIC_NO             VARCHAR2(64 CHAR),
  MR_NO                 VARCHAR2(20 CHAR),
  ISPRN                 NUMBER (1) ,
  PAT_TYPE              VARCHAR2(64 CHAR ),
  VALID_DATE            TIMESTAMP,
  AUTO_FLAG             VARCHAR2(2 CHAR ),
  PRINT_OPERATOR        VARCHAR2(8 CHAR ),
  PE_VISIT_ID           NUMBER(3),
  MAILING_ADDRESS       VARCHAR2(200 CHAR ),
  constraint PK_CLINIC_MASTER primary key (ID)
);
-- Add comments to the table 
comment on table CLINIC_MASTER
  is '就诊记录';
-- Add comments to the columns
comment on column CLINIC_MASTER.ID
  is '就诊记录ID';
comment on column CLINIC_MASTER.HOSID
  is '医院ID';
comment on column CLINIC_MASTER.VISIT_DATE
  is '就诊日期';
comment on column CLINIC_MASTER.VISIT_NO
  is '就诊序号';
comment on column CLINIC_MASTER.CLINIC_LABEL
  is '号别';
comment on column CLINIC_MASTER.VISIT_TIME_DESC
  is '就诊时间描述';
comment on column CLINIC_MASTER.SERIAL_NO
  is '号码';
comment on column CLINIC_MASTER.NAME
  is '姓名';
comment on column CLINIC_MASTER.NAME_PHONETIC
  is '姓名拼音';
comment on column CLINIC_MASTER.SEX
  is '性别';
comment on column CLINIC_MASTER.AGE
  is '年龄';
comment on column CLINIC_MASTER.IDENTITY
  is '身份';
comment on column CLINIC_MASTER.CHARGE_TYPE
  is '费别';
comment on column CLINIC_MASTER.INSURANCE_TYPE
  is '医保类别';
comment on column CLINIC_MASTER.INSURANCE_NO
  is '医疗保险号';
comment on column CLINIC_MASTER.UNIT_IN_CONTRACT
  is '合同单位';
comment on column CLINIC_MASTER.CLINIC_TYPE
  is '号类';
comment on column CLINIC_MASTER.FIRST_VISIT_INDICATOR
  is '初诊标志';
comment on column CLINIC_MASTER.VISIT_DEPT
  is '就诊科室';
comment on column CLINIC_MASTER.VISIT_SPECIAL_CLINIC
  is '就诊专科';
comment on column CLINIC_MASTER.DOCTOR
  is '医生';
comment on column CLINIC_MASTER.MR_PROVIDE_INDICATOR
  is '提供病案标志';
comment on column CLINIC_MASTER.REGISTRATION_STATUS
  is '挂号状态';
comment on column CLINIC_MASTER.REGISTERING_DATE
  is '挂号日期';
comment on column CLINIC_MASTER.SYMPTOM
  is '症状';
comment on column CLINIC_MASTER.REGIST_FEE
  is '挂号费';
comment on column CLINIC_MASTER.CLINIC_FEE
  is '诊疗费';
comment on column CLINIC_MASTER.OTHER_FEE
  is '其它费';
comment on column CLINIC_MASTER.CLINIC_CHARGE
  is '实收费用';
comment on column CLINIC_MASTER.OPERATOR
  is '挂号员';
comment on column CLINIC_MASTER.RETURNED_DATE
  is '退号日期';
comment on column CLINIC_MASTER.RETURNED_OPERATOR
  is '退号挂号员';
comment on column CLINIC_MASTER.MODE_CODE
  is '挂号模式';
comment on column CLINIC_MASTER.CARD_NAME
  is '卡名';
comment on column CLINIC_MASTER.CARD_NO
  is '卡号';
comment on column CLINIC_MASTER.ACCT_DATE_TIME
  is '结帐时间';
comment on column CLINIC_MASTER.ACCT_NO
  is '结帐号码';
comment on column CLINIC_MASTER.PAY_WAY
  is '支付方式';
comment on column CLINIC_MASTER.MR_PROVIDED_INDICATOR
  is '病案传送否';
comment on column CLINIC_MASTER.INVOICE_NO
  is '发票号码';
comment on column CLINIC_MASTER.ISPRN
  is '病历单是否已经打印';
comment on column CLINIC_MASTER.PRINT_OPERATOR
  is '打印操作员';
comment on column CLINIC_MASTER.MAILING_ADDRESS
  is '住址';