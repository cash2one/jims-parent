/*==============================================================*/
/* Table: BLOOD_CAPACITY    用血方式                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/

ALTER TABLE BLOOD_CAPACITY ADD CONSTRAINT PK_BLOOD_CAPACITY
����PRIMARY KEY(ID);
comment on column BLOOD_CAPACITY.ID
  is '用血方式主键';