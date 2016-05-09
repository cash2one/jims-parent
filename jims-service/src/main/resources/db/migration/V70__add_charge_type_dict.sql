-- drop table CHARGE_TYPE_DICT cascade constraints;

/*==============================================================*/
/* Table: CHARGE_TYPE_DICT    费别字典                         */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table CHARGE_TYPE_DICT 
(
   ID                   VARCHAR2(64 char)    not null,
   HOSID                VARCHAR2(64 char),
   CHARGE_TYPE_CODE     CHAR,
   CHARGE_TYPE_NAME     CHAR(8),
   CHARGE_PRICE_INDICATOR NUMBER(11,2),
   INPUT_CODE_WB        CHAR(8),
   IS_INSUR             CHAR,
   GROUP_NO             NUMBER(11,2),
   GROUP_NAME           VARCHAR2(64 char),
   INSURANCE_TYPE_INQ   NUMBER(11,2),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATA          TIMESTAMP(7),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP(7),
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_CHARGE_TYPE_DICT primary key (ID)
 );

comment on table CHARGE_TYPE_DICT is
'费别字典';

comment on column CHARGE_TYPE_DICT.ID is
'字典ID';

comment on column CHARGE_TYPE_DICT.HOSID is
'医院id';

comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_CODE is
'费别代码';

comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_NAME is
'费别名称';

comment on column CHARGE_TYPE_DICT.CHARGE_PRICE_INDICATOR is
'享受优惠价格标志';

comment on column CHARGE_TYPE_DICT.INPUT_CODE_WB is
'五笔码';

comment on column CHARGE_TYPE_DICT.IS_INSUR is
'是否是医保类别';

comment on column CHARGE_TYPE_DICT.GROUP_NO is
'费别分组号';

comment on column CHARGE_TYPE_DICT.GROUP_NAME is
'费别分组名称';

comment on column CHARGE_TYPE_DICT.INSURANCE_TYPE_INQ is
'院长查询用的医保类别';

comment on column CHARGE_TYPE_DICT.CREATE_BY is
'创建人';

comment on column CHARGE_TYPE_DICT.CREATE_DATA is
'创建时间';

comment on column CHARGE_TYPE_DICT.UPDATE_BY is
'修改人';

comment on column CHARGE_TYPE_DICT.UPDATE_DATE is
'修改时间';

comment on column CHARGE_TYPE_DICT.DEL_FLAG is
'删除标志';

comment on column CHARGE_TYPE_DICT.REMARKS is
'备注信息';
