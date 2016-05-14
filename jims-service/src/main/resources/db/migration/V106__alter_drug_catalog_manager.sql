/*==============================================================*/
/* Table: DRUG_DICT    药品字典                                 */
/* Table: DRUG_NAME_DICT    药品名称字典                        */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  txb                                             */
/*==============================================================*/
-- Add/modify columns
alter table DRUG_DICT modify input_code VARCHAR2(50 CHAR);
-- Add/modify columns
alter table DRUG_NAME_DICT modify input_code VARCHAR2(50 CHAR);
alter table DRUG_NAME_DICT modify input_code_wb VARCHAR2(50 CHAR);