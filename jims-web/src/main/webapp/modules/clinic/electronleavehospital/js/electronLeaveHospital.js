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
                $.messager.alert("提示信息","保存成功");
            }else{
                $.messager.alert("提示信息","保存失败","error");
            }

        }),function(data){
            $.messager.alert("提示信息","保存失败","error");
        }
    });

    /**
     * 删除某一个菜单
     */
    /*$("#removeBtn").on('click', function () {
        var row = $("#tt").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的菜单");
            return;
        }



        var children = $("#tt").treegrid('getChildren', row.id);
        if (children.length > 0) {
            $.messager.alert("系统提示", "请先删除子菜单");
            return;
        } else {
            if(!row.id){
                $.messager.alert("系统提示","对于新添加的菜单请先刷新在进行删除",'error') ;
                return
            }
            $.messager.confirm("系统提示", "确认删除:【" + row.menuName + "】的菜单吗?", function (r) {
                if (r) {
                    $.post('/api/menu/del/' + row.id, function (data) {
                        $.messager.alert("系统提示", "删除成功");
                        $("#tt").treegrid('remove', row.id);
                    });
                }
            })

        }
    })*/

    /**
     * 修改一个菜单
     */
    /*$("#updateBtn").on('click', function () {

        var node = $("#tt").treegrid('getSelected');
        if (node == null) {
            $.messager.alert("系统提示", "请选中要修改的菜单");
            return;
        }
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改菜单');
        //$('#fm').form('clear');
        $("#menuName").textbox('setValue', node.menuName);
        $("#href").textbox('setValue', node.href);
        $("#parentName").textbox('setValue', node.menuName);
        $("#parentId").textbox('setValue', node._parentId);
        $("#position").textbox('setValue', node.position);
        $("#id").val(node.id);

    });*/

})