$(function () {
    var base_url = '/service/drug-buy-plan/'
    var orgId = parent.config.org_Id
        ,username = '采购员'
        , drugDicts = []  // 检索的药品字典数据
        , suppliers = []  // 供应商数据

    var planSelectIndex = 0;   // 购买计划表当前选择行索引

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#buyPlanTable').datagrid('getRows').length - 1
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'buyNo', rowspan: null, colspan: 12})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'checkMoney', rowspan: null, colspan: 8})
    }

    /**
     * 初始化药品购买计划表
     */
    var initBuyPlanTable = function () {
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
                {field: 'drugCode', title: '药名', width: 220, align: "center", formatter: function (value) {
                    if (value == undefined || value == '') return ''
                    for (var i = 0, j = (drugDicts ? drugDicts.length : 0 ); i < j; i++) {
                        if (drugDicts[i].drugCode == value) {
                            value = drugDicts[i].drugName
                            break
                        }
                    }
                    return '<div style="text-align:left">' + value + '</div>';
                }},
                {field: 'checkSupplier', title: '审核供应商', width: 200, align: "center", formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return '<div style="text-align: left">'+suppliers[i].supplier+'</div>'
                        }
                    }
                    return ''
                }},
                {field: 'supplier', title: '生成厂家', width: 200, align: "center",
                    formatter: function (value) {
                        return '<div style="text-align:left">' + value + '</div>';
                    }
                },
                {field: 'checkNumber', title: '审核数量', width: 60, align: "center"},
                {field: 'checker', title: '审核人', width: 70, align: "center"},
                {field: 'stockSupplier', title: '供应商', width: 200, align: "center",formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return '<div style="text-align: left">'+suppliers[i].supplier+'</div>'
                        }
                    }
                    return ''
                }},
                {field: 'storer', title: '仓管员', width: 70, align: "center"},
                {field: 'purchasePrice', title: '进货价', width: 60, align: "center"},
                {field: 'monthUsed', title: '月消耗量', width: 60, align: "center"},
                {field: 'stockNum', title: '库存参考数', width: 90, align: "center"},
                {field: 'packSpec', title: '包装规格', width: 60, align: "center"},
                {field: 'checkMoney', title: '审核金额', width: 60, align: "center",formatter:function(value,row,index){
                    if(row.buyNo == '审核金额合计') return '<div style="text-align:left">　　'+value+'</div>'
                    return ((isNaN(row.checkNumber) ? 0 : +row.checkNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'buyer', title: '采购员', width: 70, align: "center"},
                {field: 'stockMoney', title: '采购金额', width: 60, align: "center",formatter:function(value,row,index){
                    return ((isNaN(row.stockNumber) ? 0 : +row.stockNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'count', title: '计划金额', width: 60, align: "center", formatter: function (value, row, index) {
                    return ((isNaN(row.wantNumber) ? 0 : +row.wantNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'stockNumber', title: '采购数量', width: 60, align: "center"},
                {field: 'packUnit', title: '包装单位', width: 60, align: "center"},
                {field: 'wantNumber', title: '计划数量', width: 60, align: "center"},
                {field: 'drugForm', title: '剂型', width: 80, align: "center"}
            ]],
            onLoadSuccess: function (data) {
                var rows = $(this).datagrid('getRows')
                if (rows.length == 0) return
                var checkMoney = 0
                for(var i= 0,j = rows.length;i<j;i++){
                    checkMoney += +((isNaN(rows[i].checkNumber) ? 0 : +rows[i].checkNumber) * (isNaN(rows[i].purchasePrice) ? 0 : +rows[i].purchasePrice)).toFixed(1)
                }
                var countRecord = {
                    buyNo: '审核金额合计',
                    checkMoney: checkMoney
                }
                $(this).datagrid('appendRow', countRecord)
                mergeLastCells()
            }
        });

    }

    /**
     * 初始化按钮等
     */
    var initBtn = function () {
        $('#temporaryNo').combobox({
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect: function (record) {
                planSelectIndex = 0
                loadDrugBuyPlan(record.value, '4')
            }
        })
        $.get(base_url + 'getBuyId', {flag: '4', orgId: orgId,buyer:username}, function (res) {
            var _temporaryNo = []
            for (var i = 0; i < res.length; i++) {
                _temporaryNo.push({value: res[i][0], label: res[i][0]})
            }
            $('#temporaryNo').combobox('loadData', _temporaryNo)
        })

        $('#saveButton').linkbutton({
            iconCls: 'icon-cog',
            text: '执行',
            onClick: saveData
        })
        $('#saveAndInStockButton').linkbutton({
            iconCls: 'icon-build',
            text: '执行并入库',
            onClick: function(){
                saveData('in')
            }
        })
        $('#flushButton').linkbutton({
            iconCls: 'icon-reload',
            text: '刷新',
            onClick: function () {
                window.location.reload();
            }
        })
        $('#closeButton').linkbutton({
            iconCls: 'icon-cancel',
            text: '关闭',
            onClick: function () {
                parent.location.href = parent.getRootPath() + '/modules/clinic/index.html'
            }
        })
    }

    /**
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function (buyId, flag) {
        $.get(base_url + 'findList', {buyId: buyId, orgId: orgId, flag: flag}, function (res) {
            for(var i=0;i<res.length;i++){
                res[i].flag = '5'
            }
            $('#buyPlanTable').datagrid('loadData', res)
            $('#buyPlanTable').datagrid('selectRow', planSelectIndex)
        })
    }

    /**
     * 加载药品字典函数
     * @param orgId 机构ID
     */
    var loadDrugDict = function (orgId) {
        $.ajaxAsync('/service/drug-price/findDrugDict', {orgId: orgId}, function (res) {
            drugDicts = res
        }, 'GET', false)
    }

    /**
     * 加载厂商
     * @param orgId
     * @param supplierClass
     */
    var loadSupplier = function(orgId,supplierClass){
        $.ajaxAsync('/service/drug-supplier-catalog/list-supplier-type', {
            orgId: orgId,
            supplierClass: supplierClass
        }, function (res) {
            suppliers = res
        }, 'GET', false)
    }

    /**
     * 保存执行数据
     */
    var saveData = function (doFlag) {
        var _allData = $('#buyPlanTable').datagrid('getRows')
        if (_allData.length == 0){
            $.messager.alert('警告','没有需要执行的数据！','warning')
            return
        }

        var handleData = [[],[]] // handleData[0] 保存的数据,handleData[1] 删除的数据
        _allData.splice(_allData.length-1 ,1)
        handleData[0] = _allData
        parent.$.postJSON(base_url + 'saveBatch', JSON.stringify(handleData), function (res) {
            if (res.code = '0')
                $.messager.alert('成功', '执行成功', 'info', function () {
                    if(doFlag == 'in'){
                        inStock()
                    } else {
                        window.location.reload()
                    }
                })
        })
    }
    // 入库
    var inStock = function(){
        $.messager.alert('成功', '入库成功', 'info', function () {
            window.location.reload()
        })
    }

    var init = function () {
        loadDrugDict(orgId)
        loadSupplier(orgId,'供应商')

        initBuyPlanTable()
        initBtn()
    }

    init()
})
