/*=============================================================*/
/* Table: drug_price_modify   调价记录表           */
/* CREATE_DATE: 2016-07-07                                     */
/* CREATE_BY :  weishen                                           */
/*  添加字段                                                   */
/*=============================================================*/
-- alter table

-- 增加字段长度
alter table DRUG_PRICE_MODIFY modify firm_id VARCHAR2(64);
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_PRICE_MODIFY
  drop constraint UK_DRUG_PRICE_MODIFY cascade;
alter table DRUG_PRICE_MODIFY
  add constraint UK_DRUG_PRICE_MODIFY unique (DRUG_CODE, DRUG_SPEC, UNITS, FIRM_ID, ORG_ID, ACTUAL_EFFICIENT_DATE)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;