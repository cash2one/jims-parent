$(function(){
    $("#dispensary").val(config.deptCode);
    $("#orgId").val(config.org_Id);
    $("#deptName").html("<b>"+config.deptName+"<b>");
    $("#prescDate").datebox("setValue", "");
    $("#prescDate").datebox({
        required: true,
        editable: false
    });
    $("#prescDate").combo("disable");
    $("#ck").on("click",function(){
        if ($("#ck").is(':checked')) {
            $('#prescDate').combo("enable");
            $("#prescDate").datebox("setValue", formatDatebox(new Date()));
        } else {
            $('#prescDate').combo("disable");
            $("#prescDate").datebox("setValue", "");
        }
    })
    //加载发药住院主记录
    $("#prescTable").datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath+'/drugPrescIn/list?'+$("#presc").serialize(),
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'prescNo', title: '处方号', width: '30%', align: 'center'},
            {field: 'name', title: '姓名', width: '20%', align: 'center'},
            {field: 'prescDate', title: '处方日期',width: '50%', align: 'center'},
            //一下是隐藏域 cxy
            {field: 'identity', title: '身份', hidden: true},
            {field: 'chargeType', title: '费别', hidden: true},
            {field: 'costs', title: '计价', hidden: true},
            {field: 'prescribedBy', title: '开方医生', hidden: true},
            {field: 'orderedBy', title: '开单科室', hidden: true},
            {field: 'repetition', title: '剂数', hidden: true},
            {field: 'prescAttr', title: '处方属性', hidden: true},
            {field: 'enteredBy', title: '录入人员', hidden: true},
            {field: 'dispensarySub', title: '子库房', hidden: true},
            {field: 'clinicNo', title: '门诊号', hidden: true},
            {field: 'countPerRepetition', title: '每剂份数', hidden: true},
            {field: 'prepayment', title: '预交金', hidden: true},
            {field: 'payments', title: '应收', hidden: true},
            {field: 'patientId', title: '病人Id', hidden: true}
         ]],onClickRow: function (index, row) {//单击行事件
             $("#drug").datagrid({
                singleSelect: true,
                fit: true,
                method: 'GET',
                url: basePath + '/drugPrescIn/detailList?masterId=' +row.id,
                idField: 'id',
                columns: [[      //每个列具体内容
                    {field: 'itemNo', title: '组别', width: '10%', align: 'center'},
                    {field: 'drugName', title: '药品名称', width: '10%', align: 'center'},
                    {field: 'firmId', title: '厂商', width: '10%', align: 'center'},
                    {field: 'dosageEach', title: '剂量', width: '10%', align: 'center'},
                    {field: 'quantity', title: '数量', width: '10%', align: 'center'},
                    {field: 'packageUnits', title: '单位', width: '10%', align: 'center'},
                    //{field: 'costs', title: '单价', width: '50%', align: 'center'},
                    {field: 'costs', title: '应收', width: '10%', align: 'center'},
                    {field: 'payments', title: '实收', width: '10%', align: 'center'},
                    {field: 'administration', title: '用法', width: '20%', align: 'center'}

                ]],onLoadSuccess:function(){
                    $('#drug').datagrid('appendRow', {
                        itemNo: '<span>合计</span>',
                        drugName: '<span></span>',
                        firmId: '<span></span>',
                        dosageEach:'<span></span>',
                        quantity:'<span></span>',
                        packageUnits:'<span></span>',
                        costs: '<span class="subtotal">' + compute("costs") + '</span>',
                        payments: '<span class="subtotal">' + compute("payments") + '</span>',
                        administration: '<span class="subtotal"></span>'
                    });
                }
             });
            $('#patientInfoForm').form('load',{
                name:row.name,
                identity:row.identity,
                chargeType:row.chargeType,
                prescribedBy:row.prescribedBy,
                orderedBy:row.orderedBy,
                repetition:row.repetition,
                prescAttr:row.prescAttr,
                enteredBy:row.enteredBy,
                dispensarySub:row.dispensarySub,
                clinicNo:row.clinicNo,
                countPerRepetition:row.countPerRepetition,
                prepayment:row.prepayment,
                payments:row.payments,
                costs:row.costs,
                patientId:row.patientId,
                deptName:config.deptName
             });
        }
    });

    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
        $('#prescTable').datagrid({url:basePath+'/drugPrescIn/list?'+$("#presc").serialize() });
    });

    //选择查询条件的时候给input的name赋值
    $("#search").combobox({
        onChange: function (n,o) {
            var search= $("#search").combobox('getValue');
            $("#searchSome").attr('name', search);
        }

    });
});


//求和
function compute(colName) {
    var rows = $('#drug').datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        if(isNaN(parseFloat(rows[i][colName]))){
            total += 0;
        }else{
            total += parseFloat(rows[i][colName]);
        }

    }
    return total.toFixed(4);
}

//确认发药
function confirmDrug(){
    var  rowMaster=$("#prescTable").datagrid('getSelected');
    if(rowMaster!=null){
        $.ajax({
            'type': 'post',
            'url': basePath + '/drugPrescIn/confirmInDrugPresc?masterId='+rowMaster.id+"&persionId="+config.persion_Id+"&deptName="+config.deptName,
            'contentType': 'application/json',
            'dataType': 'json',
            'success': function (data) {
                if(data.data=="success"){
                    $('#prescTable').datagrid('load');
                    $('#drug').datagrid('loadData', { total: 0, rows: [] });
                    $.messager.alert("提示信息", "发药成功");
                }else{
                    $.messager.alert("提示信息", "发药失败", "error");
                }
             }
        });
    }else{
        $.messager.alert("提示信息", "请选择药品", "error");
    }


}