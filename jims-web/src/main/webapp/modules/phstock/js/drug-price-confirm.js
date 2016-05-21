$(function () {
    var drugNameDictList = [];//药品名称列表
    var drugSupplierDict  = [];//药品生产商列表
    var selectedModifyListRow;
    var editIndex;
    $.get(basePath + "/drug-price/findDrugNameDictList", function (data) {
        drugNameDictList = data;
    });
    $.get( basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商", function (data) {
        drugSupplierDict = data;
    });
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#modifyList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#modifyList").datagrid({
        title:'调价记录',
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        rownumbers: true,
        method:"get",
       loadMsg: '数据正在加载中，请稍后.....',
       // pagination: true,//分页控件
       //pageSize: 15,
       //pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "operator",
            field: "operator",
            hidden: true
        },{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "orgId",
            field: "orgId",
            hidden: true
        },  {
            title: "confirmOperator",
            field: "confirmOperator",
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
            align: 'center'
        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center',
            formatter:function(value,row,index){
                var supplierName = value;
                $.each(drugSupplierDict, function (index,item) {
                    if(item.supplierCode == value){
                        supplierName =  item.supplierId;
                    }
                });
                return supplierName;
            }
        }, {
            title: "原批发价",
            field: "originalTradePrice",
            width: '7%',
            align: 'center'

        }, {
            title: "新批发价",
            field: "currentTradePrice",
            width: '7%',
            align: 'center'
        }, {
            title: "原零售价",
            field: "originalRetailPrice",
            width: '7%',
            align: 'center'

        }, {
            title: "新零售价",
            field: "currentRetailPrice",
            width: '7%',
            align: 'center'
        }, {
            title: "实际生效日期",
            field: "actualEfficientDate",
            width: '12%',
            align: 'center',
            editor:{
                type:'datetimebox',options: {
                onChange: function (newValue, oldValue) {
                    if(selectedModifyListRow){
                        var noticeEfficientDate = new Date(selectedModifyListRow.noticeEfficientDate);
                        if (new Date(newValue).getTime() < noticeEfficientDate.getTime() ){
                            $.messager.alert("提示","实际生效日期应大于通知生效日期","info");
                        }
                    }

                }
            }
            },
            formatter: function (value,row,index) {
                if(!value){
                    return "";
                }
                row.actualEfficientDate = new Date(value);
                var date = new Date(value);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var hour = date.getHours();
                var minutes = date.getMinutes();
                var second = date.getSeconds();

                return year + "年" + month + "月"+ day + "日"+ hour + "时"+ minutes + "分"+ second + "秒";
            }
        }, {
            title: "通知生效日期",
            field: "noticeEfficientDate",
            width: '12%',
            align: 'center',
            formatter: function (value,row,index) {
                row.noticeEfficientDate = new Date(value);
                var date = new Date(value);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var hour = date.getHours();
                var minutes = date.getMinutes();
                var second = date.getSeconds();

                return year + "年" + month + "月"+ day + "日"+ hour + "时"+ minutes + "分"+ second + "秒";
            }
        }, {
            title: "调价依据",
            field: "modifyCredential",
            width: '9%',
            align: 'center'
        }]],
        onSelect: function (rowIndex, rowData) {
            rowData.confirmOperator = parent.config.operator;
            stopEdit();
            selectedModifyListRow = rowData;
            $("#modifyList").datagrid("beginEdit",rowIndex);
            editIndex = rowIndex;

        }
    });

    //提取
    $("#addBtn").on("click", function () {
       var startDate = $("#start").datetimebox("getValue");
       var endDate = $("#end").datetimebox("getValue");
        var url = basePath + '/drug-price-modify/findModifyListByNoticeEfficientDate?startDate='+startDate+"&endDate="+endDate;
        $("#modifyList").datagrid("reload",url);
    });
     //填充时间
    $("#fillBtn").on("click", function () {
        var newDate = $("#data").datetimebox("getValue");
        var rows = $("#modifyList").datagrid("getRows");
        $.each(rows, function (index,row) {
            $("#modifyList").datagrid("beginEdit",index);
            //row.actualEfficientDate="";
            //
            //row.actualEfficientDate = newDate;
            row.confirmOperator = parent.config.operator;
            ////$('#modifyList').datagrid('deleteRow',index);
            ////$('#modifyList').datagrid('insertRow',{index:index,row:row});
            //$('#modifyList').datagrid('updateRow', {
            //        index: index,
            //        row: row
            //    }
            //);
            var ed = $('#modifyList').datagrid('getEditor', {index:index,field:'actualEfficientDate'});
            $(ed.target).datetimebox('setValue', newDate);
            ////$('#modifyList').datagrid('refreshRow',index);
            $("#modifyList").datagrid("endEdit",index);
            //editIndex = index;
        });

    });
    //删除
    $("#delBtn").on("click", function () {
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
    $("#saveBtn").on("click", function () {

        if (editIndex || editIndex == 0) {
            $("#modifyList").datagrid("endEdit", editIndex);
        }
        console.log(editIndex);
        var insertData = $("#modifyList").datagrid("getChanges", "inserted");
        var updateDate = $("#modifyList").datagrid("getChanges", "updated");
        var deleteDate = $("#modifyList").datagrid("getChanges", "deleted");

        var drugPriceModifyVo = {};
        drugPriceModifyVo.inserted = insertData;
        drugPriceModifyVo.deleted = deleteDate;
        drugPriceModifyVo.updated = updateDate;
        console.log(JSON.stringify(drugPriceModifyVo));
        //if (drugPriceModifyVo) {
        //    $.postJSON(basePath + "/drug-price-modify/saveModifyConfirm", JSON.stringify(drugPriceModifyVo), function (data) {
        //        $.messager.alert("系统提示", "保存成功", "info");
        //        $('#modifyList').datagrid('loadData', { total: 0, rows: [] });
        //    }, function (data) {
        //        $.messager.alert("系统提示", "保存失败", "error");
        //        $('#modifyList').datagrid('loadData', { total: 0, rows: [] });
        //    })
        //}
    });




 var p = $('#modifyList').datagrid('getPager');
 $(p).pagination({
 beforePageText: '第',
 afterPageText: '页    共 {pages} 页',
 displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
 });
});