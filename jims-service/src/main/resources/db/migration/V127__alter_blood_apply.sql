/*==============================================================*/
/* Table: BLOOD_APPLY    申请用血                               */
/* CREATE_BY :  zhangpeng                                       */
/*  添加字段                                                    */
/*==============================================================*/
 ALTER TABLE BLOOD_APPLY ADD(VISIT_ID VARCHAR2(64));
comment on column BLOOD_APPLY.VISIT_ID
  is '住院id';

  ALTER TABLE BLOOD_APPLY ADD(CLINIC_ID VARCHAR2(64));
comment on column BLOOD_APPLY.VISIT_ID
  is '门诊就诊id';