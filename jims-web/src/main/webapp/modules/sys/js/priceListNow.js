/**
 * Created by wei on 2016/5/5.
 */
$(function () {
    var orgId="1";
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    /*parent.config.org_Id;*/

    $("#dg").datagrid({
        title: '信息查询',
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        singleSelect: true,
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        url:'/service/price-list/list-now?orgId='+orgId,
        idField:'fldId',
        remoteSort:false,
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns: [[{
            title: '编号',
            field: 'id',
            hidden: 'true'
        }, {
            title: '药品',
            field: 'itemName',
            width: "20%"

        },{
            title: '类型',
            field: 'label',
            width: "10%"

        }, {
            title: '规格',
            field: 'itemSpec',
            width: "10%"

        }, {
            title: '单位',
            field: 'units',
            width: "5%"

        },{
            title: '基本价格',
            field: 'price',
            width: "10%"

        },{
            title: '优惠价格',
            field: 'preferPrice',
            width: "10%"

        },{
            title: '外宾价格',
            field: 'foreignerPrice',
            width: "10%"

        },{
            title: '拼音码',
            field: 'inputCode',
            width: "5%"

        },{
            title: '起用日期',
            field: 'startDate',
            width: "15%",
            formatter: function (row) {
                var date = new Date(+row+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');

                return date
            }
        },{
            title: '结束时间',
            field: 'stopDate',
            width: "15%",
            formatter: function (row) {
                if(row!=null) {
                    var date = new Date(+row+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');

                    return date
                }
            }

        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });


    $('#label').combogrid({
        delay: 300,
        width:'196px',
        mode: 'remote',
        method: 'GET',
        url: '/service/price-list/list',
        idField: 'label',
        textField: 'label',
        columns: [[
            {field:'label',title:'类别',width:"180px",sortable:true}
        ]]
    });

    //设置分页控件
    var p = $('#dg').datagrid('getPager');



    $("#searchBtn").on("click", function () {
        var inputCode = $("#inputCode").textbox("getValue");
        var label=$("#label").textbox("getValue");
        console.log(label);
        console.log(inputCode)
        $.get("/service/price-list/find-by-input-code-now?inputCode=" + inputCode+"&label="+label+"&orgId="+orgId, function (data) {

            $("#dg").datagrid('loadData', data);
        });
    });


})