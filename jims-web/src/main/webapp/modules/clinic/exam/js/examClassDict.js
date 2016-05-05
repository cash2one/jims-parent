/**
 * 检查类别维护
 * @author tangxb
 * @version 2016-04-29
 */

$(function () {

    //检查类别
    $("#examClassGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#classft',
        url: basePath +  "/examClassDict/listByOrgId?orgId=1",
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '检查类别编码',
            field: 'examClassCode',
            width: '20%'
        }, {
            title: '检查类别名称',
            field: 'examClassName',
            width: '20%'
        },{
            title: '执行科室',
            field: 'performBy',
            width: '20%'
        },{
            title: '打印格式',
            field: 'printStyle',
            width: '20%'
        },{
            title: '特殊科室',
            field: 'specialtiesDept',
            width: '20%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var options = $("#examSubclassGrid").datagrid('options');
            options.url = basePath + "/examSubclassDict/list-by-class?orgId=" + 1+ "&className=" + rowData.examClassName;
            $("#examSubclassGrid").datagrid('reload');
        }
    });
    //检查子类别
    $("#examSubclassGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers:true,
        url: basePath + "/examSubclassDict/listByOrgId?orgId=" + 1,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title: "examClassName",
            field: "examClassName",
            hidden: true
        }, {
            title: '子类',
            field: 'examSubclassName',
            width:'50%'
        }, {
            title: '拼音码',
            field: 'inputCode',
            width:'50%'
        }]]
    });


    //检查类别模态框
    $("#classWin").window({
        title: '检查分类维护',
        closed: true,
        modal: true
    });
    //检查子类别模态框
    $("#subclassWin").window({
        title: '检查子分类维护',
        closed: true,
        modal: true,
        onClose: function () {
            $("#subclassForm").form('reset');
        },
        onOpen:function(){
            var row  = $("#examClassGrid").datagrid('getSelected') ;
            if(row){
                $("#examSubClassNameParent").combobox('setValue',row.examClassName) ;
            }

        }
    });

    //类别项目下拉框
    $("#examSubClassNameParent").combobox({
        valueField: 'examClassName',
        textField: 'examClassName',
        method: 'GET',
        url: basePath +  "/examClassDict/listByOrgId?orgId=1",
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examClassName);
            }
        }
    });
    //执行科室下拉框
    $("#performBy").combobox({
        valueField: 'deptCode',
        textField: 'deptName',
        method: 'GET',
        url: basePath + "/dept-dict/list",
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].deptCode);
            }
        }
    });
    //打印格式下拉框
    $("#printStyle").combobox({
        valueField: 'value',
        textField: 'text',
        method: 'GET',
        data: [{
            value: '0',
            text: '打印',
            selected: true
        }, {
            value: '1',
            text: '传真'
        }]
    });
    //特殊科室下拉框
    $("#specialtiesDept").combobox({
        valueField: 'value',
        textField: 'text',
        method: 'GET',
        data: [{
            value: '0',
            text: '否',
            selected: true
        }, {
            value: '1',
            text: '是'
        }]
    });

    //添加分类按钮
    $("#addClassBtn").on('click', function () {
        $("#classWin").window('open');
    });
    //取消分类按钮
    $("#cancelClassBtn").on('click', function () {
        $("#classForm").form('reset');
        $("#classWin").window('close');
    });

    //添加子分类按钮
    $("#addBtn").on('click', function () {
        $("#subclassWin").window('open');
    });
    //取消子分类按钮
    $("#cancelSubclassBtn").on('click', function () {
        $("#subclassForm").form('reset');
        $("#subclassWin").window('close');
    });

    //分类项目保存
    $("#saveClassBtn").on('click', function () {

        var saveObj = {};
        saveObj.examClassCode = $("#examClassCode").val();
        saveObj.examClassName = $("#examClassName").val();
        saveObj.inputCode = "";
        saveObj.performBy = $("#performBy").combobox('getValue');
        saveObj.printStyle = $("#printStyle").combobox('getValue');
        saveObj.specialtiesDept = $("#specialtiesDept").combobox('getValue');
        saveObj.orgId = '1';
        console.log(saveObj);
        $.postJSON(basePath + "/examClassDict/save", JSON.stringify(saveObj), function (data) {
            $("#classWin").window('close');
            $.messager.alert('系统提示', '分类添加成功');
            $("#examClassGrid").datagrid('reload');
            $("#classForm").form('reset');
        }, function (data) {
            $.messager.alert('系统提示', '保存失败', 'info');
        });
    });
    //分类项目删除
    $("#removeClassBtn").on('click', function () {
        var row = $("#examClassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", '请选择要删除的项目', 'error');
            return;
        }
        //首先判断有没有子项目
        var promise = $.get(basePath + "/examSubclassDict/list-by-class?orgId=" + 1+ "&className=" + row.examClassName, function (data) {
            if (data.length > 0) {
                $.messager.alert("系统提示", '存在该类别的子类别，不允许删除！', 'error');
                return;
            }

            $.messager.confirm('系统提示','确定要进行删除操作吗',function(r){
                if(r){
                    $.postJSON(basePath + "/examClassDict/del" , row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        $("#examClassGrid").datagrid('reload');
                    });
                }
            });

        })

    });


    //子分类项目保存
    $("#saveSubclassBtn").on('click', function () {
            var saveObj = {};
            saveObj.examClassName = $('#examSubClassNameParent').combobox('getValue');
            saveObj.examSubclassName = $("#examSubClassName").val();
            saveObj.inputCode = "";
            saveObj.orgId = '1';
            saveObj.id =  $("#id").textbox('getValue');


        $.postJSON(basePath + "/examSubclassDict/save", JSON.stringify(saveObj), function (data) {
                $("#subclassWin").window('close');
                $.messager.alert('系统提示', '子分类添加成功');
                $("#examSubclassGrid").datagrid('reload');
                $("#subclassForm").form('reset');
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'info');
            });
    });

     //子分类项目编辑
    $("#editBtn").on('click', function () {
        var row = $("#examSubclassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", "请选择要编辑的记录", "info");
            return;
        }
        $('#examSubClassNameParent').combobox('setValue', row.examClassName);
        $("#examSubClassName").val( row.examSubclassName);
        $("#id").textbox('setValue', row.id);

        $("#subclassWin").window('open');
    });

    //子分类项目删除
    $("#removeBtn").on('click', function () {

        var row = $("#examSubclassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert('系统提示', '请选择要删除的项目', 'info');
            return;
        }

        $.messager.confirm('系统提示','确定要进行删除操作吗',function(r){
            if(r){
                $.postJSON(basePath + "/examSubclassDict/del", row.id, function (data) {
                    $.messager.alert("系统提示", '删除成功', 'info');
                    $("#examSubclassGrid").datagrid('reload');
                })
            }
        });

    });

});