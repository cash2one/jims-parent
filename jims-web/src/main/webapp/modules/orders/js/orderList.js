var editRow = undefined;
var rowNum=-1;
var indicator = [{ "value": "1", "text": "长" }, { "value": "2", "text": "临" }];
var Oclass =[{ "value": "1", "text": "药品" }, { "value": "2", "text": "非药品" }];
var zi =[{ "value": "1", "text": "自带药" }, { "value": "2", "text": "不加价" }];
var tujing =[{ "value": "1", "text": "无用法" }, { "value": "2", "text": "静点" }, { "value": "3", "text": "皮下注射" }];
var pinci =[{ "value": "1", "text": "Q8H" }, { "value": "2", "text": "Q12H" }, { "value": "3", "text": "Q4H" }];
$(function(){
    $('#orderList').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'get',
        url:basePath+'/inOrders/getOrders?'+$('#searchform').serialize(),
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
     /*   pagination:true,//分页控件*/
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'repeatIndicator',title:'长',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :indicator,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'orderClass',title:'类别',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :Oclass,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'startDateTime',title:'下达时间',width:'10%',align:'center',editor:{type: 'datebox'}},
            {field:'orderText',title:'医嘱内容',width:'10%',align:'center',editor:{
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
                       /* var drugCode = $("#centerList").datagrid('getEditor',{index:editRow,field:'drugCode'});
                        $(drugCode.target).textbox('setValue',row.drugCode);
                        var drugSpec = $("#centerList").datagrid('getEditor',{index:editRow,field:'drugSpec'});
                        $(drugSpec.target).textbox('setValue',row.drugSpec);
                        var firmId = $("#centerList").datagrid('getEditor',{index:editRow,field:'firmId'});
                        $(firmId.target).textbox('setValue',row.firmId);*/
                        var dosage = $("#orderList").datagrid('getEditor',{index:editRow,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dosage);
                        var dosageUnits = $("#centerList").datagrid('getEditor',{index:editRow,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dosageUnits);
                    }
            }}},
            {field:'billingAttr',title:'自',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :zi,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'dosage',title:'剂量',width:'5%',align:'center'},
            {field:'dosageUnits',title:'单位',width:'5%',align:'center'},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :tujing,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :pinci,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'performSchedule',title:'执行时间',width:'10%',align:'center',editor:{type: 'datebox'}},
          //  {field:'',title:'阴阳',width:'5%',align:'center'},
            {field:'stopDateTime',title:'结束时间',width:'10%',align:'center',editor:{type: 'datebox'}},
            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'doctor',title:'医生',width:'10%',align:'center',editor:'text',
                formatter:function(value, row, index){
                    return "李俊山";
                }},
            {field:'freqCounter',title:'次数',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停止医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止校対护士',width:'5%',align:'center'},
            {field:'patientId',hidden:'true',
                formatter:function(){
                return "15005451";
            }},
            {field:'visitId',hidden:'true',
                formatter:function(){
                    return "1";
                }},
            {field:'orderNo',hidden:'true'},
            {field:'orderSubNo',hidden:'true'}
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $("#orderList").datagrid('insertRow', {
                    index:0,
                    row:{
                    }
                });
            }
        },'-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                deleteItem();
            }
        },'-',{
            text: '插入',
            iconCls:'icon-save',
            handler:function(){

                save();
            }
        },'-',{
            text: '子医嘱',
            iconCls:'icon-save',
            handler:function(){
                $("#list_data").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#list_data").datagrid("endEdit", editRow);
                }
                save();
            }
        },'-',{
            text: '刷新',
            iconCls:'icon-reload',
            handler:function(){
                $("#list_data").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#list_data").datagrid("endEdit", editRow);
                }
                reload();
            }
        },'-',{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#list_data").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#list_data").datagrid("endEdit", editRow);
                }
                save();
            }
        }
        ],onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }
            if(rowNum!=rowIndex){
                if(rowNum>=0){
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum=rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
            }
        }
    });
    //设置分页控件
  /*  var p = $('#orderList').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });*/

    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
            $('#orderList').datagrid("load");   //点击搜索
        });
});
//保存
function save(){
    var  rows=$('#orderList').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    $.postJSON(basePath+'/inOrders/save',tableJson,function(data){
        if(data=='success'){
            $.messager.alert("提示消息","保存成功");
            $('#orderList').datagrid('load');
            $('#orderList').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

//刷新
function reload(){
    $("#orderList").datagrid("reload");
}

//保存子医嘱
function saveSubOrders(){
    var row = $('#tt').datagrid('getSelected');
    var orderNo =row.orderNo;
    var orderSubNo = row.orderSubNo;

}
