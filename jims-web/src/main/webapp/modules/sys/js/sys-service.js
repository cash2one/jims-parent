/**
 * 系统服务维护
 * @author txb
 * @version 2016-05-31
 */

$(function () {
    var editIndex = undefined;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    /**
     * 服务数据框
     */
    $("#serviceDg").datagrid({
        title: '基础服务维护',
        fit: true,
        fitColumns: true,
        //striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "服务名称",
            field: "serviceName",
            width: '11%',
            align: 'center',
            editor:{
                type:"textbox",options:{

                }
            }

        }, {
            title: "服务描述",
            field: "serviceDescription",
            width: '11%',
            align: 'center',
            editor:{
                type:"textbox",options:{

                }
            }
        }, {
            title: "服务类型",
            field: "serviceType",
            width: '11%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '无偿服务'
                }else if(value == "1"){
                    return '有偿服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务类别",
            field: "serviceClass",
            width: '11%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '机构服务'
                }else if(value == "1"){
                    return '个人服务'
                }else if(value == "2"){
                    return '所有服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务图片",
            field: "serviceImage",
            width: '11%',
            align: 'center'
        }]]
    });

    /**
     * 服务弹出框
     */
    $("#serviceDialog").dialog({
        title: '基础服务增加',
        width: 1000,
        height: 350,
        closed:true

    });

    /**
     * 服务类型
     */
    $("#serviceType").combobox({
        valueField:"value",
        textField:"text",
        data: [{
            text: '无偿服务',
            value: "0"
        },{
            text: '有偿服务',
            value: "1",
            selected:true
        }]
    });
    /**
     * 服务类别
     */
    $("#serviceClass").combobox({
        valueField:'value',
        textField:'text',
        data: [{
            text: '机构服务',
            value: "0"
        },{
            text: '个人服务',
            value: "1",
            selected:true
        },{
            text: '所有服务',
            value: "2"
        }]
    });
    /**
     * 服务定位
     */
    $("#serviceNameLocation").textbox({
        buttonIcon:"icon-search",
        onClickButton: function () {
            var rows = $("#serviceDg").datagrid("getRows");
            var value = $("#serviceNameLocation").textbox("getValue");
            $.each(rows, function (index,row) {
                if(row.serviceName.indexOf(value) != -1){
                    $("#serviceDg").datagrid("scrollTo",index);
                    $("#serviceDg").datagrid("selectRow",index);
                }
            });
        }
    });
    /**
     * 文件上传
     */
    $("#serviceImage").filebox({
        buttonText: '选择文件',
        buttonAlign: 'right'
    });
    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        reset();
        $("#serviceType").combobox("setValue","0");
        $("#serviceClass").combobox("setValue","0");
        $("#serviceDialog").dialog("open");
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        reset();
        var row = $("#serviceDg").datagrid("getSelected");
        $("#id").textbox("setValue",row.id);
        $("#serviceName").textbox("setValue",row.serviceName);
        $("#serviceType").combobox("setValue",row.serviceType);
        $("#serviceClass").combobox("setValue",row.serviceClass);
        //service.serviceImage = $("#serviceImage").filebox("setValue",row.serviceImage);
        $("#serviceDescription").val(row.serviceDescription);
        $("#serviceDialog").dialog("open");

    });

    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        stopEdit();
        var row = $("#serviceDg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }
        if (!row.id) {
            //判断是否是新加项目
            var index = $("#serviceDg").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#serviceDg").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/sys-service/del", row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        loadDict();
                    })
                }
            });
        }

    });

    /**
     * 保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#serviceName").textbox("getValue")){
            $.messager.alert("提示","请输入必填项",'error');
            return ;
        }
        var service = {};
        service.id = $("#id").textbox("getValue");
        service.serviceName = $("#serviceName").textbox("getValue");
        service.serviceType = $("#serviceType").combobox("getValue");
        service.serviceClass = $("#serviceClass").combobox("getValue");
        service.serviceImage = $("#serviceImage").filebox("getValue");
        service.serviceDescription = $("#serviceDescription").val();
        $.postJSON(basePath + "/sys-service/save", JSON.stringify(service), function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            loadDict();
            reset();
        })


    });

    /**
    *基础服务价格模态
    */
    $("#serviceDetailDialog").dialog({
        title: '基础服务价格',
        width: 1000,
        height: 350,
        closed:true

    });
    /**
     *基础服务价格数据框
     */
     $("#serviceDetailDg").datagrid({
        fit: true,
        fitColumns: true,
        //striped: true,
        singleSelect: true,
        toolbar: '#serviceDetailTb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title:"serviceId",
            field:"serviceId",
            hidden:true
        },{
            title:"服务价格",
            field:"servicePrice",
            width:"40%",
            editor:{
                type:"textbox",options:{

                }
            }
        },{
            title:"服务时限",
            field:"serviceTimeLimit",
            width:"40%",
            editor:{
                type:"combobox",options:{
                    valueField:"value",
                    textField:"text",
                    data:[{
                        text:"年",
                        value:"年"
                    },{
                        text:"季",
                        value:"季"
                    },{
                        text:"月",
                        value:"月"
                    },{
                        text:"日",
                        value:"日"
                    }]
                }
            }
        }]]

    });

    /**
     * 明细维护
     */
    $("#detailBtn").on("click", function () {
        $("#serviceDetailDg").datagrid("loadData",{total:0,rows:[]});
        var row = $("#serviceDg").datagrid("getSelected");
        if(row){
            var url = basePath + "/sys-service/detail-list?serviceId=" + row.id;
            $("#serviceDetailDg").datagrid("reload",url);
            $("#serviceDetailDialog").dialog("open");
        }else{
            $.messager.alert("提示","请选择一个服务项目","info");
        }

    });
    /**
     * 基础服务价格添加
     */
    $("#addDetailBtn").on("click", function () {
        stopEdit();
        var row = $("#serviceDg").datagrid("getSelected");
        $("#serviceDetailDg").datagrid("appendRow",{serviceId:row.id,serviceTimeLimit:'月'});
        var rows = $("#serviceDetailDg").datagrid("getRows");
        var addRowIndex = $("#serviceDetailDg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#serviceDetailDg").datagrid('selectRow', editIndex);
        $("#serviceDetailDg").datagrid('beginEdit', editIndex);


    });
    /**
     * 基础服务价格修改
     */
    $("#editDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid("getSelected");
        var index = $("#serviceDetailDg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });
    /**
     * 基础服务价格删除
     */
    $("#delDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#serviceDetailDg").datagrid('getRowIndex', row);
            $("#serviceDetailDg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });
    /**
     * 基础服务价格保存
     */
    $("#submitDetailBtn").on("click",function(){
        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
        }
        var insertData = $("#serviceDetailDg").datagrid("getChanges", "inserted");
        var updateDate = $("#serviceDetailDg").datagrid("getChanges", "updated");
        var deleteDate = $("#serviceDetailDg").datagrid("getChanges", "deleted");

        var priceBeanVo = {};
        priceBeanVo.inserted = insertData;
        priceBeanVo.deleted = deleteDate;
        priceBeanVo.updated = updateDate;
        //console.log(priceBeanVo);
        //console.log(JSON.stringify(priceBeanVo));
        if (priceBeanVo) {
            $.postJSON(basePath + "/sys-service/save-detail", JSON.stringify(priceBeanVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDetail()
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#serviceDetailDg').datagrid('loadData', { total: 0, rows: [] });
            })
        }
    });




    /**
     * 菜单维护
     */
    $("#menuBtn").on("click", function () {

    });

    /**
     * 加载服务
     */
    var loadDict = function () {
        $.get(basePath + "/sys-service/list" , function (data) {
            $("#serviceDg").datagrid('loadData', data);
        });
    };
    var loadDetail = function () {
        var row = $("#serviceDg").datagrid("getSelected");
        $.get(basePath + "/sys-service/detail-list?serviceId=" + row.id , function (data) {
            $("#serviceDetailDg").datagrid('loadData', data);
        });
    };
    var reset = function () {
        $("#id").textbox("setValue","");
        $("#serviceName").textbox("setValue","");
        $("#serviceType").combobox("setValue","");
        $("#serviceClass").combobox("setValue","");
        //service.serviceImage = $("#serviceImage").filebox("setValue",row.serviceImage);
        $("#serviceDescription").val("");
    };

    loadDict();

});