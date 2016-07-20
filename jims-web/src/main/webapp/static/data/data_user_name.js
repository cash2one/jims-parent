/**
 * Created by pq on 2016/7/20 0020.
 * 通过病人Id拿到病人姓名
 */
var loginUser={};
loginUser.dictType="v_clinic_item_price";
var currentUser = [];
function userName(value, rowData, rowIndex){
    var inputParamVos=new Array();
    inputParamVos.push(InputParamVo1);
    if(value!='' && value!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
        loginUser.inputParamVos=inputParamVos;

    }else{

    }
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
}