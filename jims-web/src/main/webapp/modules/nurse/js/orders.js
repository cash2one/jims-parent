var editRow = undefined;
var rowNum=-1;
$(function() {
    $('#orderList').datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method: 'get',
        url: basePath + '/ordersNurse/getNurseOrders?' + $('#searchform').serialize(),
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        fitColumns:true,
        fit: true,//自动大小
        columns: [[      //每个列具体内容
            {field:'remarks'
                ,formatter:function(value, rowData, rowIndex){
                if(rowData.orderNo!=rowData.orderSubNo){
                    return "<div style='color:blue;font-weight:bold; '>子</div>";
                }else{
                    return "";
                }
            }},
            {field: 'repeatIndicator', title: '长', formatter:itemFormatter,width: '2%', align: 'center'},
            {field: 'orderClass', title: '类别', width: '3%', formatter:orderClassFormatter,align: 'center'},
            {field: 'startDateTime', title: '开始时间', width: '6%', align: 'center',formatter:formatDateBoxFull},
            {field: 'orderText', title: '医嘱内容', width: '5%', align: 'center'},
            {
                field: 'dosage',
                title: '剂量',
                width: '3%',
                align: 'center'
            },
            {field: 'administration', title: '途径', width: '5%', formatter:billingAttrFormatter,align: 'center'},
            {field: 'frequency', title: '次数', width: '3%',formatter:performFreqFormatter, align: 'center'},
            {field: 'performSchedule', title: '执行时间', width: '5%', align: 'center'},
            {field: 'stopDateTime', title: '结束时间', width: '7%', align: 'center',formatter:formatDateBoxFull},
            {field: 'verifyDataTime', title: '校对时间', width: '8%', align: 'center',formatter:formatDateBoxFull},
            {field: 'time', title: '摆药截至时间', width: '8%', align: 'center'},
            {field: 'billingAttr', title: '自', width: '2%', align: 'center',formatter:billingAttrFormatter},
            {field:'performResult',title:'阴阳',width:'5%',align:'center',editor:'text'},
            {field: 'freqDetail', title: '医生说明', width: '5%', align: 'center'},
            {
                field: 'dosageUnits',
                title: '单位',
                width: '3%',
                align: 'center'

            },
            {field: 'freqCounter', title: '临嘱执行次数', width: '5%', align: 'center'},
            {field:'doctor',title:'开医嘱医生',width:'5%',align:'center'},
            {field:'nurse',title:'校对护士',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止护士',width:'5%',align:'center'},
            {field:'execDateTime',title:'执行时间',width:'6%',align:'center',formatter:formatDateBoxFull},
            {field:'execOperator',title:'执行护士',width:'5%',align:'center'},
            {field:'patientId',hidden:true},
            {field:'visitId',hidden:true},
            {field:'orderNo',hidden:true}
        ]],
        toolbar: [{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#orderList").datagrid('endEdit', editRow);
                if (editRow != undefined) {

                    $("#orderList").datagrid("endEdit", editRow);
                }
                save();
            }
        },'-',{
            text: '临时医嘱执行',
            iconCls:'icon-add',
            handler:function(){
                executeOrders();
            }
        },'-',{
            text: '校对',
            handler:function(){
                proofOrders();
            }
        },'-',{
            text: '停止',
            handler:function(){
                $("#orderList").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#orderList").datagrid("endEdit", editRow);
                }
                nurseStopOrders();
            }
        }
        ],

       onClickRow: function (rowIndex, rowData) {

            var row = $('#orderList').datagrid('getSelected');
            var dataGrid = $('#orderList');
            var row = $('#orderList').datagrid('getSelected');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false//新开
            } else {
                if (rowNum != rowIndex) {
                    if (rowNum >= 0) {
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum = rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }


        }, onDblClickRow: function (rowIndex, rowData) {
            $("#ordersDialog").dialog('open');
        }, rowStyler:function(index,row){
            if(row.orderStatus=='6'){//传输
                return 'background-color:#90EE90;color:#8A2BE2;';
            }else if(row.orderStatus=='1'){//护士转抄
                return 'background-color:#A7CACB;color:black;';
            }else if(row.orderStatus=='2') {//护士执行
                return 'background-color:#EED5D2;color:blue;';
            }else if(row.orderStatus=='3') {//护士停止
                return 'background-color:#698B69;color:yellow;';
            }else if(row.orderStatus=='4') {//护士作废
                return 'background-color:#CD0000;color:red;';
            }else if(row.orderStatus=='7'){//医生停止
                return 'background-color:#FFA54F;color:black;';
            }else if(row.orderStatus=='8'){//医生作废
                return 'background-color:#778899;color:black;';
            }else if(row.orderStatus=='5'){//医生新开
                return "color:#7D26CD";
            }





        }
    });
    $("#submit_search").linkbutton({iconCls: 'icon-search', plain: true}).click(function () {
        $('#orderList').datagrid("load");   //点击搜索
    });
});

//设置分页控件
var p = $('#orderList').datagrid('getPager');
$(p).pagination({
    pageSize: 10,//每页显示的记录条数，默认为10
    pageList: [5,10,15],//可以设置每页记录条数的列表
    beforePageText: '第',//页数文本框前显示的汉字
    afterPageText: '页    共 {pages} 页',
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
});
//校对
function proofOrders(){
    var ordersRow = $('#orderList').datagrid("getSelections");
    var tableJson=JSON.stringify(ordersRow);
    $.postJSON(basePath+'/ordersNurse/proofOrders',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","校对成功");
            $('#orderList').datagrid("reload");
        }else{
            $.messager.alert('提示',"校对失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"校对错误", "error");
    })
}


//执行
function executeOrders(){


    var ordersRow = $('#orderList').datagrid("getSelected");
    var tableJson=JSON.stringify(ordersRow);
    if(ordersRow.repeatIndicator=='1'){//长期医嘱执行
        $.messager.alert("提示消息","只有临时医嘱才需要执行");
    }else if(ordersRow.execDateTime!=null) {
        $.messager.alert("提示消息", "该条医嘱已经执行，不能重复执行");
    } else {
    $.postJSON(basePath+'/ordersNurse/executeOrders',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","执行成功");
            $('#orderList').datagrid("reload");
        }else{
            $.messager.alert('提示',"执行失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"执行错误", "error");
    })
    }
}


function loadBaseInfo(id){
    $.ajax({
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: id = id,
        url: basePath + '/bedRec/getInPats',
        success: function (data) {
            $.each(data,function(id,item) { //循环对象取值
                $('#baseInfo').form('load',item);
            })
        }
    });
}


//护士医生停止医嘱
function nurseStopOrders(){
    var row = $('#orderList').datagrid('getSelected');
    var tableJson=JSON.stringify(row);
    if(row == null){
        $.messager.alert('提示',"请将医嘱信息填写完整！", "error");
    }else if(row.repeatIndicator=='0'){
        $.messager.alert('提示',"该医嘱是临时医嘱,不能进行停止操作！", "error");
    }else if(row.orderStatus=='2'){
        $.postJSON(basePath+'/ordersNurse/stopOrders',tableJson,function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息","该医嘱已经停止");
                $('#orderList').datagrid('load');
                $('#orderList').datagrid('clearChecked');
            }else{
                $.messager.alert('提示',"医嘱停止操作失败", "error");
            }
        });
    }else{
        $.messager.alert("提示消息","该医嘱未进行校对不能停止");
    }
}


//保存
function save(){
    var patId = "71921abbf7204bff8d4b3a534fbc08de";
    var visitId ="1";
    $("#orderList").datagrid('endEdit', rowNum);
    var  rows=$('#orderList').datagrid('getChanges');
    var tableJson=JSON.stringify(rows);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    var submitJson=tableJson+",\"patientId\":\""+patId+"\",\"visitId\":\""+visitId+"\"}]";
    $.postJSON(basePath+'/inOrders/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","保存成功");
            $('#orderList').datagrid('load');
            $('#orderList').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"网络异常", "error");
    })
}