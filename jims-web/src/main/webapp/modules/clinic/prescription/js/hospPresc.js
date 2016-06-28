var rowNum=-1;
var orderNo=0;
var prescNo;
var prescDate;
var bindingPrescTitle;
var prescSource;
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
                    data:drugData,
                    idField: 'item_name',
                    textField: 'item_name',
                    columns: [[
                        {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
                        {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                        {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                        {field: 'quanity', title: '库存', width: '15%', align: 'center'},
                        {field: 'units', title: '包装单位', width: '15%', align: 'center'},
                        {field: 'item_class', title: '库房', width: '15%', align: 'center'},
                        {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
                        {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center'},
                        {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'},
                        {field: 'subj_code', title: '',hidden:true},
                        {field: 'package_spec', title: '',hidden:true},
                        {field: 'performed_by', title: '',hidden:true},
                        {field: 'price', title: '',hidden:true}
                    ]],onClickRow: function (index, row) {
                        var drugCode = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drug_code);
                        var drugSpec = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drug_spec);
                        var firmId = $("#centerList").datagrid('getEditor',{index:rowNum,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.supplier);
                        var dosage = $("#centerList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dose_per_unit);
                        var packageUnits = $("#centerList").datagrid('getEditor',{index:rowNum,field:'packageUnits'});
                        $(packageUnits.target).textbox('setValue',row.package_unit);

                        var packageSpec = $("#centerList").datagrid('getEditor',{index:rowNum,field:'packageSpec'});
                        $(packageSpec.target).textbox('setValue',row.package_spec);
                        var costs = $("#centerList").datagrid('getEditor',{index:rowNum,field:'costs'});
                        $(costs.target).textbox('setValue',row.price);
                        var charges = $("#centerList").datagrid('getEditor',{index:rowNum,field:'payments'});
                        $(charges.target).textbox('setValue',row.price);
                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂商',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'dosage',title:'单次剂量',width:'5%',align:'center',hidden:true,editor:{type:'textbox',options:{editable:true}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',hidden:true,editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'10%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationDict,
                    valueField:'id',
                    textField:'administrationName'
                   /* onSelect:function(recode){
                       var idx =  $("#centerList").datagrid('getRows').length-1;
                        var charges = $("#centerList").datagrid('getEditor',{index:rowNum,field:'payments'});
                        $(charges.target).textbox('setValue',row.price);
                    }*/
                }
            }},
            {field:'frequency',title:'频次',width:'10%',align:'center',formatter:performFreqFormatter,editor:{
                type:'combobox',
                options:{
                    data :performFreqDict,
                    valueField:'id',
                    textField:'freqDesc'
                }
            }},
            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'quantity',title:'数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'packageUnits',title:'包装单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'costs',title:'应收',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'payments',title:'实收',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'packageSpec',title:'包装规格',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderNo',title:'处方',hidden:'true',
                formatter: function (value, row, index) {
                    orderNo = value;
                    return value;
                }},
            {field:'subOrderNo',title:'子处方',hidden:'true'},
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
                    var index = $("#centerList").datagrid('appendRow', {
                            prescNo:prescNo,
                            orderNo:orderNo,
                            subOrderNo:orderNo
                        }).datagrid('getRows').length-1;
                    $("#centerList").datagrid('beginEdit', index);
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        },{
            text: '子处方',
            iconCls: 'icon-edit',
            handler: function() {
                var selRow = $('#centerList').datagrid('getChecked');
                if(selRow!=null&&selRow!=''&&selRow!='undefined') {
                    changeSubPresc(selRow);
                }else{
                    $.messager.alert('提示',"请选择要操作的处方！", "error");
                }
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
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
    $("#centerList").datagrid('loadData', { total: 0, rows: [] });
    orderNo=0;
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
                   var idx = $('#leftList').datagrid('appendRow', {
                            prescNo: prescNo,
                            prescDate: prescDate,
                            bindingPrescTitle: bindingPrescTitle
                    }).datagrid('getRows').length-1;
                    $("#prescNo").val(prescNo);
                    $('#leftList').datagrid('selectRow',idx);
                }
            })
            $("#centerList").datagrid();
        }
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
                var lidx = $('#leftList').datagrid('appendRow', {
                        prescNo: prescNo,
                        prescDate: prescDate,
                        bindingPrescTitle: bindingPrescTitle
                }).datagrid("getRows").length-1;
                $("#prescNo").val(prescNo);
                $('#leftList').datagrid('selectRow',lidx);
            }
        })
        $("#centerList").datagrid();
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


//把选中处方修改成子处方
function changeSubPresc(row){
    var rows = $('#centerList').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    var nowrow = row[0];
    var index= $('#centerList').datagrid('getRowIndex',nowrow);
    if(index>=0) {
        //1.判断该条处方是否有子处方，如果有，则不允许把当前处方变成其他处方的子处方
        prerow = rows[index-1];
        //变为子处方时，途径与频次与父处方一致
        nowrow.subOrderNo = prerow.orderNo;
        nowrow.administration=prerow.administration;
        nowrow.frequency=prerow.frequency;
    }else{
        $.messager.alert('提示',"第一条处方不能设置子处方", "warning");
    }
}