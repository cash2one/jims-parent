/*==============================================================*/
/* Table: OUTP_ACCT_MASTER  门诊收费结帐主记录                  */
/* CREATE_BY: pq                                                */
/*==============================================================*/
-- Add/modify columns
alter table OUTP_ACCT_MASTER modify ACCT_NO VARCHAR2(64);
