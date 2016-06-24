/*==============================================================*/
/* Table: PERFORM_DEFAULT_SCHEDULE  修改字段长度                        */
/* CREATE_BY: pq                                       */
/*==============================================================*/
alter table PERFORM_DEFAULT_SCHEDULE modify FREQ_DESC VARCHAR2(64);
alter table PERFORM_DEFAULT_SCHEDULE modify ADMINISTRATION VARCHAR2(64);