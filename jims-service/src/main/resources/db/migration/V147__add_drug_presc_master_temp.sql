--drop table DRUG_PRESC_MASTER_TEMP cascade constraints;

/*==============================================================*/
/* Table: DRUG_PRESC_MASTER_TEMP   门诊待发药处方主表           */
/*CREATE_BY: pq	                                      */
/*==============================================================*/
create table DRUG_PRESC_MASTER_TEMP 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   PRESC_DATE           TIMESTAMP            not null,
   PRESC_NO             NUMBER(5)            not null,
   DISPENSARY           VARCHAR2(8),
   QUEUE_ID             VARCHAR2(2),
   STATUS               NUMBER(1),
   PATIENT_ID           VARCHAR2(64),
   NAME                 VARCHAR2(20),
   NAME_PHONETIC        VARCHAR2(16),
   IDENTITY             VARCHAR2(10),
   CHARGE_TYPE          VARCHAR2(8),
   UNIT_IN_CONTRACT     VARCHAR2(11),
   PRESC_TYPE           VARCHAR2(11),
   PRESC_ATTR           VARCHAR2(8),
   PRESC_SOURCE         NUMBER(1),
   REPETITION           NUMBER(2),
   COSTS                NUMBER(10,4),
   PAYMENTS             NUMBER(10,4),
   ORDERED_BY           VARCHAR2(8),
   PRESCRIBED_BY        VARCHAR2(64),
   ENTERED_BY           VARCHAR2(20),
   CHARGE_INDICATOR     NUMBER(1),
   RCPT_NO              VARCHAR2(20),
   SEX                  VARCHAR2(4),
   AGE                  NUMBER(3),
   COUNT_PER_REPETITION NUMBER(2),
   ENTERED_DATETIME     TIMESTAMP,
   DISPENSING_DATETIME  TIMESTAMP,
   MEMO                 VARCHAR2(100),
   DISPENSING_PROVIDER  VARCHAR2(64),
   DISCG_TAKING_INDICATOR NUMBER(1),
   DOCTOR_USER          VARCHAR2(64),
   DECOCTION            NUMBER(1),
   DOSAGE_EACH          NUMBER(10,4),
   VERIFY_BY            VARCHAR2(64),
   VERIFIED_DATETIME    TIMESTAMP,
   DISPENSATION_BY      VARCHAR2(8),
   DISPENSATION_DATE    TIMESTAMP,
   PRINT_DATE_TIME      TIMESTAMP,
   LIST_NO              NUMBER(1),
   DISPENSARY_SUB       VARCHAR2(8),
   FLAG                 NUMBER(1),
   CLINIC_NO            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 char),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_DRUG_PRESC_MASTER_TEMP primary key (ID)    
);

comment on table DRUG_PRESC_MASTER_TEMP is
'注释：此表用于记录已开单或已收费但尚未发药的处方，由门诊收费或将来的门诊医生工作站录入，发药后，由门诊药局将对应的处方删除。';

comment on column DRUG_PRESC_MASTER_TEMP.ID is
'病人标识号';

comment on column DRUG_PRESC_MASTER_TEMP.ORG_ID is
'医院ID';

comment on column DRUG_PRESC_MASTER_TEMP.PRESC_DATE is
'处方日期';

comment on column DRUG_PRESC_MASTER_TEMP.PRESC_NO is
'处方号';

comment on column DRUG_PRESC_MASTER_TEMP.DISPENSARY is
'发药药局';

comment on column DRUG_PRESC_MASTER_TEMP.QUEUE_ID is
'发药队列号';

comment on column DRUG_PRESC_MASTER_TEMP.STATUS is
'处理状态';

comment on column DRUG_PRESC_MASTER_TEMP.PATIENT_ID is
'病人标识号';

comment on column DRUG_PRESC_MASTER_TEMP.NAME is
'姓名';

comment on column DRUG_PRESC_MASTER_TEMP.NAME_PHONETIC is
'姓名拼音';

comment on column DRUG_PRESC_MASTER_TEMP.IDENTITY is
'身份';

comment on column DRUG_PRESC_MASTER_TEMP.CHARGE_TYPE is
'费别';

comment on column DRUG_PRESC_MASTER_TEMP.UNIT_IN_CONTRACT is
'病人合同单位';

comment on column DRUG_PRESC_MASTER_TEMP.PRESC_TYPE is
'处方类别';

comment on column DRUG_PRESC_MASTER_TEMP.PRESC_ATTR is
'厨房属性';

comment on column DRUG_PRESC_MASTER_TEMP.PRESC_SOURCE is
'处方来源';

comment on column DRUG_PRESC_MASTER_TEMP.REPETITION is
'剂数';

comment on column DRUG_PRESC_MASTER_TEMP.COSTS is
'费用';

comment on column DRUG_PRESC_MASTER_TEMP.PAYMENTS is
'实付费用';

comment on column DRUG_PRESC_MASTER_TEMP.ORDERED_BY is
'开单科室';

comment on column DRUG_PRESC_MASTER_TEMP.PRESCRIBED_BY is
'开方医生';

comment on column DRUG_PRESC_MASTER_TEMP.ENTERED_BY is
'录方人';

comment on column DRUG_PRESC_MASTER_TEMP.CHARGE_INDICATOR is
'费用类别';

comment on column DRUG_PRESC_MASTER_TEMP.RCPT_NO is
'收据号';

comment on column DRUG_PRESC_MASTER_TEMP.SEX is
'性别';

comment on column DRUG_PRESC_MASTER_TEMP.AGE is
'年龄';

comment on column DRUG_PRESC_MASTER_TEMP.COUNT_PER_REPETITION is
'每剂煎几副';

comment on column DRUG_PRESC_MASTER_TEMP.ENTERED_DATETIME is
'录入日期';

comment on column DRUG_PRESC_MASTER_TEMP.DISPENSING_DATETIME is
'发药日期';

comment on column DRUG_PRESC_MASTER_TEMP.MEMO is
'备注';

comment on column DRUG_PRESC_MASTER_TEMP.DISPENSING_PROVIDER is
'调剂者';

comment on column DRUG_PRESC_MASTER_TEMP.DOCTOR_USER is
'医生';

comment on column DRUG_PRESC_MASTER_TEMP.DISPENSARY_SUB is
'发药子药局';

comment on column DRUG_PRESC_MASTER_TEMP.FLAG is
'退药标志';

comment on column DRUG_PRESC_MASTER_TEMP.CREATE_DATE is
'创建日期';

comment on column DRUG_PRESC_MASTER_TEMP.CREATE_BY is
'创建人';

comment on column DRUG_PRESC_MASTER_TEMP.UPDATE_BY is
'更新人';

comment on column DRUG_PRESC_MASTER_TEMP.UPDATE_DATE is
'更新日期';

comment on column DRUG_PRESC_MASTER_TEMP.REMARKS is
'备注信息';

comment on column DRUG_PRESC_MASTER_TEMP.DEL_FLAG is
'删除标志';
