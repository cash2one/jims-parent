$(function () {
    var patientId = parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    $("#patientId").val(patientId);
    $("#visitId").val(visitId);

        $.ajax({
            'type': 'post',
            'url': basePath+'/electronleavehospital/get',
            'contentType': 'application/json',
            'data':JSON.stringify({"patientId": patientId,"visitId":visitId}),
            'dataType': 'json',
            'success': function(data){
                $('#form').form('load',data);
                getDiv("form");
            }
        });

    $("#saveBtn").on("click",function(){
        formSubmitInput("form");
        $.postForm(basePath+"/electronleavehospital/save","form",function(data){
            if(data.data=="success"){
                $.messager.alert("保存成功");
            }else{
                $.messager.alert("保存失败","error");
            }

        }),function(data){
            $.messager.alert("提示信息","保存失败","error");
        }
    });


})