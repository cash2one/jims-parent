/*=============================================================*/
/* Table: DRUG_SUPPLIER_CATALOG    药品供应商生产商            */
/* CREATE_DATE: 2016-07-08                                     */
/* CREATE_BY :  fengyuguang                                    */
/*  字段长度修改                                               */
/*=============================================================*/
-- Add/modify columns
--增加字段长度
alter table DRUG_SUPPLIER_CATALOG modify INPUT_CODE varchar2(20);