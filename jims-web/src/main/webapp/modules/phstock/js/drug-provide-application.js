/**
 * 录入申请
 * @author yangruidong
 * @version 2016-07-04
 */


$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    var orgId = config.org_Id;
    //var orgId = 1;
    var currentSelectDeptData;
    var unitsForm = [];
    var staffFrom = [];
    var currentStorage=config.currentStorage;

    $("#drug-provide-application").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "序号",
            field: "itemNo",
            hidden: true
        }, {
            title: "药名",
            field: "drugName",
            width: '200',
            align: 'center',
            editor: {

                type: 'combogrid',
                options: {
                    panelWidth: 900,
                    idField: 'drug_name',
                    textField: 'drug_name',
                    mode: 'remote',
                    method: 'GET',
                    url: basePath + '/input-setting/listParamByGET?orgId=' + orgId + '&dictType=v_drug_provide_application',
                    columns: [[
                        {field: 'drug_code', title: '代码', width: 100, align: 'center'},
                        {field: 'drug_name', title: '名称', width: 100, align: 'center'},
                        {field: 'input_code', title: '拼音码', width: 100, align: 'center'},
                        {field: 'supplier_id', title: '厂家', width: 100, align: 'center'},
                        {field: 'drug_spec', title: '规格', width: 100, align: 'center'},
                        {field: 'label', title: '单位', width: 100, align: 'center'},
                        {field: 'trade_price', title: '批发价', width: 100, align: 'center'},
                        {field: 'retail_price', title: '零售价', width: 100, align: 'center'},
                        {field: 'min_spec', title: '最小规格', width: 100, align: 'center'}
                    ]],
                    onSelect: function (index, data) {
                        var row = $('#drug-provide-application').datagrid('getSelected');
                        row.drugCode = data.drug_code;
                        row.drugName = data.drug_name;
                        row.drugSpec = data.min_spec;
                        row.packageSpec=data.drug_spec;
                        row.packageUnits =data.units;
                        row.units=data.min_units;
                        row.label = data.label;
                        row.firmId = data.firm_id;
                        row.supplierId = data.supplier_id;
                        $('#drug-provide-application').datagrid('endEdit', editIndex1);
                        var rows = $("#drug-provide-application").datagrid('getRows');
                        onClickCell(rows.length - 1, 'quantity');
                    }
                }
            }
        }, {
            title: "规格",
            field: "drugSpec",
            width: '100',
            align: 'center'
        }, {
            title: "单位",
            field: "label",
            width: '100',
            align: 'center'
        }, {
            title: "批号",
            field: "batchNo",
            width: '100',
            align: 'center'
        }, {
            title: "申请数量",
            field: "quantity",
            width: '100',
            align: 'center',
            editor: {
                type: 'numberbox',
                options: {
                    required: true,
                    missingMessage: '数量不能为空'
                }
            }
        }, {
            title: "生产厂家",
            field: "supplierId",
            width: '150',
            align: 'center'
        }, {
            title: "申请单号",
            field: "documentNo",
            width: '100',
            align: 'center'
        }]],
        onClickCell: onClickCell
    });


    //datagrid的单元格编辑
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex1 = undefined;

    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#drug-provide-application').datagrid('validateRow', editIndex1)) {
            $('#drug-provide-application').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing2()) {
            $('#drug-provide-application').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
        }
    }

    //获取来源
    $("#storage").combobox({
        url: basePath + '/drug-storage-dept/findListByLevel?orgId='+orgId+'&condition=remarks<\''+ config.currentStorageObj.level+'\'',
        valueField: 'storageCode',
        textField: 'storageName',
        method: 'GET',
        onSelect: function (row) {
            currentSelectDeptData = row.storageCode;
            $.get(basePath + "/drug-storage-dept/findSubList", {
                orgId: orgId,
                storageCode: currentSelectDeptData
            }, function (data) {
                $("#subStorage").combobox('loadData', data);
            });
        }
    });

    //获取子库
    $("#subStorage").combobox({
        valueField: 'subStorageCode',
        textField: 'subStorage',
        height: 25,
        width: 120,
        onSelect: function (row) {
            var storageData = $("#storage").combobox("getValue");
            $.get(basePath + "/drugProvideApplication/findDocument", {
                orgId: orgId,
                provideStorage: storageData,
                subStorage: row.subStorageCode,
                flag: 0
            }, function (data) {
                $("#documentNo").combobox('loadData', data);

            });
        }
    });

    //获取子库房
    $("#subStorage1").combobox({
        url:basePath + "/drug-storage-dept/findSubList",
        queryParams:  {
            orgId: orgId,
            storageCode:config.currentStorage
        },
        method:'GET',
        valueField: 'subStorageCode',
        textField: 'subStorage',
        height: 25,
        width: 120
    });

    //获取申请号
    $("#documentNo").combobox({
        valueField: 'documentNo',
        textField: 'documentNo',
        height: 25,
        width: 120,
        onSelect: function (row) {
            var storageData = $("#storage").combobox("getValue");
            var subStorageData = $("#subStorage").combobox("getValue");
            currentSelectDeptData = row.documentNo;
            $.get(basePath + "/drugProvideApplication/findList", {
                orgId: orgId,
                provideStorage: storageData,
                subStorage: subStorageData,
                documentNo: currentSelectDeptData
            }, function (data) {
                $("#drug-provide-application").datagrid('loadData', data);
            });
        }
    });


    //格式化时间 年月之间不带“-”
    function getNowFormatDate() {
        var day = new Date();
        var Year = 0;
        var Month = 0;
        var Day = 0;
        var Hours = 0;
        var Times = 0;
        var Mills = 0;
        var CurrentDate = "";
        //初始化时间
        Year = day.getFullYear();
        Month = day.getMonth() + 1;
        Day = day.getDate();
        Hours = day.getHours(); //获取当前小时数(0-23)
        Times = day.getMinutes(); //获取当前分钟数(0-59)
        Mills = day.getSeconds();//获取当前的秒数
        CurrentDate += Year;
        if (Month >= 10) {
            CurrentDate += Month;
        }
        else {
            CurrentDate += "0" + Month
        }
        if (Day >= 10) {
            CurrentDate += Day;
        }
        else {
            CurrentDate += "0" + Day;
        }
        if (Times >= 10) {
            CurrentDate += Times;
        }
        else {
            CurrentDate += "0" + Times;
        }
        if (Mills >= 10) {
            CurrentDate += Mills;
        }
        else {
            CurrentDate += "0" + Mills;
        }
        return CurrentDate;
    }

    //开始编辑行
    $("#addBtn").on('click', function () {
        //发放子库房
        var subStorageCode = $('#subStorage').combobox('getValue');
        var  StorageCode= $('#storage').combobox('getValue');
        //请领子库房
        var subStorage1 = $('#subStorage1').combobox('getValue');
        if (!currentSelectDeptData) {
            $.messager.alert("系统提示", "请先选择来源", "info");
            return;
        }
        if (!subStorageCode) {
            $.messager.alert("系统提示", "请选择子库，在维护数据", "info");
            return;
        }
        if (!subStorage1) {
            $.messager.alert("系统提示", "请选择请领子库房，在维护数据", "info");
            return;
        }
        //序号
        var rows = $("#drug-provide-application").datagrid('getRows')
        if (rows.length > 0) {
            var c = rows[rows.length - 1].itemNo
            itemNo = c + 1;
        } else {
            itemNo = 1;
        }
        $("#drug-provide-application").datagrid('appendRow', {
            orgId: orgId, flag: 0, itemNo: itemNo, quantity: 0, batchNo: 'X',
            applicantStorage: currentStorage, applicantStorageSub: subStorage1 ,provideStorage : StorageCode,subStorage:subStorageCode
        });
        var rows = $("#drug-provide-application").datagrid('getRows');
        onClickCell(rows.length - 1, 'drugName');
    });


    //查询
    $("#seaBtn").on('click', function () {

        var storage = $("#storage").combobox('getValue');
        var substorage = $("#subStorage").combobox('getValue');
        var documentNo = $("#documentNo").combobox('getValue');
        var subStorage1 = $('#subStorage1').combobox('getValue');
        //选取科室时重新加载数据
        $.get(basePath + "/drugProvideApplication/findList", {
            provideStorage: storage,
            orgId: orgId,
            subStorage: substorage,
            applicantStorageSub:subStorage1,
            documentNo: documentNo
        }, function (data) {
            $("#drug-provide-application").datagrid('loadData', data);
        });
    });


    //包名称保存
    $("#saveBtn").on("click", function () {
        if (editIndex1 || editIndex1 == 0) {
            $("#drug-provide-application").datagrid("endEdit", editIndex1);
        }

        var insertData = $("#drug-provide-application").datagrid("getChanges", "inserted");
        var updateData = $("#drug-provide-application").datagrid("getChanges", "updated");
        var deleteData = $("#drug-provide-application").datagrid("getChanges", "deleted");


        //获取申请号     当前年月日加小时和分钟   总共10位
        if (insertData != null) {
            var nowdate = getNowFormatDate();
            var documentNo = nowdate.substr(2, 6);
            documentNo += nowdate.substr(8, 13);
        }
        //删除多余的字段
        for (var i = 0; i < insertData.length; i++) {
            delete insertData[i]["supplierId"];
            delete insertData[i]["label"];
            delete insertData[i]["drugName"];

            insertData[i]["documentNo"] = documentNo;
            //数量必须大于0
            if (insertData[i].quantity == "0") {
                $.messager.alert("系统提示", "数量不能为空,必须大于0", "info");
                var rows = $("#drug-provide-application").datagrid('getRows');
                onClickCell(rows.length - 1, 'quantity');
                return;
            }
        }
        for (var j = 0; j < updateData.length; j++) {
            delete updateData[j]["supplierId"];
            delete updateData[i]["label"];
            delete updateData[i]["drugName"];
        }
        for (var x = 0; x < deleteData.length; x++) {
            delete deleteData[x]["supplierId"];
            delete deleteData[i]["label"];
            delete deleteData[i]["drugName"];
        }

        var drugProvideApplicationVo = {};
        drugProvideApplicationVo.inserted = insertData;
        drugProvideApplicationVo.deleted = deleteData;
        drugProvideApplicationVo.updated = updateData;

        var storage = $("#storage").combobox('getValue');
        var substorage = $("#subStorage").combobox('getValue');
        var document = $("#documentNo").combobox('getValue');

        if (document != "" && insertData != null) {
            //删除多余的字段
            for (var i = 0; i < insertData.length; i++) {
                delete insertData[i]["supplierId"];
                delete insertData[i]["label"];
                delete insertData[i]["drugName"];

                insertData[i]["documentNo"] = document;
                //数量必须大于0
                if (insertData[i].quantity == "0") {
                    $.messager.alert("系统提示", "数量不能为空,必须大于0", "info");
                    return;
                }
            }
        }

        if (drugProvideApplicationVo) {
            $.postJSON(basePath + "/drugProvideApplication/saveAll", JSON.stringify(drugProvideApplicationVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    var url = basePath + '/drugProvideApplication/findList?orgId=' + orgId + '&applicantStorage=' + storage + '&applicantStorageSub=' + substorage;
                    $("#drug-provide-application").datagrid('reload', url);
                    $("#documentNo").combobox('setValue', '')
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });


    //删除
    $("#delBtn").on("click", function () {
        var row = $("#drug-provide-application").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#drug-provide-application").datagrid('getRowIndex', row);
            $("#drug-provide-application").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });


});




