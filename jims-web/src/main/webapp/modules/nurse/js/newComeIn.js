$(function() {

    $('#waitList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        columns: [[      //每个列具体内容
            {field: 'name', title: '姓名',width: '15%', align: 'center'},
            {field: 'patientId', title: '病人ID', width: '15%', align: 'center'}
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function (data) {

        }
    });
})