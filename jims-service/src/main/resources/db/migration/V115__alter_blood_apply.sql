/*==============================================================*/
/* Table: BLOOD_APPLY    ��Ѫ����                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
-- �������
ALTER TABLE BLOOD_APPLY ADD CONSTRAINT PK_BLOOD_APPLY
����PRIMARY KEY(ID);
comment on column BLOOD_APPLY.ID
  is '��Ѫ��������';