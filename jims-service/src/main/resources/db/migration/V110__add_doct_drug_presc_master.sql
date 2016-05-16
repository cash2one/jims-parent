
--drop table DOCT_DRUG_PRESC_MASTER cascade constraints;

/*==============================================================*/
/* Table: DOCT_DRUG_PRESC_MASTER    待发药住院处方主记录        */
/* CREATE_DATE: 2016-05-16 11:52:40                             */
/* CREATE_BY: CTQ						                                    */
/*==============================================================*/
create table DOCT_DRUG_PRESC_MASTER 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   PRESC_DATE           TIMESTAMP            not null,
   PRESC_NO             NUMBER(5)            not null,
   DISPENSARY           VARCHAR2(8),
   NAME                 VARCHAR2(20),
   NAME_PHONETIC        VARCHAR2(16),
   IDENTITY             VARCHAR2(10),
   CHARGE_TYPE          VARCHAR2(8),
   UNIT_IN_CONTRACT     VARCHAR2(11),
   PRESC_TYPE           NUMBER(1),
   PRESC_ATTR           VARCHAR2(8),
   PRESC_SOURCE         NUMBER(1),
   DISCHARGE_TAKING_INDICATOR NUMBER(1),
   BINDING_PRESC_TITLE  VARCHAR2(40),
   REPETITION           NUMBER(2),
   COUNT_PER_REPETITION NUMBER(2),
   COSTS                NUMBER(10,4),
   PAYMENTS             NUMBER(10,4),
   ORDERED_BY           VARCHAR2(8),
   PRESCRIBED_BY        VARCHAR2(20),
   ENTERED_BY           VARCHAR2(20),
   PRESC_STATUS         NUMBER(1),
   DISPENSING_PROVIDER  VARCHAR2(20),
   USAGE                VARCHAR2(50),
   DECOCTION            NUMBER(1),
   DOCTOR_USER          VARCHAR2(20),
   NEWLY_PRINT          VARCHAR2(1),
   VERIFY_BY            VARCHAR2(20),
   VERIFIED_DATETIME    TIMESTAMP,
   DIAGNOSIS_NAME       VARCHAR2(100),
   DISPENSARY_SUB       VARCHAR2(8),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATA          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATA          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             char(1) default '0',
   constraint PK_DOCT_DRUG_PRESC_MASTER primary key (ID)
   );

comment on table DOCT_DRUG_PRESC_MASTER is
'待发药住院处方主记录';

comment on column DOCT_DRUG_PRESC_MASTER.ID is
'主键';

comment on column DOCT_DRUG_PRESC_MASTER.ORG_ID is
'机构ID';

comment on column DOCT_DRUG_PRESC_MASTER.PATIENT_ID is
'病人ID';

comment on column DOCT_DRUG_PRESC_MASTER.VISIT_ID is
'住院ID';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_DATE is
'处方日期';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_NO is
'处方号';

comment on column DOCT_DRUG_PRESC_MASTER.DISPENSARY is
'发药药局';

comment on column DOCT_DRUG_PRESC_MASTER.NAME is
'姓名';

comment on column DOCT_DRUG_PRESC_MASTER.NAME_PHONETIC is
'姓名拼音码';

comment on column DOCT_DRUG_PRESC_MASTER.IDENTITY is
'身份';

comment on column DOCT_DRUG_PRESC_MASTER.CHARGE_TYPE is
'费别';

comment on column DOCT_DRUG_PRESC_MASTER.UNIT_IN_CONTRACT is
'合同单位';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_TYPE is
'处方类型';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_ATTR is
'处方属性';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_SOURCE is
'处方来源';

comment on column DOCT_DRUG_PRESC_MASTER.DISCHARGE_TAKING_INDICATOR is
'出院带药标志';

comment on column DOCT_DRUG_PRESC_MASTER.BINDING_PRESC_TITLE is
'处方名';

comment on column DOCT_DRUG_PRESC_MASTER.REPETITION is
'剂数';

comment on column DOCT_DRUG_PRESC_MASTER.COUNT_PER_REPETITION is
'每剂份数';

comment on column DOCT_DRUG_PRESC_MASTER.COSTS is
'计价金额';

comment on column DOCT_DRUG_PRESC_MASTER.PAYMENTS is
'应收金额';

comment on column DOCT_DRUG_PRESC_MASTER.ORDERED_BY is
'开单科室';

comment on column DOCT_DRUG_PRESC_MASTER.PRESCRIBED_BY is
'执行人代码';

comment on column DOCT_DRUG_PRESC_MASTER.ENTERED_BY is
'确认人代码';

comment on column DOCT_DRUG_PRESC_MASTER.PRESC_STATUS is
'处方状态';

comment on column DOCT_DRUG_PRESC_MASTER.DISPENSING_PROVIDER is
'确认人名称';

comment on column DOCT_DRUG_PRESC_MASTER.USAGE is
'说明';

comment on column DOCT_DRUG_PRESC_MASTER.DECOCTION is
'是否代煎';

comment on column DOCT_DRUG_PRESC_MASTER.DOCTOR_USER is
'医生代码';

comment on column DOCT_DRUG_PRESC_MASTER.NEWLY_PRINT is
'是否重打';

comment on column DOCT_DRUG_PRESC_MASTER.DISPENSARY_SUB is
'发药子药局';

comment on column DOCT_DRUG_PRESC_MASTER.CREATE_BY is
'创建人';

comment on column DOCT_DRUG_PRESC_MASTER.CREATE_DATA is
'创建时间';

comment on column DOCT_DRUG_PRESC_MASTER.UPDATE_BY is
'更新人';

comment on column DOCT_DRUG_PRESC_MASTER.UPDATE_DATA is
'更新时间';

comment on column DOCT_DRUG_PRESC_MASTER.REMARKS is
'备注';

comment on column DOCT_DRUG_PRESC_MASTER.DEL_FLAG is
'是否删除';