$(function () {

    var registerVo = {};

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


    //添加注册信息
    $("#btnSubmit").click(function () {
        $("#btnSubmit").attr('disabled','true')
        var flag = false
        $('.reg-inp li span').each(function(){
            if($(this).css('color') == 'rgb(255, 0, 0)' && $.trim($(this).html()) != '*'){
                flag = true
            }
        })
        if(flag) return
        registerVo.name = $("#name").val();
        registerVo.cardNo = $("#cardNo").val();
        registerVo.nickName = $("#nickName").val();
        registerVo.email = $("#email").val();
        registerVo.phoneNum = $("#phoneNum").val();
        registerVo.password = $("#password").val();

        if (registerVo.cardNo != "" && registerVo.name != "" && registerVo.email != "" && registerVo.nickName != "" && registerVo.password != "" && registerVo.phoneNum != "") {
            jQuery.ajax({
                'type': 'POST',
                'url': "/service/register/add-info",
                'contentType': 'application/json',
                'data': JSON.stringify(registerVo),
                'dataType': 'json',
                'success': function (data) {
                    if (data.data == "success") {

                        if (confirm("注册成功，是否跳转到登录页面，进行登录")) {
                            window.location.href = "/modules/sys/login.html";
                        }

                    } else {
                        alert("注册失败");
                    }
                    //  return true;
                },

                'error': function (data) {
                    console.log("失败");
                }
            });
        } else {
            alert("请先填写注册的信息");
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
        $("#res-email").text("*");
    });
    //校验邮箱是否合法，是否已被注册
    $("#email").blur(function () {
        registerVo.email = $("#email").val();

        if ($("#email").val() == "") {
            $("#res-email").text("*邮箱不能为空");
            return false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            $("#res-email").text("*邮箱格式不正确");
            $("#email").focus(function () {
                $("#res-email").text("*");
            });
            return false;
        }
        if ($("#email").val().length > 50) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法,请重新填写");
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
        $("#res-phone").text("*");
    });
    //文本框获取焦点的时候，显示
    $("#phoneNum").blur(function () {
        var phone = $("#phoneNum").val();
        registerVo.phoneNum = phone;
        if ($("#phoneNum").val() == "") {
            $("#res-phone").text("*手机号不能为空");
            return false;
        }
        if (phone.length != 11) {
            $("#res-phone").text("*请输入有效的手机号");
            return false;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-phone").text('*请输入有效的手机号码');
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
        $("#res-password").text("*");
    });
    $("#password").blur(function () {
        var password = $("#password").val();
        if (password.length == 0) {
            $("#res-password").text("*密码不能为空");
        }
    });

});
