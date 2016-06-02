--drop table OUTP_ACCT_MASTER cascade constraints;

/*==============================================================*/
/* Table: OUTP_ACCT_MASTER  门诊收费结帐主记录                  */
/* CREATE_BY: pq                                                */
/*==============================================================*/
create table OUTP_ACCT_MASTER 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   ACCT_NO              Integer       not null,
   OPERATOR_NO          VARCHAR2(64),
   ACCT_DATE            TIMESTAMP,
   MIN_RCPT_NO          VARCHAR2(64),
   MAX_RCPT_NO          VARCHAR2(64),
   RCPTS_NUM            NUMBER(4),
   FREE_OF_CHARGE_NUM   NUMBER(4),
   REFUND_NUM           NUMBER(4),
   REFUND_AMOUNT        NUMBER(10,2),
   TOTAL_COSTS          NUMBER(10,2),
   TOTAL_INCOMES        NUMBER(10,2),
   TALLY_DATE           TIMESTAMP,
   RCPT_TOTAL           NUMBER(10,2),
   RCPT_CNT             NUMBER(10,2),
   RCPT_REFUND          NUMBER(10,2),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_OUTP_ACCT_MASTER primary key (ID)
);


comment on table OUTP_ACCT_MASTER is
'门诊收费结帐主记录';

comment on column OUTP_ACCT_MASTER.ID is
'主键';

comment on column OUTP_ACCT_MASTER.ORG_ID is
'医院ID';

comment on column OUTP_ACCT_MASTER.ACCT_NO is
'结帐序号';

comment on column OUTP_ACCT_MASTER.OPERATOR_NO is
'收款员号';

comment on column OUTP_ACCT_MASTER.ACCT_DATE is
'结帐日期';

comment on column OUTP_ACCT_MASTER.MIN_RCPT_NO is
'最小收据序号';

comment on column OUTP_ACCT_MASTER.MAX_RCPT_NO is
'最大收据序号';

comment on column OUTP_ACCT_MASTER.RCPTS_NUM is
'收据张数';

comment on column OUTP_ACCT_MASTER.FREE_OF_CHARGE_NUM is
'免费人次';

comment on column OUTP_ACCT_MASTER.REFUND_NUM is
'退费收据张数';

comment on column OUTP_ACCT_MASTER.REFUND_AMOUNT is
'退费金额';

comment on column OUTP_ACCT_MASTER.TOTAL_COSTS is
'总费用';

comment on column OUTP_ACCT_MASTER.TOTAL_INCOMES is
'总收入';

comment on column OUTP_ACCT_MASTER.TALLY_DATE is
'记帐日期';

comment on column OUTP_ACCT_MASTER.CREATE_DATE is
'创建日期';

comment on column OUTP_ACCT_MASTER.CREATE_BY is
'创建人';

comment on column OUTP_ACCT_MASTER.UPDATE_BY is
'更新人';

comment on column OUTP_ACCT_MASTER.UPDATE_DATE is
'更新日期';

comment on column OUTP_ACCT_MASTER.REMARKS is
'备注信息';

comment on column OUTP_ACCT_MASTER.DEL_FLAG is
'删除标志';
