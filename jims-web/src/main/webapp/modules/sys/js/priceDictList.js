$(function () {
    $('#aa').combobox({
        data:'BILL_ITEM_CLASS_DICT',
        url: basePath+'/dict/findType',
        valueField:'label',
        textField:'label',
        method:'GET'
    });
    $('#bb').combobox({
        data: 'TALLY_SUBJECT_DICT',
        url: basePath + '/dict/findType',
        valueField: 'label',
        textField: 'label',
        method: 'GET'
    });
    $('#cc').combobox({
        data: 'OUTP_RCPT_FEE_DICT',
        url: basePath + '/dict/findType',
        valueField: 'label',
        textField: 'label',
        method: 'GET'
    });
    $('#dd').combobox({
        data: 'INP_RCPT_FEE_DICT',
        url: basePath + '/dict/findType',
        valueField: 'label',
        textField: 'label',
        method: 'GET'
    });
    $('#ee').combobox({
        data: 'MR_FEE_CLASS_DICT',
        url: basePath + '/dict/findType',
        valueField: 'label',
        textField: 'label',
        method: 'GET'
    });
    $('#ff').combobox({
        data: 'RECK_ITEM_CLASS_DICT',
        url: basePath + '/dict/findType',
        valueField: 'label',
        textField: 'label',
        method: 'GET'
    });
    $('#dt').datetimebox({
        showSeconds: false
    });
    
    $("#feeTypeMask").on("click", function () {
        console.log($("#feeTypeMask").prop("checked"));
        if ($("#feeTypeMask").prop("checked") == true){
            value ='1';
        }else{
           value ='0';
        }
    })

});

/**
 * 保存方法
 */
function saveDict() {
    $.postForm(basePath + '/price/save', 'prescForm', function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $("#dlg").dialog('close');
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}