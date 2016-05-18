var editRow = undefined;
var prescNo;
var prescDate;
var bindingPrescTitle;
var administration = [{ "value": "口服", "text": "口服" }, { "value": "静脉注射", "text": "静脉注射" }, { "value": "小儿头皮静脉", "text": "小儿头皮静脉" }, { "value": "静脉输液", "text": "静脉输液" }, { "value": "续静滴", "text": "续静滴" }];
var frequency = [{ "value": "一日一次", "text": "一日一次" }, { "value": "一日二次", "text": "一日二次" }, { "value": "一日三次", "text": "一日三次" }];
//页面加载
$(function(){
    var patientId = '1';
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/doctDrugPrescMaster/list?patientId='+patientId,
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:'true'},
            {field:'prescNo',title:'处方号',width:'30%',align:'center'},
            {field:'prescDate',title:'处方日期',width:'30%',align:'center'},
            {field:'bindingPrescTitle',title:'处方名称',width:'30%',align:'center'}
        ]], onClickRow: function (index, row) {
            $.get(basePath+'/doctDrugPrescDetail/list?prescMasterId=' + row.id, function (data) {
                $("#centerList").datagrid("loadData", data);
            });
        }, onLoadSuccess: function(){
            var selRow =  $("#leftList").datagrid("getChecked");

            //判断是否有选中行数据，如果没有，则默认选中第一行
            if(selRow==null||selRow==''||selRow=='undefined'){
                $('#leftList').datagrid('selectRow',0);
                selRow = $("#leftList").datagrid("getChecked");
            }
            $.get(basePath+'/doctDrugPrescDetail/list?prescMasterId=' + selRow[0].id, function (data) {
                $("#centerList").datagrid("loadData", data);
            });
        }
    });
    $('#centerList').datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:true},
            {field:'drugName',title:'名称',width:'10%',align:'center',editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'drugName',
                    textField: 'drugName',
                    method:'GET',
                    url: basePath+'/outppresc/dictlist',
                    columns: [[
                        {field: 'drugCode', title: '代码', width: '8%', align: 'center'},
                        {field: 'drugName', title: '名称', width: '15%', align: 'center'},
                        {field: 'drugSpec', title: '规格', width: '15%', align: 'center'},
                        {field: 'firmId', title: '厂家', width: '15%', align: 'center'},
                        {field: 'dosage', title: '单次用量', width: '15%', align: 'center'},
                        {field: 'dosageUnits', title: '用量单位', width: '15%', align: 'center'}
                    ]],onClickRow: function (index, row) {
                        var drugCode = $("#centerList").datagrid('getEditor',{index:editRow,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drugCode);
                        var drugSpec = $("#centerList").datagrid('getEditor',{index:editRow,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drugSpec);
                        var firmId = $("#centerList").datagrid('getEditor',{index:editRow,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.firmId);
                        var dosage = $("#centerList").datagrid('getEditor',{index:editRow,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dosage);
                        var dosageUnits = $("#centerList").datagrid('getEditor',{index:editRow,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dosageUnits);
                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂商',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'dosage',title:'单次剂量',width:'5%',align:'center',hidden:true,editor:{type:'textbox',options:{editable:true}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',hidden:true,editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'frequency',title:'频次',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'quantity',title:'数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'costs',title:'应收',width:'10%',align:'center'},
            {field:'payments',title:'实收',width:'10%',align:'center'},
            {field:'packageSpec',title:'包装规格',hidden:'true'},
            {field:'packageUnits',title:'包装单位',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true',editor:{type:'textbox',options:{editable:false}}}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    $("#centerList").datagrid('insertRow', {
                        index:0,
                        row:{prescNo:prescNo}
                    });
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#centerList").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#centerList").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        }
    });
});
//点击新方
function addPre(){
    //disableForm('prescForm',false);
    //获取处方列表所有行，并取出所有行中处方号prescNo的最大值，加1后作为新处方的处方号
    var rows = $('#leftList').datagrid('getRows');
    if(rows.length>0){
        for(var i=0;i<rows.length;i++){
           /* if(rows[i].chargeIndicator=='新开'){
                $.messager.alert("提示消息", "已有新开处方，请先保存或者弃方后再试!");
                return;
            }*/
            for(var j=0;j<rows.length;j++){
                if(rows[i].prescNo>rows[j].prescNo){
                    prescNo= rows[i].prescNo+1;
                    break;
                }else{
                    prescNo = rows[j].prescNo+1;
                    break;
                }
            }
        }
    }else{
        prescNo=1;
    }
    $.ajax({
        'type': 'POST',
        'url': basePath+'/doctDrugPrescMaster/getPrescMaster',
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            var parse = eval(data);
            prescNo=parse.prescNo;
            prescDate = parse.prescDate;
            bindingPrescTitle = '';
            $('#leftList').datagrid('insertRow', {
                index:0,
                row: {
                    prescNo: prescNo,
                    prescDate: prescDate,
                    bindingPrescTitle: bindingPrescTitle
                }
            });
            $('#leftList').datagrid('selectRow',0);
        }
    })
    $("#centerList").datagrid();
}
//保存处方及药品信息
function savePre(){
    $("#centerList").datagrid('endEdit', editRow);
    var  rows=$('#centerList').datagrid('getRows');
    var formJson=fromJson('prescForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"list\":"+tableJson+"}";
    $.postJSON(basePath+'/doctDrugPrescMaster/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#centerList').datagrid('load');
            $('#centerList').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//点击毁方
function destroyPre() {
    //把你选中的 数据查询出来。
    var selectRows = $('#leftList').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要销毁的处方!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要进行毁方处理吗？", function (r) {
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
    $.ajax({
        'type': 'POST',
        'url': basePath+'/doctDrugPrescMaster/delete',
        'contentType': 'application/json',
        'data': ids=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息",data.code+"条记录毁方成功！");
                $('#leftList').datagrid('load');
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            }else{
                $.messager.alert('提示',"毁方失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"毁方失败", "error");
        }
    });
}
//刷新页面
function giveUpPre(){
    $('#leftList').datagrid('load');
    $('#leftList').datagrid('clearChecked');
    $('#centerList').datagrid('load');
    $('#centerList').datagrid('clearChecked');
}