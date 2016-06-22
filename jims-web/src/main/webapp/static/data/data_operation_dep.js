var operatingRoom=[];//手术室
var operatingDate={};
operatingDate.orgId="";

$.ajax({
    'type':'POST',
    'url':basePath+'/dept-dict/getOperation',
    'data':JSON.stringify(operatingDate),
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        operatingRoom=data;
    }
})

function operationgFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i=0; i<operatingRoom.length; i++){
        if(operatingRoom[i].deptCode == value){
            return operatingRoom[i].deptName;
        }
    }
}