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

    /**
     * ɾ��ĳһ���˵�
     */
    /*$("#removeBtn").on('click', function () {
        var row = $("#tt").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("ϵͳ��ʾ", "��ѡ��Ҫɾ���Ĳ˵�");
            return;
        }



        var children = $("#tt").treegrid('getChildren', row.id);
        if (children.length > 0) {
            $.messager.alert("ϵͳ��ʾ", "����ɾ���Ӳ˵�");
            return;
        } else {
            if(!row.id){
                $.messager.alert("ϵͳ��ʾ","��������ӵĲ˵�����ˢ���ڽ���ɾ��",'error') ;
                return
            }
            $.messager.confirm("ϵͳ��ʾ", "ȷ��ɾ��:��" + row.menuName + "���Ĳ˵���?", function (r) {
                if (r) {
                    $.post('/api/menu/del/' + row.id, function (data) {
                        $.messager.alert("ϵͳ��ʾ", "ɾ���ɹ�");
                        $("#tt").treegrid('remove', row.id);
                    });
                }
            })

        }
    })*/

    /**
     * �޸�һ���˵�
     */
    /*$("#updateBtn").on('click', function () {

        var node = $("#tt").treegrid('getSelected');
        if (node == null) {
            $.messager.alert("ϵͳ��ʾ", "��ѡ��Ҫ�޸ĵĲ˵�");
            return;
        }
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '�޸Ĳ˵�');
        //$('#fm').form('clear');
        $("#menuName").textbox('setValue', node.menuName);
        $("#href").textbox('setValue', node.href);
        $("#parentName").textbox('setValue', node.menuName);
        $("#parentId").textbox('setValue', node._parentId);
        $("#position").textbox('setValue', node.position);
        $("#id").val(node.id);

    });*/

})