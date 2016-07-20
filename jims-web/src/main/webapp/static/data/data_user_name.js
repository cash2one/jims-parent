/**
 * Created by pq on 2016/7/20 0020.
 * 通过病人Id拿到病人姓名
 */
var loginUser={};
loginUser.dictType="persion_info";
var currentUser ;

function formatUserName(value, rowData, rowIndex){
    alert("value="+value);
    var inputParamVos=new Array();
    if(value!='' && value!=null){
        var InputParamVo={};
        InputParamVo.colName='id';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
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
            currentUser = data.name;

        }
    });
}