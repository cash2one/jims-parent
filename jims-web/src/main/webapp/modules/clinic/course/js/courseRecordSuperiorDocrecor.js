//保存
function save(){
    formSubmitInput("courseRecordForm");
    $.postForm(basePath+"/courseRecordSuperiorDocrecor/save","courseRecordForm",function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","error");
    }
}
