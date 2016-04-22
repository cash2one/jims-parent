//保存
function save(){
    formSubmitInput("enterForm");
    $.postForm(basePath+"/enter/save","enterForm",function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","error");
    }
}
//查询
$("#look").on("click",function(){
    var id="10d4d0beef2b419cacfe806e96117ae8";
    $.ajax({
               'type': 'post',
               'url': basePath+'/enter/get',
               'contentType': 'application/json',
               'data': id=id,
               'dataType': 'json',
               'success': function(data){
                   $('#enterForm').form('load',data);
                   getDiv('enterForm');


               }
           });

});


