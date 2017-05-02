'use strict';
class Util {
    constructor() {}
    static getHtmlParameter(param) {
        var requestParam = "";
        var url = unescape(location.href);
        var paramArr = (url.substring(url.indexOf("?") + 1, url.length)).split("&");
        for(var i = 0; i < paramArr.length; i++) {
            var temp = paramArr[i].split("=");
            if(temp[0].toUpperCase() == param.toUpperCase()) {
                requestParam = paramArr[i].split("=")[1];
                break;
            }
        }
        return requestParam;
    }
    getElementId(ele) {
        var id_token = ele.split("_");
        return _.take(id_token, id_token.length - 1).join("_");
    }
}
// function setAspectRatio(img, expected) {
//   log.info("radio");
//   // No support for IE8 and lower
//   if (img.naturalWidth === 'undefined') return;
//   // Get natural dimensions of image
//   var width = img.naturalWidth;
//   var height = img.naturalHeight;
//   var new_width = 0;
//   var new_height = 0;
//   // Examine if width is larger than height then reduce the width but fix the height
//   if (width > height) {
//     img.style.width = (width / height * expected) + 'px';
//     img.style.height = expected + 'px';
//     // horizontally center the image
//     img.style.transform = 'translatex(-' + parseInt((width / height * expected) - expected) + 'px)';
//     img.style.webkitTransform = 'translateX(-' + parseInt((width / height * expected) - expected) + 'px)';
//   }
//   // Examine if height is larger than width then reduce the height but fix the width
//   else if (height > width) {
//     img.style.width = expected + 'px';
//     img.style.height = (height / width * expected) + 'px';
//     // vertically center the image
//     img.style.transform = 'translateY(-' + parseInt((height / width * expected) - expected) + 'px)';
//     img.style.webkitTransform = 'translateY(-' + parseInt((height / width * expected) - expected) + 'px)';
//   }
//   // Or return fix width and height
//   else {
//     img.style.width = expected + 'px';
//     img.style.height = expected + 'px';
//   }
//   log.info(img.style.width);
//   log.info(img.style.height);
// }
// function createRandomData() {
//   var result = [];
//   result[0] = ['data1', _.random(100), _.random(100), _.random(100), _.random(100), _.random(100)];
//   result[1] = ['data2', _.random(100), _.random(100), _.random(100), _.random(100), _.random(100)];
//   return result;
// }