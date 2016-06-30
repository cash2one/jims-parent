/**
 * Created by dt on 2016/5/11.
 */
$(function () {
    $.extend({
        ajaxAsync : function(url,callback){
            return $.ajax({
                type: 'get',
                url: url,
                async : false,
                success: callback,
                'contentType': 'application/json'
            });
        }
    });
    var editIndex;
    var drugNameCode;//药品编码

    var specUnits = [];//规格单位字典
    var specUnitPromise =  $.ajaxAsync( basePath + "/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });
    var drugFormDict = [];//剂型字典
    var drugFormDictPromise =  $.ajaxAsync( basePath  + "/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    });
    var drugToxi = [];//毒理属性字典
    var drugToxiPromise =  $.ajaxAsync( basePath  + "/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
        drugToxi = data;
    });
    var doseUnit = [];//剂型单位字典
    var doseUnitPromise =  $.ajaxAsync(  basePath  + "/dict/findListByType?type=dose_unit", function (data) {
        doseUnit = data;
    });
    var drugPreciousFlagDict = [];//贵重等级字典
    var drugPreciousFlagDictPromise =  $.ajaxAsync(basePath  + "/dict/findListByType?type=DRUG_PRECIOUS_FLAG_DICT", function (data) {
        drugPreciousFlagDict = data;
    });
    var drugTypeDict = [];//药品类型字典
    var drugTypeDictPromise =  $.ajaxAsync(basePath  + "/dict/findListByType?type=drug_type_dict", function (data) {
        drugTypeDict = data;
    });
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
        url: basePath +  "/drug-class-dict/list-parent?parentId=*",
        onSelect: function(rowData) {
            if (editIndex || editIndex == 0) {
                $("#drugNameDict").datagrid('endEdit', editIndex);
                editIndex = undefined;
            }
            $('#drugNameDict').datagrid('loadData', { total: 0, rows: [] });
            $('#drugSubClass').combobox('clear');
            console.log(rowData);
            var url = basePath + "/drug-class-dict/list-parent?parentId=" + rowData.classCode;
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
        url:basePath  + "/dict/findListByType?type=DRUG_FORM_DICT",
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
            //var
            //drugNameCode = "";
            //drugNameCode = drugNameCode + $("#drugSubClass").combobox('getValue') + rowData.value;
            $.ajaxAsync(basePath + "/drug-catalog/getDrugCodeByRule?secondType="+$("#drugSubClass").combobox('getValue')+"&drugForm="+rowData.value,function(data){
                drugNameCode = data.code;
                console.log(data.code);
                console.log(rowData.value);
            });
            //drugNameCode = drugNameCode +
            stopEdit();
            $('#clear').click();

            $('#drugNameDict').datagrid('appendRow', {
                drugCode: drugNameCode, drugName: '',stdIndicator:'0'
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
                    return '<input type="checkbox" name="drugNameDictCheckbox" style="height: 25px;width: 25px"  checked="checked">';
                }else {
                    return '<input type="checkbox" name="drugNameDictCheckbox" style="height: 25px;width: 25px"  >';
                }
            }
        }
        ]],
        onClickCell: function (rowIndex, field, value) {
            if(field == 'stdIndicator'){
                var rows =  $(this).datagrid("getRows");
                var flag = 0;
                $.each(rows, function (index,row) {
                    if(row[field] == 1){
                        var drugNameDictCheckboxs = $("input[name='drugNameDictCheckbox']");
                        $.messager.alert("提示","选择一个正名","info");
                        $(drugNameDictCheckboxs.get(rowIndex)).removeAttr("checked");
                        flag =1;
                    }
                });

                var row = rows[rowIndex];
                if (value == 0 && flag == 0){
                    row[field] = 1;
                }else{
                    row[field] = 0;
                }
                console.log(row[field]);

            }
        },
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
                    url: basePath + "/dict/findListByType?type=spec_unit"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(specUnits, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
                    url: basePath  + "/dict/findListByType?type=DRUG_FORM_DICT"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(drugFormDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
                    url: basePath  + "/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(drugToxi, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px" checked="checked">';
                }
                else{
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px" >';
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
                    url: basePath  + "/dict/findListByType?type=dose_unit"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(doseUnit, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
                    url:basePath  + "/dict/findListByType?type=DRUG_PRECIOUS_FLAG_DICT"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(drugPreciousFlagDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '类别',
            field: 'drugIndicator',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url: basePath  + "/dict/findListByType?type=drug_type_dict"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(drugTypeDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }
        ]],
        onClickCell: function (rowIndex, field, value) {
            if(field == 'otc'){
                var rows =  $(this).datagrid("getRows");
                var row = rows[rowIndex];
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

        if (drugCatalogBeanVo) {
            $.postJSON(basePath + "/drug-catalog/save", JSON.stringify(drugCatalogBeanVo), function (data) {
                if("1" == data) {
                    //$.messager.alert("系统提示", "保存成功", "info");
                    $.messager.confirm('系统提示, 保存成功','是否定义价格？',function(r){
                        if(r){
                            var https=parent.getRootPath() + '/modules/phstock/drug-price-marger.html';
                            parent.addTab('定义价格',https);
                        }
                    })
                } else {
                    $.messager.alert('提示', "保存失败，请确认是否唯一", "error");
                }
                $("#clear").click();
            }, function (data) {
                $.messager.alert('提示', "保存失败，请确认是否唯一", "error");
                $("#clear").click();
            })
        }
    });

    //增加DrugNameDict
    $("#addDrugName").on('click',function(){
        if(!$("#drugSubClass").combobox('getValue')){
            $.messager.alert("提示","请选择新维护的药品类别信息，药品类别不能为空！",'info');
            return;
        }
        stopEdit();

        var first=drugNameCode.substr(0,5);
        var num=Number(drugNameCode.substr(5,3))+1;
        var last=drugNameCode.substr(8);
        var second='';
        if(num<100){
            if(num.length=1){
                second='00'+num;
            }else {
                second = '0' + num;
            }
        }else{
            second=num;
        }

        drugNameCode=(first+second+last);
        $('#drugNameDict').datagrid('appendRow', {drugCode: drugNameCode, drugName: '',stdIndicator:'0'});
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
            $.get(basePath + "/drug-price/listDrugPriceList?orgId=" + parent.config.org_Id + "&drugCode="+row.drugCode, function (data) {
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
        var stdIndicatorRow ;
        $.each(allRowsTop, function (index,row) {
            if (row['stdIndicator'] == 1){
                stdIndicatorRow = row;
            }
        });

        if (allRowsBottom.length <= 0 && allRowsTop.length <= 0) {
            $.messager.alert("提示", "请首先添加药品名称！", "error");
        } else if (!stdIndicatorRow) {
            $.messager.alert("提示", "请给药品选正名！", "info");
        }else {
            code = stdIndicatorRow.drugCode;
            name = stdIndicatorRow.drugName;

            $('#drugDict').datagrid('appendRow', {
                drugCode: code, drugName: name, drugSpec: '', units: '', drugForm: '', toxiProperty: '',otc: '0', dosePerUnit: '',
                doseUnits: '',limitClass:'',preciousFlag:'',  drugIndicator: '1'
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
            $.get(basePath + "/drug-price/listDrugPriceList?orgId=" + parent.config.org_Id + "&drugCode="+row.drugCode, function (data) {
                if (data.length > 0&&row.id) {
                    $.messager.alert("提示", "已经存在该药品的价格等信息，不允许删除！", "info");
                } else {
                    $('#drugDict').datagrid('deleteRow', index);
                    editIndex = undefined;
                }
            })
        }
    });

    $("#listPrice").on('click',function(){
        var https=parent.getRootPath() + '/modules/phstock/drug-price-marger.html';
        parent.addTab('定义价格',https);
    })



});


