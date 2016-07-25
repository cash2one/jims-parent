var editRow = undefined;
var rowNum=-1;
var patId = parent.patVisit.patientId;
var visitId =parent.patVisit.visitId;
var orderNo =0 ;
var orderSubNo = 1;
var orderCostsArray={};

$(function(){
    $("#patientId").val(patId);
    $("#visitId").val(visitId);
    $.ajax({
        'type': 'GET',
        'url':basePath+'/inOrders/getCost',
        'contentType': 'application/json',
        'data':'visitId='+visitId+'&patientId='+patId,
        'dataType': 'json',
        'async': false,
        'success': function(data){
            var orderNo=0;
            var orderNoOld=0;
            for(var i=0;i<data.length;i++){
                var list=[];
                orderNo=data[i].orderNo;
                if(orderNo!=orderNoOld){
                    list.push(data[i]);
                    orderNoOld=orderNo;
                    orderCostsArray[orderNoOld]=list;
                }else{
                    list=orderCostsArray[orderNoOld];
                    list.push(data[i]);
                    orderCostsArray[orderNoOld]=list;
                }
            }
        }
    });

    $('#orderList').datagrid({
        idField:'id',
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'GET',
        //url:basePath+'/inOrders/getOrders?'+$('#searchform').serialize(),
        remoteSort:false,
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'remarks'
                ,formatter:function(value, rowData, rowIndex){
                if(rowData.orderNo!=rowData.orderSubNo){
                    return "<div style='color:blue;font-weight:bold; '>子</div>";
                }else{
                    return "";
                }
            }},
            {field:'repeatIndicator',title:'长',width:'5%',align:'center',formatter:indicatorFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :ordersType,
                    valueField:'value',
                    textField:'label',
                    onSelect:function(rowIndex){
                        var performSchedule = $("#orderList").datagrid('getEditor',{index:rowNum,field:'performSchedule'});
                        var repeatIndicatorValue = $("#orderList").datagrid('getEditor',{index:rowNum,field:'repeatIndicator'});
                        var repeatIndicator = $(repeatIndicatorValue.target).combobox('getValue');
                        if(repeatIndicator=='0'){//临时医嘱
                            var d = new Date();
                            $('#frequency').combobox('disable');
                            $(performSchedule.target).textbox('setValue',d.getHours()+":"+ d.getMinutes());
                            /*    var stopDate=formatDateBoxFull(new Date());
                             var stopDateTime = $("#orderList").datagrid('getEditor',{index:rowNum,field:'stopDateTime'});
                             $(stopDateTime.target).datebox("setValue",stopDate);*/

                        }
                    }}}},
            {field:'orderClass',title:'类别',width:'5%',align:'center',formatter:orderClassFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :Oclass,
                    valueField:'value',
                    textField:'label',
                    onSelect:function(value){

                        var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                        var value = $(orderClass.target).combobox('getValue');
                        var orderClass=value;
                        var ed = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderText'});
                        $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                        /*如果类别是药品医嘱内容是药品的内容，如果是非药品显示非药品的医嘱内容*/
                        if(value=='1'){//药品
                            $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                            $(ed.target).combogrid("grid").datagrid("loadData", drugData);
                        }else if(value=='2'){//非药品
                            $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                            $(ed.target).combogrid("grid").datagrid("loadData", clinicOrderData);
                        }

                    }
                }
            }},
            //当前时间
            {field:'startDateTime',title:'下达时间',width:'10%',align:'center',formatter:formatDateBoxFull, editor:{type: 'datebox',options:{editable:true,disable:false}}},
            {field:'orderText',title:'医嘱内容',width:'10%',align:'center',editor:{
                type:'combogrid',
                options:{
                    panelWidth: 550,
                    idField:'item_name',
                    textField:'item_name',
                    columns:[
                        [
                            {field: 'drug_code', title: '药品代码', width: '10%', align: 'center'},
                            {field: 'item_name', title: '名称', width: '10%', align: 'center'},
                            {field: 'drug_spec', title: '药品规格', width: '5%', align: 'center'},
                            {field: 'item_spec', title: '项目规格', width: '5%', align: 'center'},
                            {field: 'supplier', title: '厂家', width: '20%', align: 'center'},
                            {field: 'dose_per_unit', title: '单次用量', width: '10%', align: 'center'},
                            {field: 'units', title: '用量单位', width: '10%', align: 'center'},
                            {field: 'item_code', title: '项目代码', width: '10%', align: 'center'},
                            {field: 'input_code', title: '拼音', width: '5%', align: 'center'},
                            {field: 'expand1', title: '扩展1', width: '5%', align: 'center'},
                            {field: 'expand2', title: '扩展2', width: '5%', align: 'center'},
                            {field: 'expand5', title: '扩展5', width: '5%', align: 'center'},
                            {field: 'price', hidden:'true'},
                            {field: 'item_class', hidden:'true'}
                        ]
                    ] ,keyHandler: {
                        up: function() {},
                        down: function() {},
                        enter: function() {},
                        query: function(q) {
                            var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                            var ed = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderText'});
                            var value = $(orderClass.target).combobox('getValue');
                            if(value=='1'){//药品
                                ordersDrugCom(q,'orderText');
                                $(ed.target).combogrid("grid").datagrid("loadData", drugData);
                            }else if(value=='2'){//非药品
                                clinicCompleting(q,'orderText');
                                $(ed.target).combogrid("grid").datagrid("loadData", clinicCompleteAuto);
                            }

                        }
                    },onClickRow: function (index, row) {

                        var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                        var value = $(orderClass.target).combobox('getValue');

                        if(value=='1'){//药品
                            var dosage = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                            $(dosage.target).textbox('setValue',row.dose_per_unit);
                            var dosageUnits = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
                            $(dosageUnits.target).textbox('setValue',row.dose_units);
                            var orderCode = $("#orderList").datagrid('getEditor',{index:rowNum,field:'orderCode'});
                            $(orderCode.target).textbox('setValue',row.drug_code);
                            $(orderClass.target).combobox('setValue',row.item_class);
                            var rows=$('#orderCostList').datagrid('getRows');
                            for(var i=0;i<rows.length;i++){
                                if(rows[i].itemCode==row.drug_code){
                                    $('#orderCostList').datagrid('deleteRow', i);
                                }
                            }
                            $('#orderCostList').datagrid('insertRow', {
                                index:1,	// index start with 0
                                row: {
                                    itemClass:  '药品',
                                    itemName:row.item_name,
                                    itemSpec:row.drug_spec,
                                    amount:row.dose_per_unit,
                                    units:row.dose_units,
                                    costs:row.price,
                                    itemCode:row.drug_code,
                                    itemNo:1
                                }
                            });
                        }else if(value=='2'){//非药品
                            var dosage = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                            $(dosage.target).textbox('setValue',1);
                            var dosageUnits = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
                            $(dosageUnits.target).textbox('setValue',row.units);
                            var orderCode = $("#orderList").datagrid('getEditor',{index:rowNum,field:'orderCode'});
                            $(orderCode.target).textbox('setValue',row.item_code);
                            $(orderClass.target).combobox('setValue',row.item_class);
                            $('#orderCostList').datagrid('insertRow', {
                                index:0,	// index start with 0
                                row: {
                                    itemClass:  row.item_class,
                                    itemName:row.item_name,
                                    itemSpec:row.item_spec,
                                    amount:1,
                                    units:row.units,
                                    costs:row.price,
                                    itemCode:row.item_code,
                                    itemNo:row.charge_item_no
                                }
                            });


                        }

                        $('#orderCostList').datagrid('selectRow',0);
                    }
                }
            }},
            {field:'billingAttr',title:'自',width:'5%',align:'center',formatter:billingAttrFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :billingAttr,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'dosage',title:'剂量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:true,disable:false}}},
            {field:'dosageUnits',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'5%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationmzDict,
                    valueField:'id',
                    textField:'administration_name',
                    onSelect: function (row) {
                        var rows=$('#orderCostList').datagrid('getRows');
                        for(var i=0;i<rows.length;i++){
                            if(rows[i].itemClass=='非药品'){
                                $('#orderCostList').datagrid('deleteRow', i);
                            }
                        }
                        $('#orderCostList').datagrid('insertRow', {
                            index:1,	// index start with 0
                            row: {
                                itemClass: '非药品',
                                itemName:row.administration_name,
                                itemSpec:row.item_spec,
                                amount:1,
                                units:row.units,
                                costs:row.price,
                                itemCode:row.item_code,
                                itemNo:row.charge_item_no
                            }
                        });
                    }
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',formatter:performFreqFormatter,editor:{
                type:'combobox',
                options:{
                    /*如果是临时医嘱频次不可填*/
                    data :performFreqDict,
                    valueField:'id',
                    textField:'freqDesc',
                    onSelect: function () {
                        var row = $('#orderList').datagrid('getSelected');
                        var performSchedule = $("#orderList").datagrid('getEditor',{index:rowNum,field:'performSchedule'});
                        var frequency = $("#orderList").datagrid('getEditor',{index:rowNum,field:'frequency'});
                        var frequencyValue = $(frequency.target).combobox('getValue');
                         var freqCounter = $("#orderList").datagrid('getEditor',{index:rowNum,field:'freqCounter'});
                        //var freqIntervalUnit = $("#orderList").datagrid('getEditor',{index:rowNum,field:'freqIntervalUnit'});

                        var repeatIndicatorValue = $("#orderList").datagrid('getEditor',{index:rowNum,field:'repeatIndicator'});
                        var repeatIndicator = $(repeatIndicatorValue.target).combobox('getValue');
                        var administration = $("#orderList").datagrid('getEditor',{index:rowNum,field:'administration'});
                        var administrationValue = $(administration.target).combobox('getValue');
                        if( repeatIndicator==null){
                            $('#frequency').combobox('disable');
                            $.messager.alert('提示', "医嘱类型不能为空！", "error");
                        }else{
                            if(repeatIndicator=='1'){//长期医嘱
                                $('#frequency').combobox('enable');
                                var perSchedule = [];
                                $.ajax({
                                    'type': 'GET',
                                    'url':basePath+'/performDefaultSchedule/findList',
                                    'contentType': 'application/json',
                                    'data':'freqDesc='+frequencyValue+'&administration='+administrationValue,
                                    'dataType': 'json',
                                    'async': false,
                                    'success': function(data){
                                        perSchedule=data;
                                        var defaultSchedule ;
                                        var freqCounterValue ;
                                        for (var i = 0; i < perSchedule.length; i++) {
                                            if (perSchedule[i].freqDesc == frequencyValue && perSchedule[i].administration == administrationValue) {
                                                defaultSchedule = perSchedule[i].defaultSchedule;
                                            }
                                        }
                                        /*  $(freqCounter.target).textbox('setValue',counter);*/
                                        $(performSchedule.target).textbox('setValue',defaultSchedule);
                                    }
                                });

                            }

                        }

                    }}}},
            {field:'performSchedule',title:'护士执行时间',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'performResult',title:'阴阳',width:'5%',align:'center'},
            {field:'stopDateTime',title:'结束时间',width:'10%',align:'center',formatter:formatDateBoxFull},
            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'freqCounter',title:'次数',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停止医生',width:'10%',align:'center'},
            {field:'stopNurse',title:'停止校対护士',width:'5%',align:'center'},
            {field:'orderNo',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}},
                formatter:function(value){
                    if(orderNo<value){
                        orderNo = value;
                    }
                    return value;
                }},
            {field:'orderSubNo',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderStatus',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderCode',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'execDateTime',hidden:'true'},
            {field:'verifyDataTime',hidden:'true'},
            {field:'lastPerformDateTime',hidden:'true'}
            /*{field:'freqIntervalUnit',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
             {field:'freqInterval',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},*/
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                $("#orderList").datagrid('endEdit', rowNum);
                if(!$("#orderList").datagrid('validateRow', rowNum)){
                    $.messager.alert('提示',"请填写完本行数据后，再添加下一条医嘱", "error");
                    return false
                }
                $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                if(rowNum>=0){
                    rowNum++;
                }
                orderNo++;
                var startDate=formatDateBoxFull(new Date());
                var idx = $("#orderList").datagrid('appendRow', {
                        orderNo:orderNo,
                        orderSubNo:orderNo
                    }).datagrid('getRows').length-1;
                rowNum=idx;
                $('#orderList').datagrid('beginEdit', idx);
            }
        },'-',{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                if(!$("#orderList").datagrid('validateRow', rowNum)){
                    $.messager.alert('提示',"请填写完本行数据后，再进行保存", "error");
                    return false
                }else{
                    $("#orderList").datagrid('endEdit', editRow);
                    if (editRow != undefined) {

                        $("#orderList").datagrid("endEdit", editRow);
                    }
                    save();
                }

            }
        },'-',{
            text: '传输医嘱',
            iconCls:'icon-add',
            handler:function(){
                issuedOrders();
            }
        },'-',{
            text: '子医嘱',
            iconCls:'icon-save',
            handler:function(){
                if (! $("#orderList").datagrid('validateRow', rowNum)) {
                    $.messager.alert('提示', "请选择要操作的医嘱！", "error");
                } else {
                    var selRow = $('#orderList').datagrid('getChecked');
                    if (selRow != null && selRow != '' && selRow != 'undefined') {
                        changeSubNo(selRow);
                    } else {
                        $.messager.alert('提示', "请选择要操作的医嘱！", "error");
                    }
                }
            }
        },'-',{
            text: '刷新',
            iconCls:'icon-reload',
            handler:function(){
                reload();
            }
        },'-',{
            text: '停止',
            handler:function(){
                $("#orderList").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#orderList").datagrid("endEdit", editRow);
                }
                docStopOrders();
            }
        },'-',{
            text: '作废',
            handler:function(){
                $("#orderList").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#orderList").datagrid("endEdit", editRow);
                }
                docCancelOrders();
            }
        },'-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                deleteOrders();
            }
        }
        ],onAfterEdit:function(rowIndex, rowData){
            var rows=$('#orderCostList').datagrid("getRows");
            orderCostsArray[rowData.orderNo]=rows;
        },
        onClickRow: function (rowIndex, rowData) {
            var row = $('#orderList').datagrid('getSelected');
            var dataGrid = $('#orderList');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false//新开
            } else {
                if (rowNum != rowIndex) {
                    if (rowNum >= 0) {
                        dataGrid.datagrid('endEdit', rowNum);
                        if(orderCostsArray[rowData.orderNo]=='null'||orderCostsArray[rowData.orderNo]=='undefined'){
                            $('#orderCostList').datagrid('loadData',  { total: 0, rows: [] });
                        }else{
                            $('#orderCostList').datagrid('loadData', orderCostsArray[rowData.orderNo]);
                        }

                    }
                    rowNum = rowIndex;
                    if(rowData.orderStatus=='5'||rowData.orderStatus=='') {
                        dataGrid.datagrid('beginEdit', rowNum);
                    }else{
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                }

            }


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
                return 'background-color:#8B668B;color:red;';
            }else if(row.orderStatus=='7'){//医生停止
                return 'background-color:#FFA54F;color:black;';
            }else if(row.orderStatus=='8'){//医生作废
                return 'background-color:#778899;color:black;';
            }else if(row.orderStatus=='5'){//医生新开
                return "color:#7D26CD";
            }
        },onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="23" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }


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

    $("#orderCostList").datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        columns: [[      //每个列具体内容
            {field: 'itemClass', title: '类别', width: '10%', align: 'center'},
            {field: 'itemName', title: '计价项目', width: '20%', align: 'center'},
            {field: 'itemSpec', title: '规格', width: '5%', align: 'center'},
            {field: 'amount', title: '数量', width: '5%', align: 'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field: 'units', title: '单位', width: '5%', align: 'center'},
            {field: 'costs', title: '当前单价', width: '10%', align: 'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'itemCode',hidden:'true'},
            {field:'itemNo',hidden:'true'}

        ]]
    });





});


function searchOrders(){
    var startTime=$("#startDateTime").datebox('getValue');
    var endTime=$("#stopDateTime").datebox('getValue');
    var repeatIndicator=$('input[name="repeatIndicator"]:checked ').val();
    $("#orderList").datagrid({url:basePath+'/inOrders/getOrders',queryParams:{"startDateTime":startTime,"stopDateTime":endTime,"repeatIndicator":repeatIndicator}});
}


//保存
function save(){
    $("#orderList").datagrid('endEdit', rowNum);
    var  rows=$('#orderList').datagrid('getChanges');
    var  costRows=$('#orderCostList').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    var costJson = JSON.stringify(costRows);
    var submitJson=tableJson+",\"patientId\":\""+patId+"\",\"visitId\":\""+visitId+"\",\"ordersCostses\":"+costJson+"}]";
    if (!$("#orderList").datagrid('validateRow', rowNum)) {
        $.messager.alert("提示消息","请把医嘱信息输入完整再进行保存！");
    }else{
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
}

//刷新
function reload(){
    $("#orderList").datagrid("reload");
    $('#orderCostList').datagrid('reload');
}

//删除
function deleteOrders(){
    var selectRows = $('#orderList').datagrid("getSelections");
    var row = $('#orderList').datagrid('getSelected');
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    if(row.orderStatus=='5'||row.orderStatus==''||row.orderStatus==null){//新开医嘱
        //提醒用户是否是真的删除数据
        $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
            if (r) {
                var strIds = "";
                for (var i = 0; i < selectRows.length; i++) {
                    strIds += selectRows[i].id + ",";
                }
                strIds = strIds.substr(0, strIds.length - 1);
                if(row.id=='undefined'|| row.id==''|| row.id==null){
                    var index1= $('#orderList').datagrid('getRowIndex', $("#orderList").datagrid('getSelected'));
                    $('#orderList').datagrid('deleteRow',index1);
                }else {
                    //真删除数据
                    $.ajax({
                        'type': 'POST',
                        'url': basePath + '/inOrders/deleteOrdersNew',
                        'contentType': 'application/json',
                        'data': id = strIds,
                        'dataType': 'json',
                        'success': function (data) {
                            if (data.data == "success") {
                                $.messager.alert("提示消息", data.code + "条记录删除成功！");
                                $('#orderList').datagrid('reload');
                                $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                            } else {
                                $.messager.alert('提示', "删除失败", "error");
                            }
                        },
                        'error': function (data) {
                            $.messager.alert('提示', "删除失败", "error");
                        }
                    });
                }
            }
        })
    }else if(row.orderStatus=='6'){//传输
        $.messager.alert('提示', "医嘱已经提交，不能删除", "error");
    }else if(row.orderStatus=='1'){//转抄
        $.messager.alert('提示', "医嘱已经转抄，不能删除", "error");
    }else if(row.orderStatus=='2') {//执行
        $.messager.alert('提示', "医嘱已经执行，不能删除", "error");
    }else if(row.orderStatus=='3') {//停止
        $.messager.alert('提示', "医嘱已经停止，不能删除", "error");
    }else if(row.orderStatus=='4') {//作废
        $.messager.alert('提示', "医嘱已经作废，不能删除", "error");
    }
    else if(row.orderStatus=='7') {//医生停止
        $.messager.alert('提示', "医嘱已经被医生停止，不能删除", "error");
    }
    else if(row.orderStatus=='8') {//医生作废
        $.messager.alert('提示', "医嘱已经被医生作废，不能删除", "error");
    }
}

//把医嘱改变成子医嘱
function changeSubNo(row){
    $('#orderList').datagrid('endEdit',rowNum);
    var rows = $('#orderList').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    var afterrow;
    var nowrow = row[0];
    var index= $('#orderList').datagrid('getRowIndex',nowrow);

    if(index>0) {
        prerow = rows[index-1];
        //2.判断2条医嘱是否同是长期或者临时,进行3判断
        if(nowrow.repeatIndicator==prerow.repeatIndicator){

            //3.判断2条医嘱是否同时药品，（药疗才可组成组合医嘱），进行4判断
            if(nowrow.orderClass=='A'||prerow.orderClass=='B'){

                //4.判断上一条医嘱状态，如果状态允许，进行5判断
                if(prerow.orderStatus==5 || prerow.orderStatus=="5"|| nowrow.orderStatus==''){

                    //5.判断选中行row的医嘱状态，如果状态是新开，则进行6操作
                    if(nowrow.orderStatus==5||nowrow.orderStatus=='5'|| nowrow.orderStatus==''){


                        var dataGrid=$('#orderList');
                        if(!dataGrid.datagrid('validateRow', index)){
                            $.messager.alert('提示',"数据填写不完整，请填写完后在添加子医嘱", "error");
                            return false
                        }
                        $('#orderList').datagrid('endEdit', index);
                        $('#orderList').datagrid('beginEdit', index);
                        //获取下一行
                        afterrow=rows[index+1];
                        //判断本身是否是子处方
                        if(afterrow!=undefined){
                            //判断是否是子医嘱
                            if(nowrow.orderNo!=nowrow.orderSubNo){
                                //判断是否有子医嘱
                                if(afterrow.orderSubNo == nowrow.orderSubNo){
                                    return false;
                                }else{
                                    //删除子医嘱
                                    nowrow.orderSubNo = nowrow.orderNo;
                                    rowNum=index;
                                    $('#orderList').datagrid('endEdit', index);
                                    $('#orderList').datagrid('beginEdit', index);
                                    return false;
                                }
                            }
                        }else{
                            if(nowrow.orderNo!=nowrow.orderSubNo){
                                nowrow.orderSubNo = nowrow.orderNo;
                                rowNum=index;
                                $('#orderList').datagrid('endEdit', index);
                                $('#orderList').datagrid('beginEdit', index);
                                return false;
                            }
                        }
                        if(afterrow!=undefined){
                            if(afterrow.orderSubNo == nowrow.orderNo){
                                $.messager.alert('提示',"此医嘱已经有子医嘱，不能设置子医嘱", "error");
                                return false;
                            }
                        }


                        //1.判断该条医嘱是否有子处方，如果有，则不允许把当前处方变成其他处方的子处方
                        prerow = rows[index-1];
                        nowrow.orderSubNo = prerow.orderSubNo;

                        var subNo = $("#orderList").datagrid('getEditor',{index:index,field:'orderSubNo'});
                        $(subNo.target).textbox('setValue',prerow.orderSubNo);
                        $('#orderList').datagrid('endEdit', index);
                        $('#orderList').datagrid('beginEdit', index);
                        save();




                    }else{
                        $.messager.alert('提示',"选中行不是新开医嘱不能构成组合医嘱！", "error");
                    }
                }else{
                    $.messager.alert('提示',"上一行不是新开医嘱不能构成组合医嘱！", "error");
                }
            }else{
                $.messager.alert('提示',"不是药疗医嘱不能构成组合医嘱！", "error");
            }
        }else{
            $.messager.alert('提示',"长期和临时医嘱不能构成组合医嘱！", "error");
        }
    }else{
        $.messager.alert('提示',"第一条不医嘱能设置子医嘱", "warning");
    }
}






//传输医嘱（下达医嘱）下达时间不能早于昨天22:00
function issuedOrders(){
    var row = $('#orderList').datagrid('getSelected');
    var tableJson=JSON.stringify(row);
    if(row==null){
        $.messager.alert('提示',"请将医嘱信息填写完整！", "error");
    }else{
        $.postJSON(basePath+"/inOrders/issuedOrders",tableJson,function(data){
            if(data.data=='success'){
                $.messager.alert('提示',"医嘱已经下达！");
                $('#orderList').datagrid('load');
            }else{
                $.messager.alert('提示',"医嘱下达失败！", "error");
            }
        });
    }
}

//医生停止医嘱
function docStopOrders(){
    var row = $('#orderList').datagrid('getSelected');
    var tableJson=JSON.stringify(row);
    if(row == null){
        $.messager.alert('提示',"请将医嘱信息填写完整！", "error");
    }else if(row.repeatIndicator=='0'){
        $.messager.alert('提示',"该医嘱是临时医嘱,不能进行停止操作！", "error");
    }else if(row.orderStatus=='2'){
        $.postJSON(basePath+'/inOrders/docStopOrders',tableJson,function(data){
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

//医生作废医嘱
function docCancelOrders(){
    var row = $('#orderList').datagrid('getSelected');
    var tableJson=JSON.stringify(row);
    if(row == null){
        $.messager.alert('提示',"请将医嘱信息填写完整！", "error");
    }else{
        if(row.execDateTime!=null){
            $.messager.alert("提示消息","该医嘱已经执行,不能作废");
        }else if(row.verifyDataTime==null){
            $.messager.alert("提示消息","该医嘱未进行校验,不能作废");
        }else if(row.lastPerformDateTime!=null){
            $.messager.alert("提示消息","该医嘱已经收费,不能作废");
        }
        else {
            $.postJSON(basePath+'/inOrders/docCancelOrders',tableJson,function(data){
                if(data.data=='success'){
                    $.messager.alert("提示消息","该医嘱已经作废");
                    $('#orderList').datagrid('load');
                    $('#orderList').datagrid('clearChecked');
                }else{
                    $.messager.alert('提示',"医生作废操作失败", "error");
                }
            });
        }

    }
}



