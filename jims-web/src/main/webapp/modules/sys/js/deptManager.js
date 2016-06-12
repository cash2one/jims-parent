$(function () {

        var property = [];
        var orgId= parent.parent.config.org_Id;
        //设置列
        $("#tt").treegrid({
            //fit: true,
            fit: true,

            idField: "id",
            treeField: "deptName",
            footer: '#tb',
            fitColumns: true,
            columns: [[{
                title: 'id',
                field: 'id',
                hidden: true
            }, {

                title: '科室名称',
                field: 'deptName',
                width:'200'

            }, {
                title: '科室编码',
                field: 'deptCode',
                width: '200'

            }, {
                title: '拼音码',
                field: 'inputCode' ,
                width: '200'

            }, {
                title: '科室属性',
                field: 'deptPropertity'  ,
                width: '200'

            }
            ]]
        });

        var loadDept = function () {

            var depts = [];
            var treeDepts = [];

            //var orgId=1
            var loadPromise = $.get("/service/dept-dict/list?orgId="+ orgId, function (data) {
                $.each(data, function (index, item) {
                    var obj = {};
                    obj.deptName = item.deptName;
                    obj.id = item.id;
                    obj.deptCode = item.deptCode;
                    obj.inputCode = item.inputCode;
                    obj.deptPropertity = item.deptPropertity;
                    obj.parentId = item.parentId;
                    obj.children = [];

                    depts.push(obj);

                });

            });


            loadPromise.done(function () {
                for (var i = 0; i < depts.length; i++) {
                    for (var j = 0; j < depts.length; j++) {
                        if (depts[i].id == depts[j].parentId) {
                            depts[i].children.push(depts[j]);
                        }
                    }
                    if (depts[i].children.length > 0 && !depts[i].parentId) {
                        treeDepts.push(depts[i]);
                    }

                    if (!depts[i].parentId && depts[i].children <= 0) {
                        treeDepts.push(depts[i])
                    }
                }

                $("#tt").treegrid('loadData', treeDepts);
            })
        }
        loadDept();


        $("#cancelBtn").on('click', function () {
            //清空
            $("#deptPropertity").html("");
            $('#dlg').dialog('close');
        });
        var propertyId;
        var deptPropertitys;

        /**
         * 添加科室信息
         */
        $("#addBtn").on('click', function () {



            $("#parentId").combobox('setValue',"");
            $("#deptCode").textbox('setValue', "");
            $("#deptName").textbox('setValue', "");

            $("#deptPropertity").html("");
            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            $("#inputCode").attr('readonly', true);



            $.ajax({
                url: "/service/dept-property/selectProperty?orgId="+orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
                    console.log(data1);
                    var data2 = eval(data1);
                    deptPropertitys = data2;
                    for (var i = 0; i < data2.length; i++) {
                        deptPropertity = data2[i].propertyType;
                        propertyId = "propertyName" + i;
                        var propertyFitem = "propertyFitem" + i;
                        $("#deptPropertity").append("<div class='fitem' id='" + propertyFitem + "'>")
                        $("#" + propertyFitem).append("<label>" + deptPropertity + ": </label>");
                        $("#" + propertyFitem).append("<select style='width: 173px;' name='propertyName' id='" + propertyId + "'/><br/>");

                        $("#" + propertyId).combobox({
                            'url':  '/service/dept-property/selectName/'+deptPropertity+'/'+orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }
                }
            });

            //  clearInput();

            $("#parentId").combobox({
                'url': '/service/dept-dict/selectParentByOrgId?orgId='+orgId,
                valueField: 'id',
                textField: 'deptName'
            });

            $("#deptName").textbox({
               onChange: function ()
            {
                var dept = $("#deptName").val();
                var inputCode = makePy(dept)[0];
                $("#inputCode").textbox('setValue',inputCode);
            }
            });


        });

        //给上级科室的下拉列表赋值
        $("#parentId").combobox({
            'url': '/service/dept-dict/selectParentByOrgId?orgId='+orgId,
            valueField: 'id',
            textField: 'deptName'
        });
        /**
         * 添加科室信息
         */
        $("#addBtnProperty").on('click', function () {


            $("#dlg_property").dialog("open").dialog("setTitle", "添加科室属性");


        });


        /**
         * 保存信息
         */
        $("#saveBtn").on('click', function () {
            //用于存放属性值的数组
            var deptProperty = [];
            //用于存放所有保存的数组
            var deptDictVo = {};
            deptDictVo.id = $("#id").val();
            deptDictVo.deptCode = $("#deptCode").val();
            deptDictVo.deptName = $("#deptName").val();
            //deptDictVo.orgId=parent.config.org_id;
            deptDictVo.orgId=orgId
            deptDictVo.inputCode=$("#inputCode").val();
            deptDictVo.parentId = $("#parentId").combobox('getValue');
            for (var i = 0; i < deptPropertitys.length; i++) {
                var propertyIds = "propertyName" + i;
                console.log(propertyIds);
                var deptIds = $("#" + propertyIds).combobox('getValue');
                deptProperty.push(deptIds);

            }
            deptDictVo.array = deptProperty;
            console.log(deptDictVo);
            jQuery.ajax({
                'type': 'POST',
                'url': "/service/dept-dict/add",
                'contentType': 'application/json',
                'data': JSON.stringify(deptDictVo),
                'dataType': 'json',
                'success': function (data) {
                    console.log(data);
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功");
                        loadDept();
                        clearInput();
                        $("#deptPropertity").html("");
                        $("#dlg").dialog('close');
                    }
                },
                'error': function (data) {
                    $.messager.alert("系统提示", "保存失败");
                }
            });

        });
        /**
         * 保存科室属性信息
         */
        $("#savePropertyBtn").on('click', function () {
            var orgDeptProperty = {};
            orgDeptProperty.propertyType = $("#propertyType").val();
            orgDeptProperty.propertyName = $("#propertyName").val();
            orgDeptProperty.propertyValue = $("#propertyValue").val();
            orgDeptProperty.orgId = orgId;

            if ($("#dm").form()) {
                jQuery.ajax({
                    'type': 'POST',
                    'url': "/service/dept-property/add",
                    'contentType': 'application/json',
                    'data': JSON.stringify(orgDeptProperty),
                    'dataType': 'json',
                    'success': function (data) {
                        if (data.data == "success") {
                            $.messager.alert("系统提示", "保存成功");
                            $("#propertyType").textbox('setValue', "");
                            $("#propertyName").textbox('setValue', "");
                            $("#propertyValue").textbox('setValue', "");
                            $('#dg').datagrid('reload');
                            $("#dlg_property").dialog('close');
                        }
                        if(data.data=="fail")
                        {
                            $.messager.alert("系统提示", "保存失败,值不能重复");
                        }
                    },
                    'error': function (data) {
                        $.messager.alert("系统提示", "保存失败");
                    }
                });
            }
        });


        /**
         * 修改科室信息
         *
         */

        $("#editBtn").on('click', function () {
            $("#deptPropertity").html("");
            $("#deptName").textbox({
                onChange: function ()
                {
                    var dept = $("#deptName").val();
                    var inputCode = makePy(dept)[0];
                    $("#inputCode").textbox('setValue',inputCode);
                }
            });

            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择要修改的科室");
                return;
            }


            $("#id").val(node.id);
            $("#deptCode").textbox('setValue', node.deptCode);
            $("#deptName").textbox('setValue', node.deptName);
            $("#inputCode").textbox('setValue',node.inputCode);
            $("#parentId").combobox('setValue', node.parentId);

            //给上级科室的下拉列表赋值

            $("#dlg").dialog('open').dialog('setTitle', "科室修改");


            $.ajax({
                url: "/service/dept-property/selectProperty?orgId="+orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
                    console.log(data1);
                    var data2 = eval(data1);
                    deptPropertitys = data2;
                    for (var i = 0; i < data2.length; i++) {
                        deptPropertity = data2[i].propertyType;
                        propertyId = "propertyName" + i;
                        var propertyFitem = "propertyFitem" + i;
                        $("#deptPropertity").append("<div class='fitem' id='" + propertyFitem + "'>")
                        $("#" + propertyFitem).append("<label>" + deptPropertity + ": </label>");
                        $("#" + propertyFitem).append("<select style='width: 173px;' name='propertyName' id='" + propertyId + "'/><br/>");
                        $("#" + propertyId).combobox({
                            'url': '/service/dept-property/selectName/'+deptPropertity+'/'+orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });
                      /*  var deptArray = [];

                       deptPropertity=' + deptPropertity+'&orgId='+orgId
                        console.log(propertyIds);


                        var deptPro = node.deptPropertity;
                        var dept = [];
                        dept = deptPro.split(" ");
                        console.log(dept);

                        $("#" + propertyId).combobox({
                            onLoadSuccess: function () {
                                for (var item = 0; item < dept.length; item++) {
                                   // var propertyIds = "propertyName" + item;
                                    $("#" + propertyId).combobox("setValue", dept[item]);

                                }
                            }
                        });*/
                        $("#deptPropertity").append("</div>")
                    }
                }
            });



        });

        /**
         * 删除
         */
        $("#delBtn").on('click', function () {
            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择要删除的科室");
                return;
            }

            if ($("#tt").treegrid("getChildren", node.id).length > 0) {
                $.messager.alert("系统提示", "请先删除子科室，在删除");
                return;
            }


            $.messager.confirm("系统提示", "确定要删除【" + node.deptName + "】吗？", function (r) {
                if (r) {
                    $.ajax({
                        'type': 'POST',
                        'url': "/service/dept-dict/del/",
                        'contentType': 'application/json',
                        'data': id = node.id,
                        'dataType': 'json',
                        'success': function (data) {
                            if (data.data == 'success') {
                                $.messager.alert("系统提示", "删除成功");
                                loadDept();
                            } else {
                                $.messager.alert('提示', "删除失败", "error");
                            }
                        }
                    });
                }
            });

        });


        /**
         * 清除输入框信息
         */
        var clearInput = function () {
            $("#deptCode").textbox('setValue', "");
            $("#deptName").textbox('setValue', "");
            $("#parentId").combobox('setValue', "");
            $("#deptProperty").combobox('setValue', "");

            $("#propertyType").remove();
        }

        //查询
        $("#selectPropertyBtn").on('click',function(){
            var orgDeptPropertyDict={};
            orgDeptPropertyDict.propertyType=  $("#propertyType").textbox('getValue');
            orgDeptPropertyDict.propertyName=$("#propertyName").textbox('getValue');
            orgDeptPropertyDict.propertyValue=$("#propertyValue").textbox('getValue');

            $.ajax({
                'type': 'POST',
                'url': "/service/dept-property/findByCondition",
                'contentType': 'application/json',
                'data': JSON.stringify(orgDeptPropertyDict),
                'dataType': 'json',
                'success': function (data) {
                        $('#dg').datagrid("loadData", data);
                }
            });
        });
      //  var orgId =parent.config.org_id;
       // var orgId=1
        $('#dg').datagrid({

            iconCls: 'icon-edit',//图标
            nowrap: false,
            striped: true,
            border: true,
            method: 'get',
            url: '/service/dept-property/list?orgId='+orgId,
            collapsible: false,//是否可折叠的
            remoteSort: false,
            idField: 'id',
            singleSelect: false,//是否单选
            pagination: true,//分页控件
            pageSize: 15,
            pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
            rownumbers: true,//行号
            columns: [[
                {field: 'propertyType', title: '属性类型', width: 235, align: 'center'},
                {field: 'propertyName', title: '属性名称', width: 235, align: 'center'},
                {field: 'propertyValue', title: '属性值', width: 240, align: 'center'}
            ]]

        });
    }
);




