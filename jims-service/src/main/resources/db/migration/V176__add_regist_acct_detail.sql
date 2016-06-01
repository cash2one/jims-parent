/*drop table REGIST_ACCT_DETAIL cascade constraints;
*/
/*==============================================================*/
/* Table: REGIST_ACCT_DETAIL     挂号结帐明细记录                               */
/*==============================================================*/
create table REGIST_ACCT_DETAIL 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   ACCT_ID              VARCHAR2(64),
   ACCT_NO              VARCHAR2(64)         not null,
   TALLY_FEE_CLASS      VARCHAR2(64)         not null,
   COSTS                NUMBER(10,2),
   INCOME               NUMBER(10,2),
   constraint PK_REGIST_ACCT_DETAIL primary key (ID)

);
comment on table REGIST_ACCT_DETAIL is
'挂号结帐明细记录';

comment on column REGIST_ACCT_DETAIL.ID is
'主键';

comment on column REGIST_ACCT_DETAIL.ORG_ID is
'医院ID';

comment on column REGIST_ACCT_DETAIL.ACCT_ID is
'结账主记录ID';

comment on column REGIST_ACCT_DETAIL.ACCT_NO is
'结帐序号';

comment on column REGIST_ACCT_DETAIL.TALLY_FEE_CLASS is
'费用帐目分类';

comment on column REGIST_ACCT_DETAIL.COSTS is
'费用';

comment on column REGIST_ACCT_DETAIL.INCOME is
'收入';
