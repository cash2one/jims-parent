*==============================================================*/
/* Table: ANAESTHESIA_DICT                                      */
/*==============================================================*/
create table ANAESTHESIA_DICT 
(
   id                   varchar2(64)                   not null,
   SERIAL_NO            number(2)                      null,
   "ANAESTHESIA _CODE"  varchar2(64)                   null,
   "ANAESTHESIA _NAME"  varchar2(64)                   null,
   INPUT_CODE           varchar2(8)                    null,
   CREATE_BY            varchar2(20)                   null,
   CREATE_DATE          TIMESTAMP                      null,
   UPDATE_BY            varchar2(20)                   null,
   UPDATE_DATE          TIMESTAMP                      null,
   REMARKS              varchar2(200)                  null,
   DEL_FLAG             char(1)                        null,
   constraint PK_ANAESTHESIA_DICT primary key (id)
);

comment on table ANAESTHESIA_DICT is 
'麻醉方法字典 ';

comment on column ANAESTHESIA_DICT.id is 
'主键';

comment on column ANAESTHESIA_DICT.SERIAL_NO is 
'序号';

comment on column ANAESTHESIA_DICT."ANAESTHESIA _CODE" is 
'麻醉方法代码';

comment on column ANAESTHESIA_DICT."ANAESTHESIA _NAME" is 
'麻醉方法名称';

comment on column ANAESTHESIA_DICT.INPUT_CODE is 
'输入码';

comment on column ANAESTHESIA_DICT.CREATE_BY is 
'创建人';

comment on column ANAESTHESIA_DICT.CREATE_DATE is 
'创建时间';

comment on column ANAESTHESIA_DICT.UPDATE_BY is 
'修改人';

comment on column ANAESTHESIA_DICT.UPDATE_DATE is 
'修改时间';

comment on column ANAESTHESIA_DICT.REMARKS is 
'备注信息';

comment on column ANAESTHESIA_DICT.DEL_FLAG is 
'删除标记';

INSERT INTO ANAESTHESIA_DICT ("ID", "SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('8', 8, 'G', '基础', 'JC');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('23', 23, 'H', '蛛网下腔阻滞麻醉', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('1', 1, 'a', '无', 'W');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('2', 2, 'M', '体外麻醉', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('3', 3, 'B', '全麻', 'QM');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('4', 4, 'C', '硬膜外麻', 'YMW');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('5', 5, 'D', '颈丛阻滞', 'JC');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('6', 6, 'E', '臂丛阻滞', 'BC');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('7', 7, 'F', '骶管阻滞', 'DM');
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('12', 12, 'J', '臂丛+全麻', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('13', 14, 'o', '经气管切开插管麻', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('15', 15, 'L', '局麻+强化', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('16', 16, 'p', '静脉麻醉', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('17', 17, 'q', '局麻', NULL);
INSERT INTO ANAESTHESIA_DICT ("ID","SERIAL_NO", "ANAESTHESIA_CODE", "ANAESTHESIA_NAME", "INPUT_CODE") VALUES ('22', 22, 's', '神经阻滞麻醉', 'SJ');