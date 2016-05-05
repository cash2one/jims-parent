-- drop table LAB_ITEM_CLASS_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: LAB_ITEM_CLASS_DICT      检验项目类别字典             */
/* CREATE_DATE: 2016-05-05 13:33                                */
/* CREATE_BY :  xueyx                                           */
/*==============================================================*/
create table LAB_ITEM_CLASS_DICT
(
ID VARCHAR2(64 CHAR) not null,
SERIAL_NO  NUMBER(2),
CLASS_CODE VARCHAR2(8 CHAR) not null,
CLASS_NAME VARCHAR2(20 CHAR),
DEPT_CODE  VARCHAR2(8 CHAR) not null  ,
REMARKS VARCHAR2(2000  CHAR),
UPDATE_BY VARCHAR2(64  CHAR),
CREATE_BY VARCHAR2(64  CHAR),
UPDATE_DATE TIMESTAMP(6),
DEL_FLAG VARCHAR2(2  CHAR),
CREATE_DATE TIMESTAMP(6),
constraint "PK_LAB_ITEM_CLASS_DICT" primary key (ID)
);
-- Add comments to the table
comment on table LAB_ITEM_CLASS_DICT
is '检验项目类别字典';
-- Add comments to the columns
comment on column LAB_ITEM_CLASS_DICT.SERIAL_NO
is '序号';
comment on column LAB_ITEM_CLASS_DICT.CLASS_CODE
is '类别代码';
comment on column LAB_ITEM_CLASS_DICT.CLASS_NAME
is '类别名称';
comment on column LAB_ITEM_CLASS_DICT.DEPT_CODE
is '科室代码';