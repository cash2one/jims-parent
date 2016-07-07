$(function () {
    var str = decodeURI(window.location.search);   //location.search是从当前URL的?号开始的字符串
    if (str.indexOf(name) != -1) {
        var pos_start = str.indexOf(name) + name.length + 1;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
            var persion_id = str.substring(11);

        }
    }


    var currentPersonId = persion_id;
    var company = {};
    //查询父机构

    $.get("/service/sys-company/select", {persionId: currentPersonId}, function (data) {
        if (data.length > 0) {
            for (var i = 0; i <= data.length; i++) {
                var orgName = data[i].orgName;
                $("#parentId").append("<option value='" + data[i].id + "'>" + orgName + "</option>");
            }
        }
        if (data.length == 1) {
            var orgName = data.orgName;
            $("#parentId").append("<option value='" + data.id + "'>" + orgName + "</option>");
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
        if($("#email").val().length>50)
        {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法,请重新填写");
            result = false;
        }
        //return true;
    })
    //校验组织机构代码
    $("#orgCode").focus(function () {
        $("#res-orgCode").css("color", "gray");
        $("#res-orgCode").text("*请输入真实证书号，以便验证通过！");
        return false;
    });
    $("#orgCode").blur(function () {
        if ($("#orgCode").val() == "") {
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
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-linkPhoneNum").css("color", "red");
            $("#res-linkPhoneNum").text('*请输入有效的手机号码');
            return false;
        }
    });

    var validForm = function () {
        var result = true;
        if ($("#orgName").val() == "") {
            $("#res-orgName").css("color", "red");
            $("#res-orgName").text("*组织机构名称不能为空");
            result = false;
        }
        if ($("#orgCode").val() == "") {
            $("#res-orgCode").css("color", "red");
            $("#res-orgCode").text("*组织机构代码不能为空");
            result = false;
        }
        if ($("#address").val() == "") {
            $("#res-address").css("color", "red");
            $("#res-address").text("*组织机构地址不能为空");
            result = false;
        }
        if ($("#linkMan").val() == "") {
            $("#res-linkMan").css("color", "red");
            $("#res-linkMan").text("*联系人不能为空");
            result = false;
        }
        if ($("#linkPhoneNum").val() == "") {
            $("#res-linkPhoneNum").css("color", "red");
            $("#res-linkPhoneNum").text("*联系电话不能为空");
            result = false;
        }
        if ($("#email").val() == "") {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱不能为空");
            result = false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) || $("#linkPhoneNum").val().length != 11) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱格式不正确");
            result = false;
        }
        if ($("#email").val().length > 50) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法,请重新填写");
            result = false;
        }
        var phone = $("#linkPhoneNum").val();
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-linkPhoneNum").css("color", "red");
            $("#res-linkPhoneNum").text('*请输入有效的手机号码');
            result = false;
        }
        return result
    }

    $('#saveService').click(function () {
        var datas = $('#addServiceModel .active')
        if (datas.length == 0) {
            alert('至少定制一项服务！')
            return false
        }
        var total = 0
        var saveData = []
        /**
         * 处理日期，
         * @param d 日期
         * @param t 年、月
         * @param n 数量
         * @returns {Date}
         */
        var handlerDate = function (d, t, n) {
            if (!d) return new Date()
            if (t == '年') {
                var year = d.getFullYear()
                d.setFullYear(+year + (isNaN(n) ? 0 : +n))
            } else if (t == '月') {
                var month = +d.getMonth() + (isNaN(n) ? 0 : +n)
                d.setFullYear(parseInt(month / 12) + d.getFullYear())
                d.setMonth(month = month % 12)
            }
            return d
        }
        for (var i = 0; i < datas.length; i++) {
            var o = {}
            var liId = $(datas[i]).attr('id')
            var serviceId = liId.substr(liId.indexOf('_') + 1)
            o.serviceId = serviceId
            o.serviceStartDate = new Date()
            o.serviceEndDate = handlerDate(new Date(), $('.span-class2', datas[i]).html(), $('.service-num', datas[i]).val())
            saveData.push(o)

            var v = $('div tr:eq(2) td:eq(1)', datas[i]).html()
            total += +v.substr(0, v.indexOf('　'))
        }


        alert('支付界面，金额' + total + '元！！')

        company.parentId = $("#parentId").val();
        company.orgName = $("#orgName").val();
        company.orgCode = $("#orgCode").val();
        company.address = $("#address").val();
        company.linkPhoneNum = $("#linkPhoneNum").val();
        company.email = $("#email").val();
        company.linkMan = $("#linkMan").val();
        company.owner = currentPersonId;
        company.serviceList = saveData
        var name = $("#orgName").val();


        $.ajax({
            'type': 'POST',
            'url': "/service/sys-company/saveCompanyAndService",
            'contentType': 'application/json',
            'data': JSON.stringify(company),
            'dataType': 'json',
            'success': function (data) {
                if (data == "1") {
                    //$.messager.alert("系统提示", "保存成功");
                    alert("保存成功");
                    //解决传到另一个htnl中的乱码问题
                    window.location.href = "/modules/sys/default.html?persion_id=" + currentPersonId;

                } else {
                    alert("保存失败！！");
                }
            },
            'error': function (data) {
                alert("保存失败！！");
            }
        });


    })
    /**
     * 保存信息
     */
    var dataArr
    $.get('/service/sys-service/findServiceWithPrice', {serviceClass: '0', serviceType: '1'}, function (res) {
        dataArr = res
    })
    if (company) {
        $("#nextBtn").on('click', function () {

            if (!validForm()) return false
            var liArr = $('#addServiceModel ul li')
            if (liArr.length < 1) {
                for (var i = 0; i < dataArr.length; i++) {
                    var li = '<li id="service_' + dataArr[i].id + '">';
                    li += '<a href="#"><span class="service-name">' + dataArr[i].serviceName + '</span></a>'
                    if (dataArr[i].serviceImage == null) {
                        li += '<img src="/static/bookstrap/images/service/normal.jpg"/>'
                    } else {
                        li += '<img src="' + dataArr[i].serviceImage + '"/>'
                    }
                    li += '<div style="width:100%;height:100%;background-color: #eee;z-index: 99">'
                    li += '<table width="100%">'
                    li += '<tr style="height: 35px">'
                    li += '<td width="60"><span class="text-success">　类别：</span></td>'
                    li += '<td colspan="3">'
                    var priceArr = dataArr[i].sysServicePriceList
                    for (var j = 0, k = (priceArr ? priceArr.length : 0); j < k; j++) {
                        li += '<span class="span-class' + (j == 0 ? '2' : '') + '">' + priceArr[j].serviceTimeLimit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                    }
                    li += '</td></tr>'
                    li += '<tr style="height: 35px">'
                    li += '<td width="60"><span class="text-success">　时长：</span></td>'
                    li += '<td colspan="3"><input class="service-num" type="text" style="width: 50px" value="';
                    if (priceArr && priceArr.length > 0) {
                        li += (priceArr[0].serviceTimeLimit == '年' ? '1' : '12')
                    }
                    li += '"/><span>　' + (priceArr && priceArr.length > 0 ? priceArr[0].serviceTimeLimit : '') + '</span></td>'

                    li += '</tr>'
                    li += '<tr style="height: 35px">'
                    li += '<td width="60"><span class="text-success">　金额：</span></td>'
                    li += '<td colspan="3" style="color: red">'
                    if (priceArr && priceArr.length > 0) {
                        var num = priceArr[0].serviceTimeLimit == '年' ? '1' : '12';
                        li += ((isNaN(priceArr[0].servicePrice) ? 0 : (+priceArr[0].servicePrice)) * num).toFixed(2)
                    } else {
                        li += '0.00'
                    }
                    li += '　元</td>'
                    li += '</tr>'
                    li += '<tr style="height: 30px">'
                    li += '<td colspan="4" align="center" >'
                    li += '<button class="btn btn-default btn-xs made-btn">定制<button>'
                    li += '<button class="btn btn-default btn-xs cancel-btn">取消<button>'
                    li += '</td></tr>'
                    li += '</table></div></li>'
                    $('#addServiceModel ul').append(li);
                }
                $('#addServiceModel ul li').each(function () {
                    var liObj = $(this)

                    $(this).mouseover(function () {
                        $(this).children('div').css('top', $(this).children('img').css('border-width'))
                        $(this).children('div').css('left', $(this).children('img').css('border-width'))
                        $(this).children('div').slideDown('normal')
                    })
                    $(this).mouseleave(function () {
                        $(this).children('div').slideUp('normal')
                    })

                    $('div tr:eq(0) td:eq(1) span', this).click(function () {
                        if ($(liObj).attr('class') == 'active') return
                        $(this).parent('td').children('span').attr('class', 'span-class')
                        $(this).attr('class', 'span-class2');
                        var v = $(this).text()
                        $('div tr:eq(1) td:eq(1) input', liObj).val(v == '年' ? 1 : 12)
                        $('div tr:eq(1) td:eq(1) span', liObj).html('　' + v)
                        initPrice(liObj)
                    })

                    $('.service-num', this).keyup(function () {
                        validNum(this, liObj)
                    })
                    $('.service-num', this).mousedown(function () {
                        validNum(this, liObj)
                    })

                    $('.made-btn', this).click(function () {
                        $(liObj).attr('class', 'active')
                        $(liObj).children('div').css('top', $(liObj).children('img').css('border-width'))
                        $(liObj).children('div').css('left', $(liObj).children('img').css('border-width'))
                        $('.service-num', liObj).attr('disabled', true)
                    })
                    $('.cancel-btn', this).click(function () {
                        $(liObj).removeAttr('class')
                        $(liObj).children('div').css('top', $(liObj).children('img').css('border-width'))
                        $(liObj).children('div').css('left', $(liObj).children('img').css('border-width'))
                        $('.service-num', liObj).attr('disabled', false)
                    })
                })
                var validNum = function (o, li) {
                    if (isNaN($(o).val())) {
                        $(o).val('1')
                    }
                    initPrice(li)
                }
                var initPrice = function (li) {
                    var n = $('.service-num', li).val()
                    var t = $('.span-class2', li).html()
                    var liId = $(li).attr('id')
                    var serviceId = liId.substr(liId.indexOf('_') + 1)
                    for (var i = 0; i < dataArr.length; i++) {
                        if (serviceId == dataArr[i].id) {
                            var price = dataArr[i].sysServicePriceList
                            for (var j = 0; j < price.length; j++) {
                                if (price[j].serviceTimeLimit == t) {
                                    var p = ((isNaN(price[j].servicePrice) ? 0 : (+price[j].servicePrice)) * n).toFixed(2)
                                    $('div tr:eq(2) td:eq(1)', li).html(p + '　元')
                                    return
                                }
                            }
                        }
                    }
                }
            }


            //if ($("#linkPhoneNum").val() == "" || $("#linkMan").val() == "" ||
            //    $("#address").val() == "" || $("#orgCode").val()=="" || $("#email").val() == "" || $("#orgName").val() == "") {
            //    $("#res-linkPhoneNum").css("color", "red");
            //    $("#res-address").css("color", "red");
            //    $("#res-orgCode").css("color", "red");
            //    $("#res-email").css("color", "red");
            //    $("#res-linkMan").css("color", "red");
            //    $("#res-orgName").css("color", "red");
            //    $("#res-orgName").text("*组织机构名称不能为空");
            //    $("#res-linkPhoneNum").text("*联系电话不能为空");
            //    $("#res-linkMan").text("*联系人不能为空");
            //    $("#res-address").text("*组织机构地址不能为空");
            //    $("#res-orgCode").text("*组织机构代码不能为空");
            //    $("#res-email").text("*邮箱不能为空");
            //    return false;
            //}
            //if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) || $("#linkPhoneNum").val().length !=11) {
            //    $("#res-email").css("color", "red");
            //    $("#res-email").text("*邮箱格式不正确");
            //    $("#res-linkPhoneNum").css("color", "red");
            //    $("#res-linkPhoneNum").text("*请输入有效的手机号");
            //    return false;
            //}
            //var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            //if (!myreg.test(phone)) {
            //    $("#res-linkPhoneNum").css("color", "red");
            //    $("#res-linkPhoneNum").text('*请输入有效的手机号码');
            //    return false;
            //}


        });
    }
});





