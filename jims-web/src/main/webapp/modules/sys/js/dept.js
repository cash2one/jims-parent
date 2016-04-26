$(document).ready(function () {
    $(function () {
        //设置列
        $("#tt").treegrid({
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
                field: 'deptName'

            }, {
                title: '科室编码',
                field: 'deptCode'

            }, {
                title: '科室属性',
                field: 'deptPropertity'

            }, {
                title: '属性名称',
                field: 'propertyName'

            }
            ]]
        });

        var loadDept = function () {

            var depts = [];
            var treeDepts = [];
            var loadPromise = $.get("/service/dept-dict/list", function (data) {
                //$("#tt").treegrid('loadData',data);
                $.each(data, function (index, item) {
                    var obj = {};
                    obj.deptName = item.deptName;
                    obj.id = item.id;
                    obj.deptCode = item.deptCode;
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
        /**
         * 添加科室信息
         */
        $("#addBtn").on('click', function () {
            // clearInput();

            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            //  clearInput();
            //给上级科室的下拉列表赋值
            $("#parentId").combobox({
                'url': '/service/dept-dict/selectParent',
                valueField: 'id',
                textField: 'deptName'
            });

            //给科室属性的下拉列表赋值
            $("#deptPropertity").combobox({
                'url': '/service/dept-property/selectProperty',
                valueField: 'propertyType',
                textField: 'propertyType'
            });

        });


        /**
         * 添加科室信息
         */
        $("#addBtnProperty").on('click', function () {
            // clearInput();

            $("#dlg_property").dialog("open").dialog("setTitle", "添加科室属性");
            //clearInput();
            //给所属组织的下拉列表赋值
            $("#orgId").combobox({
                url: '/service/sys-company/select',
                valueField: 'id',
                textField: 'orgName'
            });
            //给属性名称的下拉列表赋值
            $("#propertyName").combobox({
                'url': '/service/dept-property/selectName',
                valueField: 'propertyName',
                textField: 'propertyName'
            });


        });




        /**
         * 保存信息
         */
        $("#saveBtn").on('click', function () {
            console.log($("#parentId").combobox('getValue'));
            var deptDict = {};
            deptDict.id = $("#id").val();
            deptDict.deptCode = $("#deptCode").val();
            deptDict.deptName = $("#deptName").val();
            deptDict.parentId = $("#parentId").combobox('getValue');
            deptDict.deptPropertity = $("#deptPropertity").combobox('getValue');

            if ($("#fm").form()) {
                jQuery.ajax({
                    'type': 'POST',
                    'url': "/service/dept-dict/add",
                    'contentType': 'application/json',
                    'data': JSON.stringify(deptDict),
                    'dataType': 'json',
                    'success': function (data) {
                        console.log(data);
                        if (data.data == "success") {
                            $.messager.alert("系统提示", "保存成功");
                            loadDept();
                            clearInput();
                            $("#dlg").dialog('close');
                        }
                    },
                    'error': function (data) {
                        $.messager.alert("系统提示", "保存失败");
                    }
                });
            }
        });


        /**
         * 保存信息
         */
        $("#savePropertyBtn").on('click', function () {
            var orgDeptProperty = {};
            orgDeptProperty.propertyType = $("#propertyType").val();
            orgDeptProperty.propertyName = $("#propertyName").val();
            orgDeptProperty.propertyValue = $("#propertyValue").val();
            orgDeptProperty.orgId = $("#orgId").val();

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
                            clearInput();
                            $("#dlg_property").dialog('close');
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
            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择要修改的科室");
                return;
            }
            $("#id").val(node.id);
            $("#deptCode").textbox('setValue', node.deptCode);
            $("#deptName").textbox('setValue', node.deptName);
            $("#deptPropertity").combobox('setValue', node.deptPropertity);
            $("#parentId").combobox('setValue', node.parentId);
            $("#dlg").dialog('open').dialog('setTitle', "科室修改");

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
            $("#parentId").textbox('setValue', "");
            $("#deptProperty").combobox('setValue', "");
        }

    });


});


