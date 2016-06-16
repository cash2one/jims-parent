CREATE OR REPLACE VIEW V_INPUT_REGISTRATION_LIST AS
SELECT current_price_list.item_class, current_price_list.item_code,
          current_price_list.item_name, current_price_list.item_spec,
          current_price_list.units, to_char(current_price_list.price,'99990.00')as price,
          current_price_list.prefer_price, current_price_list.foreigner_price,
          current_price_list.performed_by, price_item_name_dict.input_code,
          price_item_name_dict.input_code_wb,current_price_list.org_id
     FROM current_price_list, price_item_name_dict
    WHERE current_price_list.item_code = price_item_name_dict.item_code
      AND current_price_list.item_class = price_item_name_dict.item_class
      AND ( current_price_list.CLASS_ON_RECKONING like 'A%' OR current_price_list.CLASS_ON_RECKONING like 'C%' or  (current_price_list.CLASS_ON_RECKONING like 'E%' AND current_price_list.item_name LIKE '%诊查%' )  )
     order by price_item_name_dict.input_code asc
