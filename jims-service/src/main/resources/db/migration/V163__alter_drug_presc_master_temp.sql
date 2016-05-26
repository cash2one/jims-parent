/*==============================================================*/
/* Table: DRUG_PRESC_MASTER_TEMP  门诊待发处方         */
/*CREATE_BY: pq	                                      */
/*==============================================================*/
alter table DRUG_PRESC_MASTER_TEMP add OUT_RESC_ID VARCHAR2(64);
-- Add comments to the columns 
comment on column DRUG_PRESC_MASTER_TEMP.OUT_RESC_ID
  is '门诊处方费用Id';