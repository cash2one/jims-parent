$(function () {
    var company = {};
    //查询父机构
    jQuery.ajax({
        'type': 'post',
        'url': "/service/sys-company/select",
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function (data) {
            if (data.length > 0) {
                for (var i = 0; i <= data.length; i++) {
                    var orgName = data[i].orgName;
                    $("#parentId").append("<option value='" + data[i].id + "'>" + orgName + "</option>");
                }
            }
            if(data.length==1)
            {
                var orgName = data.orgName;
                $("#parentId").append("<option value='" + data.id + "'>" + orgName + "</option>");
            }


        },
        'error': function (data) {
            alert("系统提示", "保存失败");
        }
    });


    //校验组织机构名称是否存在
    $("#orgName").focus(function () {
        $("#res-orgName").css("color", "gray");
        $("#res-orgName").text("*请输入真实的机构名称，以便验证通过！");
        return false;
    });
    $("#orgName").blur(function () {
        if ($("#orgName").val() == "") {
            $("#res-orgName").css("color", "red");
            $("#res-orgName").text("*组织机构名称不能为空");
            return false;
        }
        var orgName = $("#orgName").val();
        jQuery.ajax({
            'type': 'POST',
            'url': "/service/sys-company/getOrgName",
            'contentType': 'application/json',
            'data': orgName,
            'dataType': 'json',
            'success': function (data) {
                console.log(data);
                if (data.data == "success") {
                    $("#res-email").css("color", "gray");
                    $("#res-orgName").text("*请输入真实的名称机构，以便验证通过！");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;

    });

    //校验邮箱是否正确
    $("#email").focus(function () {

        $("#res-email").text("*请输入真实的邮箱地址，以便验证通过！");
        $("#res-email").css("color", "gray");
        return false;
    });
    $("#email").blur(function () {

        if ($("#email").val() == "") {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱不能为空");
            return false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱格式不正确");
            return false;
        }
        //return true;
    })
    //校验组织机构代码
    $("#orgCode").focus(function(){
        $("#res-orgCode").css("color", "gray");
        $("#res-orgCode").text("*请输入真实证书号，以便验证通过！");
        return false;
    });
    $("#orgCode").blur(function(){
       if($("#orgCode").val()=="")
       {
           $("#res-orgCode").css("color", "red");
           $("#res-orgCode").text("*组织机构代码不能为空");
           return false;
       }
    });

    //校验组织机构地址
    $("#address").focus(function () {
        $("#res-address").css("color", "gray");
        $("#res-address").text("*请输入真实机构地址，以便验证通过！");
        return false;
    });
    $("#address").blur(function () {
        if ($("#address").val() == "") {
            $("#res-address").css("color", "red");
            $("#res-address").text("*组织机构地址不能为空");
            return false;
        }
    });
    //校验联系人
    $("#linkMan").focus(function () {
        $("#res-linkMan").css("color", "gray");
        $("#res-linkMan").text("*请输入真实的联系人，以方便联系！");
        return false;
    });
    $("#linkMan").blur(function () {
        if ($("#linkMan").val() == "") {
            $("#res-linkMan").css("color", "red");
            $("#res-linkMan").text("*联系人不能为空");
            return false;
        }
    });

    //校验联系电话
    $("#linkPhoneNum").focus(function () {
        $("#res-linkPhoneNum").css("color", "gray");
        $("#res-linkPhoneNum").text("*请输入真实的联系电话，以方便联系！");
        return false;
    });
    $("#linkPhoneNum").blur(function () {
        if ($("#linkPhoneNum").val() == "") {
            $("#res-linkPhoneNum").css("color", "red");
            $("#res-linkPhoneNum").text("*联系电话不能为空");
            return false;
        }

        var phone = $("#linkPhoneNum").val();
        if (phone.length != 11) {
            $("#res-linkPhoneNum").css("color", "red");
            $("#res-linkPhoneNum").text("*请输入有效的手机号");
            return false;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-linkPhoneNum").text('*请输入有效的手机号码');
            return false;
        }
    });
    /**
     * 保存信息
     */
    if(company)
    {
        $("#saveBtn").on('click', function () {
            if ($("#linkPhoneNum").val() == "" || $("#linkMan").val() == "" ||
                $("#address").val() == "" || $("#orgCode").val()=="" || $("#email").val() == "" || $("#orgName").val() == "") {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-address").css("color", "red");
                $("#res-orgCode").css("color", "red");
                $("#res-email").css("color", "red");
                $("#res-linkMan").css("color", "red");
                $("#res-orgName").css("color", "red");
                $("#res-orgName").text("*组织机构名称不能为空");
                $("#res-linkPhoneNum").text("*联系电话不能为空");
                $("#res-linkMan").text("*联系人不能为空");
                $("#res-address").text("*组织机构地址不能为空");
                $("#res-orgCode").text("*组织机构代码不能为空");
                $("#res-email").text("*邮箱不能为空");
                return false;
            }
            if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) || $("#linkPhoneNum").val().length !=11) {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱格式不正确");
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text("*请输入有效的手机号");
                return false;
            }
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if (!myreg.test(phone)) {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text('*请输入有效的手机号码');
                return false;
            }
            company.parentId = $("#parentId").val();
            company.orgName = $("#orgName").val();
            company.orgCode = $("#orgCode").val();
            company.address = $("#address").val();
            company.linkPhoneNum = $("#linkPhoneNum").val();
            company.email = $("#email").val();
            company.linkMan=$("#linkMan").val();
            var name = $("#orgName").val();


            jQuery.ajax({
                'type': 'POST',
                'url': "/service/sys-company/insertReturnId",
                'contentType': 'application/json',
                'data': JSON.stringify(company),
                'dataType': 'json',
                'success': function (data) {
                    console.log(data);
                    if (data.data == "success") {
                        //$.messager.alert("系统提示", "保存成功");
                        alert("保存成功！！");
                        //解决传到另一个htnl中的乱码问题
                        encodeURI(window.location.href = "/modules/sys/default.html?name=" + name);

                    }
                },
                'error': function (data) {
                    alert("系统提示", "保存失败");
                }
            });

        });
    }
});





