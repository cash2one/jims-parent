var editRow = undefined;
var visitDate='2015-06-09';
var visitNo='410';
var prescNo ='';
var serialNo='';
var itemClass='西、成药';
var chargeIndicator='新开';
var drugName = [{ "value": "氨茶碱注射液", "text": "氨茶碱注射液" }, { "value": "奥氮平（奥兰之）", "text": "奥氮平（奥兰之）" }, { "value": "奥氮平片", "text": "奥氮平片" }, { "value": "胺碘酮注射液", "text": "胺碘酮注射液" }, { "value": "阿司匹林肠溶片", "text": "阿司匹林肠溶片" }];
var administration = [{ "value": "口服", "text": "口服" }, { "value": "静脉注射", "text": "静脉注射" }, { "value": "小儿头皮静脉", "text": "小儿头皮静脉" }, { "value": "静脉输液", "text": "静脉输液" }, { "value": "续静滴", "text": "续静滴" }];
var frequency = [{ "value": "一日一次", "text": "一日一次" }, { "value": "一日二次", "text": "一日二次" }, { "value": "一日三次", "text": "一日三次" }];
var providedIndicator = [{ "value": "1", "text": "取药" }];
var skinFlag =  [{ "value": "1", "text": "阴性" }, { "value": "2", "text": "阳性" }, { "value": "3", "text": "无皮试" }];
var xiyaoGrid;
var zhongyaoGrid;

$(function(){
    var itemClass = $("#itemClass").val();
    var clinicId = $("#clinicMasterId",parent.document).val();
    $("#clinicId").val(clinicId);
    xiyaoGrid= $('#list_data').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        /*method:'GET',
        url:basePath+'/outppresc/sublist',*/
        columns:[[      //每个列具体内容
            {field:'orderNo',title:'处方号',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :drugName,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
                /* options:{required:true,
                 url: basePath+'/outppresc/dictlist',
                 valueField: 'value',
                 textField: 'label',
                 method: 'GET',
                 onLoadSuccess: function () {
                 var data = $(this).combobox('getData');
                 $(this).combobox('select', data[0].label);
                 } ,
                 onChange:function(newValue,oldValue){
                 alert(newValue+"--"+oldValue);
                 *//* if(newValue=="退货出库"){
                 $('#receiver').combogrid('enable');
                 $.messager.confirm('系统消息', '您要“退货出库”给供应商吗？', function (r) {
                 if (r) {
                 depts = new Array;
                 for(var i = 0 ;i< suppliers.length;i++){
                 var dept = {};
                 dept.storageName = suppliers[i].supplierName;
                 dept.storageCode = suppliers[i].supplierCode;
                 dept.disburseNoPrefix = suppliers[i].inputCode;
                 depts.push(dept)
                 }
                 $('#receiver').combogrid('grid').datagrid('loadData', depts);
                 }
                 });
                 }*//*
                 }
                 }*/
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:'text'},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:'text'},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:'text'},
            {field:'performNurse',title:'剂量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:'text'},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text',
                    required:true
                }

            }},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:'text'},
            {field:'itemClass',title:'药局',width:'5%',align:'center'},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :providedIndicator,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :skinFlag,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'subOrderNo',title:'子处方',hidden:'true'},
            {field:'itemNo',title:'项目序号',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    $("#list_data").datagrid('insertRow', {
                        index:0,
                        row:{}
                    });
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
        }
    });
    zhongyaoGrid = $('#list_data').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
       /* method:'GET',
        url:basePath+'/outppresc/sublist',*/
        columns:[[      //每个列具体内容
            {field:'orderNo',title:'处方号',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :drugName,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
                /* options:{required:true,
                 url: basePath+'/outppresc/dictlist',
                 valueField: 'value',
                 textField: 'label',
                 method: 'GET',
                 onLoadSuccess: function () {
                 var data = $(this).combobox('getData');
                 $(this).combobox('select', data[0].label);
                 } ,
                 onChange:function(newValue,oldValue){
                 alert(newValue+"--"+oldValue);
                 *//* if(newValue=="退货出库"){
                 $('#receiver').combogrid('enable');
                 $.messager.confirm('系统消息', '您要“退货出库”给供应商吗？', function (r) {
                 if (r) {
                 depts = new Array;
                 for(var i = 0 ;i< suppliers.length;i++){
                 var dept = {};
                 dept.storageName = suppliers[i].supplierName;
                 dept.storageCode = suppliers[i].supplierCode;
                 dept.disburseNoPrefix = suppliers[i].inputCode;
                 depts.push(dept)
                 }
                 $('#receiver').combogrid('grid').datagrid('loadData', depts);
                 }
                 });
                 }*//*
                 }
                 }*/
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:'text'},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:'text'},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:'text'},
            {field:'performNurse',title:'剂量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:'text'},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }

            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:'text'},
            {field:'itemClass',title:'药局',width:'5%',align:'center'},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :providedIndicator,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'subOrderNo',title:'子处方',hidden:'true'},
            {field:'itemNo',title:'项目序号',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    $("#list_data").datagrid('insertRow', {
                        index:0,
                        row:{}
                    });
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
        }
    });
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list?clinicId='+clinicId,
        columns:[[      //每个列具体内容
            {field:'visitDate',title:'就诊时间',width:'20%',align:'center'},
            {field:'visitNo',title:'就诊序号',width:'15%',align:'center'},
            {field:'serialNo',title:'开单序号',width:'15%',align:'center'},
            {field:'prescNo',title:'处方号',width:'15%',align:'center'},
            {field:'itemClass',title:'处方分类',width:'15%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "A") {
                        value = "西、成药";
                    }
                    else if (value == "B") {
                        value = "草药";
                    }
                    return value;
                }},
            {field:'chargeIndicator',title:'收费状态',width:'15%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        value = "新开";
                    }
                    return value;
                }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]], onClickRow: function (index, row) {
            if(row.itemClass=='A'){
                $.get(basePath+'/outppresc/sublist?prescNo=' + row.prescNo, function (data) {
                    $("#list_data").datagrid("loadData", data);
                });
            }else{
                $.get(basePath+'/outppresc/sublist?prescNo=' + row.prescNo, function (data) {
                    $("#list_data").datagrid("loadData", data);
                });
            }

        }/*, onLoadSuccess: function(){
            $('#leftList').datagrid('selectRow',0);
        }*/
    });
    $('#list_data').datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'prescNo',title:'处方号',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :drugName,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
                /* options:{required:true,
                 url: basePath+'/outppresc/dictlist',
                 valueField: 'value',
                 textField: 'label',
                 method: 'GET',
                 onLoadSuccess: function () {
                 var data = $(this).combobox('getData');
                 $(this).combobox('select', data[0].label);
                 } ,
                 onChange:function(newValue,oldValue){
                 alert(newValue+"--"+oldValue);
                 *//* if(newValue=="退货出库"){
                 $('#receiver').combogrid('enable');
                 $.messager.confirm('系统消息', '您要“退货出库”给供应商吗？', function (r) {
                 if (r) {
                 depts = new Array;
                 for(var i = 0 ;i< suppliers.length;i++){
                 var dept = {};
                 dept.storageName = suppliers[i].supplierName;
                 dept.storageCode = suppliers[i].supplierCode;
                 dept.disburseNoPrefix = suppliers[i].inputCode;
                 depts.push(dept)
                 }
                 $('#receiver').combogrid('grid').datagrid('loadData', depts);
                 }
                 });
                 }*//*
                 }
                 }*/
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:'text'},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:'text'},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:'text'},
            {field:'performNurse',title:'剂量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:'text'},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text',
                    required:true
                }

            }},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:'text'},
            {field:'itemClass',title:'药局',width:'5%',align:'center'},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :providedIndicator,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :skinFlag,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'subOrderNo',title:'子处方',hidden:'true'},
            {field:'itemNo',title:'项目序号',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    $("#list_data").datagrid('insertRow', {
                        index:0,
                        row:{}
                    });
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
        }
    });
});
//西药/草药单选按钮事件
function funItem(obj){
    var itemClass=obj.value;
    $("#itemClass").val(obj.value);
    $(obj).attr("checked","true");
    if(itemClass=='A'){
        xiyaoGrid;
    }else if(itemClass=='B'){
        zhongyaoGrid;
    }
}

function addPre(){//点击新方
    $('#leftList').datagrid('insertRow', {
        url:{},//
        index:0,	// index start with 0
        row: {
            visitDate: visitDate,
            visitNo: visitNo,
            serialNo: serialNo,
            prescNo: prescNo,
            itemClass:itemClass,
            chargeIndicator:chargeIndicator
        }
    });
}

//保存处方及药品信息
function savePre(){
    $("#list_data").datagrid('endEdit', editRow);
    var  rows=$('#list_data').datagrid('getRows');
    var formJson=fromJson('prescForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"list\":"+tableJson+"}";
    $.postJSON(basePath+'/outppresc/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

function giveUpPre(){//弃方即刷新页面
    $('#leftList').datagrid('load');
    $('#leftList').datagrid('clearChecked');
    $('#list_data').datagrid('load');
    $('#list_data').datagrid('clearChecked');
}

//批量删除药品信息
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}
function del(id){
    //真删除数据
    $.ajax({
        'type': 'POST',
        'url': basePath+'/outppresc/delete',
        'contentType': 'application/json',
        'data': "ids="+id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息",data.code+"条记录删除成功！");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"保存失败", "error");
        }
    });
}

$("#stockRecordDialog").dialog({
    title: '选择规格',
    //style="width:500px;height:300px;
    width: 1000,
    height: 300,
    closed: false,
    catch: false,
    modal: true,
    closed: true,
    onOpen: function () {
        $("#stockRecordDatagrid").datagrid({
            singleSelect: true,
            fit: true,
            fitColumns: true,
            url: '/api/exp-stock/stock-export-record/',
            method: 'GET',
            columns: [[{
                title: '代码',
                field: 'expCode'
            }, {
                title: '名称',
                field: 'expName'
            }, {
                title: '包装规格',
                field: 'expSpec'
            }, {
                title: '数量',
                field: 'quantity'
            }, {
                title: '包装单位',
                field: 'units'
            }, {
                title: '基本规格',
                field: 'minSpec'
            }, {
                title: '基本单位',
                field: 'minUnits'
            }, {
                title: '厂家',
                field: 'firmId'
            }, {
                title: '进价价',
                field: 'purchasePrice'
            }, {
                title: '批发价',
                field: 'tradePrice'
            }, {
                title: '零售价',
                field: 'retailPrice'
            }, {
                title: '批号',
                field: 'batchNo'
            }, {
                title: '有效期',
                field: 'expireDate',
                formatter:formatterDate
            }, {
                title: '入库单号',
                field: 'documentNo'
            }, {
                title: '生产日期',
                field: 'producedate',
                formatter:formatterDate
            }, {
                title: '消毒日期',
                field: 'disinfectdate',
                formatter:formatterDate
            }, {
                title: '产品类别',
                field: 'expForm'
            }, {
                title: '是否包装',
                field: 'singleGroupIndicator',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        value = "是";
                    }
                    else if (value == "2") {
                        value = "否";
                    }
                    else if (value == "S") {
                        value = "是";
                    } else {
                        value = "是";
                    }
                    return value;
                }
            }, {
                title: '子包装1',
                field: 'subPackage1'
            }, {
                title: '子单位1',
                field: 'subPackageUnits1'
            }, {
                title: '子规格1',
                field: 'subPackageSpec1'
            }, {
                title: '子包装2',
                field: 'subPackage2'
            }, {
                title: '子单位2',
                field: 'subPackageUnits2'
            }, {
                title: '子规格2',
                field: 'subPackageSpec2'
            }, {
                title: '灭菌标识',
                field: 'killflag'
            }]],
            onLoadSuccess:function(data){
                flag = flag+1;
                if(flag==2){
                    var dat ={};
                    dat= $("#stockRecordDatagrid").datagrid('getData');
                    if(dat.total==0 && editIndex!=undefined){
                        $("#exportDetail").datagrid('endEdit', editIndex);
                        $.messager.alert('系统提示','库房暂无该产品,请重置产品名称','info');
                        $("#stockRecordDialog").dialog('close');
                        $("#exportDetail").datagrid('beginEdit', editIndex);
                    }
                    flag=0;
                }
            }
        });



    }
});


