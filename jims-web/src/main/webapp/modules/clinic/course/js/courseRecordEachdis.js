/**
 * Created by qinlongxin on 2016/4/22.
 */
function saveEachdis() {
    formSubmitInput("eachdisForm");
    $.postForm(basePath + "/courseRecordeachdis/save", "eachdisForm", function (data) {
        if (data.code == "1") {
            $.messager.alert("��ʾ��Ϣ", "����ɹ�");
        } else {
            $.messager.alert("��ʾ��Ϣ", "����ʧ��", "error");
        }

    }), function (data) {
        $.messager.alert("��ʾ��Ϣ", "����ʧ��", "error");
    }
}

