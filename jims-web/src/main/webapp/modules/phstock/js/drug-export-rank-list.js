/**
 * Created by wei on 2016/5/14.
 */
$("#dg").datagrid({
    title: '进库药品排行榜',
    fit: true,//让#dg数据创铺满父类容器
    toolbar: '#tb',
    pagination:true,//分页控件
    pageSize:15,
    pageList: [10,15,30,50],//可以设置每页记录条数的列表
    columns: [[
        {title: '药品编码', field: 'q',width: "10%"},
        {title: '药品名称', field: 'q', width: "20%"},
        {title: '生产商', field: 'q',width: "20%"},
        {title: '规格', field: 'q',width: "10%"},
        {title: '单位', field: 'q',width: "10%"},
        {title: '数量',  field: 'q',width: "10%"},
        {title: '零价金额', field: 'q',width: "10%"},
        {title: '批价金额', field: 'q',width: "10%"}
    ]]

});
$('#type').combogrid({
    delay: 150,
    width: '260px',
    method: 'GET',
    url: basePath + '/drug-class-dict/list',
    idField: 'className',
    textField: 'className',
    mode: 'remote',
    columns: [[
        {field: 'className', title: '类别', width: "260px", sortable: true}
    ]]
});
//分页控件
var p = $('#dg').datagrid('getPager');

var reset = function () {
    $('#chk3').click();
    $("#type").combogrid('disable');
};
reset();

$('#chk3').click(function () {
    $("#type").combogrid('setValue', '');

    $("#type").combogrid('disable');
});
$('#chk4').click(function () {

    $("#type").combogrid('enable');

});
