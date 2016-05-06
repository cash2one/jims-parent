-- drop table MR_FEE_CLASS_DICT cascade constraints;

/*==============================================================*/
/* Table: MR_FEE_CLASS_DICT    病案首页类别                     */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table MR_FEE_CLASS_DICT 
(
   ID                   VARCHAR2(64 char)    not null,
   MR_FEE_CLASS_CODE    CHAR,
   MR_FEE_CLASS_NAME    CHAR(4),
   INPUT_CODE           CHAR(8),
   MR_FEE_CLASS_DESC    CHAR(50),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_MR_FEE_CLASS_DICT primary key (ID)
  );
comment on table MR_FEE_CLASS_DICT is
'病案首页类别';

comment on column MR_FEE_CLASS_DICT.ID is
'主键';

comment on column MR_FEE_CLASS_DICT.MR_FEE_CLASS_CODE is
'费用分类代码';

comment on column MR_FEE_CLASS_DICT.MR_FEE_CLASS_NAME is
'费用分类名称';

comment on column MR_FEE_CLASS_DICT.INPUT_CODE is
'输入码';

comment on column MR_FEE_CLASS_DICT.MR_FEE_CLASS_DESC is
'费用分类描述';

comment on column MR_FEE_CLASS_DICT.CREATE_BY is
'创建人';

comment on column MR_FEE_CLASS_DICT.CREATE_DATE is
'创建时间';

comment on column MR_FEE_CLASS_DICT.UPDATE_BY is
'修改人';

comment on column MR_FEE_CLASS_DICT.UPDATE_DATE is
'修改时间';

comment on column MR_FEE_CLASS_DICT.DEL_FLAG is
'删除标记';

comment on column MR_FEE_CLASS_DICT.REMARKS is
'备注信息';
