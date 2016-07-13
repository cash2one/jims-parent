var ordersDrugData={};
ordersDrugData.dictType="v_clinic_item_price";
ordersDrugData.inputParamVos =inputParamVos;


var drugData = [];


/**
 * 药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(ordersDrugData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        drugData = data;
    }
});


//药品自动补全

function ordersDrugCom(q,id){
    var inputParamVos=new Array();
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
        ordersDrugData.inputParamVos=inputParamVos;

    }else{
        $("#"+id).combogrid('setValue','');
    }
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(ordersDrugData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            drugData = data;

        }
    });
}