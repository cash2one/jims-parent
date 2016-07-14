CREATE OR REPLACE VIEW V_ADMINISTRATION_DICT AS
select distinct A."ID",A."ADMINISTRATION_CODE",A."ADMINISTRATION_NAME",A."INPUT_CODE",A."INP_OUTP_FLAG",A."REMARKS",A."UPDATE_BY",A."CREATE_BY",A."UPDATE_DATE",A."DEL_FLAG",A."CREATE_DATE",B."ITEM_NAME",B."CHARGE_ITEM_CLASS",B."CHARGE_ITEM_SPEC",B."CLINIC_ITEM_CODE",B."CHARGE_ITEM_CODE",B."ITEM_CODE",B."AMOUNT",B."BACKBILL_RULE",B."ITEM_SPEC",B."UNITS",B."PRICE",B."STD_INDICATOR",B."ORG_ID" from
administration_dict a left join
(SELECT pind.ITEM_NAME,
       cvc.CHARGE_ITEM_CLASS,
       cvc.CHARGE_ITEM_SPEC,
       cvc.CLINIC_ITEM_CODE,
       cvc.CHARGE_ITEM_CODE,
       pind.ITEM_CODE,
       cvc.AMOUNT,
       cvc.BACKBILL_RULE,
       pl.ITEM_SPEC,
       pl.UNITS,
       pl.PRICE,
       cind.std_indicator,
       cvc.org_id
  FROM CLINIC_VS_CHARGE cvc, CLINIC_ITEM_NAME_DICT cind, PRICE_ITEM_NAME_DICT pind,PRICE_LIST pl
 WHERE (cvc.CLINIC_ITEM_CLASS =
       cind.ITEM_CLASS)
   and (cvc.CLINIC_ITEM_CODE = cind.ITEM_CODE)
   and (cvc.CHARGE_ITEM_CLASS =
       pind.ITEM_CLASS)
   and (cvc.CHARGE_ITEM_CODE = pind.ITEM_CODE)
   and (cvc.CHARGE_ITEM_CODE=pl.ITEM_CODE)
	 and cvc.ORG_ID=cind.ORG_ID
	 and pind.ORG_ID=pl.ORG_ID
   and cvc.ORG_ID=pind.ORG_ID
    AND sysdate >= START_DATE
      and (pl.STOP_DATE IS NULL or SYSDATE <= STOP_DATE)
      and pl.item_class =  cind.ITEM_CLASS) b on a.administration_name=b.ITEM_NAME
 where a.inp_outp_flag in ('0','9');
