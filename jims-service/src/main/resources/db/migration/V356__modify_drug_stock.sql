-- Create table
--    author:zhuqi
/*==============================================================*/
/* Table:DRUG_STOCK                                             */
/*==============================================================*/

-- Add/modify columns
alter table DRUG_STOCK add price_list_id VARCHAR2(64 CHAR);

--增加字段长度
alter table DRUG_PRICE_MODIFY modify  firm_id varchar(64)