
$(function() {
    var orgId = 1;
    var editIndex;

    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
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
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.checker = $("#checker").combobox("getValue");
                row.checkerDes = $("#checker").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });
    //获取员工表（用户表），以获取操作者
    $("#antiOperator").combobox({
        url:basePath + '/staffDict/listStaffDict',
        valueField:'empNo',
        textField:'name' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.antiOperator = $("#antiOperator").combobox("getValue");
                row.antiOperatorDes = $("#antiOperator").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });

    //获取包字典下的灭菌方法
    $("#antiWays").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=MJFF",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.antiWays = $("#antiWays").combobox("getValue");
                row.antiWaysDes = $("#antiWays").combobox("getText");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });

    //获取包字典下的灭菌锅号
    $("#boilerNo").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=MJGH",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.boilerNo = $("#boilerNo").combobox("getValue");
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
            });
        }
    });

    //获取包字典下的灭菌锅次
    $("#boilerTimes").combobox({
        url:basePath + '/orgSysDict/findUnits?orgId=' + orgId + "&type=MJGC",
        valueField:'value',
        textField:'label' ,
        method:'GET',
        onSelect:function(){
            var ordersRow = $('#list_data').datagrid("getSelections");
            $.each(ordersRow, function(index, row){
                row.boilerTimes = $("#boilerTimes").combobox("getValue");
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
        method:'GET',
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        onClickCell: onClickCell0,
        columns: [[      //每个列具体内容
            {field: 'belongDept', hidden:true},
            {field: 'id', title: '灭菌', width: '40',align:'center',formatter:function(value){
                return '<input type="checkbox" name="mj" />'
            }},
            {field: 'belongDeptDes', title: '所属科室', width: '8%', align: 'center'},
            {field: 'asepsisCode', title: '代码', width: '7%', align: 'center'},
            {field: 'asepsisName', title: '名称', width: '15%', align: 'center'},
            {field: 'asepsisSpec', title: '规格', width: '4%', align: 'center'},
            {field: 'amount', title: '总数量', width: '5%', align: 'center'},
            {field: 'amountAnti', title: '灭菌数量', width: '5%', align: 'center',editor:{
                type : 'numberbox',
                options:{
                    precision : 0
                }}
            },
            {field: 'units', hidden:true},
            {field: 'unitsDes', title: '单位', width: '3%', align: 'center'},
            {field:'antiOperator',hidden:true},
            {field:'antiOperatorDes',title:'消毒人',width:'5%',align:'center'},
            {field:'antiWays',hidden:true},
            {field:'antiWaysDes',title:'消毒方式',width:'10%',align:'center'},
            {field:'boilerNo',title:'锅号',width:'10%',align:'center'},
            {field:'boilerTimes',title:'锅次',width:'10%',align:'center'},
            {field: 'checker', hidden:true},
            {field: 'checkerDes', title: '查对者', width: '5%', align: 'center'},
            {field: 'packOperator', hidden:true},
            {field: 'packOperatorDes', title: '打包者', width: '5%', align: 'center'},
            {field: 'packWays', hidden:true},
            {field: 'packWaysDes', title: '打包方法', width: '10%', align: 'center'},
            {field: 'sterOperator', hidden:true},
            {field: 'sterOperatorDes', title: '清洗人', width: '5%', align: 'center'},
            {field: 'cleanWays', hidden:true},
            {field: 'cleanWaysDes', title: '清洗方式', width: '10%', align: 'center'},
            {field: 'cleanNo', title: '清洗机号', width: '5%', align: 'center'},
            {field: 'cleanTimes', title: '清洗机次', width: '5%', align: 'center'},
            {field: 'memos', title: '备注', width: '10%', align: 'center'},
            {field: 'asepsisState', hidden:true},
            {field: 'antiDate', hidden:true}
        ]],
        //frozenColumns:[[//列将保持不动，而其他列横向滚动。
        //    {field:'id',checkbox:true,formatter:function(value,row,index){
        //        if (value.tagId){
        //            return value.tagId;
        //        } else {
        //            return value;
        //        }
        //    }}
        //]],
        onClickRow: function (index, row) {
            row.antiOperator = $("#antiOperator").combobox("getValue");
            row.antiWays = $("#antiWays").combobox("getValue");
            row.checker = $("#checker").combobox("getValue");
            row.antiOperatorDes = $("#antiOperator").combobox("getText");
            row.antiWaysDes = $("#antiWays").combobox("getText");
            row.checkerDes = $("#checker").combobox("getText");
            row.boilerNo = $("#boilerNo").combobox("getValue");
            row.boilerTimes = $("#boilerTimes").combobox("getValue");
            row.asepsisState = "3";
            row.antiDate = new Date();
            //$('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row));
            //onClickCell0(index,'amountAnti');
        },
        onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件
            e.preventDefault(); //阻止浏览器捕获右键事件
            $(this).datagrid("clearSelections"); //取消所有选中项
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
        }
        //option:{onClickCell: onClickCell}
    });

    $('#antiDate').datebox({
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
        var b = $("#checker").combobox("getData");
        var listAll = $('#list_data').datagrid('getRows');
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
                    if(row.type=="QXFF"&&row.value == r.cleanWays){
                        r.cleanWaysDes = row.label;
                    }
                    if(row.type=="DBFF"&&row.value == r.packWays){
                        r.packWaysDes = row.label;
                    }
                    if(row.type=="PACKAGE_UNITS"&&row.value == r.units){
                        r.unitsDes = row.label;
                    }
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                });
            });
        });
        $.each(b,function(index,row){
            $.each(listAll, function(i, r){
                if(row.empNo == r.checker){
                    r.checkerDes = row.name;
                }
                if(row.empNo == r.sterOperator){
                    r.sterOperatorDes = row.name;
                }
                if(row.empNo == r.packOperator){
                    r.packOperatorDes = row.name;
                }
                $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
            });
        });
        //$.each(listAll, function(i, r){
        //    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
        //});
    }
    var loadListData = function (flag) {
        if(flag=="1"){
            var belongDept=$("#belongDept").combobox('getValue');
            var asepsisName=$("#asepsisName").combobox('getValue');
            $("#list_data").datagrid({url:basePath+'/asepsisAntiRec/list',queryParams:{"state":"2","orgId":orgId,"belongDept":belongDept,"asepsisName":asepsisName}});
        }else{
        //$("#list_data").datagrid({url:basePath+'/asepsisAntiRec/list',queryParams:{"state":"2","orgId":orgId}});
            $.get(basePath+'/asepsisAntiRec/list',{"state":"2","orgId":orgId},function(res){
                $("#list_data").datagrid('loadData',res)
            })
        }
        setTimeout("loadAnotherData()",1000);
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
            r.asepsisState = "3";
        });
        if(updateData.length<=0){
            alert('请选择需要修改的数据');
            return ;
        }
        if($("#checker").combobox('getValue').length==0){
            alert('请选择查对者');
            return ;
        }
        if($("#antiOperator").combobox('getValue').length==0){
            alert('请选择操作者');
            return ;
        }
        if($("#antiWays").combobox('getValue').length==0){
            alert('请选择灭菌方式');
            return ;
        }
        if($("#boilerNo").combobox('getValue').length==0){
            alert('请选择灭菌锅号');
            return ;
        }
        if($("#boilerTimes").combobox('getValue').length==0){
            alert('请选择灭菌锅次');
            return ;
        }
        var asepsisAntiRecVo = {};
        asepsisAntiRecVo.updated = updateData;
        asepsisAntiRecVo.orgId = orgId;
        if (asepsisAntiRecVo) {
            $.postJSON(basePath + "/asepsisAntiRec/saveClean", JSON.stringify(asepsisAntiRecVo), function (data) {
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






    var editIndex2 = undefined;
    function endEditing0(){
        if (editIndex2 == undefined){return true}
        if ($('#list_data').datagrid('validateRow', editIndex2)){
            $('#list_data').datagrid('endEdit', editIndex2);
            editIndex2 = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell0(index, field){
        //$('#list_data').datagrid('beginEdit', index)
        if (endEditing0()){
            $('#list_data').datagrid('selectRow', index) .datagrid('editCell', {index:index,field:field});
            editIndex2 = index;
        }
    }






























    //
    ////datagrid的单元格编辑
    //$.extend($.fn.datagrid.methods, {
    //    editCell: function (jq, param) {
    //        return jq.each(function () {
    //            var opts = $(this).datagrid('options');
    //            var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
    //            for (var i = 0; i < fields.length; i++) {
    //                var col = $(this).datagrid('getColumnOption', fields[i]);
    //                col.editor1 = col.editor;
    //                if (fields[i] != param.field) {
    //                    col.editor = null;
    //                }
    //            }
    //            $(this).datagrid('beginEdit', param.index);
    //            for (var i = 0; i < fields.length; i++) {
    //                var col = $(this).datagrid('getColumnOption', fields[i]);
    //                col.editor = col.editor1;
    //            }
    //        });
    //    }
    //});
    //var editIndex1 = undefined;
    //function endEditing() {
    //    if (editIndex1 == undefined) {
    //        return true
    //    }
    //    if ($('#list_data').datagrid('validateRow', editIndex1)) {
    //        $('#list_data').datagrid('endEdit', editIndex1);
    //        editIndex1 = undefined;
    //        return true;
    //    } else {
    //        return false;
    //    }
    //}
    //function onClickCell(index, field) {
    //    if (endEditing()) {
    //        $('#list_data').datagrid('selectRow', index) .datagrid('editCell', {index: index, field: field});
    //        editIndex1 = index;
    //    }
    //}





})

/**
 onClickRow中的方法：
 row.antiOperator = null;
 row.antiWays = null;
 row.boilerNo = null;
 row.boilerTimes = null;
 row.checker = null;
 var ordersRow = $('#list_data').datagrid("getSelections");
 $.each(ordersRow, function(i, rowSel){
    row.antiOperator = $("#antiOperator").combobox("getValue");
    row.antiWays = $("#antiWays").combobox("getValue");
    row.boilerNo = $("#boilerNo").combobox("getValue");
    row.boilerTimes = $("#boilerTimes").combobox("getValue");
    row.checker = $("#checker").combobox("getValue");
    row.asepsisState = "3";
    row.antiDate = new Date();
    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
});
 */