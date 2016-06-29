/*==============================================================*/
/* Table: CLINIC_APPOINTS                 门诊预约记录        */
/*==============================================================*/
create table CLINIC_APPOINTS
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   VISIT_DATE_APPTED    TIMESTAMP,
   CLINIC_LABEL         VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   VISIT_TIME_APPTED    VARCHAR2(64),
   APPT_MADE_DATE       TIMESTAMP,
   MODE_CODE            CHAR,
   CARD_NAME            VARCHAR2(64),
   CARD_NO              VARCHAR2(20),
   SERIAL_NO            NUMBER(3),
   NAME                 VARCHAR2(100 char),
   SEX                  VARCHAR2(4),
   AGE                  NUMBER(16),
   ID_NO                VARCHAR2(64),
   IDENTITY             VARCHAR2(64),
   CHARGE_TYPE          VARCHAR2(64),
   INSURANCE_NO         VARCHAR2(18),
   INSURANCE_TYPE       VARCHAR2(16),
   UNIT_IN_CONTRACT     VARCHAR2(64),
   NAME_PHONETIC        VARCHAR2(16),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char                 default '0',
   VISIT_INDICATOR       VARCHAR2(64),
   VISIT_DEPT              VARCHAR2(64),
   constraint PK_CLINIC_APPOINTS primary key (ID)
);

comment on column CLINIC_APPOINTS.ID is
'主键';

comment on column CLINIC_APPOINTS.ORG_ID is
'医院ID';

comment on column CLINIC_APPOINTS.VISIT_DATE_APPTED is
'就诊日期';

comment on column CLINIC_APPOINTS.CLINIC_LABEL is
'号别';

comment on column CLINIC_APPOINTS.PATIENT_ID is
'病人标识号';

comment on column CLINIC_APPOINTS.VISIT_TIME_APPTED is
'预约就诊时间';

comment on column CLINIC_APPOINTS.APPT_MADE_DATE is
'何日预约';

comment on column CLINIC_APPOINTS.MODE_CODE is
'预约模式';

comment on column CLINIC_APPOINTS.CARD_NAME is
'卡名';

comment on column CLINIC_APPOINTS.CARD_NO is
'卡号';

comment on column CLINIC_APPOINTS.SERIAL_NO is
'流水号';

comment on column CLINIC_APPOINTS.NAME is
'姓名';

comment on column CLINIC_APPOINTS.SEX is
'性别';

comment on column CLINIC_APPOINTS.AGE is
'年龄';

comment on column CLINIC_APPOINTS.ID_NO is
'身份证号';

comment on column CLINIC_APPOINTS.IDENTITY is
'身份';

comment on column CLINIC_APPOINTS.CHARGE_TYPE is
'费别';

comment on column CLINIC_APPOINTS.INSURANCE_NO is
'保险号码';

comment on column CLINIC_APPOINTS.INSURANCE_TYPE is
'保险类型';

comment on column CLINIC_APPOINTS.UNIT_IN_CONTRACT is
'合同单位';

comment on column CLINIC_APPOINTS.NAME_PHONETIC is
'姓名拼音码';

comment on column CLINIC_APPOINTS.CREATE_DATE is
'创建日期';

comment on column CLINIC_APPOINTS.CREATE_BY is
'创建人';

comment on column CLINIC_APPOINTS.UPDATE_DATE is
'更新日期';

comment on column CLINIC_APPOINTS.UPDATE_BY is
'更新人';

comment on column CLINIC_APPOINTS.REMARKS is
'备注信息';

comment on column CLINIC_APPOINTS.DEL_FLAG is
'删除标志';

comment on column CLINIC_APPOINTS.VISIT_INDICATOR is
'诊别';

comment on column CLINIC_APPOINTS.VISIT_DEPT is
'就诊科室';
