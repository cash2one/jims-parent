--drop table DOCT_DRUG_PRESC_DETAIL cascade constraints;

/*==============================================================*/
/* Table: DOCT_DRUG_PRESC_DETAIL    待发药住院处方明细记录      */
/* CREATE_DATE: 2016-05-16 11:52:40                             */
/* CREATE_BY: CTQ						                                    */
/*==============================================================*/
create table DOCT_DRUG_PRESC_DETAIL 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   PRESC_MASTER_ID      VARCHAR2(64),
   PRESC_DATE           TIMESTAMP,
   PRESC_NO             NUMBER(4),
   ITEM_NO              NUMBER(2),
   ORDER_NO             NUMBER(4),
   ORDER_SUB_NO         NUMBER(2),
   DRUG_CODE            VARCHAR2(20),
   DRUG_SPEC            VARCHAR2(30),
   DRUG_NAME            VARCHAR2(100),
   FIRM_ID              VARCHAR2(64),
   PACKAGE_SPEC         VARCHAR2(20),
   PACKAGE_UNITS        VARCHAR2(8),
   QUANTITY             NUMBER(6,2),
   DOSAGE               NUMBER(12,4),
   DOSAGE_UNITS         VARCHAR2(8),
   ADMINISTRATION       VARCHAR2(64),
   COSTS                NUMBER(10,4),
   PAYMENTS             NUMBER(10,4),
   MEMO                 VARCHAR2(50),
   AMOUNT_PER_PACKAGE   NUMBER(5),
   FREQUENCY            VARCHAR2(64),
   DOSAGE_EACH          NUMBER(8,4),
   FREQ_DETAIL          VARCHAR2(80),
   BATCH_NO             VARCHAR2(16),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             char(1) default '0',
   constraint PK_DOCT_DRUG_PRESC_DETAIL primary key (ID)
   );


comment on table DOCT_DRUG_PRESC_DETAIL is
'待发药住院处方明细记录';

comment on column DOCT_DRUG_PRESC_DETAIL.ID is
'主键';

comment on column DOCT_DRUG_PRESC_DETAIL.ORG_ID is
'机构ID';

comment on column DOCT_DRUG_PRESC_DETAIL.PATIENT_ID is
'病人ID';

comment on column DOCT_DRUG_PRESC_DETAIL.VISIT_ID is
'住院ID';

comment on column DOCT_DRUG_PRESC_DETAIL.PRESC_MASTER_ID is
'住院处方主记录ID';

comment on column DOCT_DRUG_PRESC_DETAIL.PRESC_DATE is
'处方日期';

comment on column DOCT_DRUG_PRESC_DETAIL.PRESC_NO is
'处方号';

comment on column DOCT_DRUG_PRESC_DETAIL.ITEM_NO is
'项目序号';

comment on column DOCT_DRUG_PRESC_DETAIL.ORDER_NO is
'医嘱序号';

comment on column DOCT_DRUG_PRESC_DETAIL.ORDER_SUB_NO is
'医嘱子序号';

comment on column DOCT_DRUG_PRESC_DETAIL.DRUG_CODE is
'药品编码';

comment on column DOCT_DRUG_PRESC_DETAIL.DRUG_SPEC is
'药品';

comment on column DOCT_DRUG_PRESC_DETAIL.DRUG_NAME is
'药名';

comment on column DOCT_DRUG_PRESC_DETAIL.FIRM_ID is
'生产厂商';

comment on column DOCT_DRUG_PRESC_DETAIL.PACKAGE_SPEC is
'包装规格';

comment on column DOCT_DRUG_PRESC_DETAIL.PACKAGE_UNITS is
'包装单位';

comment on column DOCT_DRUG_PRESC_DETAIL.QUANTITY is
'数量';

comment on column DOCT_DRUG_PRESC_DETAIL.DOSAGE is
'剂量';

comment on column DOCT_DRUG_PRESC_DETAIL.DOSAGE_UNITS is
'剂量单位';

comment on column DOCT_DRUG_PRESC_DETAIL.ADMINISTRATION is
'用法';

comment on column DOCT_DRUG_PRESC_DETAIL.COSTS is
'费用';

comment on column DOCT_DRUG_PRESC_DETAIL.PAYMENTS is
'已付费用';

comment on column DOCT_DRUG_PRESC_DETAIL.MEMO is
'备注';

comment on column DOCT_DRUG_PRESC_DETAIL.AMOUNT_PER_PACKAGE is
'每包装数量';

comment on column DOCT_DRUG_PRESC_DETAIL.FREQUENCY is
'执行频率描述';

comment on column DOCT_DRUG_PRESC_DETAIL.DOSAGE_EACH is
'单次剂量';

comment on column DOCT_DRUG_PRESC_DETAIL.FREQ_DETAIL is
'医嘱说明';

comment on column DOCT_DRUG_PRESC_DETAIL.CREATE_BY is
'创建人';

comment on column DOCT_DRUG_PRESC_DETAIL.CREATE_DATE is
'创建时间';

comment on column DOCT_DRUG_PRESC_DETAIL.UPDATE_BY is
'更新人';

comment on column DOCT_DRUG_PRESC_DETAIL.UPDATE_DATE is
'更新时间';

comment on column DOCT_DRUG_PRESC_DETAIL.REMARKS is
'备注';

comment on column DOCT_DRUG_PRESC_DETAIL.DEL_FLAG is
'是否删除';