-- drop table CLINIC_MASTER cascade constraints;

/*==============================================================*/
/* Table: CLINIC_MASTER   病人就诊记录信息                                      */
/*==============================================================*/
create table CLINIC_MASTER
(
  ID                    VARCHAR2(64 CHAR )not null,
  ORG_ID                 VARCHAR2(64 CHAR ),
  PATIENT_ID VARCHAR2(64),
  VISIT_DATE            DATE ,
  VISIT_NO              NUMBER(5),
  CLINIC_LABEL          VARCHAR2(64),
  VISIT_TIME_DESC       VARCHAR2(100 CHAR),
  SERIAL_NO             NUMBER(10),
  NAME                  VARCHAR2(200 CHAR),
  NAME_PHONETIC         VARCHAR2(200 CHAR),
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
  OPERATOR              VARCHAR2(64 CHAR),
  RETURNED_DATE         TIMESTAMP,
  RETURNED_OPERATOR     VARCHAR2(64 CHAR),
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
  PRINT_OPERATOR        VARCHAR2(64 CHAR ),
  PE_VISIT_ID           NUMBER(3),
  MAILING_ADDRESS       VARCHAR2(200 CHAR ),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1) default '0',
  constraint PK_CLINIC_MASTER primary key (ID)
);
-- Add comments to the table 
comment on table CLINIC_MASTER
  is '就诊记录';
-- Add comments to the columns
comment on column CLINIC_MASTER.ID
  is '就诊记录ID';
comment on column CLINIC_MASTER.ORG_ID
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
  INSERT INTO "CLINIC_MASTER" ("ID", "ORG_ID", "VISIT_DATE", "VISIT_NO", "CLINIC_LABEL", "VISIT_TIME_DESC", "SERIAL_NO", "NAME", "NAME_PHONETIC", "SEX", "AGE", "IDENTITY", "CHARGE_TYPE", "INSURANCE_TYPE", "INSURANCE_NO", "UNIT_IN_CONTRACT", "CLINIC_TYPE", "FIRST_VISIT_INDICATOR", "VISIT_DEPT", "VISIT_SPECIAL_CLINIC", "DOCTOR", "MR_PROVIDE_INDICATOR", "REGISTRATION_STATUS", "REGISTERING_DATE", "SYMPTOM", "REGIST_FEE", "CLINIC_FEE", "OTHER_FEE", "CLINIC_CHARGE", "OPERATOR", "RETURNED_DATE", "RETURNED_OPERATOR", "MODE_CODE", "CARD_NAME", "CARD_NO", "ACCT_DATE_TIME", "ACCT_NO", "PAY_WAY", "MR_PROVIDED_INDICATOR", "INVOICE_NO", "CLINIC_NO", "MR_NO", "ISPRN", "PAT_TYPE", "VALID_DATE", "AUTO_FLAG", "PRINT_OPERATOR", "PE_VISIT_ID", "MAILING_ADDRESS", "CREATE_BY", "CREATE_DATE", "UPDATE_BY", "UPDATE_DATE", "REMARKS", "DEL_FLAG","PATIENT_ID") VALUES ('1', NULL, TO_DATE('20160102000000', 'YYYYMMDDHH24MISS'), 76742, '骨伤科普通号', '昼夜', 2, '曹淑云', 'NAME', '1', 56, '一般人员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '174', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0','15006135');