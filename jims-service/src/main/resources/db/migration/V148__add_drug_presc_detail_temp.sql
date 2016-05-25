/*==============================================================*/
/* Table: DRUG_PRESC_DETAIL_TEMP    代发处方门诊明细表          */
/*==============================================================*/
create table DRUG_PRESC_DETAIL_TEMP 
(
   ID                   VARCHAR2(64)         not null,
   MASTER_ID            VARCHAR2(64),
   PRESE_ID             VARCHAR2(64),
   PRESC_DATE           TIMESTAMP            not null,
   PRESC_NO             NUMBER(5)            not null,
   ITEM_NO              NUMBER(2)            not null,
   DRUG_CODE            VARCHAR2(20),
   DRUG_SPEC            VARCHAR2(20),
   DRUG_NAME            VARCHAR2(100),
   FIRM_ID              VARCHAR2(10),
   PACKAGE_SPEC         VARCHAR2(20),
   PACKAGE_UNITS        VARCHAR2(8),
   QUANTITY             NUMBER(6,2),
   COSTS                NUMBER(10,4),
   PAYMENTS             NUMBER(10,4),
   ADMINISTRATION       VARCHAR2(100),
   ORDER_NO             NUMBER(4),
   ORDER_SUB_NO         NUMBER(2),
   DOSAGE_EACH          NUMBER(10,4),
   DOSAGE_UNITS         VARCHAR2(8),
   FREQUENCY            VARCHAR2(16),
   FREQ_DETAIL          VARCHAR2(80),
   BATCH_NO             VARCHAR2(16),
   ABIDANCE             NUMBER(1),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 char),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_DRUG_PRESC_DETAIL_TEMP primary key (ID)
);

comment on table DRUG_PRESC_DETAIL_TEMP is
'待发药处方明细记录：此表为待发药处方的明细记录，由门诊收费或将来的门诊医生工作站录入，发药后，由门诊药局将其删除。';

comment on column DRUG_PRESC_DETAIL_TEMP.ID is
'主键';

comment on column DRUG_PRESC_DETAIL_TEMP.MASTER_ID is
'代发药处方主表ID';

comment on column DRUG_PRESC_DETAIL_TEMP.PRESE_ID is
'处方主记录外键';

comment on column DRUG_PRESC_DETAIL_TEMP.PRESC_DATE is
'处方日期';

comment on column DRUG_PRESC_DETAIL_TEMP.PRESC_NO is
'处方号';

comment on column DRUG_PRESC_DETAIL_TEMP.ITEM_NO is
'项目序号';

comment on column DRUG_PRESC_DETAIL_TEMP.DRUG_CODE is
'药品代码';

comment on column DRUG_PRESC_DETAIL_TEMP.DRUG_SPEC is
'药品规格';

comment on column DRUG_PRESC_DETAIL_TEMP.DRUG_NAME is
'药品名称';

comment on column DRUG_PRESC_DETAIL_TEMP.FIRM_ID is
'厂商标识';

comment on column DRUG_PRESC_DETAIL_TEMP.PACKAGE_SPEC is
'包装规格';

comment on column DRUG_PRESC_DETAIL_TEMP.PACKAGE_UNITS is
'单位';

comment on column DRUG_PRESC_DETAIL_TEMP.QUANTITY is
'数量';

comment on column DRUG_PRESC_DETAIL_TEMP.COSTS is
'费用';

comment on column DRUG_PRESC_DETAIL_TEMP.PAYMENTS is
'实付费用';

comment on column DRUG_PRESC_DETAIL_TEMP.ADMINISTRATION is
'用法';

comment on column DRUG_PRESC_DETAIL_TEMP.ORDER_NO is
'医嘱序号';

comment on column DRUG_PRESC_DETAIL_TEMP.ORDER_SUB_NO is
'医嘱子序号';

comment on column DRUG_PRESC_DETAIL_TEMP.DOSAGE_EACH is
'使用剂量';

comment on column DRUG_PRESC_DETAIL_TEMP.CREATE_DATE is
'创建日期';

comment on column DRUG_PRESC_DETAIL_TEMP.CREATE_BY is
'创建人';

comment on column DRUG_PRESC_DETAIL_TEMP.UPDATE_BY is
'更新人';

comment on column DRUG_PRESC_DETAIL_TEMP.UPDATE_DATE is
'更新日期';

comment on column DRUG_PRESC_DETAIL_TEMP.REMARKS is
'备注信息';

comment on column DRUG_PRESC_DETAIL_TEMP.DEL_FLAG is
'删除标志';