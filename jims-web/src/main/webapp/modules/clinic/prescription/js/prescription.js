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

$(function(){
    var itemClass = $("#itemClass").val();
    var clinicId = $("#clinicMasterId",parent.document).val();
    $("#clinicId").val(clinicId);
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
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'drugCode',
                    textField: 'drugName',
                    method:'GET',
                    url: basePath+'/outppresc/dictlist',
                    columns: [[
                        {field: 'drugCode', title: '代码', width: '8%', align: 'center'},
                        {field: 'drugName', title: '名称', width: '15%', align: 'center'},
                        {field: 'drugSpec', title: '规格', width: '15%', align: 'center'},
                        {field: 'firmId', title: '厂家', width: '15%', align: 'center'},
                        {field: 'dosage', title: '单次用量', width: '15%', align: 'center'},
                        {field: 'dosageUnits', title: '用量单位', width: '15%', align: 'center'},
                        {field: 'itemClass', title: '药局', width: '15%', align: 'center',
                            formatter: function (value, row, index) {
                            if (value == "A") {
                                value = "西药局";
                            }
                            else if (value == "B") {
                                value = "中药局";
                            }
                            return value;
                        }}
                    ]],onClickRow: function (index, row) {
                        var drugSpec = $("#list_data").datagrid('getEditor',{index:editRow,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drugSpec);
                        var firmId = $("#list_data").datagrid('getEditor',{index:editRow,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.firmId);
                        var dosage = $("#list_data").datagrid('getEditor',{index:editRow,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dosage);
                        var dosageUnits = $("#list_data").datagrid('getEditor',{index:editRow,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dosageUnits);
                        var itemClass = $("#list_data").datagrid('getEditor',{index:editRow,field:'itemClass'});
                        $(itemClass.target).textbox('setValue',row.itemClass);
                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'repetition',title:'剂数',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:true,disable:false}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:{type:'numberbox',options:{editable:false,disable:false}}},
            {field:'itemClass',title:'药局',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :providedIndicator,
                    valueField:'value',
                    textField:'text'
                }
            }},
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :skinFlag,
                    valueField:'value',
                    textField:'text'
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
                        row:{prescNo:selRow[0].prescNo}
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
            $("#prescDialog").dialog('open');
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }

        }
    });
    $("#prescDialog").dialog({
        title: '计价项目',
        //style="width:500px;height:300px;
        left:1235,
        top:480,
        width: 500,
        height: 300,
        catch: false,
        modal: false,
        closed: true,
        onOpen: function () {
            $("#prescriptionDatagrid").datagrid({
                singleSelect: true,
                fit: true,
                fitColumns: true,
                url: basePath+'/outppresc/jijia',
                method: 'GET',
                columns: [[{
                    title: '类别',
                    field: 'itemClass',
                    width:'15%'
                }, {
                    title: '计价项目',
                    field: 'drugName',
                    width:'20%'
                }, {
                    title: '规格',
                    field: 'drugSpec',
                    width:'20%'
                }, {
                    title: '数量',
                    field: 'amount',
                    width:'15%'
                }, {
                    title: '单位',
                    field: 'units',
                    width:'15%'
                }, {
                    title: '金额',
                    field: 'price',
                    width:'15%'
                }]],
                onLoadSuccess:function(data){
                    /*  flag = flag+1;
                     if(flag==2){
                     var dat ={};
                     dat= $("#prescriptionDatagrid").datagrid('getData');
                     if(dat.total==0 && editIndex!=undefined){
                     $("#exportDetail").datagrid('endEdit', editIndex);
                     $.messager.alert('系统提示','库房暂无该产品,请重置产品名称','info');
                     $("#stockRecordDialog").dialog('close');
                     $("#exportDetail").datagrid('beginEdit', editIndex);
                     }
                     flag=0;
                     }*/
                }
            });
        }
    });
});
//西药/草药单选按钮事件
function funItem(obj){
    var itemClass=obj.value;
    $("#itemClass").val(obj.value);
    $(obj).attr("checked","true");
    if(itemClass=='A'){
        $('#list_data').datagrid({
            singleSelect: true,
            fit: true,
            fitColumns: true,
            nowrap: false,
            columns:[[      //每个列具体内容
                {field:'prescNo',title:'处方号',width:'5%',align:'center'},
                {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                    type:'combogrid',
                    options: {
                        panelWidth: 500,
                        idField: 'drugCode',
                        textField: 'drugName',
                        method:'GET',
                        url: basePath+'/outppresc/dictlist',
                        columns: [[
                            {field: 'drugCode', title: '代码', width: '8%', align: 'center'},
                            {field: 'drugName', title: '名称', width: '15%', align: 'center'},
                            {field: 'drugSpec', title: '规格', width: '15%', align: 'center'},
                            {field: 'firmId', title: '厂家', width: '15%', align: 'center'},
                            {field: 'dosage', title: '单次用量', width: '15%', align: 'center'},
                            {field: 'dosageUnits', title: '用量单位', width: '15%', align: 'center'},
                            {field: 'itemClass', title: '药局', width: '15%', align: 'center',
                                formatter: function (value, row, index) {
                                    if (value == "A") {
                                        value = "西药局";
                                    }
                                    else if (value == "B") {
                                        value = "中药局";
                                    }
                                    return value;
                                }}
                        ]],onClickRow: function (index, row) {
                            var drugSpec = $("#list_data").datagrid('getEditor',{index:editRow,field:'drugSpec'});
                            $(drugSpec.target).textbox('setValue',row.drugSpec);
                            var firmId = $("#list_data").datagrid('getEditor',{index:editRow,field:'firmId'});
                            $(firmId.target).textbox('setValue',row.firmId);
                            var dosage = $("#list_data").datagrid('getEditor',{index:editRow,field:'dosage'});
                            $(dosage.target).textbox('setValue',row.dosage);
                            var dosageUnits = $("#list_data").datagrid('getEditor',{index:editRow,field:'dosageUnits'});
                            $(dosageUnits.target).textbox('setValue',row.dosageUnits);
                            var itemClass = $("#list_data").datagrid('getEditor',{index:editRow,field:'itemClass'});
                            $(itemClass.target).textbox('setValue',row.itemClass);
                        }
                    }
                }},
                {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
                {field:'firmId',title:'厂家',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
                {field:'repetition',title:'剂数',width:'5%',align:'center',editor:'numberbox'},
                {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:true,disable:false}}},
                {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
                {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                    type:'combobox',
                    options:{
                        data :administration,
                        valueField:'value',
                        textField:'text'
                    }
                }},
                {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                    type:'combobox',
                    options:{
                        data :frequency,
                        valueField:'value',
                        textField:'text'
                    }
                }},
                {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
                {field:'units',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
                {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
                {field:'charges',title:'实收',width:'5%',align:'center',editor:{type:'numberbox',options:{editable:false,disable:false}}},
                {field:'itemClass',title:'药局',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
                {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
                {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                    type:'combobox',
                    options:{
                        data :providedIndicator,
                        valueField:'value',
                        textField:'text'
                    }
                }},
                /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
                {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                    type:'combobox',
                    options:{
                        data :skinFlag,
                        valueField:'value',
                        textField:'text'
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
                            row:{prescNo:selRow[0].prescNo}
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
                $("#prescDialog").dialog('open');
                //tooltips选中行，药品价目列表信息
                if (editRow != undefined) {
                    $("#list_data").datagrid('endEdit', editRow);
                }

            }
        });
    }else if(itemClass=='B'){
        $('#list_data').datagrid({
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
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
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
            $.messager.alert('提示',"删除失败", "error");
        }
    });
}



