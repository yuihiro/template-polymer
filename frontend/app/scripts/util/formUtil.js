'use strict';
class FormUtil {
    constructor() {}
    static setFormData(element, data) {
        var el = null;
        _.forOwn(data, function(value, label) {
            el = element.$["f_" + label];
            if(!el) {
                //log.info("[setFormData] no element id : " + label);
                return;
            }
            if(el.localName == "paper-checkbox") {
                value = DataUtil.toBoolean(value);
                el.checked = value;
            } else if(el.localName == "label") {
                el.textContent = value;
            } else if(el.localName == "nagi-radio") {
                el.value = DataUtil.toBoolean(value);
            } else {
                el.value = value;
            }
        });
    }
    static getFormData(element) {
        var result = {};
        var elements = element.$;
        _.each(elements, function(item) {
            var token = item.id.substr(0, 2);
            if(token != "f_") {
                return;
            }
            var label = item.id.substr(2, item.id.length);
            if(item.localName == "paper-checkbox") {
                result[label] = item.checked;
            } else {
                if(!item.readonly && !item.disabled) {
                    if(typeof item.getValue === "function") {
                        result[label] = item.getValue();
                    } else {
                        result[label] = item.value;
                    }
                }
            }
        })
        return result;
    }
    static getLikeStr(str) {
        if(str.length == 0) {
            return "";
        }
        return "%" + str + "%";
    }
    static validateData(data, option) {
        var result = validate(data, option);
        if(result) {
            var message = "";
            _.forIn(result, function(value, key) {
                message = value.toString();
                //message += value.toString() + "<br />";
                app.showAlert("알림", message);
                return false;
            });
        } else {
            return true;
        }
    }
}