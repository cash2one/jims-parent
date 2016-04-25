$(document).ready(function () {
    $(function () {
        //设置列
        $("#tt").treegrid({
            url: '/service/dept-dict/list',
            footer: '#tb',
            idField: "id",
            treeField: "deptName",
            columns: [[{
                title: 'id',
                field: 'id',
                hidden: true
            }, {
                title: '科室编码',
                field: 'deptCode'

            }, {
                title: '科室名称',
                field: 'deptName'

            }, {
                title: '科室属性',
                field: 'deptPropertity'

            }, {
                title: '属性名称',
                field: 'propertyName'

            }
            ]]
        });


        /**
         * 添加科室信息
         */
        $("#addBtn").on('click', function () {
            clearInput();
            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            $("#deptPropertity").combobox({
                'url': '/service/dept-dict/selectProperty',
                valueField: 'id',
                textField: 'propertyType'
            });

        });


        /**
         * 添加科室信息
         */
        $("#addBtnProperty").on('click', function () {
            // clearInput();
            $("#dlg_property").dialog("open").dialog("setTitle", "添加科室属性");
            $("#orgId").combobox({
                url: '/service/sys-company/select',
                valueField: 'id',
                textField: 'orgName'
            });
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

            var deptDict = {};
            deptDict.deptCode = $("#deptCode").val();
            deptDict.deptName = $("#deptName").val();
            deptDict.parentId = $("#parentId").val();
            deptDict.deptProperty = $("#deptProperty").val();
            deptDict.id=$("id").val();

            if ($("#fm").form()) {
                jQuery.ajax({
                    'type': 'POST',
                    'url': "/service/dept-dict/add",
                    'contentType': 'application/json',
                    'data': JSON.stringify(deptDict),
                    'dataType': 'json',
                    'success': function (data) {
                        if (data.data == "success") {
                            $.messager.alert("系统提示", "保存成功");
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
            orgDeptProperty.orgId = $("orgId").val();

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
         * 清除输入框信息
         */
        var clearInput = function () {
            $("#id").val();
            $("#deptCode").textbox('setValue', "");
            $("#deptName").textbox('setValue', "");
            $("#parentId").textbox('setValue', "");
            $("#deptProperty").combobox('setValue', "");
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
        $("#dlg").dialog('open').dialog('setTitle', "科室修改");

    });

});


