var rowNum=-1;
var visitDate;
var visitNo;
var prescNo;
var itemClass;
var clinicId;
var chargeIndicator='新开';
//页面加载
$(function(){
    itemClass = $("#itemClass").val();
    clinicId = $("#clinicMasterId",parent.document).val();
    $("#clinicId").val(clinicId);
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list?clinicId='+clinicId,
        columns:[[      //每个列具体内容
            {field:'visitDate',title:'就诊时间',width:'20%',align:'center'},
            {field:'visitNo',title:'就诊序号',width:'20%',align:'center'},
            {field:'prescNo',title:'处方号',width:'20%',align:'center'},
            {field:'itemClass',title:'处方分类',width:'20%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "A") {
                        value = "西、成药";
                    }
                    else if (value == "B") {
                        value = "草药";
                    }
                    return value;
                }},
            {field:'chargeIndicator',title:'收费状态',width:'20%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        value = "未收费";
                    }
                    return value;
                }}
        ]], onClickRow: function (index, row) {
            subLoadData(row);
        }, onLoadSuccess: function(){
            var selRow =  $("#leftList").datagrid("getChecked");

            //判断是否有选中行数据，如果没有，则默认选中第一行
            if(selRow==null||selRow==''||selRow=='undefined'){
                $('#leftList').datagrid('selectRow',0);
                selRow = $("#leftList").datagrid("getChecked");
            }
            subLoadData(selRow[0]);
        }
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
                    data:ordersDrugData,
                    idField:'drug_code',
                    textField:'item_name',
                    columns:[
                        [
                            {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
                            {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                            {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                            {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
                            {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center'},
                            {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'}
                        ]],onClickRow: function (index, row) {
                        var drugCode = $("#list_data").datagrid('getEditor',{index:rowNum,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drug_code);
                        var drugSpec = $("#list_data").datagrid('getEditor',{index:rowNum,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drug_spec);
                        var firmId = $("#list_data").datagrid('getEditor',{index:rowNum,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.supplier);
                        var dosage = $("#list_data").datagrid('getEditor',{index:rowNum,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dose_per_unit);
                        var dosageUnits = $("#list_data").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dose_units);
                        /*var itemClass = $("#list_data").datagrid('getEditor',{index:rowNum,field:'itemClass'});
                        $(itemClass.target).textbox('setValue',row.itemClass);*/

                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'repetition',title:'剂数',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:true,disable:false}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'5%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationDict,
                    valueField:'id',
                    textField:'administrationName',
                    required:true
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',formatter:performFreqFormatter,editor:{
                type:'combobox',
                options:{
                    data :performFreqDict,
                    valueField:'id',
                    textField:'freqDesc',
                    required:true,
                    onSelect:function(rec){

                    }
                }
            }},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:{type:'numberbox',options:{editable:false,disable:false}}},
            {field:'itemClass',title:'药局',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
           /* {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :providedIndicator,
                    valueField:'value',
                    textField:'text'
                }
            }},*/
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试',width:'5%',align:'center',formatter:skinFlagFormatter,editor:{
                type:'combobox',
                options:{
                    data :skinFlag,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'skinResult',title:'皮试结果',width:'5%',align:'center',formatter:skinResultFormatter,editor:{
                type:'combobox',
                options:{
                    data :skinResult,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'subOrderNo',title:'子处方',hidden:'true'},
            {field:'itemNo',title:'项目序号',hidden:'true'},
            {field:'serialNo',title:'流水号',hidden:'true'},
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
        }],onDblClickRow:function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
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

        },onClickRow:function(rowIndex,rowData){
            $("#prescDialog").dialog('open');
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

                }
            });
        }
    });
});
//加载数据时加载子项方法
function subLoadData(row){
    if(row!=undefined&&row!='undifined'){
        //如果选中数据非新开数据，则右侧药局部分禁用
        if(row.chargeIndicator!='新开'){
            disableForm('prescForm',true);
        }else{
            disableForm('prescForm',false);
        }
        if(row.itemClass=='A'){
            changeRadio('A');
            $.get(basePath+'/outppresc/sublist?prescNo=' + row.prescNo+"&clinicId="+clinicId, function (data) {
                $("#list_data").datagrid("loadData", data);
            });
        }else{
            changeRadio('B');
            $.get(basePath+'/outppresc/sublist?prescNo=' + row.prescNo+"&clinicId="+clinicId, function (data) {
                $("#list_data").datagrid("loadData", data);
            });
        }
    }

}
//西药/草药单选按钮事件
function funItem(obj){
    itemClass=obj.value;
    $("#itemClass").val(obj.value);
    changeRadio(obj.value);
    var selRow = $('#leftList').datagrid('getChecked');
    subItem(itemClass,selRow[0]);

}
//西药/草药单选按钮事件-更新行
function subItem(itemClass,selRow){
    if(itemClass=='A'){
        $('#leftList').datagrid('updateRow',{
            index: 0,
            row: {
                visitDate: selRow.visitDate,
                visitNo: selRow.visitNo,
                prescNo: selRow.prescNo,
                itemClass:'西、成药',
                chargeIndicator:selRow.chargeIndicator
            }
        });
    }else if(itemClass=='B'){
        $('#leftList').datagrid('updateRow',{
            index: 0,
            row: {
                visitDate: selRow.visitDate,
                visitNo: selRow.visitNo,
                prescNo: selRow.prescNo,
                itemClass:'草药',
                chargeIndicator:selRow.chargeIndicator
            }
        });
    }
}
//点击新方
function addPre(){
    disableForm('prescForm',false);
    //获取处方列表所有行，并取出所有行中处方号prescNo的最大值，加1后作为新处方的处方号
     var rows = $('#leftList').datagrid('getRows');
     if(rows.length>0){
         for(var i=0;i<rows.length;i++){
             if(rows[i].chargeIndicator=='新开'){
                 $.messager.alert("提示消息", "已有新开处方，请先保存或者弃方后再试!");
                 return;
             }
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
        'url': basePath+'/outppresc/getClinicMaster',
        'contentType': 'application/json',
        'data': id=clinicId,
        'dataType': 'json',
        'success': function(data){
            var parse = eval(data);
            visitDate=parse.visitDate;
            visitNo = parse.visitNo;
            itemClass = itemClass;
            prescNo = prescNo;
            chargeIndicator = chargeIndicator;
            $('#leftList').datagrid('insertRow', {
                url:{},//
                index:0,	// index start with 0
                row: {
                    visitDate: visitDate,
                    visitNo: visitNo,
                    prescNo: prescNo,
                    itemClass:itemClass,
                    chargeIndicator:chargeIndicator
                }
            });
            $('#leftList').datagrid('selectRow',0);
        }
    })
    $("#list_data").datagrid();
}
//保存处方及药品信息
function savePre(){
    $("#list_data").datagrid('endEdit', rowNum);
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
//弃方即刷新页面
function giveUpPre(){
    $('#leftList').datagrid('load');
    $('#leftList').datagrid('clearChecked');
    $('#list_data').datagrid('load');
    $('#list_data').datagrid('clearChecked');
}
//删除药品信息
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
    $.ajax({
        'type': 'POST',
        'url': basePath+'/outppresc/delete',
        'contentType': 'application/json',
        'data': ids=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息",data.code+"条记录删除成功！");
                $('#leftList').datagrid('load');
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
//禁用右侧中西药
function disableForm(formId,isDisabled) {
    var attr="disable";
    if(!isDisabled){
        attr="enable";
    }
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);

    /*//禁用jquery easyui中的下拉选（使用select生成的combox）
    $("#" + formId + " select[class='textbox-text validatebox-text']").each(function () {
        if (this.id) {
            $("#" + this.id).combobox(attr);
        }
    });*/
}
//选中处方行，更改radio选中值
function changeRadio(obj){
    $("#itemClass").val(obj);
    itemClass=obj;
    $('input:radio').each(function(){
        if($(this).val()==obj){
            $(this).prop("checked",true);
        }else{
            $(this).prop("checked",false);
        }
    });
}



