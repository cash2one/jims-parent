/*==============================================================*/
/* VIEW: V_CLINIC_NAME_PRICE    住院医嘱的非药品视图                              */
/* CREATE_BY :  pq                                             */
/*=============================================================*/

create or replace view v_clinic_name_price as
select c.item_class,a.item_code,c.item_name,c.item_spec,
       c.units,nvl(b.amount,1)*nvl(c.price,0) as price,c.prefer_price,c.performed_by,c.subj_code,a.INPUT_CODE,
       b.charge_item_no
from clinic_item_name_dict a,
      clinic_vs_charge b , current_price_list c
where  a.item_class <> 'A' and  a.item_class <> 'B'
      and a.ITEM_CODE = b.clinic_item_code
      and b.charge_item_code = c.item_code;


