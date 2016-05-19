-- Create table
/*==============================================================*/
/* Table: CLINIC_ITEM_CLASS_DICT      药品类别字典表                                */
/*==============================================================*/
create table DRUG_CLASS_DICT
(
  ID          VARCHAR2(64) not null,
  CLASS_CODE  VARCHAR2(10) not null,
  CLASS_NAME  VARCHAR2(40),
  PARENT_ID   VARCHAR2(10),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE
);
-- Add comments to the table
comment on table DRUG_CLASS_DICT
  is '类别字典表';
-- Add comments to the columns
comment on column DRUG_CLASS_DICT.ID
  is '主键';
comment on column DRUG_CLASS_DICT.CLASS_CODE
  is '类别代码';
comment on column DRUG_CLASS_DICT.CLASS_NAME
  is '类别名称';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_CLASS_DICT
  add constraint DRUG_CLASS_DICT_PK primary key (ID);

insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA300','清热泻火药','CA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA400','清热燥湿药','CA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA500','清虚热药','CA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CB','驱虫药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CB100','驱虫药','CB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CC','收涩药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CC100','收涩药','CC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CD','外用药及其他','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CD100','外用药及其他','CD');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CE','温理药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CE100','温理药','CE');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CF','消食药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CF100','消食药','CF');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CG','泻下药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CG100','泻下药','CG');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CH','解表药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CH100','辛凉解表药','CH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CH200','辛温解表药','CH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CI','止血药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CI100','止血药','CI');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CJ','祛风湿药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CJ100','祛风湿药','CJ');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0','医用材料','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0101','消毒材料','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0102','敷料','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0103','其他材料','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0104','血费','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0201','消毒材料-不收费','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0202','敷料-不收费','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'I0203','其他材料-不收费','I0');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1','抗微生物药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1100','青霉素类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1200','头孢菌素类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1300','氨基糖苷类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1400','大环内酯类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1500','其他抗生素','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1600','磺胺类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1700','喹诺酮类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1800','硝基呋喃类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1900','抗结核病药','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1A00','抗麻风病药','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1B00','抗真菌药','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1C00','抗病毒药','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A1D00','四环素类','A1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2','抗寄生虫病药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2100','抗疟药','A2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2200','抗阿米巴病药及抗滴虫病药','A2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2300','抗利什曼原虫病药','A2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2400','抗血吸虫病药','A2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A2500','驱肠虫药','A2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A3','麻醉药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A3100','局部麻醉药','A3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A3200','全身麻醉药','A3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A3300','麻醉辅助药','A3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A4','镇痛、解热、抗炎、抗风湿、抗痛风药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A4100','镇痛药','A4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A4200','解热镇痛、抗炎、抗风湿药','A4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A4300','抗痛风药','A4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5','神经系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5100','抗震颤麻痹药                     ','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5200','抗重症肌无力药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5300','抗癫痫药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5400','脑血管病用药及降颅压药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5500','镇静催眠药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5600','其他','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5700','抗痴呆药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6','治疗精神障碍药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6100','抗精神病药','A6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6200','抗焦虑药','A6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6300','抗抑郁药','A6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6400','抗狂躁药','A6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A6500','镇静催眠药','A6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7','心血管系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7100','抗心绞痛药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7200','抗心律失常药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7300','抗心力衰竭药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7400','抗高血压药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7500','抗休克药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7600','调脂及抗动脉粥样硬化药','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A7700','其他','A7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A8','呼吸系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A8100','祛痰药','A8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A8200','镇咳剂','A8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A8300','平喘药','A8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A8400','其他','A8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9','消化系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9100','抗酸药及抗溃疡病药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9200','助消化药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9300','胃肠解痉药及胃动力药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9400','泄药及止泻药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9500','肝胆疾病用药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9600','其他','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9700','微生态制剂','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9800','治疗炎性肠病药','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AA','泌尿系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AA100','利尿药','AA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AA200','良性前列腺增生用药','AA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AA300','透析用药','AA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB','血液系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB100','抗贫血药','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB200','抗血小板药','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB300','促凝血药','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB400','抗凝血药及溶栓药','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB500','血容量扩充剂','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AB600','升白细胞药','AB');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC','激素及影响内分泌药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC100','下丘脑垂体激素及其类似物','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC200','肾上腺皮质激素类药','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC300','胰岛素及口服降糖药','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC301','胰岛素','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC302','口服降血糖药','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC400','甲状腺素及抗甲状腺药','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC500','雄激素及同化激素','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC600','雌激素及孕激素','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AC700','钙代谢调节药及抗骨质疏松药','AC');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AD','抗变态反应药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AD100','抗变态反应药','AD');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AE','免疫系统用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AE100','免疫系统用药','AE');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AF','维生素、矿物质类药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AF100','维生素','AF');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AF101','矿物质','AF');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AF102','肠外营养药','AF');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AF103','钙调节药','AF');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AG','调节水、电解质及酸碱平衡药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AG100','水、电解质平衡调节药','AG');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AG200','酸碱平衡调节药','AG');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AG300','其他','AG');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH','解毒药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH100','氰化物中毒解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH200','有机磷酸酯类中毒解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH300','亚硝酸盐中毒解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH400','阿片类中毒解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH500','鼠药解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH600','苯二氮卓类中毒解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH700','重金属解毒药','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AI','生物制品','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AI100','生物制品','AI');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AJ','诊断用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AJ100','造影剂','AJ');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AJ200','其它','AJ');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AK','皮肤科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AK100','抗感染药','AK');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AK200','角质溶解药','AK');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AK300','肾上腺皮质激素类药','AK');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AK400','其他','AK');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AL','眼科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AL100','抗感染药','AL');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AL200','青光眼用药','AL');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AL300','其他','AL');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AM','耳鼻喉科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AM100','耳鼻喉科用药','AM');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AN','妇产科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AN100','子宫收缩药','AN');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AN200','其他','AN');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AO','计划生育用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AO100','避孕药','AO');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP','抗肿瘤药物','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP100','烷化剂抗肿瘤药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP200','抗代谢类抗肿瘤药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP300','抗生素类抗肿瘤药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP400','天然来源抗肿瘤药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP500','激素类抗肿瘤药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP600','其他','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AP700','抗肿瘤辅助药','AP');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AQ','骨科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AQ100','骨科用药','AQ');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1','内科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1100','解表剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1101','辛温解表','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1102','辛凉解表','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1103','表里双解','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1104','扶正解表','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1200','祛暑剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1201','解表祛暑','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1202','健胃祛暑','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1300','泻下剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1301','润肠通便','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1400','清热剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1401','清热泻火','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1402','清热解毒','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1403','清肝解毒','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1404','清热祛湿','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1405','清利肝胆湿热','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1406','清利胃肠湿热','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1500','温里剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1501','温中健脾','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1600','止咳、平喘剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1601','散寒止咳','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1602','清肺止咳','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1603','润肺止咳','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1604','清肺平喘','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1605','理肺止咳','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1606','清热化痰','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1607','润肺化痰','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1700','开窍剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1701','清热开窍','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1702','化痰开窍','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1800','固涩剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1801','补肾缩泉','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1802','固肠止泻','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1900','扶正剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1901','健脾益气','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1902','健脾和胃','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1903','健脾养血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1904','滋阴补肾','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1905','滋阴降火','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1906','滋肾养肝','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1907','温补肾阳','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1908','益气养阴','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1909','益气复脉','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1910','滋补心肺','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1911','气血双补','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1A00','安神剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1A01','养心安神','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1B00','止血剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1B01','凉血止血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1B02','散瘀止血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C00','袪瘀剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C01','活血祛瘀','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C02','益气活血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C03','理气活血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C04','滋阴活血','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C05','化瘀宽胸','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C06','化瘀通脉','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1C07','化瘀解毒','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1D00','理气剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1D01','舒肝解郁','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1D02','疏肝和胃','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1D03','理气止痛','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1E00','消导剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1E01','消食导滞','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1F00','治风剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1F01','疏散外风','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1F02','祛风化瘀','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1F03','平肝熄风','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1F04','祛风通络','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G00','祛湿剂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G01','消肿利水','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G02','益肾通淋','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G03','化瘀通淋','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G04','扶正祛湿','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G05','化浊降脂','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G06','散寒除湿通痹','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G07','清热除湿通痹','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G08','祛风除湿通痹','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G09','清热利湿通淋','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1G10','化瘀祛湿','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1H00','肿瘤用药','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1H01','抗肿瘤用药','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1HO2','肿瘤放化疗辅助用药','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2','外科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2100','清热利湿','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2200','清热消肿','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2300','清热解毒','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2400','通淋消石','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2500','软坚散结','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3','妇科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3100','理气剂','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3101','养血舒肝','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3102','活血化瘀','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3103','化瘀止血','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3104','收敛止血','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3200','清热剂','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3201','清热除湿','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3202','清热解毒','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3300','扶正剂','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3301','养血理气','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3302','益气养血','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3303','滋阴安神','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3304','健脾养肝','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3305','益气补肾','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3306','益气养阴','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3307','滋补心肺','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3308','扶正解毒','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3400','散结剂','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B3401','消肿散结','B3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4','眼科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4100','清热剂','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4101','清热散风','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4102','泻火明目','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4200','扶正剂','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4201','滋阴养肝','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B4202','益气养阴','B4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5','耳鼻喉科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5100','耳病','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5101','滋肾平肝','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5200','鼻病 ','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5201','宣肺通窍','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5202','清热通窍','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5203','疏风清热','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5204','扶正解表','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5300','咽喉病','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5301','化痰利咽','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5302','滋阴清热','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B5303','清热凉血','B5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B6','骨伤科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B6100','活血化瘀','B6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B6200','活血通络','B6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B6300','补肾壮骨','B6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B6400','祛风活络','B6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B7','皮肤科用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B7100','皮肤科用药','B7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B8','民族药及其它用药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B8100','民族药及其它用药','B8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C1','安神药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C1100','安神药','C1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C2','补益药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C2100','补气药','C2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C2200','补血药','C2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C2300','补阳药','C2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C2400','补阴药','C2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C3','芳香化湿','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C3100','芳香化湿药','C3');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C4','化痰、止咳、平喘药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C4100','化痰、止咳、平喘药','C4');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C5','活血化瘀药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C5100','活血化瘀药','C5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C6','开窍药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C6100','开窍药','C6');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C7','理气药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C7100','理气药','C7');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C8','利水渗湿药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C8100','利水渗湿药','C8');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C9','平肝息风药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'C9100','平肝息风药','C9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA','清热药','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA100','清热解毒药','CA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'CA200','清热凉血药','CA');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AH800','其它解毒药物','AH');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A5800','中枢兴奋药','A5');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A9900','止吐药物','A9');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AR','循环系统','*');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AR100','心脑血管疾病','AR');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AR200','解痉药','AR');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AR300','其它降压药','AR');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'AR400','钙拮抗药物','AR');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B1H03','其他','B1');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'B2600','清热剂','B2');
insert  into drug_class_dict (id,class_code,class_name,parent_id) values(sys_guid(),'A4400','其他','A4');