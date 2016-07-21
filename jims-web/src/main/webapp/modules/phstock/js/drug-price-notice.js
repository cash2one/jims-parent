/**
 * Created by wei on 2016/7/20.
 */
$(function(){

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    var sortFlag = false;
    $("#dg").datagrid({
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        singleSelect: true,
        columns: [[
            {title: '编号',     field: 'id',        align:'center', hidden: 'true'},
            {title: '项目代码', field: 'itemCode', align:'center', width: "10%"},
            {title: '项目名称', field: 'itemName', align:'center', width: "10%"},
            {title: '规格', field: 'itemSpec', align:'center', width: "10%"},
            {title: '单位', field: 'units', align:'center', width: "5%"},
            {title: '原始价格', field: 'price', align:'center', width: "5%"},
            {title: '标准价格', field: 'price', align:'center', width: "5%"},
            {title: '优惠价格', field: 'preferPrice', align:'center', width: "5%"},
            {title: '外宾价格', field: 'foreignerPrice', align:'center', width: "5%"},
            {title: '自费', field: 'feeTypeMask', align:'center', width: "5%", formatter: function (value) {if (value == 0) {return "是"}else{return "否"}}},
            {title: '备注', field: 'memo', align:'center', width: "15%"},
            {title: '起用日期', field: 'startDate', align:'center', width: "10%"},
            {title: '调价变更依据', field: 'changedMemo', align:'center', width: "15%"}]],

        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    $('#label').combogrid({
        editable:false,
        delay: 300,
        width:'196px',
        mode: 'remote',
        method: 'GET',
        url: basePath +'/price-list/list',
        idField: 'value',
        textField: 'label',
        columns:
            [[{field:'label',title:'类别',width:"180px",sortable:true}]]
    });


    $("#searchBtn").on("click", function () {
        var label = $("#label").textbox("getValue");
        console.log(label)
        var startDate = $("#startDate").datetimebox("getValue");
        var stopDate = $("#stopDate").datetimebox("getValue");
        $.get(basePath +"/price-list/price-notice" ,{label:label,startDate:startDate,stopDate:stopDate,orgId:parent.config.org_Id}, function (data) {

            $("#dg").datagrid('loadData', data);

        });
    });



});

