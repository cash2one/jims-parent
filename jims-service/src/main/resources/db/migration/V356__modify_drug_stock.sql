-- Create table
--    author:zhuqi
/*==============================================================*/
/* Table:DRUG_STOCK                                             */
/*==============================================================*/

-- Add/modify columns
alter table DRUG_STOCK add price_list_id VARCHAR2(64 CHAR);

--增加字段长度
alter table DRUG_PRICE_MODIFY modify  firm_id varchar(64);
-- Add/modify columns

alter table drug_inventory_check modify  firm_id varchar(64);

-- Drop columns
alter table DRUG_STOCK drop column price_list_id;
-- Create/Recreate primary, unique and foreign key constraints

alter table DRUG_STOCK
  add constraint UNIQUE_DRUG_STOCK unique (DRUG_CODE, DRUG_SPEC, FIRM_ID, PACKAGE_SPEC, BATCH_NO, STORAGE, SUB_STORAGE, ORG_ID);