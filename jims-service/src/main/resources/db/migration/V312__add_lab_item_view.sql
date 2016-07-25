--检验项目视图
create or replace view lab_item_view as
select "ITEM_CLASS","ITEM_NAME",
"ITEM_CODE","STD_INDICATOR","INPUT_CODE",
"INPUT_CODE_WB","EXPAND1","EXPAND2","EXPAND3",
"EXPAND4","EXPAND5","ITEM_STATUS","BBSM","USER_GRANT",
"ID","ORG_ID","REMARKS","UPDATE_BY","CREATE_BY",
"UPDATE_DATE","DEL_FLAG","CREATE_DATE",
(select sum(price) as price from v_clinic_name_price l where l.item_code = d.item_code) as price
 from clinic_item_name_dict d  where item_class='C';