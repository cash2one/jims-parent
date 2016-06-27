
$(function() {
    var clinicId=$("#clinicMasterId",parent.document).val();
    $.ajax({
        'type': 'get',
        'url':basePath + '/clinicMaster/get',
        'contentType': 'application/json',
        'data': {id:clinicId},
        'dataType': 'json',
        'success': function(data){
            $("#patientInfo").form('load',data);
        }
    })
})
function saveInfo(){
    var formJson=fromJson('patientInfo');
    $.postJSON(basePath+"/clinicMaster/savePatInfo",formJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","病人信息保存成功");

            getClinicForRegist()
        }else{
            $.messager.alert("提示信息","病人信息保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","病人信息保存失败","errorzn");
    }
}



