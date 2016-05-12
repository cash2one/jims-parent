/**
 * Created by dt on 2016/5/11.
 */
var expNameCas = [];//产品名称列表
var expTypes = [];//产品类型列表
var expCategorys = [];//产品类别列表
var codeArrays = [];//新生成的expCode数组
$(function () {
    var editIndex;
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
            $('#drugdict').datagrid('reload', url);

        }
    });
    //定义药品大类
    $("#drugClass").combobox({
        idField: 'drugCode',
        textField: 'drugName',
        method: 'GET',
        url: basePath +  "/drug-catalog/listSubClassDict?orgId="+parent.config.org_Id+"&parentId=*",
        onselect: function(rowData) {
            if (editIndex || editIndex == 0) {
                $("#examRptPatternGrid").datagrid('endEdit', editIndex);
                editIndex = undefined;
            }
            $('#drugNameDict').datagrid('loadData', { total: 0, rows: [] });
            $('#drugNameDict').combobox('clear');

            var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + rowData.drugCode;
            $('#drugSubClass').combobox('reload', url);
        }
    });
    //定义药品亚类
    $("#drugSubClass").combobox({
        idField: 'drugCode',
        textField: 'drugName',
        method: 'GET',
        url: '',
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examSubclassName);
                var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + data[0].drugCode;
                $('#drugName').datagrid('reload', url);
            }
        },
        onSelect: function(rowData){
            var url = basePath + "/drug-catalog/listSubClassDict?orgId=" + parent.config.org_Id + "&parentId=" + rowData.drugCode;
            $('#drugName').datagrid('reload', url);
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
            width: "30%"
        }, {
            title: '品名',
            field: 'drugName',
            width: "30%",
            editor: {type: 'text', options: {required: true}}
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            //if (row.columnProtect != 1) {
            //    $(this).datagrid('beginEdit', index);
            //    editIndex = index;
            //}
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
            title: '品名',
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
                    panelHeight: 'auto',
                    valueField: 'measuresName',
                    textField: 'measuresName',
                    method: 'get',
                    url: '/api/measures-dict/list'
                }
            }
        }, {
            title: '剂型',
            field: 'drugForm',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'formName',
                    textField: 'formName',
                    method: 'get',
                    url: '/api/exp-form-dict/list'
                }
            }
        }, {
            title: '毒理属性',
            field: 'toxiProperty',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'toxiProperty',
                    textField: 'toxiProperty',
                    method: 'get',
                    url: '/api/exp-property-dict/list'
                }
            }
        }, {
            title: 'OTC类型',
            field: 'otc',
            width: "7%"
        }, {
            title: '单位剂量',
            field: 'dosePerUnit',
            width: "7%"
        }, {
            title: '剂量单位',
            field: 'doseUnits',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    panelHeight: 'auto',
                    valueField: 'measuresName',
                    textField: 'measuresName',
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
                    data: [{'code': '1', 'name': '是'}, {'code': '2', 'name': '否'}]
                }
            },
            formatter:function(value,row,index){
                if(value=="1"){
                    value = "是";
                }
                else if(value=="2"){
                    value="否";
                }
                else if(value=="S"){
                    value = "是";
                }else{
                    value ="是";
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
                    valueField: 'code',
                    textField: 'name',
                    data: [{'code': '1', 'name': '全院产品'}, {'code': '2', 'name': '普通产品'}]
                }
            },
            formatter:function(value,row,index){
                if(value=="1"){
                    value="全院产品";
                }else if(value == "2"){
                    value = "普通产品";
                }
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
                    data: [{'code': '1', 'name': '全院产品'}, {'code': '2', 'name': '普通产品'}]
                }
            }
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            if (row.columnProtect != 1) {
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
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

        var beanChangeVo = {};
        beanChangeVo.drugDictBeanChangeVo = drugDictChangeVo;
        beanChangeVo.drugNameDictBeanChangeVo = drugNameDictChangeVo;
        console.log(beanChangeVo);

        if (beanChangeVo) {
            $.postJSON(basePath + "/drug-catalog/save", JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                $("#clear").click();
                codeArrays = [];
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });
    //
    ////生成10位代码
    //var newCode = "";
    //var newExpCode = function () {
    //    var scope = $("#expScope").combobox('getValue');//产品范围
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
        stopEdit();
        $('#drugNameDict').datagrid('appendRow', {
            //expCode: newCode, expName: ''
        });
        var rows = $("#drugNameDict").datagrid("getRows");
        var addRowIndex = $("#drugNameDict").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#drugNameDict").datagrid('selectRow', editIndex);
        $("#drugNameDict").datagrid('beginEdit', editIndex);


    });
    //
    //删除DrugNameDict
    $("#removeDrugName").on('click',function(){
        var row = $('#drugNameDict').datagrid('getSelected');
        var index = $('#drugNameDict').datagrid('getRowIndex', row);
        if (index == -1) {
            $.messager.alert("提示", "请选择删除的行", "info");
        } else {
            $.get("/api/exp-price-list/get-exp-price-list?expCode=" + row.expCode, function (data) {
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
    $("#appendExp").on('click',function(){
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
            code = row.expCode;
            name = row.expName;

            $('#drugDict').datagrid('appendRow', {
                expCode: code, expName: name, expSpec: '', units: '', expForm: '', toxiProperty: '', dosePerUnit: '',
                doseUnits: '', storageCode: parent.config.storageCode, expIndicator: ''
            });

            var rows = $("#drugDict").datagrid("getRows");
            var addRowIndex = $("#drugDict").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#drugDict").datagrid('selectRow', editIndex);
            $("#drugDict").datagrid('beginEdit', editIndex);
        }
    });
    //删除drugDict
    $("#removeExp").on('click', function () {
        var row = $('#drugDict').datagrid('getSelected');
        var index = $('#drugDict').datagrid('getRowIndex', row);
        if (index == -1) {
            $.messager.alert("提示", "请选择删除的行", "info");
        } else {
            $.get("/api/exp-price-list/get-exp-price-list?expCode=" + row.expCode, function (data) {
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
    //禁止药品类别选中
    $("#drugClass").combobox('disable');
    $("#drugSubClass").combobox('disable');
});


