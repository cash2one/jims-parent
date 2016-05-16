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
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
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
            $.ajaxAsync(basePath + "/drug-price/listDrugDictByDrugCode?drugCode="+rowData.drugCode , function (data) {
                $.each(data, function (index,item) {
                    var spec = {};
                    spec.drugSpec = item.drugSpec;
                    drugNameSpecList.push(spec);
                });
                console.log(drugNameSpecList);
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
            title: '药品',
            field: 'drugCode',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '包装数量',
            field: 'amountPerPackage',
            width: '6%',
            editor: {
                type: 'text',options: {
                    required: true
                }
            }
        }, {
            title: '规格',
            field: 'drugSpec',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    data:drugNameSpecList
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
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '停价',
            field: 'stopDate',
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            }
        }, {
            title: '批发价',
            field: 'tradePrice',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '零售价格',
            field: 'retailPrice',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '最高限价',
            field: 'hlimitPrice',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
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
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: 'GMP标志',
            field: 'gmp',
            width: '6%',
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
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
            color:'bule',
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
                type: 'text'
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
            var url = basePath + "/drug-price/listDrugNameDictByClassCode?orgId=" +parent.config.org_Id +"&classCode="+ rowData.classCode;
            $('#datagridLeft').datagrid('reload', url);
        }
    });

    $("#addBtn").on('click', function () {
        stopEdit();
        $("#datagridRight").datagrid('appendRow', {});
        var rows = $("#datagridRight").datagrid('getRows');
        var addRowIndex = $("#datagridRight").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#datagridRight").datagrid('selectRow', editIndex);
        $("#datagridRight").datagrid('beginEdit', editIndex);
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
     * 保存修改的内容
     * 基础字典的改变，势必会影响其他的统计查询
     * 基础字典的维护只能在基础数据维护的时候使用。
     */
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#datagridRight").datagrid("endEdit", editIndex);
        }

        var insertData = $("#datagridRight").datagrid("getChanges", "inserted");
        var updateDate = $("#datagridRight").datagrid("getChanges", "updated");
        var deleteDate = $("#datagridRight").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;


        if (beanChangeVo) {
            $.postJSON("/api/exp-coding-rule/merge", beanChangeVo, function (data, status) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#datagridRight').datagrid('load');
                $('#datagridRight').datagrid('clearChecked');
            }, function (data) {
                $.messager.alert('提示', data.responseJSON.errorMessage, "error");
            })
        }
    });
})