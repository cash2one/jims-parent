CREATE OR REPLACE VIEW V_ADMINISTRATION_DICT AS
SELECT pind.ITEM_NAME,
       cvc.CHARGE_ITEM_CLASS,
       cvc.CHARGE_ITEM_SPEC,
       cvc.CLINIC_ITEM_CODE,
       cvc.CHARGE_ITEM_CODE,
       pind.ITEM_CODE,
       cvc.AMOUNT,
       cvc.BACKBILL_RULE,
       pl.ITEM_SPEC,
       pl.UNITS,
       pl.PRICE
  FROM CLINIC_VS_CHARGE cvc, CLINIC_ITEM_NAME_DICT cind, PRICE_ITEM_NAME_DICT pind,PRICE_LIST pl
 WHERE (cvc.CLINIC_ITEM_CLASS =
       cind.ITEM_CLASS)
   and (cvc.CLINIC_ITEM_CODE = cind.ITEM_CODE)
   and (cvc.CHARGE_ITEM_CLASS =
       pind.ITEM_CLASS)
   and (cvc.CHARGE_ITEM_CODE = pind.ITEM_CODE)
   and (cvc.CHARGE_ITEM_CODE=pl.ITEM_CODE)
/*   and (cvc.ORG_ID=cind.ORG_ID)
   and (cvc.ORG_ID=pind.ORG_ID)
   and (cvc.ORG_ID=pl.ORG_ID)*/
    AND sysdate >= START_DATE
      and (pl.STOP_DATE IS NULL or SYSDATE <= STOP_DATE)
      and pl.item_class =  cind.ITEM_CLASS
 /*  and ((cind.ITEM_NAME = '皮内注射'))
   and (pind.STD_INDICATOR = 1)
   and (cind.ITEM_CLASS = 'E');*/