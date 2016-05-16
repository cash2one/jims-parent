/**
 * Created by fyg on 2016/5/14.
 */
$(function(){
    $("#dg").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        columns: [[
            {title: '',field: 'field', width: '10%',colspan:1},
            {title: '入库', field: 'q', width: '30%', colspan:3},
            {title: '出库', field: 'w', width: '30%',  colspan:3},
            {title: '库存', field: 'e', width: '30%',colspan:2}
        ],[
                {title: '库房', field: 'drugName', width: '10%'},
                {title: '品次', field: 'drugName', width: '10%'},
                {title: '品种', field: 'drugName', width: '10%'},
                {title: '金额', field: 'drugName', width: '10%'},
                {title: '品次', field: 'drugName', width: '10%'},
                {title: '品种', field: 'drugName', width: '10%'},
                {title: '金额', field: 'drugName', width: '10%'},
                {title: '品种', field: 'drugName', width: '15%'},
                {title: '金额', field: 'drugName', width: '15%'}

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
        $("#date1").textbox('disable');
        $("#date2").textbox('disable');
    }
    reset();
    $("#check").on('click', function(){
        var checks = document.getElementById('check');
        if(checks.checked){
            $("#date1").textbox('enable');
            $("#date2").textbox('enable');
        }else{
            $("#date1").textbox('disable');
            $("#date2").textbox('disable');
        }

    });
    if ($("#check").is(":checked")) {
        $("#date1").textbox('enable');
        $("#date2").textbox('enable');
    }else{
        $("#date1").textbox('disable');
        $("#date2").textbox('disable');
    }
    var check = document.getElementById('check');
    if(check.checked){
        $("#date1").textbox('enable');
        $("#date2").textbox('enable');
    }else{
        $("#date1").textbox('disable');
        $("#date2").textbox('disable');
    }
});
