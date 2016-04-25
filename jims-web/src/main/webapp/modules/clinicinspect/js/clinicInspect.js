function onloadMethod() {
    $(function () {
        var province = $('#province').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false,
          //  url: ,
            onChange: function (newValue, oldValue) {
                $.get('cascade-data.php', {parentid: newValue}, function (data) {
                    city.combobox("clear").combobox('loadData', data);
                    county.combobox("clear")
                }, 'json');
            }
        });
        var city = $('#city').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false,
            onChange: function (newValue, oldValue) {
                $.get('cascade-data.php', {parentid: newValue}, function (data) {
                    county.combobox("clear").combobox('loadData', data);
                }, 'json');
            }
        });
        var county = $('#county').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false
        });
    });

}