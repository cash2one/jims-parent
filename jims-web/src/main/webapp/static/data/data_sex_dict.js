var sex =[]//性别字典
var sexData = {};
sexData.orgId="";
sexData.dictType="SYS_DICT"

var InputParamVo = {};
var inputParamVos=[];
var q='SEX_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='8';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
sexData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(sexData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        sex=data;
    }
});

function sexFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<sex.length;i++){
        if(sex[i].value == value){
            return sex[i].label;
        }
    }
}