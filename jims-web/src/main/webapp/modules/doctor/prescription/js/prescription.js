var rowNum=-1;
var orderNo=0;
var visitDate;
var visitNo;
var prescNo;
var itemClass;
var clinicId;
var orgId;
var chargeIndicator='新开';
//页面加载
$(function(){
    itemClass = $("#itemClass").val();
    clinicId = parent.clinicMaster.id;
    orgId = parent.config.org_Id;
    $("#clinicId").val(clinicId);
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list?clinicId='+clinicId,
        columns:[[      //每个列具体内容
            {field:'orderNo',title:'医嘱号',hidden:true,
                formatter: function (value, row, index) {
                if(orderNo < value){
                    orderNo = value;
                }
                return value;
        }},
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
                    }else if(value== "1"){
                        value = "已收费";
                    }else if(value=='2'){
                        value = "已退费";
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
        width: 'auto',
        height: 'auto',
        fit: true,
        fitColumns: true,
        singleSelect:true,//是否单选
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:'true'},
            {field:'markSubOrderNo',title:'全',width:'3%',align:'center',formatter:function(value, rowData, rowIndex){
                if(rowData.subOrderNo==rowData.orderNo){
                    return "";
                }else{
                    return "子";
                }
            }},
            {field:'prescNo',title:'处方号',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    required: true,
                    data:westernDrugData,
                    idField:'item_name',
                    textField:'item_name',
                    columns:[
                        [
                            {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
                            {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                            {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                            {field: 'quanity', title: '库存', width: '15%', align: 'center'},
                            {field: 'units', title: '包装单位', width: '15%', align: 'center'},
                            {field: 'item_class', title: '库房', width: '15%', align: 'center',
                                formatter : function(value,row,index){
                                if(value=='A'){
                                    return '西药房'
                                } else if(value=='B'){
                                    return '中草药房'
                                }
                            }},
                            {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
                            {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center'},
                            {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'},
                            {field: 'subj_code', title: '',hidden:true},
                            {field: 'performed_by', title: '',hidden:true},
                            {field: 'price', title: '',hidden:true}
                        ]],keyHandler: {
                        query: function(q) {
                            var ed = $('#list_data').datagrid('getEditor', {index:rowNum,field:'drugName'});
                            comboGridCompleting(q,'drugName');
                            $(ed.target).combogrid("grid").datagrid("loadData", comboGridComplete);
                        }
                    },onClickRow: function (index, row) {
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
                        var itemClass = $("#list_data").datagrid('getEditor',{index:rowNum,field:'itemClass'});
                        $(itemClass.target).textbox('setValue',row.itemClass);
                        var units = $("#list_data").datagrid('getEditor',{index:rowNum,field:'units'});
                        $(units.target).textbox('setValue',row.units);
                        var subjCode = $("#list_data").datagrid('getEditor',{index:rowNum,field:'subjCode'});
                        $(subjCode.target).textbox('setValue',row.subj_code);
                        var performedBy = $("#list_data").datagrid('getEditor',{index:rowNum,field:'performedBy'});
                        $(performedBy.target).textbox('setValue',row.performed_by);

                        var charges = $("#list_data").datagrid('getEditor',{index:rowNum,field:'charges'});
                        $(charges.target).textbox('setValue',row.price);
                        $("#prescDialog").dialog('open');
                        var index =  $("#prescriptionDatagrid").datagrid('appendRow', {
                                itemClass: row.item_class,
                                itemName: row.item_name,
                                itemSpec: row.drug_spec,
                                amount:row.amount,
                                units:row.units,
                                charges:row.price
                            }).datagrid('getRows').length-1;
                    }
                }
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'firmId',title:'厂家',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'repetition',title:'剂数',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:{type:'numberbox',options:{required:true}}},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'administration',title:'途径',width:'5%',align:'center',formatter:administrationFormatter,editor:{
                type:'combobox',
                options:{
                    data :administrationmzDict,
                    valueField:'id',
                    textField:'administrationName',
                    required:true,
                    onSelect:function(row){;
                        var index =  $("#prescriptionDatagrid").datagrid('appendRow', {
                                itemClass: row.item_class,
                                itemName: row.administration_name,
                                itemSpec: row.item_spec,
                                amount:row.amount,
                                units:row.units,
                                charges:row.price
                            }).datagrid('getRows').length-1;
                    }
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
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:{type:'numberbox',options: {required: true}}},
            {field:'units',title:'单位',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center',editor:{type:'numberbox',options:{editable:false,disable:false}}},
            {field:'itemClass',title:'药局',width:'5%',align:'center',editor:{type:'textbox',options:{editable:false,disable:false}},
                formatter : function(value,row,index){
                    if(value=='A'){return '西药房'}
                    else if(value=='B'){return '中草药房'}
                }
            },
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'skinFlag',title:'皮试',width:'5%',align:'center',formatter:skinFlagFormatter,editor:{
                type:'combobox',
                options:{
                    data :skinFlag,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'skinResult',title:'皮试结果',width:'5%',align:'center',formatter:skinResultFormatter,editor:{
                type:'r',
                options:{
                    data :skinResult,
                    valueField:'value',
                    textField:'label'
                }
            }},
            {field:'orderNo',title:'处方',hidden:true},
            {field:'subOrderNo',title:'子处方',hidden:true},
            {field:'serialNo',title:'流水号',hidden:'true'},
            {field:'subjCode',title:'会计科目',hidden:'true',editor:{type:'textbox',options:{editable:false}}},
            {field:'performedBy',title:'执行科室',hidden:'true',editor:{type:'textbox',options:{editable:false}}},
            {field:'drugCode',title:'药品编号',hidden:'true',editor:{type:'textbox',options:{editable:false}}}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                var dataGrid=$('#list_data');
                if(!dataGrid.datagrid('validateRow', rowNum)){
                    $.messager.alert('提示',"请填写完本行数据后，再添加下一条处方", "error");
                    return false
                }
                $("#list_data").datagrid('endEdit', rowNum);
                if(rowNum>=0){
                    rowNum++;
                }
                var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    orderNo++;
                    var idx = $("#list_data").datagrid('appendRow', {
                            prescNo:selRow[0].prescNo,
                            orderNo:orderNo,
                            subOrderNo:orderNo
                        }).datagrid('getRows').length-1;
                    rowNum=idx;
                    $('#list_data').datagrid('beginEdit', idx);
                }else{
                    $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                    return;
                }
            }
        },{
            text: '子处方',
            iconCls: 'icon-edit',
            handler: function() {
                //var dataGrid=$('#list_data');
                //if(!dataGrid.datagrid('validateRow', rowNum)){
                //    $.messager.alert('提示',"请填写本行数据后，在添加下一句", "error");
                //    return false
                //}
                var selRow = $('#list_data').datagrid('getChecked');
                if(selRow!=null&&selRow!=''&&selRow!='undefined') {
                    changeSubPresc(selRow);
                }else{
                    $.messager.alert('提示',"请选择要操作的处方！", "error");
                }
            }
        }/*, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }*/],onClickRow:function(rowIndex,rowData){
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                $.messager.alert('提示',"数据填写不完整，请填写完整后再对其他行进行编辑", "error");
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
            //alert(rowData);
            $("#prescDialog").dialog('open');
            $.get(basePath+'/outppresc/priceItem?masterId=' + rowData.id+"&clinicId="+clinicId, function (data) {
                $("#prescriptionDatagrid").datagrid("loadData", data);
            });
        }
    });
    $("#prescDialog").dialog({
        title: '计价项目',
        //style="width:500px;height:300px;
        left:500,
        top:200,
        width: 500,
        height: 500,
        catch: false,
        modal: false,
        closed: true,
        onOpen: function () {
            $("#prescriptionDatagrid").datagrid({
                singleSelect: true,
                fit: true,
                fitColumns: true,
                /*  url: basePath+'/outppresc/jijia',
                 method: 'GET',*/
                columns: [[{
                    title: '类别',
                    field: 'itemClass',
                    width:'15%'
                }, {
                    title: '计价项目',
                    field: 'itemName',
                    width:'20%'
                }, {
                    title: '规格',
                    field: 'itemSpec',
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
                    field: 'charges',
                    width:'15%'
                }]],
                onLoadSuccess:function(data){

                }
            });
        }
    });

    //处方属性下拉框
    $('#prescAttr').combobox({
        data: prescAttrDict,
        valueField: 'label',
        textField: 'label',
        required:true
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
            $("#medicineId").hide();
            $(".layout-split-south .datagrid").show();
            $.get(basePath+'/outppresc/sublist?prescNo=' + row.prescNo+"&clinicId="+clinicId, function (data) {
                $("#list_data").datagrid("loadData", data);
            });
        }else{
            changeRadio('B');

            $("#medicineId").show();
            $(".layout-split-south .datagrid").hide();
            herbalList();
        }
    }

}
//中药列表
function herbalList(){
    var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
    if(selRow!=null&&selRow!=''&&selRow!='undefined') {
        prescNo = selRow[0].prescNo;

        var liHtml = "";
        $("#herbal_ul").html("");
        $.postJSON(basePath+'/outppresc/subherballist', "{\"clinicId\":\""+clinicId+"\",\"prescNo\":\""+selRow[0].prescNo+"\"}", function (data) {
            rowNumZ = data.length;
            for (var i = 0; i < data.length; i++) {
                liHtml+='<li style="position:relative;" onclick="centerActive(this,\'herbalHide'+i+'\')" id="herbal'+i+'"  inputhide="herbalHide'+i+'">' +
                '<div><input  type="text" id="freqDetail'+i+'" class="easyui-textbox" style="width: 200px;" value="'+data[i].freqDetail+'"/></div>' +
                '<input  type="text" id="drugName'+i+'" class="easyui-textbox" onclick="openOombogrid(this,\''+i+'\')" style="width: 150px" value="'+data[i].drugName+'"/>' +
                '<input type="text" value="'+data[i].amount+'" style="width: 50px" class="easyui-textbox" id="amount'+i+'" namehide="amount" inputhide="herbalHide'+i+'" />' +
                '<span id="span'+i+'" class="color-blue" style="padding-left:10px;">'+data[i].units+'</span>' +
                '<a class="ul_li_a"   onclick="delherbal(\''+data[i].id+'\')">X</a>' +
                '<input type="hidden" id="drugCode'+i+'" namehide="drugCode" inputhide="herbalHide'+i+'" value="'+data[i].drugCode+'"/> ' +
                '<input type="hidden" id="drugSpec'+i+'" namehide="drugSpec" inputhide="herbalHide'+i+'" value="'+data[i].drugSpec+'"/> ' +
                '<input type="hidden" id="firmId'+i+'" namehide="firmId" inputhide="herbalHide'+i+'" value="'+data[i].firmId+'"/> ' +
                '<input type="hidden" id="dosage'+i+'" namehide="dosage" inputhide="herbalHide'+i+'" value="'+data[i].dosage+'"/> ' +
                '<input type="hidden" id="dosageUnits'+i+'" namehide="dosageUnits" inputhide="herbalHide'+i+'" value="'+data[i].dosageUnits+'"/> ' +
                '<input type="hidden" id="itemClass'+i+'" namehide="itemClass" inputhide="herbalHide'+i+'" value="'+data[i].itemClass+'"/> ' +
                '<input type="hidden" id="units'+i+'" namehide="units" inputhide="herbalHide'+i+'" value="'+data[i].units+'"/> ' +
                '<input type="hidden" id="subjCode'+i+'" namehide="subjCode" inputhide="herbalHide'+i+'" value="'+data[i].subjCode+'"/> ' +
                '<input type="hidden" id="performedBy'+i+'" namehide="performedBy" inputhide="herbalHide'+i+'" value="'+data[i].performedBy+'"/> ' +
                '<input type="hidden" id="orderNo'+i+'" namehide="orderNo" inputhide="herbalHide'+i+'" value="'+data[i].orderNo+'"/> ' +
                '<input type="hidden" id="subOrderNo'+i+'" namehide="subOrderNo" inputhide="herbalHide'+i+'" value="'+data[i].subOrderNo+'"/> ' +
                '<input type="hidden" id="prescNo'+i+'" namehide="prescNo" inputhide="herbalHide'+i+'" value="'+data[i].prescNo+'"/> ' +
                '<input type="hidden" id="id'+i+'" namehide="id" inputhide="herbalHide'+i+'" value="'+data[i].id+'"/> ' +
                '<input type="hidden" id="charges'+i+'" namehide="charges" inputhide="herbalHide'+i+'" value="'+data[i].charges+'"/> '+
                '<input type="hidden" id="serialNo'+i+'" namehide="serialNo" inputhide="herbalHide'+i+'"  value="'+data[i].serialNo+'"/> ';
                $("#herbal_ul").append(liHtml);
                /*$('#freqDetail'+i).textbox("setValue",'');
                 $('#amount'+i).numberbox("setValue",'');*/
                liHtml='';
            }

        }, function () {
            $.messager.alert("提示信息", "网络连接失败");
        });
    }
}
//西药/草药单选按钮事件
function funItem(obj){
    itemClass=obj.value;
    if(itemClass=='B'){
        $("#medicineId").show();
        $(".layout-split-south .datagrid").hide();
    }else{
        $("#medicineId").hide();
        $(".layout-split-south .datagrid").show();
    }

    $("#itemClass").val(obj.value);
    changeRadio(obj.value);
    var selRow = $('#leftList').datagrid('getChecked');
    if(selRow[0]!=undefined){
        subItem(itemClass,selRow[0]);
    }

}
//西药/草药单选按钮事件-更新行
function subItem(itemClass,selRow){
    var idx = $('#leftList').datagrid('getRowIndex',selRow);
    if(itemClass=='A'){
        $('#leftList').datagrid('updateRow',{
            index:idx,
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
            index:idx,
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
    itemClass = $("#itemClass").val();
    if(itemClass=='A'){
        $("#list_data").datagrid('loadData', { total: 0, rows: [] });
        newpresc();
        $("#list_data").datagrid();
    }else{
        newpresc();
        $("#medicineId").show();
        $(".layout-split-south .datagrid").hide();
    }

}
//新方
function newpresc(){
    disableForm('prescForm',false);
    //获取处方列表所有行，并取出所有行中处方号prescNo的最大值，加1后作为新处方的处方号
    var rows = $('#leftList').datagrid('getRows');
    if(rows.length>0){
        for(var i=0;i<rows.length;i++){
            if(rows[i].chargeIndicator=='新开'){
                $.messager.alert("提示消息", "已有新开处方，请先保存或者弃方后再试!");
                return;
            }else{

            }
            /*for(var j=0;j<rows.length;j++){
                if(rows[i].prescNo>rows[j].prescNo){
                    prescNo= rows[i].prescNo+1;
                    break;
                }else{
                    prescNo = rows[j].prescNo+1;
                    break;
                }
            }*/
        }
    }/*else{
        prescNo=1;
    }*/
    /*    var index= $('#list_data').datagrid('getRowIndex',nowrow);*/
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
            var idx = $('#leftList').datagrid('appendRow', {
                    visitDate: visitDate,
                    visitNo: visitNo,
                    prescNo: prescNo,
                    itemClass:itemClass,
                    chargeIndicator:chargeIndicator

                }).datagrid('getRows').length-1;
            $('#leftList').datagrid('selectRow',idx);
        }
    })
}
//保存处方及药品信息
function savePre(){
    if(itemClass=='B'){
        var administration = $('#administration').combobox('getValue');
        var frequency = $('#frequency').combobox('getValue');
        var repetition = $("#repetition").val();
        var formJson=fromJson('prescForm');
        formJson = formJson.substring(0, formJson.length - 1);
        var drugJson="\"list\":[";

        $("#herbal_ul li").each(function(){
            var liHidden=$(this).attr("inputhide");
            drugJson+="{";
            $("input[inputhide='"+liHidden+"']").each(function(){
                drugJson+='"'+$(this).attr("namehide")+'":"'+$(this).val()+'",';
            });

            drugJson = drugJson.substring(0, drugJson.length - 1);
            drugJson+=",\"administration\":\""+administration+"\",\"frequency\":\""+frequency+"\",\"repetition\":\""+repetition+"\"";
            drugJson+="},";
        });
        drugJson = drugJson.substring(0, drugJson.length - 1);
        drugJson+="]";

        var submitJsons=formJson+","+drugJson+"}";
       /* alert(submitJsons)*/
        $.postJSON(basePath+'/outppresc/save',submitJsons,function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息",data.code+"条处方，保存成功");
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
    }else{
        var dataGrid=$('#list_data');
        if(!dataGrid.datagrid('validateRow', rowNum)){
            $.messager.alert('提示',"请填写完本行数据后，再保存", "error");
            return false
        }
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

}
//弃方即刷新页面
function giveUpPre(){
    $('#leftList').datagrid('load');
    $('#leftList').datagrid('clearChecked');
    $("#list_data").datagrid('loadData', { total: 0, rows: [] });
    orderNo=0;
}
//删除药品信息
function doDelete() {
    var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
    if(selRow!=null&&selRow!=''&&selRow!='undefined') {
        if(selRow[0].chargeIndicator==0){
            //提醒用户是否是真的删除数据
            $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
                if (r) {
                    del(selRow[0].prescNo);
                }
            });
        }else{
            $.messager.alert('提示', "该处方已收费，不能进行删除操作", "warning");
            return;
        }
    }else{
        $.messager.alert("提示消息", "请选择要删除的处方数据!","warning");
        return;
    }

}
function del(prescNos){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/outppresc/delByPrescNo?prescNo='+prescNos+'&orgId='+orgId+'&clinicId='+clinicId,
        //'data':"prescNo="+prescNos+"&orgId="+orgId+"&clinicId="+clinicId,
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            if(data.code!='0'){
                if(data.data=='success'){
                    $.messager.alert("提示消息","1条处方删除成功！");
                    $('#leftList').datagrid('load');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }

        }, 'error': function(data){
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


//把选中处方修改成子处方
function changeSubPresc(row){
    $('#list_data').datagrid('endEdit',rowNum);
    var rows = $('#list_data').datagrid('getRows');    // 获取所有行
    var prerow;//rows[rowIndex]//根据行索引获取行数据
    var afterrow;
    var nowrow = row[0];
    var index= $('#list_data').datagrid('getRowIndex',nowrow);
    if(index>0) {
        var dataGrid=$('#list_data');
        if(!dataGrid.datagrid('validateRow', index)){
            $.messager.alert('提示',"数据填写不完整，请填写完整后再添加子处方", "error");
            return false
        }
        $('#list_data').datagrid('endEdit', index);
        $('#list_data').datagrid('beginEdit', index);
        //获取下一行
        afterrow=rows[index+1];
        //判断本身是否是子处方
        if(afterrow!=undefined){
            //判断是否是子医嘱
            if(nowrow.orderNo!=nowrow.subOrderNo){
                //判断是否有子医嘱
                if(afterrow.subOrderNo == nowrow.subOrderNo){
                    return false;
                }else{
                    //删除子医嘱
                    nowrow.subOrderNo = nowrow.orderNo;
                    rowNum=index;
                    $('#list_data').datagrid('endEdit', index);
                    $('#list_data').datagrid('beginEdit', index);
                    return false;
                }
            }
        }else{
            if(nowrow.orderNo!=nowrow.subOrderNo){
                nowrow.subOrderNo = nowrow.orderNo;
                rowNum=index;
                $('#list_data').datagrid('endEdit', index);
                $('#list_data').datagrid('beginEdit', index);
                return false;
            }
        }
        if(afterrow!=undefined){
            if(afterrow.subOrderNo == nowrow.orderNo){
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
        nowrow.subOrderNo = prerow.subOrderNo;
        $('#list_data').datagrid('endEdit', index);
        $('#list_data').datagrid('beginEdit', index);
    }else{
        $.messager.alert('提示',"第一条处方不能设置子医嘱", "warning");
    }
}

//删除中药
function delherbal(id){
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        $.ajax({
            'type': 'POST',
            'url': basePath + '/outppresc/delete',
            'contentType': 'application/json',
            'data': ids = id,
            'dataType': 'json',
            'success': function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", data.code + "条记录删除成功！");
                    $('#leftList').datagrid('load');
                } else {
                    $.messager.alert('提示', "删除失败", "error");
                }
            },
            'error': function (data) {
                $.messager.alert('提示', "删除失败", "error");
            }
        });
    });
}




