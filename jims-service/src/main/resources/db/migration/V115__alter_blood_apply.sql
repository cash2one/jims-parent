/*==============================================================*/
/* Table: BLOOD_APPLY    申请用血                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
ALTER TABLE BLOOD_APPLY ADD CONSTRAINT PK_BLOOD_APPLY
PRIMARY KEY(ID);
comment on column BLOOD_APPLY.ID
  is '申请用血主键';