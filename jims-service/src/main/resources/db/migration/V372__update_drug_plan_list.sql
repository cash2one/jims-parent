/*=============================================================*/
/* update 表修改约束                                                    */
/*=============================================================*/

ALTER TABLE DRUG_BUY_PLAN DROP CONSTRAINT DRUG_BUY_PLAN_UK ;

alter table DRUG_BUY_PLAN
  add constraint DRUG_BUY_PLAN_UK unique (BUY_ID, BUY_NO, ORG_ID);