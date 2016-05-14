/**
 * Created by fyg on 2016/5/14.
 */
$(function(){
    $("#unit").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        method: 'get',
        url: basePath + '',
        singleSelect: true,
        columns: [[
            {title: 'id', field: 'id', hidden: true},
            {title: '申请单位', field: 'unitName', width: '50%', align: 'center'},
            {title: '申请时间', field: 'unitTime', width: '50%', align: 'center'}
        ]]
    });

    $("#drugInformation").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        method: 'get',
        url: basePath + '',
        singleSelect: true,
        columns: [[
            {title: 'id', field: 'id', hidden: true},
            {title: '药名', field: 'drugName', width: '10%', align: 'center'},
            {title: '规格', field: 'drugName', width: '10%', align: 'center'},
            {title: '单位', field: 'drugName', width: '10%', align: 'center'},
            {title: '批号', field: 'drugName', width: '10%', align: 'center', editor: {type: 'numberbox'}},
            {title: '申请数量', field: 'drugName', width: '10%', align: 'center'},
            {title: '生产厂家', field: 'drugName', width: '10%', align: 'center'},
            {title: '申请单号', field: 'drugName', width: '10%', align: 'center'},
            {title: '消耗量', field: 'drugName', width: '10%', align: 'center', editor: 'numberbox'},
            {title: '调拨量', field: 'drugName', width: '10%', align: 'center', editor: 'numberbox'}
        ]]
    });
});