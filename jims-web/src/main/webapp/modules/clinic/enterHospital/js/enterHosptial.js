//保存

function save(){
    var clinicId=$("#clinicMasterId",parent.document).val();
    $("#patientId").val(clinicId);

    formSubmitInput("enterForm");
    $.postForm(basePath+"/enter/save","enterForm",function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            $.ajax({
                'type': 'post',
                'url': basePath+'/enter/get',
                'contentType': 'application/json',
                'data': patId=clinicId,
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
    var clinicId=$("#clinicMasterId",parent.document).val();
    $.ajax({
               'type': 'POST',
               'url': basePath+'/enter/get',
               'contentType': 'application/json',
               'data': patId=clinicId,
               'dataType': 'json',
               'success': function(data){
                   $('#enterForm').form('load',data);
                   getDiv('enterForm');

               }
           });

});


