--drop table ORDERS cascade constraints;

/*==============================================================*/
/* Table: ORDERS   医嘱						                              */
/* CREATE_DATE: 2016-05-16 11:52:40                             */
/* CREATE_BY: CTQ						                                    */
/*==============================================================*/
create table ORDERS 
(
   ID                   VARCHAR2(64)         not null,
   PATIENT_ID           VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   OTHER_ID      VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   ORDER_NO             NUMBER(4)            not null,
   ORDER_SUB_NO         NUMBER(2)            not null,
   REPEAT_INDICATOR     NUMBER(1),
   ORDER_CLASS          VARCHAR2(1),
   ORDER_TEXT           VARCHAR2(200),
   ORDER_CODE           VARCHAR2(20),
   DOSAGE               NUMBER(10,4),
   DOSAGE_UNITS         VARCHAR2(8),
   ADMINISTRATION       VARCHAR2(16),
   DURATION             NUMBER(2),
   DURATION_UNITS       VARCHAR2(4),
   START_DATE_TIME      TIMESTAMP,
   STOP_DATE_TIME       TIMESTAMP,
   FREQUENCY            VARCHAR2(16),
   FREQ_COUNTER         NUMBER(2),
   FREQ_INTERVAL        NUMBER(2),
   FREQ_INTERVAL_UNIT   VARCHAR2(4),
   FREQ_DETAIL          VARCHAR2(80),
   PERFORM_SCHEDULE     VARCHAR2(16),
   PERFORM_RESULT       VARCHAR2(8),
   ORDERING_DEPT        VARCHAR2(8),
   DOCTOR               VARCHAR2(20),
   STOP_DOCTOR          VARCHAR2(20),
   NURSE                VARCHAR2(20),
   STOP_NURSE           VARCHAR2(20),
   ENTER_DATE_TIME      TIMESTAMP,
   STOP_ORDER_DATE_TIME TIMESTAMP,
   ORDER_STATUS         VARCHAR2(1),
   DRUG_BILLING_ATTR    NUMBER(1),
   BILLING_ATTR         NUMBER(1),
   LAST_PERFORM_DATE_TIME TIMESTAMP,
   LAST_ACCTING_DATE_TIME TIMESTAMP,
   CURRENT_PRESC_NO     NUMBER(5),
   DOCTOR_USER          VARCHAR2(16),
   VERIFY_DATA_TIME     TIMESTAMP,
   ORDER_PRINT_INDICATOR NUMBER(1),
   PROCESSING_DATE_TIME TIMESTAMP,
   PROCESSING_NURSE     VARCHAR2(20),
   STOP_PROCESSING_NURSE VARCHAR2(20),
   STOP_PROCESSING_DATE_TIME TIMESTAMP,
   CANCEL_DATE_TIME     TIMESTAMP,
   CANCEL_DOCTOR        VARCHAR2(20),
   DEGREE               NUMBER(2),
   APP_NO               VARCHAR2(20),
   CONTINUE_ORDER_NO    VARCHAR2(20),
   CONVERSION_DATE_TIME TIMESTAMP,
   IS_ADJUST            NUMBER(1),
   AMOUNT               NUMBER,
   PHAM_STD_CODE        VARCHAR2(7),
   STOP_FLAG            VARCHAR2(1),
   ADAPT_SYMPTOM_INDICATE NUMBER(1),
   INSUR_FLAG           VARCHAR2(20),
   EXEC_OPERATOR        VARCHAR2(20),
   EXEC_DATE_TIME       TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATA          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATA          TIMESTAMP,
   REMARKS              VARCHAR2(200),
  DEL_FLAG             char(1) default '0',
   constraint PK_ORDERS primary key (ID)
        );

comment on table ORDERS is
'医嘱';
comment on column ORDERS.ID is
'主键';

comment on column ORDERS.PATIENT_ID is
'病人标识号';

comment on column ORDERS.ORG_ID is
'机构ID';

comment on column ORDERS.PRESC_MASTER_ID is
'处方/检查/检验等通用申请表ID';

comment on column ORDERS.VISIT_ID is
'住院标识';

comment on column ORDERS.ORDER_NO is
'医嘱序号';

comment on column ORDERS.ORDER_SUB_NO is
'医嘱子序号';

comment on column ORDERS.REPEAT_INDICATOR is
'长期医嘱标志';

comment on column ORDERS.ORDER_CLASS is
'医嘱类别';

comment on column ORDERS.ORDER_TEXT is
'医嘱正文';

comment on column ORDERS.ORDER_CODE is
'医嘱代码';

comment on column ORDERS.DOSAGE is
'药品一次使用剂量';

comment on column ORDERS.DOSAGE_UNITS is
'剂量单位';

comment on column ORDERS.ADMINISTRATION is
'给药途径和方法';

comment on column ORDERS.DURATION is
'持续时间';

comment on column ORDERS.DURATION_UNITS is
'停止日期及时间';

comment on column ORDERS.START_DATE_TIME is
'起始日期及时间';

comment on column ORDERS.STOP_DATE_TIME is
'停止日期及时间';

comment on column ORDERS.FREQUENCY is
'执行频率描述';

comment on column ORDERS.FREQ_COUNTER is
'频率次数';

comment on column ORDERS.FREQ_INTERVAL is
'频率间隔';

comment on column ORDERS.FREQ_INTERVAL_UNIT is
'频率间隔单位';

comment on column ORDERS.FREQ_DETAIL is
'执行时间详细描述';

comment on column ORDERS.PERFORM_SCHEDULE is
'护士执行时间';

comment on column ORDERS.PERFORM_RESULT is
'执行结果';

comment on column ORDERS.ORDERING_DEPT is
'开医嘱科室';

comment on column ORDERS.DOCTOR is
'开医嘱医生';

comment on column ORDERS.STOP_DOCTOR is
'停医嘱医生';

comment on column ORDERS.NURSE is
'开医嘱校对护士';

comment on column ORDERS.STOP_NURSE is
'停医嘱校对护士';

comment on column ORDERS.ENTER_DATE_TIME is
'开医嘱录入日期及时间';

comment on column ORDERS.STOP_ORDER_DATE_TIME is
'停医嘱录入日期及时间';

comment on column ORDERS.ORDER_STATUS is
'医嘱状态';

comment on column ORDERS.DRUG_BILLING_ATTR is
'药品计价属性';

comment on column ORDERS.BILLING_ATTR is
'计价属性';

comment on column ORDERS.LAST_PERFORM_DATE_TIME is
'最后一次执行日期及时间';

comment on column ORDERS.LAST_ACCTING_DATE_TIME is
'最后一次计价日期及时间';

comment on column ORDERS.CURRENT_PRESC_NO is
'对应处方号';

comment on column ORDERS.DOCTOR_USER is
'医生代码';

comment on column ORDERS.VERIFY_DATA_TIME is
'校对时间';

comment on column ORDERS.ORDER_PRINT_INDICATOR is
'医嘱本打印标志';

comment on column ORDERS.CREATE_BY is
'创建人';

comment on column ORDERS.CREATE_DATA is
'创建时间';

comment on column ORDERS.UPDATE_BY is
'更新人';

comment on column ORDERS.UPDATE_DATA is
'更新时间';

comment on column ORDERS.REMARKS is
'备注';

comment on column ORDERS.DEL_FLAG is
'是否删除';