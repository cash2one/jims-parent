-- drop table LAB_TEST_MASTER cascade constraints;

/*==============================================================*/
/* Table: LAB_TEST_MASTER     检验主记录                                 */
/*==============================================================*/
-- Create table
create table LAB_TEST_MASTER
(
  ID                      VARCHAR2(64 CHAR) not null,
  TEST_NO                 VARCHAR2(20 CHAR) not null,
  PRIORITY_INDICATOR      NUMBER(1),
  PATIENT_ID              VARCHAR2(10 CHAR),
  VISIT_ID                NUMBER(2),
  WORKING_ID              VARCHAR2(6 CHAR),
  EXECUTE_DATE            TIMESTAMP,
  NAME                    VARCHAR2(20 CHAR),
  NAME_PHONETIC           VARCHAR2(16 CHAR),
  CHARGE_TYPE             VARCHAR2(8 CHAR),
  SEX                     VARCHAR2(4 CHAR),
  AGE                     NUMBER(3),
  TEST_CAUSE              VARCHAR2(8 CHAR),
  RELEVANT_CLINIC_DIAG    VARCHAR2(80 CHAR),
  SPECIMEN                VARCHAR2(8 CHAR),
  NOTES_FOR_SPCM          VARCHAR2(100 CHAR),
  SPCM_RECEIVED_DATE_TIME TIMESTAMP,
  SPCM_SAMPLE_DATE_TIME   TIMESTAMP,
  REQUESTED_DATE_TIME     TIMESTAMP,
  ORDERING_DEPT           VARCHAR2(8 CHAR),
  ORDERING_PROVIDER       VARCHAR2(20 CHAR),
  PERFORMED_BY            VARCHAR2(8 CHAR),
  RESULT_STATUS           VARCHAR2(1 CHAR),
  RESULTS_RPT_DATE_TIME   TIMESTAMP,
  TRANSCRIPTIONIST        VARCHAR2(20 CHAR),
  VERIFIED_BY             VARCHAR2(8 CHAR),
  COSTS                   NUMBER(8,2),
  CHARGES                 NUMBER(8,2),
  BILLING_INDICATOR       NUMBER(1),
  PRINT_INDICATOR         NUMBER(1),
  SUBJECT                 VARCHAR2(40 CHAR),
  CONTAINER_CARRIER       VARCHAR2(20 CHAR),
  STATUS                  VARCHAR2(160 CHAR),
  MEMO                    VARCHAR2(160 CHAR),
  WARD_CODE               VARCHAR2(20 CHAR),
  PHYEXAM_FLAG            VARCHAR2(20 CHAR),
  NAYAOTISHI              VARCHAR2(100 CHAR),
  JIANYI                  VARCHAR2(500 CHAR),
  WSW                     VARCHAR2(1 CHAR),
  SAMPLEID                VARCHAR2(40 CHAR),
  DEL_FLAG                NUMBER(1),
  REMARKS VARCHAR2(2000  CHAR),
  UPDATE_BY VARCHAR2(64  CHAR),
  CREATE_BY VARCHAR2(64  CHAR),
  UPDATE_DATE TIMESTAMP(6),
  CREATE_DATE TIMESTAMP(6),
   constraint "PK_LAB_TEST_MASTER" primary key (ID)
);

-- Add comments to the table
comment on table LAB_TEST_MASTER
  is '检验主记录';
-- Add comments to the columns
comment on column LAB_TEST_MASTER.TEST_NO
  is '申请序号';
comment on column LAB_TEST_MASTER.PRIORITY_INDICATOR
  is '优先标志';
comment on column LAB_TEST_MASTER.PATIENT_ID
  is '病人标识号';
comment on column LAB_TEST_MASTER.VISIT_ID
  is '本次住院标识';
comment on column LAB_TEST_MASTER.WORKING_ID
  is '工作单号';
comment on column LAB_TEST_MASTER.EXECUTE_DATE
  is '执行日期';
comment on column LAB_TEST_MASTER.NAME
  is '姓名';
comment on column LAB_TEST_MASTER.NAME_PHONETIC
  is '姓名拼音';
comment on column LAB_TEST_MASTER.CHARGE_TYPE
  is '费别';
comment on column LAB_TEST_MASTER.SEX
  is '性别';
comment on column LAB_TEST_MASTER.AGE
  is '年龄';
comment on column LAB_TEST_MASTER.TEST_CAUSE
  is '检验目的';
comment on column LAB_TEST_MASTER.RELEVANT_CLINIC_DIAG
  is '临床诊断';
comment on column LAB_TEST_MASTER.SPECIMEN
  is '标本';
comment on column LAB_TEST_MASTER.NOTES_FOR_SPCM
  is '标本说明';
comment on column LAB_TEST_MASTER.SPCM_RECEIVED_DATE_TIME
  is '标本采样日期及时间';
comment on column LAB_TEST_MASTER.SPCM_SAMPLE_DATE_TIME
  is '标本收到日期及时间';
comment on column LAB_TEST_MASTER.REQUESTED_DATE_TIME
  is '申请日期及时间';
comment on column LAB_TEST_MASTER.ORDERING_DEPT
  is '申请科室';
comment on column LAB_TEST_MASTER.ORDERING_PROVIDER
  is '申请医生';
comment on column LAB_TEST_MASTER.PERFORMED_BY
  is '执行科室';
comment on column LAB_TEST_MASTER.RESULT_STATUS
  is '结果状态';
comment on column LAB_TEST_MASTER.RESULTS_RPT_DATE_TIME
  is '报告日期及时间';
comment on column LAB_TEST_MASTER.TRANSCRIPTIONIST
  is '报告者';
comment on column LAB_TEST_MASTER.VERIFIED_BY
  is '校对者';
comment on column LAB_TEST_MASTER.COSTS
  is '费用';
comment on column LAB_TEST_MASTER.CHARGES
  is '应收费用';
comment on column LAB_TEST_MASTER.BILLING_INDICATOR
  is '计价标志';
comment on column LAB_TEST_MASTER.PRINT_INDICATOR
  is '打印标志';
comment on column LAB_TEST_MASTER.WARD_CODE
  is '护理单元';
comment on column LAB_TEST_MASTER.NAYAOTISHI
  is '耐药细菌提示';
comment on column LAB_TEST_MASTER.JIANYI
  is 'LIS建议';
comment on column LAB_TEST_MASTER.SAMPLEID
  is 'LIS接口主键';
