drop table PAT_MASTER_INDEX cascade constraints;

/*==============================================================*/
/* Table: PAT_MASTER_INDEX     病人主索引                                 */
/*==============================================================*/
create table PAT_MASTER_INDEX
(
  ID                    VARCHAR2(64 CHAR) not null,
  HOSID                 VARCHAR2(64 CHAR),
  INP_NO                VARCHAR2(10 CHAR),
  NAME                  VARCHAR2(20 CHAR),
  NAME_PHONETIC         VARCHAR2(16 CHAR),
  SEX                   VARCHAR2(4 CHAR),
  DATE_OF_BIRTH         DATE,
  BIRTH_PLACE           VARCHAR2(100 CHAR),
  CITIZENSHIP           VARCHAR2(2 CHAR),
  NATION                VARCHAR2(10 CHAR),
  ID_NO                 VARCHAR2(18 CHAR),
  IDENTITY              VARCHAR2(10 CHAR),
  CHARGE_TYPE           VARCHAR2(8 CHAR),
  UNIT_IN_CONTRACT      VARCHAR2(40 CHAR),
  MAILING_ADDRESS       VARCHAR2(200 CHAR),
  ZIP_CODE              VARCHAR2(6 CHAR),
  PHONE_NUMBER_HOME     VARCHAR2(16 CHAR),
  PHONE_NUMBER_BUSINESS VARCHAR2(16 CHAR),
  NEXT_OF_KIN           VARCHAR2(20 CHAR),
  RELATIONSHIP          VARCHAR2(2 CHAR),
  NEXT_OF_KIN_ADDR      VARCHAR2(100 CHAR),
  NEXT_OF_KIN_ZIP_CODE  VARCHAR2(6 CHAR),
  NEXT_OF_KIN_PHONE     VARCHAR2(16 CHAR),
  LAST_VISIT_DATE       DATE,
  VIP_INDICATOR         NUMBER(1),
  CREATE_DATE           DATE,
  OPERATOR              VARCHAR2(20 CHAR),
  SERVICE_AGENCY        VARCHAR2(40 CHAR),
  BUSINESS_ZIP_CODE     VARCHAR2(6 CHAR),
  PHOTO                 VARCHAR2(20 CHAR),
  PATIENT_CLASS         VARCHAR2(1 CHAR),
  DEGREE                VARCHAR2(10 CHAR),
  RACE                  VARCHAR2(10 CHAR),
  RELIGION              VARCHAR2(16 CHAR),
  MOTHER_LANGUAGE       VARCHAR2(16 CHAR),
  FOREIGN_LANGUAGE      VARCHAR2(16 CHAR),
  ID_TYPE               VARCHAR2(10 CHAR),
  VIP_NO                VARCHAR2(18 CHAR),
  E_NAME                VARCHAR2(100 CHAR),
  OCCUPATION            VARCHAR2(100 CHAR),
  NEXT_OF_ID            VARCHAR2(18 CHAR),
  NEXT_OF_BATH          VARCHAR2(18 CHAR),
  NEXT_OF_SEX           VARCHAR2(18 CHAR),
  INSUR_NH_NO           VARCHAR2(18 CHAR),
  INSURANCE_NO          VARCHAR2(18 CHAR),
  ALERGY_DRUGS          VARCHAR2(100 CHAR),
  NATIVE_PLACE          VARCHAR2(6 CHAR),
  MAILING_ADDRESS_CODE  VARCHAR2(30 CHAR),
  HEALTHY_CARD_NO       VARCHAR2(30 CHAR),
  ADDRESS_NOW           VARCHAR2(200 CHAR)
)
-- Add comments to the table
comment on table PAT_MASTER_INDEX
  is '病人主索引';
-- Add comments to the columns
comment on column PAT_MASTER_INDEX.ID
  is '病人标识号';
comment on column PAT_MASTER_INDEX.HOSID
  is '医院ID';
comment on column PAT_MASTER_INDEX.INP_NO
  is '住院号';
comment on column PAT_MASTER_INDEX.NAME
  is '姓名';
comment on column PAT_MASTER_INDEX.NAME_PHONETIC
  is '姓名拼音';
comment on column PAT_MASTER_INDEX.SEX
  is '性别';
comment on column PAT_MASTER_INDEX.DATE_OF_BIRTH
  is '出生日期';
comment on column PAT_MASTER_INDEX.BIRTH_PLACE
  is '出生地';
comment on column PAT_MASTER_INDEX.CITIZENSHIP
  is '国籍';
comment on column PAT_MASTER_INDEX.NATION
  is '民族';
comment on column PAT_MASTER_INDEX.ID_NO
  is '身份证号';
comment on column PAT_MASTER_INDEX.IDENTITY
  is '身份';
comment on column PAT_MASTER_INDEX.CHARGE_TYPE
  is '费别';
comment on column PAT_MASTER_INDEX.UNIT_IN_CONTRACT
  is '合同单位';
comment on column PAT_MASTER_INDEX.MAILING_ADDRESS
  is '（通信地址）户口地址';
comment on column PAT_MASTER_INDEX.ZIP_CODE
  is '邮政编码';
comment on column PAT_MASTER_INDEX.PHONE_NUMBER_HOME
  is '家庭电话号码';
comment on column PAT_MASTER_INDEX.PHONE_NUMBER_BUSINESS
  is '单位电话号码';
comment on column PAT_MASTER_INDEX.NEXT_OF_KIN
  is '联系人姓名';
comment on column PAT_MASTER_INDEX.RELATIONSHIP
  is '与联系人关系';
comment on column PAT_MASTER_INDEX.NEXT_OF_KIN_ADDR
  is '联系人地址';
comment on column PAT_MASTER_INDEX.NEXT_OF_KIN_ZIP_CODE
  is '联系人邮政编码';
comment on column PAT_MASTER_INDEX.NEXT_OF_KIN_PHONE
  is '联系人电话号码';
comment on column PAT_MASTER_INDEX.LAST_VISIT_DATE
  is '上次就诊日期';
comment on column PAT_MASTER_INDEX.VIP_INDICATOR
  is '重要人物标志';
comment on column PAT_MASTER_INDEX.CREATE_DATE
  is '建卡日期';
comment on column PAT_MASTER_INDEX.OPERATOR
  is '操作员';
comment on column PAT_MASTER_INDEX.SERVICE_AGENCY
  is '医疗体系病人标志';
comment on column PAT_MASTER_INDEX.PHOTO
  is 'UUF,';
comment on column PAT_MASTER_INDEX.MAILING_ADDRESS_CODE
  is '户口地址行政区划';
comment on column PAT_MASTER_INDEX.ADDRESS_NOW
  is '现住址';