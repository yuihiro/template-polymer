'use strict';
class DataUtil {
    constructor() {}
    static addDefaultLabel(data) {
        var item = {
            label: "-",
            value: "",
            name: "-",
            id: "",
        }
        return _.fill(data, item, 0, 1);
    }
    static addAllLabel(data) {
        var item = {
            label: "ALL",
            value: "-",
        }
        return _.union([item], data);
    }
    static convertData(data, label_col, value_col) {
        if(_.isArray(data) == false || data.length === 0) {
            return [];
        }
        var total = data.length;
        _.each(data, function(item) {
            try {
                item.label = item[label_col];
                item.value = item[value_col];
            } catch(e) {}
        });
    }
    static mapToTreeData(data) {
        var map = {};
        var result = [];
        _.each(data, function(item) {
            map[item.id] = item;
            if(item.depth != 0) {
                map[item.parent_id].folder = true;
                if(map[item.parent_id].children == null) {
                    map[item.parent_id].children = [];
                }
                map[item.parent_id].children.push(item);
            } else {
                result.push(item);
            }
        });
        return result;
    }
    static toBoolean(value) {
        if(typeof value == "string") {
            if(value == "true" || value == "YES" || value == "1" || value == "ON") {
                return true;
            }
        }
        if(typeof value == "number") {
            if(value == 1) {
                return true;
            }
        }
        return false;
    }
}