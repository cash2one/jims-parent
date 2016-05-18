/*==============================================================*/
/* Table: outp_orders    门诊医嘱主记录                        */
/* CREATE_BY :  zhangpeng                                       */
/*  添加字段                                                    */
/*==============================================================*/
 ALTER TABLE outp_orders ADD(VISIT_ID VARCHAR2(64));
comment on column outp_orders.VISIT_ID
  is '住院id';
