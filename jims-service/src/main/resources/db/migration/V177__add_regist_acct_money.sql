/*drop table REGIST_ACCT_MONEY cascade constraints;*/

/*==============================================================*/
/* Table: REGIST_ACCT_MONEY     挂号结帐金额分类                                */
/*==============================================================*/
create table REGIST_ACCT_MONEY 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   ACCT_ID              VARCHAR2(64),
   ACCT_NO              VARCHAR2(64)         not null,
   MONEY_TYPE           VARCHAR2(64)         not null,
   INCOME_AMOUNT        NUMBER(10,2),
   REFUNDED_AMOUNT      NUMBER(10,2),
   constraint PK_REGIST_ACCT_MONEY primary key (ID)
    
);

comment on table REGIST_ACCT_MONEY is
'挂号结帐金额分类';

comment on column REGIST_ACCT_MONEY.ID is
'主键';

comment on column REGIST_ACCT_MONEY.ORG_ID is
'医院ID';

comment on column REGIST_ACCT_MONEY.ACCT_ID is
'结账主记录ID';

comment on column REGIST_ACCT_MONEY.ACCT_NO is
'结帐序号';

comment on column REGIST_ACCT_MONEY.MONEY_TYPE is
'金额类别';

comment on column REGIST_ACCT_MONEY.INCOME_AMOUNT is
'数额';

comment on column REGIST_ACCT_MONEY.REFUNDED_AMOUNT is
'退数额';
