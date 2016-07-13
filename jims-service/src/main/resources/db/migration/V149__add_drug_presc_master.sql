--drop table DRUG_PRESC_MASTER cascade constraints;

/*==============================================================*/
/* Table: DRUG_PRESC_MASTER   药品处方主记录           */
/*CREATE_BY: pq	                                      */
/*==============================================================*/
create table DRUG_PRESC_MASTER
(
  ID                         VARCHAR2(64)  not null,
  CLINIC_Id                  VARCHAR2(64),
  VISIT_ID                   VARCHAR2(64),
  PRESC_DATE                 TIMESTAMP not null,
  PRESC_NO                   NUMBER(5) not null,
  ORG_ID                     VARCHAR2(64),
  DISPENSARY                 VARCHAR2(8),
  PATIENT_ID                 VARCHAR2(64),
  NAME                       VARCHAR2(20),
  NAME_PHONETIC              VARCHAR2(16),
  IDENTITY                   VARCHAR2(10),
  CHARGE_TYPE                VARCHAR2(8),
  UNIT_IN_CONTRACT           VARCHAR2(11),
  PRESC_TYPE                 NUMBER(1),
  PRESC_ATTR                 VARCHAR2(8),
  PRESC_SOURCE               NUMBER(1),
  REPETITION                 NUMBER(2),
  COSTS                      NUMBER(10,4),
  ORDERED_BY                 VARCHAR2(8),
  PRESCRIBED_BY              VARCHAR2(64),
  ENTERED_BY                 VARCHAR2(64),
  DISPENSING_PROVIDER        VARCHAR2(64),
  COUNT_PER_REPETITION       NUMBER(2),
  ENTERED_DATETIME           TIMESTAMP,
  DISPENSING_DATETIME        TIMESTAMP,
  MEMO                       VARCHAR2(100),
  SUB_STORAGE                VARCHAR2(8),
  FLAG                       NUMBER(1),
  DOCTOR_USER                VARCHAR2(16),
  ENTERED_DATATIME           TIMESTAMP default sysdate,
  VERIFY_BY                  VARCHAR2(64),
  DISCHARGE_TAKING_INDICATOR NUMBER(1),
  PAYMENTS                   NUMBER(10,4),
  DECOCTION                  NUMBER(1) default 0,
  VERIFIED_DATETIME          TIMESTAMP,
  RCPT_NO                    VARCHAR2(64),
  ORIGINAL_PRESC_DATE        TIMESTAMP,
  ORIGINAL_PRESC_NO          NUMBER(5),
  RETURN_VISIT_NO            NUMBER(5),
  RETURN_VISIT_DATE          TIMESTAMP,
  BATCH_PROVIDE_NO           VARCHAR2(64),
  DISPENSATION_BY            VARCHAR2(64),
  DISPENSATION_DATE          TIMESTAMP,
  CFBH_MZ                    VARCHAR2(64),
  FHR_MZ                     VARCHAR2(64),
  FHBZ_MZ                    VARCHAR2(1),
  BZ_MZ                      VARCHAR2(20),
  HSPH                       VARCHAR2(64),
  FFPH                       VARCHAR2(64),
  DISPENSARY_SUB             VARCHAR2(8),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 char),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG            char(1) default '0',
  constraint PK_DRUG_PRESC_MASTER primary key (ID)
);

-- Add comments to the table 
comment on table DRUG_PRESC_MASTER
  is '药品处方主记录';
-- Add comments to the columns
comment on column DRUG_PRESC_MASTER.ID
  is '主键';
comment on column DRUG_PRESC_MASTER.PRESC_DATE
  is '处方日期';
comment on column DRUG_PRESC_MASTER.PRESC_NO
  is '处方号';
comment on column DRUG_PRESC_MASTER.ORG_ID
  is '医院ID';
comment on column DRUG_PRESC_MASTER.DISPENSARY
  is '发药药局';
comment on column DRUG_PRESC_MASTER.PATIENT_ID
  is '病人标识号';
comment on column DRUG_PRESC_MASTER.NAME
  is '姓名';
comment on column DRUG_PRESC_MASTER.NAME_PHONETIC
  is '姓名拼音';
comment on column DRUG_PRESC_MASTER.IDENTITY
  is '身份';
comment on column DRUG_PRESC_MASTER.CHARGE_TYPE
  is '费别';
comment on column DRUG_PRESC_MASTER.UNIT_IN_CONTRACT
  is '病人合同单位';
comment on column DRUG_PRESC_MASTER.PRESC_TYPE
  is '处方类别';
comment on column DRUG_PRESC_MASTER.PRESC_ATTR
  is '处方属性';
comment on column DRUG_PRESC_MASTER.PRESC_SOURCE
  is '处方来源';
comment on column DRUG_PRESC_MASTER.REPETITION
  is '剂数';
comment on column DRUG_PRESC_MASTER.COSTS
  is '费用';
comment on column DRUG_PRESC_MASTER.ORDERED_BY
  is '开单科室';
comment on column DRUG_PRESC_MASTER.PRESCRIBED_BY
  is '开方医生';
comment on column DRUG_PRESC_MASTER.ENTERED_BY
  is '录方人';
comment on column DRUG_PRESC_MASTER.DISPENSING_PROVIDER
  is '发药人';
comment on column DRUG_PRESC_MASTER.COUNT_PER_REPETITION
  is '每剂煎几份';
comment on column DRUG_PRESC_MASTER.ENTERED_DATETIME
  is '录入日期';
comment on column DRUG_PRESC_MASTER.DISPENSING_DATETIME
  is '发药日期';
comment on column DRUG_PRESC_MASTER.MEMO
  is '备注';
comment on column DRUG_PRESC_MASTER.SUB_STORAGE
  is '子库房';
comment on column DRUG_PRESC_MASTER.FLAG
  is '退药标志';
comment on column DRUG_PRESC_MASTER.DOCTOR_USER
  is '医生';
comment on column DRUG_PRESC_MASTER.ENTERED_DATATIME
  is '录入日期';
comment on column DRUG_PRESC_MASTER.VERIFY_BY
  is '审核';
comment on column DRUG_PRESC_MASTER.DISCHARGE_TAKING_INDICATOR
  is '出院带药标志';
comment on column DRUG_PRESC_MASTER.PAYMENTS
  is '实付费用';
comment on column DRUG_PRESC_MASTER.RETURN_VISIT_NO
  is '退费处方号';
comment on column DRUG_PRESC_MASTER.RETURN_VISIT_DATE
  is '退费处方日期';
comment on column DRUG_PRESC_MASTER.BATCH_PROVIDE_NO
  is '批量发药号';
comment on column DRUG_PRESC_MASTER.CFBH_MZ
  is '毒麻处方编号';
comment on column DRUG_PRESC_MASTER.FHR_MZ
  is '毒麻处方复核人';
comment on column DRUG_PRESC_MASTER.FHBZ_MZ
  is '毒麻处方复核标志';
comment on column DRUG_PRESC_MASTER.BZ_MZ
  is '毒麻处方备注';
comment on column DRUG_PRESC_MASTER.HSPH
  is '毒麻处方回收批号';
comment on column DRUG_PRESC_MASTER.FFPH
  is '毒麻处方发放批号';
comment on column DRUG_PRESC_MASTER.DISPENSARY_SUB
  is '发药子药局';
  comment on column DRUG_PRESC_MASTER.CREATE_DATE is
'创建日期';

comment on column DRUG_PRESC_MASTER.CREATE_BY is
'创建人';

comment on column DRUG_PRESC_MASTER.UPDATE_BY is
'更新人';

comment on column DRUG_PRESC_MASTER.UPDATE_DATE is
'更新日期';

comment on column DRUG_PRESC_MASTER.REMARKS is
'备注信息';

comment on column DRUG_PRESC_MASTER.DEL_FLAG is
'删除标志';
