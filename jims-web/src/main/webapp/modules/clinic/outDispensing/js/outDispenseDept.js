$(function () {

     $("#dept").datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + "/dept-dict/list?orgId=" + config.org_Id,
        idField: 'id',
        columns: [
            [      //每个列具体内容
                {field: 'id', title: '序号', width: '10%', align: 'center'},
                {field: 'deptCode', title: '科室编码', width: '20%', align: 'center'},
                {field: 'deptName', title: '科室名称', width: '60%', align: 'center'},
                {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center'}
            ]
        ], onClickRow: function (index, row) {//单击行事件
            $('#drugDialog').dialog('destroy');
             config.deptCode=row.deptCode;
             config.deptName=row.deptName;
             location.href = "/modules/clinic/outDispensing/centerRegion.html";
        }});
    $('#drugDialog').dialog('open').dialog('center').dialog('setTitle', '选择科室');
 });



