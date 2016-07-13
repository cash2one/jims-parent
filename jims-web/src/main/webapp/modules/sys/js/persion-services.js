$(function () {
    var dataArr = [];
    var str = decodeURI(window.location.search);   //location.search�Ǵӵ�ǰURL��?�ſ�ʼ���ַ���
    if (str.indexOf(name) != -1) {
        var pos_start = str.indexOf(name) + name.length + 1;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
            var persion_id = '';
            if(str.indexOf("persionId=")>=0){
                persion_id = str.substring(str.indexOf("persionId=")+10);
            }else if(str.indexOf("persion_id=")>=0){
                persion_id = str.substring(str.indexOf("persion_id=")+11);
            }
        }
    }


    var persionServiceList={};

    $("#default").on('click', function () {
        window.location.href = "/modules/sys/default.html?persionId="+persion_id;
    });
    $('#saveService').click(function(){
        var datas = $('#addServiceModel .curr-btn-save')
        if(datas.length == 0){
            alert('���ٶ���һ�����')
            return false
        }
        var total = 0
        var saveData = []
        /**
         * �������ڣ�
         * @param d ����
         * @param t �ꡢ��
         * @param n ����
         * @returns {Date}
         */
        var handlerDate = function(d,t,n){
            if(!d) return new Date()
            if(t == '��'){
                var year = d.getFullYear()
                d.setFullYear(+year + (isNaN(n) ? 0 : +n))
            } else if(t == '��'){
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
            total += +v.substr(0,v.indexOf('��'))
        }


        //alert('֧�����棬���'+total+'Ԫ����')

        persionServiceList.serviceList = saveData

        jQuery.ajax({
            'type': 'POST',
            'url': "/service/persion-service-list/saveService",
            'contentType': 'application/json',
            'data': JSON.stringify(persionServiceList),
            'dataType': 'json',
            'success': function (data) {
                if (data == "1") {
                    alert("����ɹ�����");
                    window.location.href="/modules/sys/default.html?persion_id="+persion_id;
                } else {
                    alert("����ʧ�ܣ���");
                }
            },
            'error': function (data) {
                alert("����ʧ�ܣ���");
            }
        });
    })
    /**
     * ������Ϣ
     */
    var dataArr
    $.get('/service/sys-service/findServiceWithPrice',{serviceClass:'1',serviceType:'1'},function(res){
        dataArr = res
        var liArr = $('#addServiceModel ul li')
        if(liArr.length < 1) {
            for (var i = 0; i < dataArr.length; i++) {
                var li = '<li id="service_' + dataArr[i].id + '">';
                li += '<div class="service-set">'
                li += '<h3>' + dataArr[i].serviceName + '</h3>'
                li += '<table width="100%">'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">�����</span></td>'
                li += '<td colspan="3">'
                var priceArr = dataArr[i].sysServicePriceList
                for(var j= 0,k = (priceArr ? priceArr.length : 0);j<k;j++){
                    li += '<span class="span-class' + (j == 0 ? '2' : '') + '">' + priceArr[j].serviceTimeLimit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                }
                li += '</td></tr>'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">��ʱ����</span></td>'
                li += '<td colspan="3"><input class="service-num" type="text" style="width: 50px" value="';
                if(priceArr && priceArr.length > 0){
                    li += (priceArr[0].serviceTimeLimit == '��' ? '1' : '12')
                }
                li +=  '"/><span>��' + (priceArr && priceArr.length > 0 ? priceArr[0].serviceTimeLimit : '') + '</span></td>'

                li += '</tr>'
                li += '<tr style="height: 35px">'
                li += '<td width="60"><span class="text-success">����</span></td>'
                li += '<td colspan="3" style="color: red">'
                if(priceArr && priceArr.length > 0){
                    var num = priceArr[0].serviceTimeLimit == '��' ? '1' : '12';
                    li += ((isNaN(priceArr[0].servicePrice) ? 0 : (+priceArr[0].servicePrice)) * num).toFixed(2)
                } else {
                    li += '0.00'
                }
                li += '��Ԫ</td>'
                li += '</tr>'
                li += '</table></div>';
                li += '<div class="curr-btn" style="margin-left: 140px;"><button>����</button></div>'
                li += '</li>'
                $('#addServiceModel ul').append(li);
            }
            $('#addServiceModel ul li').each(function(){
                var liObj = $(this)
                $(this).children('div').slideDown('normal')

                $('div tr:eq(0) td:eq(1) span',this).click(function(){
                    if($(liObj).attr('class') == 'active') return
                    $(this).parent('td').children('span').attr('class','span-class')
                    $(this).attr('class','span-class2');
                    var v = $(this).text()
                    $('div tr:eq(1) td:eq(1) input',liObj).val(v == '��' ? 1 : 12)
                    $('div tr:eq(1) td:eq(1) span',liObj).html('��' + v)
                    initPrice(liObj)
                })

                $('.service-num',this).keyup(function(){
                    validNum(this,liObj)
                })
                $('.service-num',this).mousedown(function(){
                    validNum(this,liObj)
                })

                $('.curr-btn',this).click(function(){
                    if($('.service-num',liObj).attr('disabled')){
                        $(".curr-btn",liObj).html("<button>ȷ��</button>");
                        $('.service-num',liObj).attr('disabled',false)
                        $(liObj).attr('class', 'curr-btn');
                    }else{
                        $(".curr-btn",liObj).html("<button>ȡ��</button>");
                        $('.service-num',liObj).attr('disabled',true)
                        $(liObj).attr('class', 'curr-btn-save');
                    }
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
                var numcheck = /^[1-9][0-9]*$/;
                if(!numcheck.test(n)){
                    $('.service-num',li).val('1');
                }
                var t = $('.span-class2',li).html()
                var liId = $(li).attr('id')
                var serviceId = liId.substr(liId.indexOf('_')+1)
                for (var i = 0; i < dataArr.length; i++) {
                    if(serviceId == dataArr[i].id){
                        var price = dataArr[i].sysServicePriceList
                        for(var j=0;j<price.length;j++){
                            if(price[j].serviceTimeLimit == t){
                                var p = ((isNaN(price[j].servicePrice) ? 0 : (+price[j].servicePrice)) * n).toFixed(2)
                                $('div tr:eq(2) td:eq(1)',li).html(p + '��Ԫ')
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





