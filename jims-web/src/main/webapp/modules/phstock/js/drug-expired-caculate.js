/**
 * Created by fyg on 2016/5/14.
 */
$(function(){
    $("#dg").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        method: 'get',
        url: basePath + '',
        singleSelect: true,
        columns: [[
            {title: 'id', field: 'id', hidden: true},
            {title: '名称', field: 'drugName', width: '13%', align: 'center'},
            {title: '规格', field: 'drugName', width: '13%', align: 'center'},
            {title: '单位', field: 'drugName', width: '13%', align: 'center'},
            {title: '批号', field: 'drugName', width: '13%', align: 'center'},
            {title: '有效期', field: 'drugName', width: '12%', align: 'center'},
            {title: '厂家', field: 'drugName', width: '12%', align: 'center'},
            {title: '库存量', field: 'drugName', width: '12%', align: 'center'},
            {title: '金额', field: 'drugName', width: '12%', align: 'center'}
        ]]
    });

    $("#bottom").datagrid({
        footer: '#tb',
        border: false
    });
    $('#cc').layout('panel', 'north').panel('resize', {height: 'auto'});
    $('#cc').layout('panel', 'south').panel('resize', {height: 'auto'});
    $("#cc").layout({
        fit: true
    });

    var reset = function () {
        $("#date1").textbox('enable');
        $("#date2").textbox('disable');
        $("#date3").textbox('disable');
    }
    reset();
    $("#radio1").on('click', function(){
         $("#date1").textbox('enable');
         $("#date2").textbox('disable');
         $("#date3").textbox('disable');
     });
     $("#radio2").on('click', function () {
         $("#date1").textbox('disable');
         $("#date2").textbox('enable');
         $("#date3").textbox('enable');
     });
});
