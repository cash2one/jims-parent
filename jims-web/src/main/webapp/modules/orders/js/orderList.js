var editRow = undefined;
var rowNum=-1;
var editNum=-1;
var patId ='15006135';
var visitId = '1';
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
        singleSelect:true,//是否单选
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
                        {field: 'dosage', title: '单次用量', width: '15%', align: 'center', editor: 'text'},
                        {field: 'dosageUnits', title: '用量单位', width: '15%', align: 'center', editor: 'text'}
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
            {field:'dosage',title:'剂量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'dosageUnits',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
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
            {field:'conversionDateTime',title:'执行时间',width:'10%',align:'center',editor:{type: 'datebox'}},
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
           /* {field:'patientId',hidden:'true',
                formatter:function(){
                return "15005451";
            }},
            {field:'visitId',hidden:'true',
                formatter:function(){
                    return "1";
                }},*/
            {field:'orderNo',hidden:'true'},
            {field:'orderSubNo',hidden:'true'},
            {field:'orderStatus',hidden:'true'}

        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $('#orderList').datagrid('insertRow', {
                    index:0,	// index start with 0
                    row: {
                    }
                });
                $('#orderList').datagrid('selectRow',0);
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
                issuedOrders();
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

            var row = $('#orderList').datagrid('getSelected');
            var dataGrid=$('#orderList');
            var row = $('#orderList').datagrid('getSelected');
            var status = row.orderStatus;
                if(!dataGrid.datagrid('validateRow', rowNum)){
                    return false//新开
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
            $("#ordersDialog").dialog('open');
           /* var row = $('#orderList').datagrid('getSelected');
            var dataGrid=$('#orderList');
            var row = $('#orderList').datagrid('getSelected');
            var status = row.orderStatus;

            if(status ==null|| status=='1'){
                if(!dataGrid.datagrid('validateRow', rowNum)){
                    return false//新开
                }else{
                    if(rowNum!=rowIndex){
                        if(rowNum>=0){
                            dataGrid.datagrid('endEdit', rowNum);
                        }
                        rowNum=rowIndex;
                        dataGrid.datagrid('beginEdit', rowIndex);
                    }
                }
            }else if(status=='2'){//下达医嘱
                $.messager.alert('提示',"医嘱已经下达不能修改", "error");
            }else if(status=='3'){//停止医嘱
                $.messager.alert('提示',"医嘱已经停止不能修改", "error");
            }else if(status=='4'){//作废
                $.messager.alert('提示',"医嘱已经作废不能修改", "error");
            }*/
        }, rowStyler:function(index,row){
            if (row.orderStatus=='1'){
                return 'color:black;';
            }else if(row.orderStatus=="2"){
                return 'color:blue;';
            }else if(row.orderStatus=="3"){
                return 'color:yellow;';
            }else if(row.orderStatus=="4"){
                return 'color:red;';
            }
        }
    });
    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
            $('#orderList').datagrid("load");   //点击搜索
        });


    $("#ordersDialog").dialog({
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
            $("#ordersDatagrid").datagrid({
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
//保存
function save(){
    $("#orderList").datagrid('endEdit', rowNum);
    var  rows=$('#orderList').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    var submitJson=tableJson+",\"patientId\":"+patId+",\"visitId\":"+visitId+"}";

    $.postJSON(basePath+'/inOrders/save',tableJson,function(data){
        if(data.data=='success'){
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
    var row = $('#orderList').datagrid('getSelected');
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    if(row.orderStatus=='1'){//新开医嘱
        //提醒用户是否是真的删除数据
        $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
            if (r) {
                var strIds = "";
                for (var i = 0; i < selectRows.length; i++) {
                    strIds += selectRows[i].id + ",";
                }
                strIds = strIds.substr(0, strIds.length - 1);
                if(strIds=='undefined'|| strIds==''){
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
                                $('#orderList').datagrid('load');
                                $('#orderList').datagrid('clearChecked');
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
    }else if(row.orderStatus=='2'){//正在执行
        $.messager.alert('提示', "医嘱正在执行，不能删除", "error");
    }else if(row.orderStatus=='3'){//停止
        $.messager.alert('提示', "医嘱已经停止，不能删除", "error");
    }else if(row.orderStatus=='4') {//停止

    }
}

//把医嘱改变成子医嘱
function changeSubNo(row){
   // var row = $('#orderList').datagrid('getSelected');
       // alert(rowNum);
        //1.获取选中行的上条医嘱
        var rows = $('#orderList').datagrid('getRows');    // 获取所有行

        var prerow;//rows[rowIndex]//根据行索引获取行数据
        var nowrow;
        if(rowNum!=0){
            prerow = rows[rowNum-1];
            nowrow = rows[rowNum];
            //2.判断2条医嘱是否同是长期或者临时,进行3判断
            if(row[0].repeatIndicator==prerow.repeatIndicator){
                //3.判断2条医嘱是否同时药品，（药疗才可组成组合医嘱），进行4判断
                if(row[0].orderClass==1&&prerow.orderClass==1){
                    //4.判断上一条医嘱状态，如果状态允许，进行5判断
                    if(prerow.orderStatus==6 || prerow.orderStatus=="6"){
                        //5.判断选中行row的医嘱状态，如果状态是新开，则进行6操作
                        if(row[0].orderStatus==6||row[0].orderStatus=='6'){
                            //6.获取上一行的orderSubNo，则给选中行的子医嘱号累加1并赋值，并把医嘱号更改为与上行医嘱号相同
                            row[0].orderSubNo = parseInt(nowrow.orderSubNo)+1;
                            row[0].orderNo = nowrow.orderNo;
                            //7.选中行为子医嘱时，获取上行医嘱的如下字段给选中行医嘱赋值：长期字段，类别字段，途径，频次，执行时间，医生说明字段，并设置不可填写
                            row[0].repeatIndicator=nowrow.repeatIndicator;
                            row[0].orderClass=nowrow.orderClass;
                            row[0].administration=nowrow.administration;
                            row[0].frequency=nowrow.frequency;
                            row[0].performSchedule = nowrow.performSchedule;
                            row[0].freqDetail= nowrow.freqDetail;
                        //8.增加行把上一行的数据赋给子医嘱
                    //    addOrders();
                          //  alert("row[0].orderNo="+row[0].orderNo+"row[0].orderSubNo="+row[0].orderSubNo);
                            alert(row[0].orderNo);
                        $('#orderList').datagrid('insertRow', {
                            index:0,	// index start with 0
                            row: {
                               orderNo:   row[0].orderNo,
                               orderSubNo:  row[0].orderSubNo,
                                repeatIndicator:  row[0].repeatIndicator,
                                orderClass:row[0].orderClass,
                                administration:row[0].administration,
                                frequency:row[0].frequency,
                                performSchedule:row[0].performSchedule,
                                freqDetail:row[0].freqDetail

                            }
                        });
                        $('#orderList').datagrid('selectRow',0);

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



//新增子医嘱时，单击的时候使某些字段变得不可编辑
/*function onClickRowSub(index){
    if (editRow != index){
            $('#orderList').datagrid('selectRow', index)
                .datagrid('beginEdit', index);
           // 得到单元格对象,index指哪一行,field跟定义列的那个一样
            var cellEdit = $('#orderList').datagrid('getEditor', {index:index,field:'repeatIndicator'});
            var $input = cellEdit.target; // 得到文本框对象
            //$input.val('aaa'); // 设值
            $input.prop('readonly',true); // 设值只读
        editRow = index;
        } else {
            $('#orderList').datagrid('selectRow', editRow);
        }

}*/


//传输医嘱（下达医嘱）
function issuedOrders(){
    var row = $('#orderList').datagrid('getSelected');
   // alert("row.id="+row.id);
    $.ajax({
        method:"POST",
        contentType:"application/json",
        dataType:"json",
        data:id=row.id,
        url:basePath+"/inOrders/issuedOrders",
        success:function(data){
             if(data!=null){
                 $.messager.alert('提示',"医嘱已经下达！");
                 $('#orderList').datagrid('load');

             }else{
                 $.messager.alert('提示',"医嘱下达失败！", "error");
             }
        }
    })
}


