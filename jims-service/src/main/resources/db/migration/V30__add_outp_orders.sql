-- drop table OUTP_ORDERS cascade constraints;
/*==============================================================*/
/* Table: OUTP_ORDERS         门诊医嘱主记录                                */
/*==============================================================*/
create table OUTP_ORDERS
(
   ID                   VARCHAR2(64 CHAR)    not null,
   ORG_ID                  VARCHAR2(64 CHAR),
   CLINIC_ID              VARCHAR2(64),
   PATIENT_ID          VARCHAR2(64 CHAR),
   VISIT_DATE           TIMESTAMP,
   VISIT_NO             NUMBER(5),
   SERIAL_NO            VARCHAR2(64),
   ORDERED_BY           VARCHAR2(64),
   DOCTOR               VARCHAR2(64),
   ORDER_DATE           TIMESTAMP,
   CLINIC_NO            VARCHAR2(15),
   DOCTOR_NO            VARCHAR2(64),
   NURSE                VARCHAR2(16),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 CHAR),
   UPDATE_BY            VARCHAR2(64 CHAR),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 CHAR),
   DEL_FLAG             NUMBER(1),
   constraint PK_OUTP_ORDERS primary key (ID)
);

comment on table OUTP_ORDERS is
'门诊医嘱主记录';

comment on column OUTP_ORDERS.ID is
'门诊医嘱主记录';

comment on column OUTP_ORDERS.PATIENT_ID is
'病人标识号';

comment on column OUTP_ORDERS.VISIT_DATE is
'就诊日期';

comment on column OUTP_ORDERS.VISIT_NO is
'就诊序号';

comment on column OUTP_ORDERS.SERIAL_NO is
'流水号';

comment on column OUTP_ORDERS.ORDERED_BY is
'开单科室';

comment on column OUTP_ORDERS.DOCTOR is
'开单医生';

comment on column OUTP_ORDERS.ORDER_DATE is
'开单日期';

comment on column OUTP_ORDERS.NURSE is
'护士登录名';

comment on column OUTP_ORDERS.CREATE_DATE is
'创建日期';

comment on column OUTP_ORDERS.CREATE_BY is
'创建人';

comment on column OUTP_ORDERS.UPDATE_BY is
'更新人';

comment on column OUTP_ORDERS.UPDATE_DATE is
'更新日期';

comment on column OUTP_ORDERS.REMARKS is
'备注信息';

comment on column OUTP_ORDERS.DEL_FLAG is
'删除标志';