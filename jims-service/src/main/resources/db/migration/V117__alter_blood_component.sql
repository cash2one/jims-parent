/*==============================================================*/
/* Table: BLOOD_COMPONENT    ѪҺ�ɷ��ֵ�                                */
/* CREATE_BY :  zhangpeng                                              */
/*==============================================================*/
-- �������
ALTER TABLE BLOOD_COMPONENT ADD CONSTRAINT PK_BLOOD_COMPONENT
����PRIMARY KEY(ID);
comment on column BLOOD_COMPONENT.ID
  is 'ѪҺ�ɷ��ֵ�����';