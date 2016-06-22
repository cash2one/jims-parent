/**
 * 人员维护
 * @author yangruidong
 * @version 2016-04-29
 */

$(function () {


    //窗体加载时禁用form表单


    var orgId =config.org_Id;
    var deptId;
    var deptName;
    //检查类别
    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        treeField: "deptName",
        toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'deptName',
            width: '100%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            deptId = node.id;
            deptName = node.deptName;
            var url = basePath + "/orgStaff/list?orgId=" + orgId + "&deptId=" + deptId;
            $("#staffGrid").datagrid("reload", url);

        }
    });

    // var orgId = parent.config.org_id;
    //加载树形结构的treegrid数据
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
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

            $("#staff").treegrid('loadData', treeDepts);
        })
    }
    loadDept();

    //人员信息
    $("#staffGrid").datagrid({
        //fit: true,
        //fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        url: basePath + "/orgStaff/list?orgId=" + orgId + "&deptId=" + deptId,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "姓名",
            field: "name",
            width: '15%'

        }, {
            title: '性别',
            field: 'sex',
            width: '15%'
        }, {
            title: '身份证号',
            field: 'cardNo',
            width: '15%'
        }, {
            title: '联系电话',
            field: 'phoneNum',
            width: '15%'
        }, {
            title: '邮箱',
            field: 'email',
            width: '15%'
        }, {
            title: '职称',
            field: 'title',
            width: '15%'
        }
        ]]
    });
    $('#sex').combobox({
        url: basePath + '/dict/findListByType?type=SEX_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    $('#nation').combobox({
        data: 'type:NATION_DICT',
        url: basePath + '/dict/findListByType?type=NATION_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });

    $('#deptName').combobox({
        url: basePath + '/dept-dict/selectParentByOrgId?orgId='+orgId,
        valueField: 'id',
        textField: 'deptName'
    });

    //加载角色
    $('#role').combobox({
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        valueField: 'id',
        textField: 'roleName',
        method: 'GET',
        multiple: true
    });

    //检查子类别模态框
    $("#addStaff").window({
        title: '组织机构人员维护',
        closed: true,
        modal: true,
        onClose: function () {
            $("#staffForm").form('reset');
        },
        onOpen: function () {
            var node = $("#staff").treegrid("getSelected");
            if (node) {
                $("#deptName").combobox('setValue', node.id);
                $("#deptName").combobox('setText', node.deptName);
            }

        }


    });

    //添加人员按钮
    $("#addBtn").on('click', function () {
        var node = $("#staff").treegrid("getSelected");
        if (node) {
            $("#addStaff").window('open');
            $("#selectCardNo").val("");
        } else {
            $.messager.alert("系统提示", "请先选择科室信息");
        }


    });
    //取消添加人员维护
    $("#cancelBtn").on('click', function () {
        $("#staffForm").form('reset');
        $("#addStaff").window('close');
    });


    $("#select").on('click', function () {
        var cardNo = $("#selectCardNo").val();
        jQuery.ajax({
            'type': 'GET',
            'url': basePath + "/orgStaff/findInfoByCardNo?cardNo=" + cardNo,
            'contentType': 'application/json',
            'dataType': 'json',
            'success': function (data) {
                console.log(data);
                if (data != "") {
                    $("#cardNo").val(data.cardNo);
                    $("#phoneNum").val(data.phoneNum);
                    $("#name").val(data.name);
                    $("#email").val(data.email);
                    $("#nickName").val(data.nickName);
                    $("#sex").combobox('setValue', data.sex);
                    $("#nation").combobox('setValue', data.nation);
                    $("#id").val(data.id);
                    $.get("/service/orgStaff/findTitleByPersionId?persionId=" + data.id+"&orgId="+orgId, function (data) {

                        if (data != null) {
                            $("#title").val(data.title);
                            $("#staffId").val(data.id);
                        }
                        var staffId=$("#staffId").val();
                      //  alert(staffId)
                        var role = [];
                        $.get("/service/orgStaff/findRole?staffId="+staffId, function (data) {
                            if (data != null) {
                                for (var i = 0; i < data.length; i++) {
                                    role.push(data[i].id);
                                }
                                $("#role").combobox('setValues', role);
                            }
                        });
                    });




                    $.get("/service/orgStaff/findPasswordByPersionId?persionId=" + data.id, function (data) {
                        if (data != null) {
                            $("#password").val(data.password);
                        }
                    });

                }
            },
            'error': function (data) {
                $.messager.alert("系统提示", "此用户没有注册，请添加人员");
            }
        });
    });


    //组织机构人员保存
    $("#saveBtn").on('click', function () {
        var flag = false
        $('.fitem  span').each(function(){
            if($(this).css('color') == 'rgb(255, 0, 0)' && $.trim($(this).html()) != '*'){
                flag = true
            }
        })
        if(flag) return
        var orgStaffVo = {};
        orgStaffVo.id = $("#id").val();
        orgStaffVo.selectCardNo = $("#selectCardNo").val();
        orgStaffVo.sex = $("#sex").combobox('getValue');
        orgStaffVo.name = $("#name").val();
        orgStaffVo.title = $("#title").val();
        orgStaffVo.cardNo = $("#cardNo").val();
        orgStaffVo.phoneNum = $("#phoneNum").val();
        orgStaffVo.email = $("#email").val();
        orgStaffVo.password = $("#password").val();
        orgStaffVo.nickName = $("#nickName").val();
        orgStaffVo.deptId = $("#deptName").combobox('getValue');
        var array = [];
        array = $('#role').combobox('getValues');
        if (array == "") {
            orgStaffVo.role = null;
        }
        else {
            orgStaffVo.role = array;
        }
        // orgStaffVo.orgId = parent.config.org_id;
        orgStaffVo.orgId = orgId;
        orgStaffVo.nation = $("#nation").combobox('getValue');
        if (orgStaffVo.cardNo != "" && orgStaffVo.email != "" && orgStaffVo.nickName != "" && orgStaffVo.phoneNum != "") {
            $.postJSON(basePath + "/orgStaff/save", JSON.stringify(orgStaffVo), function (data) {
                if (data.data == "success") {
                    $("#addStaff").window('close');
                    $.messager.alert('系统提示', '人员变动成功');
                    $("#staffGrid").datagrid('reload');
                    $("#staffForm").form('reset');
                }
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'info');
            });
        }
        else {
            $.messager.alert('系统提示', '保存失败,有的字段不能为空', 'info');
        }

    });


    //删除
    $("#removeBtn").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", '请选择要删除的项目', 'error');
            return;
        }
        $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
            if (r) {
                $.postJSON(basePath + "/orgStaff/del", row.id, function (data) {
                    if (data.data == "success") {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        $("#staffGrid").datagrid('reload');
                    }

                });
            }
        });

    })






    var registerVo={};
    //文本框获取焦点的时候，显示
    $("#name").focus(function () {
        $("#res-name").text("*请输入正确的姓名");
        $("#res-name").css("color", "gray");
    });
    //判断用户名不能为空
    $("#name").blur(function () {
        if ($('#name').val() == "") {
            $("#res-name").text("*姓名不能为空");
            $("#res-name").css("color", "red");
            return false;
        }
    });

    //文本框获取焦点的时候，显示
    $("#title").focus(function () {
        $("#res-title").text("*请输入职称");
        $("#res-title").css("color", "gray");
    });
    //判断用户名不能为空
    $("#title").blur(function () {
        if ($('#title').val() == "") {
            $("#res-title").text("*职称不能为空");
            $("#res-title").css("color", "red");
            return false;
        }
    });


    //文本框获取焦点的时候，显示
    $("#cardNo").focus(function () {
        $("#res-card").text("*请输入正确的身份证号");
        $("#res-card").css("color", "gray");
    })
    ;

    //检验身份证号是否已存在
    $("#cardNo").blur(function () {
        registerVo.cardNo = $("#cardNo").val();
        if ($("#cardNo").val() == "") {
            $("#res-card").text("*身份证号不能为空");
            $("#res-card").css("color", "red");
            return false;
        }
        //身份证正则表达式(18位)
        var isIdCard2 = /^[1-9]\d{5}(19\d{2}|[2-9]\d{3})((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}X)$/i;
        var stard = "10X98765432"; //最后一位身份证的号码
        var first = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; //1-17系数
        var sum = 0;
        var cardid = $("#cardNo").val();
        if (!isIdCard2.test(cardid)) {
            $("#res-card").text("*身份证号不合法");
            $("#res-card").css("color", "red");
            return false;
        }

        registerVo.cardNo = $("#cardNo").val();
        jQuery.ajax({
            'type': 'POST',
            'url': "/service/register/getCard",
            'contentType': 'application/json',
            'data': JSON.stringify(registerVo),
            'dataType': 'json',
            'success': function (data) {
                if (data && data.data == "success") {
                    $("#res-card").text("*身份证号已经存在");
                    $("#res-card").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;

    });

    //文本框获取焦点的时候，显示
    $("#nickName").focus(function () {
        $("#res-nick").text("*6-12位字符");
        $("#res-nick").css("color", "gray");
    });

    //校验用户名是否已经存在
    $("#nickName").blur(function () {
        registerVo.nickName = $("#nickName").val();
        var name = $("#nickName").val();
        if ($("#nickName").val() == "") {
            $("#res-nick").text("*用户名不能为空");
            $("#res-nick").css("color", "red");
            return false;
        }
        if (name.length < 6) {
            $("#res-nick").text("*请输入正确长度的字符");
            $("#res-nick").css("color", "red");
            return false;
        }
        if (name.length > 12) {
            $("#res-nick").text("*请输入正确长度的字符");
            $("#res-nick").css("color", "red");
            return false;
        }

        jQuery.ajax({
            'type': 'POST',
            'url': "/service/register/getNick",
            'contentType': 'application/json',
            'data': JSON.stringify(registerVo),
            'dataType': 'json',
            'success': function (data) {
                if (data && data.data == "success") {
                    $("#res-nick").text("*用户名已经存在");
                    $("#res-nick").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });

    //文本框获取焦点的时候，显示
    $("#email").focus(function () {
        $("#res-email").text("*请输入正确的邮箱");
        $("#res-email").css("color", "gray");
    });
    //校验邮箱是否合法，是否已被注册
    $("#email").blur(function () {
        registerVo.email = $("#email").val();

        if ($("#email").val() == "") {
            $("#res-email").text("*邮箱不能为空");
            $("#res-email").css("color", "red");
            return false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            $("#res-email").text("*邮箱格式不正确");
            $("#res-email").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'POST',
            'url': "/service/register/getEmail",
            'contentType': 'application/json',
            'data': JSON.stringify(registerVo),
            'dataType': 'json',
            'success': function (data) {

                if (data && data.data == "success") {
                    $("#res-email").text("*邮箱已注册");
                    $("#res-email").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });


    $("#phoneNum").focus(function () {
        $("#res-phone").text("*请输入正确的手机号");
        $("#res-phone").css("color", "gray");
    });
    //文本框获取焦点的时候，显示
    $("#phoneNum").blur(function () {
        var phone = $("#phoneNum").val();
        registerVo.phoneNum = phone;
        if ($("#phoneNum").val() == "") {
            $("#res-phone").text("*手机号不能为空");
            $("#res-phone").css("color", "red");
            return false;
        }
        if (phone.length != 11) {
            $("#res-phone").text("*请输入有效的手机号");
            $("#res-phone").css("color", "red");
            return false;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-phone").text('*请输入有效的手机号码');
            $("#res-phone").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'POST',
            'url': "/service/register/getPhone",
            'contentType': 'application/json',
            'data': JSON.stringify(registerVo),
            'dataType': 'json',
            'success': function (data) {

                if (data && data.data == "success") {
                    $("#res-phone").text("*手机号已经注册");
                    $("#res-phone").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;
    });


    //文本框获取焦点的时候，显示
    $("#password").focus(function () {
        $("#res-password").text("*请输入正确的密码");
        $("#res-password").css("color", "gray");
    });
    $("#password").blur(function () {
        var password = $("#password").val();
        if (password.length == 0) {
            $("#res-password").text("*密码不能为空");
            $("#res-password").css("color", "red");
        }
    });

});




