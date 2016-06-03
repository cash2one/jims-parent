$(function () {
    var dataArr = [];
    var str = decodeURI(window.location.search);   //location.search是从当前URL的?号开始的字符串
    if (str.indexOf(name) != -1) {
        var pos_start = str.indexOf(name) + name.length + 1;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
            var persion_id = str.substring(11);



        }
    }


    var persionServiceList={};

    $('#saveService').click(function(){
        var datas = $('#addServiceModel .active')
       /* if(datas.length == 0){
            alert('至少定制一项服务！')
            return false
        }*/
        var total = 0
        var saveData = []
        /**
         * 处理日期，
         * @param d 日期
         * @param t 年、月
         * @param n 数量
         * @returns {Date}
         */
        var handlerDate = function(d,t,n){
            if(!d) return new Date()
            if(t == '年'){
                var year = d.getFullYear()
                d.setFullYear(+year + (isNaN(n) ? 0 : +n))
            } else if(t == '月'){
                var month = + d.getMonth() + (isNaN(n) ? 0 : +n)
                d.setFullYear(parseInt(month / 12) + d.getFullYear())
                d.setMonth(month = month % 12)
            }
            return d
        }
        for(var i=0;i<datas.length;i++){
            var o = {}
            var liId = $(datas[i]).attr('id')
            var serviceId = liId.substr(liId.indexOf('_')+1)
            o.serviceId = serviceId
            o.serviceStartDate = new Date()
            o.serviceEndDate = handlerDate(new Date(),$('.span-class2',datas[i]).html(),$('.service-num',datas[i]).val())
            o.persionId=persion_id
            o.flag="1"
            saveData.push(o)

            var v = $('div tr:eq(2) td:eq(1)',datas[i]).html()
            total += +v.substr(0,v.indexOf('　'))
        }


        alert('支付界面，金额'+total+'元！！')

        persionServiceList.serviceList = saveData

        jQuery.ajax({
            'type': 'POST',
            'url': "/service/persion-service-list/saveService",
            'contentType': 'application/json',
            'data': JSON.stringify(persionServiceList),
            'dataType': 'json',
            'success': function (data) {
                if (data == "1") {
                    alert("保存成功！！");
                    window.location.href="/modules/sys/default.html? persionId="+persion_id;
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
    $.get('/service/sys-service/findServiceWithPrice',{serviceClass:'1',serviceType:'1'},function(res){

        dataArr = res



        var liArr = $('#addServiceModel ul li')
        if(liArr.length < 1) {
            for (var i = 0; i < dataArr.length; i++) {
                var li = '<li id="service_' + dataArr[i].id + '">';
                li += '<a href="#"><span class="service-name">' + dataArr[i].serviceName + '</span></a>'
                li += '<img src="/static/bookstrap/images/service/normal.jpg"/>'
                li += '<div style="width:100%;height:100%;background-color: #eee;z-index: 99">'
                li += '<table width="100%">'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">　类别：</span></td>'
                li += '<td colspan="3">'
                var priceArr = dataArr[i].sysServicePriceList
                for(var j= 0,k = (priceArr ? priceArr.length : 0);j<k;j++){
                    li += '<span class="span-class' + (j == 0 ? '2' : '') + '">' + priceArr[j].serviceTimeLimit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                }
                li += '</td></tr>'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">　时长：</span></td>'
                li += '<td colspan="3"><input class="service-num" type="text" style="width: 50px" value="';
                if(priceArr && priceArr.length > 0){
                    li += (priceArr[0].serviceTimeLimit == '年' ? '1' : '12')
                }
                li +=  '"/><span>　' + (priceArr && priceArr.length > 0 ? priceArr[0].serviceTimeLimit : '') + '</span></td>'

                li += '</tr>'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">　金额：</span></td>'
                li += '<td colspan="3" style="color: red">'
                if(priceArr && priceArr.length > 0){
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
            $('#addServiceModel ul li').each(function(){
                var liObj = $(this)

                $(this).mouseover(function(){
                    $(this).children('div').css('top',$(this).children('img').css('border-width'))
                    $(this).children('div').css('left',$(this).children('img').css('border-width'))
                    $(this).children('div').slideDown('normal')
                })
                $(this).mouseleave (function(){
                    $(this).children('div').slideUp('normal')
                })

                $('div tr:eq(0) td:eq(1) span',this).click(function(){
                    if($(liObj).attr('class') == 'active') return
                    $(this).parent('td').children('span').attr('class','span-class')
                    $(this).attr('class','span-class2');
                    var v = $(this).text()
                    $('div tr:eq(1) td:eq(1) input',liObj).val(v == '年' ? 1 : 12)
                    $('div tr:eq(1) td:eq(1) span',liObj).html('　' + v)
                    initPrice(liObj)
                })

                $('.service-num',this).keyup(function(){
                    validNum(this,liObj)
                })
                $('.service-num',this).mousedown(function(){
                    validNum(this,liObj)
                })

                $('.made-btn',this).click(function(){
                    $(liObj).attr('class', 'active')
                    $(liObj).children('div').css('top', $(liObj).children('img').css('border-width'))
                    $(liObj).children('div').css('left', $(liObj).children('img').css('border-width'))
                    $('.service-num',liObj).attr('disabled',true)
                })
                $('.cancel-btn',this).click(function(){
                    $(liObj).removeAttr('class')
                    $(liObj).children('div').css('top', $(liObj).children('img').css('border-width'))
                    $(liObj).children('div').css('left', $(liObj).children('img').css('border-width'))
                    $('.service-num',liObj).attr('disabled',false)
                })
            })
            var validNum = function(o,li){
                if(isNaN($(o).val())){
                    $(o).val('1')
                }
                initPrice(li)
            }
            var initPrice = function(li){
                var n = $('.service-num',li).val()
                var t = $('.span-class2',li).html()
                var liId = $(li).attr('id')
                var serviceId = liId.substr(liId.indexOf('_')+1)
                for (var i = 0; i < dataArr.length; i++) {
                    if(serviceId == dataArr[i].id){
                        var price = dataArr[i].sysServicePriceList
                        for(var j=0;j<price.length;j++){
                            if(price[j].serviceTimeLimit == t){
                                var p = ((isNaN(price[j].servicePrice) ? 0 : (+price[j].servicePrice)) * n).toFixed(2)
                                $('div tr:eq(2) td:eq(1)',li).html(p + '　元')
                                return
                            }
                        }
                    }
                }
            }
        }


        if(dataArr.length!=10)
        {
            for(var j=0;j<(10-dataArr.length);j++)
            {
                var li = '<li>';
                li += '</li>' ;
                $('#addServiceModel ul').append(li);
            }
        }
    })





});





