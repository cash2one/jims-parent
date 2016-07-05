//非药品
var clinicData={};
clinicData.orgId="1";
clinicData.dictType="V_CLINIC_NAME_PRICE";
var clinicOrderData = [];
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicOrderData = data;
    }
});