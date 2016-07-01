//icd10
var comboGridComplete = [];
var icdData={};
icdData.orgId="";
icdData.dictType="emr_data_icd10";

var icdAllData = [];

function comboGridCompleting(q,id){
    var drugNameData={};
    drugNameData.orgId="1";
    drugNameData.dictType="emr_data_icd10"
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='PINYIN_INDEX';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }else{
        $("#"+id).combogrid('setValue','');
    }
    drugNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(drugNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            comboGridComplete = data;

        }
    });
}


$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(icdData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        icdAllData = data;
    }
});



/**
 * icd翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function icdFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < icdAllData.length; i++) {
        if (icdAllData[i].code == value) {
            return icdAllData[i].zhongwen_mingcheng;
        }
    }
}


/**
 * 诊断类型
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function typeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < administration.length; i++) {
        if (administration[i].value == value) {
            return administration[i].text;
        }
    }
}