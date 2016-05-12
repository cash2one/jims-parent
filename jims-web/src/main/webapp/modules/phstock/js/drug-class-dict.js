/***
 * 朱齐
 * 2016-5-10 11:36:23
 * 药品类别字典
 */
$(function(){
    var list=[];
    var orgId=parent.config.org_Id;
    var obj = [];
    var way = "*";
    var classCodeWay=true;
    var classNameWay=true;

    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").treegrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    //树形
    $("#dg").treegrid({
        idField: "id",
        treeField: "className",
        fitColumns: true,
        columns: [[{
            title: 'id',
            field: 'id',
            hidden: true
        }, {
            title: '类别名称',
            field: 'className',
            width: "40%"
        }, {
            title: '类别代码',
            field: 'classCode',
            width: "40%"
        },{
            title: '父类Id',
            field: 'parentId',
            hidden: true
        }]]
    });

    //弹出框中的下拉列表
    $('#drugParentId').combogrid({
        delay: 300,
        width:'196px',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/drug-class-dict/list-parent?orgId='+orgId+"&parentId="+"*",
        idField: 'classCode',
        textField: 'className',
        columns: [[
            {field:'classCode',title:'父类编码',width:"80px",sortable:true},
            {field:'className',title:'父类名称',width:"80px",sortable:true}
        ]]
    });

    //为树形准备数据
    var loadDept = function (){
        obj = [];
        var loadPromise = $.get(basePath + '/drug-class-dict/list?orgId='+orgId, function (data) {
            list=data;
            for(var i=0;i<data.length;i++) {
                if (data[i].parentId == "*" && data[i].delFlag=="0") {
                    var depts = {};
                    depts.id = data[i].id;
                    depts.classCode = data[i].classCode;
                    depts.className = data[i].className;
                    depts.parentId = data[i].parentId;
                    depts.start = "open";
                    depts.children = [];
                    obj.push(depts);
                }
            }
        })

        loadPromise.done(function (){
            for(var i=0;i<obj.length;i++){
                var child=[];
                for(var j=0;j<list.length;j++){
                    if(list[j].parentId==obj[i].classCode &&list[j].delFlag=="0"){
                        var depts = {};
                        depts.id = list[j].id;
                        depts.classCode = list[j].classCode;
                        depts.className = list[j].className;
                        depts.parentId = list[j].parentId;
                        depts.start = "open";
                        depts.children = [];
                        child.push(depts);
                    }
                }
                obj[i].children=child;
            }
            $("#dg").treegrid('loadData', obj);
        })
        $("#drugParentId").combogrid('enable');
    }
    loadDept();

    //弹出框默认值清空
    var reset = function(){
        $("#drugParentIdChange").html("");
        $("#drugClassCodeChange").html("");
        $("#drugClassNameChange").html("");
        $("#drugId").textbox("setValue","");
        $('#drugParentId').combogrid('setValue',"");
        $("#drugClassCode").textbox("setValue","");
        $("#drugClassName").textbox("setValue","");

    }

    $("#bottom").datagrid({
        footer: '#tb',
        border: false
    });
    $('#cc').layout('panel', 'north').panel('resize', {height: 'auto'});
    $('#cc').layout('panel', 'south').panel('resize', {height: 'auto'});
    $("#cc").layout({
        fit: true
    });


    //点击修改按钮
    $("#updateBtn").on('click', function () {
        reset();
        var node = $("#dg").treegrid('getSelected');
        if (node == null) {
            $.messager.alert("系统提示", "请选中要修改的行");
            return;
        }else{
            $('#drugParentId').combogrid();
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改药品类别');
            $("#drugParentId").combogrid('disable');

            $("#drugId").textbox('setValue', node.id);
            if(node.parentId!="*"){
                way="";
                var str1 = node.classCode.substring(2);
                $("#drugClassCode").textbox('setValue', str1);
            }else{
                way="*";
                $("#drugClassCode").textbox('setValue', node.classCode);
            }
            $("#drugClassName").textbox('setValue', node.className);
            $("#drugParentId").combogrid('setValue', node.parentId);
        }
    })

    //点击新增父类按钮
    $("#addParentBtn").on('click', function () {
        reset();
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增药品父类');
        way="*";
        //$("#drugParentId").textbox('readonly',true);
        $("#drugParentId").combogrid('disable');
        $("#drugId").textbox('setValue', "");
        $("#drugParentId").textbox('setValue', "*");
        $("#drugClassCode").textbox('setValue', "");
        $("#drugClassName").textbox('setValue',"");
    })

    //新增子类药品
    $("#addChildrenBtn").on('click', function () {
        reset();
        $("#drugClassCodeChange").html("");
        way="z";
        $('#drugParentId').combogrid();
        $("#drugParentId").combogrid('enable');
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增药品子类');
        $("#drugId").textbox('setValue', "");
        $("#drugParentId").textbox('setValue', "");
        $("#drugClassCode").textbox('setValue', "");
        $("#drugClassName").textbox('setValue',"");
    })

    //点击保存修改和新增
    $("#saveBtn").on('click', function () {
        var drugClassDict={};
        drugClassDict.id=$("#drugId").textbox('getValue');
        classCode=$("#drugClassCode").textbox('getValue');
        drugClassDict.className=$("#drugClassName").textbox('getValue');
        if($("#drugParentId").textbox('getValue')=="" && way=="*"){
            drugClassDict.parentId="*"
        }else{
            drugClassDict.parentId=$("#drugParentId").combogrid('getValue');

        };
        drugClassDict.orgId=orgId;
        drugClassDict.delFlag="0";
        var all=true;

        // 验证是否为空,若不为空，将parentId 与子类code组合
        if(classCode==""){
            $("#drugClassCodeChange").html("请输入代码");
            all=false;
        }else{
            if(drugClassDict.parentId!="*"){
                drugClassDict.classCode=drugClassDict.parentId+classCode;
            }else{
                drugClassDict.classCode=classCode;
            }
        }


        if(drugClassDict.className==""){
            $("#drugClassNameChange").html("请输入名称");
            all=false;
        }

        if(drugClassDict.parentId==""){
            $("#drugParentIdChange").html("请选择父类");
            all=false;
        }

        if(all && classCodeWay && classNameWay){
            $('#dlg').dialog('close');
            $.postJSON(basePath + "/drug-class-dict/save", JSON.stringify(drugClassDict), function (data){
                $.messager.alert("系统提示", "保存成功");
                loadDept();
            })
            $("#drugParentId").combogrid('enable');
        }
    })

    //删除按钮
    $("#removeBtn").on('click', function () {
        var row = $("#dg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的菜单");
            return;
        }

        var children = $("#dg").treegrid('getChildren', row.id);
        if (children.length > 0) {
            $.messager.alert("系统提示", "请先删除子类别");
            return;
        } else {
            $.messager.confirm("系统提示", "确认删除:【" + row.className + "】的类别吗?", function (r) {
                if (r) {
                    //$.postJSON(basePath + "/menuDict/del" ,row.id, function (data) {
                    var drugClassDict={};
                    drugClassDict.id=row.id;
                    drugClassDict.classCode=row.classCode;
                    drugClassDict.className=row.className;
                    drugClassDict.parentId=row.parentId;
                    drugClassDict.orgId=orgId;
                    drugClassDict.delFlag="1";
                    console.log(drugClassDict);

                    $.postJSON(basePath + "/drug-class-dict/save", JSON.stringify(drugClassDict), function (data){
                        $.messager.alert("系统提示", "删除成功");
                        loadDept();
                    })
                }
            })
        }
    });

    //验证calssCode
    $('#drugClassCode').textbox({
        onChange: function (value) {
            var classCode=$("#drugClassCode").textbox('getValue');
            var patrn=/[a-zA-Z0-9]/;
            console.log(way);
            var drugId=$("#drugId").textbox("getValue");
            var parentId=$("#drugParentId").combogrid("getValue");
            if(way!="*"){
                if(classCode.length!=3){
                    $("#drugClassCodeChange").html("请输入3位类别码");
                    classCodeWay=false;
                }else{
                    $("#drugClassCodeChange").html("");
                    if(patrn.test(classCode)){
                        $("#drugClassCodeChange").html("");
                        classCodeWay=true;
                    }else{
                        $("#drugClassCodeChange").html("请输入字母数字");
                        classCodeWay=false;
                    }
                }
            }else{
                if(classCode.length!=2){
                    $("#drugClassCodeChange").html("请输入2位类别码");
                    classCodeWay=false;
                }else{
                    $("#drugClassCodeChange").html("");
                    var check=false;
                    for(var i=0;i<list.length;i++){
                        if(list[i].classCode==classCode &&list[i].id!=drugId){
                            check=true;
                        }
                    }
                    if(check){
                        $("#drugClassCodeChange").html("编码已存在");
                        classCodeWay=false;
                    }else{
                        if(patrn.test(classCode)){
                            $("#drugClassCodeChange").html("");
                            classCodeWay=true;
                        }else{
                            $("#drugClassCodeChange").html("请输入字母数字");
                            classCodeWay=false;
                        }
                    }
                }
            }
        }
    })

    //className重名检测
    $('#drugClassName').textbox({
        onChange: function (value) {
            var className=$("#drugClassName").textbox('getValue');
            var drugId=$("#drugId").textbox("getValue");
            var check=false;
            for(var i=0;i<list.length;i++){
                if(list[i].className==className &&list[i].id!=drugId){
                    check=true;
                }
            }
            if(check){
                $("#drugClassNameChange").html("名称已存在");
                classNameWay=false;
            }else{
                $("#drugClassNameChange").html("");
                classNameWay=true;
            }
        }
    })

    //下拉框选择之后清除提示
    $("#drugParentId").combogrid({
        onChange:function(value){
            $("#drugParentIdChange").html("");
        }
    })
})
