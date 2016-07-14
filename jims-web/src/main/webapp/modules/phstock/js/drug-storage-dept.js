/**
 * Created by fyg on 2016/5/12.
 */
$(function () {
    var editIndex;
    var orgId = parent.config.org_Id;
    var storageCode;
    var oldSubStorage;
    var inserted = [];
    var updated = [];
    var deleted = [];

    var storage =[];
    $.ajax({
        url: parent.basePath + '/dict/findListByType',
        data: {type:'DRUG_STOCK_TYPE_DICT'},
        type: 'get',
        async: false
    }).always(function(res){
        if(res){
            storage = res;
        }
    })

    /**
     * 格式化字典表数据
     * @param arr
     * @param value
     */
    var formatDict = function(arr,value){
        if(arr && arr.length > 0 && value != undefined){
            for(var i= 0,j=arr.length;i<j;i++){
                if(arr[i].value == value){
                    return arr[i].label;
                }
            }
        }
        return '';
    }

    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drug-storage").datagrid('endEdit', editIndex);
            $("#drug-sub-storage").datagrid('endEdit', editIndex);
            editIndex == undefined;
        }
    }

    var addData = function () {
        if (editIndex || editIndex == 0) {
            $("#drug-storage").datagrid("endEdit", editIndex);
        }
        if ($("#drug-storage").datagrid("getChanges").length > 0) {
            var insertData = $("#drug-storage").datagrid("getChanges", "inserted");
            var updateData = $("#drug-storage").datagrid("getChanges", "updated");
            var deleteData = $("#drug-storage").datagrid("getChanges", "deleted");

            if (insertData && insertData.length > 0) {
                for (var i = 0; i < insertData.length; i++) {
                    insertData[i].orgId = orgId;
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

    $("#drug-storage").datagrid({
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        toolbar: '#tb',
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/drug-storage-dept/list?orgId=' + orgId,
        singleSelect: true,
        columns: [[
            {
                title: 'ID',
                field: 'id',
                hidden: true
            }, {
                title: '单位代码',
                field: 'storageCode',
                width: '20%',
                align: 'center'
            }, {
                title: '单位名称',
                field: 'storageName',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'combogrid', options: {
                        idField: 'deptName',
                        textField: "storageName",
                        mode: 'remote',
                        url: basePath + '/dept-dict/list?orgId=' + orgId,
                        method: 'get',
                        columns: [[
                            {title: '科室名称', field: 'deptName', align: 'center', width: '30%'},
                            {title: '科室编码', field: 'deptCode', align: 'center', width: '30%'},
                            {title: '拼音码', field: 'inputCode', align: 'center', width: '30%'}
                        ]]
                    }
                }
            }, {
                title: '单位类型',
                field: 'storageType',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'combobox',
                    options: {
                        editable: false,
                        align: 'center',
                        valueField: 'value',
                        textField: 'label',
                        data: storage
                    }
                },formatter: function (value) {
                    return formatDict(storage,value);
                }
            }, {
                title: '付款单前缀',
                field: 'disburseNoPrefix',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'textbox'
                }
            }, {
                title: '当前付款单号',
                field: 'disburseNoAva',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'numberbox'
                }
            }, {
                title: '组织机构',
                field: 'orgId',
                hidden: true
            }
        ]],
        onClickRow: function(index,row){
            $.get(basePath + '/drug-storage-dept/list?orgId=' + orgId,function(data){
                if(index > data.length - 1){
                    stopEdit();
                    $("#drug-storage").datagrid("beginEdit", index);
                }
            });
            stopEdit();
            storageCode = row.storageCode;
            $.get(basePath + '/drug-sub-storage-dept/list-by-storageCode?orgId=' + orgId + '&storageCode=' + row.storageCode, function (data) {
                oldSubStorage = [];
                oldSubStorage = data;
                $("#drug-sub-storage").datagrid('loadData', data);
            });
        }
    });

    var loadStorageDept = function () {
        $.get(basePath + '/drug-storage-dept/list?orgId=' + orgId, function (data) {
            $("#drug-storage").datagrid('loadData', data);
        });
        //提交完成后重置增删改数据
        inserted = [];
        updated = [];
        deleted = [];
    }

    $("#addStorageBtn").on('click', function () {
        stopEdit();
        $("#drug-storage").datagrid('appendRow', {});
        var rows = $("#drug-storage").datagrid('getRows');
        var addRowIndex = $("#drug-storage").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#drug-storage").datagrid('selectRow', editIndex);
        $("#drug-storage").datagrid('beginEdit', editIndex);
    });
    $("#editStorageBtn").on('click', function () {
        var row = $("#drug-storage").datagrid("getSelected");
        var index = $("#drug-storage").datagrid("getRowIndex", row);
        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }
        if (editIndex == undefined) {
            $("#drug-storage").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#drug-storage").datagrid("endEdit", editIndex);
            $("#drug-storage").datagrid("beginEdit", index);
            editIndex = index;
        }
    });
    $("#delStorageBtn").on('click', function () {
        var row = $("#drug-storage").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#drug-storage").datagrid('getRowIndex', row);
            $("#drug-storage").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统警告', "请选择要删除的行", 'error');
        }
    });
    $("#saveStorageBtn").on('click', function () {
        addData();
        var beanChangeVo = {};
        beanChangeVo.inserted = inserted;// inserted;
        beanChangeVo.deleted = deleted; //deleted;
        beanChangeVo.updated = updated; //updated;
        if(beanChangeVo.updated.length > 0){
            $.messager.confirm('系统警告','注意:此修改可能会引起库存子单位的改变,您确定要保存吗?',function(r){
                if(!r){
                    beanChangeVo.updated = null;
                    loadStorageDept();
                    return ;
                }else{
                    $.postJSON(basePath + '/drug-storage-dept/save', JSON.stringify(beanChangeVo), function (resp) {
                        if (resp.data == 'success') {
                            $.messager.alert("提示消息", "保存成功!");
                            loadStorageDept();
                        } else {
                            $.messager.alert('提示', "保存失败", "error");
                            loadStorageDept();
                        }
                    }, function (data) {
                        $.messager.alert('提示', "保存失败", "error");
                        loadStorageDept();
                    });
                }
            });
        }else{
            $.postJSON(basePath + '/drug-storage-dept/save', JSON.stringify(beanChangeVo), function (resp) {
                if (resp.data == 'success') {
                    $.messager.alert("提示消息", "保存成功!");
                    loadStorageDept();
                } else {
                    $.messager.alert('提示', "保存失败", "error");
                    loadStorageDept();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
                loadStorageDept();
            });
        }
    });

    $("#drug-sub-storage").datagrid({
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        toolbar: '#stb',
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        singleSelect: true,
        columns: [[{
            title: 'id',
            field: 'id',
            hidden: true
        },{
            title: '所属组织机构',
            field: 'orgId',
            hidden: true
        },{
            title: '库存单位代码',
            field: 'storageCode',
            align: 'center',
            hidden: true
        },{
            title: '子库房代码',
            field: 'subStorageCode',
            align: 'center',
            width: '14%',
            editor: 'textbox'
        },{
            title: '子库房名称',
            field: 'subStorage',
            align: 'center',
            width: '14%',
            editor: 'textbox'
        },{
            title: '拼音码',
            field: 'inputCode',
            align: 'center',
            width: '14%'
        },{
            title: '入库单前缀',
            field: 'importNoPrefix',
            align: 'center',
            width: '14%',
            editor: 'textbox'
        },{
            title: '入库单可用流水号',
            field: 'importNoAva',
            align: 'center',
            width: '15%',
            editor: 'numberbox'
        },{
            title: '出库单前缀',
            field: 'exportNoPrefix',
            align: 'center',
            width: '14%',
            editor: 'textbox'
        },{
            title: '出库单可用流水号',
            field: 'exportNoAva',
            align: 'center',
            width: '15%',
            editor: 'numberbox'
        }]],
        onClickRow: function(index,row){
            stopEdit();
            $(this).datagrid('beginEdit',index);
            editIndex = index;
        }
    });

    $("#addSubStorageBtn").on("click", function () {
        stopEdit();
        var row = $("#drug-storage").datagrid('getSelected');
        if(row){
            $("#drug-sub-storage").datagrid('appendRow', {});
            var rows = $("#drug-sub-storage").datagrid('getRows');
            var addRowIndex = $("#drug-sub-storage").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#drug-sub-storage").datagrid('selectRow', editIndex);
            $("#drug-sub-storage").datagrid('beginEdit', editIndex);
        }else{
            $.messager.alert('系统提示', "请选择库存单位!", 'info');
        }

    });
    $("#delSubStorageBtn").on("click", function () {
        var row = $("#drug-sub-storage").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#drug-sub-storage").datagrid('getRowIndex', row);
            $("#drug-sub-storage").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行!", 'info');
        }
    });

    $("#saveSubStorageBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#drug-sub-storage").datagrid("endEdit", editIndex);
        }
        var insertData = $("#drug-sub-storage").datagrid("getChanges", "inserted");
        var updateData = $("#drug-sub-storage").datagrid("getChanges", "updated");
        var deleteData = $("#drug-sub-storage").datagrid("getChanges", "deleted");
        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;
        for(var i=0;i<beanChangeVo.inserted.length;i++){
            beanChangeVo.inserted[i].orgId = orgId;
            beanChangeVo.inserted[i].storageCode = storageCode;
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-sub-storage-dept/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadSubStorageDept(storageCode);
                stopEdit();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
                stopEdit();
            })
        }
    });
    //加载库房子单位列表
    var loadSubStorageDept = function (storageCode) {
        $.get(basePath + '/drug-sub-storage-dept/list-by-storageCode?orgId=' + orgId + '&storageCode=' + storageCode, function (data) {
            $("#drug-sub-storage").datagrid('loadData', data);
        });
    }
});
