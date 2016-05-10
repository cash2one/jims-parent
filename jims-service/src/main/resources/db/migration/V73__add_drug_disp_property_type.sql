/*==============================================================*/
/* Table: DRUG_DISP_PROPERTY_DICT   摆药类别字典表字典表        */
/*==============================================================*/-- Create table
create table DRUG_DISP_PROPERTY_DICT
(
  id                   varchar2(64),
  dispensing_property  VARCHAR2(10) not null,
  drug_administrations VARCHAR2(800),
  remarks              VARCHAR2(2000),
  update_by            VARCHAR2(64),
  create_by            VARCHAR2(64),
  update_date          DATE,
  del_flag             VARCHAR2(100),
  create_date          DATE
)
;
-- Add comments to the table
comment on table DRUG_DISP_PROPERTY_DICT
  is '摆药类别字典';
-- Add comments to the columns
comment on column DRUG_DISP_PROPERTY_DICT.id
  is '主键';
comment on column DRUG_DISP_PROPERTY_DICT.dispensing_property
  is '摆药类别';
comment on column DRUG_DISP_PROPERTY_DICT.drug_administrations
  is '对应给药途径';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_DISP_PROPERTY_DICT
  add constraint pk_DRUG_DISP_PROPERTY_DICT primary key (ID);
