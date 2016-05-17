/**
 * Created by luohk on 2016/5/13.
 */
$(function () {
    //ajax同步
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
    var drugNameSpecList = [];
    var selectedSpec ;
    var drugNameDictList = [];
    $.ajaxAsync(basePath + "/drug-price/findDrugNameDictList", function (data) {
        drugNameDictList = data;
    });
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#datagridRight").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    $("#global").layout({
        fit: true
    });
    $("#datagridLeft").datagrid({
        title: '药品价格维护',
        fit: true,
        fitColumns: true,
        singleSelect: true,
        method:'get',
        toolbar: '#datagridLeftTb',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'drugCode',
            field: "drugCode",
            hidden: true
        }, {
            title: '药品名称',
            field: 'drugName',
            width: '100%'
        }]],
        onDblClickRow: function (rowIndex, rowData) {
            console.log(rowData);
        },
        onSelect:function(rowIndex, rowData){
            $.ajaxAsync(basePath + "/drug-price/listDrugPriceList?drugCode="+rowData.drugCode +"&orgId=" + parent.config.org_Id, function (data) {
                $("#datagridRight").datagrid("loadData",data);
            })  ;
            $.ajaxAsync(basePath + "/drug-price/listDrugDictByDrugCode?drugCode="+rowData.drugCode , function (data) {
                drugNameSpecList = [];
                $.each(data, function (index,item) {
                    var drugDict = {};
                    drugDict.drugName = item.drugName;
                    drugDict.drugCode = item.drugCode;
                    drugDict.drugSpec= item.drugSpec;
                    drugDict.units = item.units;

                    drugNameSpecList.push(drugDict);
                });

            })
        }
    });
    $("#datagridRight").datagrid({
        title: '药品价格维护',
        fit: true,//让#dg数据创铺满父类容器
        //  url: basePath + "/AdministrationDict/listAll",
        idField: 'id',
        toolbar: '#datagridRightTb',
        method:'get',
        singleSelect: true,
        columns: [[{
            title: '开始日期',
            field: 'startDate',
            width: '6%',
            hidden:true
        },{
            title: '药品',
            field: 'drugCode',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            },
            formatter: function (value,row,index) {
                $.each(drugNameDictList, function (index,item) {
                    if(item.drugCode == value){
                        console.log(item.drugName);
                        return item.drugName;
                    }
                });
                return "11";
            }
        }, {
            title: '包装数量',
            field: 'amountPerPackage',
            width: '6%',
            editor: {
                type: 'textbox',options: {
                onChange: function (newValue, oldValue) {
                    var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
                    $(spec.target).combobox("setValue",selectedSpec +"*"+newValue);
                }
                }
            }
        }, {
            title: '规格',
            field: 'drugSpec',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'drugSpec',
                    textField: 'drugSpec',
                    url:"",
                    method:"get",
                    onSelect: function (rowData) {
                        var row = $("#datagridRight").datagrid("getSelected");
                        var index= $("#datagridRight").datagrid("getRowIndex",row);
                        row.minSpec = rowData.drugSpec;
                        row.minUnits = rowData.units;
                        var minSpecTr = $('#datagridRight').datagrid('getPanel').find('div.datagrid-body tr td[field="minSpec"] div')[index];
                        var minUnitsTr = $('#datagridRight').datagrid('getPanel').find('div.datagrid-body tr td[field="minUnits"] div')[index];

                        $(minSpecTr).html(rowData.drugSpec);
                        $(minUnitsTr).html(rowData.units);

                        selectedSpec = undefined;
                        selectedSpec  = rowData.drugSpec

                    }
                }
            }
        }, {
            title: '单位',
            field: 'units',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=spec_unit"
                }
            }
        }, {
            title: '厂家',
            field: 'firmId',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'supplierId',
                    textField: 'supplierId',
                    method: 'GET',
                    url: basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商"
                }
            }
        }, {
            title: '停价',
            field: 'stopDate',
            styler: function () {
                return "text-align: center"
            },
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            },
            formatter: function (value,row,index) {
                if (value == 1){
                    return '是';
                }else{
                    return '否'
                }
            }
        }, {
            title: '批发价',
            field: 'tradePrice',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            }
        }, {
            title: '零售价格',
            field: 'retailPrice',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            }
        }, {
            title: '最高限价',
            field: 'hlimitPrice',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            }
        }, {
            title: '价格类别',
            field: 'priceClass',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath + "/dict/findListByType?type=TENDER_PRICE_CLASS"
                }
            }
        }, {
            title: '批发文号',
            field: 'passNo',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            }
        }, {
            title: 'GMP标志',
            field: 'gmp',
            width: '6%',
            styler: function () {
                return "text-align: center"
            },
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            },
            formatter: function (value,row,index) {
                if (value == 1){
                    return '是';
                }else{
                    return '否'
                }
            }
        }, {
            title: '最小规格',
            width: '6%',
            field: 'minSpec'
        }, {
            title: '最小单位',
            field: 'minUnits',
            width: '6%'
        }, {
            title: '住院收据分类',
            field: 'classOnInpRcpt',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: '',
                    textField: '',
                    method: 'GET',
                    url: ""
                }
            }
        }, {
            title: '门诊收据分类',
            field: 'classOnOutpRcpt',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: '',
                    textField: '',
                    method: 'GET',
                    url: ""
                }
            }
        }, {
            title: '核算分类',
            field: 'classOnReckoning',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: '',
                    textField: '',
                    method: 'GET',
                    url: ""
                }
            }
        }, {
            title: '会计科目',
            field: 'subjCode',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: '',
                    textField: '',
                    method: 'GET',
                    url: ""
                }
            }
        }, {
            title: '病案首页分类',
            field: 'classOnMr',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: '',
                    textField: '',
                    method: 'GET',
                    url: ""
                }
            }
        }, {
            title: '备注',
            field: 'memos',
            width: '6%',
            editor: {
                type: 'textbox', options: {
                }
            }
        }

        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
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
            $('#datagridLeft').datagrid('loadData', { total: 0, rows: [] });
            $('#datagridRight').datagrid('loadData', { total: 0, rows: [] });
            $('#drugSubClass').combobox('clear');
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
        onSelect: function(rowData){
            $('#datagridRight').datagrid('loadData', { total: 0, rows: [] });
            var url = basePath + "/drug-price/listDrugNameDictByClassCode?orgId=" +parent.config.org_Id +"&classCode="+ rowData.classCode;
            $('#datagridLeft').datagrid('reload', url);
        }
    });
    //新增
    $("#addBtn").on('click', function () {
        if (drugNameSpecList.length == 0) {
            $.messager.alert("提示", "请选择药品或维护规格", "info");
            return;
        }

        stopEdit();
        $("#datagridRight").datagrid('appendRow', {startDate:new Date(),drugCode:drugNameSpecList[0].drugCode,minSpec:"",minUnits:""});

        var rows = $("#datagridRight").datagrid('getRows');

        var addRowIndex = $("#datagridRight").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#datagridRight").datagrid('selectRow', editIndex);
        $("#datagridRight").datagrid('beginEdit', editIndex);


        var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
        $(spec.target).combobox("loadData",drugNameSpecList);


    });

    $("#delBtn").on('click', function () {
        var row = $("#datagridRight").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#datagridRight").datagrid('getRowIndex', row);
            $("#datagridRight").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    $("#editBtn").on('click', function () {
        var row = $("#datagridRight").datagrid("getSelected");
        var index = $("#datagridRight").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#datagridRight").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#datagridRight").datagrid("endEdit", editIndex);
            $("#datagridRight").datagrid("beginEdit", index);
            editIndex = index;
        }
    });

    /**
     * 保存
     */
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#datagridRight").datagrid("endEdit", editIndex);
        }

        var insertData = $("#datagridRight").datagrid("getChanges", "inserted");
        var updateDate = $("#datagridRight").datagrid("getChanges", "updated");
        var deleteDate = $("#datagridRight").datagrid("getChanges", "deleted");

        var drugPriceList = {};
        drugPriceList.inserted = insertData;
        drugPriceList.deleted = deleteDate;
        drugPriceList.updated = updateDate;

        console.log(drugPriceList);
        if (drugPriceList) {
            $.postJSON(basePath + "/drug-price/save", JSON.stringify(drugPriceList), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#datagridRight').datagrid('loadData',{total:0,row:[]});
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#datagridRight').datagrid('loadData',{total:0,row:[]});
            })
        }
    });
});