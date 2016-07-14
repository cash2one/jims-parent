/*=============================================================*/
/* Table: DRUG_RATIONAL_DOSAGE    药品用量信息                 */
/* CREATE_DATE: 2016-07-12                                     */
/* CREATE_BY :  fengyuguang                                    */
/*  创建表                                                     */
/*=============================================================*/
-- Create table
create table DRUG_RATIONAL_DOSAGE
(
  id                  VARCHAR2(64) not null,
  drug_code           VARCHAR2(20) not null,
  drug_spec           VARCHAR2(20),
  dose_per_unit       NUMBER(8,3),
  dose_units          VARCHAR2(8),
  max_dosage          NUMBER(8,3),
  max_presc_dosage    NUMBER(8,3),
  max_outp_abidance   NUMBER(8,3),
  administration_code VARCHAR2(16),
  freq_desc           CHAR(16),
  freq_counter        NUMBER(11,2),
  freq_interval       NUMBER(11,2),
  freq_interval_units CHAR(4)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table DRUG_RATIONAL_DOSAGE
  is '药品用量信息';
-- Add comments to the columns
comment on column DRUG_RATIONAL_DOSAGE.id
  is 'ID';
comment on column DRUG_RATIONAL_DOSAGE.drug_code
  is '药品代码';
comment on column DRUG_RATIONAL_DOSAGE.drug_spec
  is '规格';
comment on column DRUG_RATIONAL_DOSAGE.dose_per_unit
  is '最小单位剂量';
comment on column DRUG_RATIONAL_DOSAGE.dose_units
  is '剂量单位';
comment on column DRUG_RATIONAL_DOSAGE.max_dosage
  is '单次用量';
comment on column DRUG_RATIONAL_DOSAGE.max_presc_dosage
  is '单处方最大开药量';
comment on column DRUG_RATIONAL_DOSAGE.max_outp_abidance
  is '处方最大用药天数';
comment on column DRUG_RATIONAL_DOSAGE.administration_code
  is '给药途径和方法';
comment on column DRUG_RATIONAL_DOSAGE.freq_desc
  is '执行频率描述';
comment on column DRUG_RATIONAL_DOSAGE.freq_counter
  is '频率次数';
comment on column DRUG_RATIONAL_DOSAGE.freq_interval
  is '频率间隔';
comment on column DRUG_RATIONAL_DOSAGE.freq_interval_units
  is '频率间隔单位';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_RATIONAL_DOSAGE
  add constraint PK_DRUG_RATIONAL_DOSAGE primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
