-- drop table LAB_TEST_ITEMS cascade constraints;

/*==============================================================*/
/* Table: LAB_TEST_ITEMS     检验项目                                 */
/*==============================================================*/
CREATE TABLE "LAB_TEST_ITEMS" (
"ID" VARCHAR2(64 CHAR) not null,
"TEST_NO" VARCHAR2(10  CHAR) NULL ,
"ITEM_NO" NUMBER(2) NULL ,
"ITEM_NAME" VARCHAR2(100  CHAR) NULL ,
"ITEM_CODE" VARCHAR2(20  CHAR) NULL ,
"BILLING_INDICATOR" NUMBER(1) DEFAULT 0  NULL ,
"TEST_BY" VARCHAR2(10  CHAR) NULL ,
"RCPT_NO" VARCHAR2(20  CHAR) NULL ,
"EXPLANATION" VARCHAR2(100  CHAR) NULL,
"DEL_FLAG"         NUMBER (1),
   constraint "PK_LAB_TEST_ITEMS" primary key (ID)
);
-- Add comments to the table
COMMENT ON COLUMN "LAB_TEST_ITEMS"."EXPLANATION" IS '退费说明';
