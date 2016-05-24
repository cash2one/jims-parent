-- Create table
create table DEPT_VS_WARD
(
  ID          VARCHAR2(64) not null,
  DEPT_CODE   VARCHAR2(100),
  WARD_CODE   VARCHAR2(100),
  ORG_ID      VARCHAR2(64),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE
);
-- Add comments to the table
comment on table DEPT_VS_WARD
  is '科室与护理单元对照';
-- Add comments to the columns
comment on column DEPT_VS_WARD.ID
  is '主键';
comment on column DEPT_VS_WARD.DEPT_CODE
  is '科室代码';
comment on column DEPT_VS_WARD.WARD_CODE
  is '护理单元编码';
comment on column DEPT_VS_WARD.ORG_ID
  is '所属组织机构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DEPT_VS_WARD
  add constraint DEPT_VS_WARD_PK primary key (ID);
alter table DEPT_VS_WARD
  add constraint DEPT_VS_WARD_UK unique (ORG_ID, WARD_CODE, DEPT_CODE);
