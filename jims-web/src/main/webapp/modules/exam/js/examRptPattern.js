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
            width: '38%',
            editor: {
                type: 'combogrid', options: {
                    idField: 'itemName',
                    textField: 'itemName',
                    method: 'GET',
                    url: basePath +  "/examRptPattern/itemListByOrgId?orgId="+parent.config.org_Id,
                    columns:[[
                        {field:'itemClass',title:'项目类别',width:30},
                        {field:'itemName',title:'项目名称',width:200},
                        {field:'itemCode',title:'项目代码',width:100},
                        {field:'inputCode',title:'输入码',width:80},
                        {field:'sumPrice',title:'价格',width:50}
                    ]],
                    //拼音码检索
                    //filter: function(q, row){
                    //    return row.inputCode.indexOf(q) >= 0 ;
                    //},
                    onSelect: function(rowIndex,rowData){
                        //var examClassName =  $('#examClass').combobox("getValue");
                        //var examSubClassName =  $('#examSubClass').combobox("getValue");
                        //
                        //$.get(basePath + "/examRptPattern/list-by-class?orgId=" + 1+ "&className=" + examClassName+ "&subClassName=" + examSubClassName, function (data) {
                        //    $.each(data, function (index,item) {
                        //        if (item.descriptionCode == rowData.itemCode){
                        //            return;
                        //        }
                        //    })
                        //});
                        var rows = $("#examRptPatternGrid").datagrid("getRows");
                        var row = $("#examRptPatternGrid").datagrid("getSelected");
                        var rowIndex = $("#examRptPatternGrid").datagrid('getRowIndex', row);
                        var flag = 0;
                        $.each(rows, function (index,item) {
                            if (item.descriptionCode == rowData.itemCode){
                                $.messager.alert('系统提示', '项目已存在，请重新选择！', 'info');
                                var objOne = $("#examRptPatternGrid").datagrid("getEditors", rowIndex);
                                $(objOne[0].target).combogrid("setValue","");
                                $(objOne[1].target).textbox("setValue","");
                                flag  = 1;
                            }
                        });
                        if (flag == 0){
                            var obj = $("#examRptPatternGrid").datagrid("getEditor", {index:rowIndex,field:'descriptionCode'});
                            $(obj.target).textbox("setValue",rowData.itemCode);
                        }



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
        }]],
        onDblClickRow: function (rowIndex, rowData) {
            //$('#itemDetail').dialog('open');
            //var url = basePath + "/examRptPattern/findListByItem?orgId=" + parent.config.org_Id + "&clinicItemCode=" + rowData.descriptionCode;
            //console.log(url);
            //$('#itemDetailGrid').datagrid('reload', url);
        }
    });


    //明细列表弹出框
    $('#itemDetail').dialog({
        title: '对照明细',
        width: 700,
        height: 200
    });

    //明细列表
    $("#itemDetailGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers:true,
        method: 'GET',
        url:'',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '类别',
            field: 'itemClass',
            width: '5%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        }, {
            title: '代码',
            field: 'itemCode',
            width: '20%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        }, {
            title: '名称',
            field: 'itemName',
            width: '35%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        }, {
            title: '规格',
            field: 'itemSpec',
            width: '20%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        }, {
            title: '单位',
            field: 'units',
            width: '20%',
            editor:{type:'textbox',options:{
                disabled:true
            }}
        }]]
    });
    //类别项目下拉框   ===============start=============================
    $("#examClass").combobox({
        valueField: 'examClassName',
        textField: 'examClassName',
        method: 'GET',
        url: basePath +  "/examClassDict/listByOrgId?orgId="+parent.config.org_Id,
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examClassName);
                var url = basePath + "/examSubclassDict/list-by-class?orgId=" + parent.config.org_Id + "&className=" + data[0].examClassName;
                $('#examSubClass').combobox('reload', url);
            }

        },
        onSelect: function(rowData) {
            if (editorRow || editorRow == 0) {
                $("#examRptPatternGrid").datagrid('endEdit', editorRow);
                editorRow = undefined;
            }
            $('#examRptPatternGrid').datagrid('loadData', { total: 0, rows: [] });
            $('#examSubClass').combobox('clear');

            var url = basePath + "/examSubclassDict/list-by-class?orgId=" + parent.config.org_Id + "&className=" + rowData.examClassName;
            $('#examSubClass').combobox('reload', url);
        }

    });
    $("#examSubClass").combobox({
        valueField: 'examSubclassName',
        textField: 'examSubclassName',
        method: 'GET',
        url: '',
        //onLoadSuccess: function () {
            //var data = $(this).combobox('getData');
            //if (data.length > 0) {
            //    $(this).combobox('setValue', data[0].examSubclassName);
            //    var url = basePath + "/examRptPattern/list-by-class?orgId=" + parent.config.org_Id+ "&className=" + data[0].examClassName+ "&subClassName=" + data[0].examSubclassName;
            //    $('#examRptPatternGrid').datagrid('reload', url);
            //}
        //},
        onSelect: function(rowData){
            var url = basePath + "/examRptPattern/list-by-class?orgId=" + parent.config.org_Id+ "&className=" + rowData.examClassName+ "&subClassName=" + rowData.examSubclassName;
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
        examRptPatternVo.orgId =  config.org_Id;

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
                    $.postJSON(basePath +  "/examRptPattern/del",row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        var url = basePath + "/examRptPattern/list-by-class?orgId=" + parent.config.org_Id+ "&className=" + examClassName+ "&subClassName=" + examSubClassName;
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