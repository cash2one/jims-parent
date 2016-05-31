create table PAT_HOSPITAL_NOTICE
(
  PATIENT_ID           VARCHAR2(10) not null,
  NAME                 VARCHAR2(20),
  SEX                  VARCHAR2(4),
  AGE                  VARCHAR2(10),
  DATE_OF_BIRTH        DATE,
  MARITAL_STATUS       VARCHAR2(4),
  OCCUPATION           VARCHAR2(4),
  NATION               VARCHAR2(10),
  ID_NO                VARCHAR2(18),
  CHILDREN             NUMBER(1),
  BIRTH_PLACE          VARCHAR2(100),
  SERVICE_AGENCY       VARCHAR2(40),
  SERVICE_AGENCY_PHONE VARCHAR2(16),
  INSURANCE_AERA       VARCHAR2(100),
  INSURANCE_AERA_PHONE VARCHAR2(16),
  NEXT_OF_KIN          VARCHAR2(20),
  RELATIONSHIP         VARCHAR2(2),
  NEXT_OF_NATION       VARCHAR2(10),
  NEXT_OF_ID_NO        VARCHAR2(18),
  NEXT_OF_KIN_ADDR     VARCHAR2(100),
  ADMISSION_DATE_TIME  DATE,
  PREPAID_FEE          NUMBER(9,2),
  PAT_ADM_CONDITION    VARCHAR2(1),
  DIAGNOSIS_DESC       VARCHAR2(250),
  DEPT_ADMISSION_TO    VARCHAR2(8),
  BED_NO               NUMBER(8),
  STRICT_SEGREGATION   VARCHAR2(20),
  COMMON_SEGREGATION   VARCHAR2(20),
  NOT_SEGREGATION      VARCHAR2(20),
  NOTES                VARCHAR2(300),
  NOTICE_ID            NUMBER(3) not null,
  VISIT_ID             NUMBER(2),
  OPERATOR             VARCHAR2(8),
  ENTER_DATE           DATE,
  ONSET_DATE           DATE,
  PARITY_NO            NUMBER(2),
  CHARGE_TYPE          VARCHAR2(8),
  DEPT_ADMISSION_FROM  VARCHAR2(8),
  CREATE_DATE                TIMESTAMP,
  CREATE_BY                  VARCHAR2(64 char),
  UPDATE_BY                  VARCHAR2(64 char),
  UPDATE_DATE                TIMESTAMP,
  REMARKS                    VARCHAR2(200 char),
  DEL_FLAG                   NUMBER(1) default 0,
  constraint PK_PAT_HOSPITAL_NOTICE primary key (PATIENT_ID)
);

comment on table PAT_HOSPITAL_NOTICE
  is '住院通知单表';
comment on column PAT_HOSPITAL_NOTICE.PATIENT_ID
  is '病人ID';
comment on column PAT_HOSPITAL_NOTICE.NAME
  is '姓名';
comment on column PAT_HOSPITAL_NOTICE.SEX
  is '性别';
comment on column PAT_HOSPITAL_NOTICE.AGE
  is '年龄';
comment on column PAT_HOSPITAL_NOTICE.DATE_OF_BIRTH
  is '出生日期';
comment on column PAT_HOSPITAL_NOTICE.MARITAL_STATUS
  is '婚姻';
comment on column PAT_HOSPITAL_NOTICE.OCCUPATION
  is '职业';
comment on column PAT_HOSPITAL_NOTICE.NATION
  is '民族';
comment on column PAT_HOSPITAL_NOTICE.ID_NO
  is '身份证号';
comment on column PAT_HOSPITAL_NOTICE.CHILDREN
  is '儿童';
comment on column PAT_HOSPITAL_NOTICE.BIRTH_PLACE
  is '出生地';
comment on column PAT_HOSPITAL_NOTICE.SERVICE_AGENCY
  is '工作单位';
comment on column PAT_HOSPITAL_NOTICE.SERVICE_AGENCY_PHONE
  is '电话';
comment on column PAT_HOSPITAL_NOTICE.INSURANCE_AERA
  is '现住址';
comment on column PAT_HOSPITAL_NOTICE.INSURANCE_AERA_PHONE
  is '电话';
comment on column PAT_HOSPITAL_NOTICE.NEXT_OF_KIN
  is '联系人';
comment on column PAT_HOSPITAL_NOTICE.RELATIONSHIP
  is '关系';
comment on column PAT_HOSPITAL_NOTICE.NEXT_OF_NATION
  is '联系人民族';
comment on column PAT_HOSPITAL_NOTICE.NEXT_OF_ID_NO
  is '联系人身份证';
comment on column PAT_HOSPITAL_NOTICE.NEXT_OF_KIN_ADDR
  is '联系人地址';
comment on column PAT_HOSPITAL_NOTICE.ADMISSION_DATE_TIME
  is '入院日期';
comment on column PAT_HOSPITAL_NOTICE.PREPAID_FEE
  is '预交住院费';
comment on column PAT_HOSPITAL_NOTICE.PAT_ADM_CONDITION
  is '住院情况';
comment on column PAT_HOSPITAL_NOTICE.DIAGNOSIS_DESC
  is '门诊诊断';
comment on column PAT_HOSPITAL_NOTICE.DEPT_ADMISSION_TO
  is '入院科室';
comment on column PAT_HOSPITAL_NOTICE.BED_NO
  is '床号';
comment on column PAT_HOSPITAL_NOTICE.STRICT_SEGREGATION
  is '严格隔离';
comment on column PAT_HOSPITAL_NOTICE.COMMON_SEGREGATION
  is '普通隔离';
comment on column PAT_HOSPITAL_NOTICE.NOT_SEGREGATION
  is '不隔离';
comment on column PAT_HOSPITAL_NOTICE.NOTES
  is '注意事项';
comment on column PAT_HOSPITAL_NOTICE.NOTICE_ID
  is '通知单次数';
comment on column PAT_HOSPITAL_NOTICE.VISIT_ID
  is '住院标识';
comment on column PAT_HOSPITAL_NOTICE.OPERATOR
  is '录入人';
comment on column PAT_HOSPITAL_NOTICE.ENTER_DATE
  is '录入时间';
comment on column PAT_HOSPITAL_NOTICE.ONSET_DATE
  is '发病日期';
comment on column PAT_HOSPITAL_NOTICE.PARITY_NO
  is '胎次';
comment on column PAT_HOSPITAL_NOTICE.CHARGE_TYPE
  is '费别';
comment on column PAT_HOSPITAL_NOTICE.DEPT_ADMISSION_FROM
  is '申请科室';














