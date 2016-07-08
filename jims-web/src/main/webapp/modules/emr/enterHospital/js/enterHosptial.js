//保存
var clinId=parent.clinicMaster.id;
var pId = parent.clinicMaster.patientId;
function save(){
    $("#zhenduan").datagrid('endEdit', rowNum1);
    if (rowNum1 != -1) {
        $("#zhenduan").datagrid("endEdit", rowNum1);
    }
    var rows = $('#zhenduan').datagrid('getRows');
    $("#clinicId").val(clinId);
    $("#patientId").val(pId);
    formSubmitInput("enterForm");
    var formJson = fromJson('enterForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson = JSON.stringify(rows);
    var submitJson = formJson + ",\"diagnosisList\":" + tableJson + "}";
    $.postJSON(basePath+"/enter/save", submitJson, function (data) {
        if(data.data=="success"){
           $.messager.alert("提示信息","保存成功");
            $.ajax({
                'type': 'post',
                'url': basePath+'/enter/get',
                'contentType': 'application/json',
                'data': JSON.stringify({"clinicId": clinId}),
                'dataType': 'json',
                'success': function(data){
                  /*  $("#diagnosisParent").val(data.id);*/
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
    var clinId=parent.clinicMaster.id;
    $.ajax({
               'type': 'POST',
               'url': basePath+'/enter/get',
               'contentType': 'application/json',
               'data':JSON.stringify({"clinicId": clinId}),
               'dataType': 'json',
               'success': function(data){
                   $('#enterForm').form('load',data);
                   getDiv('enterForm');

               }
           });

});


