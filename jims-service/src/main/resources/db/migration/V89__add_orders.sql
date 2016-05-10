  -- Create table
/*==============================================================*/
/* Table: ORDERS      医嘱                                */
/*==============================================================*/
  create table ORDERS 
(
   ID                   varchar2(64)                   not null,
   PATIENT_ID           varchar2(64)                   null,
   VISIT_ID             number(11)                     null,
   ORDER_NO             number(11)                     null,
   ORDER_SUB_NO         number(11)                     null,
   REPEAT_INDICATOR     char(1)                        null,
   ORDER_CLASS          char(1)                        null,
   ORDER_TEXT           varchar2(200)                  null,
   ORDER_CODE           varchar2(64)                   null,
   DOSAGE               number(11,4)                   null,
   DOSAGE_UNITS         varchar2(8)                    null,
   ADMINISTRATION       varchar2(64)                   null,
   DURATION             number(4)                      null,
   START_DATE_TIME      TIMESTAMP                      null,
   STOP_DATE_TIME       TIMESTAMP                      null,
   FREQUENCY            varchar2(16)                   null,
   FREQ_COUNTER         number(2)                      null,
   FREQ_INTERVAL        number(2)                      null,
   FREQ_INTERVAL_UNIT   varchar2(4)                    null,
   FREQ_DETAIL          varchar2(80)                   null,
   PERFORM_SCHEDULE     varchar2(16)                   null,
   PERFORM_RESULT       varchar2(8)                    null,
   ORDERING_DEPT        varchar2(8)                    null,
   DOCTOR               varchar2(8)                    null,
   STOP_DOCTOR          varchar2(8)                    null,
   NURSE                varchar2(8)                    null,
   STOP_NURSE           varchar2(8)                    null,
   ENTER_DATE_TIME      TIMESTAMP                      null,
   STOP_ORDER_DATE_TIME TIMESTAMP                      null,
   ORDER_STATUS         char(1)                        null,
   DRUG_BILLING_ATTR    number(2)                      null,
   BILLING_ATTR         number(2)                      null,
   LAST_PERFORM_DATE_TIME TIMESTAMP                      null,
   "TIME"               TIMESTAMP                      null,
   LAST_ACCTING_DATE_TIME TIMESTAMP                      null,
   CURRENT_PRESC_NO     number(5)                      null,
   DOCTOR_USER          number(16)                     null,
   VERIFY_DATA_TIME     TIMESTAMP                      null,
   ORDER_PRINT_INDICATOR number(11)                     null,
   PROCESSION_DATE_TIME TIMESTAMP                      null,
   PROCESSION_NURSE     number(8)                      null,
   STOP_PROCESSION_DATE_TIME TIMESTAMP                      null,
   STOP_PROCESSION_NURSE number(8)                      null,
   CANCEL_DATE_TIME     TIMESTAMP                      null,
   CANCEL_DOCTOR        varchar2(8)                    null,
   DEGREE               number(2)                      null,
   APP_NO               varchar2(20)                   null,
   IS_ADJUST            number(1)                      null,
   CONVERSION_DATE_TIME TIMESTAMP                      null,
   CONTINUE_ORDER_NO    varchar2(20)                   null,
   STOP_FLAG            varchar2(21)                   null,
   ADAPT_SYMPTOM_INDICATE char(1)                        null,
   DUTY_DOCTOR          varchar2(20)                   null,
   CREATE_BY            varchar2(20)                   null,
   CREATE_DATE          TIMESTAMP                      null,
   UPDATE_BY            varchar2(20)                   null,
   UPDATE_DATE          TIMESTAMP                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(200)                  null,
   constraint PK_ORDERS primary key(ID)
);

comment on table ORDERS is 
'医嘱';

comment on column ORDERS.ID is 
'医嘱主键';

comment on column ORDERS.PATIENT_ID is 
'病人标识号';

comment on column ORDERS.VISIT_ID is 
'病人本次住院标识';

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

comment on column ORDERS."TIME" is 
'摆药时，将摆药的截止日期(自动填入)';

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

comment on column ORDERS.PROCESSION_DATE_TIME is 
'转抄时间';

comment on column ORDERS.PROCESSION_NURSE is 
'转抄护士';

comment on column ORDERS.STOP_PROCESSION_DATE_TIME is 
'停止医嘱转抄时间';

comment on column ORDERS.STOP_PROCESSION_NURSE is 
'停止医嘱转抄护士';

comment on column ORDERS.CANCEL_DATE_TIME is 
'作废时间';

comment on column ORDERS.CANCEL_DOCTOR is 
'作废医生';

comment on column ORDERS.DEGREE is 
'?????';

comment on column ORDERS.APP_NO is 
'检验申请号';

comment on column ORDERS.IS_ADJUST is 
'是否需要手工调整执行单';

comment on column ORDERS.CONVERSION_DATE_TIME is 
'执行单生成日期';

comment on column ORDERS.CONTINUE_ORDER_NO is 
'续静滴途径名';

comment on column ORDERS.STOP_FLAG is 
'停止医嘱标志';

comment on column ORDERS.ADAPT_SYMPTOM_INDICATE is 
'适应症标志';

comment on column ORDERS.DUTY_DOCTOR is 
'责任医师';

comment on column ORDERS.CREATE_BY is 
'创建人';

comment on column ORDERS.CREATE_DATE is 
'创建时间';

comment on column ORDERS.UPDATE_BY is 
'修改人';

comment on column ORDERS.UPDATE_DATE is 
'修改时间';

comment on column ORDERS.DEL_FLAG is 
'删除标记';

comment on column ORDERS.REMARKS is 
'备注信息';
