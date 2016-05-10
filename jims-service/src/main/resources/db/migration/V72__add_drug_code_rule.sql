/*==============================================================*/
/* Table:DRUG_CODING_RULE                 编码规则字典表        */
/*==============================================================*/
-- Create table
create table DRUG_CODING_RULE
(
  id          varchar2(64) not null,
  code_level  NUMBER(1) not null,
  level_width NUMBER(2),
  class_name  VARCHAR2(8),
  remarks     VARCHAR2(2000),
  update_by   VARCHAR2(64),
  create_by   VARCHAR2(64),
  update_date DATE,
  del_flag    VARCHAR2(100),
  create_date DATE
)
;
-- Add comments to the table
comment on table DRUG_CODING_RULE
  is '药品编码描述字典';
-- Add comments to the columns
comment on column DRUG_CODING_RULE.code_level
  is '编码级';
comment on column DRUG_CODING_RULE.level_width
  is '级长';
comment on column DRUG_CODING_RULE.class_name
  is '级名';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_CODING_RULE
  add constraint pk_DRUG_CODING_RULE primary key (ID);
