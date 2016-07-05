$(function () {
    var zhuyuanxinxiId = $("#zhuyuanxinxiId").val();
    if(zhuyuanxinxiId!=null&&zhuyuanxinxiId!=""){
        $.ajax({
            'type': 'post',
            'url': basePath+'/electronleavehospital/get',
            'contentType': 'application/json',
            'data': id=zhuyuanxinxiId,
            'dataType': 'json',
            'success': function(data){
                $('#form').form('load',data);
                getDiv("form");
            }
        });
    }
    $("#saveBtn").on("click",function(){
        formSubmitInput("form");
        $.postForm(basePath+"/electronleavehospital/save","form",function(data){
            if(data.code=="1"){
                $.messager.alert("��ʾ��Ϣ","����ɹ�");
            }else{
                $.messager.alert("��ʾ��Ϣ","����ʧ��","error");
            }

        }),function(data){
            $.messager.alert("��ʾ��Ϣ","����ʧ��","error");
        }
    });


})