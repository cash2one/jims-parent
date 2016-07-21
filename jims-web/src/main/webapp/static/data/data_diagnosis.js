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


/**
 * Created by pq on 2016/7/20 0020.
 * 通过病人Id拿到病人姓名
 */
var loginUser={};
loginUser.dictType="persion_info";
loginUser.isOrgId = false;
var currentUser =[];

function formatUserName(value, rowData, rowIndex){

    var inputParamVos=new Array();
    if(value!='' && value!=null){
        var InputParamVo={};
        InputParamVo.colName='id';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
        loginUser.inputParamVos=inputParamVos;
        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(loginUser),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                currentUser = data;
            }
        });
        return currentUser[0].name;
    }else{
      return ;
    }

}