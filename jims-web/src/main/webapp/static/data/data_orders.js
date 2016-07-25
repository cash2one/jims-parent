var ordersType =  [];
var billingAttr = [];
var Oclass =[{ "value": "1", "label": "药品" }, { "value": "2", "label": "非药品" }];








var clinicCompleteAuto = [];


/**
 * 医嘱类型
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=REPEAT_INDICATOR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        ordersType=data;
    }
});




/**
 * 医嘱类型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function indicatorFormatter(value, rowData, rowIndex) {
    if (value ==null) {
        return;
    }
    for (var i = 0; i < ordersType.length; i++) {
        if (ordersType[i].value == value) {
            return ordersType[i].label;
        }
    }
}





function orderClassFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    if(value=='A'||value =='B'||value =='1'){
        return "药品";
    }else{
        return "非药品";
    }


}







/**
 * 计价属性
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=BILLING_ATTR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        billingAttr=data;
    }
});

/**
 * 计价属性翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function billingAttrFormatter(value, rowData, rowIndex) {
    if (value == null) {
        return;
    }

    for (var i = 0; i < billingAttr.length; i++) {
        if (billingAttr[i].value == value) {
            return billingAttr[i].label;
        }
    }
}







//非药品自动补全
function clinicCompleting(q,id){
    var clinicNameData={};
    clinicNameData.orgId="1";
    clinicNameData.dictType="V_CLINIC_NAME_PRICE"
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    clinicNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(clinicNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            clinicCompleteAuto = data;

        }
    });
}



