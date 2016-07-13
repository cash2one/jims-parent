
/*==============================================================*/
/* Table: OUTP_PRESC         处方医嘱明细记录                                   */
/*==============================================================*/
create table OUTP_PRESC
(
   ID                   VARCHAR2(64 CHAR)    not null,
   ORG_ID                  VARCHAR2(64 CHAR),
  CLINIC_ID              VARCHAR2(64),
  PATIENT_ID          VARCHAR2(64 CHAR),
   VISIT_DATE           TIMESTAMP,
   VISIT_NO             NUMBER(5),
   SERIAL_NO            VARCHAR2(64 CHAR),
   PRESC_NO             NUMBER(5),
   ITEM_NO              NUMBER(2),
   ITEM_CLASS           VARCHAR2(64 CHAR),
   DRUG_CODE            VARCHAR2(64 CHAR),
   DRUG_NAME            VARCHAR2(100 CHAR),
   DRUG_SPEC            VARCHAR2(64 CHAR),
   FIRM_ID              VARCHAR2(100 CHAR),
   UNITS                VARCHAR2(64 CHAR),
   AMOUNT               NUMBER(8,4),
   DOSAGE               NUMBER(15,4),
   DOSAGE_UNITS         VARCHAR2(64 CHAR),
   ADMINISTRATION       VARCHAR2(100 CHAR),
   FREQUENCY            VARCHAR2(64 CHAR),
   PROVIDED_INDICATOR   NUMBER(1),
   COSTS                NUMBER(10,4),
   CHARGES              NUMBER(10,4),
   CHARGE_INDICATOR     NUMBER(1),
   DISPENSARY           VARCHAR2(64 CHAR),
   REPETITION           NUMBER(2),
   ORDER_NO             NUMBER(2),
   SUB_ORDER_NO         NUMBER(2),
   FREQ_DETAIL          VARCHAR2(80 CHAR),
   GETDRUG_FLAG         NUMBER(2),
   PRESC_ATTR           VARCHAR2(100 CHAR),
   ABIDANCE             NUMBER(3),
   PERFORM_NURSE        VARCHAR2(20 CHAR),
   PERFORM_RESULT       VARCHAR2(64 CHAR),
   SKIN_FLAG            VARCHAR2(64 CHAR),
   PRESC_PSNO           NUMBER(2),
   SKIN_RESULT          VARCHAR2(64 CHAR),
   DISPENSARY_SUB       VARCHAR2(64 CHAR),
   DECOCTION            NUMBER(1),
   DRUG_PRESC_DATE      TIMESTAMP,
   DRUG_PRESC_NO        NUMBER(5),
   NEWVISIT_DATE        TIMESTAMP,
   NEWVISIT_NO          NUMBER(5),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 CHAR),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 CHAR),
   REMARKS              VARCHAR2(200 CHAR),
   DEL_FLAG             char(1) DEFAULT  '0',
    constraint "PK_OUTP_PRESC" primary key (ID)
)
;

comment on table OUTP_PRESC is
'处方医嘱明细记录';

comment on column OUTP_PRESC.ID is
'处方医嘱明细记录';

comment on column OUTP_PRESC.VISIT_DATE is
'就诊日期';

comment on column OUTP_PRESC.VISIT_NO is
'就诊序号';

comment on column OUTP_PRESC.SERIAL_NO is
'流水号';

comment on column OUTP_PRESC.PRESC_NO is
'处方序号';

comment on column OUTP_PRESC.ITEM_NO is
'项目序号';

comment on column OUTP_PRESC.ITEM_CLASS is
'项目类别';

comment on column OUTP_PRESC.DRUG_CODE is
'药名编码';

comment on column OUTP_PRESC.DRUG_NAME is
'药品名称';

comment on column OUTP_PRESC.DRUG_SPEC is
'药品规格';

comment on column OUTP_PRESC.FIRM_ID is
'厂家标识';

comment on column OUTP_PRESC.UNITS is
'单位';

comment on column OUTP_PRESC.AMOUNT is
'数量';

comment on column OUTP_PRESC.DOSAGE is
'一次用量';

comment on column OUTP_PRESC.DOSAGE_UNITS is
'用量单位';

comment on column OUTP_PRESC.ADMINISTRATION is
'用药途径';

comment on column OUTP_PRESC.FREQUENCY is
'频次';

comment on column OUTP_PRESC.PROVIDED_INDICATOR is
'自备标记';

comment on column OUTP_PRESC.COSTS is
'计价金额';

comment on column OUTP_PRESC.CHARGES is
'实收费用';

comment on column OUTP_PRESC.CHARGE_INDICATOR is
'收费标记';

comment on column OUTP_PRESC.DISPENSARY is
'摆药药局';

comment on column OUTP_PRESC.FREQ_DETAIL is
'执行时间详细描述';

comment on column OUTP_PRESC.GETDRUG_FLAG is
'取药标志';
comment on column OUTP_PRESC.DECOCTION is
 '处方代煎';
comment on column OUTP_PRESC.DRUG_PRESC_DATE is
'发药处方日期';
comment on column OUTP_PRESC.DRUG_PRESC_NO is
'发药处方号';
comment on column OUTP_PRESC.CREATE_DATE is
'创建日期';
comment on column OUTP_PRESC.CREATE_BY is
'创建人';

comment on column OUTP_PRESC.UPDATE_DATE is
'修改日期';

comment on column OUTP_PRESC.UPDATE_BY is
'更新人';

comment on column OUTP_PRESC.REMARKS is
'备注信息';

comment on column OUTP_PRESC.DEL_FLAG is
'删除标志';