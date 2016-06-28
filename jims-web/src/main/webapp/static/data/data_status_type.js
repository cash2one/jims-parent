var patientStatus =[];//手术病情
var patientStatusData = {};
patientStatusData.orgId ="";
userBloodData.dictType = "SYS_DICT";
userBloodData.inputParamVos = inputParamVos;

$.ajax({
    type:"POST",
    url:basePath +"/input-setting/listParam",
    data:JSON.stringify(patientStatusData),
    contentType:"application/json",
    dataType:"json",
    async:"false",
    success:function(data){
        patientStatus=data;
    }
})

function patientStatusFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<patientStatus.length; i++){
        if(patientStatus[i].id == value){
            return patientStatus[i].LABEL;
        }
    }
}

//function patientStatus