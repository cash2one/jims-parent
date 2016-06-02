--drop table OUTPBILL.OUTP_ACCT_MONEY cascade constraints;

/*==============================================================*/
/* Table: OUTP_ACCT_MONEY    门诊收费结帐金额分类               */
/* CREATE_BY: pq                                                */
/*==============================================================*/
create table OUTP_ACCT_MONEY 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   ACCT_ID              VARCHAR2(64),
   ACCT_NO              VARCHAR2(64)         not null,
   MONEY_TYPE           VARCHAR2(64)         not null,
   INCOME_AMOUNT        NUMBER(10,2),
   REFUNDED_AMOUNT      NUMBER(10,2),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_OUTP_ACCT_MONEY primary key (ID)
    
);


comment on table OUTP_ACCT_MONEY is
'门诊收费结帐金额分类';

comment on column OUTP_ACCT_MONEY.ID is
'主键';

comment on column OUTP_ACCT_MONEY.ORG_ID is
'医院ID';

comment on column OUTP_ACCT_MONEY.ACCT_ID is
'结账主记录ID';

comment on column OUTP_ACCT_MONEY.ACCT_NO is
'结帐序号';

comment on column OUTP_ACCT_MONEY.MONEY_TYPE is
'金额类别';

comment on column OUTP_ACCT_MONEY.INCOME_AMOUNT is
'数额';

comment on column OUTP_ACCT_MONEY.REFUNDED_AMOUNT is
'退数额';

comment on column OUTP_ACCT_MONEY.CREATE_DATE is
'创建日期';

comment on column OUTP_ACCT_MONEY.CREATE_BY is
'创建人';

comment on column OUTP_ACCT_MONEY.UPDATE_BY is
'更新人';

comment on column OUTP_ACCT_MONEY.UPDATE_DATE is
'更新日期';

comment on column OUTP_ACCT_MONEY.REMARKS is
'备注信息';

comment on column OUTP_ACCT_MONEY.DEL_FLAG is
'删除标志';
