-- 无菌用品包 字典表
create table ASEPSIS_DICT
(
  id           VARCHAR2(64),
  item_class   VARCHAR2(20),
  asepsis_code VARCHAR2(20) not null,
  asepsis_name VARCHAR2(100),
  asepsis_spec VARCHAR2(20),
  units        VARCHAR2(8),
  anti_price   NUMBER(6,2),
  noback_price NUMBER(6,2),
  belong_dept  VARCHAR2(20),
  memos        VARCHAR2(50),
  input_code   VARCHAR2(20),
  valid_days   NUMBER,
  clean_price  NUMBER(6,2),
  pack_price   NUMBER(6,2),
  asep_price   NUMBER(6,2),
  item_rollback_flag NUMBER,
  flag        VARCHAR2(1),
  org_id      VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_DICT
  is '无菌用品包 字典表';
-- Add comments to the columns 
comment on column ASEPSIS_DICT.item_class
  is '包类别';
comment on column ASEPSIS_DICT.asepsis_code
  is '包代码';
comment on column ASEPSIS_DICT.asepsis_name
  is '包名称';
comment on column ASEPSIS_DICT.asepsis_spec
  is '规格';
comment on column ASEPSIS_DICT.units
  is '单位';
comment on column ASEPSIS_DICT.anti_price
  is '消毒费';
comment on column ASEPSIS_DICT.noback_price
  is '辅料费';
comment on column ASEPSIS_DICT.belong_dept
  is '所属科室';
comment on column ASEPSIS_DICT.memos
  is '备注';
comment on column ASEPSIS_DICT.input_code
  is '输入码';
comment on column ASEPSIS_DICT.valid_days
  is '有效期';
comment on column ASEPSIS_DICT.clean_price
  is '清洗费用';
comment on column ASEPSIS_DICT.pack_price
  is '打包费用';
comment on column ASEPSIS_DICT.asep_price
  is '灭菌费用';
comment on column ASEPSIS_DICT.flag
  is '已经使用标记（0停止，1使用）';
comment on column ASEPSIS_DICT.item_rollback_flag
  is '返还标志(0不回收，1回收)';
comment on column ASEPSIS_DICT.org_id
  is '所属机构ID';

alter table ASEPSIS_DICT
  add constraint PK_ASEPSIS_DICT primary key (ID);
