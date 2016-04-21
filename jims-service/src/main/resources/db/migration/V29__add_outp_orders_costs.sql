drop table OUTP_ORDERS_COSTS cascade constraints;;
/*==============================================================*/
/* Table: OUTP_ORDERS_COSTS         门诊医师收费明细                                   */
/*==============================================================*/
create table OUTP_ORDERS_COSTS
(
   ID                   VARCHAR2(64 CHAR)    not null,
   PATIENT_ID           VARCHAR2(64 CHAR),
   VISIT_DATE           TIMESTAMP,
   VISIT_NO             NUMBER(10),
   SERIAL_NO            VARCHAR2(10 CHAR )   not null,
   ORDER_CLASS          VARCHAR2(2 CHAR)     not null,
   ORDER_NO             NUMBER(4)            not null,
   ORDER_SUB_NO         NUMBER(4),
   ITEM_NO              NUMBER(4)            not null,
   ITEM_CLASS           VARCHAR2(5 CHAR),
   ITEM_NAME            VARCHAR2(400 CHAR),
   ITEM_CODE            VARCHAR2(100 CHAR),
   ITEM_SPEC            VARCHAR2(50 CHAR),
   UNITS                VARCHAR2(64 CHAR),
   REPETITION           NUMBER(4),
   AMOUNT               NUMBER(8,4),
   ORDERED_BY_DEPT      VARCHAR2(64 CHAR),
   ORDERED_BY_DOCTOR    VARCHAR2(64 CHAR),
   PERFORMED_BY         VARCHAR2(64 CHAR),
   CLASS_ON_RCPT        VARCHAR2(64 CHAR),
   COSTS                NUMBER(10,4),
   CHARGES              NUMBER(10,4),
   RCPT_NO              VARCHAR2(20),
   BILL_DESC_NO         NUMBER(10),
   BILL_ITEM_NO         NUMBER(2),
   CHARGE_INDICATOR     NUMBER(1),
   CLASS_ON_RECKONING   VARCHAR2(64 CHAR),
   SUBJ_CODE            VARCHAR2(64 CHAR),
   PRICE_QUOTIETY       NUMBER(7,4),
   ITEM_PRICE           NUMBER(10,4),
   CLINIC_NO            VARCHAR2(20 CHAR),
   BILL_DATE            TIMESTAMP,
   BILL_NO              NUMBER(5),
   WARD_CODE            VARCHAR2(64 CHAR),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 CHAR),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 CHAR),
   REMARKS              VARCHAR2(200 CHAR),
   DEL_FLAG             NUMBER(1),
   constraint PK_OUTP_ORDERS_COSTS primary key (ID)
);

comment on table OUTP_ORDERS_COSTS is
'门诊医生收费明细';

comment on column OUTP_ORDERS_COSTS.ID is
'门诊医生收费明细';

comment on column OUTP_ORDERS_COSTS.PATIENT_ID is
'ID号';

comment on column OUTP_ORDERS_COSTS.VISIT_DATE is
'就诊日期';

comment on column OUTP_ORDERS_COSTS.VISIT_NO is
'就诊序号';

comment on column OUTP_ORDERS_COSTS.SERIAL_NO is
'流水号';

comment on column OUTP_ORDERS_COSTS.ORDER_CLASS is
'诊疗项目类别';

comment on column OUTP_ORDERS_COSTS.ORDER_NO is
'医嘱号';

comment on column OUTP_ORDERS_COSTS.ORDER_SUB_NO is
'子医嘱号';

comment on column OUTP_ORDERS_COSTS.ITEM_NO is
'顺序号';

comment on column OUTP_ORDERS_COSTS.ITEM_CLASS is
'收费项目类别';

comment on column OUTP_ORDERS_COSTS.ITEM_NAME is
'项目名称';

comment on column OUTP_ORDERS_COSTS.ITEM_CODE is
'项目代码';

comment on column OUTP_ORDERS_COSTS.ITEM_SPEC is
'项目规格';

comment on column OUTP_ORDERS_COSTS.UNITS is
'单位';

comment on column OUTP_ORDERS_COSTS.REPETITION is
'付数';

comment on column OUTP_ORDERS_COSTS.AMOUNT is
'数量';

comment on column OUTP_ORDERS_COSTS.ORDERED_BY_DEPT is
'录入科室';

comment on column OUTP_ORDERS_COSTS.ORDERED_BY_DOCTOR is
'录入医生';

comment on column OUTP_ORDERS_COSTS.PERFORMED_BY is
'执行诊室';

comment on column OUTP_ORDERS_COSTS.CLASS_ON_RCPT is
'收费项目分类';

comment on column OUTP_ORDERS_COSTS.COSTS is
'计价金额';

comment on column OUTP_ORDERS_COSTS.CHARGES is
'实收费用';

comment on column OUTP_ORDERS_COSTS.RCPT_NO is
'收据号码';

comment on column OUTP_ORDERS_COSTS.CHARGE_INDICATOR is
'收费标记';

comment on column OUTP_ORDERS_COSTS.CLASS_ON_RECKONING is
'核算项目分类';

comment on column OUTP_ORDERS_COSTS.SUBJ_CODE is
'会计科目';

comment on column OUTP_ORDERS_COSTS.PRICE_QUOTIETY is
'收费系数';

comment on column OUTP_ORDERS_COSTS.ITEM_PRICE is
'单价';

comment on column OUTP_ORDERS_COSTS.BILL_DATE is
'项目收费日期';

comment on column OUTP_ORDERS_COSTS.BILL_NO is
'项目收费编号';

comment on column OUTP_ORDERS_COSTS.WARD_CODE is
'执行科室';

comment on column OUTP_ORDERS_COSTS.CREATE_DATE is
'创建日期';

comment on column OUTP_ORDERS_COSTS.CREATE_BY is
'创建人';

comment on column OUTP_ORDERS_COSTS.UPDATE_DATE is
'更新日期';

comment on column OUTP_ORDERS_COSTS.UPDATE_BY is
'更新人';

comment on column OUTP_ORDERS_COSTS.REMARKS is
'备注信息';

comment on column OUTP_ORDERS_COSTS.DEL_FLAG is
'删除标志';