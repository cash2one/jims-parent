//保存

function save(){
    var patientId = parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    $("#patientId").val(patientId);
    $("#visitId").val(visitId);
    formSubmitInput("enterForm");
    var formJson = fromJson('enterForm');
    $.postJSON(basePath+"/enter/save", formJson, function (data) {
        if(data.data=="success"){
           $.messager.alert("提示信息","保存成功");
            $.ajax({
                'type': 'post',
                'url': basePath+'/enter/get',
                'contentType': 'application/json',
                'data': JSON.stringify({"patientId": patientId,"visitId":visitId}),
                'dataType': 'json',
                'success': function(data){
                    $('#enterForm').form('load',data);
                    getDiv('enterForm');

                }
            });
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","error");
    }
}
//查询
$(function(){
    var patientId =parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    $.ajax({
               'type': 'POST',
               'url': basePath+'/enter/get',
               'contentType': 'application/json',
               'data': JSON.stringify({"patientId": patientId,"visitId":visitId}),
               'dataType': 'json',
               'success': function(data){
                   $('#enterForm').form('load',data);
                   getDiv('enterForm');

               }
           });

});


