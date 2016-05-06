-- drop table EXAM_BILL_ITEMS cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: EXAM_BILL_ITEMS      ���Ƽ���Ŀ
/* CREATE_DATE: 2016-05-05                             */
/* CREATE_BY :  zp
/*==============================================================*/
create table EXAM_BILL_ITEMS 
(
   id                   varchar2(64 char)              not null,
   EXAM_NO              varchar2(10 char)              null,
   EXAM_ITEM_NO         number(11)                     null,
   CHARGE_ITEM_NO       number(11)                     null,
   PATIENT_ID           varchar2(10 char)              null,
   VISIT_ID             number(11)                     null,
   ITEM_CLASS           char(1)                        null,
   ITEM_NAME            varchar2(100 char)             null,
   ITEM_CODE            varchar2(64 char)              null,
   ITEM_SPEC            varchar2(64 char)              null,
   AMOUNT               number(6,2)                    null,
   UNITS                varchar2(10 char)              null,
   ORDERED_BY           varchar2(10 char)              null,
   PERFORMED_BY         varchar2(10 char)              null,
   COSTS                number(8,2)                    null,
   CHARGES              number(8,2)                    null,
   BILLING_DATE_TIME    TIMESTAMP                      null,
   OPERATOR_NO          varchar2(4 char)               null,
   VERIFIED_INDICATOR   number(11)                     null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          TIMESTAMP                      null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          TIMESTAMP                      null,
   DEL_FLAG             char(1)                        null,
   REMARKES             varchar2(64 char)              null,
   constraint PK_EXAM_BILL_ITEMS primary key  (id)
);

comment on table EXAM_BILL_ITEMS is 
'���Ƽ���Ŀ';

comment on column EXAM_BILL_ITEMS.id is 
'����';

comment on column EXAM_BILL_ITEMS.EXAM_NO is 
'�������';

comment on column EXAM_BILL_ITEMS.EXAM_ITEM_NO is 
'��Ŀ���';

comment on column EXAM_BILL_ITEMS.CHARGE_ITEM_NO is 
'�Ƽ���Ŀ���';

comment on column EXAM_BILL_ITEMS.PATIENT_ID is 
'���˱�ʶ��';

comment on column EXAM_BILL_ITEMS.VISIT_ID is 
'���˱���סԺ��ʶ';

comment on column EXAM_BILL_ITEMS.ITEM_CLASS is 
'��Ŀ���';

comment on column EXAM_BILL_ITEMS.ITEM_NAME is 
'��Ŀ����';

comment on column EXAM_BILL_ITEMS.ITEM_CODE is 
'��Ŀ����';

comment on column EXAM_BILL_ITEMS.ITEM_SPEC is 
'��Ŀ���';

comment on column EXAM_BILL_ITEMS.AMOUNT is 
'����';

comment on column EXAM_BILL_ITEMS.UNITS is 
'��λ';

comment on column EXAM_BILL_ITEMS.ORDERED_BY is 
'��������';

comment on column EXAM_BILL_ITEMS.PERFORMED_BY is 
'ִ�п���';

comment on column EXAM_BILL_ITEMS.COSTS is 
'����';

comment on column EXAM_BILL_ITEMS.CHARGES is 
'Ӧ�շ���';

comment on column EXAM_BILL_ITEMS.BILLING_DATE_TIME is 
'�Ƽ����ڼ�ʱ��';

comment on column EXAM_BILL_ITEMS.OPERATOR_NO is 
'�Ƽ�Ա��';

comment on column EXAM_BILL_ITEMS.VERIFIED_INDICATOR is 
'����ȷ�ϱ�־';

comment on column EXAM_BILL_ITEMS.CREATE_BY is 
'������';

comment on column EXAM_BILL_ITEMS.CREATE_DATE is 
'����ʱ��';

comment on column EXAM_BILL_ITEMS.UPDATE_BY is 
'�޸���';

comment on column EXAM_BILL_ITEMS.UPDATE_DATE is 
'�޸�ʱ��';

comment on column EXAM_BILL_ITEMS.DEL_FLAG is 
'ɾ�����';

comment on column EXAM_BILL_ITEMS.REMARKES is 
'��ע��Ϣ';