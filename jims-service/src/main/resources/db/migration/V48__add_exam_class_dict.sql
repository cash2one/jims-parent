-- Create table
create table EXAM_CLASS_DICT
(
  ID               VARCHAR2(64) not null,
  ORG_ID           VARCHAR2(64),
  SERIAL_NO        NUMBER(2),
  EXAM_CLASS_CODE  VARCHAR2(1),
  EXAM_CLASS_NAME  VARCHAR2(20) not null,
  INPUT_CODE       VARCHAR2(8),
  PERFORM_BY       VARCHAR2(64),
  PRINT_STYLE      VARCHAR2(6),
  SPECIALTIES_DEPT NUMBER(1),
  LOACAL_ID_CLASS  VARCHAR2(5),
  WARD_CODE        VARCHAR2(64),
  HTTP_IP          VARCHAR2(100),
  MEMO             VARCHAR2(5),
  OUTP_PERFORM     VARCHAR2(5),
  REMARKS          VARCHAR2(2000),
  UPDATE_BY        VARCHAR2(64),
  CREATE_BY        VARCHAR2(64),
  UPDATE_DATE      TIMESTAMP(6),
  DEL_FLAG         VARCHAR2(2),
  CREATE_DATE      TIMESTAMP(6)
);
-- Add comments to the table
comment on table EXAM_CLASS_DICT
  is '检查项目类别表';
-- Add comments to the columns
comment on column EXAM_CLASS_DICT.ID
  is '主键';
comment on column EXAM_CLASS_DICT.ORG_ID
  is '组织机构';
comment on column EXAM_CLASS_DICT.SERIAL_NO
  is '序号';
comment on column EXAM_CLASS_DICT.EXAM_CLASS_CODE
  is '检查类别代码';
comment on column EXAM_CLASS_DICT.EXAM_CLASS_NAME
  is '检查类别名称';
comment on column EXAM_CLASS_DICT.INPUT_CODE
  is '输入码';
comment on column EXAM_CLASS_DICT.PERFORM_BY
  is '执行科室';
comment on column EXAM_CLASS_DICT.PRINT_STYLE
  is '打印方式';
comment on column EXAM_CLASS_DICT.SPECIALTIES_DEPT
  is '特殊科室';
comment on column EXAM_CLASS_DICT.WARD_CODE
  is '护理单元';
comment on column EXAM_CLASS_DICT.HTTP_IP
  is '结果反馈地址';
comment on column EXAM_CLASS_DICT.OUTP_PERFORM
  is '适用于门诊检查不可选控制';
alter table EXAM_CLASS_DICT
  add constraint EXAM_CLASS_DICT_PK primary key (ID);
