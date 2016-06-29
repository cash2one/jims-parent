var ordersType =  [];
var billingAttr = [];









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
function itemFormatter(value, rowData, rowIndex) {
    if (value == 0) {
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

    for (var i = 0; i < Oclass.length; i++) {
        if (Oclass[i].value == value) {
            return Oclass[i].label;
        }
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
    if (value == 0) {
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
    clinicNameData.dictType="V_CINIC_ITEM_NANE"
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
    }else{
        $("#"+id).combogrid('setValue','');
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



