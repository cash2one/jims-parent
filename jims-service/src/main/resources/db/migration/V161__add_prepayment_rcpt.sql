/*drop table PREPAYMENT_RCPT cascade constraints;*/

/*==============================================================*/
/* Table: PREPAYMENT_RCPT    预交金记录                         */
/* CREATE_BY CTQ                                                */
/* CREATE_DATE 2016-05-25 10:12:51                              */
/*==============================================================*/
create table PREPAYMENT_RCPT 
(
   ID                   VARCHAR2(64)         not null,
   PATIENT_ID           VARCHAR2(64),
   RCPT_NO              VARCHAR2(14),
   AMOUNT               NUMBER(9,2),
   PAY_WAY              VARCHAR2(8),
   BANK                 VARCHAR2(30),
   CHECK_NO             VARCHAR2(16),
   TRANSACT_TYPE        VARCHAR2(4),
   TRANSACT_DATE        DATE,
   OPERATOR_NO          VARCHAR2(4),
   REFUNDED_RCPT_NO     VARCHAR2(8),
   ACCT_NO              VARCHAR2(6),
   ADDR                 VARCHAR2(40),
   CHECK_DIV            VARCHAR2(40),
   BANK_CODE            VARCHAR2(30),
   VISIT_ID             VARCHAR2(64),
   SETTLED_NO           VARCHAR2(20),
   USED_RCPT_NO         VARCHAR2(10),
   USED_FLAG            VARCHAR2(1),
   BANK_AUOUNT_NO       VARCHAR2(20),
   INVOICE_NO           VARCHAR2(20),
   SERIAL_NO             VARCHAR2(64),
   SETTLED_PRE_NO       VARCHAR2(16),
   SETTLE_BALANCE       NUMBER(9,2),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             CHAR(1) default '0',
   constraint PK_PREPAYMENT_RCPT primary key (ID)
);

comment on table PREPAYMENT_RCPT is
'预交金记录';

comment on column PREPAYMENT_RCPT.ID is
'主键';

comment on column PREPAYMENT_RCPT.PATIENT_ID is
'病人标识';

comment on column PREPAYMENT_RCPT.RCPT_NO is
'预交金收据号';

comment on column PREPAYMENT_RCPT.AMOUNT is
'金额';

comment on column PREPAYMENT_RCPT.PAY_WAY is
'支付方式';

comment on column PREPAYMENT_RCPT.BANK is
'开户银行';

comment on column PREPAYMENT_RCPT.CHECK_NO is
'支票号';

comment on column PREPAYMENT_RCPT.TRANSACT_TYPE is
'操作类型';

comment on column PREPAYMENT_RCPT.TRANSACT_DATE is
'操作日期';

comment on column PREPAYMENT_RCPT.OPERATOR_NO is
'收款员号';

comment on column PREPAYMENT_RCPT.REFUNDED_RCPT_NO is
'退费收据号';

comment on column PREPAYMENT_RCPT.ACCT_NO is
'预交金结帐序号';

comment on column PREPAYMENT_RCPT.CHECK_DIV is
'合同单位';

comment on column PREPAYMENT_RCPT.BANK_CODE is
'银行帐号';

comment on column PREPAYMENT_RCPT.INVOICE_NO is
'收款发票号';

comment on column PREPAYMENT_RCPT.CREATE_BY is
'创建人';

comment on column PREPAYMENT_RCPT.CREATE_DATE is
'创建时间';

comment on column PREPAYMENT_RCPT.UPDATE_BY is
'更新人';

comment on column PREPAYMENT_RCPT.UPDATE_DATE is
'更新时间';

comment on column PREPAYMENT_RCPT.REMARKS is
'备注';

comment on column PREPAYMENT_RCPT.DEL_FLAG is
'删除标识';
