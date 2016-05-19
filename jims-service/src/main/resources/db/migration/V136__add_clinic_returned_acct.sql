/*==============================================================*/
/* Table: CLINIC_RETURNED_ACCT                 退号记录表        */
/*==============================================================*/
create table CLINIC_RETURNED_ACCT
(
   ID                   VARCHAR2(64),
   CLINIC_ID            VARCHAR2(64),
   VISIT_DATE           TIMESTAMP                 not null,
   VISIT_NO             NUMBER(5)            not null,
   CLINIC_LABEL         VARCHAR2(64),
   TIME_DESC            VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   PATIENT_NAME         VARCHAR2(100 char),
   REGIST_FEE           NUMBER(16,2),
   CLINIC_FEE           NUMBER(16,2),
   OTHER_FEE            NUMBER(16,2),
   CLINIC_CHARGE        NUMBER(16,2),
   OPERATOR             VARCHAR2(64),
   RETURNED_DATE        TIMESTAMP,
   RETURNED_OPERATOR    VARCHAR2(64),
   ACCT_NO              VARCHAR2(64),
   ACCT_DATE_TIME       DATE,
   SERIAL_NO            NUMBER(3),
   RE_FLAG              CHAR,
   PAY_WAY              VARCHAR2(8),
   INVOICE_NO           VARCHAR2(64),
   AUTO_FLAG            VARCHAR2(3),
   PRINT_OPERATOR       VARCHAR2(8),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char,
   constraint PK_CLINIC_RETURNED_ACCT primary key (ID)
);
comment on table CLINIC_RETURNED_ACCT is
'挂号结帐主记录';

comment on column CLINIC_RETURNED_ACCT.ID is
'主键';

comment on column CLINIC_RETURNED_ACCT.CLINIC_ID is
'挂号表主键';

comment on column CLINIC_RETURNED_ACCT.VISIT_DATE is
'就诊日期';

comment on column CLINIC_RETURNED_ACCT.VISIT_NO is
'就诊序号';

comment on column CLINIC_RETURNED_ACCT.CLINIC_LABEL is
'号别';

comment on column CLINIC_RETURNED_ACCT.TIME_DESC is
'就诊时间描述';

comment on column CLINIC_RETURNED_ACCT.PATIENT_ID is
'病人标识';

comment on column CLINIC_RETURNED_ACCT.PATIENT_NAME is
'姓名';

comment on column CLINIC_RETURNED_ACCT.REGIST_FEE is
'挂号费';

comment on column CLINIC_RETURNED_ACCT.CLINIC_FEE is
'诊疗费';

comment on column CLINIC_RETURNED_ACCT.OTHER_FEE is
'其它费';

comment on column CLINIC_RETURNED_ACCT.CLINIC_CHARGE is
'实收费用';

comment on column CLINIC_RETURNED_ACCT.OPERATOR is
'挂号员';

comment on column CLINIC_RETURNED_ACCT.RETURNED_DATE is
'退号日期';

comment on column CLINIC_RETURNED_ACCT.RETURNED_OPERATOR is
'退号挂号员';

comment on column CLINIC_RETURNED_ACCT.ACCT_NO is
'结账号';

comment on column CLINIC_RETURNED_ACCT.ACCT_DATE_TIME is
'结账时间';

comment on column CLINIC_RETURNED_ACCT.SERIAL_NO is
'挂号结帐主记录';

comment on column CLINIC_RETURNED_ACCT.RE_FLAG is
'挂号结帐主记录';

comment on column CLINIC_RETURNED_ACCT.PAY_WAY is
'支付方式';

comment on column CLINIC_RETURNED_ACCT.INVOICE_NO is
'发票号';

comment on column CLINIC_RETURNED_ACCT.PRINT_OPERATOR is
'打印操作员';

comment on column CLINIC_RETURNED_ACCT.CREATE_DATE is
'创建日期';

comment on column CLINIC_RETURNED_ACCT.CREATE_BY is
'创建人';

comment on column CLINIC_RETURNED_ACCT.UPDATE_DATE is
'更新日期';

comment on column CLINIC_RETURNED_ACCT.UPDATE_BY is
'更新人';

comment on column CLINIC_RETURNED_ACCT.REMARKS is
'备注信息';

comment on column CLINIC_RETURNED_ACCT.DEL_FLAG is
'删除标志';
