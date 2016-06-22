$(function () {
    var base_url = '/service/drug-buy-plan/'
    var orgId = '1'
        ,username = '采购员'
        , drugDicts = []  // 检索的药品字典数据
        , suppliers = []  // 供应商数据

    var planSelectIndex = 0;   // 购买计划表当前选择行索引

    // 执行状态数据
    var flagData = [
        {value:'1',label:'仓管员暂存'},
        {value:'2',label:'仓管员保存'},
        {value:'3',label:'采购员保存'},
        {value:'4',label:'审核员保存'},
        {value:'5',label:'采购员执行'},
        {value:'6',label:'已入库'}]

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#buyPlanTable').datagrid('getRows').length - 1
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'buyId', rowspan: null, colspan: 8})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'purchasePrice', rowspan: null, colspan: 3})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'stockSupplier', rowspan: null, colspan: 3})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'checkSupplier', rowspan: null, colspan: 6})
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
                {field: 'buyId', title: '采购单号', width: 80, align: "center"},
                {field: 'buyNo', title: '采购序号', width: 60, align: "center"},
                {field: 'flag', title: '执行标志', width: 80, align: "center",formatter:function(value){
                    return format(flagData,value)
                }},
                {field: 'drugCode', title: '药名', width: 220, halign: "center", align: "left", formatter: function (value) {
                    if (value == undefined || value == '') return ''
                    for (var i = 0, j = (drugDicts ? drugDicts.length : 0 ); i < j; i++) {
                        if (drugDicts[i].drugCode == value) {
                            value = drugDicts[i].drugName
                            break
                        }
                    }
                    return value;
                }},
                {field: 'packSpec', title: '包装规格', width: 60, align: "center"},
                {field: 'packUnit', title: '包装单位', width: 60, align: "center"},
                {field: 'drugForm', title: '剂型', width: 80, align: "center"},
                {field: 'wantNumber', title: '计划数量', width: 60, align: "center"},
                {field: 'count', title: '计划金额', width: 60, align: "center", formatter: function (value, row, index) {
                    if(row.id == -1) return value
                    return ((isNaN(row.wantNumber) ? 0 : +row.wantNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'purchasePrice', title: '进货价', width: 60, align: "center"},
                {field: 'storer', title: '仓管员', width: 70, align: "center"},
                {field: 'stockNumber', title: '采购数量', width: 60, align: "center"},
                {field: 'stockMoney', title: '采购金额', width: 60, align: "center",formatter:function(value,row,index){
                    if(row.id == -1) return value
                    return ((isNaN(row.stockNumber) ? 0 : +row.stockNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'stockSupplier', title: '采购供应商', width: 200, align: "center",formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return '<div style="text-align: left">'+suppliers[i].supplier+'</div>'
                        }
                    }
                    return ''
                }},
                {field: 'buyer', title: '采购员', width: 70, align: "center"},
                {field: 'checkNumber', title: '审核数量', width: 60, align: "center"},
                {field: 'checkMoney', title: '审核金额', width: 60, align: "center",formatter:function(value,row,index){
                    if(row.id == -1) return value
                    return ((isNaN(row.checkNumber) ? 0 : +row.checkNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'checkSupplier', title: '审核供应商', width: 200, halign: "center",align: "left", formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return suppliers[i].supplier
                        }
                    }
                    return ''
                }},
                {field: 'checker', title: '审核人', width: 70, align: "center"},
                {field: 'storage', title: '库房', width: 70, align: "center"},
                {field: 'supplier', title: '厂商', width: 200, halign: "center", align: "left"},
                {field: 'stockNum', title: '库存参考数', width: 90, align: "center"},
                {field: 'outStockNum', title: '出库参考数', width: 90, align: "center"}
            ]]
        });

    }

    /**
     * 格式化数据
     * @param arr 数组格式类似 [{value:'1',label:'测试'}...]
     * @param value
     * @returns {*}
     */
    var format = function(arr,value){
        if(arr){
            for(var i= 0,len=arr.length;i<len;i++){
                if(arr[i].value == value)
                    return arr[i].label
            }
        }
        return value

    }

    /**
     * 初始化按钮等
     */
    var initBtn = function () {
        $.get(base_url + 'getBuyId', {orgId: orgId}, function (res) {
            var _temporaryNo = []
            for (var i = 0; i < res.length; i++) {
                _temporaryNo.push({buyId: res[i][0], flag: format(flagData,res[i][1])})
            }
            $('#temporaryNo').combogrid({
                panelWidth:203,
                idField:'buyId',
                multiple : true,
                textField: 'buyId',
                editable: false,
                data : _temporaryNo,
                columns : [[
                    {field:'buyId',title:'单据号',width:100,align:"center"},
                    {field:'flag',title:'执行状态',width:100,align:"center"}
                ]],
                onSelect: function (index,row) {
                    planSelectIndex = 0
                    loadDrugBuyPlan(row.buyId)
                },
                onUnselect : function(index,row){
                    var _rows = $('#buyPlanTable').datagrid('getRows')
                    if(_rows.length > 0) $('#buyPlanTable').datagrid('deleteRow',_rows.length - 1)
                    var start = -1
                    for(var i=_rows.length-1;i > -1;i--){
                        if(_rows[i].buyId == row.buyId){
                            $('#buyPlanTable').datagrid('deleteRow',i)
                            if(start == -1) {
                                start = i
                            }
                            continue
                        }
                        if(start != -1) break
                    }
                    if($('#buyPlanTable').datagrid('getRows').length > 0) addCount()
                }
            })
        })

        $('#drug_gps').textbox({
            buttonText : '定位',
            width:130,
            onClickButton : function(){
                var value = $('#drug_gps').textbox('getText')
                var tds = $('#buyPlanTable').datagrid('getPanel').find('div.datagrid-body tr td[field="drugCode"]')
                for(var i=0;i<tds.length-1;i++){
                    if($('div',tds[i]).html() && $('div',tds[i]).html().indexOf(value.toUpperCase()) == 0){
                        $('#buyPlanTable').datagrid('selectRow',i)
                        return
                    }
                }
            }
        })

        $('#supplier_gps').textbox({
            buttonText : '定位',
            width:130,
            onClickButton : function(){
                var rows = $('#buyPlanTable').datagrid('getRows')
                var value = $('#supplier_gps').textbox('getText')
                for(var i=0;i<rows.length-1;i++){
                    if(rows[i].supplier && rows[i].supplier.indexOf(value.toUpperCase()) == 0){
                        $('#buyPlanTable').datagrid('selectRow',i)
                        return
                    }
                }
            }
        })

        $('#stock_gps').textbox({
            buttonText : '定位',
            width:130,
            onClickButton : function(){
                var value = $('#stock_gps').textbox('getText')
                var tds = $('#buyPlanTable').datagrid('getPanel').find('div.datagrid-body tr td[field="stockSupplier"]')
                for(var i=0;i<tds.length-1;i++){
                    if($('div div',tds[i]).html() && $('div div',tds[i]).html().indexOf(value.toUpperCase()) == 0){
                        $('#buyPlanTable').datagrid('selectRow',i)
                        return
                    }
                }
            }
        })

        $('#printButton').linkbutton({
            iconCls: 'icon-print',
            text: '打印',
            onClick: function(){
                alert()
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

    //添加合计行
    var addCount = function(doType){
        var rows = $('#buyPlanTable').datagrid('getRows')
        if (rows.length == 0) return
        var wantMoney = 0
        var stockMoney = 0
        var checkMoney = 0
        for(var i= 0,j = rows.length;i<j;i++){
            wantMoney += +((isNaN(rows[i].wantNumber) ? 0 : +rows[i].wantNumber) * (isNaN(rows[i].purchasePrice) ? 0 : +rows[i].purchasePrice)).toFixed(1)
            stockMoney += +((isNaN(rows[i].stockNumber) ? 0 : +rows[i].stockNumber) * (isNaN(rows[i].purchasePrice) ? 0 : +rows[i].purchasePrice)).toFixed(1)
            checkMoney += +((isNaN(rows[i].checkNumber) ? 0 : +rows[i].checkNumber) * (isNaN(rows[i].purchasePrice) ? 0 : +rows[i].purchasePrice)).toFixed(1)
        }
        var countRecord = {
            id : -1,
            count: wantMoney,
            stockMoney: stockMoney,
            checkMoney: checkMoney
        }
        $('#buyPlanTable').datagrid('appendRow', countRecord)
        mergeLastCells()
    }

    /**
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function (buyId, flag) {
        $.get(base_url + 'findList', {buyId: buyId, orgId: orgId}, function (res) {
            if(res && res.length > 0){
                var _rows = $('#buyPlanTable').datagrid('getRows')
                var _len = _rows.length
                if(_len > 0) {
                    _len --
                    _rows.splice(_len ,1)
                    for(var i= 0;i<_len;i++){
                        if(_rows[i].buyId < buyId){
                            res = _rows.slice(0,i).concat(res,_rows.slice(i))
                            break
                        }
                        if(i == _len -1)
                            res = _rows.concat(res)
                    }
                }
                $('#buyPlanTable').datagrid('loadData', res)
                $('#buyPlanTable').datagrid('selectRow', planSelectIndex)
                addCount()
            }
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

    var init = function () {
        loadDrugDict(orgId)
        loadSupplier(orgId,'供应商')

        initBuyPlanTable()
        initBtn()
    }

    init()
})
