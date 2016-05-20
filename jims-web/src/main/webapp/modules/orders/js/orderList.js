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
                deleteOrders();
            }
        },'-',{
            text: '传输医嘱',
            iconCls:'icon-add',
            handler:function(){
                save();
            }
        },'-',{
            text: '子医嘱',
            iconCls:'icon-save',
            handler:function(){
                var selRow = $('#orderList').datagrid('getChecked');
                if(selRow!=null&&selRow!=''&&selRow!='undefined') {
                    changeSubNo(selRow);
                }else{
                    $.messager.alert('提示',"请选择要操作的医嘱！", "error");
                }
            }
        },'-',{
            text: '刷新',
            iconCls:'icon-reload',
            handler:function(){
                reload();
            }
        },'-',{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#orderList").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#orderList").datagrid("endEdit", editRow);
                }
                save();
            }
        }
        ],onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#orderList');
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
        },onDblClickRow:function(rowIndex, rowData){
           /* var dataGrid=$('#orderList');
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
            }*/
        }
    });

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

//删除
function deleteOrders(){
    var selectRows = $('#orderList').datagrid("getSelections");
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
            if(strIds=='undefined'|| strIds==''){
                var index1= $('#orderList').datagrid('getRowIndex', $("#orderList").datagrid('getSelected'))
                $('#orderList').datagrid('deleteRow',index1);
            }else {
                //真删除数据
                $.ajax({
                    'type': 'POST',
                    'url': basePath + '/operatioinOrder/delete',
                    'contentType': 'application/json',
                    'data': id = strIds,
                    'dataType': 'json',
                    'success': function (data) {
                        if (data == 1) {
                            $.messager.alert("提示消息", data + "条记录删除成功！");
                            $('#operationName').datagrid('load');
                            $('#operationName').datagrid('clearChecked');
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
}
//把医嘱改变成子医嘱
function changeSubNo(row){
    alert(rowNum)
    //1.获取选中行的上条医嘱
    var rows = $('#orderList').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    if(rowNum!=0){
        prerow = rows[rowNum-1];
        //2.判断2条医嘱是否同是长期或者临时,进行3判断
        if(row[0].repeatIndicator==prerow.repeatIndicator){
            //3.判断2条医嘱是否同时药品，（药疗才可组成组合医嘱），进行4判断
            if(row[0].orderClass==1&&prerow.orderClass==1){
                //4.判断上一条医嘱状态，如果状态允许，进行5判断
                if(prerow.orderStatus==1 || prerow.orderStatus=="1"){
                    //5.判断选中行row的医嘱状态，如果状态是新开，则进行6操作
                    if(row[0].orderStatus==1||row[0].orderStatus=='1'){
                        //6.获取上一行的orderSubNo，则给选中行的子医嘱号累加1并赋值，并把医嘱号更改为与上行医嘱号相同
                        row[0].orderSubNo = parseInt(prerow.orderSubNo)+1;
                        row[0].orderNo = prerow.orderNo;
                        //7.选中行为子医嘱时，获取上行医嘱的如下字段给选中行医嘱赋值：长期字段，类别字段，途径，频次，执行时间，医生说明字段，并设置不可填写
                        row[0].repeatIndicator=prerow.repeatIndicator;
                        row[0].orderClass=prerow.orderClass;
                        row[0].administration=prerow.administration;
                        row[0].frequency=prerow.frequency;
                        row[0].performSchedule = prerow.performSchedule;
                        row[0].freqDetail= prerow.freqDetail;

                    }else{
                        $.messager.alert('提示',parseInt(rowNum+1)+"行不是新开医嘱不能构成组合医嘱！", "error");
                    }
                }else{
                    $.messager.alert('提示',parseInt(rowNum)+"行不是新开医嘱不能构成组合医嘱！", "error");
                }
            }else{
                $.messager.alert('提示',"不是药疗医嘱不能构成组合医嘱！", "error");
            }
        }else{
            $.messager.alert('提示',"长期和临时医嘱不能构成组合医嘱！", "error");
        }
    }else{
        $.messager.alert('提示',"该医嘱不能构成组合医嘱！", "error");
    }
}