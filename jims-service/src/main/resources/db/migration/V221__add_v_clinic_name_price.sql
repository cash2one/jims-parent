/*==============================================================*/
/* VIEW: V_CLINIC_NAME_PRICE    住院医嘱的非药品视图                              */
/* CREATE_BY :  pq                                             */
/*=============================================================*/

CREATE OR REPLACE VIEW V_CLINIC_ITEM_PRICE AS
select c.item_class,c.item_code,c.item_name,c.item_spec,
       c.units,c.price,c.prefer_price,c.performed_by,c.subj_code,a.INPUT_CODE,
       b.charge_item_no
from clinic_item_name_dict a,
      clinic_vs_charge b , current_price_list c
where  a.item_class <> 'A' and  a.item_class <> 'B'
      and a.ITEM_CODE = b.clinic_item_code
      and b.charge_item_code = c.item_code

