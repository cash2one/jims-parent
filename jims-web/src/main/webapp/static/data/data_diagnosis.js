//icd10
var icdData={};
icdData.dictType="emr_data_icd10";
icdData.isOrgId=false;
icdData.inputParamVos=inputParamVos;
var icdAllData = [];

function icdCompleting(q,id){
    var inputParamVos=new Array();
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='KEYWORD_SHUOMING';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
        icdData.inputParamVos=inputParamVos;

    }else{
        $("#"+id).combogrid('setValue','');
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
    if (value == 0 ||value == null) {
        return;
    }
    return rowData.icdName;
}

