/**
 * Created by wei on 2016/5/10.
 */
$(function () {
    var orgId="1";
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#dg").datagrid({
        title: '药品厂商维护',
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        singleSelect: true,
        columns: [[{
            title: '编号',
            field: 'id',
            hidden: 'true'
        }, {
            title: '供应厂商名称',
            field: 'supplierId',
            width: "10%",
            editor: {
                type: 'text', options: {
                    required: true, validType: 'length[0,8]', missingMessage:'请输入四个以内的汉字'}
            }
        }, {
            title: '厂商',
            field: 'supplier',
            width: "10%",
            editor: {
                type: 'text', options: {
                    required: true, validType: 'length[0,8]', missingMessage:'请输入四个以内的汉字'}
            }
        }, {
            title: '厂商类别',
            field: 'supplierClass',
            width: "10%",
            editor: {
                type: 'combogrid', options:{
                    idField: 'supplierClass',
                    textField: 'supplierClass',
                    method: 'GET',
                    url: "/service/drug-supplier-catalog/list-type?orgId="+orgId,
                    mode: 'remote',
                    columns: [[
                        {field:'supplierClass',title:'厂商类别',width:"160px"}
                    ]]
                }
            }
        }, {
            title: '输入码',
            field: 'inputCode',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true, validType: 'length[0,8]', missingMessage:'请输入四个以内的汉字'}
            }
        }, {
            title: '是否国外',
            field: 'foreignx',
            width: "20%",
            type: 'checkbox'

        }, {
            title: '厂商代码',
            field: 'supplierCode',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true, validType: 'length[0,8]', missingMessage:'请输入四个以内的汉字'}
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });


    $('#supplierType').combogrid({
        delay: 150,
        width:'150px',
        mode: 'remote',
        method: 'GET',
        url: '/service/drug-supplier-catalog/list-type?orgId='+orgId,
        idField: 'supplierClass',
        textField: 'supplierClass',
        columns: [[
            {field:'supplierClass',title:'类别',width:"150px",sortable:true}
        ]]
    });

    $('#chk2').click(function(){

            $("#inputCode").textbox('enable');
            $("#supplierType").combogrid('disable');
    });
    $('#chk1').click(function(){

            $("#inputCode").textbox('disable');
            $("#supplierType").combogrid('enable');

    });



    $("#searchBtn").on("click", function () {
        var inputCode=$("#inputCode").textbox("getValue");
        var supplierType=$("#supplierType").textbox("getValue");
        if(supplierType==""){
                $.get("/service/drug-supplier-catalog/list-supplier-inputCode?orgId=" +orgId+"&inputCode="+inputCode, function (data) {

                $("#dg").datagrid('loadData', data);
            });
        }else{
                $.get("/service/drug-supplier-catalog/list-supplier-type?orgId=" + orgId+"&supplierClass="+supplierType, function (data) {

            $("#dg").datagrid('loadData', data);
        });
        }
    });

    $("#addBtn").on('click', function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#delBtn").on('click', function () {
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#dg").datagrid('getRowIndex', row);
            $("#dg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    $("#editBtn").on('click', function () {
        var row = $("#dg").datagrid("getSelected");
        var index = $("#dg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#dg").datagrid("endEdit", editIndex);
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });

    var loadDict = function () {
        //var name = $("#name").textbox("getValue");
        $.get("/service/drug-supplier-catalog/list?orgId="+orgId , function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }

    loadDict();


    /**
     * 保存修改的内容
     * 基础字典的改变，势必会影响其他的统计查询
     * 基础字典的维护只能在基础数据维护的时候使用。
     */
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateDate = $("#dg").datagrid("getChanges", "updated");
        var deleteDate = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;


        if (beanChangeVo) {
            $.postJSON("/api/exp-coding-rule/merge", beanChangeVo, function (data, status) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', data.responseJSON.errorMessage, "error");
            })
        }
    });
})