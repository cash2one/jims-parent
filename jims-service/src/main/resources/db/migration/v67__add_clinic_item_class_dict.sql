-- drop table CLINIC_ITEM_CLASS_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: CLINIC_ITEM_CLASS_DICT      诊疗项目分类字典                                */
/*==============================================================*/
create table CLINIC_ITEM_CLASS_DICT
(
ID VARCHAR2(64 CHAR) not null,
SERIAL_NO  NUMBER(2),
CLASS_CODE VARCHAR2(1  CHAR) not null,
CLASS_NAME VARCHAR2(10  CHAR),
INPUT_CODE VARCHAR2(8  CHAR),
REMARKS VARCHAR2(2000  CHAR),
UPDATE_BY VARCHAR2(64  CHAR),
CREATE_BY VARCHAR2(64  CHAR),
UPDATE_DATE TIMESTAMP(6),
DEL_FLAG VARCHAR2(2  CHAR),
CREATE_DATE TIMESTAMP(6),
constraint "PK_CLINIC_ITEM_CLASS_DICT" primary key (ID)
);

-- Add comments to the table
comment on table CLINIC_ITEM_CLASS_DICT
is '诊疗项目分类字典';
-- Add comments to the columns
comment on column CLINIC_ITEM_CLASS_DICT.SERIAL_NO
is '序号';
comment on column CLINIC_ITEM_CLASS_DICT.CLASS_CODE
is '项目类别代码';
comment on column CLINIC_ITEM_CLASS_DICT.CLASS_NAME
is '项目类别名称';
comment on column CLINIC_ITEM_CLASS_DICT.INPUT_CODE
is '输入码';

 
