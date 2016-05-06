-- drop table DRUG_DISPENS_PROPERTY cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: DRUG_DISPENS_PROPERTY      药品摆药分类定义 
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table DRUG_DISPENS_PROPERTY 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar(64 char)               null,
   DISPENSARY           char(8)                        null,
   DRUG_CODE            char(20)                       null,
   DISPENSING_PROPERTY  char(10)                       null,
   DRUG_SPEC            varchar2(64 char)              null,
   DISPENSING_CUMULATE  number(11,2)                   null,
   SEPARABLE            number(11,2)                   null,
   VIRTUAL_CABINET      number(11,2)                   null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                           null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                           null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_DRUG_DISPENS_PROPERTY primary key  (id)
);

comment on table DRUG_DISPENS_PROPERTY is 
'药品摆药分类定义';

comment on column DRUG_DISPENS_PROPERTY.id is 
'主键';

comment on column DRUG_DISPENS_PROPERTY.HOSPITAL_ID is 
'医院id';

comment on column DRUG_DISPENS_PROPERTY.DISPENSARY is 
'调配药房';

comment on column DRUG_DISPENS_PROPERTY.DRUG_CODE is 
'药品代码';

comment on column DRUG_DISPENS_PROPERTY.DISPENSING_PROPERTY is 
'摆药类别';

comment on column DRUG_DISPENS_PROPERTY.DRUG_SPEC is 
'药品规格';

comment on column DRUG_DISPENS_PROPERTY.DISPENSING_CUMULATE is 
'摆药累积';

comment on column DRUG_DISPENS_PROPERTY.SEPARABLE is 
'可分割否';

comment on column DRUG_DISPENS_PROPERTY.VIRTUAL_CABINET is 
'虚拟药柜';

comment on column DRUG_DISPENS_PROPERTY.CREATE_BY is 
'创建人';

comment on column DRUG_DISPENS_PROPERTY.CREATE_DATE is 
'创建时间';

comment on column DRUG_DISPENS_PROPERTY.UPDATE_BY is 
'修改人';

comment on column DRUG_DISPENS_PROPERTY.UPDATE_DATE is 
'修改时间';

comment on column DRUG_DISPENS_PROPERTY.DEL_FLAG is 
'删除标记';

comment on column DRUG_DISPENS_PROPERTY.REMARKS is 
'备注信息';
