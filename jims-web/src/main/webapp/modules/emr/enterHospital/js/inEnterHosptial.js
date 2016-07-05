//保存

function save(){
    var patientId=$("#patientId",parent.document).val();
    $("#patientId").val(patientId);
    formSubmitInput("enterForm");
    var formJson = fromJson('enterForm');
    $.postJSON(basePath+"/enter/save", formJson, function (data) {
        if(data.code=="1"){
           $.messager.alert("提示信息","保存成功");
            $.ajax({
                'type': 'post',
                'url': basePath+'/enter/get',
                'contentType': 'application/json',
                'data': patId=patientId,
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
    var patientId=$("#patientId",parent.document).val();
    $.ajax({
               'type': 'POST',
               'url': basePath+'/enter/get',
               'contentType': 'application/json',
               'data': patId=patientId,
               'dataType': 'json',
               'success': function(data){
                   $('#enterForm').form('load',data);
                   getDiv('enterForm');

               }
           });

});


