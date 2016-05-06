-- drop table EXAM_BILL_ITEMS cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: EXAM_BILL_ITEMS      检查计价项目
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
'检查计价项目';

comment on column EXAM_BILL_ITEMS.id is 
'主键';

comment on column EXAM_BILL_ITEMS.EXAM_NO is 
'申请序号';

comment on column EXAM_BILL_ITEMS.EXAM_ITEM_NO is 
'项目序号';

comment on column EXAM_BILL_ITEMS.CHARGE_ITEM_NO is 
'计价项目序号';

comment on column EXAM_BILL_ITEMS.PATIENT_ID is 
'病人标识号';

comment on column EXAM_BILL_ITEMS.VISIT_ID is 
'病人本次住院标识';

comment on column EXAM_BILL_ITEMS.ITEM_CLASS is 
'项目类别';

comment on column EXAM_BILL_ITEMS.ITEM_NAME is 
'项目名称';

comment on column EXAM_BILL_ITEMS.ITEM_CODE is 
'项目代码';

comment on column EXAM_BILL_ITEMS.ITEM_SPEC is 
'项目规格';

comment on column EXAM_BILL_ITEMS.AMOUNT is 
'数量';

comment on column EXAM_BILL_ITEMS.UNITS is 
'单位';

comment on column EXAM_BILL_ITEMS.ORDERED_BY is 
'开单科室';

comment on column EXAM_BILL_ITEMS.PERFORMED_BY is 
'执行科室';

comment on column EXAM_BILL_ITEMS.COSTS is 
'费用';

comment on column EXAM_BILL_ITEMS.CHARGES is 
'应收费用';

comment on column EXAM_BILL_ITEMS.BILLING_DATE_TIME is 
'计价日期及时间';

comment on column EXAM_BILL_ITEMS.OPERATOR_NO is 
'计价员号';

comment on column EXAM_BILL_ITEMS.VERIFIED_INDICATOR is 
'划价确认标志';

comment on column EXAM_BILL_ITEMS.CREATE_BY is 
'创建人';

comment on column EXAM_BILL_ITEMS.CREATE_DATE is 
'创建时间';

comment on column EXAM_BILL_ITEMS.UPDATE_BY is 
'修改人';

comment on column EXAM_BILL_ITEMS.UPDATE_DATE is 
'修改时间';

comment on column EXAM_BILL_ITEMS.DEL_FLAG is 
'删除标记';

comment on column EXAM_BILL_ITEMS.REMARKES is 
'备注信息';