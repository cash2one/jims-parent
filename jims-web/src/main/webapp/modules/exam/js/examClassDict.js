
/**
 * 检查类别维护
 * @author tangxb
 * @version 2016-04-29
 */


$(function () {


    var classCodeTest=false;
    var deptDictList=[];        //获取科室列表
    $.get(basePath + "/dept-dict/list?orgId="+config.org_Id,function(data){
        deptDictList=data;
    })


    //检查类别
    $("#examClassGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#classft',
        url: basePath +  "/examClassDict/listByOrgId?orgId="+config.org_Id,
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '检查类别编码',
            field: 'examClassCode',
            width: '20%'
        }, {
            title: '检查类别名称',
            field: 'examClassName',
            width: '20%'
        },{
            title: '执行科室',
            field: 'performBy',
            width: '20%',
            formatter:function(value,row,index){
                var performByName=value;
                $.each(deptDictList,function(index,item){
                    if(item.deptCode==value){
                        performByName= item.deptName;
                    }
                })
                return performByName;
            }
        },{
            title: '打印格式',
            field: 'printStyle',
            width: '20%',
            formatter:function(value,row,index){
                var printStyleName=value;
                if(value==0){
                    printStyleName="打印";
                }else{
                    printStyleName="传真";
                }
                return printStyleName;
            }
        },{
            title: '特殊科室',
            field: 'specialtiesDept',
            width: '20%',
            formatter:function(value,row,index){
                var specialtiesDeptName=value;
                if(value==0){
                    specialtiesDeptName="否";
                }else{
                    specialtiesDeptName="是";
                }
                return specialtiesDeptName;
            }
        }]],
        onClickRow: function (rowIndex, rowData) {
            var options = $("#examSubclassGrid").datagrid('options');
            options.url = basePath + "/examSubclassDict/list-by-class?orgId=" + config.org_Id+ "&className=" + rowData.examClassName;
            $("#examSubclassGrid").datagrid('reload');
        }
    });

    //验证examClassName
    $('#examClassName').textbox({
        onChange: function(value) {
            var examClassName = $("#examClassName").textbox("getValue");
            var data=$("#examSubClassNameParent").combobox("getData");
            for(var i=0;i<data.length;i++){
                if(data[i].examClassName==examClassName){
                    $.messager.alert('系统提示', '分类名称重复，请重新输入', 'error');
                    $("#examClassName").textbox("setValue",'');
                }
            }
        }
        });



    //检查子类别
    $("#examSubclassGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers:true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title: "examClassName",
            field: "examClassName",
            hidden: true
        }, {
            title: '子类',
            field: 'examSubclassName',
            width:'50%'
        }, {
            title: '拼音码',
            field: 'inputCode',
            width:'50%'
        }]]
    });


    //检查类别模态框
    $("#classWin").window({
        title: '检查分类维护',
        closed: true,
        modal: true
    });
    //检查子类别模态框
    $("#subclassWin").window({
        title: '检查子分类维护',
        closed: true,
        modal: true,
        onClose: function () {
            $("#subclassForm").form('reset');
        },
        onOpen:function(){
            var row  = $("#examClassGrid").datagrid('getSelected') ;
            if(row){
                $("#examSubClassNameParent").combobox('setValue',row.examClassName) ;
                $('#examSubClassNameParent').combobox('disable');
            }
        }
    });

    //类别项目下拉框
    $("#examSubClassNameParent").combobox({
        width:176,
        editable:false,
        valueField: 'examClassName',
        textField: 'examClassName',
        method: 'GET',
        url: basePath +  "/examClassDict/listByOrgId?orgId="+config.org_Id,
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            console.log(data)
            if (data.length > 0) {
                $(this).combobox('setValue', data[0].examClassName);
            }
        }
    });

    //执行科室下拉框
    $("#performBy").combogrid({
        width:176,
        //editable:false,
        idField: 'deptCode',
        textField: 'deptName',
        method: 'GET',
        mode:'remote',
        url: basePath + "/dept-dict/findListWithFilter?orgId="+config.org_Id,
        columns:[[
            {field:'deptCode',title:'科室代码',width:64,align : "center"},
            {field:'deptName',title:'科室名称',width:110,halign : "center",align : "left" },
            //{field: 'inputCode', title: '拼音码', width: 60},
        ]]
    });


    $("#printStyle").combobox({
        width:176,
        editable:false,
        valueField: 'value',
        textField: 'text',
        method: 'GET',
        data: [{
            value: '0',
            text: '打印',
            selected: true
        }, {
            value: '1',
            text: '传真'
        }]
    });
    //特殊科室下拉框
    $("#specialtiesDept").combobox({
        width:176,
        editable:false,
        valueField: 'value',
        textField: 'text',
        method: 'GET',
        data: [{
            value: '0',
            text: '否',
            selected: true
        }, {
            value: '1',
            text: '是'
        }]
    });

    //添加分类按钮
    $("#addClassBtn").on('click', function () {
        $("#classWin").window('open');
    });
    //取消分类按钮
    $("#cancelClassBtn").on('click', function () {
        $("#classForm").form('reset');
        $("#classWin").window('close');
    });

    //添加子分类按钮
    $("#addBtn").on('click', function () {
        var rows = $('#examClassGrid').datagrid('getSelected');
        if(!rows){
            alert('请先选择左栏的类别');
            return false;
        }
        $("#subclassWin").window('open');
    });
    //取消子分类按钮
    $("#cancelSubclassBtn").on('click', function () {
        $("#subclassForm").form('reset');
        $("#subclassWin").window('close');
    });

    //分类项目保存
    $("#saveClassBtn").on('click', function () {
        var performByCode=$("#performBy").combogrid('getValue');
        var performByName=$("#performBy").combogrid('getText');
        var examClassCode = $("#examClassCode").val().trim();
        var examClassName = $("#examClassName").val().trim();
        var rex = /^[0-9]|[a-z]|[A-Z]$/;
        if(!rex.test(examClassCode)){
            alert('请保证编码是1位数字或字母');
            return false;
        }
        if(!limitLength(examClassName,20,'类别名称')){
            return false;
        }
        var a=false;
        for(var i=0;i<deptDictList.length;i++){
            if(deptDictList[i].deptCode==performByCode){
                if(deptDictList[i].deptName==performByName){
                    a=true;
                }
            }
        }
        if(a){
            var saveObj = {};
            saveObj.examClassCode = examClassCode;
            saveObj.examClassName = examClassName;
            saveObj.inputCode = "";
            saveObj.performBy = $("#performBy").combogrid('getValue');
            saveObj.printStyle = $("#printStyle").combobox('getValue');
            saveObj.specialtiesDept = $("#specialtiesDept").combobox('getValue');
            saveObj.orgId = config.org_Id;
            //console.log(saveObj);
            $.postJSON(basePath + "/examClassDict/save", JSON.stringify(saveObj), function (data) {
                $("#classWin").window('close');
                $.messager.alert('系统提示', '分类添加成功','info');
                $("#examClassGrid").datagrid('reload');
                $("#classForm").form('reset');
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'error');
            });
        }else{
            var performByCode=$("#performBy").combogrid('getDataOptions');
            $.messager.alert('系统提示', '请重新选择科室', 'error');
        }
    });
    //分类项目删除
    $("#removeClassBtn").on('click', function () {
        var row = $("#examClassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", '请选择要删除的项目', 'error');
            return;
        }
        //首先判断有没有子项目
        var promise = $.get(basePath + "/examSubclassDict/list-by-class?orgId=" + config.org_Id+ "&className=" + row.examClassName, function (data) {
            if (data.length > 0) {
                $.messager.alert("系统提示", '存在该类别的子类别，不允许删除！', 'error');
                return;
            }

            $.messager.confirm('系统提示','确定要进行删除操作吗',function(r){
                if(r){
                    $.postJSON(basePath + "/examClassDict/del" , row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        $("#examClassGrid").datagrid('reload');
                    });
                }
            });

        })

    });
    function limitLength(value, byteLength,title) {
        var newvalue = value.replace(/[^\x00-\xff]/g, "**");
        var length = newvalue.length;
        //当输入文字的字节数小于设定的字节数
        if (length * 1 <=byteLength * 1){
            return true;
        }
        var limitDate = newvalue.substr(0, byteLength);
        var count = 0;
        var limitvalue = "";
        for (var i = 0; i < limitDate.length; i++) {
            var flat = limitDate.substr(i, 1);
            if (flat == "*") {
                count++;
            }
        }
        var size = 0;
        var istar = newvalue.substr(byteLength * 1 - 1, 1);//校验点是否为“×”
        //if 基点是×; 判断在基点内有×为偶数还是奇数
        if (count % 2 == 0) {
            //当为偶数时
            size = count / 2 + (byteLength * 1 - count);
            limitvalue = value.substr(0, size);
        } else {
            //当为奇数时
            size = (count - 1) / 2 + (byteLength * 1 - count);
            limitvalue = value.substr(0, size);
        }
        alert( "["+title+"]最大输入" + byteLength + "个字节（相当于"+byteLength /2+"个汉字）！");
        //document.getElementById(csId).value = limitvalue;
        return false;
    }

    //子分类项目保存
    $("#saveSubclassBtn").on('click', function () {
        var examSubName = $("#examSubClassName").val().trim();
        if(examSubName==""){
            alert('请输入子类名称');
            return false;
        }else if(!limitLength(examSubName,12,'子分类名称')){
            return false;
        }
            var saveObj = {};
            saveObj.examClassName = $('#examSubClassNameParent').combobox('getValue');
            saveObj.examSubclassName = examSubName;
            saveObj.inputCode = "";
            saveObj.orgId = config.org_Id;
            saveObj.id =  $("#id").textbox('getValue');

        var exam=$('#examSubclassGrid').datagrid('getData');

        for(var i=0;i<exam.rows.length;i++){
            if(exam.rows[i].examSubclassName==saveObj.examSubclassName){
                $.messager.alert('系统提示', '子类名称重复，请重新命名！');

                return false;
            }
        }
            $.postJSON(basePath + "/examSubclassDict/save", JSON.stringify(saveObj), function (data) {
                //$("#subclassWin").window('close');
                $.messager.alert('系统提示', '子分类添加成功');
                $("#examSubclassGrid").datagrid('reload');
                $("#subclassForm").form('reset');
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'info');
            });


    });

     //子分类项目编辑
    $("#editBtn").on('click', function () {
        var row = $("#examSubclassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", "请选择要编辑的记录", "info");
            return;
        }
        $('#examSubClassNameParent').combobox('setValue', row.examClassName);
        $("#examSubClassName").val( row.examSubclassName);
        $("#id").textbox('setValue', row.id);

        $("#subclassWin").window('open');
    });

    //子分类项目删除
    $("#removeBtn").on('click', function () {

        var row = $("#examSubclassGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert('系统提示', '请选择要删除的项目', 'info');
            return;
        }

        $.messager.confirm('系统提示','确定要进行删除操作吗',function(r){
            if(r){
                $.postJSON(basePath + "/examSubclassDict/del", row.id, function (data) {
                    $.messager.alert("系统提示", '删除成功', 'info');
                    $("#examSubclassGrid").datagrid('reload');
                })
            }
        });

    });


    $.extend($.fn.validatebox.defaults.rules, {

        examClassCode: {//param的值为[]中值
            validator: function (value) {
                var reg = /^[a-zA-Z0-9]{0,1}$/;
                return reg.test(value);
            },
            message: '编码只能是1位数字或字母.'
        }

    })

});