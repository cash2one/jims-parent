
$(function() {
    var orgId = 1;
    var editIndex;
    var listAll;

    //获取所属科室
    $("#belongDept").combobox({
        url:basePath + '/dept-dict/list?orgId='+orgId,
        valueField:'deptCode',
        textField:'deptName' ,
        method:'GET'
    });

    //获取包名称
    $("#asepsisName").combobox({
        url:basePath + '/asepsisDict/findList?orgId='+orgId,
        valueField:'asepsisCode',
        textField:'asepsisName' ,
        method:'GET'
    });
    //获取员工表（用户表），以获取查对者
    $("#checker").combobox({
        url:basePath + '/staffDict/listStaffDict',
        valueField:'empNo',
        textField:'name' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getChecked");
            $.each(ordersRow, function(index, row){
                row.checker = $("#checker").combobox("getValue");
                row.checkerDes = $("#checker").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });
    //获取员工表（用户表），以获取操作者
    $("#sterOperator").combobox({
        url:basePath + '/staffDict/listStaffDict',
        valueField:'empNo',
        textField:'name' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.sterOperator = $("#sterOperator").combobox("getValue");
                row.sterOperatorDes = $("#sterOperator").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });

    //获取包字典下的清洗方法
    $("#cleanWays").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=QXFF",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.cleanWays = $("#cleanWays").combobox("getValue");
                row.cleanWaysDes = $("#cleanWays").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });
    //获取包字典下的清洗机号
    $("#cleanNo").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=QXJH",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.cleanNo = $("#cleanNo").combobox("getValue");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });
    //获取包字典下的清洗机次
    $("#cleanTimes").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=QXJC",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.cleanTimes = $("#cleanTimes").combobox("getValue");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '76%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        //fit: true,//自动大小
        method:'GET',
        //url:basePath+'/asepsisAntiRec/list',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field: 'documnetNo', title: '业务单据'},
            {field: 'belongDept', hidden:true},
            {field: 'belongDeptDes', title: '所属科室', width: '8%', align: 'center'},
            //{field: 'belongDeptDes', title: '所属科室', width: '10%', align: 'center'},
            {field: 'asepsisCode', title: '代码', width: '8%', align: 'center'},
            {field: 'asepsisName', title: '名称', width: '12%', align: 'center'},
            {field: 'asepsisSpec', title: '规格', width: '3%', align: 'center'},
            {field: 'amount', title: '数量', width: '3%', align: 'center'},
            {field: 'units', hidden:true},
            {field: 'unitsDes', title: '单位', width: '3%', align: 'center'},
            {field: 'impDate', title: '送物时间', width: '10%', align: 'center',formatter: function(value){
                return parent.formatDatebox(value)
            }},
            {field: 'sterOperator', hidden:true},
            {field: 'sterOperatorDes', title: '清洗人', width: '8%', align: 'center'},
            {field: 'cleanWays', hidden:true},
            {field: 'cleanWaysDes', title: '清洗方式', width: '10%', align: 'center'},
            {field: 'cleanNo', title: '清洗机号', width: '6%', align: 'center'},
            {field: 'cleanTimes', title: '清洗机次', width: '6%', align: 'center'},
            {field: 'checker', hidden:true},
            {field: 'checkerDes', title: '查对者', width: '8%', align: 'center'},
            {field: 'memos', title: '备注', width: '10%', align: 'center'},
            {field: 'asepsisState', hidden:true},
            {field: 'sterDate', hidden:true}
        ]],
        frozenColumns:[[//列将保持不动，而其他列横向滚动。
            {field:'id',checkbox:true}
        ]],
        onDblClickRow: function (rowIndex, rowData) {//双击事件
            parent.addTabs(rowData.name, rowData.name, '/modules/clinic/patientHospital.html');
        },
        onClickRow: function (index, row) {
            //listAll = $('#list_data').datagrid('getRows');
            //$.each(listAll, function(i, rowAll){
            row.sterOperator = $("#sterOperator").combobox("getValue");
            row.checker = $("#checker").combobox("getValue");
            row.cleanWays = $("#cleanWays").combobox("getValue");
            row.sterOperatorDes = $("#sterOperator").combobox("getText");
            row.checkerDes = $("#checker").combobox("getText");
            row.cleanWaysDes = $("#cleanWays").combobox("getText");
            row.cleanNo = $("#cleanNo").combobox("getValue");
            row.cleanTimes = $("#cleanTimes").combobox("getValue");
            row.asepsisState = "1";
            row.sterDate = new Date();
            $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            //});
            //if($(this).checked!="checked"){
            //    alert(1);
            //    row.sterOperator = null;
            //    row.checker = null;
            //    row.cleanWays = null;
            //    row.cleanNo = null;
            //    row.cleanTimes = null;
            //    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            //}
            //var ordersRow = $('#list_data').datagrid("getSelections");
            //$.each(ordersRow, function(i, rowSel){
            //    rowSel.sterOperator = $("#sterOperator").combobox("getValue");
            //    rowSel.checker = $("#checker").combobox("getValue");
            //    rowSel.cleanWays = $("#cleanWays").combobox("getValue");
            //    rowSel.cleanNo = $("#cleanNo").combobox("getValue");
            //    rowSel.cleanTimes = $("#cleanTimes").combobox("getValue");
            //    rowSel.asepsisState = "1";
            //    rowSel.sterDate = new Date();
            //    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",rowSel))
            //});
        },
        onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件
            e.preventDefault(); //阻止浏览器捕获右键事件
            $(this).datagrid("clearSelections"); //取消所有选中项
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
        }
    });
    $('#sterDate').datebox({
        //width: 140,
        editable: false,
        value: parent.formatDatebox(new Date())
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5, 10, 15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    loadAnotherData = function(){
        var a = $("#belongDept").combobox("getData");
        listAll = $('#list_data').datagrid('getRows');
        $.each(a,function(index,row){
            $.each(listAll, function(i, r){
                if(row.deptCode == r.belongDept){
                    r.belongDeptDes = row.deptName;
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                }
            });
        });

        $.get(basePath + "/orgSysDict/findList",{orgId:orgId}, function (data) {
            $.each(data,function(index,row){
                $.each(listAll, function(i, r){
                    if(row.type=="PACKAGE_UNITS"&&row.value == r.units){
                        r.unitsDes = row.label;
                        $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                    }
                });
            });
        });
    }
    var loadListData = function (flag) {
        if(flag=="1"){
            var belongDept=$("#belongDept").combobox('getValue');
            var asepsisName=$("#asepsisName").combobox('getValue');
            $("#list_data").datagrid({url:basePath+'/asepsisAntiRec/list',queryParams:{"state":"0","orgId":orgId,"belongDept":belongDept,"asepsisName":asepsisName}});
        }else{
            $("#list_data").datagrid({url:basePath+'/asepsisAntiRec/list',queryParams:{"state":"0","orgId":orgId}});
        }
        setTimeout("loadAnotherData()",500);
    }
    loadListData();
    $("#searchBtn").on("click",function(){
        loadListData("1");
    });

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#list_data").datagrid("endEdit", editIndex);
        }
        var updateData = $('#list_data').datagrid('getChecked');
        $.each(updateData, function(i, r){
            r.asepsisState = "1";
        });
        if(updateData.length<=0){
            alert('请选择需要修改的数据');
            return ;
        }
        if($("#checker").combobox('getValue').length==0){
            alert('请选择查对者');
            return ;
        }
        if($("#sterOperator").combobox('getValue').length==0){
            alert('请选择操作者');
            return ;
        }
        if($("#cleanWays").combobox('getValue').length==0){
            alert('请选择清洗方式');
            return ;
        }
        if($("#cleanNo").combobox('getValue').length==0){
            alert('请选择清洗机号');
            return ;
        }
        if($("#cleanTimes").combobox('getValue').length==0){
            alert('请选择清洗机次');
            return ;
        }
        var asepsisDictVo = {};
        asepsisDictVo.updated = updateData;
        asepsisDictVo.orgId = orgId;
        if (asepsisDictVo) {
            $.postJSON(basePath + "/asepsisAntiRec/saveClean", JSON.stringify(asepsisDictVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    //$("#list_data").datagrid('reload');
                    //$("#list_data").datagrid("clearSelections");
                    loadListData();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });

})

