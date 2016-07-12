

/*==============================================================*/
/* Table: EXAM_APPOINTS      检查预约记录                                */
/*==============================================================*/
create table EXAM_APPOINTS
(
  ID                  VARCHAR2(64 CHAR) not null,
  ORG_ID                  VARCHAR2(64 CHAR),
  CLINIC_ID              VARCHAR2(64),
  EXAM_NO             VARCHAR2(64 CHAR) ,
  PATIENT_ID          VARCHAR2(64 CHAR),
  VISIT_ID            VARCHAR2(64 CHAR),
  LOCAL_ID_CLASS      VARCHAR2(64 CHAR),
  PATIENT_LOCAL_ID    VARCHAR2(64 CHAR),
  NAME                VARCHAR2(20 CHAR),
  NAME_PHONETIC       VARCHAR2(20 CHAR),
  SEX                 VARCHAR2(64 CHAR),
  DATE_OF_BIRTH       TIMESTAMP,
  BIRTH_PLACE         VARCHAR2(64 CHAR),
  IDENTITY            VARCHAR2(64 CHAR),
  CHARGE_TYPE         VARCHAR2(64 CHAR),
  MAILING_ADDRESS     VARCHAR2(100 CHAR),
  ZIP_CODE            VARCHAR2(6 CHAR),
  PHONE_NUMBER        VARCHAR2(16 CHAR),
  EXAM_CLASS          VARCHAR2(64 CHAR),
  EXAM_SUB_CLASS      VARCHAR2(64 CHAR),
  CLIN_SYMP           VARCHAR2(2000 CHAR),
  PHYS_SIGN           VARCHAR2(1000 CHAR),
  RELEVANT_LAB_TEST   VARCHAR2(200 CHAR),
  RELEVANT_DIAG       VARCHAR2(400 CHAR),
  CLIN_DIAG           VARCHAR2(1000 CHAR),
  EXAM_MODE           VARCHAR2(64 CHAR),
  EXAM_GROUP          VARCHAR2(64 CHAR),
  PERFORMED_BY        VARCHAR2(64 CHAR),
  PATIENT_SOURCE      VARCHAR2(64 CHAR),
  FACILITY            VARCHAR2(20 CHAR),
  REQ_DATE_TIME       TIMESTAMP,
  REQ_DEPT            VARCHAR2(64 CHAR),
  REQ_PHYSICIAN       VARCHAR2(20 CHAR),
  REQ_MEMO            VARCHAR2(60 CHAR),
  SCHEDULED_DATE      TIMESTAMP,
  NOTICE              VARCHAR2(400 CHAR),
  COSTS               NUMBER(8,2),
  CHARGES             NUMBER(8,2),
  DOCTOR_USER         VARCHAR2(64 CHAR),
  WORKED_INDICATOR    NUMBER(1) default 0,
  ISREAD              VARCHAR2(1) default 'N',
  EMERGENCY_INDICATOR NUMBER(1),
  BILLING_INDICATOR   NUMBER(1) default 0,
  CNSLT_STATE         NUMBER(2) default 0 not null,
  QUEUE_NO            NUMBER(4),
  DEVICE              VARCHAR2(40 CHAR),
  TIME_INTERVAL       VARCHAR2(10 CHAR),
  EQUIPMENT_NO        VARCHAR2(20 CHAR),
  VISIT_NO            NUMBER(11),
  REGISTER            VARCHAR2(50 CHAR),
  REG_PRN_FLAG        NUMBER(1),
  CNSLT_NO            VARCHAR2(10 CHAR),
  EXAM_REASON         VARCHAR2(200 CHAR),
  PRIORITY_INDICATOR  NUMBER(1),
  SPECIAL_INDICATOR   NUMBER(1),
  SHARE_EXAM_NO       VARCHAR2(10 CHAR),
  PAUSE_INDICATOR     VARCHAR2(10 CHAR),
  DB_USER             VARCHAR2(64 CHAR),
  CNSLT_NAME          VARCHAR2(32 CHAR),
  PRINT_STATUS        NUMBER(1),
  WARD_CODE           VARCHAR2(64 CHAR),
  RCPT_NO             VARCHAR2(20 CHAR),
  IN_OR_OUT           NUMBER(1),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1),
  constraint PK_EXAM_APPOINTS primary key (ID)
);
-- Add comments to the table
comment on table EXAM_APPOINTS
  is '检查预约记录';
-- Add comments to the columns
comment on column EXAM_APPOINTS.ID
  is '检查预约记录ID';
comment on column EXAM_APPOINTS.EXAM_NO
  is '申请序号';
comment on column EXAM_APPOINTS.PATIENT_ID
  is '病人标识号';
comment on column EXAM_APPOINTS.VISIT_ID
  is '住院标识';
comment on column EXAM_APPOINTS.LOCAL_ID_CLASS
  is '检查号类别';
comment on column EXAM_APPOINTS.PATIENT_LOCAL_ID
  is '检查标识号';
comment on column EXAM_APPOINTS.NAME
  is '姓名';
comment on column EXAM_APPOINTS.NAME_PHONETIC
  is '姓名拼音';
comment on column EXAM_APPOINTS.SEX
  is '性别';
comment on column EXAM_APPOINTS.DATE_OF_BIRTH
  is '出生日期';
comment on column EXAM_APPOINTS.BIRTH_PLACE
  is '出生地';
comment on column EXAM_APPOINTS.IDENTITY
  is '身份';
comment on column EXAM_APPOINTS.CHARGE_TYPE
  is '费别';
comment on column EXAM_APPOINTS.MAILING_ADDRESS
  is '通信地址';
comment on column EXAM_APPOINTS.ZIP_CODE
  is '邮政编码';
comment on column EXAM_APPOINTS.PHONE_NUMBER
  is '联系电话';
comment on column EXAM_APPOINTS.EXAM_CLASS
  is '检查类别';
comment on column EXAM_APPOINTS.EXAM_SUB_CLASS
  is '检查子类';
comment on column EXAM_APPOINTS.CLIN_SYMP
  is '临床症状';
comment on column EXAM_APPOINTS.PHYS_SIGN
  is '体征';
comment on column EXAM_APPOINTS.RELEVANT_LAB_TEST
  is '相关化验结果';
comment on column EXAM_APPOINTS.RELEVANT_DIAG
  is '其他诊断';
comment on column EXAM_APPOINTS.CLIN_DIAG
  is '临床诊断';
comment on column EXAM_APPOINTS.EXAM_MODE
  is '检查方式';
comment on column EXAM_APPOINTS.EXAM_GROUP
  is '检查分组';
comment on column EXAM_APPOINTS.PERFORMED_BY
  is '执行科室';
comment on column EXAM_APPOINTS.PATIENT_SOURCE
  is '病人来源';
comment on column EXAM_APPOINTS.FACILITY
  is '外来医疗单位名称';
comment on column EXAM_APPOINTS.REQ_DATE_TIME
  is '申请日期及时间';
comment on column EXAM_APPOINTS.REQ_DEPT
  is '申请科室';
comment on column EXAM_APPOINTS.REQ_PHYSICIAN
  is '申请医生';
comment on column EXAM_APPOINTS.REQ_MEMO
  is '申请备注';
comment on column EXAM_APPOINTS.SCHEDULED_DATE
  is '预约日期及时间';
comment on column EXAM_APPOINTS.NOTICE
  is '注意事项';
comment on column EXAM_APPOINTS.COSTS
  is '费用';
comment on column EXAM_APPOINTS.CHARGES
  is '应收费用';
comment on column EXAM_APPOINTS.DOCTOR_USER
  is '申请医生用户名';
comment on column EXAM_APPOINTS.WARD_CODE
  is '护理单元';
comment on column EXAM_APPOINTS.RCPT_NO
  is '收据号';
  comment on column EXAM_APPOINTS.IN_OR_OUT
  is '门诊住院标示';