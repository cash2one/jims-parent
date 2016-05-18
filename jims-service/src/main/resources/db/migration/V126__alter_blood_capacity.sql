/*==============================================================*/
/* Table: BLOOD_CAPACITY    用血方式                            */
/* CREATE_BY :  zhangpeng                                       */
/*  添加字段                                         */
/*==============================================================*/

 ALTER TABLE BLOOD_CAPACITY ADD(VISIT_ID VARCHAR2(64));
comment on column BLOOD_CAPACITY.VISIT_ID
  is '住院id';

  ALTER TABLE BLOOD_CAPACITY ADD(CLINIC_ID VARCHAR2(64));
comment on column BLOOD_CAPACITY.VISIT_ID
  is '门诊就诊id';