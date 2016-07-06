//icd10
var icdData={};
icdData.dictType="emr_data_icd10";
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


/*$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(icdData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        icdAllData = data;
    }
});*/



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
   var  icdName = '';
    for (var i = 0; i < icdAllData.length; i++) {
        if (icdAllData[i].code == value) {
            icdName = icdAllData[i].zhongwen_mingcheng;
        }
    }
    if(icdName==''){
        var inputParamVos=new Array();
        var InputParamVo={};
        InputParamVo.colName='KEYWORD_SHUOMING';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
        icdData.inputParamVos=inputParamVos;

        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(icdData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                icdAllData.push(data[0]);
               icdName = data[0].zhongwen_mingcheng;
              return icdName;
            }
        });
    }else{
        return icdName;
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