-- drop table SPECIMAN_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: SPECIMAN_DICT      标本字典                                */
/*==============================================================*/
create table SPECIMAN_DICT
(
ID VARCHAR2(64 CHAR) not null,
SERIAL_NO     NUMBER(2),
SPECIMAN_CODE VARCHAR2(2  CHAR) not null,
SPECIMAN_NAME VARCHAR2(8  CHAR),
INPUT_CODE    VARCHAR2(8  CHAR),
DEPT_CODE     VARCHAR2(8  CHAR),
REMARKS VARCHAR2(2000  CHAR),
UPDATE_BY VARCHAR2(64  CHAR),
CREATE_BY VARCHAR2(64  CHAR),
UPDATE_DATE TIMESTAMP(6),
DEL_FLAG VARCHAR2(2  CHAR),
CREATE_DATE TIMESTAMP(6),
constraint "PK_SPECIMAN_DICT" primary key (ID)
);
-- Add comments to the table
comment on table SPECIMAN_DICT
is '标本字典';
-- Add comments to the columns
comment on column SPECIMAN_DICT.SERIAL_NO
is '序号';
comment on column SPECIMAN_DICT.SPECIMAN_CODE
is '标本代码';
comment on column SPECIMAN_DICT.SPECIMAN_NAME
is '标本名称';
comment on column SPECIMAN_DICT.INPUT_CODE
is '输入码';
comment on column SPECIMAN_DICT.DEPT_CODE
is '科室代码';
