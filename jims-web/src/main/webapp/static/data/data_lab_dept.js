var performedBy=[];
var performedByDate={};
performedByDate.orgId="";

/**
 * 检验科室
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/dept-dict/getList' ,
    data: JSON.stringify(performedByDate),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        performedBy=data;
    }
});

function performedBFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
     for(var i=0;i<performedBy.length;i++){
            if(performedBy[i].deptCode == value){
                return performedBy[i].deptName;
            }
      }
}
