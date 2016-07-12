$(function () {
    var editIndex;
    var list=[];
    var classCodeTest=false;
    var classNameTest=false;
    var orgId=parent.config.org_Id;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    $("#dg").datagrid({
        width: '100%',
        //url: "/service/lab-item-dict/list",
        mode: 'remote',
        //method: 'GET',
        singleSelect: true,
        columns: [[{
            title: '编号',
            field: 'id',
            hidden:true
        },{
            title: '类别代码',
            field: 'classCode',
            width: "33%"
        },{
            title: '类别名称',
            field: 'className',
            width: "33%"
        },{
            title: '所属部门',
            field: 'deptCode',
            width: "33%",
            editor:{

            }
        },{
            title: '所属组织结构',
            field: 'orgId',
            hidden:true
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    //var deptNameList=$.get(basePath + '/dept-dict/list');

    var loadDept = function (){
        $.get(basePath + '/labitemclass/list', function (data) {

            $.get(basePath + '/dept-dict/list', function (node) {
                list=data;
                for(var i=0;i<list.length;i++){
                    for (var j=0;j<node.length;j++){
                        if(list[i].deptCode==node[j].deptCode){
                            list[i].deptCode=node[j].deptName;
                        }
                    }
                }
                $("#dg").datagrid('loadData', list);
            })
        });
    }
    loadDept();

//弹出框的下拉框
    $('#deptCode').combogrid({
        delay: 300,
        width:'196px',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/dept-dict/list',
        idField: 'deptCode',
        textField: 'deptName',
        columns: [[
            {field:'deptName',title:'部门名称',width:"150px",sortable:true},
            {field:'deptCode',title:'部门编码',width:"150px",sortable:true}
        ]]
    });


    $("#bottom").datagrid({
        toolbar: '#tb',
        border: false
    });
    $('#cc').layout('panel', 'north').panel('resize', {height: 'auto'});
    $('#cc').layout('panel', 'south').panel('resize', {height: 'auto'});
    $("#cc").layout({
        fit: true
    });

    //清空弹出框
    var reset = function(){
        $("#classCodeChange").html("");
        $("#classNameChange").html("");
        $("#deptCodeChange").html("");
        $("#id").textbox("setValue","");
        $("#classCode").textbox("setValue","");
        $("#className").textbox("setValue","");
        $('#deptCode').combogrid('setValue',"");
    }

    //删除按钮
    $("#delBtn").on("click",function(){
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            $.messager.confirm("系统提示", "确认要删除吗?", function (r){
                if(r){
                    $.post('/service/labitemclass/del?ids='+row.id, function (node) {
                        loadDept();
                    })
                }else{
                    return
                }
            })
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });


//增加按钮
    $("#addBtn").on("click",function(){
        reset();
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增类别');
    });

    //提交数据
    $("#okBtn").on("click",function(){
        var labItemClassDict = {};
        labItemClassDict.id = $("#id").textbox("getValue")
        labItemClassDict.classCode = $("#classCode").textbox("getValue")
        labItemClassDict.className = $("#className").textbox("getValue")
        labItemClassDict.deptCode = $("#deptCode").combogrid('getValue');
        labItemClassDict.orgId=orgId;
        if(classCodeTest &&classNameTest  && labItemClassDict.deptCode.length>0 && labItemClassDict.classCode.length>0 && labItemClassDict.className.length>0 ){

            $.postJSON(basePath + '/labitemclass/save',JSON.stringify(labItemClassDict), function (data) {
                $('#dlg').dialog('close');
                $.messager.alert("系统提示", "保存成功", "info");
                loadDept();
                reset();
            });
        }else {
            if(labItemClassDict.classCode.length==0){
                $("#classCodeChange").css("color","red");
                $("#classCodeChange").html("请输入编号");
            }
            if(labItemClassDict.className.length==0){
                $("#classNameChange").css("color","red");
                $("#classNameChange").html("请输入名称");
            }

            if(labItemClassDict.deptCode.length==0){
                $("#deptCodeChange").css("color","red");
                $("#deptCodeChange").html("请选择部门");
            }
        }

        //$.ajax({
        //    'type': 'post',
        //    'url': basePath + '/lab-item-dict/save',
        //    'contentType': 'application/json',
        //    'data': JSON.stringify(labItemClassDict),
        //    'dataType': 'json',
        //    'success': function (data) {
        //        $('#dlg').dialog('close');
        //            $.messager.alert("系统提示", "保存成功", "info");
        //            $("#dg").datagrid();
        //            reset();
        //    }
        //});
    });

    $("#searchBtn").on("click",function(){
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            $("#id").textbox("setValue",row.id);
            $("#classCode").textbox("setValue",row.classCode);
            $("#className").textbox("setValue",row.className);
            $('#deptCode').combogrid('setValue',row.deptCode);
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改类别');
        } else {
            $.messager.alert('系统提示', "请选择要修改的行", 'info');
        }
    });

    $("#dlgClose").on('click',function(){
        $('#dlg').dialog('close');
        reset();
    })

    //验证classCode
    $('#classCode').textbox({
        onChange: function(value){
            classCodeTest=true;
            var classCodeChange=$("#classCode").textbox("getValue");
            var classId=$("#id").textbox("getValue");
            for(var i=0;i<list.length;i++){
                if(list[i].classCode==classCodeChange && list[i].id !=classId){
                    classCodeTest=false;
                }
            }
            if(classCodeChange.length>0){
                if(classCodeTest){
                    $("#classCodeChange").css("color","green");
                    $("#classCodeChange").html("✔");
                }else{
                    $("#classCodeChange").css("color","red");
                    $("#classCodeChange").html("编号重复，请更改")
                }
            }else{
                classCodeTest=false;
                $("#classCodeChange").css("color","red");
                $("#classCodeChange").html("必填选项")
            }

            if(classCodeChange.length>8){
                $("#classCodeChange").css("color","red");
                $("#classCodeChange").html("编号长度不能超过8位")
            }
        }
    });

    //验证className
    $('#className').textbox({
        onChange: function(value){
            classNameTest=true;
            var classNameChange=$("#className").textbox("getValue");
            var classId=$("#id").textbox("getValue");
            for(var i=0;i<list.length;i++){
                if(list[i].className==classNameChange && list[i].id !=classId){
                    classNameTest=false;
                }
            }
            if(classNameChange.length>0){
                if(classNameTest){
                    $("#classNameChange").css("color","green");
                    $("#classNameChange").html("✔");
                }else{
                    $("#classNameChange").css("color","red");
                    $("#classNameChange").html("名称重复，请更改")
                }
            }else{
                classNameTest=true;
                $("#classNameChange").css("color","red");
                $("#classNameChange").html("必填选项")
            }
        }
    });


    $("#deptCode").combogrid({
        onChange:function(value){
            $("#deptCodeChange").html("");
        }
    })

});
