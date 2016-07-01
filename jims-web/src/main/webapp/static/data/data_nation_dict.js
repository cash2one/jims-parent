var nation = [];//名族字典
var nationData = {};
nationData.orgId="";
nationData.dictType = "SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q='NATION_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
nationData.inputParamVos = inputParamVos;
$.ajax({
    'type':'POST',
    'url':basePath + '/input-setting/listParam',
    data:JSON.stringify(nationData),
    'contentType':'application/json',
    'dataType':'json',
    'async': false,
    success:function(data){
        nation=data;
    }
})

/**
 * 名族字典翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function nationFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<nation.length; i ++){
        if(nation[i].value == value){
            return nation[i].label;
        }
    }
}