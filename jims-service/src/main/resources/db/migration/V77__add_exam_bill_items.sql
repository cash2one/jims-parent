-- drop table EXAM_BILL_ITEMS cascade constraints;

/*==============================================================*/
/* Table: EXAM_BILL_ITEMS    检查计价项目                       */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table EXAM_BILL_ITEMS 
(
   ID                   VARCHAR2(64 char)    not null,
   EXAM_NO              VARCHAR2(10 char),
   EXAM_ITEM_NO         NUMBER(11),
   CHARGE_ITEM_NO       NUMBER(11),
   PATIENT_ID           VARCHAR2(10 char),
   VISIT_ID             NUMBER(11),
   ITEM_CLASS           CHAR,
   ITEM_NAME            VARCHAR2(100 char),
   ITEM_CODE            VARCHAR2(64 char),
   ITEM_SPEC            VARCHAR2(64 char),
   AMOUNT               NUMBER(6,2),
   UNITS                VARCHAR2(10 char),
   ORDERED_BY           VARCHAR2(10 char),
   PERFORMED_BY         VARCHAR2(10 char),
   COSTS                NUMBER(8,2),
   CHARGES              NUMBER(8,2),
   BILLING_DATE_TIME    TIMESTAMP,
   OPERATOR_NO          VARCHAR2(4 char),
   VERIFIED_INDICATOR   NUMBER(11),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKES             VARCHAR2(64 char),
   constraint PK_EXAM_BILL_ITEMS primary key (ID)
  );
comment on table EXAM_BILL_ITEMS is
'检查计价项目';

comment on column EXAM_BILL_ITEMS.ID is
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
