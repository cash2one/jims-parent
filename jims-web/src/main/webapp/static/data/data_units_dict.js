var units = [{"value": "1", "text": "毫升"}, {"value": "2", "text": "单位"}, {"value": "3", "text": "人/份"}];
/**
 * 血量单位翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function unitsFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < units.length; i++) {
        if (units[i].value == value) {
            return units[i].text;
        }
    }
}