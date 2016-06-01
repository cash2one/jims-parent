/*drop table REGIST_ACCT_MASTER cascade constraints;*/

/*==============================================================*/
/* Table: REGIST_ACCT_MASTER     挂号结帐主记录                 */
/*==============================================================*/
create table REGIST_ACCT_MASTER 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   CLINIC_ID            VARCHAR2(64),
   ACCT_NO              VARCHAR2(64)         not null,
   OPERATOR_NO          VARCHAR2(64)         not null,
   ACCT_DATE            DATE                 not null,
   REGIST_NUM           NUMBER(16,2),
   REFUND_NUM           NUMBER(16,2),
   REFUND_AMOUNT        NUMBER(16,2),
   TOTAL_COSTS          NUMBER(16,2),
   TOTAL_INCOMES        NUMBER(16,2),
   TALLY_DATE           DATE,
   FULFILL_DATE_TIME    DATE,
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char(1)            default '0',
   constraint PK_REGIST_ACCT_MASTER primary key (ID)
    
);

comment on table REGIST_ACCT_MASTER is
'挂号结帐主记录';

comment on column REGIST_ACCT_MASTER.ID is
'主键';

comment on column REGIST_ACCT_MASTER.ORG_ID is
'医院ID';

comment on column REGIST_ACCT_MASTER.CLINIC_ID is
'就诊记录ID';

comment on column REGIST_ACCT_MASTER.ACCT_NO is
'结帐序号';

comment on column REGIST_ACCT_MASTER.OPERATOR_NO is
'收款员号';

comment on column REGIST_ACCT_MASTER.ACCT_DATE is
'结帐日期';

comment on column REGIST_ACCT_MASTER.REGIST_NUM is
'挂号人次';

comment on column REGIST_ACCT_MASTER.REFUND_NUM is
'退费人次';

comment on column REGIST_ACCT_MASTER.REFUND_AMOUNT is
'退费金额';

comment on column REGIST_ACCT_MASTER.TOTAL_COSTS is
'总费用';

comment on column REGIST_ACCT_MASTER.TOTAL_INCOMES is
'总收入';

comment on column REGIST_ACCT_MASTER.TALLY_DATE is
'记帐日期';

comment on column REGIST_ACCT_MASTER.CREATE_DATE is
'创建日期';

comment on column REGIST_ACCT_MASTER.CREATE_BY is
'创建人';

comment on column REGIST_ACCT_MASTER.UPDATE_BY is
'更新人';

comment on column REGIST_ACCT_MASTER.UPDATE_DATE is
'更新日期';

comment on column REGIST_ACCT_MASTER.REMARKS is
'备注信息';

comment on column REGIST_ACCT_MASTER.DEL_FLAG is
'删除标志';
