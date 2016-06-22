var ordersType =  [];
var billingAttr = [];
//药品
var ordersDrugData={};
ordersDrugData.orgId="1";
ordersDrugData.dictType="v_drug_info_mz";
//非药品V_CINIC_ITEM_NANE

var administrationDict = [];
var performFreqDict = [];


var ClinicItemcolumnsData = [
    {field: 'item_code', title: '代码', width: '8%', align: 'center'},
    {field: 'item_name', title: '名称', width: '15%', align: 'center'},
    {field: 'input_code', title: '拼音', width: '15%', align: 'center'},
    {field: 'item_class', title: '类别', width: '15%', align: 'center'},
    {field: 'expand1', title: '扩展1', width: '15%', align: 'center'},
    {field: 'expand2', title: '扩展2', width: '15%', align: 'center'},
    {field: 'expand5', title: '扩展5', width: '15%', align: 'center'}
];
/**
 * 医嘱类型
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=REPEAT_INDICATOR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        ordersType=data;
    }
});




/**
 * 医嘱类型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function itemFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < ordersType.length; i++) {
        if (ordersType[i].value == value) {
            return ordersType[i].label;
        }
    }
}

function orderClassFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < Oclass.length; i++) {
        if (Oclass[i].value == value) {
            return Oclass[i].label;
        }
    }
}



/**
 * 药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(ordersDrugData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        ordersDrugData=data;
    }
});




/**
 * 计价属性
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=BILLING_ATTR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        billingAttr=data;
    }
});

/**
 * 计价属性翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function billingAttrFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < billingAttr.length; i++) {
        if (billingAttr[i].value == value) {
            return billingAttr[i].label;
        }
    }
}




/**
 * 途径
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/AdministrationDict/listAdministrationByInpOrOutpFlag',
    data: 'inpOrOutpFlag=1',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        administrationDict=data;
    }
});

/**
 * 途径翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function administrationFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < administrationDict.length; i++) {
        if (administrationDict[i].id == value) {
            return administrationDict[i].administrationName;
        }
    }
}
/**
 * 频率
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/PerformFreqDict/findPer',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        performFreqDict=data;
    }
});




/**
 * 频率翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function performFreqFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < performFreqDict.length; i++) {
        if (performFreqDict[i].id == value) {
            return performFreqDict[i].freqDesc;
        }
    }
}

//药品自动补全
function comboGridCompleting(q,id){
    var drugNameData={};
    drugNameData.orgId="1";
    drugNameData.dictType="v_drug_info_mz"
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }else{
        $("#"+id).combogrid('setValue','');
    }
    drugNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(drugNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            $("#"+id).combogrid("grid").datagrid("loadData", data);
            $("#"+id).combogrid('setText',q);
        }
    });
}

/*
function getOrderText(type){
    if(type=='2'){//非药品
        ordersDrugData.dictType='v_cinic_item_nane';
        drugcolumnsData = [
            {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
            {field: 'item_name', title: '名称', width: '15%', align: 'center'},
            {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
            {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
            {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center', editor: 'text'},
            {field: 'dose_units', title: '用量单位', width: '15%', align: 'center', editor: 'text'}
        ];
        drugColumns=drugColumns.push(drugcolumnsData);
        $('#doctorNameId').combogrid({
            options: {
                panelWidth: 500,
                data: ordersDrugData,
                idField: 'drug_code',
                textField: 'item_name',
                columns: drugColumns
                 */
/*,keyHandler: {
                 up: function() {},
                 down: function() {},
                 enter: function() {},
                 query: function(q) {
                 comboGridCompleting(q,'orderText');
                 }
                 }*//*
, onClickRow: function (index, row) {
                    var dosage = $("#orderList").datagrid('getEditor', {index: rowNum, field: 'dosage'});
                    $(dosage.target).textbox('setValue', row.dose_per_unit);
                    var dosageUnits = $("#orderList").datagrid('getEditor', {index: rowNum, field: 'dosageUnits'});
                    $(dosageUnits.target).textbox('setValue', row.dose_units);

                }
            }
        });
    }
}
*/
