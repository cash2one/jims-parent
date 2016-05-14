/**
 * 药理信息维护
 * @author yangruidong
 * @version 2016-05-13
 */
$(function () {

    $("#drugName").combogrid({
        panelWidth: 300,
        idField: 'drugCode',
        textField: 'drugName',
        value:'006',
        rownumbers:'true',
       // url: 'datagrid_data1.json',
        method: 'get',
        columns: [[
            {field:'drugCode',title:'代码',width:80,align:'center'},
            {field:'drugName',title:'名称',width:120,align:'center'},
            {field:'drugName',title:'拼音头',width:100,align:'center'}
        ]],
        fitColumns: true
    });


});




