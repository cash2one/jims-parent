/*==============================================================*/
/* Table: BLOOD_CAPACITY    申请用血量表                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
-- 添加主键
ALTER TABLE BLOOD_CAPACITY ADD CONSTRAINT PK_BLOOD_CAPACITY
　　PRIMARY KEY(ID);
comment on column BLOOD_CAPACITY.ID
  is '申请用血量表主键';