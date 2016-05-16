/**
 * Created by fyg on 2016/5/13.
 */
$(function(){
    $("#dg").datagrid({
        iconCls: 'icon-edit',
        width: 'auto',
        height: 'auto',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '',
        remoteSort: false,  //定义从服务器对数据进行排序
        singleSelect: true,
        columns:[[
            {title: 'id',field: 'id',hidden: true},
            {title: '药品名称', field: 'drugName', width: '8%', align: 'center'},
            {title: '规格', field: 'drugStandard', width: '8%', align: 'center'},
            {title: '包装规格', field: 'packStandard', width: '8%', align: 'center'},
            {title: '单位', field: 'unit', width: '8%', align: 'center'},
            {title: '厂家', field: 'supplier', width: '8%', align: 'center'},
            {title: '原批发价', field: 'drugName', width: '8%', align: 'center'},
            {title: '新批发价', field: 'drugName', width: '8%', align: 'center'},
            {title: '原零售价', field: 'drugName', width: '8%', align: 'center'},
            {title: '新零售价', field: 'drugName', width: '8%', align: 'center'},
            {title: '通知生效日期', field: 'drugName', width: '7%', align: 'center'},
            {title: '实际生效日期', field: 'drugName', width: '7%', align: 'center'},
            {title: '调价依据', field: 'drugName', width: '7%', align: 'center'},
            {title: '录入人', field: 'drugName', width: '7%', align: 'center'}
        ]]
    });
});
