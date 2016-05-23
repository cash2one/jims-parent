var rowNum=-1;
var prescNo;
var prescDate;
var bindingPrescTitle;
var prescSource;
var administration = [{ "value": "口服", "text": "口服" }, { "value": "静脉注射", "text": "静脉注射" }, { "value": "小儿头皮静脉", "text": "小儿头皮静脉" }, { "value": "静脉输液", "text": "静脉输液" }, { "value": "续静滴", "text": "续静滴" }];
var frequency = [{ "value": "一日一次", "text": "一日一次" }, { "value": "一日二次", "text": "一日二次" }, { "value": "一日三次", "text": "一日三次" }];
var dispensary =  [{ "value": "1", "text": "西药局" }, { "value": "2", "text": "中药局" }];
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
            {field:'prepayment',title:'预交金',hidden:'true'},
            {field:'prescSource',title:'处方来源',hidden:'true'},
            {field:'dianosis',title:'诊断',hidden:'true'},
            {field:'usage',title:'用法',hidden:'true'},
            {field:'prescStatus',title:'处方状态',hidden:'true'},
            {field:'prescDate',title:'处方日期',width:'30%',align:'center'},
            {field:'prescNo',title:'处方号',width:'30%',align:'center'},
            {field:'bindingPrescTitle',title:'处方名称',width:'30%',align:'center'}
        ]], onClickRow: function (index, row) {
            loadSubData(row);
        }, onLoadSuccess: function(){
            var selRow =  $("#leftList").datagrid("getChecked");

            //判断是否有选中行数据，如果没有，则默认选中第一行
            if(selRow==null||selRow==''||selRow=='undefined'){
                $('#leftList').datagrid('selectRow',0);
                selRow = $("#leftList").datagrid("getChecked");
            }
            loadSubData(selRow[0]);
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
                        var drugCode = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drugCode);
                        var drugSpec = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drugSpec);
                        var firmId = $("#centerList").datagrid('getEditor',{index:rowNum,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.firmId);
                        var dosage = $("#centerList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dosage);
                        var dosageUnits = $("#centerList").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
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
            {field:'packageUnits',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'costs',title:'应收',width:'10%',align:'center'},
            {field:'payments',title:'实收',width:'10%',align:'center'},
            {field:'packageSpec',title:'包装规格',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true',editor:{type:'textbox',options:{editable:false}}}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
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
        }],onDblClickRow:function (rowIndex, rowData) {
            var dataGrid=$('#centerList');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }

        }
    });
});
function loadSubData(row){
    if(row!=undefined&&row!='undifined') {
        $("#prepayment").val(row.prepayment);
        $("#prescNo").val(row.prescNo);
        $("#diagnosisName").val(row.dianosis);
        $("#usage").val(row.usage);
        $("#bindingPrescTitle").val(row.bindingPrescTitle);
        //funItem(row.prescSource);
        $.get(basePath + '/doctDrugPrescDetail/list?prescMasterId=' + row.id, function (data) {
            $("#centerList").datagrid("loadData", data);
        });

    }
}
//点击新方
function addPre(){
    var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
    if(selRow!=null&&selRow!=''&&selRow!='undefined'){
        if(selRow[0].prescStatus==0||selRow[0].prescStatus=='0'){
            $.messager.alert('提示',"当前有未保存的处方，请保存或者刷新后重试", "error");
        }else{
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
                    $("#prescNo").val(prescNo);
                    $('#leftList').datagrid('selectRow',0);
                }
            })
            $("#centerList").datagrid();
        }
    }

}
//保存处方及药品信息
function savePre(){
    $("#centerList").datagrid('endEdit', rowNum);
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
//西药/草药单选按钮事件
function funItem(obj){
    $("#prescSource").val(obj.value);
    prescSource=obj.value;
    $('input[name="prescSource"]:radio').each(function(){
        if($(this).val()==obj.value){
            $(this).prop("checked",true);
            if(obj.value==1){
                //部分信息西药不需要显示
                //$('.vdbox input').removeAttribute("value")
                $('.vdbox').hide();
            }else{
                $('.vdbox').show();
            }
        }else{
            $(this).prop("checked",false);
        }
    });
}
