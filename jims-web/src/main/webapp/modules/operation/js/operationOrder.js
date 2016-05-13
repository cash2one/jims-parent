$(function(){
    $('#patient').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: '/modules/clinic/clinicItem/js/datagrid_data2.json',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '50%', align: 'center'},
            {field: 'name', title: '姓名', width: '50%'},
        ]]


    });



    });