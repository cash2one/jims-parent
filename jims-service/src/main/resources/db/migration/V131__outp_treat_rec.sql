/*==============================================================*/
/* Table: OUTP_TREAT_REC    检查治疗医嘱明细记录                        */
/* CREATE_BY :  zhangpeng                                       */
/*  添加字段                                                    */
/*==============================================================*/
 ALTER TABLE OUTP_TREAT_REC ADD(VISIT_ID VARCHAR2(64));
comment on column OUTP_TREAT_REC.VISIT_ID
  is '住院id';

  ALTER TABLE OUTP_TREAT_REC ADD(ORG_ID VARCHAR2(64));
comment on column OUTP_TREAT_REC.ORG_ID
  is '住院id';

  ALTER TABLE OUTP_TREAT_REC ADD(CLINIC_ID VARCHAR2(64));
comment on column OUTP_TREAT_REC.CLINIC_ID
  is '住院id';
