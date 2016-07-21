/**
 * Created by wei on 2016/7/12.
 */
$(function () {
    var editIndex;
    var orgId = parent.config.org_Id;
    var documentNo;
    var oldSubStorage;
    var inserted = [];
    var updated = [];
    var deleted = [];
    var storageCode = parent.config.currentStorage
    var drugSupplierDict  = [];
    var drugSupplier  = [];
    var person=config.userName;


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
    $.ajaxAsync( basePath + '/drug-supplier-catalog/findListWithFilter?orgId=' + orgId, function (data) {
        drugSupplierDict = data;
    });
    $.ajaxAsync(basePath + '/drug-storage-dept/findListByLevel?orgId='+orgId,function(data){
        console.log(data)
        drugSupplier = data;
    })
    var drugFormDict = [];//剂型字典
    var drugFormDictPromise =  $.ajaxAsync( basePath  + "/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    });
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drug-storage").datagrid('endEdit', editIndex);
            $("#drug-sub-storage").datagrid('endEdit', editIndex);
            editIndex == undefined;
        }
    }

    $('#storageDept').combogrid({
        delay: 150,
        width: '150px',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/drug-storage-dept/findSubList?orgId='+orgId+'&storageCode'+ storageCode,
        idField: 'subStorageCode',
        textField: "subStorage",
        columns: [[
            {field: 'subStorage', width: "150px", sortable: true}
        ]]
    });
    $('#supplier').combogrid({
        editable: false,
        width: '150px',
        idField: 'id',
        textField: "supplier",
        method: 'GET',
        url:basePath + '/drug-supplier-catalog/list-supplier?orgId=' + orgId ,
        columns: [[
            {field: 'supplier', width: "150px", sortable: true}
        ]]
    });




    $("#drug-storage").datagrid({
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        toolbar: '#tb',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        singleSelect: false,
        columns: [[{
            title: 'id',
            field: 'id',
            hidden:true
            },{
                title: '供应商',
                field: 'supplier',
                width: '20%',
                align: 'center',
                 formatter:function(value,row,index){
                 var supplierName = value;
                 $.each(drugSupplierDict, function (index,item) {
                         if(item.id == value){
                             supplierName =  item.supplier;
                         }
                     });
                 $.each(drugSupplier, function (index,item) {
                     if(item.storageCode == value){
                         supplierName =  item.storageName;
                        }
                     });
                 return supplierName;
             }
            }, {
                title: '上账标志',
                field: 'flag',
                width: '10%',
                align: 'center',
                formatter: function (value,row,index) {
                    if(value==1){
                        return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px"  checked="checked">'
                    }else{
                        return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px" >'
                    }
                }
            }, {
                title: '单据号',
                field: 'documentNo',
                width: '15%',
                align: 'center'
            }, {
                title: '入库日期',
                field: 'importDate',
                width: '10%',
                align: 'center',
                formatter:function(row){
                    if(row != null){
                        var date = new Date(+row+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
                        return date
                    }else{
                        return ''
                    }

                }

            }, {
                title: '凭证号',
                field: 'voucherNo',
                width: '15%',
                align: 'center'

            }, {
                title: '上账日期',
                field: 'tallyDate',
                width: '20%',
                align: 'center'
            },{
                title: '上账人',
                field: 'tallyOperator',
                width: '10%',
                align: 'center'
            }
        ]],
        onClickCell: function (rowIndex, field, value) {
            if(field == 'flag'){
                var rows =  $(this).datagrid("getRows");
                var row = rows[rowIndex];
                if (value == 0 ||value==null){
                    row[field] = 1;
                }else{
                    row[field] = 0;
                }
            }
        },
        onClickRow: function(index,row){
            stopEdit();

            documentNo = row.documentNo;
            $.get(basePath + '/drug-import-master/find-detail-list', {documentNo:row.documentNo}, function (data) {
                console.log(data)
                oldSubStorage = [];
                oldSubStorage = data;
                $("#drug-sub-storage").datagrid('loadData', data);
                });
        }
    });


    $("#importMaster").on('click', function () {
        var storageDept = $("#storageDept").combogrid("getValue");
        var supplier = $("#supplier").combogrid("getValue");
        var startImportDate = $("#startImportDate").datebox("getValue");
        var stopImportDate = $("#stopImportDate").datebox("getValue");
        var tallyDate = $("#tallyDate").datetimebox("getValue");

        $.get(basePath + '/drug-import-master/find-list',
            {orgId:orgId,subStorage:storageDept,supplier:supplier,startImportDate:startImportDate,stopImportDate:stopImportDate,storage:storageCode}, function (data) {
            $("#drug-storage").datagrid('loadData', data);
        });

    });




    $("#drug-sub-storage").datagrid({
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        toolbar: '#stb',
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        singleSelect: false,
        columns: [[{
            title: '药品名称',
            field: 'drugName',
            align: 'center',
            width: '5%'
        },{
            title: '剂型',
            field: 'drugForm',
            align: 'center',
            align: 'center',
            width: '5%',
            formatter:function(value,row,index){
                var label;
                $.each(drugFormDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        },{
            title: '规格',
            field: 'drugSpec',
            align: 'center',
            width: '5%'
        },{
            title: '单位',
            field: 'units',
            align: 'center',
            width: '5%'
        },{
            title: '数量',
            field: 'quantity',
            align: 'center',
            width: '5%'
        },{
            title: '进价',
            field: 'purchasePrice',
            align: 'center',
            width: '5%'
        },{
            title: '进价金额',
            field: 'purchasePriceCount',
            align: 'center',
            width: '5%',
            formatter:function(value,row){
                return row.quantity*row.purchasePrice;
            }
        },{
            title: '零价',
            field: 'retailPrice',
            align: 'center',
            width: '5%'
        },{
            title: '零价金额',
            field: 'retailPriceCount',
            align: 'center',
            width: '5%',
            formatter:function(value,row){
                return row.quantity*row.retailPrice;
            }
        },{
            title: '发票号',
            field: 'invoiceNo',
            align: 'center',
            width: '10%'
        },{
            title: '发票日期',
            field: 'invoiceDate',
            align: 'center',
            width: '10%',
            formatter:function(row){
              if(row != null){
                  var date = new Date(+row+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
                  return date
              }else{
                  return ''
              }
            }
        },{
            title: '发票标志',
            field: 'invoiceSign',
            align: 'center',
            width: '5%'
        },{
            title: '厂家',
            field: 'firmId',
            align: 'center',
            width: '15%',
            formatter:function(value,row,index){
                var supplierName = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.id == value){
                        supplierName =  item.supplier;
                    }
                });
                return supplierName;
            }
        },{
            title: '批号',
            field: 'batchNo',
            align: 'center',
            width: '10%'
        },{
            title: '单据号',
            field: 'documentNo',
            align: 'center',
            width: '10%'
        },{
            title: '当前库存量',
            field: 'currentQuantity',
            align: 'center',
            width: '5%'
        }]],
        onClickRow: function(index,row){
            stopEdit();
            $(this).datagrid('beginEdit',index);
            editIndex = index;
        }
    });
    //上账
    var list=[];//上账列
    $("#allPut").on('click',function(){
        list=[]
        var rows= $("#drug-storage").datagrid("getRows");
        var tallyDate = $("#tallyDate").datetimebox("getValue");
        console.log(rows);

        for(var i=0;i<rows.length;i++){
            if(rows[i].flag==1){
                rows[i].voucherNo="R"+formatterDate(tallyDate);
                rows[i].tallyOperator=person;
                rows[i].tallyDate=tallyDate;
                list.push(rows[i]);
            }
        }
        $("#drug-storage").datagrid("loadData",rows);

    });


    $("#saveStorageBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#drug-sub-storage").datagrid('endEdit', editIndex);
            $("#drug-storage").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }

        var drugNameDictChangeVo = {};
        drugNameDictChangeVo.updated = list;
        console.log(drugNameDictChangeVo)
        $.postJSON(basePath + "/drug-import-master/save", JSON.stringify(drugNameDictChangeVo), function (data) {
            $.messager.alert("系统提示", "保存成功", "info");
            $('#drug-storage').datagrid('loadData', { total: 0, rows: [] });
            $('#drug-sub-storage').datagrid('loadData', { total: 0, rows: [] });
        }, function (data) {
            $.messager.alert("系统提示", "保存失败", "error");
            $('#drug-storage').datagrid('loadData', { total: 0, rows: [] });
            $('#drug-sub-storage').datagrid('loadData', { total: 0, rows: [] });
        })


    });


    function formatterDate(val, row) {
        if (val != null) {
            var date = new Date(val);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            var h = date.getHours();
            var mm = date.getMinutes();
            var s = date.getSeconds();
            var dateTime = y  + (m < 10 ? ("0" + m) : m) + (d < 10 ? ("0" + d) : d) ;
            return dateTime;
        }
    }
});
