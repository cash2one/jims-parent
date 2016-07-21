/**
 * 价表调价
 * Created by fyg on 2016/7/19.
 */
$(function () {
    var orgId = config.org_Id;
    var data = [{label: '药品',value: '1'},{label: '非药品',value: '0'}];

    $("#priceList").datagrid({
        fit: true,
        border: false,
        fitColumns: true,
        autoRowHeight: false,
        toolbar: '#tb',
        striped: true,
        nowrap: true,
        rownumbers: true,
        singleSelect: true,
        loadMsg: '数据正在加载中，请稍后......',
        columns: [[
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                hidden: true
            },{
                title: '项目代码',
                field: 'itemCode',
                align: 'center',
                width: '9%'
            },{
                title: '项目名称',
                field: 'itemName',
                align: 'center',
                width: '10%'
            },{
                title: '物价码',
                field: 'materialCode',
                align: 'center',
                width: '9%'
            },{
                title: '规格',
                field: 'itemSpec',
                align: 'center',
                width: '9%'
            }, {
                title: '单位',
                field: 'units',
                align: 'center',
                width: '9%'
            }, {
                title: '基本价格',
                field: 'price',
                align: 'center',
                width: '9%'
            },{
                title: '优惠价格',
                field: 'preferPrice',
                align: 'center',
                width: '9%'
            }, {
                title: '外宾价格',
                field: 'foreignerPrice',
                align: 'center',
                width: '9%'
            }, {
                title: '执行科室',
                field: 'performedBy',
                align: 'center',
                width: '9%'
            }, {
                title: '启用日期',
                field: 'startDate',
                align: 'center',
                width: '9%'
            }
        ]],
        onSelect: function(index,row){
            $("#item_name").textbox('setValue',row.itemName);
            $("#basic_price").textbox('setValue',row.price);
            $("#prefer_price").textbox('setValue',row.preferPrice);
            $("#foreigner_price").textbox('setValue',row.foreignerPrice);
            $("#start_date").textbox('setValue',row.startDate);
            $("#item-name").textbox('setValue',row.itemName);
        }
    });

    //类别
    $("#itemClass").combobox({
        valueField: 'value',
        textField: 'label',
        width: 150,
        data: data,
        editable: false,
        onSelect: function (data) {
            if(data.label == '药品'){
                $.messager.alert('系统提示','药品请在药品管理系统中维护!','info');
                $("#itemClass").combobox('setValue','');
            }
        }
    });

    //原价不能编辑
    $("#item_name").textbox('disable');
    $("#basic_price").textbox('disable');
    $("#prefer_price").textbox('disable');
    $("#foreigner_price").textbox('disable');
    $("#start_date").datetimebox('disable');
    //调价时项目名称不能编辑
    $("#item-name").textbox('disable');

    //原价启用日期
    $('#start_date').datetimebox({
        showSeconds: false  //不显示秒钟信息
    });
    //调价启用日期
    $('#start-date').datetimebox({
        showSeconds: false  //不显示秒钟信息
    });
})

//根据拼音码定位加载数据
function load_data() {
    reset();
    var itemClass = $("#itemClass").combobox('getValue');   //获取类别
    if(itemClass && itemClass != ''){   //类别不为空
        var inputCode = $('#itemName').val();
        if (inputCode && inputCode != "") {
            $.ajax({
                'type': 'GET',
                'url': '/service/price/get-by-inputCode?inputCode=' + inputCode + '&orgId=' + config.org_Id,
                'success': function (data) {
                    if(data.length > 0){
                        $("#priceList").datagrid('loadData', data);
                        $("#priceList").datagrid('selectRow', 0);    //默认选中第一行(如果数据只有一条则选中)
                    }else{
                        $.messager.alert('系统提示','没有查到数据，请确认输入是否正确!','info');
                        $("#itemName").val('');
                        $("#priceList").datagrid('loadData', []);
                        reset();
                    }
                }
            });
        } else {
            $("#priceList").datagrid('loadData', []);
            reset();
        }
    }else{
        $.messager.alert('系统提示','请选择类别','info');
        $("#itemName").val('');
    }
}

//保存调价
$("#save_data").on('click',function(){
    var priceDictListVo = {};
    var row = $("#priceList").datagrid('getSelected');
    priceDictListVo.itemClass = row.itemClass;     //项目类别
    priceDictListVo.itemCode = row.itemCode;       //代码
    priceDictListVo.itemName = row.itemName        //名称
    priceDictListVo.itemSpec = row.itemSpec;       //规格
    priceDictListVo.units = row.units;              //计价单位
    priceDictListVo.price = $("#modify_basic_price").textbox('getValue');           //基本价格
    priceDictListVo.preferPrice = $("#modify_prefer_price").textbox('getValue');    //优惠价格
    priceDictListVo.foreignerPrice = $("#modify_foreigner_price").textbox('getValue');    //外宾价格
    priceDictListVo.startDate = $("#start-date").datetimebox('getValue');    //启用日期
    priceDictListVo.stopDate = priceDictListVo.startDate;    //停用日期
    priceDictListVo.performedBy = row.performedBy;  //执行科室
    priceDictListVo.feeTypeMask = row.feeTypeMask;  //自费标志
    priceDictListVo.classOnInpRcpt = row.classOnInpRcpt;     //住院收据
    priceDictListVo.classOnOutpRcpt = row.classOnOutpRcpt;     //门诊收据
    priceDictListVo.classOnReckoning = row.classOnReckoning;     //核算科目
    priceDictListVo.subjCode = row.subjCode;           //会计科目
    priceDictListVo.classOnMr = row.classOnMr;      //病案首页
    priceDictListVo.memo = row.memo;                //备注信息
    priceDictListVo.inputCode = row.inputCode;      //拼音码
    priceDictListVo.materialCode = row.materialCode;    //物价码
    priceDictListVo.orgId = row.orgId;      //所属组织机构
    priceDictListVo.id = row.id;      //所属组织机构


    if (parseInt(priceDictListVo.price) == 0 || parseInt(priceDictListVo.preferPrice) == 0 || parseInt(priceDictListVo.foreignerPrice) == 0) {
        $.messager.alert('系统提示', '不能将价格调整为0,请重新输入合适的价格!', 'info');
        return;
    }

    if(priceDictListVo.startDate == null || priceDictListVo.startDate == ''){
        $.messager.alert('系统提示', '启用日期不能为空,请输入!', 'info');
        return;
    }

    var newDate = priceDictListVo.startDate;
    newDate = new Date(newDate.replace(/-/, "/"));
    var oldDate = row.startDate;
    oldDate = new Date(oldDate.replace(/-/,"/"));
    if(newDate < oldDate){
        $.messager.alert('系统提示','调价后的启用日期不能在原先启用日期之前,请重新设置!','info');
        return ;
    }

    console.log(priceDictListVo);
    if (priceDictListVo) {
        $.postJSON(basePath + '/price/save-adjust-price', JSON.stringify(priceDictListVo), function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", "保存成功", "success");
                load_data();
                reset();
                $("#modify_basic_price").textbox('setValue', '0.00');
                $("#modify_prefer_price").textbox('setValue', '0.00');
                $("#modify_foreigner_price").textbox('setValue', '0.00');
                $("#start-date").textbox('setValue', '');
            } else {
                $.messager.alert('提示消息', data.code, "error");
            }
        }, function (data) {
            $.messager.alert('提示', "保存失败", "error");
        });
    }
});

//清空输入的调价数据
$("#clear").on('click',function(){
    $("#modify_basic_price").textbox('setValue','0.00');
    $("#modify_prefer_price").textbox('setValue','0.00');
    $("#modify_foreigner_price").textbox('setValue','0.00');
    $("#start-date").textbox('setValue','');
});

//清空数据
function reset(){
    $("#item_name").textbox('setValue','');
    $("#basic_price").textbox('setValue','');
    $("#prefer_price").textbox('setValue','');
    $("#foreigner_price").textbox('setValue','');
    $("#start_date").textbox('setValue','');
    $("#item-name").textbox('setValue','');
}




