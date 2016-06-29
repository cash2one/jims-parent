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
            {field:'dispensarySub',title:'发药子药局',hidden:'true'},
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
        width: 'auto',
        height: 'auto',
        singleSelect: true,
        fit: true,
        fitColumns: true,
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:true},
            {field:'markorderSubNo',title:'全',width:'3%',align:'center',formatter:function(value, rowData, rowIndex){
                if(rowData.orderSubNo==rowData.orderNo){
                    return "";
                }else{
                    return "子";
                }
            }},
            {field:'drugName',title:'名称',width:'10%',align:'center',editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    required: true,
                    data:drugData,
                    idField: 'item_name',
                    textField: 'item_name',
                    columns: [[
                        {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
                        {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                        {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                        {field: 'quanity', title: '库存', width: '15%', align: 'center'},
                        {field: 'units', title: '单位', width: '15%', align: 'center'},
                        {field: 'item_class', title: '库房', width: '15%', align: 'center'},
                        {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
                        {field: 'dose_per_unit', title: '最小用量', width: '15%', align: 'center'},
                        {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'},
                        {field: 'subj_code', title: '',hidden:true},
                        {field: 'package_spec', title: '',hidden:true},
                        {field: 'performed_by', title: '',hidden:true},
                        {field: 'price', title: '',hidden:true},
                        {field: 'package_units', title: '',hidden:true},
                        {field: 'package_spec', title: '',hidden:true},
                        {field: 'sub_storage', title: '',hidden:true},
                        {field: 'firm_id', title: '',hidden:true}
                    ]],keyHandler: {
                        query: function(q) {
                            var ed = $('#centerList').datagrid('getEditor', {index:rowNum,field:'drugName'});
                            comboGridCompleting(q,'drugName');
                            $(ed.target).combogrid("grid").datagrid("loadData", comboGridComplete);
                        }
                    },onClickRow: function (index, row) {
                        var drugCode = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drug_code);
                        var drugSpec = $("#centerList").datagrid('getEditor',{index:rowNum,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drug_spec);
                        var firm = $("#centerList").datagrid('getEditor',{index:rowNum,field:'firm'});
                        $(firm.target).textbox('setValue',row.supplier);
                        var firmId = $("#centerList").datagrid('getEditor',{index:rowNum,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.firm_id);
                        var dosage = $("#centerList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dose_per_unit);
                        var packageUnits = $("#centerList").datagrid('getEditor',{index:rowNum,field:'packageUnits'});
                        $(packageUnits.target).textbox('setValue',row.package_unit);
                        var packageSpec = $("#centerList").datagrid('getEditor',{index:rowNum,field:'packageSpec'});
                        $(packageSpec.target).textbox('setValue',row.package_spec);
                        var costs = $("#centerList").datagrid('getEditor',{index:rowNum,field:'costs'});
                        $(costs.target).textbox('setValue',row.price);
                        var payments = $("#centerList").datagrid('getEditor',{index:rowNum,field:'payments'});
                        $(payments.target).textbox('setValue',row.price);
                        var dosageUnits = $("#centerList").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dose_units);
                        $('#leftList').datagrid('getChecked');
                        var selrow = $('#leftList').datagrid('getChecked');
                        selrow[0].dispensarySub = row.sub_storage;
                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'10%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firm',title:'厂商',width:'12%',align:'center',formatter:drugFirmFormatter,editor:{type:'textbox',options:{editable:false,disable:false}}},

            {field:'dosageEach',title:'单次剂量',width:'5%',align:'center',editor: { type:'numberbox', options: {required: true}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'10%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationDict,
                    valueField:'id',
                    textField:'administrationName',
                    required: true
                }
            }},
            {field:'frequency',title:'频次',width:'10%',align:'center',formatter:performFreqFormatter,editor:{
                type:'combobox',
                options:{
                    data :performFreqDict,
                    valueField:'id',
                    textField:'freqDesc',
                    required: true
                }
            }},

            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'quantity',title:'总量',width:'5%',align:'center',editor:'numberbox'},
            {field:'packageUnits',title:'包装单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'costs',title:'应收',width:'8%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'payments',title:'实收',width:'8%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'dosage',title:'剂量',width:'5%',align:'center',hidden:true,editor:{type:'textbox',options:{editable:true}}},
            {field:'packageSpec',title:'包装规格',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂商ID',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:true}}},
            {field:'orderNo',title:'处方',hidden:'true',
                formatter: function (value, row, index) {
                    orderNo = value;
                    return value;
                }},
            {field:'orderSubNo',title:'子处方',hidden:'true'},
            {field:'drugCode',title:'药品编号',hidden:'true',editor:{type:'textbox',options:{editable:false}}}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var dataGrid=$('#centerList');
                if(!dataGrid.datagrid('validateRow', rowNum)){
                    $.messager.alert('提示',"请填写本行数据后，再添加下一条处方", "error");
                    return false
                }
                $("#centerList").datagrid('endEdit', rowNum);
                if(rowNum>=0){
                    rowNum++;
                }
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    var index = $("#centerList").datagrid('appendRow', {
                            prescNo:prescNo,
                            orderNo:orderNo,
                            orderSubNo:orderNo
                        }).datagrid('getRows').length-1;
                    rowNum=index;
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
        }],onClickRow:function (rowIndex, rowData) {
            var dataGrid=$('#centerList');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                $.messager.alert('提示',"数据填写不完整，请填写完整后再对其他行进行编辑！", "error");
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
    //处方属性下拉框
    $('#prescAttr').combobox({
        data: prescAttrDict,
        valueField: 'label',
        textField: 'label'
    });
    //诊断
    $('#diagnosisName').combogrid({
        width:'300',
        height: 'auto',
        data:icdAllData,
        idField:'zhongwen_mingcheng',
        textField:'zhongwen_mingcheng',
        mode: 'remote',
        columns: [[
            {field: 'zhongwen_mingcheng', title: '中文名称', width: '30%', align: 'left'},
            {field: 'code', title: 'ICD-10编码', width: '20%', align: 'left'},
            {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'left'},
        ]],keyHandler: {
            query: function (q) {
                comboGridCompleting(q, 'diagnosisName');
                $('#diagnosisName').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    })
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
    var dataGrid=$('#centerList');
    if(!dataGrid.datagrid('validateRow', rowNum)){
        $.messager.alert('提示',"请填写完本行数据后，再保存", "error");
        return false
    }
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
    $('#centerList').datagrid('endEdit',rowNum);
    var rows = $('#centerList').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    var afterrow;
    var nowrow = row[0];
    var index= $('#centerList').datagrid('getRowIndex',nowrow);
    if(index>=0) {
        var dataGrid=$('#centerList');
        if(!dataGrid.datagrid('validateRow', index)){
            $.messager.alert('提示',"数据填写不完整，请填写完整后再添加子处方", "error");
            return false
        }
        $('#centerList').datagrid('endEdit', index);
        $('#centerList').datagrid('beginEdit', index);
        //获取下一行
        afterrow=rows[index+1];
        //判断本身是否是子处方
        if(afterrow!=undefined){
            //判断是否是子处方
            if(nowrow.orderNo!=nowrow.orderSubNo){
                //判断是否有子处方
                if(afterrow.orderSubNo == nowrow.orderSubNo){
                    return false;
                }else{
                    //删除子处方
                    nowrow.orderSubNo = nowrow.orderNo;
                    rowNum=index;
                    $('#centerList').datagrid('endEdit', index);
                    $('#centerList').datagrid('beginEdit', index);
                    return false;
                }
            }
        }else{
            if(nowrow.orderNo!=nowrow.orderSubNo){
                nowrow.orderSubNo = nowrow.orderNo;
                rowNum=index;
                $('#centerList').datagrid('endEdit', index);
                $('#centerList').datagrid('beginEdit', index);
                return false;
            }
        }
        if(afterrow!=undefined){
            if(afterrow.orderSubNo == nowrow.orderNo){
                $.messager.alert('提示',"此处方已经有子处方，不能设置子处方", "error");
                return false;
            }
        }
        //1.判断该条医嘱是否有子处方，如果有，则不允许把当前处方变成其他处方的子处方
        prerow = rows[index-1];
        if(nowrow.administration!=prerow.administration){
            $.messager.alert('提示',"子处方与处方途径不一致，不能设置为子处方", "error");
            return false;
        }
        if(nowrow.frequency!=prerow.frequency){
            $.messager.alert('提示',"子处方与处方频次不一致，不能设置为子处方", "error");
            return false;
        }
        nowrow.orderSubNo = prerow.orderNo;
        nowrow.administration=prerow.administration;
        nowrow.frequency=prerow.frequency;
        $('#centerList').datagrid('endEdit', index);
        $('#centerList').datagrid('beginEdit', index);

    }else{
        $.messager.alert('提示',"第一条处方不能设置子处方", "warning");
    }
}

