/*==============================================================*/
/* Table: DRUG_PRESC_MASTER_TEMP  住院病人费用明细记录         */
/*CREATE_BY: pq	                                      */
/*==============================================================*/
create table INP_BILL_DETAIL
(
  ID                         VARCHAR2(64) not null,
  PATIENT_ID                 VARCHAR2(64) not null,
  VISIT_ID                   VARCHAR2(64) not null,
  ITEM_NO                    NUMBER(6) not null,
  ITEM_CLASS                 VARCHAR2(1),
  ITEM_NAME                  VARCHAR2(100),
  ITEM_CODE                  VARCHAR2(20),
  ITEM_SPEC                  VARCHAR2(50),
  AMOUNT                     NUMBER(6,2),
  UNITS                      VARCHAR2(64),
  ORDERED_BY                 VARCHAR2(64),
  PERFORMED_BY               VARCHAR2(64),
  COSTS                      NUMBER(10,4),
  CHARGES                    NUMBER(10,4),
  BILLING_DATE_TIME          TIMESTAMP,
  OPERATOR_NO                VARCHAR2(64),
  RCPT_NO                    VARCHAR2(64),
  CLASS_ON_INP_RCPT          VARCHAR2(1),
  SUBJ_CODE                  VARCHAR2(64),
  CLASS_ON_MR                VARCHAR2(64),
  ITEM_PRICE                 NUMBER(10,4),
  PRICE_QUOTIETY             NUMBER(7,4),
  DISCHARGE_TAKING_INDICATOR NUMBER(1),
  WARD_CODE                  VARCHAR2(64),
  CLASS_ON_RECKONING         VARCHAR2(10),
  ORDER_GROUP                VARCHAR2(64),
  ORDER_DOCTOR               VARCHAR2(64),
  PERFORM_GROUP              VARCHAR2(64),
  PERFORM_DOCTOR             VARCHAR2(64),
  CONVEY_DATE                TIMESTAMP,
  DOCTOR_USER                VARCHAR2(64),
  TRANSFLAG                  CHAR(1) default '0',
  MEMO                       VARCHAR2(128),
  OPER_TYPE                  VARCHAR2(1),
  OPER_ID                    VARCHAR2(64),
  OPER_CODE                  VARCHAR2(64),
  ADAPT_SYMPTOM_INDICATE     NUMBER(1),
  DOCUMENT_NO                VARCHAR2(64),
  TRANS_FLAG                 VARCHAR2(64),
  QYB_JZXH                   VARCHAR2(64),
  QYB_JZLSH                  VARCHAR2(64),
  QYB_QZFJE                  NUMBER(14,4),
  QYB_GGZFJE                 NUMBER(14,4),
  QYB_FHFWJE                 NUMBER(14,4),
  QYB_INSUR_NAME             VARCHAR2(100),
  QYB_PARAMID                VARCHAR2(64),
  PERFORMED_BY_SUB           VARCHAR2(64),
  YB_UPLOAD                  NUMBER(1),
  NH_UPLOAD                  NUMBER(1),
  CREATE_DATE                TIMESTAMP,
  CREATE_BY                  VARCHAR2(64 char),
  UPDATE_BY                  VARCHAR2(64 char),
  UPDATE_DATE                TIMESTAMP,
  REMARKS                    VARCHAR2(200 char),
  DEL_FLAG                   NUMBER(1)            default 0,
  constraint PK_INP_BILL_DETAIL primary key (ID)  
);

-- Add comments to the table 
comment on table INP_BILL_DETAIL
  is '住院病人费用明细记录';
-- Add comments to the columns 
comment on column INP_BILL_DETAIL.PATIENT_ID
  is '病人标识';
comment on column INP_BILL_DETAIL.VISIT_ID
  is '病人本次住院标识';
comment on column INP_BILL_DETAIL.ITEM_NO
  is '费用项目序号';
comment on column INP_BILL_DETAIL.ITEM_CLASS
  is '项目类别';
comment on column INP_BILL_DETAIL.ITEM_NAME
  is '项目名称';
comment on column INP_BILL_DETAIL.ITEM_CODE
  is '项目代码';
comment on column INP_BILL_DETAIL.ITEM_SPEC
  is '项目规划';
comment on column INP_BILL_DETAIL.AMOUNT
  is '数量';
comment on column INP_BILL_DETAIL.UNITS
  is '单位';
comment on column INP_BILL_DETAIL.ORDERED_BY
  is '开单科室';
comment on column INP_BILL_DETAIL.PERFORMED_BY
  is '执行科室';
comment on column INP_BILL_DETAIL.COSTS
  is '费用';
comment on column INP_BILL_DETAIL.CHARGES
  is '应收费用';
comment on column INP_BILL_DETAIL.BILLING_DATE_TIME
  is '计价日期及时间';
comment on column INP_BILL_DETAIL.OPERATOR_NO
  is '计价员号';
comment on column INP_BILL_DETAIL.RCPT_NO
  is '对应的结算序号';
comment on column INP_BILL_DETAIL.CLASS_ON_INP_RCPT
  is '住院收据分类';
comment on column INP_BILL_DETAIL.SUBJ_CODE
  is '会计科目分类';
comment on column INP_BILL_DETAIL.CLASS_ON_MR
  is '病案首页分类';
comment on column INP_BILL_DETAIL.ITEM_PRICE
  is '项目标准单价';
comment on column INP_BILL_DETAIL.PRICE_QUOTIETY
  is '收费系数';
comment on column INP_BILL_DETAIL.DISCHARGE_TAKING_INDICATOR
  is '出院带药标志';
comment on column INP_BILL_DETAIL.QYB_JZXH
  is '就诊编号';
comment on column INP_BILL_DETAIL.QYB_JZLSH
  is '记账流水号';
comment on column INP_BILL_DETAIL.QYB_QZFJE
  is '明细项目全自费金额';
comment on column INP_BILL_DETAIL.QYB_GGZFJE
  is '明细项目挂钩自付金额';
comment on column INP_BILL_DETAIL.QYB_FHFWJE
  is '明细项目符合范围金额';
comment on column INP_BILL_DETAIL.PERFORMED_BY_SUB
  is '发药药房的子管理单位';
comment on column INP_BILL_DETAIL.YB_UPLOAD
  is '医保费用上传标志';
comment on column INP_BILL_DETAIL.NH_UPLOAD
  is '农合费用上传标志';
comment on column INP_BILL_DETAIL.CREATE_DATE 
  is '创建日期';

comment on column INP_BILL_DETAIL.CREATE_BY 
  is '创建人';

comment on column INP_BILL_DETAIL.UPDATE_BY 
  is '更新人';

comment on column INP_BILL_DETAIL.UPDATE_DATE 
  is '更新日期';

comment on column INP_BILL_DETAIL.REMARKS 
  is '备注信息';

comment on column INP_BILL_DETAIL.DEL_FLAG 
  is '删除标志';
