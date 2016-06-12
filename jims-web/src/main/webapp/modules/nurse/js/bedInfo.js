/*
var wardCode='160101';
$(function(){
    $('#bedInfo').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/getAllBed?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bed_no1', title: '床号', width: '10%', align: 'center'},
            {field: 'name', title: '姓名', width: '10%', align: 'center'},
            {field: 'sex', title: '性别', width: '10%', align: 'center'},
            {field: 'age', title: '年龄', width: '10%', align: 'center'},
            {field: 'visit_id', title: '住院号', width: '10%', align: 'center'},
            {field: 'diagnosis', title: '诊断', width: '10%', align: 'center'},
            {field: 'patient_id', title: '病人ID', width: '10%', align: 'center'},
            {field: 'doctor_in_charge', title: '医生', width: '10%', align: 'center'}

        ]],onClickRow: function (index, row) {//单击行事件
            window.parent.document.getElementById("centerIframe").src = "/modules/nurse/centerRegion.html?id="+row.id;
        }
    });




});*/
