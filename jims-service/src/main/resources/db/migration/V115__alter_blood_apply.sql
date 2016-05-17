/*==============================================================*/
/* Table: BLOOD_APPLY    用血申请                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
-- 添加主键
ALTER TABLE BLOOD_APPLY ADD CONSTRAINT PK_BLOOD_APPLY
　　PRIMARY KEY(ID);
comment on column BLOOD_APPLY.ID
  is '用血申请主键';