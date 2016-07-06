/**
 * 系统服务维护
 * @author txb
 * @version 2016-05-31
 */

$(function () {
    var editIndex = undefined;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    var checkedMenus = []; //选中菜单
    var flag = 0;//增加修改状态
    /**
     * 服务数据框
     */
    $("#serviceDg").datagrid({
        title: '基础服务维护',
        fit: true,
        fitColumns: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "服务名称",
            field: "serviceName",
            width: '11%',
            align: 'center',
            editor:{
                type:"textbox",options:{

                }
            }

        }, {
            title: "服务描述",
            field: "serviceDescription",
            width: '11%',
            align: 'center',
            editor:{
                type:"textbox",options:{

                }
            }
        }, {
            title: "服务类型",
            field: "serviceType",
            width: '11%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '无偿服务'
                }else if(value == "1"){
                    return '有偿服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务类别",
            field: "serviceClass",
            width: '11%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '机构服务'
                }else if(value == "1"){
                    return '个人服务'
                }else if(value == "2"){
                    return '所有服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务图片",
            field: "serviceImage",
            width: '66%',
            align: 'center',
            formatter: function (value,index,row) {
                return "<img src='"+value+"' style='width:50px;height:50px;'/>"
            }
        }]]
    });
    /**
     * 服务名称
     */
    $("#serviceName").textbox({
            width:'200px'
        }
    );
    /**
     * 服务类型
     */
    $("#serviceType").combobox({
        valueField:"value",
        textField:"text",
        width:'200px',
        data: [{
            text: '无偿服务',
            value: "0"
        },{
            text: '有偿服务',
            value: "1",
            selected:true
        }]
    });
    /**
     * 服务类别
     */
    $("#serviceClass").combobox({
        valueField:'value',
        textField:'text',
        width:'200px',
        data: [{
            text: '机构服务',
            value: "0"
        },{
            text: '个人服务',
            value: "1",
            selected:true
        },{
            text: '所有服务',
            value: "2"
        },{
            text: '机构管理服务',
            value: "3"
        }]
    });

    /**
     * 服务弹出框
     */
    $("#serviceDialog").dialog({
        title: '基础服务增加',
        width: 1000,
        height: 350,
        closed:true

    });
    /**
     * 服务定位
     */
    $("#serviceNameLocation").textbox({
        buttonIcon:"icon-search",
        onClickButton: function () {
            var rows = $("#serviceDg").datagrid("getRows");
            var value = $("#serviceNameLocation").textbox("getValue");
            $.each(rows, function (index,row) {
                if(row.serviceName.indexOf(value) != -1){
                    $("#serviceDg").datagrid("scrollTo",index);
                    $("#serviceDg").datagrid("selectRow",index);
                }
            });
        }
    });

    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        reset();
        flag = 1;
        $("#serviceType").combobox("setValue","0");
        $("#serviceClass").combobox("setValue","0");
        $("#serviceDialog").dialog("open");
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        reset();
        flag = 0;
        var row = $("#serviceDg").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一个服务",'info');
            return;
        }
        $("#id").textbox("setValue",row.id);
        $("#serviceName").textbox("setValue",row.serviceName);
        $("#serviceType").combobox("setValue",row.serviceType);
        $("#serviceClass").combobox("setValue",row.serviceClass);
        //service.serviceImage = $("#serviceImage").filebox("setValue",row.serviceImage);
        $("#serviceDescription").val(row.serviceDescription);
        $("#serviceDialog").dialog("open");

    });

    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        stopEdit();
        var row = $("#serviceDg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }
        if (!row.id) {
            //判断是否是新加项目
            var index = $("#serviceDg").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#serviceDg").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/sys-service/del", row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        loadDict();
                    })
                }
            });
        }

    });

    /**
     * 保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#serviceName").textbox("getValue")){
            $.messager.alert("提示","请输入必填项",'error');
            return ;
        }


        var row = $("#serviceDg").datagrid("getSelected");

        var maxsize = 2*1024*1024;//2M
        var errMsg = "上传的附件文件不能超过2M！！！";
        var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
        var  browserCfg = {};
        var ua = window.navigator.userAgent;
        console.log(ua);
        if (ua.indexOf("MSIE")>=1){
            browserCfg.ie = true;
        }else if(ua.indexOf("Firefox")>=1){
            browserCfg.firefox = true;
        }else if(ua.indexOf("Chrome")>=1){
            browserCfg.chrome = true;
        }
        var obj_file = document.getElementById("serviceImage");
        if(obj_file.value==""){
            if(flag == 1){
                alert("请先选择上传文件");
                return;
            }
            if(row && !row.id){
                alert("请先选择上传文件");
                return;
            }
        }
        var serviceImage = $("#serviceImage").val();
        var suffer=serviceImage.substring(serviceImage.lastIndexOf(".")+1).toLowerCase();
        if(suffer!="jpg"&&suffer!="png"&&suffer!="gif"&&suffer!="jpeg"&&suffer!="bmp"&&suffer!="swf"){
            if( flag == 1 ){
                $.messager.alert("系统提示", "请选择正确格式的图片","error");
                return;
            }
            if( row && !row.id ){
                $.messager.alert("系统提示", "请选择正确格式的图片","error");
                return;
            }
        }
        var filesize = 0;
        if(browserCfg.firefox || browserCfg.chrome ){
            if(obj_file.files[0]){
                filesize = obj_file.files[0].size;

            }
        }else if(browserCfg.ie){
            if(obj_file.value){
                var obj_img = document.getElementById('tempimg');
                obj_img.dynsrc=obj_file.value;
                filesize = obj_img.fileSize;
            }
        }else{
            alert(tipMsg);
            return;
        }
        if(filesize==-1){
            alert(tipMsg);
            return;
        }else if(filesize>maxsize){
            alert(errMsg);
            return;
        }

        var oData = new FormData(document.getElementById("serviceForm"));
        console.log(oData);
        $.ajax({
            url: basePath + "/sys-service/save" ,
            type: 'POST',
            data:  oData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                    $.messager.alert('系统提示', '保存成功', 'info');
                    loadDict();
                    reset();
                $("#serviceDialog").dialog("close");
            },
            error: function (returndata) {
                    $.messager.alert('系统提示', '保存失败', 'error');
                    loadDict();
                    reset();
            }
        });
        $.postJSON(basePath + "/sys-service/save", JSON.stringify(oData), function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            flag = 0;
            loadDict();
            reset();
        })


    });

    /**
    *基础服务价格模态
    */
    $("#serviceDetailDialog").dialog({
        title: '基础服务价格',
        width: 1000,
        height: 350,
        closed:true

    });
    /**
    *基础服务菜单模态
    */
    $("#serviceMenuDialog").dialog({
        title: '服务菜单维护',
        width: 400,
        height:450,
        closed:true

    });
    /**
     *基础服务价格数据框
     */
     $("#serviceDetailDg").datagrid({
        fit: true,
        fitColumns: true,
        //striped: true,
        singleSelect: true,
        toolbar: '#serviceDetailTb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title:"serviceId",
            field:"serviceId",
            hidden:true
        },{
            title:"服务价格",
            field:"servicePrice",
            width:"40%",
            editor:{
                type:"textbox",options:{

                }
            }
        },{
            title:"服务时限",
            field:"serviceTimeLimit",
            width:"40%",
            editor:{
                type:"combobox",options:{
                    valueField:"value",
                    textField:"text",
                    data:[{
                        text:"年",
                        value:"年"
                    },{
                        text:"月",
                        value:"月"
                    }]
                }
            }
        }]],
         onSelect:function(rowIndex,rowDate){
             if (editIndex == undefined) {
                 $("#serviceDetailDg").datagrid("beginEdit", rowIndex);
                 editIndex = rowIndex;
             } else {
                 $("#serviceDetailDg").datagrid("endEdit", editIndex);
                 $("#serviceDetailDg").datagrid("beginEdit", rowIndex);
                 editIndex = rowIndex;
             }
         }

    });

    /**
     * 明细维护
     */
    $("#detailBtn").on("click", function () {
        $("#serviceDetailDg").datagrid("loadData",{total:0,rows:[]});
        var row = $("#serviceDg").datagrid("getSelected");
        if(row){
            var url = basePath + "/sys-service/detail-list?serviceId=" + row.id;
            $("#serviceDetailDg").datagrid("reload",url);
            $("#serviceDetailDialog").dialog("open");
        }else{
            $.messager.alert("提示","请选择一个服务项目","info");
        }

    });
    /**
     * 基础服务价格添加
     */
    $("#addDetailBtn").on("click", function () {
        stopEdit();
        var rows = $("#serviceDetailDg").datagrid("getRows");
        console.log(rows.length)
        if(rows.length >= 2){
            $.messager.alert("提示","最多只能添加一个月价格，一个年价格","error");
            return;
        }
        var row = $("#serviceDg").datagrid("getSelected");
        if(rows.length ==0){
            $("#serviceDetailDg").datagrid("appendRow",{serviceId:row.id,serviceTimeLimit:'月'});
        }else{
            $("#serviceDetailDg").datagrid("appendRow",{serviceId:row.id,serviceTimeLimit:'年'});

        }
        var rows = $("#serviceDetailDg").datagrid("getRows");
        var addRowIndex = $("#serviceDetailDg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#serviceDetailDg").datagrid('selectRow', editIndex);
        $("#serviceDetailDg").datagrid('beginEdit', editIndex);


    });
    /**
     * 基础服务价格修改
     */
    $("#editDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid("getSelected");
        var index = $("#serviceDetailDg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });
    /**
     * 基础服务价格删除
     */
    $("#delDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#serviceDetailDg").datagrid('getRowIndex', row);
            $("#serviceDetailDg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });
    /**
     * 基础服务价格保存
     */
    $("#submitDetailBtn").on("click",function(){
        var flag = 0;

        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
        }
        var rows = $("#serviceDetailDg").datagrid("getRows");
        if(rows.length == 0){
            $.messager.alert("提示","请添加一条数据","error");
            return;
        }else{
            $.each(rows, function (index,row) {
                if(!row.servicePrice){
                    flag = 1;
                }
            });

        if(flag == 1){
            $.messager.alert("提示","添加服务价格","error");
            return;
        }
        }
        if(rows.length >= 2){
            if(rows[0].serviceTimeLimit == rows[1].serviceTimeLimit){
                $.messager.alert("提示","最多只能添加一个月价格，一个年价格","error");
                return;
            }
        }

        var insertData = $("#serviceDetailDg").datagrid("getChanges", "inserted");
        var updateDate = $("#serviceDetailDg").datagrid("getChanges", "updated");
        var deleteDate = $("#serviceDetailDg").datagrid("getChanges", "deleted");

        var priceBeanVo = {};
        priceBeanVo.inserted = insertData;
        priceBeanVo.deleted = deleteDate;
        priceBeanVo.updated = updateDate;
        //console.log(priceBeanVo);
        //console.log(JSON.stringify(priceBeanVo));
        if (priceBeanVo) {
            $.postJSON(basePath + "/sys-service/save-detail", JSON.stringify(priceBeanVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDetail()
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#serviceDetailDg').datagrid('loadData', { total: 0, rows: [] });
            })
        }
    });




    /**
     * 菜单维护
     */
    $("#menuBtn").on("click", function () {
        var row = $("#serviceDg").datagrid("getSelected");
        if(row){
            var menus=[];
            var menuTreeData=[];
            var serviceVsMenu;
            checkedMenus = [];
            $.ajax({
                type: 'get',
                async:false,
                url: basePath + "/sys-service/service-vs-menu-list?serviceId=" + row.id,
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    serviceVsMenu = data
                },
                error: function(data){

                }
            });
            var menuPromise = $.get(basePath + "/menuDict/list", function (data) {
                $.each(data,function(index,item){
                    var menu ={} ;
                    menu.id = item.id ;
                    menu.pid = item.pid ;
                    menu.text = item.menuName ;
                    menu.state = 'open' ;
                    menu.checked = false ;
                    menu.children=[] ;
                    menus.push(menu) ;
                });
                for(var i = 0 ;i<menus.length;i++){
                    //判断儿子节点
                    for(var j = 0 ;j<menus.length;j++){
                        if(menus[i].id ==menus[j].pid){
                            menus[i].children.push(menus[j]) ;
                        }
                    }
                    //判断服务菜单选中
                    for(var x = 0 ; x<serviceVsMenu.length;x++){
                        if (serviceVsMenu[x].menuId == menus[i].id && menus[i].children.length == 0 ){
                            menus[i].checked = true;
                        }
                    }
                    //判断是不是根节点  start
                    if(menus[i].children.length>0 && !menus[i].pid){
                        menuTreeData.push(menus[i]) ;
                    }

                    if(!menus[i].pid&&menus[i].children.length<=0){
                        menuTreeData.push(menus[i]) ;
                    }
                    //判断是不是根节点  end
                }
            });

            menuPromise.done(function () {
                $("#serviceMenuTree").tree('loadData',menuTreeData) ;
            });
            $("#serviceMenuDialog").dialog("open");
        }else{
            $.messager.alert("提示","请选择一个服务项目","info");
        }
    });
    /**
     * 菜单树
     */
    $("#serviceMenuTree").tree({
        method:'get',
        animate:true,
        checkbox:true
        //onlyLeafCheck:true
    });
    /**
     * 菜单明细保存
     */
    $("#submitMenuBtn").on("click", function () {
        var row = $("#serviceDg").datagrid("getSelected");
        var menuVsServices  = [];
        var menuVsServicesParent  = [];
        var menus = $('#serviceMenuTree').tree('getChecked');
        for (var n = 0;n<menus.length;n++){
            if(menus[n].children.length == 0){
                var menuVsService = {};
                menuVsService.serviceId = row.id;
                menuVsService.menuId = menus[n].id;
                menuVsServices.push(menuVsService);
            }

            var parentNode = $('#serviceMenuTree').tree('getParent',menus[n].target);
            var parentPid;
            if(parentNode){
                parentPid = parentNode.pid ;
                do{
                    var menuVsServiceParentNode = {};
                    var ok = 0;//是否父节点存在标志

                    if(menuVsServicesParent.length == 0 ){
                        menuVsServiceParentNode.serviceId = row.id;
                        menuVsServiceParentNode.menuId = parentNode.id;
                        menuVsServicesParent.push(menuVsServiceParentNode);
                    }
                    for(var i = 0; i < menuVsServicesParent.length ; i++){
                        if(menuVsServicesParent[i].menuId == parentNode.id){
                            ok = 1;
                            break;
                        }
                    }
                    if(ok==0 ){
                        menuVsServiceParentNode.serviceId = row.id;
                        menuVsServiceParentNode.menuId = parentNode.id;
                        menuVsServicesParent.push(menuVsServiceParentNode);
                    }
                        var parentNode = $('#serviceMenuTree').tree('getParent',parentNode.target);
                        if(parentNode){
                            parentPid = parentNode.pid;
                        }
                }while(parentPid)
            }


        }
        //console.log(menuVsServices);
        //console.log(menuVsServicesParent);
        //console.log(menuVsServices.concat(menuVsServicesParent));
        if(menuVsServices.concat(menuVsServicesParent).length > 0){
            $.postJSON(basePath + "/sys-service/save-serviceVsMenu",JSON.stringify(menuVsServices.concat(menuVsServicesParent)), function () {
                $.messager.alert("系统提示", "保存成功", "info");
            })
        }else{
            $.messager.alert("系统提示", "请选择菜单", "info");
        }


    });

    /**
     *全部展开
     */
    $("#menuExpandBtn").on("click", function () {
        $('#serviceMenuTree').tree('expandAll');
    });
    /**
     *全部折叠
     */
    $("#menuCollapseBtn").on("click", function () {
        $('#serviceMenuTree').tree('collapseAll');
    });
    /**
     *菜单查询
     */
    $("#menuSelectBtn").on("click", function () {
        var menuName = $("#searchMenu").textbox("getValue");
        if(menuName){
            //调用查询方法 返回id
            var node = $('#serviceMenuTree').tree('find','35E111DB41F9420B9B19B200A41488CB');
            $('#serviceMenuTree').tree('expandTo', node.target);
            $('#serviceMenuTree').tree('scrollTo', node.target).tree('select', node.target);
        }
    });

    /**
     * 加载服务
     */
    var loadDict = function () {
        $.get(basePath + "/sys-service/list" , function (data) {
            $("#serviceDg").datagrid('loadData', data);
        });
    };
    /**
     * 加载服务明细
     */
    var loadDetail = function () {
        var row = $("#serviceDg").datagrid("getSelected");
        $.get(basePath + "/sys-service/detail-list?serviceId=" + row.id , function (data) {
            $("#serviceDetailDg").datagrid('loadData', data);
        });
    };
    /**
     * 重置服务明细
     */
    var reset = function () {
        $("#id").textbox("setValue","");
        $("#serviceName").textbox("setValue","");
        $("#serviceType").combobox("setValue","");
        $("#serviceClass").combobox("setValue","");
        $("#serviceImage").val("");
        $("#serviceDescription").val("");
    };

    loadDict();

//    服务参数设置

    var paramEditIndex = undefined ;

    $("#serviceParamDialog").dialog({
        modal:true,
        width:800,
        height:600,
        title:'参数设置',
        closed:true,
        onBeforeOpen:function(){
            var row = $("#serviceDg").datagrid('getSelected') ;
            var serviceId = row.id ;

            var options = $("#serviceParamDg").datagrid('options') ;
            options.url= basePath+"/service-param/list?serviceId="+serviceId ;
            $("#serviceParamDg").datagrid('reload') ;
        }
    })

    //定义参数表格
    $("#serviceParamDg").datagrid({
        fit:true,
        fitColumn:true,
        method:'GET',
        columns:[[{
            title:'id',
            field:'id',
            hidden:true
        },{
            title:'serviceId',
            field:'serviceId',
            hidden:true
        },{
            title:'参数名称',
            field:'paramName',
            width:'20%',
            editor:{type:'text',options:{}}
        },{
            title:'值域',
            field:'valueRange',
            width:'75%',
            editor:{type:'text',options:{}}
        }]],
        onDblClickRow:function(index,row){
            if(paramEditIndex==0||paramEditIndex){
                $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
                paramEditIndex = index ;
            }else{
                paramEditIndex = index ;
            }
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }
    }) ;

    //添加参数按钮
    $("#addServiceParamBtn").on('click',function(){
        var row = $("#serviceDg").datagrid('getSelected') ;
        if(paramEditIndex==undefined){
            var test = $("#serviceParamDg").datagrid('appendRow',{serviceId:row.id}) ;
            var rows = $("#serviceParamDg").datagrid('getRows') ;
            var temIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[rows.length-1]) ;
            paramEditIndex = temIndex ;
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }else{
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            $("#serviceParamDg").datagrid("appendRow",{serviceId:row.id})
            var rows = $("#serviceParamDg").datagrid('getRows') ;
            var temIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[rows.length-1]) ;
            paramEditIndex = temIndex ;
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }
    }) ;

    //删除按钮
    $("#delServiceParamBtn").on('click',function(){

        if(paramEditIndex==0||paramEditIndex){
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            paramEditIndex = undefined ;
        }

        var rows = $("#serviceParamDg").datagrid('getSelections') ;
        for(var i = 0 ;i<rows.length;i++){
            var tempIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[i]) ;
            $("#serviceParamDg").datagrid('deleteRow',tempIndex) ;
        }

    })

    //保存按钮
    $("#submitServiceParamBtn").on('click',function(){


        var beanChangeVo = {} ;
        beanChangeVo.inserted=[] ;
        beanChangeVo.deleted=[] ;
        beanChangeVo.updated=[] ;

        if(paramEditIndex==0||paramEditIndex){
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            paramEditIndex=undefined ;
        }

        var inserted = $("#serviceParamDg").datagrid('getChanges','inserted') ;
        console.log(inserted) ;
        for(var i = 0 ;i<inserted.length;i++){
            if(inserted[i].paramName){
                beanChangeVo.inserted.push(inserted[i]) ;
            }
        }

        var deleted = $("#serviceParamDg").datagrid('getChanges','deleted') ;
        console.log(deleted) ;
        for(var i = 0 ;i<deleted.length;i++){
            if(deleted[i].id){
                beanChangeVo.deleted.push(deleted[i]) ;
            }
        }

        var updated = $("#serviceParamDg").datagrid('getChanges','updated') ;
        for(var i = 0 ;i<updated.length;i++){
            if(updated[i].paramName){
                beanChangeVo.updated.push(updated[i]) ;
            }
        }
        $.postJSON(basePath+"/service-param/merge",JSON.stringify(beanChangeVo),function(data){
            $("#serviceParamDg").datagrid('reload') ;
            $.messager.alert("系统提示","操作成功",'info') ;
        })
    }) ;
    //参数设置按钮
    $("#paramBtn").on('click',function(){

        var row = $("#serviceDg").datagrid('getSelected') ;

        if(row.serviceClass=='1'){
            $.messager.alert("系统提示","个人服务，不许允许定制参数")
            return ;
        }
        $("#serviceParamDialog").dialog("open") ;
    }) ;
});