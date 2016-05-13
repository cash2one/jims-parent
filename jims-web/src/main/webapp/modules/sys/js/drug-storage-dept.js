/**
 * Created by fyg on 2016/5/12.
 */
$(function(){
    var editIndex ;
    var orgId = parent.config.org_Id;

    var inserted = [];
    var updated = [];
    var deleted = [];

    var stopEdit = function(){
        if(editIndex || editIndex == 0){
            $("#dg").datagrid('endEdit',editIndex);
            editIndex == undefined ;
        }
    }

    var addData = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }
        if ($("#dg").datagrid("getChanges").length > 0) {
            var insertData = $("#dg").datagrid("getChanges", "inserted");
            var updateData = $("#dg").datagrid("getChanges", "updated");
            var deleteData = $("#dg").datagrid("getChanges", "deleted");

            if (insertData && insertData.length > 0) {
                for (var i = 0; i < insertData.length; i++) {
                    inserted.push(insertData[i]);
                }
            }
            if (updateData && updateData.length > 0) {
                for (var i = 0; i < updateData.length; i++) {
                    updated.push(updateData[i]);
                }
            }
            if (deleteData && deleteData.length > 0) {
                for (var i = 0; i < deleteData.length; i++) {
                    deleted.push(deleteData[i]);
                }
            }
        }
    }

    $("#dg").datagrid({
        iconCls: 'icon-edit',
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/drug-storage-dept/list?orgId='+ orgId,
        remoteSort: false,  //定义从服务器对数据进行排序
        singleSelect: true,//是否单选
        columns: [[
            {
                title: 'ID',
                field: 'id',
                hidden: true
            },{
                title: '单位代码',
                field: 'storageCode',
                width: '14%',
                align: 'center'
            },{
                title: '单位名称',
                field: 'storageName',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'combogrid', options: {
                        idField: 'deptName',
                        treeField: "storageName",
                        mode: 'remote',
                        url: basePath + '/dept-dict/list?orgId=' + orgId,
                        method: 'get',
                        columns: [[
                            {title: '科室名称', field: 'deptName', align: 'center', width: '50%'},
                            {title: '科室编码', field: 'deptCode', align: 'center', width: '50%'}
                        ]]
                    }
                }
            }, {
                title: '单位类型',
                field: 'storageType',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'combogrid',options: {
                        idField: 'label',
                        treeField: "label",
                        mode: 'remote',
                        url: basePath + '/dict/label-value-list?type=' + 'DRUG_STOCK_TYPE_DICT',
                        method: 'get',
                        columns: [[
                            {title: '标签',field: 'label',align: 'center', width: '50%'},
                            {title: '键值',field: 'value',align: 'center', width: '50%'}
                        ]]
                    }
                }
            }, {
                title: '付款单前缀',
                field: 'disburseNoPrefix',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'textbox'
                }
            }, {
                title: '当前付款单号',
                field: 'disburseNoAva',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'textbox'
                }
            }, {
                title: '出库单前缀',
                field: 'exportNoPrefix',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'textbox'
                }
            }, {
                title: '入库单前缀',
                field: 'importNoPrefix',
                width: '14%',
                align: 'center',
                editor: {
                    type: 'textbox'
                }
            }
        ]],
        onClickRow: function(rowIndex,rowData){
            stopEdit();
            $("#dg").datagrid('beginEdit',rowIndex);
            editIndex = rowIndex ;
        }
    });

    $('#cc').layout('panel', 'south').panel('resize', {height: 'auto'});
    $("#cc").layout({
        fit: true
    });

    var loadDept = function () {
        $.get(basePath + '/drug-storage-dept/list?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
        //提交完成后重置增删改数据
        inserted = [];
        updated = [];
        deleted = [];
    }

    $("#addBtn").on('click', function(){
        stopEdit();
        $("#dg").datagrid('appendRow',{});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex',rows[rows.length - 1]);
        editIndex = addRowIndex ;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#saveBtn").on('click', function(){
        addData();

        var beanChangeVo = {};

        beanChangeVo.inserted = inserted;// inserted;
        beanChangeVo.deleted = deleted; //deleted;
        beanChangeVo.updated = updated; //updated;
        console.log(beanChangeVo);
        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-storage-dept/save', JSON.stringify(beanChangeVo), function (resp) {
                if (resp.data == 'success') {
                    $.messager.alert("提示消息", "保存成功!");
                    loadDept();
                } else {
                    $.messager.alert('提示', "保存失败", "error");
                    loadDept();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
                loadDept();
            });
        }
    });
});
