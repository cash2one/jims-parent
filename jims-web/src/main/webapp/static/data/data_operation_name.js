var operation=[];//手术名称
var operationData={};
operationData.orgId="";
operationData.dictType="operation_dict"

$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(operationData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data) {
        operation = data;
    }
})

function operationNameFormatter(value,rowData,rowIndex){
    if(value == 0){
        return ;
    }
    for(var i=0;i<operation.length;i++){
        if(operation[i].operation_code == value){
            return operation[i].operation_name;
        }
    }
}