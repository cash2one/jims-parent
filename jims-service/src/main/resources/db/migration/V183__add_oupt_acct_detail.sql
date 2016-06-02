--drop table OUTP_ACCT_DETAIL cascade constraints;

/*==============================================================*/
/* Table: OUTP_ACCT_DETAIL  门诊收费结帐明细记录                */
/* CREATE_BY: pq                                                */
/*==============================================================*/
create table OUTP_ACCT_DETAIL 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   ACCT_ID              VARCHAR2(64),
   ACCT_NO              VARCHAR2(6)          not null,
   TALLY_FEE_CLASS      VARCHAR2(64)         not null,
   COSTS                NUMBER(10,2),
   INCOME               NUMBER(10,2),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_OUTP_ACCT_DETAIL primary key (ID)
);


comment on table OUTP_ACCT_DETAIL is
'门诊收费结帐明细记录';

comment on column OUTP_ACCT_DETAIL.ID is
'主键';

comment on column OUTP_ACCT_DETAIL.ORG_ID is
'医院ID';

comment on column OUTP_ACCT_DETAIL.ACCT_ID is
'结账主记录ID';

comment on column OUTP_ACCT_DETAIL.ACCT_NO is
'结帐序号';

comment on column OUTP_ACCT_DETAIL.TALLY_FEE_CLASS is
'费用帐目分类';

comment on column OUTP_ACCT_DETAIL.COSTS is
'费用';

comment on column OUTP_ACCT_DETAIL.INCOME is
'收入';

comment on column OUTP_ACCT_DETAIL.CREATE_DATE is
'创建日期';

comment on column OUTP_ACCT_DETAIL.CREATE_BY is
'创建人';

comment on column OUTP_ACCT_DETAIL.UPDATE_BY is
'更新人';

comment on column OUTP_ACCT_DETAIL.UPDATE_DATE is
'更新日期';

comment on column OUTP_ACCT_DETAIL.REMARKS is
'备注信息';

comment on column OUTP_ACCT_DETAIL.DEL_FLAG is
'删除标志';
