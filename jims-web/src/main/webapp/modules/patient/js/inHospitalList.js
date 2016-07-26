//页面加载
$(function() {

    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        /*title:'与当前登记病人重名记录',*/
        toolbar: '#searchDiv',
        method: 'GET',
        url: basePath + '/patMasterIndex/list',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'id', title: 'ID', hidden: 'true'},
            {field: 'name', title: '姓名', width: '15%', align: 'center'},
            {field: 'idNo', title: '身份证号', width: '25%', align: 'center'},
            {field: 'sex', title: '性别', width: '10%', align: 'center', formatter: setDataFormatter},
            {field: 'inpNo', title: '住院号', width: '10%', align: 'center'},
            {field: 'birthPlace', title: '出生地', width: '20%', align: 'center'},
            {field: 'mailingAddress', title: '通信地址', width: '20%', align: 'center'}
        ]],toolbar: [{
            text: '登记',
            iconCls: 'icon-add',
            handler: function() {
                $("#newDialog").dialog('open');
            }
        },{
            text: '取消登记',
            iconCls: 'icon-remove',
            handler: function() {
                var selRow = $('#leftList').datagrid('getChecked');
                if(selRow!=null && selRow!='' && selRow!=undefined){
                    var id = selRow[0].id;
                    $.ajax({
                        'type': 'POST',
                        'url': basePath+'/patMasterIndex/delete',
                        'contentType': 'application/json',
                        'data': ids=id,
                        'dataType': 'json',
                        'success': function(data){
                            if(data.data=='success'){
                                $.messager.alert("提示消息",data.code+"条记录取消登记成功！");
                                $('#leftList').datagrid('load');
                            }else if(data.data=='warning') {
                                $.messager.alert('提示',"已缴纳预交金，不能取消登记！", "error");
                            }else if(data.data=='warn') {
                                $.messager.alert('提示',"该病人未进行入院登记，不能进行取消登记操作！", "error");
                            }else{
                                $.messager.alert('提示',"取消登记失败", "error");
                            }
                        },
                        'error': function(data){
                            $.messager.alert('提示',"取消登记失败", "error");
                        }
                    });
                }else{
                    $.messager.alert('提示',"请选择要取消的登记！", "warning");
                }
            }
        }], onClickRow: function (index, row) {
            $("#masterForm").form('load', row);
        }, onLoadSuccess: function () {

        }

    });
    //设置分页控件
    var p = $('#leftList').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

    $("#saveBtn").on('click', function () {
        if($("#masterForm").form('validate')) {
            //根据身份证号去patmasterindex查询是否有该人，如果有，在根据id去pat_in_hospital查询patient_id是否已住院，若未住院，则回填，若已住院则提示，否则新增
            if(validIfInHosp($("#id").val())){
                $.postForm(basePath + '/patMasterIndex/save', 'masterForm', function (data) {
                    if (data.data == 'success') {
                        $.messager.confirm("操作提示", "是否交预交金？", function (data) {
                            $("#masterForm").form('clear');
                            $("#newDialog").dialog('close');
                            $("#leftList").datagrid("reload");
                            if (data) {
                                window.location.href="/modules/finance/prepaymentList.html";
                            }
                        });
                    } else {
                        $.messager.alert('提示', "保存失败", "error");
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                });
            }

        }
    });

});
//列表条件查询
    function searchByCondition() {
        var name = $("#name").textbox('getValue');
        var idNo = $("#idNo").textbox('getValue');
        var hospNo = $("#hospNo").textbox('getValue');
        /* var ybType=$("#ybType").textbox('getValue');
         var ybNo=$("#ybNo").textbox('getValue');*/
        $("#orderList").datagrid({
            url: basePath + '/patMasterIndex/list',
            queryParams: {"name": name, "idNo": idNo, "hospNo": hospNo}
        });
    }





