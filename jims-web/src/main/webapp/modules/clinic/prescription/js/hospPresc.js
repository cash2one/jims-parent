
//页面加载
$(function(){
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list?clinicId='+clinicId,
        columns:[[      //每个列具体内容
            {field:'visitDate',title:'就诊时间',width:'20%',align:'center'},
            {field:'visitNo',title:'就诊序号',width:'20%',align:'center'},
            {field:'prescNo',title:'处方号',width:'20%',align:'center'},
            {field:'itemClass',title:'处方分类',width:'20%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "A") {
                        value = "西、成药";
                    }
                    else if (value == "B") {
                        value = "草药";
                    }
                    return value;
                }},
            {field:'chargeIndicator',title:'收费状态',width:'20%',align:'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        value = "未收费";
                    }
                    return value;
                }}
        ]], onClickRow: function (index, row) {

        }, onLoadSuccess: function(){

        }
    });

});
