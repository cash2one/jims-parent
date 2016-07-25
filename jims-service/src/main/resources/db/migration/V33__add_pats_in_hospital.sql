-- drop table PATS_IN_HOSPITAL cascade constraints;

/*==============================================================*/
/* Table: PATS_IN_HOSPITAL      在院病人记录                                */
/*==============================================================*/
create table PATS_IN_HOSPITAL
(
  ID                     VARCHAR2(64 CHAR) not null,
  ORG_ID                  VARCHAR2(64 CHAR),
  PATIENT_ID VARCHAR2(64),
  VISIT_ID               VARCHAR2(64),
  WARD_CODE              VARCHAR2(64 CHAR),
  DEPT_CODE              VARCHAR2(64 CHAR),
  BED_NO                 NUMBER(8),
  ADMISSION_DATE_TIME    TIMESTAMP,
  ADM_WARD_DATE_TIME     TIMESTAMP,
  DIAGNOSIS              VARCHAR2(100 CHAR),
  PATIENT_CONDITION      VARCHAR2(64 CHAR),
  NURSING_CLASS          VARCHAR2(64 CHAR),
  DOCTOR_IN_CHARGE       VARCHAR2(20 CHAR),
  OPERATING_DATE         TIMESTAMP,
  BILLING_DATE_TIME      TIMESTAMP,
  PREPAYMENTS            NUMBER(10,2),
  TOTAL_COSTS            NUMBER(12,4),
  TOTAL_CHARGES          NUMBER(12,4),
  GUARANTOR              VARCHAR2(8 CHAR),
  GUARANTOR_ORG          VARCHAR2(40 CHAR),
  GUARANTOR_PHONE_NUM    VARCHAR2(16 CHAR),
  BILL_CHECKED_DATE_TIME TIMESTAMP,
  SETTLED_INDICATOR      NUMBER(1),
  LEND_BED_NO            NUMBER(3),
  BED_DEPT_CODE          VARCHAR2(10 CHAR),
  BED_WARD_CODE          VARCHAR2(10 CHAR),
  DEPT_CODE_LEND         VARCHAR2(10 CHAR),
  LEND_INDICATOR         NUMBER(1),
  IS_NEWBORN             NUMBER(1),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1),
  constraint PK_PATS_IN_HOSPITAL primary key (ID)
);
-- Add comments to the table 
comment on table PATS_IN_HOSPITAL
  is '在院病人记录';
-- Add comments to the columns 
comment on column PATS_IN_HOSPITAL.ID
  is '病人标识号';
comment on column PATS_IN_HOSPITAL.ORG_ID
  is '医院ID';
comment on column PATS_IN_HOSPITAL.VISIT_ID
  is '病人本次住院标识';
comment on column PATS_IN_HOSPITAL.WARD_CODE
  is '所在病房代码';
comment on column PATS_IN_HOSPITAL.DEPT_CODE
  is '所在科室代码';
comment on column PATS_IN_HOSPITAL.BED_NO
  is '床号';
comment on column PATS_IN_HOSPITAL.ADMISSION_DATE_TIME
  is '入院日期及时间';
comment on column PATS_IN_HOSPITAL.ADM_WARD_DATE_TIME
  is '入科日期及时间';
comment on column PATS_IN_HOSPITAL.DIAGNOSIS
  is '主要诊断';
comment on column PATS_IN_HOSPITAL.PATIENT_CONDITION
  is '病情状态';
comment on column PATS_IN_HOSPITAL.NURSING_CLASS
  is '护理等级';
comment on column PATS_IN_HOSPITAL.DOCTOR_IN_CHARGE
  is '经治医生';
comment on column PATS_IN_HOSPITAL.OPERATING_DATE
  is '手术日期';
comment on column PATS_IN_HOSPITAL.BILLING_DATE_TIME
  is '计价截止日期及时间';
comment on column PATS_IN_HOSPITAL.PREPAYMENTS
  is '预交金余额';
comment on column PATS_IN_HOSPITAL.TOTAL_COSTS
  is '累计未结费用';
comment on column PATS_IN_HOSPITAL.TOTAL_CHARGES
  is '优惠后未结费用';
comment on column PATS_IN_HOSPITAL.GUARANTOR
  is '经济担保人';
comment on column PATS_IN_HOSPITAL.GUARANTOR_ORG
  is '经济担保人所在单位';
comment on column PATS_IN_HOSPITAL.GUARANTOR_PHONE_NUM
  is '经济担保人联系电话';
comment on column PATS_IN_HOSPITAL.BILL_CHECKED_DATE_TIME
  is '上次划价检查日期';
comment on column PATS_IN_HOSPITAL.SETTLED_INDICATOR
  is '出院结算标记';
  INSERT INTO "PATS_IN_HOSPITAL" ("ID", "ORG_ID", "VISIT_ID", "WARD_CODE", "DEPT_CODE", "BED_NO", "ADMISSION_DATE_TIME", "ADM_WARD_DATE_TIME", "DIAGNOSIS", "PATIENT_CONDITION", "NURSING_CLASS", "DOCTOR_IN_CHARGE", "OPERATING_DATE", "BILLING_DATE_TIME", "PREPAYMENTS", "TOTAL_COSTS", "TOTAL_CHARGES", "GUARANTOR", "GUARANTOR_ORG", "GUARANTOR_PHONE_NUM", "BILL_CHECKED_DATE_TIME", "SETTLED_INDICATOR", "LEND_BED_NO", "BED_DEPT_CODE", "BED_WARD_CODE", "DEPT_CODE_LEND", "LEND_INDICATOR", "IS_NEWBORN", "PATIENT_ID","DEL_FLAG") VALUES ('1', NULL, 1, '161101', '140101', 38, TO_TIMESTAMP(' 2010-10-01 1:5:2:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), TO_TIMESTAMP(' 2010-10-01:0:3:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '精神分裂症', '3', '2', '000TYW', NULL, TO_TIMESTAMP(' 2016-01-02 2:3:0:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), 5000, 8135.1420, 8135.1420, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, 1, NULL,'15006135','0');