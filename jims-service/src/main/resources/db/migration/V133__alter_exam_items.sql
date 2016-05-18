/*==============================================================*/
/* Table: EXAM_ITEMS    检查项目记录                        */
/* CREATE_BY :  zhangpeng                                       */
/*  添加字段                                                    */
/*==============================================================*/
 ALTER TABLE EXAM_ITEMS ADD(VISIT_ID VARCHAR2(64));
comment on column EXAM_ITEMS.VISIT_ID
  is '住院id';
  ALTER TABLE EXAM_ITEMS ADD(CLINIC_ID VARCHAR2(64));
comment on column EXAM_ITEMS.CLINIC_ID
  is '门诊id';
  ALTER TABLE EXAM_ITEMS ADD(PATIENT_ID VARCHAR2(64));
comment on column EXAM_ITEMS.PATIENT_ID
  is '病人id';
  ALTER TABLE EXAM_ITEMS ADD(ORG_ID VARCHAR2(64));
comment on column EXAM_ITEMS.ORG_ID
  is '组织结构代码';
  ALTER TABLE EXAM_ITEMS ADD(APPOINTS_ID VARCHAR2(64));
comment on column EXAM_ITEMS.APPOINTS_ID
  is '主记录id';