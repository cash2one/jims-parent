$(function(){
//?startDateDispense='+formatDatebox(new Date())+'&stopDateDispense='+formatDatebox(new Date())
    $('#confirmDrug').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/drugPresc/findMaster',
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true, //分页控件
       pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'prescSource', title: '处方来源', width: '5%', align: 'center'},
            {field: 'prescAttr', title: '处方属性', width: '5%', align: 'center'},
            {field: 'clinicId', title: '门诊号', width: '10%', align: 'center'},
            {field: 'name', title: '姓名', width: '5%', align: 'center'},
            {field: 'dispensingDatetime', title: '发药日期', width: '10%', align: 'center',formatter: formatDateBoxFull},
            {field: 'prescNo', title: '处方号', width: '5%', align: 'center'},
            {field: 'repetition', title: '剂数', width: '5%', align: 'center'},
            {field: 'identity', title: '身份', width: '5%', align: 'center'},
            {field: 'chargeType', title: '费别', width: '5%', align: 'center'},
            {field: 'unitInContract', title: '合同单位', width: '10%', align: 'center'},
            {field: 'costs', title: '应收', width: '5%', align: 'center'},
            {field: 'payments', title: '实收', width: '5%', align: 'center'},
            {field: 'orderedBy', title: '开单科室', width: '5%', align: 'center'},
            {field: 'prescribedBy', title: '处方医师', width: '5%', align: 'center'},
            {field: 'enteredBy', title: '录入人', width: '5%', align: 'center'},
            {field: 'dispensary', title: '药房', width: '5%', align: 'center'},
            {field: 'dispensarySub', title: '子药房', width: '5%', align: 'center'}
        ]],
        view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
        },
        onExpandRow: function(index,row){
            $('#ddv-'+index).datagrid({
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                method:'GET',
                url: basePath + '/drugPresc/getDetail?masterId='+ row.id,
                columns:[[
                    {field: 'itemNo', title: '组别', width: '10%', align: 'center'},
                    {field: 'drugName', title: '药品名称', width: '10%', align: 'center'},
                    {field: 'firmId', title: '厂商', width: '10%', align: 'center'},
                    {field: 'dosageEach', title: '剂量', width: '10%', align: 'center'},
                    {field: 'quantity', title: '数量', width: '10%', align: 'center'},
                    {field: 'packageUnits', title: '单位', width: '10%', align: 'center'},
                    //{field: 'costs', title: '单价', width: '50%', align: 'center'},
                    {field: 'costs', title: '应收', width: '10%', align: 'center'},
                    {field: 'payments', title: '实收', width: '10%', align: 'center'},
                    {field: 'administration', title: '用法', width: '20%', align: 'center'}

                ]],
                onResize:function(){
                    $('#confirmDrug').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#confirmDrug').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $('#confirmDrug').datagrid('fixDetailRowHeight',index);
        }
    });


    //设置分页控件
    var p = $('#confirmDrug').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
     //   var queryParams = $('#confirmDrug').datagrid('options').queryParams;
    //    queryParams["drugPrescMaster.name"] = $('#name').val();
     /*   queryParams["drugPrescMaster.regCname"] = $('#regCname').val();
        queryParams["qvo.sysEname"] = $('#sysEname').val();
        queryParams["qvo.sysCname"] = $('#sysCname').eval();*/

        $('#confirmDrug').datagrid({url:basePath + '/drugPresc/findMaster?'+$("#patientInfoForm").serialize() });
      //  $('#confirmDrug').datagrid("reload");   //点击搜索
    });
});