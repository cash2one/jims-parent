/**
 * Created by dt on 2016/5/11.
 */
var expNameCas = [];//产品名称列表
var expTypes = [];//产品类型列表
var expCategorys = [];//产品类别列表
var codeArrays = [];//新生成的expCode数组
$(function () {
    var editIndex;
    var drugNameCode;
    //停止编辑
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drugNameDict").datagrid('endEdit', editIndex);
            $("#drugDict").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    //单选按钮切换

    $(".radios").click(function () {
        var radioValue = $(this).val();
        if (radioValue == 0){
            $("#drugClass").combobox('clear');
            $("#drugSubClass").combobox('clear');

            $("#drugClass").combobox('disable');
            $("#drugSubClass").combobox('disable');

            $("#drugName").combogrid('enable');
        }
        if (radioValue == 1){
            $("#drugName").combogrid('disable');
            $("#drugName").combogrid('clear');

            $("#drugClass").combobox('enable');
            $("#drugSubClass").combobox('enable');
        }

    });

    //定义药品名称
    $('#drugName').combogrid({
        panelWidth: 500,
        idField: 'drugCode',
        textField: 'drugName',
        loadMsg: '数据正在加载',
        url: basePath + '/drug-catalog/drugNameDictList',
        mode: 'remote',
        method: 'GET',
        fitColumns:true,
        columns: [[
            {field: 'drugCode', title: '编码', width: 150, align: 'center'},
            {field: 'drugName', title: '名称', width: 200, align: 'center'},
            {field: 'inputCode', title: '拼音', width: 50, align: 'center'}
        ]],
        onSelect: function(rowIndex,rowData){
            var url = basePath + "/drug-catalog/listDrugNameDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugNameDict').datagrid('reload', url);
            var url = basePath + "/drug-catalog/listDrugDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugDict').datagrid('reload', url);

        }
    });
    //定义药品大类
    $("#drugClass").combobox({
        valueField: 'classCode',
        textField: 'className',
        width:150,
        method: 'GET',
        url: basePath +  "/drug-catalog/listSubClassDict?orgId="+parent.config.org_Id+"&parentId=*",
        onSelect: function(rowData) {
            if (editIndex || editIndex == 0) {
                $("#drugNameDict").datagrid('endEdit', editIndex);
                editIndex = undefined;
            }
            $('#drugNameDict').datagrid('loadData', { total: 0, rows: [] });
            $('#drugSubClass').combobox('clear');
            console.log(rowData);
            var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + rowData.classCode;
            $('#drugSubClass').combobox('reload', url);
        }
    });
    //定义药品亚类
    $("#drugSubClass").combobox({
        valueField: 'classCode',
        textField: 'className',
        width:150,
        method: 'GET',
        url: '',
        onLoadSuccess: function () {
            //var data = $(this).combobox('getData');
            //if (data.length > 0) {
            //    $(this).combobox('setValue', data[0].classCode);
            //    var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + data[0].parentId;
            //    $('#drugNameDict').datagrid('reload', url);
            //}
        },
        onSelect: function(rowData){
            //var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + rowData.parentId;
            //$('#drugNameDict').datagrid('reload', url);
            $("#dosageDialog").dialog({title:"选择剂型"}).dialog("open");
        }
    });
    //定义剂型列表
    $("#dosageDatagrid").datagrid({
        singleSelect: true,
        fit:true,
        method:'get',
        url:basePath  + "/drug-catalog/findListType?type=DRUG_FORM_DICT",
        columns:[[{
            title: 'id',
            field: 'id',
            hidden: true
        },{
            title: '代码',
            field: 'value',
            width: "20%"
        },{
            title: '名称',
            field: 'label',
            width: "80%"
        }]],
        onDblClickRow: function (rowIndex, rowData) {
            drugNameCode = "";
            drugNameCode = drugNameCode + $("#drugSubClass").combobox('getValue') + rowData.value;
            //===================待开发
            // drugNameCode + 随机生成码 同过编码规则
            //=================
            stopEdit();
            $('#drugNameDict').datagrid('appendRow', {
                drugCode: drugNameCode, drugName: ''
            });
            var rows = $("#drugNameDict").datagrid("getRows");
            var addRowIndex = $("#drugNameDict").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#drugNameDict").datagrid('selectRow', editIndex);
            $("#drugNameDict").datagrid('beginEdit', editIndex);
            $("#dosageDialog").dialog("close");
        }
    });

    //定义页面下方drugNameDict列表
    $("#drugNameDict").datagrid({
        title: '药品名称',
        footer: '#tbNameDict',
        singleSelect: true,
        fit:true,
        method:'get',
        columns: [[{
            title: 'id',
            field: 'id',
            hidden: true
        },{
            title: '代码',
            field: 'drugCode',
            width: "40%"
        }, {
            title: '药品名称',
            field: 'drugName',
            width: "50%",
            editor: {type: 'text', options: {required: true}}
        }, {
            title: '正名',
            field: 'stdIndicator',
            width: "8%",
            styler: function () {
            return "text-align: center"
            },

            formatter:function(value,row,index){
                if(value==1){
                    return '<input type="checkbox" name="drugNameDictCheckbox" style="height: 20px;width: 20px" checked="checked">';
                }
                else{
                    return '<input type="checkbox" name="drugNameDictCheckbox" style="height: 20px;width: 20px" >';
                }
            }
        }
        ]],
        onClickRow: function (index, row) {
            //stopEdit();

        }
    });
    //定义页面下方drugDict列表
    $("#drugDict").datagrid({
        title:'药品剂型剂量',
        footer: '#tbDict',
        singleSelect: true,
        fit: true,
        method:'get',
        columns: [[{
            title: 'id',
            field: 'id',
            hidden:true
        },{
            title: '药品名称',
            field: 'drugName',
            width: "10%",
            editor: {type: 'text', options: {required: true}}
        }, {
            title: '规格',
            field: 'drugSpec',
            width: "10%",
            editor: {type: 'text', options: {required: true}}
        }, {
            title: '单位',
            field: 'units',
            width: "7%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: '150',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url: basePath + "/drug-catalog/listMeasuresDict"
                }
            }
        }, {
            title: '剂型',
            field: 'drugForm',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: '150',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url: basePath  + "/drug-catalog/findListType?type=DRUG_FORM_DICT"
                }
            }
        }, {
            title: '毒理属性',
            field: 'toxiProperty',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: '150',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url: basePath  + "/drug-catalog/findListType?type=DRUG_TOXI_PROPERTY_DICT"
                }
            }
        }, {
            title: 'OTC',
            field: 'otc',
            width: "3%",
            styler: function () {
              return "text-align: center"
             },

            formatter:function(value,row,index){
                if(value==1){
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 20px;width: 20px" checked="checked">';
                }
                else{
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 20px;width: 20px" >';
                }
            }
        }, {
            title: '单位剂量',
            field: 'dosePerUnit',
            width: "7%",
            editor:{type:"textbox"}
        }, {
            title: '剂量单位',
            field: 'doseUnits',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: '150',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url: '/api/measures-dict/list'
                }
            }
        }, {
            title: '限制等级',
            field: 'limitClass',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'code',
                    textField: 'name',
                    data: [
                        {'code': '0', 'name': '非限制级'},
                        {'code': '1', 'name': '限制级'},
                        {'code': '2', 'name': '特殊级'},
                        {'code': '3', 'name': '其他'}
                    ]
                }
            },
            formatter:function(value,row,index){
                if(value  == '0'){
                    value = "非限制级";
                }else if(value=="1"){
                    value = "限制级";
                }
                else if(value=="2"){
                    value="特殊级";
                }
                else if(value=="3"){
                    value = "其他";
                }else{
                    value ="";
                }
                return value;
            }
        }, {
            title: '贵重等级',
            field: 'preciousFlag',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url:basePath  + "/drug-catalog/findListType?type=DRUG_PRECIOUS_FLAG_DICT"
                }
            },
            formatter:function(value,row,index){

                return value;
            }
        }, {
            title: '类别',
            field: 'drugIndicator',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'code',
                    textField: 'name',
                    data: [
                        {'code': '1', 'name': '西药'},
                        {'code': '2', 'name': '中成药'},
                        {'code': '3', 'name': '中草药'},
                        {'code': '4', 'name': '辅料'},
                        {'code': '5', 'name': '试剂'},
                        {'code': '6', 'name': '耗材'},
                        {'code': '7', 'name': '其他'}]
                }
            }
        }
        ]],
        onClickCell: function (rowIndex, field, value) {
            if(field == 'otc'){
                var rows =  $(this).datagrid("getRows");
                var row = rows[rowIndex];
                console.log(row);
                if (value == 0){
                    row[field] = 1;
                }else{
                    row[field] = 0;
                }
            }
        },
        onClickRow: function (index, row) {
            //stopEdit();
            //if (row.columnProtect != 1) {
            //    $(this).datagrid('beginEdit', index);
            //    editIndex = index;
            //}
        }
    });

    $("#top").datagrid({
        toolbar: '#tb',
        fit: true,
        border: false
    });
    $("#bottom").datagrid({
        footer: '#ft',
        fit: true,
        border: false
    });

    $("#global").layout({
        fit: true
    });

    //清除
    $("#clear").on('click',function(){
        $("#drugNameDict").datagrid('loadData', {total: 0, rows: []});
        $("#drugDict").datagrid('loadData', {total: 0, rows: []});
    });

    //保存
    $("#save").on('click',function(){
        if (editIndex || editIndex == 0) {
            $("#drugNameDict").datagrid('endEdit', editIndex);
            $("#drugDict").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
        var insertNameData = $("#drugNameDict").datagrid("getChanges", "inserted");
        var updateNameData = $("#drugNameDict").datagrid('getChanges', "updated");
        var deleteNameData = $("#drugNameDict").datagrid('getChanges', "deleted");

        var insertData = $("#drugDict").datagrid("getChanges", "inserted");
        var updateData = $("#drugDict").datagrid('getChanges', "updated");
        var deleteData = $("#drugDict").datagrid('getChanges', "deleted");

        var drugNameDictChangeVo = {};
        drugNameDictChangeVo.inserted = insertNameData;
        drugNameDictChangeVo.updated = updateNameData;
        drugNameDictChangeVo.deleted = deleteNameData;

        var drugDictChangeVo = {};
        drugDictChangeVo.inserted = insertData;
        drugDictChangeVo.updated = updateData;
        drugDictChangeVo.deleted = deleteData;

        var drugCatalogBeanVo = {};
        drugCatalogBeanVo.drugDictDrugCatalogBeanVo = drugDictChangeVo;
        drugCatalogBeanVo.drugNameDictDrugCatalogBeanVo = drugNameDictChangeVo;
        console.log(drugCatalogBeanVo);

        //if (drugCatalogBeanVo) {
        //    $.postJSON(basePath + "/drug-catalog/save", JSON.stringify(drugCatalogBeanVo), function (data) {
        //        $.messager.alert("系统提示", "保存成功", "info");
        //        $("#clear").click();
        //        codeArrays = [];
        //    }, function (data) {
        //        console.log(data);
        //        $.messager.alert('提示', "保存失败", "error");
        //    })
        //}
    });
    //
    ////生成10位代码
    //var newCode = "";
    //var newExpCode = function () {
    //    var scope = $("#drugSubClass").combobox('getValue');//
    //    newCode = '0' + scope;
    //    var category = $("#expCategory").combobox('getValue');//产品类别
    //    category == '' ? newCode : newCode += category;
    //    var type = $("#expType").combobox('getValue');//产品类型
    //    if (type == '') {
    //        $.messager.alert("系统提示", "产品类型不能为空,请选择产品类型！", "error");
    //        return '';
    //    } else {
    //        type == '' ? newCode : newCode += type;
    //    }
    //    var ll_len = newCode.length;
    //    var max = "";
    //    var getMax;
    //    if (ll_len < 10) {
    //        getMax = $.get("/api/exp-name-dict/list-max-exp-code?length=" + ll_len + "&header=" + newCode, function (data) {
    //            max = data + "";
    //            while (10 - newCode.length > max.length) {
    //                max = '0' + max;
    //            }
    //            newCode = newCode + max;
    //        });
    //    }
    //
    //    return getMax;
    //}
    ////当增加多条信息时，newCode从数据库获取的是相同的最大值，这是需要在js里面手动增大max值与前面的固定六位数字拼接
    //var existCode = function(){
    //    if(codeArrays.length > 0){
    //        for (var i = 0; i < codeArrays.length; i++) {
    //            if (codeArrays[i] == newCode){
    //                var num = newCode.substr(6, 4);
    //                num = parseInt(num) + 1;
    //                var max = num+"";
    //                newCode = newCode.substr(0, 6);
    //                while (10 - newCode.length > max.length) {
    //                    max = '0' + max;
    //                }
    //                newCode = newCode + max;
    //            }
    //        }
    //    }
    //    return newCode;
    //}
    //增加DrugNameDict
    $("#addDrugName").on('click',function(){
        if(!$("#drugSubClass").combobox('getValue')){
            $.messager.alert("提示","请选择新维护的药品类别信息，药品类别不能为空！");
            return;
        }
        stopEdit();
        $('#drugNameDict').datagrid('appendRow', {
            expCode: drugNameCode, expName: ''
        });
        var rows = $("#drugNameDict").datagrid("getRows");
        var addRowIndex = $("#drugNameDict").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#drugNameDict").datagrid('selectRow', editIndex);
        $("#drugNameDict").datagrid('beginEdit', editIndex);


    });

    //删除DrugNameDict
    $("#removeDrugName").on('click',function(){
        var row = $('#drugNameDict').datagrid('getSelected');
        var index = $('#drugNameDict').datagrid('getRowIndex', row);
        if (index == -1) {
            $.messager.alert("提示", "请选择删除的行", "info");
        } else {
            $.get(basePath + "/drug-catalog/listDrugPriceList?orgId=" + parent.config.org_Id + "&drugCode="+row.drugCode, function (data) {
                if (data.length > 0 && row.id) {
                    $.messager.alert("提示", "已经存在该药品的价格等信息，不允许删除！", "info");
                } else {
                    $('#drugNameDict').datagrid('deleteRow', index);
                    editIndex = undefined;
                }
            })

        }
    });
    //添加drugDict
    $("#appendDrug").on('click',function(){
        stopEdit();
        var allRowsTop = $('#drugNameDict').datagrid("getRows");
        var allRowsBottom = $('#drugDict').datagrid("getRows");
        var code = "";
        var name = "";
        var row = $('#drugNameDict').datagrid('getSelected');
        if (allRowsBottom.length <= 0 && allRowsTop.length <= 0) {
            $.messager.alert("提示", "请首先添加药品名称！", "error");
        } else if (!row) {
            $.messager.alert("提示", "请首先选中药品名称！", "error");
        }else {
            code = row.drugCode;
            name = row.drugName;

            $('#drugDict').datagrid('appendRow', {
                drugCode: code, drugName: name, drugSpec: '', units: '', drugForm: '', toxiProperty: '',otc: '0', dosePerUnit: '',
                doseUnits: '',limitClass:'',preciousFlag:'',  drugIndicator: ''
            });

            var rows = $("#drugDict").datagrid("getRows");
            var addRowIndex = $("#drugDict").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#drugDict").datagrid('selectRow', editIndex);
            $("#drugDict").datagrid('beginEdit', editIndex);
        }
    });
    //删除drugDict
    $("#removeDrug").on('click', function () {
        var row = $('#drugDict').datagrid('getSelected');
        var index = $('#drugDict').datagrid('getRowIndex', row);
        if (index == -1) {
            $.messager.alert("提示", "请选择删除的行", "info");
        } else {
            $.get(basePath + "/drug-catalog/listDrugPriceList?orgId=" + parent.config.org_Id + "&drugCode="+row.drugCode, function (data) {
                if (data.length > 0&&row.id) {
                    $.messager.alert("提示", "已经存在该药品的价格等信息，不允许删除！", "info");
                } else {
                    $('#drugDict').datagrid('deleteRow', index);
                    editIndex = undefined;
                }
            })
        }
    });
    //
    ////价格
    //$("#listPrice").on('click', function () {
    //    if ($('#drugDict').datagrid("getRows").length > 0) {
    //        var code = $('#drugDict').datagrid('getData').rows[0].expCode;
    //        var name = $('#drugDict').datagrid('getData').rows[0].expName;
    //        setCookie("exp_code", code);
    //        setCookie("exp_name", name);
    //        parent.addTab('产品价格维护', '/his/ieqm/exp-price-list');
    //
    //    } else {
    //        $.messager.alert("提示", "请提取查询价格的行", "info");
    //    }
    //});
});


