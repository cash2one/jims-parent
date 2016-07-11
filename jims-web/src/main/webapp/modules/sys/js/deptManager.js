$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

        //校验数据是否合法
        $.extend($.fn.validatebox.defaults.rules, {
            IsExisted: {
                validator: function (value, param) {
                    var rows = $('#dg').datagrid('getRows')
                    var select_index = $('#dg').datagrid('getRowIndex', $('#dg').datagrid('getSelected'))
                    var p;
                    if (param[0] == "propertyType") {
                        p = "propertyValue"
                    } else if (param[0] == "propertyValue") {
                        p = "propertyType"
                    }
                    for (var index = rows.length - 1; index > -1; index--) {
                        if (index != select_index && rows[index][param[0]] == value && rows[index][p] == rows[select_index][p]) {
                            return false
                        }
                    }
                    return true
                },
                message: '同类型的值不能相同'
            }
        });


        var property = [];
        var orgId = config.org_Id;
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
                field: 'deptName',
                width: '200'

            }, {
                title: '科室编码',
                field: 'deptCode',
                width: '200'

            }, {
                title: '拼音码',
                field: 'inputCode',
                width: '200'

            }, {
                title: '科室属性',
                field: 'deptPropertityName',
                width: '200'

            }
            ]]
        });

        var loadDept = function () {

            var depts = [];
            var treeDepts = [];

            //var orgId=1
            var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
                $.each(data, function (index, item) {
                    var obj = {};
                    obj.deptName = item.deptName;
                    obj.id = item.id;
                    obj.deptCode = item.deptCode;
                    obj.inputCode = item.inputCode;
                    obj.deptPropertity = item.deptPropertity;
                    obj.deptPropertityName = item.deptPropertityName;
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
                console.log(treeDepts);
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


            $("#parentId").combobox('setValue', "");
            $("#deptCode").textbox('setValue', "");
            $("#deptName").textbox('setValue', "");

            $("#deptPropertity").html("");
            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            $("#inputCode").attr('readonly', true);


            $.ajax({
                url: "/service/dept-property/selectProperty?orgId=" + orgId,
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
                            'url': '/service/dept-property/selectName/' + deptPropertity + '/' + orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }
                }
            });

            //  clearInput();

            $("#parentId").combobox({
                'url': '/service/dept-dict/selectParentByOrgId?orgId=' + orgId,
                valueField: 'id',
                textField: 'deptName'
            });

            $("#deptName").textbox({
                onChange: function () {
                    var dept = $("#deptName").val();
                    var inputCode = makePy(dept)[0];
                    $("#inputCode").textbox('setValue', inputCode);
                }
            });


        });

        //给上级科室的下拉列表赋值
        $("#parentId").combobox({
            'url': '/service/dept-dict/selectParentByOrgId?orgId=' + orgId,
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
         * 保存科室信息
         */
        $("#saveBtn").on('click', function () {
            //用于存放属性值的数组
            var deptProperty = [];
            //用于存放所有保存的数组
            var deptDictVo = {};
            deptDictVo.id = $("#id").val();
            deptDictVo.deptCode = $("#deptCode").val();
            deptDictVo.deptName = $("#deptName").val();
            deptDictVo.orgId = orgId
            deptDictVo.inputCode = $("#inputCode").val();
            deptDictVo.parentId = $("#parentId").combobox('getValue');
            for (var i = 0; i < deptPropertitys.length; i++) {
                var propertyIds = "propertyName" + i;
                console.log(propertyIds);
                var deptIds = $("#" + propertyIds).combobox('getValue');
                deptProperty.push(deptIds);

            }
            deptDictVo.array = deptProperty;
            if (deptDictVo != null && deptDictVo.deptCode != "" && deptDictVo.deptName != "" && deptDictVo.orgId != "" && deptDictVo.inputCode != null && deptDictVo.array != "") {
                jQuery.ajax({
                    'type': 'POST',
                    'url': "/service/dept-dict/add",
                    'contentType': 'application/json',
                    'data': JSON.stringify(deptDictVo),
                    'dataType': 'json',
                    'success': function (data) {
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
            } else {
                $.messager.alert("系统提示", "填写信息不完整，请重新填写！");
            }


        });


        /**
         * 修改科室信息
         *
         */

        $("#editBtn").on('click', function () {
            $("#deptPropertity").html("");
            $("#deptName").textbox({
                onChange: function () {
                    var dept = $("#deptName").val();
                    var inputCode = makePy(dept)[0];
                    $("#inputCode").textbox('setValue', inputCode);
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
            $("#inputCode").textbox('setValue', node.inputCode);
            $("#parentId").combobox('setValue', node.parentId);

            //给上级科室的下拉列表赋值

            $("#dlg").dialog('open').dialog('setTitle', "科室修改");


            $.ajax({
                url: "/service/dept-property/selectProperty?orgId=" + orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
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
                            'url': '/service/dept-property/selectName/' + deptPropertity + '/' + orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }


                    //修改科室属性时，给回显的属性赋值
                    var deptPro = node.deptPropertity;
                    var dept = [];
                    dept = deptPro.split(";");
                    for (var item = 0; item < dept.length; item++) {
                        var propertyIds = "propertyName" + item;
                        $("#" + propertyIds).combobox("setValue", dept[item]);
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
        $("#selectPropertyBtn").on('click', function () {
            var propertyTypeData = $("#propertyType").textbox('getValue');
            var propertyNameData = $("#propertyName").textbox('getValue');
            var propertyValueData = $("#propertyValue").textbox('getValue');

            $.get(basePath + "/dept-property/findByCondition", {
                propertyType: propertyTypeData,
                orgId: orgId,
                propertyName: propertyNameData,
                propertyValue: propertyValueData
            }, function (data) {
                $("#dg").datagrid('loadData', data);
            });

        });
        $('#dg').datagrid({
            fit: true,
            fitColumns: true,
            striped: true,
            singleSelect: true,
            method: 'get',
            url: '/service/dept-property/listProperty?orgId=' + orgId,
            idField: 'id',
            singleSelect: true,//是否单选
            rownumbers: true,//行号
            fitColumns: true,
            columns: [[
                {
                    field: 'propertyType', title: '属性类型', width: 235, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            validType: 'IsExisted["propertyType"]',
                            missingMessage: '属性类型不能为空'
                        }
                    }
                },
                {
                    field: 'propertyName', title: '属性名称', width: 235, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '属性名称不能为空'
                        }
                    }
                },
                {
                    field: 'propertyValue', title: '属性值', width: 240, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            validType: 'IsExisted["propertyValue"]',
                            missingMessage: '属性值不能为空'
                        }
                    }
                }
            ]],
            onClickCell: onClickCell,
            onBeforeSelect: function (index) {
                return $('#dg').datagrid('validateRow', editIndex)
            }
        });


//datagrid的单元格编辑
        $.extend($.fn.datagrid.methods, {
            editCell: function (jq, param) {
                return jq.each(function () {
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
                        if (fields[i] != param.field) {
                            col.editor = null;
                        }
                    }
                    $(this).datagrid('beginEdit', param.index);
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor = col.editor1;
                    }
                });
            }
        });

        var editIndex = undefined;

        function endEditing1() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#dg').datagrid('validateRow', editIndex)) {
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function onClickCell(index, field) {
            if (endEditing1()) {
                if (field == 'propertyType') {
                    var row = $('#dg').datagrid('getRows')[index];
                    if (row.id)return;
                }
                if (field == 'propertyValue') {
                    var row = $('#dg').datagrid('getRows')[index];
                    if (row.id)return;
                }
                $('#dg').datagrid('selectRow', index)
                    .datagrid('editCell', {index: index, field: field});
                editIndex = index;
            }
        }

        //开始编辑行
        $("#addPropertyBtn").on('click', function () {
            $.get(basePath + "/dept-dict/list?orgId=" + orgId, function (data) {
                if (data != "") {
                    $.messager.alert('系统提示', "已有科室,不能再维护科室属性了", 'info');
                    return;
                } else {
                    $("#dg").datagrid('appendRow', {orgId: orgId});
                    var rows = $("#dg").datagrid('getRows');
                    onClickCell(rows.length - 1, 'dg');
                }
            });

        });

        //删除
        $("#delPropertyBtn").on("click", function () {
            $.get(basePath + "/dept-dict/list?orgId=" + orgId, function (data) {
                if (data != "") {
                    $.messager.alert('系统提示', "已有科室,不能删除科室属性了", 'info');
                    return;
                } else {
                    var row = $("#dg").datagrid('getSelected');
                    if (row) {
                        var rowIndex = $("#dg").datagrid('getRowIndex', row);
                        $("#dg").datagrid('deleteRow', rowIndex);
                        if (editIndex == rowIndex) {
                            editIndex = undefined;
                        }
                    } else {
                        $.messager.alert('系统提示', "请选择要删除的行", 'info');
                    }
                }
            });
        });

        //关闭
        $("#cancelPropertyBtn").on("click", function () {
            $("#dg").datagrid('reload');
        });

        /**
         * 保存科室属性信息
         */
        $("#savePropertyBtn").on('click', function () {
            if (!endEditing1()) {
                return
            }
            var insertData = $("#dg").datagrid("getChanges", "inserted");
            var updateData = $("#dg").datagrid("getChanges", "updated");
            var deleteData = $("#dg").datagrid("getChanges", "deleted");
            //保存时判断值不能为空
            for (var i = 0; i < insertData.length; i++) {
                if (!("propertyValue" in insertData[i]) || !("propertyName" in insertData[i]) || !("propertyType" in insertData[i])) {
                    $.messager.alert("系统提示", "值,名称,类型不能为空", "info");
                    return
                }
            }
            var orgDeptPropertyDictVo = {};
            orgDeptPropertyDictVo.inserted = insertData;
            orgDeptPropertyDictVo.deleted = deleteData;
            orgDeptPropertyDictVo.updated = updateData;
            orgDeptPropertyDictVo.orgId = orgId;
            if (orgDeptPropertyDictVo) {
                $.postJSON("/service/dept-property/saveAll", JSON.stringify(orgDeptPropertyDictVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        $("#dg").datagrid('reload');
                        $("#dlg_property").dialog('close');
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }
        });

    }
);




