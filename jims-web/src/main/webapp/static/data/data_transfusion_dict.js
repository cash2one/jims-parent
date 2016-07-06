var provideWay =[]//性别字典
var provideWayData = {};
provideWayData.orgId="";
provideWayData.dictType="SYS_DICT"

var InputParamVo = {};
var inputParamVos=[];
var q='TRANSFUSION_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='8';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
provideWayData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(provideWayData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        provideWay=data;
    }
});

function sexFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<provideWay.length;i++){
        if(provideWay[i].value == value){
            return provideWay[i].label;
        }
    }
}