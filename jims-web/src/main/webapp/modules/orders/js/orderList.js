var editRow = undefined;
var rowNum=-1;
var patId ='15006135';
var visitId = '1';
var orderNo =0 ;
var orderSubNo = 1;
var Oclass =[{ "value": "1", "label": "药品" }, { "value": "2", "label": "非药品" }];
var clinicPrice =  [];

$(function(){
    $('#orderList').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'GET',
        url:basePath+'/inOrders/getOrders?'+$('#searchform').serialize(),
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'repeatIndicator',title:'长',width:'5%',align:'center',formatter:itemFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :ordersType,
                    valueField:'value',
                    textField:'label',
                    onSelect:function(rowIndex){
                        var performSchedule = $("#orderList").datagrid('getEditor',{index:rowNum,field:'performSchedule'});
                        var repeatIndicatorValue = $("#orderList").datagrid('getEditor',{index:rowNum,field:'repeatIndicator'});
                        var repeatIndicator = $(repeatIndicatorValue.target).combobox('getValue');
                        if(repeatIndicator=='0'){//临时医嘱
                            var d = new Date();
                            $('#frequency').combobox('disable');
                            alert(d.getHours()+":"+ d.getMinutes());
                            $(performSchedule.target).textbox('setValue',d.getHours()+":"+ d.getMinutes());

                        }
                    }}}},
            {field:'orderClass',title:'类别',width:'5%',align:'center',formatter:orderClassFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :Oclass,
                    valueField:'value',
                    textField:'label',
                    onSelect:function(value){

                        var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                        var value = $(orderClass.target).combobox('getValue');
                        var orderClass=value;
                /*        var currentDate = new Date();
                        var year = (currentDate.getFullYear() + "").substr(4 - RegExp.$1.length);
                        var day = year+"-"+currentDate.getMonth()+1+"-"+currentDate.getDate();
                        var startDateTime = $("#orderList").datagrid('getEditor',{index:rowNum,field:'startDateTime'});
                        $(startDateTime.target).datebox('setValue',day);*/
                        var ed = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderText'});
                        $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                        /*如果类别是药品医嘱内容是药品的内容，如果是非药品显示非药品的医嘱内容*/
                        if(value=='1'){//药品
                            $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                            $(ed.target).combogrid("grid").datagrid("loadData", drugData);
                        }else if(value=='2'){//非药品
                            $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
                            $(ed.target).combogrid("grid").datagrid("loadData", clinicOrderData);
                        }

                    }
                }
            }},
            //当前时间
            {field:'startDateTime',title:'下达时间',width:'10%',align:'center',formatter:formatDateBoxFull, editor:{type: 'datebox',options:{editable:true,disable:false}}},
            {field:'orderText',title:'医嘱内容',width:'10%',align:'center',editor:{
                type:'combogrid',
                options:{
                    panelWidth: 450,
                    idField:'item_name',
                    textField:'item_name',
                    columns:[
                        [
                            {field: 'drug_code', title: '药品代码', width: '10%', align: 'center'},
                            {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                            {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                            {field: 'supplier', title: '厂家', width: '20%', align: 'center'},
                            {field: 'dose_per_unit', title: '单次用量', width: '5%', align: 'center'},
                            {field: 'units', title: '用量单位', width: '5%', align: 'center'},
                            {field: 'item_code', title: '项目代码', width: '10%', align: 'center'},
                            {field: 'input_code', title: '拼音', width: '5%', align: 'center'},
                                {field: 'expand1', title: '扩展1', width: '5%', align: 'center'},
                             {field: 'expand2', title: '扩展2', width: '5%', align: 'center'},
                             {field: 'expand5', title: '扩展5', width: '5%', align: 'center'},
                            {field: 'price', hidden:'true'},
                            {field: 'item_class', hidden:'true'}
                        ]
                    ] ,keyHandler: {
                        up: function() {},
                        down: function() {},
                        enter: function() {},
                        query: function(q) {
                            var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                            var ed = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderText'});
                            var value = $(orderClass.target).combobox('getValue');
                            if(value=='1'){//药品
                                comboGridCompleting(q,'orderText');
                                $(ed.target).combogrid("grid").datagrid("loadData", comboGridComplete);
                            }else if(value=='2'){//非药品
                                clinicCompleting(q,'orderText');
                                $(ed.target).combogrid("grid").datagrid("loadData", clinicOrderData);
                            }

                        }
                    },onClickRow: function (index, row) {
                        var dosage = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosage'});
                        $(dosage.target).textbox('setValue',row.dose_per_unit);
                        var dosageUnits = $("#orderList").datagrid('getEditor',{index:rowNum,field:'dosageUnits'});
                        $(dosageUnits.target).textbox('setValue',row.dose_units);
                        var orderClass = $('#orderList').datagrid('getEditor', {index:rowNum,field:'orderClass'});
                        var value = $(orderClass.target).combobox('getValue');
                        if(value=='1'){//药品
                            var orderCode = $("#orderList").datagrid('getEditor',{index:rowNum,field:'orderCode'});
                            $(orderCode.target).textbox('setValue',row.drug_code);
                            $('#orderCostList').datagrid('insertRow', {
                                index:0,	// index start with 0
                                row: {
                                    itemClass:  row.item_class,
                                    itemName:row.item_name,
                                    itemSpec:row.drug_spec,
                                    amount:row.dose_per_unit,
                                    units:row.dose_units,
                                    costs:row.price,
                                    itemCode:row.drug_code,
                                    itemNo:1
                                }
                            });
                        }else if(value=='2'){//非药品
                            var orderCode = $("#orderList").datagrid('getEditor',{index:rowNum,field:'orderCode'});
                            $(orderCode.target).textbox('setValue',row.item_code);
                            $.ajax({
                                'type': 'GET',
                                'url':basePath+'/inOrders/getClinicPrice',
                                data: 'itemCode='+row.item_code,
                                'contentType': 'application/json',
                                'dataType': 'json',
                                'async': false,
                                'success': function(data){
                                    clinicPrice=data;
                                    for(var i=0;i<clinicPrice.length;i++){
                                        $('#orderCostList').datagrid('insertRow', {
                                            index:0,	// index start with 0
                                            row: {
                                                itemClass:  clinicPrice[i].item_class,
                                                itemName:clinicPrice[i].item_name,
                                                itemSpec:clinicPrice[i].item_spec,
                                                amount:1,
                                                units:clinicPrice[i].units,
                                                costs:clinicPrice[i].price,
                                                itemCode:clinicPrice[i].item_code,
                                                itemNo:clinicPrice[i].charge_item_no
                                            }
                                        });
                                    }

                                }
                            })

                        }

                        $('#orderCostList').datagrid('selectRow',0);
                    }
                }
            }},
            {field:'billingAttr',title:'自',width:'5%',align:'center',formatter:billingAttrFormatter,editor:{
                type:'combobox',
                options:{
                    required:true,
                    data :billingAttr,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'dosage',title:'剂量',width:'5%',align:'center',editor:{type:'textbox',options:{editable:true,disable:false}}},
            {field:'dosageUnits',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'5%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationDict,
                    valueField:'id',
                    textField:'administrationName'
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',formatter:performFreqFormatter,editor:{
                type:'combobox',
                options:{
                    /*如果是临时医嘱频次不可填*/
                    data :performFreqDict,
                    valueField:'id',
                    textField:'freqDesc',
                    onSelect: function () {
                        var row = $('#orderList').datagrid('getSelected');
                        var performSchedule = $("#orderList").datagrid('getEditor',{index:rowNum,field:'performSchedule'});
                        var frequency = $("#orderList").datagrid('getEditor',{index:rowNum,field:'frequency'});
                        var frequencyValue = $(frequency.target).combobox('getValue');
                       // var freqCounter = $("#orderList").datagrid('getEditor',{index:rowNum,field:'freqCounter'});
                        //var freqIntervalUnit = $("#orderList").datagrid('getEditor',{index:rowNum,field:'freqIntervalUnit'});

                        var repeatIndicatorValue = $("#orderList").datagrid('getEditor',{index:rowNum,field:'repeatIndicator'});
                        var repeatIndicator = $(repeatIndicatorValue.target).combobox('getValue');
                        var administration = $("#orderList").datagrid('getEditor',{index:rowNum,field:'administration'});
                        var administrationValue = $(administration.target).combobox('getValue');
                        if( repeatIndicator==null){
                            $('#frequency').combobox('disable');
                            $.messager.alert('提示', "医嘱类型不能为空！", "error");
                        }else{
                            if(repeatIndicator=='1'){//长期医嘱
                                $('#frequency').combobox('enable');
                                var perSchedule = [];
                                $.ajax({
                                    'type': 'GET',
                                    'url':basePath+'/performDefaultSchedule/findList',
                                    'contentType': 'application/json',
                                    'data':'freqDesc='+frequencyValue+'&administration='+administrationValue,
                                    'dataType': 'json',
                                    'async': false,
                                    'success': function(data){
                                        perSchedule=data;
                                        var defaultSchedule ;
                                        for (var i = 0; i < perSchedule.length; i++) {
                                            if (perSchedule[i].freqDesc == frequencyValue && perSchedule[i].administration == administrationValue) {
                                                defaultSchedule = perSchedule[i].defaultSchedule;
                                            }
                                        }
                                      /*  $(freqCounter.target).textbox('setValue',counter);*/
                                        $(performSchedule.target).textbox('setValue',defaultSchedule);
                                    }
                                });

                            }

                        }

                    }}}},
            {field:'performSchedule',title:'执行时间',width:'5%',align:'center',formatter:formatDateBoxFull,editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'performResult',title:'阴阳',width:'5%',align:'center'},
            {field:'stopDateTime',title:'结束时间',width:'10%',align:'center',editor:{type: 'datebox'}},
            {field:'freqDetail',title:'医生说明',width:'10%',align:'center',editor:'text'},
            {field:'doctor',title:'医生',width:'10%',align:'center',editor:'text',
                formatter:function(value, row, index){
                    return "李俊山";
                }},
            {field:'freqCounter',title:'次数',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'stopDoctor',title:'停止医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止校対护士',width:'5%',align:'center'},
            {field:'orderNo',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}},
                formatter:function(value){
                    if(orderNo<value){
                        orderNo = value;
                    }
                 return value;
                }},
            {field:'orderSubNo',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderStatus',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderCode',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}}
            /*{field:'freqIntervalUnit',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'freqInterval',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},*/
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {

                $("#orderList").datagrid('endEdit', editRow);

                if(rowNum>=0){
                    rowNum++;
                }

                orderNo++;
                var idx = $("#orderList").datagrid('appendRow', {
                        orderNo:orderNo,
                        orderSubNo:orderNo
                    }).datagrid('getRows').length-1;
                rowNum=idx;
                $('#orderList').datagrid('beginEdit', idx);
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
            // var status = row.orderStatus;
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false//新开
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowNum);
                }
            }

            $.ajax({
                'type': 'GET',
                'url':basePath+'/inOrders/getCostById',
                'contentType': 'application/json',
                'data':'ordersId='+row.id,
                'dataType': 'json',
                'async': false,
                'success': function(data){
                    $('#orderCostList').datagrid('loadData', data);
                }
            });

        }, rowStyler:function(index,row){



            if(row.orderStatus=='6'){//传输
                return 'background-color:#A7CACB;color:#F49C00;';
            }else if(row.orderStatus=='1'){//转抄
                return 'background-color:#A7CACB;color:black;';
            }else if(row.orderStatus=='2') {//执行
                return 'background-color:#A7CACB;color:blue;';
            }else if(row.orderStatus=='3') {//停止
                return 'background-color:#A7CACB;color:yellow;';
            }else if(row.orderStatus=='4') {//作废
                return 'background-color:#A7CACB;color:red;';
            }


        },onLoadSuccess:function(){
           /* var  rows=$('#orderList').datagrid('getRows');
            for(var i=0;i<rows.length;i++){
                if(rows[i].orderNo!=rows[i].orderSubNo){
                    $("#orderList").datagrid("selectRecord",rows[i-1].id);
                    $("#orderList").datagrid("selectRecord",rows[i].id);
                }
            }*/

        }
    });
    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
        $('#orderList').datagrid("load");   //点击搜索
    });


    $("#orderCostList").datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        columns: [[      //每个列具体内容
            {field: 'itemClass', title: '类别', width: '10%', align: 'center'},
            {field: 'itemName', title: '计价项目', width: '20%', align: 'center'},
            {field: 'itemSpec', title: '规格', width: '5%', align: 'center'},
            {field: 'amount', title: '数量', width: '5%', align: 'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field: 'units', title: '单位', width: '5%', align: 'center'},
            {field: 'costs', title: '当前单价', width: '10%', align: 'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'itemCode',hidden:'true'},
            {field:'itemNo',hidden:'true'}

        ]]
    });





});
//保存
function save(){
    $("#orderList").datagrid('endEdit', rowNum);
    var  rows=$('#orderList').datagrid('getRows');
    var  costRows=$('#orderCostList').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    tableJson=tableJson.substring(0, tableJson.length - 1);
    var costJson = JSON.stringify(costRows);
    var submitJson=tableJson+",\"patientId\":"+patId+",\"visitId\":"+visitId+",\"ordersCostses\":"+costJson+"}]";
    $.postJSON(basePath+'/inOrders/save',submitJson,function(data){
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
    if(row.orderStatus=='5'){//新开医嘱
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
                                $('#orderCostList').datagrid('loadData', { total: 0, rows: [] });
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
    }else if(row.orderStatus=='6'){//传输
        $.messager.alert('提示', "医嘱已经提交，不能删除", "error");
    }else if(row.orderStatus=='1'){//转抄
        $.messager.alert('提示', "医嘱已经转抄，不能删除", "error");
    }else if(row.orderStatus=='2') {//执行
        $.messager.alert('提示', "医嘱已经执行，不能删除", "error");
    }else if(row.orderStatus=='3') {//停止
        $.messager.alert('提示', "医嘱已经停止，不能删除", "error");
    }else if(row.orderStatus=='4') {//作废
        $.messager.alert('提示', "医嘱已经作废，不能删除", "error");
    }
}

//把医嘱改变成子医嘱
function changeSubNo(row){
    $('#orderList').datagrid('endEdit',rowNum);
    var rows = $('#orderList').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    var afterrow;
    var nowrow = row[0];
    var index= $('#orderList').datagrid('getRowIndex',nowrow);

    if(index>0) {
        prerow = rows[index-1];
        //2.判断2条医嘱是否同是长期或者临时,进行3判断
        if(nowrow.repeatIndicator==prerow.repeatIndicator){

            //3.判断2条医嘱是否同时药品，（药疗才可组成组合医嘱），进行4判断
            if(nowrow.orderClass==1&&prerow.orderClass==1){

                //4.判断上一条医嘱状态，如果状态允许，进行5判断
                if(prerow.orderStatus==5 || prerow.orderStatus=="5"|| nowrow.orderStatus==''){

                    //5.判断选中行row的医嘱状态，如果状态是新开，则进行6操作
                    if(nowrow.orderStatus==5||nowrow.orderStatus=='5'|| nowrow.orderStatus==''){


                        var dataGrid=$('#orderList');
                        if(!dataGrid.datagrid('validateRow', index)){
                            $.messager.alert('提示',"数据填写不完整，请填写完后在添加子医嘱", "error");
                            return false
                        }
                        $('#orderList').datagrid('endEdit', index);
                        $('#orderList').datagrid('beginEdit', index);
                        //获取下一行
                        afterrow=rows[index+1];
                        //判断本身是否是子处方
                        if(afterrow!=undefined){
                            //判断是否是子医嘱
                            if(nowrow.orderNo!=nowrow.orderSubNo){
                                //判断是否有子医嘱
                                if(afterrow.orderSubNo == nowrow.orderSubNo){
                                    return false;
                                }else{
                                    //删除子医嘱
                                    nowrow.orderSubNo = nowrow.orderNo;
                                    rowNum=index;
                                    $('#orderList').datagrid('endEdit', index);
                                    $('#orderList').datagrid('beginEdit', index);
                                    return false;
                                }
                            }
                        }else{
                            if(nowrow.orderNo!=nowrow.orderSubNo){
                                nowrow.orderSubNo = nowrow.orderNo;
                                rowNum=index;
                                $('#orderList').datagrid('endEdit', index);
                                $('#orderList').datagrid('beginEdit', index);
                                return false;
                            }
                        }
                        if(afterrow!=undefined){
                            if(afterrow.orderSubNo == nowrow.orderNo){
                                $.messager.alert('提示',"此处方已经有子医嘱，不能设置子医嘱", "error");
                                return false;
                            }
                        }


                        //1.判断该条医嘱是否有子处方，如果有，则不允许把当前处方变成其他处方的子处方
                        prerow = rows[index-1];
                        nowrow.orderSubNo = prerow.orderSubNo;

                        var subNo = $("#orderList").datagrid('getEditor',{index:index,field:'orderSubNo'});
                        $(subNo.target).textbox('setValue',prerow.orderSubNo);
                        $('#orderList').datagrid('endEdit', index);
                        $('#orderList').datagrid('beginEdit', index);



                  save();




                    }else{
                        $.messager.alert('提示',"选中行不是新开医嘱不能构成组合医嘱！", "error");
                    }
                }else{
                    $.messager.alert('提示',"上一行不是新开医嘱不能构成组合医嘱！", "error");
                }
            }else{
                $.messager.alert('提示',"不是药疗医嘱不能构成组合医嘱！", "error");
            }
        }else{
            $.messager.alert('提示',"长期和临时医嘱不能构成组合医嘱！", "error");
        }
    }else{
        $.messager.alert('提示',"第一条处方不能设置子医嘱", "warning");
    }
}






//传输医嘱（下达医嘱）下达时间不能早于昨天22:00
function issuedOrders(){
    var row = $('#orderList').datagrid('getSelected');
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


