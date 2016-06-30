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
    var selectedPriceListRow;
    var selectedSpec ;
    var drugNameDictList = [];//药品名称列表
    var drugSupplierDict  = [];//药品生产商列表
    var tenderPriceClass  = [];//价格分类列表
    var inpRcptFeeDict  = [];//住院收据列表
    var outpRcptFeeDict  = [];//门诊收据列表
    var reckItemClassDict  = [];//核算分类列表
    var tallySubjectDict  = [];//会计科目列表
    var mrFeeClassDict  = [];//病案首页分类
    var specUnit=[];
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
    $.ajaxAsync( basePath  + "/dict/findListByType?type=spec_unit", function (data) {
        specUnit = data;
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
                var priceList=[];
                for(var i=0;i<data.length;i++){
                    priceList[i]=data[i];
                    priceList[i].edic=1;
                }

                $("#datagridRight").datagrid("loadData",priceList);
            })  ;


        }

    });
    $("#datagridRight").datagrid({
        title: '药品价格维护',
        fit: true,//让#dg数据创铺满父类容器
        idField: 'id',
        toolbar: '#datagridRightTb',
        method:'get',
        singleSelect: true,
        columns: [[{
            title:'id',
            field:'id',
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
            title: '可编辑状态',
            field: 'edic',
            width: '6%',
            hidden:true
        },{
            title: '包装数量',
            field: 'amountPerPackage',
            width: '6%',
            editor: {
                type: 'textbox',options: {
                onChange: function (newValue, oldValue) {
                    var row=$('#datagridRight').datagrid('getSelected');
                    console.log(row);
                    if (editIndex != undefined){
                        var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
                        console.log(spec)
                        if (selectedSpec == undefined){
                            selectedSpec =  $(spec.target).textbox("getValue");

                        }
                        if (newValue != 1 ){
                            $(spec.target).textbox("setValue",newValue +"*"+row.minSpec);
                        }else{

                            $(spec.target).textbox("setValue",row.minSpec)
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
                type: 'textbox', options: {
                    valueField: 'drugSpec',
                    textField: 'drugSpec',
                    editable:false,
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
            },formatter:function(value,row,index){
                var label = value;
                $.each(specUnit, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '厂家',
            field: 'firmId',
            width: '8%',
            editor: {
                type: 'combobox', options: {
                    valueField: 'id',
                    textField: 'supplierId',
                    method: 'GET',
                    url: basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商"
                }
            },formatter: function (value,row,index) {
                var supplierId = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.id == value){
                        supplierId  =  item.supplierId;
                    }
                });
                return  supplierId;
            }
        },{
            title: '开始日期',
            field: 'startDate',
            width: '10%',
            editor: {
                type: 'datetimebox', options: {
                    onChange: function (newValue, oldValue) {
                        var priceListStartDate = new Date(selectedPriceListRow);

                    }
                }
            },
            formatter: function (value,row,index) {
                row.startDate = new Date(value);
                return value;
            }
        },{
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
            field: 'minSpec',
            editor: {
                type: 'textbox'
            }
        }, {
            title: '最小单位',
            field: 'minUnits',
            width: '6%',
            editor: {
                type: 'textbox'
            }
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
            if(row.edic==1){
                stopEdit();
            }else{
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
                var spec =  $("#datagridRight").datagrid("getEditor",{index:editIndex,field:"drugSpec"});
                $(spec.target).textbox("loadData",drugNameSpecList);
            }
        }
    });

    //弹出框选择药品规格
    $("#drugSpecDialog").dialog({
        title:'选择规格',
        closed:true,
        catch:false,
        modal:true,
        onOpen:function(){
            $("#drugSpecList").datagrid('loadData',drugNameSpecList);
            $("#drugSpecList").datagrid('selectRow',0)
        }
    })

    var drugSpecSelect='';
    $("#drugSpecList").datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        columns: [[{
                title: '药品编码',
                field: 'drugCode',
                align: 'center',
                width: '25%'
                },{
                title: '药品名称',
                field: 'drugName',
                align: 'center',
                width: '25%'
            },{
                title: '药品剂量',
                field: 'drugSpec',
                align: 'center',
                width: '25%'
            },{
                title: '单位',
                field: 'units',
                align: 'center',
                width: '25%'
            }
            ]
        ],
        onClickRow: function (index, row) {
            drugSpecSelect='';
            drugSpecSelect=row.drugSpec;
            $("#datagridRight").datagrid('appendRow', {orgId:parent.config.org_Id,drugCode:row.drugCode,minSpec:row.drugSpec,minUnits:drugNameSpecList[0].units,classOnInpRcpt:"J" ,classOnOutpRcpt:"J",	classOnReckoning:"P01",subjCode:"",classOnMr:""	});
            var rows = $("#datagridRight").datagrid('getRows');
            var addRowIndex = $("#datagridRight").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#datagridRight").datagrid('selectRow', editIndex);
            $("#datagridRight").datagrid('beginEdit', editIndex);
            $("#drugSpecDialog").dialog('close');
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
            var url = basePath + "/drug-price/listDrugNameDictByClassCode?classCode="+ rowData.classCode;
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
        $("#drugSpecDialog").dialog('open');
    });
    //删除
    $("#delBtn").on('click', function () {
        var row = $("#datagridRight").datagrid('getSelected');
        if (row.edic==1) {
            $.messager.alert('系统提示', "价格不允许删除，只能停用", 'info');
        } else {
            var rowIndex = $("#datagridRight").datagrid('getRowIndex', row);
            $("#datagridRight").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        }
    });

    $("#stopBtn").on('click', function () {
        var row = $("#datagridRight").datagrid('getSelected');
        $.messager.confirm('Confirm','是否确定停价该药品？',function(r){
             if (r){
                 $.postJSON(basePath + "/drug-price/stop",row.id, function (data) {
                     $.messager.alert("系统提示", "停价成功", "info");
                     $('#datagridRight').datagrid('loadData', { total: 0, rows: [] });
                 });
                      }
              });
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
        for(var i=0;i<insertData.length;i++){
                delete insertData[i].edic;
        }

        for(var i=0;i<updateDate.length;i++){
            delete updateDate[i].edic;
        }
        for(var i=0;i<deleteDate.length;i++){
                delete deleteDate[i].edic;
        }

        var drugPriceList = {};
        drugPriceList.inserted = insertData;
        drugPriceList.deleted = deleteDate;
        drugPriceList.updated = updateDate;

        console.log(drugPriceList);
       // console.log(JSON.stringify(drugPriceList));
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