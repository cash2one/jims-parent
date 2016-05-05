/**
 * 菜单
 * @author tangxb
 * @version 2016-05-03
 */


$(function () {
    var editorRow = undefined; //编辑标志
    //检查项目
    $("#examRptPatternGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers:true,
        toolbar: '#tb',
        url: '',
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '项目名称',
            field: 'description',
            width: '35%',
            editor: {
                type: 'combogrid', options: {
                    valueField: 'itemCode',
                    textField: 'itemName',
                    method: 'GET',
                    url: basePath +  "/clinicItem/itemListByOrgId?orgId=1",
                    columns:[[
                        {field:'项目类别',title:'itemClass',width:60},
                        {field:'项目名称',title:'itemName',width:100},
                        {field:'项目代码',title:'itemCode',width:120},
                        {field:'输入码',title:'inputCode',width:100},
                        {field:'价格',title:'sumPrice',width:100}
                    ]],
                    onSelect: function(rowData){
                        $.get(basePath +  "/clinicItem/itemListByOrgId?orgId=1", function (data) {
                            var selectedRow = $("#examRptPatternGrid").datagrid("getSelected");
                            var rowIndex = $("#examRptPatternGrid").datagrid('getRowIndex',selectedRow);
                            var obj = $("#examRptPatternGrid").datagrid("getEditor", {index:rowIndex,field:'descriptionCode'});
                            $(obj.target).textbox("setValue",rowData.itemCode);
                        });
                    }
                }
            }
        }, {
            title: '项目代码',
            field: 'descriptionCode',
            width: '35%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        },{
            title: '输入码',
            field: 'inputCode',
            width: '30%',
            hidden: true
        }]]
        //onDblClickRow: function (rowIndex, rowData) {
        //    var options = $("#examSubclassGrid").datagrid('options');
        //    options.url = basePath + "/examSubclassDict/list-by-class?orgId=" + 1+ "&className=" + rowData.examClassName;
        //    $("#examSubclassGrid").datagrid('reload');
        //}
    });

    //类别项目下拉框   ===============start=============================
    $("#examClass").combobox({
        valueField: 'examClassName',
        textField: 'examClassName',
        method: 'GET',
        url: basePath +  "/examClassDict/listByOrgId?orgId=1",
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examClassName);
            }
        },
        onSelect: function(rowData){
            if (editorRow || editorRow == 0) {
                $("#examRptPatternGrid").datagrid('endEdit', editorRow);
                editorRow = undefined;
            }
            $('#examRptPatternGrid').datagrid('reload');
            $('#examSubClass').combobox('clear');
            var url = basePath + "/examSubclassDict/list-by-class?orgId=" + 1+ "&className=" + rowData.examClassName;
            $('#examSubClass').combobox('reload', url);
        }
    });
    $("#examSubClass").combobox({
        valueField: 'examSubclassName',
        textField: 'examSubclassName',
        method: 'GET',
        url: '',
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examSubclassName);
                var url = basePath + "/examRptPattern/list-by-class?orgId=" + 1+ "&className=" + data[0].examClassName+ "&subClassName=" + data[0].examSubclassName;
                $('#examRptPatternGrid').datagrid('reload', url);
            }
        },
        onSelect: function(rowData){
            var url = basePath + "/examRptPattern/list-by-class?orgId=" + 1+ "&className=" + rowData.examClassName+ "&subClassName=" + rowData.examSubclassName;
            $('#examRptPatternGrid').datagrid('reload', url);
        }
    });
    //类别项目下拉框   ===============end=============================



    /**
     * 保存
     */
    $("#saveBtn").on('click', function () {
        if (editorRow || editorRow == 0) {
            $("#examRptPatternGrid").datagrid("endEdit", editorRow);
        }

        var insertData = $("#examRptPatternGrid").datagrid("getChanges", "inserted");
        var updateData = $("#examRptPatternGrid").datagrid("getChanges", "updated");
        var examRptPatternVo = {};
        examRptPatternVo.inserted = insertData;
        examRptPatternVo.updated = updateData;
        examRptPatternVo.examClass =  $('#examClass').combobox("getValue");
        examRptPatternVo.examSubClass =  $('#examSubClass').combobox("getValue");
        examRptPatternVo.orgId =  1;

        $.postJSON(basePath +  "/examRptPattern/saveList",JSON.stringify(examRptPatternVo), function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
        })



    });

    /**
     * 添加项目
     */
    $("#addBtn").on('click', function () {
        var examClassName = $("#examClass").combobox('getValue');
        var examSubClassName = $("#examSubClass").combobox('getValue');
        if (!examClassName|| !examSubClassName) {
            $.messager.alert("系统提示", "请先选择检查类别或子类！");
            return;
        }
        if (examClassName && examSubClassName) {
            if (editorRow || editorRow == 0) {
                $("#examRptPatternGrid").datagrid('endEdit', editorRow);
                editorRow = undefined;
            }

            $("#examRptPatternGrid").datagrid('appendRow', {});
            var rows = $("#examRptPatternGrid").datagrid('getRows');
            if (editorRow == 0 || editorRow > 0) {
                $("#examRptPatternGrid").datagrid('endEdit', editorRow);
                editorRow = $("#examRptPatternGrid").datagrid('getRowIndex', rows[rows.length - 1]);
            } else {
                editorRow = $("#examRptPatternGrid").datagrid('getRowIndex', rows[rows.length - 1]);
            }
            $("#examRptPatternGrid").datagrid('selectRow', editorRow);
            $("#examRptPatternGrid").datagrid('beginEdit', editorRow);
        }
    });


    /**
     * 删除某一个菜单
     */
    $("#removeBtn").on('click', function () {
        var examClassName = $("#examClass").combobox('getValue');
        var examSubClassName = $("#examSubClass").combobox('getValue');

        var row = $("#examRptPatternGrid").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }



        if (!row.id) {
            //判断是否是新加项目
            var index = $("#examRptPatternGrid").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#examRptPatternGrid").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath +  "/examClassDict/del",row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        var url = basePath + "/examRptPattern/list-by-class?orgId=" + 1+ "&className=" + examClassName+ "&subClassName=" + examSubClassName;
                        $('#examRptPatternGrid').datagrid('reload', url);
                    })
                }
            });

        }
    });

    /**
     * 修改一个菜单
     */
    $("#updateBtn").on('click', function () {

        var selectedRow = $("#examRptPatternGrid").datagrid('getSelected');
        var index = $("#examRptPatternGrid").datagrid("getRowIndex", selectedRow);

        if (index == -1) {
            $.messager.alert("系统提示", "请选中要修改的菜单");
            return;
        }
        if (editorRow == undefined) {
            $("#examRptPatternGrid").datagrid("beginEdit", index);
            editorRow = index;
        } else {
            $("#examRptPatternGrid").datagrid("endEdit", editorRow);
            $("#examRptPatternGrid").datagrid("beginEdit", index);
            editorRow = index;
        }

    });
});