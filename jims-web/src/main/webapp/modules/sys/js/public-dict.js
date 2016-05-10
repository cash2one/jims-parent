/**
 * Created by fengyuguang on 2016/5/6.
 */
$(function(){
    var editTypeIndex;
    var editLabelIndex;
    var sysDictType;    //定义字典表类型

    //定义左侧多个操作的增删改数据
    var typeInserted = [];
    var typeUpdated = [];
    var typeDeleted = [];
    //定义右侧多个操作的增删改数据
    var labelInserted = [];
    var labelUpdated = [];
    var labelDeleted = [];
    //定义整体多个操作的增删改数据
    var inserted = [];
    var updated = [];
    var deleted = [];

    var stopTypeEdit = function () {    //停止编辑左侧列表
        if (editTypeIndex || editTypeIndex == 0) {
            $("#descrip").datagrid('endEdit', editTypeIndex);
            editTypeIndex = undefined;
        }
    }
    var stopLabelEdit = function () {   //停止编辑右侧列表
        if (editLabelIndex || editLabelIndex == 0) {
            $("#label_value").datagrid('endEdit', editLabelIndex);
            editLabelIndex = undefined;
        }
    }

    //临时保存增删改数据
    var tempSave = function () {
        if (editTypeIndex || editTypeIndex == 0) {
            $("#descrip").datagrid("endEdit", editTypeIndex);
        }
        if (editLabelIndex || editLabelIndex == 0) {
            $("#label_value").datagrid("endEdit", editLabelIndex);
        }
        var insertedData = {};
        //保存刷新之前的左侧列表里面的增删改数据倒数组里面
        if ($("#descrip").datagrid("getChanges").length > 0) {
            var typeInsertData = $("#descrip").datagrid("getChanges", "inserted");
            var typeUpdateData = $("#descrip").datagrid("getChanges", "updated");
            var typeDeleteData = $("#descrip").datagrid("getChanges", "deleted");
            /*if (typeInsertData && typeInsertData.length > 0) {
                for (var i = 0; i < typeInsertData.length; i++) {
                    if(typeInsertData[i].type == undefined || typeInsertData[i].type == "" || typeInsertData[i].description == undefined || typeInsertData[i].description == ""){
                        $.messager.alert("提示消息", "类型或描述不能为空!");
                        $("#descrip").datagrid('reload');
                        $("#label_value").datagrid('reload');
                    }else{
                        var labelInsertData = $("#label_value").datagrid("getChanges", "inserted");
                        if (labelInsertData && labelInsertData.length > 0) {
                            for (var j = 0; j < labelInsertData.length; j++) {
                                insertedData.type = typeInsertData[i].type;
                                insertedData.description = typeInsertData[i].description;
                                insertedData.label = labelInsertData[j].label;
                                insertedData.value = labelInsertData[j].value;
                                insertedData.sort = labelInsertData[j].sort;
                                insertedData.remarks = labelInsertData[j].remarks;
                                insertedData.inputCode = labelInsertData[j].inputCode;
                                if(insertedData.label == undefined || insertedData.label == ""){
                                    $.messager.alert("提示消息","标签不能为空!");
                                }else if(insertedData.value == undefined || insertedData.value == ""){
                                    $.messager.alert("提示消息", "键值不能为空!");
                                }else if(insertedData.sort == undefined || insertedData.sort == ""){
                                    $.messager.alert("提示消息", "请指定排序!");
                                }else{
                                    inserted.push(insertedData);
                                    console.log("insertedData:" + insertedData.label);
                                }
                            }
                        }
                    }
                }
            }else{
                var labelInsertData = $("#label_value").datagrid("getChanges", "inserted");
                if (labelInsertData && labelInsertData.length > 0) {
                    for (var j = 0; j < labelInsertData.length; j++) {
                        var theRowData = $("#descrip").datagrid('getSelected');
                        insertedData.type = theRowData.type;
                        insertedData.description = theRowData.description;
                        insertedData.label = labelInsertData[j].label;
                        insertedData.value = labelInsertData[j].value;
                        insertedData.sort = labelInsertData[j].sort;
                        insertedData.remarks = labelInsertData[j].remarks;
                        insertedData.inputCode = labelInsertData[j].inputCode;
                        if (insertedData.label == undefined || insertedData.label == "") {
                            $.messager.alert("提示消息", "标签不能为空!");
                        } else if (insertedData.value == undefined || insertedData.value == "") {
                            $.messager.alert("提示消息", "键值不能为空!");
                        } else if (insertedData.sort == undefined || insertedData.sort == "") {
                            $.messager.alert("提示消息", "请指定排序!");
                        } else {
                            inserted.push(insertedData);
                            console.log("insertedData:" + insertedData.label);
                        }
                    }
                }
            }*/
            if (typeUpdateData && typeUpdateData.length > 0) {
                for (var i = 0; i < typeUpdateData.length; i++) {
                    typeUpdated.push(typeUpdateData[i]);
                }
            }
            if (typeDeleteData && typeDeleteData.length > 0) {
                for (var i = 0; i < typeDeleteData.length; i++) {
                    typeDeleted.push(typeDeleteData[i]);
                }
            }
        }

        //保存刷新之前的右侧列表里面的增删改数据倒数组里面
        if ($("#label_value").datagrid("getChanges").length > 0) {
            var labelInsertData = $("#label_value").datagrid("getChanges", "inserted");
            var labelUpdateData = $("#label_value").datagrid("getChanges", "updated");
            var labelDeleteData = $("#label_value").datagrid("getChanges", "deleted");
            if (labelInsertData && labelInsertData.length > 0) {
                $.messager.confirm('提示','您要保存刚才的操作吗?',function(r){
                     if(r){
                        save();
                     }else{
                         loadTypeDict();
                         loadLabelDict();
                     }
                 });
                for (var i = 0; i < labelInsertData.length; i++) {
                    var theRowData = $("#descrip").datagrid('getSelected');
                    console.log("---theRowData.type:" + theRowData.type);
                    //labelInserted.push(labelInsertData[i]);
                    //整合左右两个列表的插入数据
                    insertedData.type = theRowData.type;
                    insertedData.description = theRowData.description;
                    insertedData.label = labelInsertData[i].label;
                    insertedData.value = labelInsertData[i].value;
                    insertedData.sort = labelInsertData[i].sort;
                    insertedData.remarks = labelInsertData[i].remarks;
                    insertedData.inputCode = labelInsertData[i].inputCode;

                    inserted.push(insertedData);
                }
            }
            if (labelUpdateData && labelUpdateData.length > 0) {
                for (var i = 0; i < labelUpdateData.length; i++) {
                    labelUpdated.push(labelUpdateData[i]);
                }
            }
            if (labelDeleteData && labelDeleteData.length > 0) {
                for (var i = 0; i < labelDeleteData.length; i++) {
                    labelDeleted.push(labelDeleteData[i]);
                }
            }
        }
    }

    /**
     * 左侧列表显示字典表的类型和描述字段
     */
    $("#descrip").datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/dict/left-list',
        remoteSort: false,  //定义从服务器对数据进行排序
        idField: 'fldId',
        singleSelect: true,//是否单选
        columns:[[
            {
                title: '类型',
                field: 'type',
                width: '40%',
                align: 'center',
                editor:{
                    type: 'textbox',
                    options:{
                        required: true
                    }
                }
            },
            {
                field: 'description',
                title: '描述',
                width: '60%',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true
                    }
                }
            }
        ]],
        onLoadSuccess: function (data) {
            var defaultRow =  $(this).datagrid('selectRow', 0);   //加载成功默认选中第一行
            var thisRow = $(this).datagrid('getSelected');      //获取被选中的行
            $.get(basePath + "/dict/right-list?type=" + thisRow.type, function (data) {
                $("#label_value").datagrid('loadData', data);
            });
        },
        //点击一行触发事件查询属于该类型的所有数据
        onClickRow:function(rowIndex, rowData){
            stopTypeEdit();
            tempSave();
            sysDictType = rowData.type;  //赋值给字典表类型
            $.get(basePath + "/dict/right-list?type=" + sysDictType,function(data){
                $("#label_value").datagrid('loadData',data);
            });
            $("#label_value").datagrid('clearSelections');
        },
        //用户双击一行时触发事件编辑
        onDblClickRow: function (rowIndex, rowData) {
            stopTypeEdit();
            stopLabelEdit();
            $(this).datagrid('beginEdit', rowIndex);
            editTypeIndex = rowIndex;
        }
    });

    /**
     * 右侧列表显示根据类型查询的数据
     */
    $("#label_value").datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        remoteSort: false,  //定义从服务器对数据进行排序
        idField: 'fldId',
        singleSelect: false,//是否单选
        columns: [[
            {
                field: 'id',
                title: 'ID',
                hidden: true
            },
            {
                field: 'label',
                title: '标签',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true
                    }
                }
            },
            {
                field: 'value',
                title: '键值',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true
                    }
                }
            },
            {field: 'remarks', title: '备注', width: '20%', align: 'center', editor: 'text'},
            {field: 'inputCode', title: '输入码', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'sort',
                title: '排序',
                width: '20%',
                align: 'center',
                editor: {
                    type: 'numberbox',
                    options: {
                        required: true
                    }
                }
            }
        ]],
        onLoadSuccess: function (data) {
            $(this).datagrid('selectRow', 0);   //加载成功默认选中第一行
        },
        onClickRow:function(rowIndex, rowData){
            stopTypeEdit();
            //tempSave();
            if(editLabelIndex != null || editLabelIndex != undefined){
                stopLabelEdit();    //点击一行选中前，先判断有没有别的行处于编辑状态，如果有，则停止编辑
            }

        },
        onDblClickRow: function (rowIndex, rowData) {   //双击一行触发事件编辑
            stopTypeEdit();
            stopLabelEdit();
            $(this).datagrid('clearSelections');    //编辑此行前先清空选择的其他行
            $(this).datagrid('beginEdit', rowIndex);
            editLabelIndex = rowIndex;
        }
    });

    $("#bottom").datagrid({
        footer: '#ft',
        border: false
    });
    $('#cc').layout('panel', 'south').panel('resize', {height: 'auto'});

    $("#cc").layout({
        fit: true
    });

    var loadTypeDict = function () {
        //提交成功后刷新左侧datagrid
        $.get(basePath + '/dict/left-list', function (data) {
            $("#descrip").datagrid('loadData', data);
        });
        //提交完成后重置增删改数据
        typeInserted = [];
        typeUpdated = [];
        typeDeleted = [];
    }

    var loadLabelDict = function () {
        //提交成功后刷新右侧datagrid
        var thisTypeData = $("#descrip").datagrid('getSelected');
        sysDictType = thisTypeData.type;
        console.log(sysDictType);
        $.get(basePath + "/dict/right-list?type=" + sysDictType, function (data) {
            $("#label_value").datagrid('loadData', data);
        });
        //提交完成后重置增删改数据
        labelInserted = [];
        labelUpdated = [];
        labelDeleted = [];
    }

    /**
     * 添加字典
     */
    $("#addTypeBtn").on('click', function () {
        stopTypeEdit();
        stopLabelEdit();
        //增加字典
        $('#descrip').datagrid('appendRow',{});
        var typeRows = $("#descrip").datagrid('getRows');
        var addTypeRowIndex = $("#descrip").datagrid('getRowIndex', typeRows[typeRows.length - 1]);
        editTypeIndex = addTypeRowIndex;
        $("#descrip").datagrid('selectRow', editTypeIndex);  //选择新增加的一行
        $("#descrip").datagrid('beginEdit', editTypeIndex);  //开始编辑新增加的行数据

        $("#label_value").datagrid('loadData', {total: 0, rows: []});   //清空右侧表格数据
        //增加键值
        $('#label_value').datagrid('appendRow', {});
        var labelRows = $("#label_value").datagrid('getRows');
        var addLabelRowIndex = $("#label_value").datagrid('getRowIndex', labelRows[labelRows.length - 1]);
        editLabelIndex = addLabelRowIndex;
        $("#label_value").datagrid('selectRow', editLabelIndex);  //选择新增加的一行
        $("#label_value").datagrid('beginEdit', editLabelIndex);  //开始编辑新增加的行数据

    });
    /**
     * 添加键值
     */
    $("#addChildBtn").on('click',function(){
        stopTypeEdit();
        stopLabelEdit();
        $('#label_value').datagrid('appendRow', {});
        var labelRows = $("#label_value").datagrid('getRows');
        var addLabelRowIndex = $("#label_value").datagrid('getRowIndex', labelRows[labelRows.length - 1]);
        editLabelIndex = addLabelRowIndex;
        $("#label_value").datagrid('selectRow', editLabelIndex);  //选择新增加的一行
        $("#label_value").datagrid('beginEdit', editLabelIndex);  //开始编辑新增加的行数据
    });
    //批量删除
    $("#delBtn").on('click', function () {
        stopTypeEdit();
        stopLabelEdit();
        tempSave();
        var deleteRows = $("#label_value").datagrid('getSelections');	//获取所有被选中的行，即要删除的所有行
        if (deleteRows.length < 1) {
            $.messager.alert("提示消息", "请选中要删的数据!");
            return;
        }
        $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
            alert("r:" + r);
            if(r){
                for (var i = 0; i < deleteRows.length; i++) {
                    var rowIndex = $("#label_value").datagrid('getRowIndex', deleteRows[i]);
                    $("#label_value").datagrid('deleteRow', rowIndex);
                }
            }
        })
    });

    /**
     * 保存改动的内容
     */
    $("#saveBtn").on('click', save());

    function save(){
        tempSave();
        //提交右侧刷新过的多个datagrid的增删改数据
        var beanChangeVo = {};
        beanChangeVo.inserted = inserted;// inserted;
        beanChangeVo.deleted = deleted; //deleted;
        beanChangeVo.updated = updated; //updated;

        if (beanChangeVo) {
            $.postJSON(basePath + '/dict/merge', beanChangeVo, function (resp) {
                console.log("resp.data:" + resp.data);
                //$.messager.alert("系统提示", "保存成功", "info");
            }, function (data) {
                console.log("data:" + data);
                //$.messager.alert('系统警告', data.responseJSON.errorMessage, "error");
            });
        }
    }
});
