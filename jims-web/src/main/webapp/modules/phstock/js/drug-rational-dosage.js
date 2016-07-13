/**
 * 药品用量信息
 * Created by fyg on 2016/7/12.
 */
$(function(){
    var currentSelectIndex = undefined;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }

    var AdministrationDictList = [];    //给药途径和方法
    $.get(basePath + '/AdministrationDict/list', function (data) {
        AdministrationDictList = data;
    });

    //药品定位
    $("#drugLocation").combogrid({
        panelWidth: 500,
        idField: 'drugCode',
        textField: 'drugName',
        loadMsg: '数据正在加载',
        url: basePath + '/drug-catalog/drugNameDictList',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'drugCode', title: '编码', width: 150, align: 'center'},
            {field: 'drugName', title: '名称', width: 200, align: 'center'},
            {field: 'inputCode', title: '拼音', width: 50, align: 'center'}
        ]]
    });

    var classCode = ''
    var drugForm = '';
    //药品类别
    $("#drugClass").combobox({
        valueField: 'classCode',
        textField: 'className',
        width: 150,
        method: 'get',
        editable: false,
        url: basePath + '/drug-class-dict/list-parent?parentId=*',
        onSelect: function () {
            classCode = $("#drugClass").combobox('getValue');
            drugForm = $("#drugForm").combobox('getValue');
            if (drugForm != '') {
                loadDrugNameDict();
            }
        }
    });

    //剂型
    $("#drugForm").combobox({
        valueField: 'value',
        textField: 'label',
        width: 150,
        method: 'GET',
        url: basePath + '/dict/label-value-list?type=' + 'DRUG_FORM_DICT',
        editable: false,
        onSelect: function () {
            classCode = $("#drugClass").combobox('getValue');
            drugForm = $("#drugForm").combobox('getValue');
            if (classCode != '') {
                loadDrugNameDict();
            }
        }
    });


    $("#dg").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        border: true,
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        toolbar: '#topBar',
        method: 'get',
        url: basePath + '/drug-rational-dosage/list',
        columns:[[
            {
                title: 'ID',
                field: 'id',
                hidden: true
            },{
                title: '药品代码',
                field: 'drugCode',
                align: 'center',
                hidden: true
            },{
                title: '药品名称',
                field: 'drugName',
                align: 'center',
                width: '8%',
                editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 500,
                        idField: 'drugCode',
                        textField: 'drugName',
                        loadMsg: '数据正在加载',
                        url: basePath + '/drug-catalog/drugNameDictList',
                        mode: 'remote',
                        method: 'GET',
                        fitColumns: true,
                        columns: [[
                            {field: 'drugCode', title: '编码', width: 150, align: 'center'},
                            {field: 'drugName', title: '名称', width: 200, align: 'center'},
                            {field: 'inputCode', title: '拼音', width: 50, align: 'center'}
                        ]],
                        onSelect: function (rowIndex, rowData) {
                            loadDrugNameData(rowData);
                        }
                    }
                }
            },{
                title: '规格',
                field: 'drugSpec',
                align: 'center',
                width: '8%'
            },{
                title: '最小单位剂量',
                field: 'dosePerUnit',
                align: 'center',
                width: '8%'
            },{
                title: '剂量单位',
                field: 'doseUnits',
                align: 'center',
                width: '8%'
            },{
                title: '单次用量',
                field: 'maxDosage',
                align: 'center',
                width: '8%',
                editor: 'textbox'
            },{
                title: '单处方最大开药量',
                field: 'maxPrescDosage',
                align: 'center',
                width: '9%',
                editor: 'textbox'
            },{
                title: '处方最大用药天数',
                field: 'maxOutpAbidance',
                align: 'center',
                width: '9%',
                editor: 'textbox'
            },{
                title: '给药途径和方法',
                field: 'administrationCode',
                align: 'center',
                width: '9%',
                editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 190,
                        idField: 'administrationCode',
                        textField: 'administrationName',
                        loadMsg: '数据正在加载',
                        url: basePath + '/AdministrationDict/list',
                        mode: 'remote',
                        method: 'GET',
                        fitColumns: true,
                        columns: [[
                            {field: 'administrationCode', title: '代码', width: 60, align: 'center'},
                            {field: 'administrationName', title: '名称', width: 80, align: 'center'},
                            {field: 'inputCode', title: '输入码', width: 50, align: 'center'}
                        ]]
                    }
                }, formatter: function (value, row, index) {
                    var administrationName = value;
                    $.each(AdministrationDictList, function (index, item) {
                        if (item.administrationCode == value) {
                            administrationName = item.administrationName;
                        }
                    });
                    return administrationName;
                }
            },{
                title: '执行频率描述',
                field: 'freqDesc',
                align: 'center',
                width: '8%',
                editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 240,
                        idField: 'freqDesc',
                        textField: 'freqDesc',
                        loadMsg: '数据正在加载',
                        url: basePath + '/PerformFreqDict/listAll',
                        mode: 'remote',
                        method: 'GET',
                        fitColumns: true,
                        editable: false,
                        columns: [[
                            {field: 'freqDesc', title: '执行频率描述', width: 80, align: 'center'},
                            {field: 'freqCounter', title: '频率次数', width: 60, align: 'center',hidden: true},
                            {field: 'freqInterval', title: '频率间隔', width: 50, align: 'center',hidden: true},
                            {field: 'freqIntervalUnits', title: '频率间隔单位', width: 50, align: 'center',hidden: true}
                        ]],
                        onSelect: function (rowIndex, rowData) {
                            loadPerformFreqData(rowData);
                        }
                    }
                }
            },{
                title: '频率次数',
                field: 'freqCounter',
                align: 'center',
                width: '8%'
            },{
                title: '频率间隔',
                field: 'freqInterval',
                align: 'center',
                width: '8%'
            },{
                title: '频率间隔单位',
                field: 'freqIntervalUnits',
                align: 'center',
                width: '8%'
            }
        ]],
        onClickRow: function (index, row) {
            //var opts = $('#dg').datagrid('getColumnFields',true);
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    //加载同一药品的不同规格、最小单位剂量、剂量单位
    var loadDrugNameData = function (drugDict) {
        $.get(basePath + '/drug-dict/get-by-name?drugCode=' + drugDict.drugCode + '&drugName=' + drugDict.drugName, function (res) {
            showWindow(res, drugDict);
        });
    };
    //展现同一药品的不同规格、最小单位剂量、剂量单位
    var showWindow = function (data, drugDict) {
        var thisRow = $('#dg').datagrid('getSelected');
        var _oldDrugName = thisRow.drugName;
        var _tempFlag = false   // 当window关闭时是否赋值

        var initData = function (data, drugDict) {
            var drugParam = {
                drugCode: data.drugCode
                , drugName: data.drugName
                , drugSpec: data.drugSpec
                , dosePerUnit: data.dosePerUnit
                , doseUnits: data.doseUnits
            }
            if (chargeDrugExisted(drugParam)) {
                $.messager.alert('警告', '该规格的药品已存在，请重新选择！', 'error');
                rollBack(_oldDrugName);
                return
            } else {
                thisRow.drugCode = data.drugCode;
                thisRow.drugName = data.drugName;
                thisRow.drugSpec = data.drugSpec;
                thisRow.dosePerUnit = data.dosePerUnit;
                thisRow.doseUnits = data.doseUnits;

                $('#dg').datagrid('endEdit', editIndex);
                $('#dg').datagrid('beginEdit', editIndex);
            }
        }

        if (data.length == 1) {
            initData(data[0], drugDict);
            return;
        }
        ;

        $('#drugNameWindow').window({
            title: '选择药品规格和单位',
            width: '550',
            height: '450',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            modal: true,
            resizable: false,
            onClose: function () {
                if (!_tempFlag) {
                    //rollBack(_oldDrugName)
                }
            }
        })
        $("#drugNameTable").datagrid({
            fit: true,
            border: 0,
            fitColumns: true, //列自适应宽度
            singleSelect: true,
            remoteSort: false,
            data: data,
            idField: 'id',
            columns: [[
                {field: 'id', title: 'ID', hidden: true},
                {field: 'drugCode', title: '药品代码', align: 'center', width: 80},
                {field: 'drugName', title: '药品名称', align: 'center', width: 80},
                {field: 'drugSpec', title: '规格', align: 'center', width: 60},
                {field: 'dosePerUnit', title: '最小单位剂量', width: 60, align: "center"},
                {field: 'doseUnits', title: '剂量单位', width: 60, align: "center"}
            ]],
            onDblClickRow: function (index, row) {
                initData(row, drugDict);
                $('#drugNameWindow').window('close');
            }
        })
    };
    //判断是否已存在此药品的规格
    var chargeDrugExisted = function (o) {
        var allRow = $('#dg').datagrid('getRows');
        for (var i = 0, j = allRow.length - 1; i < j; i++) {
            if (i != currentSelectIndex && allRow[i].drugCode == o['drugCode'] && allRow[i].drugName == o['drugName']
                && allRow[i].drugSpec == o['drugSpec'] && allRow[i].dosePerUnit == o['dosePerUnit']
                && allRow[i].doseUnits == o['doseUnits']) {
                return true;
            }
        }
        return false;
    };
    /**
     * 药品返回到选择前的值
     * @param oldV 选择前的值
     */
    var rollBack = function (oldV) {
        var editor = $('#dg').datagrid('getEditor', {index: currentSelectIndex, field: 'drugName'});
        $(editor.target).combogrid('setValue', oldV);
        $('#dg').datagrid('endEdit', currentSelectIndex);
    };

    //加载频率次数、频率间隔、频率间隔单位
    var loadPerformFreqData = function (data) {
        var thisRow = $('#dg').datagrid('getSelected');
        thisRow.freqCounter = data.freqCounter;
        thisRow.freqInterval = data.freqInterval;
        thisRow.freqIntervalUnits = data.freqIntervalUnits;

        $('#dg').datagrid('endEdit', editIndex);
        $('#dg').datagrid('beginEdit', editIndex);
    };

    $("#addBtn").on("click", function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#delBtn").on("click", function () {
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#dg").datagrid('getRowIndex', row);
            $("#dg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateData = $("#dg").datagrid("getChanges", "updated");
        var deleteData = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        console.log(beanChangeVo.inserted);

        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-rational-dosage/merge', JSON.stringify(beanChangeVo), function (data) {
                console.log(data);
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });

    $("#searchBtn").on("click", function () {
        var drugClass = $("#drugClass").combobox('getValue');
        var drugSubClass = $("#drugSubClass").combobox('getValue');
        var drugLocation = $("#drugLocation").combobox('getValue');
        var drugForm = $("#drugForm").combobox('getValue');
        console.log("drugClass:" + drugClass);
        console.log("drugSubClass:" + drugSubClass);
        console.log("drugLocation:" + drugLocation);
        console.log("drugForm:" + drugForm);
    });

    var loadDict = function () {
        $.get(basePath + '/drug-rational-dosage/list', function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();

    //单选按钮切换
    $(".radios").click(function () {
        var radioValue = $(this).val();
        if (radioValue == 0) {
            $("#drugClass").combobox('clear');
            $("#drugForm").combobox('clear');

            $("#drugClass").combobox('disable');
            $("#drugForm").combobox('disable');
            $("#drugLocation").combogrid('enable');
        }
        if (radioValue == 1) {
            $("#drugLocation").combogrid('disable');
            $("#drugLocation").combogrid('clear');

            $("#drugClass").combobox('enable');
            $("#drugForm").combobox('enable');
        }
    });
});
