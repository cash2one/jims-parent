/*==============================================================*/
/* Table: EXAM_MASTER   检查主记录 */
/* CREATE_DATE: 2016-07-05                         */
/* CREATE_BY: zhaoning                                                */
/*==============================================================*/
create table EXAM_MASTER
(
  ID                  VARCHAR2(64),
  EXAM_NO             VARCHAR2(10),
  LOCAL_ID_CLASS      VARCHAR2(64),
  PATIENT_LOCAL_ID    VARCHAR2(64),
  PATIENT_ID          VARCHAR2(64),
  VISIT_ID            NUMBER(2),
  NAME                VARCHAR2(20),
  SEX                 VARCHAR2(64),
  DATE_OF_BIRTH       TIMESTAMP,
  EXAM_CLASS          VARCHAR2(64),
  EXAM_SUB_CLASS      VARCHAR2(64),
  SPM_RECVED_DATE     TIMESTAMP,
  CLIN_SYMP           VARCHAR2(400),
  PHYS_SIGN           VARCHAR2(400),
  RELEVANT_LAB_TEST   VARCHAR2(200),
  RELEVANT_DIAG       VARCHAR2(400),
  CLIN_DIAG           VARCHAR2(100),
  EXAM_MODE           VARCHAR2(1),
  EXAM_GROUP          VARCHAR2(16),
  DEVICE              VARCHAR2(100),
  PERFORMED_BY        VARCHAR2(64),
  PATIENT_SOURCE      VARCHAR2(1),
  FACILITY            VARCHAR2(20),
  REQ_DATE_TIME       TIMESTAMP,
  REQ_DEPT            VARCHAR2(64),
  REQ_PHYSICIAN       VARCHAR2(20),
  REQ_MEMO            VARCHAR2(60),
  SCHEDULED_DATE_TIME TIMESTAMP,
  NOTICE              VARCHAR2(400),
  EXAM_DATE_TIME      TIMESTAMP,
  REPORT_DATE_TIME    TIMESTAMP,
  TECHNICIAN          VARCHAR2(100),
  REPORTER            VARCHAR2(100),
  RESULT_STATUS       VARCHAR2(1),
  COSTS               NUMBER(8,2),
  CHARGES             NUMBER(8,2),
  CHARGE_INDICATOR    NUMBER(1),
  CHARGE_TYPE         VARCHAR2(64),
  IDENTITY            VARCHAR2(64),
  RPTSTATUS           VARCHAR2(100),
  PRINT_STATUS        VARCHAR2(50),
  EXAM_SUBDEPT        VARCHAR2(64),
  CONFIRM_DOCT        VARCHAR2(8),
  STUDY_UID           VARCHAR2(128),
  PHOTO_STATUS        VARCHAR2(50),
  CONFIRM_DATE_TIME   DATE,
  HP_VALUE            VARCHAR2(10),
  DIAG_HOSTNAME       VARCHAR2(30),
  REPORT_LOCK_STATUS  VARCHAR2(10),
  QUEUE_NO            NUMBER(4),
  EQUIPMENT_NO        VARCHAR2(8),
  TIME_INTERVAL       VARCHAR2(10),
  VISIT_NO            NUMBER(4),
  REGISTER            VARCHAR2(16),
  REN_INDICATOR       NUMBER(1),
  REN_EXAM_NO         VARCHAR2(10),
  CNSLT_NO            VARCHAR2(8),
  EXAM_REASON         VARCHAR2(200),
  PRIORITY_INDICATOR  NUMBER(1),
  SPECIAL_INDICATOR   NUMBER(1),
  SHARE_EXAM_NO       VARCHAR2(10),
  PAUSE_INDICATOR     VARCHAR2(10),
  BIRTH_PLACE         VARCHAR2(6),
  PHONE_NUMBER        VARCHAR2(16),
  ZIP_CODE            VARCHAR2(6),
  MAILING_ADDRESS     VARCHAR2(40),
  NAME_PHONETIC       VARCHAR2(50),
  DOCTOR_USER         VARCHAR2(64),
  REP_INDICATOR       VARCHAR2(4),
  PACS_FLAG           VARCHAR2(2),
  PACS_PATHS          VARCHAR2(2000),
  RCPT_NO             VARCHAR2(20),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP
);
-- Add comments to the table
comment on table EXAM_MASTER
  is '检查主记录 ';
-- Add comments to the columns
comment on column EXAM_MASTER.ID
  is '检查主记录ID';
comment on column EXAM_MASTER.EXAM_NO
  is '申请序号';
comment on column EXAM_MASTER.LOCAL_ID_CLASS
  is '检查号类别';
comment on column EXAM_MASTER.PATIENT_LOCAL_ID
  is '检查标识号';
comment on column EXAM_MASTER.PATIENT_ID
  is '病人标识号';
comment on column EXAM_MASTER.VISIT_ID
  is '病人本次住院标识';
comment on column EXAM_MASTER.NAME
  is '姓名';
comment on column EXAM_MASTER.SEX
  is '性别';
comment on column EXAM_MASTER.DATE_OF_BIRTH
  is '出生日期';
comment on column EXAM_MASTER.EXAM_CLASS
  is '检查类别';
comment on column EXAM_MASTER.EXAM_SUB_CLASS
  is '检查子类';
comment on column EXAM_MASTER.SPM_RECVED_DATE
  is '标本收到日期及时间';
comment on column EXAM_MASTER.CLIN_SYMP
  is '临床症状';
comment on column EXAM_MASTER.PHYS_SIGN
  is '体征';
comment on column EXAM_MASTER.RELEVANT_LAB_TEST
  is '相关化验结果';
comment on column EXAM_MASTER.RELEVANT_DIAG
  is '其他诊断';
comment on column EXAM_MASTER.CLIN_DIAG
  is '临床诊断';
comment on column EXAM_MASTER.EXAM_MODE
  is '检查方式';
comment on column EXAM_MASTER.EXAM_GROUP
  is '检查分组';
comment on column EXAM_MASTER.DEVICE
  is '使用仪器';
comment on column EXAM_MASTER.PERFORMED_BY
  is '执行科室';
comment on column EXAM_MASTER.PATIENT_SOURCE
  is '病人来源';
comment on column EXAM_MASTER.FACILITY
  is '外来医疗单位名称';
comment on column EXAM_MASTER.REQ_DATE_TIME
  is '申请日期及时间';
comment on column EXAM_MASTER.REQ_DEPT
  is '申请科室';
comment on column EXAM_MASTER.REQ_PHYSICIAN
  is '申请医生';
comment on column EXAM_MASTER.REQ_MEMO
  is '申请备注';
comment on column EXAM_MASTER.SCHEDULED_DATE_TIME
  is '预约日期及时间';
comment on column EXAM_MASTER.NOTICE
  is '注意事项';
comment on column EXAM_MASTER.EXAM_DATE_TIME
  is '检查日期及时间';
comment on column EXAM_MASTER.REPORT_DATE_TIME
  is '报告日期及时间';
comment on column EXAM_MASTER.TECHNICIAN
  is '操作者';
comment on column EXAM_MASTER.REPORTER
  is '报告者';
comment on column EXAM_MASTER.RESULT_STATUS
  is '检查结果状态';
comment on column EXAM_MASTER.COSTS
  is '费用';
comment on column EXAM_MASTER.CHARGES
  is '应收费用';
comment on column EXAM_MASTER.CHARGE_INDICATOR
  is '计价标志';
comment on column EXAM_MASTER.CHARGE_TYPE
  is '病人费用类别';
comment on column EXAM_MASTER.IDENTITY
  is '身份';
comment on column EXAM_MASTER.NAME_PHONETIC
  is '审核者';
comment on column EXAM_MASTER.DOCTOR_USER
  is '操作技师';
comment on column EXAM_MASTER.PACS_FLAG
  is '图像来源';
comment on column EXAM_MASTER.RCPT_NO
  is '收据号';