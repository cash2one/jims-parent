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
    var drugNameDictList = [];//药品名称列表
    var drugSupplierDict  = [];//药品生产商列表
    var tenderPriceClass  = [];//价格分类列表
    var inpRcptFeeDict  = [];//住院收据列表
    var outpRcptFeeDict  = [];//门诊收据列表
    var reckItemClassDict  = [];//核算分类列表
    var tallySubjectDict  = [];//会计科目列表
    var mrFeeClassDict  = [];//病案首页分类
    $.ajaxAsync(basePath + "/drug-price/findDrugNameDictList", function (data) {
        drugNameDictList = data;
    });
    $.ajaxAsync( basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商", function (data) {
        drugSupplierDict = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=TENDER_PRICE_CLASS", function (data) {
        tenderPriceClass = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=INP_RCPT_FEE_DICT", function (data) {
        inpRcptFeeDict = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=OUTP_RCPT_FEE_DICT", function (data) {
        outpRcptFeeDict = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=RECK_ITEM_CLASS_DICT", function (data) {
        reckItemClassDict = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=TALLY_SUBJECT_DICT", function (data) {
        tallySubjectDict = data;
    });
    $.ajaxAsync( basePath  + "/dict/findListByType?type=MR_FEE_CLASS_DICT", function (data) {
        mrFeeClassDict = data;
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

                });
                $("#datagridRight").datagrid("loadData",data);
            })  ;

            console.log(drugNameSpecList);
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
                type: 'textbox', options: {
                }
            },
            formatter: function (value,row,index) {
                var drugName = value;
                $.each(drugNameDictList, function (index,item) {
                    if(item.drugCode == value){
                        drugName =  item.drugName;
                    }
                });
                return drugName;
            }
        }, {
            title: '机构id',
            field: 'orgId',
            width: '6%',
            hidden:true
        },{
            title: '包装数量',
            field: 'amountPerPackage',
            width: '6%',
            editor: {
                type: 'textbox',options: {
                onChange: function (newValue, oldValue) {
                    if (editIndex != undefined){
                        var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
                        if (selectedSpec == undefined){
                            selectedSpec =  $(spec.target).combobox("getValue");
                        }
                        if (newValue != 1 ){
                            $(spec.target).combobox("setValue",selectedSpec +"*"+newValue);
                        }else{
                            $(spec.target).combobox("setValue",selectedSpec)
                        }
                    }
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
                    valueField: 'supplierCode',
                    textField: 'supplierId',
                    method: 'GET',
                    url: basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商"
                }
            },formatter: function (value,row,index) {
                var supplierId = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.supplierCode == value){
                        supplierId  =  item.supplierId;
                    }
                });
                return  supplierId;
            }
        },{
            title: '开始日期',
            field: 'startDate',
            hidden:true
        },{
            title: '停价日期',
            field: 'stopDate',
            hidden:true,
            formatter: function (value,row,index) {
             if (value){
                 row.stopDate =  new Date(value);
             }else{
                 row.stopDate = null;

             }

            }
        },{
            title: '停价',
            field: 'stopDateCheck',
            styler: function () {
                return "text-align: center"
            },
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            },
            formatter: function (value,row,index) {
                console.log("value" + value);
                if (row.stopDate && value == null){
                    row.stopDateCheck = 1;
                    return '是';
                }else if (value == 1){
                    row.stopDateCheck = 1;
                    row.stopDate = new Date();
                    return '是';
                }else{
                    row.stopDate = null;
                    row.stopDateCheck = 0;
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
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(tenderPriceClass, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url:basePath  + "/dict/findListByType?type=INP_RCPT_FEE_DICT"
                }
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(inpRcptFeeDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '门诊收据分类',
            field: 'classOnOutpRcpt',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=OUTP_RCPT_FEE_DICT"
                }
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(outpRcptFeeDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '核算分类',
            field: 'classOnReckoning',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=RECK_ITEM_CLASS_DICT"
                }
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(reckItemClassDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '会计科目',
            field: 'subjCode',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=TALLY_SUBJECT_DICT"
                }
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(tallySubjectDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '病案首页分类',
            field: 'classOnMr',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=MR_FEE_CLASS_DICT"
                }
            },
            formatter:function(value,row,index){
                var label = value;
                $.each(mrFeeClassDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
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
            var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
            $(spec.target).combobox("loadData",drugNameSpecList);

            if (row.startDate){
                row.startDate = new Date(row.startDate);
            }else{
                row.startDate = new Date();
            }


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

        stopEdit();                                                                                                      //可直接写默认值
        $("#datagridRight").datagrid('appendRow', {orgId:parent.config.org_Id,drugCode:drugNameSpecList[0].drugCode,minSpec:"",minUnits:"",classOnInpRcpt:"J" ,classOnOutpRcpt:"J",	classOnReckoning:"P01",subjCode:"",classOnMr:""	});

        var rows = $("#datagridRight").datagrid('getRows');

        var addRowIndex = $("#datagridRight").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#datagridRight").datagrid('selectRow', editIndex);
        $("#datagridRight").datagrid('beginEdit', editIndex);


        var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
        $(spec.target).combobox("loadData",drugNameSpecList);


    });
    //删除
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
        //console.log(JSON.stringify(drugPriceList));
        if (drugPriceList) {
            $.postJSON(basePath + "/drug-price/save", JSON.stringify(drugPriceList), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#datagridRight').datagrid('loadData', { total: 0, rows: [] });
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#datagridRight').datagrid('loadData', { total: 0, rows: [] });
            })
        }
    });
    
    $("#addPriceBtn").on("click", function () {

        var row = $("#datagridRight").datagrid("getSelected");
        if  (!row){
            $.messager.alert("提示","请选择大包装药品",'info');
            return;
        }

        if (row.amountPerPackage == 1){
            $.messager.alert("提示","该药品已经是基本价格",'info');
            return;
        }
        stopEdit();
        var newSpec = row.drugSpec.substring(0,row.drugSpec.length - row.amountPerPackage.length - 1);
        var tradePrice = parseFloat(row.tradePrice  /  row.amountPerPackage).toFixed(4);
        var retailPrice =parseFloat(row.retailPrice  /  row.amountPerPackage).toFixed(4);
        $("#datagridRight").datagrid("appendRow",{drugCode:row.drugCode,amountPerPackage:'1',drugSpec:newSpec,units:row.units,
            firmId:row.firmId,startDate:new Date(),tradePrice:tradePrice,retailPrice:retailPrice,priceClass:row.priceClass,
            passNo:row.passNo,gmp:row.gmp,orgId:parent.config.org_Id,
            minSpec:row.minSpec,minUnits:row.minUnits,classOnInpRcpt:row.classOnInpRcpt ,
            classOnOutpRcpt:row.classOnOutpRcpt,	classOnReckoning:row.classOnReckoning,
            subjCode:row.subjCode,classOnMr:row.classOnMr,memos:row.memos	});
        var rows = $("#datagridRight").datagrid('getRows');

        var addRowIndex = $("#datagridRight").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#datagridRight").datagrid('selectRow', editIndex);
        $("#datagridRight").datagrid('beginEdit', editIndex);


        var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
        $(spec.target).combobox("loadData",drugNameSpecList);

    })
});