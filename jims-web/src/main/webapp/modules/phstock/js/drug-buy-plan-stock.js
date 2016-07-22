$(function () {
    var base_url = '/service/drug-buy-plan/'
    var orgId = config.org_Id

    var planSelectIndex = 0;   // 购买计划表当前选择行索引


    var specUnits = [];//规格单位字典
    $.get("/service/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });

    var drugToxi = [];//毒理属性字典
    $.get( "/service/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
        drugToxi = data;
    });

    var drugFormDict = [];//剂型字典
    $.get("/service/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    })

    //入库子单位组件
    $('#subStorage').combobox( {
        valueField:'subStorageCode'
        ,textField:'subStorage'
        ,url: parent.basePath + '/drug-storage-dept/findSubList'
        ,queryParams: {orgId : orgId,storageCode : config.currentStorage}
        ,method:'get'
        ,width:140
        ,editable: false
        ,onSelect:function(record){
            var _prefix = record['importNoPrefix']
            var _suffix = record['importNoAva']
            if(_prefix == undefined) _prefix = ''
            if(_suffix == undefined) _suffix = ''
            var _len = (_prefix + _suffix).length
            var _v = _len > 9 ? (_prefix + _suffix).substr(0,10) : _prefix + '0000000000'.substr(_len - 10) + _suffix
            $('#importDocument').textbox('setValue',_v)
        }
    })
    $('#temporaryNo').combobox({
        valueField: '0',
        textField: '0',
        editable: false,
        url:base_url+'getBuyId?flag=5&orgId='+orgId+'&storage='+currentStorage,
        method:'get',
        mode:'remote',
        onSelect: function (record) {
            planSelectIndex = 0
            if($('#buyPlanTable').datagrid('getRows').length > 0) {
                var _t = $.messager.defaults;
                $.messager.defaults = { ok: "保存", cancel: "不保存", width: '300' };
                $.messager.confirm('提示','是否保存当前页面数据？',function(r){
                    if(r){
                        inStock();
                    } else {
                        loadDrugBuyPlan(record[0], '5')
                    }
                })
                $.messager.defaults = _t;
            } else {
                loadDrugBuyPlan(record[0], '5')
            }
        }
    })
    $('#inStockButton').linkbutton({
        iconCls: 'icon-build',
        text: '入库',
        onClick: inStock
    })
    $('#flushButton').linkbutton({
        iconCls: 'icon-reload',
        text: '刷新',
        onClick: function () {
            window.location.reload();
        }
    })

    //初始化药品购买计划表
    $("#buyPlanTable").datagrid({
        fit: true,
        border: 0,
        striped: true,
        singleSelect: true,
        remoteSort: false,
        idField: 'id',
        toolbar: '#tbn',
        columns: [[
            {field: 'id', title: '编号', hidden: true},
            {field: 'buyNo', title: '采购序号', width: 60, align: "center", formatter: function (value) {
                if (value == '审核金额合计') return '<div style="text-align:right">' + value + '：　　　</div>'
                return value
            }},
            {field: 'drugName', title: '药名', width: 220, halign: "center", align: "left"},
            {field: 'supplier', title: '生成厂家', width: 200, halign: "center", align: "left"},
            {field: 'executedNumber', title: '执行数量', width: 60, align: "center"},
            {field: 'purchasePrice', title: '进货价', width: 60, align: "center"}, {
                title: "批号",
                field: "batchNo",
                width: '70px',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        missingMessage: '批号不能为空'
                    }
                }
            }, {
                title: "扣率",
                field: "discount",
                width: '60px',
                align: 'center',editor:{
                    type : 'numberbox',
                    options:{
                        missingMessage:'扣率不能为空',
                        min : 1,
                        max : 100,
                        precision : 0
                    }
                }
            }, {
                title: "有效期",
                field: "expireDate",
                width: '80px',
                align: 'center',
                editor : {
                    type: 'datebox',
                    options: {
                        editable: false
                    }
                },formatter: function(value){
                    return parent.formatDatebox(value)
                }
            },
            {field: 'monthUsed', title: '月消耗量', width: 60, align: "center"},
            {field: 'stockNum', title: '库存参考数', width: 90, align: "center"},
            {field: 'packSpec', title: '包装规格', width: 60, align: "center"},
            {field: 'packUnit', title: '包装单位', width: 60, align: "center",
                formatter:function(value,row,index){
                    var unitsName = value;
                    $.each(specUnits, function (index,item) {
                        if(item.value == value){
                            unitsName =  item.label;
                        }
                    });
                    return unitsName;
                }},
            {field: 'drugForm', title: '剂型', width: 80, align: "center",
                formatter:function(value,row,index){
                    var label=value;
                    $.each(drugFormDict, function (index,item) {
                        if (item.value == value){
                            label =   item.label;
                        }
                    });
                    return label;
                }}
        ]],
        onClickRow: function (index) {
            $(this).datagrid('endEdit',planSelectIndex)
            $(this).datagrid('beginEdit',index)
            planSelectIndex=index
        }
    });

    /**
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function (buyId, flag) {
        $.get(base_url + 'findList', {buyId: buyId, orgId: orgId, flag: flag}, function (res) {
            for(var i=0;i<res.length;i++){
                res[i].expireDate = parent.formatDatebox(new Date());
                res[i].batchNo = 'X';
                res[i].discount = '100';
            }
            $('#buyPlanTable').datagrid('loadData', res)
            $('#buyPlanTable').datagrid('selectRow', planSelectIndex);
            //$('#buyPlanTable').datagrid('beginEdit', planSelectIndex);
        })
    }

    //保存入库数据
    function inStock() {
        if($('#subStorage').combobox('getValue') == ''){
            $.messager.alert('警告','请选择入库子库房！','warning');
            return;
        }
        var rows = $('#buyPlanTable').datagrid('getRows')
        if (rows.length == 0){
            $.messager.alert('警告','没有需要入库的数据！','warning')
            return
        }
        $('#buyPlanTable').datagrid('endEdit',planSelectIndex);
        for(var i=0;i<rows.length;i++){
            var row = rows[i];
            if(!row.batchNo  || !row.expireDate  || !row.discount){
                $.messager.alert('警告','请将表单填写完整！','warning');
                $('#buyPlanTable').datagrid('beginEdit',i);
                $('#buyPlanTable').datagrid('selectRow',i);
                planSelectIndex=i;
                return;
            }
            row.expireDate = parent.parseToDate(row.expireDate)
            row.importDocument = $('#importDocument').textbox('getValue');
            row.subStorage = $('#subStorage').combobox('getValue');
            row.flag = '6';
        }
        parent.$.postJSON(base_url + 'drugInStock', JSON.stringify(rows), function (res) {
            if (res) {
                $.messager.alert('入库', '入库成功', 'info', function () {
                    window.location.reload()
                })
            } else {
                $.messager.alert('入库', '入库失败', 'error')
            }
        })
    }
})
