
$(function() {
    var orgId = 1;
    var fp = 2;
    var currentSelectIndex = undefined;
    var orgSysDictData = [];//全部字典表数据
    var orgSysDictSubData = [];//字典表部分数据
    var orgSysDictDataQxff = [];//字典表（清洗方法）数据
    var orgSysDictDataQxjh = [];//字典表（清洗机号）数据
    var orgSysDictDataQxjc = [];//字典表（清洗机次）数据
    var orgSysDictDataDbff = [];//字典表（打包方法）数据
    var orgSysDictDataMjff = [];//字典表（灭菌方法）数据
    var orgSysDictDataMjgh = [];//字典表（灭菌锅号）数据
    var orgSysDictDataMjgc = [];//字典表（灭菌锅次）数据
    var staffDictData = [];//医院用户表（操作者和查对者）

    function endEditing(){
        if (currentSelectIndex == undefined){return true}
        if ($('#list_data').datagrid('validateRow', currentSelectIndex)){
            $('#list_data').datagrid('endEdit', currentSelectIndex);
            currentSelectIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell0(index, field){
        if (endEditing()){
            $('#list_data').datagrid('selectRow', index) .datagrid('editCell', {index:index,field:field});
            currentSelectIndex = index;
        }
    }

    var saveRows = [];
    clickRow = function(){
        saveRows = [];
        var rows = $('#list_data').datagrid('getRows');
        $(':checkbox[name="pb"]').each(function(index){
            $('#list_data').datagrid('endEdit', index)
            if($(this).prop('checked') && rows[index].amountAnti){
                var row = rows[index];
                row.asepsisState = "3";
                saveRows.push(row);
                //$(this).prop('checked', true);
            }
        })
    }
    $.get(basePath + "/orgSysDict/findList",{orgId:orgId}, function (data) {
        $.each(data,function(index,row){
            orgSysDictData.push(row);
            if(row.type=="QXFF"){orgSysDictDataQxff.push(row);}
            else if(row.type=="QXJH"){orgSysDictDataQxjh.push(row);}
            else if(row.type=="QXJC"){orgSysDictDataQxjc.push(row);}
            else if(row.type=="DBFF"){orgSysDictDataDbff.push(row);}
            else if(row.type=="MJFF"){orgSysDictDataMjff.push(row);}
            else if(row.type=="MJGH"){orgSysDictDataMjgh.push(row);}
            else if(row.type=="MJGC"){orgSysDictDataMjgc.push(row);}
        });
    });

    $.get(basePath + "/staffDict/listStaffDict", function (data) {
        $.each(data,function(index,row){
            staffDictData.push(row);
        });
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
    //获取员工表（用户表），以获取操作者和查对者（查询条件下拉列表中的）
    staffCombobox = function(staffDictDataFlag){
        $("#"+staffDictDataFlag).combobox({
            valueField:'empNo',
            textField:'name' ,
            editable:false,
            data:staffDictData,
            required:true,
            onSelect:function(){
                //var saveRows = $('#list_data').datagrid("getSelections");
                clickRow();
                $.each(saveRows, function(index, row){
                    if(staffDictDataFlag=="checker"){
                        row.checker = $("#checker").combobox("getValue");
                        row.checkerDes = $("#checker").combobox("getText");
                    }else if(staffDictDataFlag=="operator"){
                        if(fp==2){
                            row.antiOperator = $("#operator").combobox("getValue");
                            row.antiOperatorDes = $("#operator").combobox("getText");
                        }else if(fp==1){
                            row.packOperator = $("#operator").combobox("getValue");
                            row.packOperatorDes = $("#operator").combobox("getText");
                        }else{
                            row.sterOperator = $("#operator").combobox("getValue");
                            row.sterOperatorDes = $("#operator").combobox("getText");
                        }
                    }
                    //$('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
                });
            }
        });
    }

    //根据传过来的ID号，获取对应的字典数据（查询条件下拉列表中的）
    antiCombobox = function(orgSysDataFlag){
        if(orgSysDataFlag == "cleanWays"){
            orgSysDictSubData = orgSysDictDataQxff;
        }else if(orgSysDataFlag == "cleanNo"){
            orgSysDictSubData = orgSysDictDataQxjh;
        }else if(orgSysDataFlag == "cleanTimes"){
            orgSysDictSubData = orgSysDictDataQxjc;
        }else if(orgSysDataFlag == "packWays"){
            orgSysDictSubData = orgSysDictDataDbff;
        }else if(orgSysDataFlag == "antiWays"){
            orgSysDictSubData = orgSysDictDataMjff;
        }else if(orgSysDataFlag == "boilerNo"){
            orgSysDictSubData = orgSysDictDataMjgc;
        }else if(orgSysDataFlag == "boilerTimes"){
            orgSysDictSubData = orgSysDictDataMjgc;
        }
        $("#"+orgSysDataFlag).combobox({
            valueField:'value',
            textField:'label' ,
            editable:false,
            data:orgSysDictSubData,
            required:true,
            onSelect:function(){
                clickRow();
                $.each(saveRows, function(index, row){
                    if(orgSysDataFlag == "cleanWays"){
                        row.cleanWays = $("#cleanWays").combobox("getValue");
                        row.cleanWaysDes = $("#cleanWays").combobox("getText");
                    }else if(orgSysDataFlag == "cleanNo"){
                        row.cleanNo = $("#cleanNo").combobox("getValue");
                    }else if(orgSysDataFlag == "cleanTimes"){
                        row.cleanTimes = $("#cleanTimes").combobox("getValue");
                    }else if(orgSysDataFlag == "packWays"){
                        row.packWays = $("#packWays").combobox("getValue");
                        row.packWaysDes = $("#packWays").combobox("getText");
                    }else if(orgSysDataFlag == "antiWays"){
                        row.antiWays = $("#"+orgSysDataFlag).combobox("getValue");
                        row.antiWaysDes = $("#"+orgSysDataFlag).combobox("getText");
                    }else if(orgSysDataFlag == "boilerNo"){
                        row.boilerNo = $("#boilerNo").combobox("getValue");
                    }else if(orgSysDataFlag == "boilerTimes"){
                        row.boilerTimes = $("#boilerTimes").combobox("getValue");
                    }
                    //$('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",row))
                });
            }
        });
    }

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
        //onClickCell: onClickCell0,
        columns: [[      //每个列具体内容
            {field:'id',title:'消毒',width:'40',align:'center',formatter:function(value,row){
                return '<input id=' + value +' type="checkbox"  name="pb">'
            }},
            {field: 'belongDept', hidden:true},
            {field: 'belongDeptDes', title: '所属科室', width: '8%', align: 'center'},
            {field: 'documnetNo', title: '单据号', width: '7%', align: 'center'},
            {field: 'asepsisCode', title: '代码', width: '7%', align: 'center'},
            {field: 'asepsisName', title: '名称', width: '15%', align: 'center'},
            {field: 'asepsisSpec', title: '规格', width: '4%', align: 'center'},
            {field: 'amount', title: '总数量', width: '5%', align: 'center'},
            {field:'amountAnti',title:'灭菌数量',width:'60',align:'center',editor:{
                    type : 'numberbox',
                    options:{
                        precision : 0
                    }
                },formatter: function(value,row ){
                    var max = isNaN(row.amount) ? 0 : +row.amount;
                    row.amountAnti = (isNaN(value) || value > max )? max : value
                    return row.amountAnti
                }
            },
            {field: 'units', hidden:true},
            {field: 'unitsDes', title: '单位', width: '3%', align: 'center'},
            {field:'antiOperator',hidden:true},
            {field:'antiOperatorDes',title:'消毒人',width:'5%',align:'center',hidden:true},
            {field:'antiWays',hidden:true},
            {field:'antiWaysDes',title:'消毒方式',width:'10%',align:'center',hidden:true},
            {field:'boilerNo',title:'锅号',width:'10%',align:'center',hidden:true},
            {field:'boilerTimes',title:'锅次',width:'10%',align:'center',hidden:true},
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
        onClickCell: function(index,field){
            for(var i= 0,j=$(this).datagrid('getRows').length;i<j;i++) {
                var c = $(':checkbox[name="pb"]')[i].checked
                $(this).datagrid('endEdit', i)
                $(':checkbox[name="pb"]')[i].checked = c
            }
            if(field == 'amountAnti'){
                $(this).datagrid('beginEdit',index)
            }
        },
        onBeforeSelect: function(index,row){
            return false;
        }
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
        $.each(listAll, function(i, r){
            $.each(a,function(index,row){
                if(row.deptCode == r.belongDept){
                    r.belongDeptDes = row.deptName;
                }
            });
            $.each(orgSysDictData,function(index,row){
                if(row.type=="QXFF"&&row.value == r.cleanWays){
                    r.cleanWaysDes = row.label;
                }
                if(row.type=="DBFF"&&row.value == r.packWays){
                    r.packWaysDes = row.label;
                }
                if(row.type=="PACKAGE_UNITS"&&row.value == r.units){
                    r.unitsDes = row.label;
                }
            });
            $.each(b,function(index,row){
                if(row.empNo == r.checker){
                    r.checkerDes = row.name;
                }
                if(row.empNo == r.sterOperator){
                    r.sterOperatorDes = row.name;
                }
                if(row.empNo == r.packOperator){
                    r.packOperatorDes = row.name;
                }
            });
            $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
        });
    }
    var loadListData = function (flag) {
        if(flag=="1"){
            var belongDept=$("#belongDept").combobox('getValue');
            var asepsisName=$("#asepsisName").combobox('getValue');
            //$("#list_data").datagrid({url:basePath+'/asepsisAntiRec/list',queryParams:{"state":"2","orgId":orgId,"belongDept":belongDept,"asepsisName":asepsisName}});
            $.get(basePath+'/asepsisAntiRec/list',{"state":"2","orgId":orgId,"belongDept":belongDept,"asepsisName":asepsisName},function(res){
                $("#list_data").datagrid('loadData',res)
                staffCombobox("operator");staffCombobox("checker");//加载查询条件下拉列表中的操作者和查对者
                antiCombobox("antiWays");antiCombobox("boilerNo");antiCombobox("boilerTimes");//加载查询条件下拉列表中相应的字典数据
                setTimeout("loadAnotherData()",10);
            })
        }else{
            $.get(basePath+'/asepsisAntiRec/list',{"state":"2","orgId":orgId},function(res){
                $("#list_data").datagrid('loadData',res)
                staffCombobox("operator");staffCombobox("checker");//加载查询条件下拉列表中的操作者和查对者
                antiCombobox("antiWays");antiCombobox("boilerNo");antiCombobox("boilerTimes");//加载查询条件下拉列表中相应的字典数据
                setTimeout("loadAnotherData()",10);
            })
        }
    }
    loadListData();
    $("#searchBtn").on("click",function(){
        loadListData("1");
    });

    $("#saveBtn").on("click", function () {
        if (currentSelectIndex || currentSelectIndex == 0) {
            $("#list_data").datagrid("endEdit", currentSelectIndex);
        }
        if($("#operator").combobox('getValue').length==0){
            alert('请选择操作者');
            return ;
        }
        if($("#checker").combobox('getValue').length==0){
            alert('请选择查对者');
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
        saveRows = [];
        var rows = $('#list_data').datagrid('getRows');
        $(':checkbox[name="pb"]').each(function(index){
            $('#list_data').datagrid('endEdit', index)
            if($(this).prop('checked') && rows[index].amountAnti){
                var row = rows[index];
                row.asepsisState = "3";
                row.antiOperator = $("#operator").combobox('getValue');
                row.checker = $("#checker").combobox('getValue');
                row.antiWays = $("#antiWays").combobox('getValue');
                row.boilerNo = $("#boilerNo").combobox('getValue');
                row.boilerTimes = $("#boilerTimes").combobox('getValue');
                saveRows.push(row);
            }
        })
        if(saveRows.length<=0){
            clickRow();
            alert('请选择需要修改的数据');
            return ;
        }

        var asepsisAntiRecVo = {};
        asepsisAntiRecVo.updated = saveRows;
        asepsisAntiRecVo.orgId = orgId;
        if (asepsisAntiRecVo) {
            $.postJSON(basePath + "/asepsisAntiRec/saveClean", JSON.stringify(asepsisAntiRecVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    loadListData();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });











































})

/**
 onClickRow中的方法：
 row.antiOperator = null;
 row.antiWays = null;
 row.boilerNo = null;
 row.boilerTimes = null;
 row.checker = null;
 var saveRows = $('#list_data').datagrid("getSelections");
 $.each(saveRows, function(i, rowSel){
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