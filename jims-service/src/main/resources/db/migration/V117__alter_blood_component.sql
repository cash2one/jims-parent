/*==============================================================*/
/* Table: BLOOD_COMPONENT    血液成分字典                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
-- 添加主键
ALTER TABLE BLOOD_COMPONENT ADD CONSTRAINT PK_BLOOD_COMPONENT
　　PRIMARY KEY(ID);
comment on column BLOOD_COMPONENT.ID
  is '血液成分字典主键';