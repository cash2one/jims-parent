/*================================================================================*/
/* Table: DRUG_SUPPLIER_CATALOG    增加生产厂商表数据                             */
/* Table: drug_stock    		       修改药品库存表数据(del_flag='0',org_id='1')    */
/* Table: drug_storage_dept    	   修改所属库房表数据(del_flag='0',org_id='1')    */
/* Table: drug_sub_storage_dept    增加所属子库房表数据                           */
/* CREATE_DATE: 2016-07-13                                                        */
/* CREATE_BY :  娄会丽                                                            */
/*================================================================================*/
--insert into DRUG_SUPPLIER_CATALOG (SUPPLIER_ID, SUPPLIER, SUPPLIER_CLASS, INPUT_CODE, MEMO, TRADEMARK, INPUT_CODE_WB, FOREIGNX, SUPPLIER_CODE, USED_FLAG, ID, ORG_ID, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE) values();
insert into DRUG_SUPPLIER_CATALOG (SUPPLIER_ID, SUPPLIER, SUPPLIER_CLASS, INPUT_CODE, MEMO, TRADEMARK, INPUT_CODE_WB, FOREIGNX, SUPPLIER_CODE, USED_FLAG, ID, ORG_ID, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values('广东齐星','广州白云山奇星药业有限公司','生产商','gzqx',null,'GZBYSQXYYYXGS',null,0,'YS000120',0,'7302ab5f08cf4ae589634f473fb69d48','1',null,null,null,to_date('13-07-2016 09:10:04', 'dd-mm-yyyy hh24:mi:ss'),'0',to_date('13-07-2016 09:10:04', 'dd-mm-yyyy hh24:mi:ss'));

update drug_stock set del_flag='0',org_id='1';

update drug_storage_dept set del_flag='0',org_id='1';

insert into drug_sub_storage_dept(storage_code,sub_storage,import_no_prefix,import_no_ava,export_no_prefix,export_no_ava,sub_storage_code,input_code,id,org_id,remarks,update_by,create_by,update_date,del_flag,create_date)
values('150103','B02子库房',null,null,null,null,'B02','B02ZKF','1','1',null,null,null,null,0,null);
insert into drug_sub_storage_dept(storage_code,sub_storage,import_no_prefix,import_no_ava,export_no_prefix,export_no_ava,sub_storage_code,input_code,id,org_id,remarks,update_by,create_by,update_date,del_flag,create_date)
values('150102','B01子库房',null,null,null,null,'B01','B01ZKF','2','1',null,null,null,null,0,null);
insert into drug_sub_storage_dept(storage_code,sub_storage,import_no_prefix,import_no_ava,export_no_prefix,export_no_ava,sub_storage_code,input_code,id,org_id,remarks,update_by,create_by,update_date,del_flag,create_date)
values('150101','A01子库房',null,null,null,null,'A01','A01ZKF','3','1',null,null,null,null,0,null);
insert into drug_sub_storage_dept(storage_code,sub_storage,import_no_prefix,import_no_ava,export_no_prefix,export_no_ava,sub_storage_code,input_code,id,org_id,remarks,update_by,create_by,update_date,del_flag,create_date)
values('150104','A02子库房',null,null,null,null,'A02','A02ZKF','4','1',null,null,null,null,0,null);