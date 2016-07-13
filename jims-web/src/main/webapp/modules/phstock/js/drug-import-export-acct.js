/**
 * Created by admin on 2016/7/12.
 */

$(function () {

    var orgId = config.org_Id;
    var storageCode = parent.config.currentStorage;
    var personId = config.persionId;
    $('#calendar').datebox({
        width: 140,
        editable: false,
        value: parent.formatDatebox(new Date())
    });
    $("#queryBtn").on("click", function () {
        var value = $("#acctType").combobox("getValue");
        var startTime = $('#calendar').datebox("getValue");
        loadData(value, startTime);
    })
    function loadData(value, startTime) {
        var url = '';
        if (value == 2) {
            url = parent.basePath + '/drug-out/find-export-data';
        } else {
            url = parent.basePath + '/drug-in/find-import-data';
        }
        $.ajax({
            url: url + "?orgId=" + orgId + "&storageCode=" + storageCode + "&startTime=" + startTime,
            type: "POST",
//            data:{orgId:orgId,storageCode:storageCode,startTime:startTime},
            dataType: "JSON",
            cache: false,
            success: function (data) {
                if (data.length != 0)
                    $("#importData").datagrid("loadData", data);
                $.messager.alert("系统提示", "数据库暂无数据", "error");
            }
        });
    }

    /**
     * 记账
     */
    $("#acctBtn").on("click", function () {
        var value = $("#acctType").combobox("getValue");
        var selectedDatas = $("#importData").datagrid("getSelections");
        if (selectedDatas != 1) {
            $.messager.alert("系统提示", "请选择一条记录进行记账", "error");
            return;
        }
        var is = selectedDatas[0].accountIndicator;
        if (is == 1) {
            $.messager.alert("系统提示", "此条记录已经记账", "error");
            return;
        }
        var url = "";
        var is = selectedDatas[0].flag;
        var id = selectedDatas[0].id;
        if (is == 0) {//入库
            url = parent.basePath + '/drug-in/acct';
        }
        if (is == 1) {
            url = parent.basePath + '/drug-out/acct';
        }
        var startTime = $('#calendar').datebox("getValue");
        $.ajax({
            url: url + "?id=" + id,
            type: "POST",
            dataType: "JSON",
            cache: false,
            success: function (data) {
                $.messager.alert("系统提示", "记账成功", "info");
                loadData(value, startTime);
            }
        })
    });

    function init1() {
        $("#importData").datagrid({
            fit: true,
            fitColumns: false,
            striped: true,
            singleSelect: true,
            toolbar: '#tb',
//        method: 'GET',
            width: 400,
            rownumbers: true,
            loadMsg: '数据正在加载中，请稍后.....',
            columns: [
                [
                    {
                        title: "id",
                        field: "id",
                        width: 100,
                        hidden: true,
                        align: 'center'
                    },
                    {
                        title: "日期",
                        field: "date",
                        width: 100,
                        align: 'center'
                    },
                    {
                        title: "单据号",
                        field: "documentNo",
                        width: 200,
                        align: 'center'
                    } ,
                    {
                        title: "入出库类别",
                        field: "importExportClass",
                        width: 70,
                        align: 'center'
                    } ,
                    {
                        title: "是否记账",
                        field: "accountIndicator",
                        width: 70,
                        align: 'center',
                        formatter: function (row, value) {
                            if (value.accountIndicator == 1)
                                return "已记账";
                            return "已记账";
                        }
                    },
                    {
                        title: "记账人",
                        field: "acctOperator",
                        width: 70,
                        align: 'center'

                    },
                    {
                        title: "记账时间",
                        field: "acctDate",
                        width: 70,
                        align: 'center'
                    },
                    {
                        title: "flag",
                        field: "flag",
                        hidden: true,
                        align: 'center'
                    }
                ]
            ]
        })
    }
})
