$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#modifyList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    var specUnits = [];//规格单位字典
    $.get( basePath + "/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });

    var drugNameDictList = [];//药品名称列表
    var drugSupplierDict  = [];//药品生产商列表
    var selectedPriceListRow;
    $.get(basePath + "/drug-price/findDrugNameDictList", function (data) {
        drugNameDictList = data;
    });
    $.get( basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商", function (data) {
        drugSupplierDict = data;
    });
    //调价变动datagrid
    $("#modifyList").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "drugCode",
            width: '12%',
            align: 'center',
            formatter:function(value,row,index){
                var drugName = value;
                $.each(drugNameDictList, function (index,item) {
                    if(item.drugCode == value){
                        drugName =  item.drugName;
                    }
                });
                return drugName;
            }

        }, {
            title: "规格",
            field: "minSpec",
            width: '6%',
            align: 'center'

        }, {
            title: "包装规格",
            field: "drugSpec",
            width: '6%',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '6%',
            align: 'center',
            formatter:function(value,row,index){
                var unitsName = value;
                $.each(specUnits, function (index,item) {
                    if(item.value == value){
                        unitsName =  item.label;
                    }
                });
                return unitsName;
            }
        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center',
            formatter:function(value,row,index){
                var supplierName = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.id == value){
                        supplierName =  item.supplier;
                    }
                });
                return supplierName;
            }
        }, {
            title: "原批发价",
            field: "originalTradePrice",
            width: '9%',
            align: 'center'

        }, {
            title: "新批发价",
            field: "currentTradePrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'textbox', options: {
                    required:true
                }
            }
        }, {
            title: "原零售价",
            field: "originalRetailPrice",
            width: '9%',
            align: 'center'

        }, {
            title: "新零售价",
            field: "currentRetailPrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'textbox', options: {
                }
            }
        }, {
            title: "通知生效日期",
            field: "noticeEfficientDate",
            width: '12%',
            align: 'center',
            editor: {
                type: 'datetimebox', options: {
                onChange: function (newValue, oldValue) {
                    var priceListStartDate = new Date(selectedPriceListRow.startDate);

                    if (new Date(newValue).getTime() < priceListStartDate.getTime() ){
                        $.messager.alert("提示","生效日期应大于启用日期","info");
                    }
                }
                }
            },
            formatter: function (value,row,index) {
                row.noticeEfficientDate = new Date(value);
                return value;
            }
        }, {
            title: "调价依据",
            field: "modifyCredential",
            width: '9%',
            align: 'center',
            editor: {
                type: 'textbox', options: {
                }
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    //价格变动datagrid
    $("#priceList").datagrid({
        fit: true,
        striped: true,
        //title: '价格记录',
        toolbar: '#modifyListTb',
        singleSelect: true,
        method: 'GET',
        //  url: basePath + "/AdministrationDict/listAll",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "drugCode",
            width: '12%',
            align: 'center',
            formatter:function(value,row,index){
                var drugName = value;
                $.each(drugNameDictList, function (index,item) {
                    if(item.drugCode == value){
                        drugName =  item.drugName;
                    }
                });
                return drugName;
            }

        }, {
            title: "药品规格",
            field: "drugSpec",
            width: '9%',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '6%',
            align: 'center',
            formatter:function(value,row,index){
                var unitsName = value;
                $.each(specUnits, function (index,item) {
                    if(item.value == value){
                        unitsName =  item.label;
                    }
                });
                return unitsName;
            }
        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center',
            formatter:function(value,row,index){
                var supplierName = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.id == value){
                        supplierName =  item.supplier;
                    }
                });
                return supplierName;
            }
        }, {
            title: "批发价",
            field: "tradePrice",
            width: '9%',
            align: 'center'
        }, {
            title: "最高限价",
            field: "hlimitPrice",
            width: '9%',
            align: 'center'
        }, {
            title: "包装量",
            field: "amountPerPackage",
            width: '9%',
            align: 'center'
        }, {
            title: "最小规格",
            field: "minSpec",
            width: '6%',
            align: 'center'
        }, {
            title: "最小单位",
            field: "minUnits",
            width: '6%',
            align: 'center',
            formatter:function(value,row,index){
                var unitsName = value;
                $.each(specUnits, function (index,item) {
                    if(item.value == value){
                        unitsName =  item.label;
                    }
                });
                return unitsName;
            }
        }, {
            title: "起用日期",
            field: "startDate",
            width: '9%',
            align: 'center'
        }, {
            title: "停止日期",
            field: "stopDate",
            width: '9%',
            align: 'center'
        }, {
            title: "备注",
            field: "memos",
            width: '6%',
            align: 'center'
        }
        ]],
        onSelect: function (rowIndex,rowData) {
            stopEdit();
            selectedPriceListRow = rowData;
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
            var url = basePath + "/drug-price/listDrugPriceList?drugCode=" +  rowData.drugCode + "&orgId=" + parent.config.org_Id;
            $('#priceList').datagrid('reload', url);

        }
    });
    //新增
    $("#addBtn").on('click', function () {
        var row = $("#priceList").datagrid("getSelected");
        var rows = $("#modifyList").datagrid('getRows');
        if(!row){
            $.messager.alert("提示",'请选择调价哪个规格',"info");
            return ;
        }
        if (rows.length > 0){
            var flag = 0;
            $.each(rows,function(index,item){
                if (item.drugCode == row.drugCode && item.drugSpec == row.drugSpec && item.units == row.units
                && item.firmId == row.firmId ){
                    $.messager.alert("提示",'不能选择已有调价记录',"info");
                    flag = 1;
                }
            });

            if (flag==1){
                return ;
            }
        }


        stopEdit();
        $("#modifyList").datagrid('appendRow', {orgId:parent.config.org_Id,drugCode:row.drugCode,drugSpec:row.drugSpec,
            units:row.units,firmId:row.firmId,minSpec:row.minSpec,minUnits:row.minUnits,originalTradePrice:row.tradePrice,
            currentTradePrice:row.tradePrice,originalRetailPrice:row.retailPrice,currentRetailPrice:row.retailPrice,
            noticeEfficientDate:new Date(),operator:parent.config.operator});



        var addRowIndex = $("#modifyList").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#modifyList").datagrid('selectRow', editIndex);
        $("#modifyList").datagrid('beginEdit', editIndex);
    });
    //删除
    $("#delBtn").on('click', function () {
        var row = $("#modifyList").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#modifyList").datagrid('getRowIndex', row);
            $("#modifyList").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });
    //保存
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#modifyList").datagrid("endEdit", editIndex);
        }

        var insertData = $("#modifyList").datagrid("getChanges", "inserted");
        var updateDate = $("#modifyList").datagrid("getChanges", "updated");
        var deleteDate = $("#modifyList").datagrid("getChanges", "deleted");

        var drugPriceModifyVo = {};
        drugPriceModifyVo.inserted = insertData;
        drugPriceModifyVo.deleted = deleteDate;
        drugPriceModifyVo.updated = updateDate;
        console.log(JSON.stringify(drugPriceModifyVo));
        if (drugPriceModifyVo) {
            $.postJSON(basePath + "/drug-price-modify/save", JSON.stringify(drugPriceModifyVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#modifyList').datagrid('loadData', { total: 0, rows: [] });
                $('#priceList').datagrid('loadData', { total: 0, rows: [] });
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#modifyList').datagrid('loadData', { total: 0, rows: [] });
                $('#priceList').datagrid('loadData', { total: 0, rows: [] });
            })
        }
    });

});