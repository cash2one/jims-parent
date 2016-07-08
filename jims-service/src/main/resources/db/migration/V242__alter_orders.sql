/*==============================================================*/
/* Table: ORDERS   住院医嘱 */
/* CREATE_DATE: 2016-07-08 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
-- Add/modify columns
alter table ORDERS modify CURRENT_PRESC_NO VARCHAR2(64);
alter table ORDERS modify DOCTOR_USER VARCHAR2(64);
alter table ORDERS modify PROCESSING_NURSE VARCHAR2(64);
alter table ORDERS modify STOP_PROCESSING_NURSE VARCHAR2(64);
alter table ORDERS modify CANCEL_DOCTOR VARCHAR2(64);