'use strict';
class ViewUtil {
    constructor() {}
    static createUserRenderer(ele) {
        var me = ele;
        return {
            active(cell) {
                if(cell.data == 1) {
                    cell.element.innerHTML = '<span><i class="inverted wifi icon" style="color:green"></i></span>';
                } else {
                    cell.element.innerHTML = '<span><i class="inverted wifi icon" style="color:gray;opacity:0.5"></i></span>';
                }
            },
            id(cell) {
                cell.element.innerHTML = '';
                var node = document.createElement('span');
                node.textContent = cell.data;
                node.style.align = "center";
                node.style.textAlign = "center";
                node.style.fontWeight = "bold";
                node.style.color = "#106cc8";
                node.style.cursor = "pointer";
                node.style.textDecoration = "underline";
                node.value = cell.data;
                node.addEventListener("click", function(e) {
                    var value = e.target.value;
                    me.popupUserInfo(value);
                });
                // var node = document.createElement('paper-button');
                // //me.toggleClass("grid-column-primary", true, child);
                // //node.classList.add("grid-column-primary");
                // //log.info(node.classList.length);
                // //log.info(Polymer.dom(node).node.classList.length);
                // node.style.height = "20px";
                // node.style.color = "white";
                // node.style.backgroundColor = "#106cc8";
                // node.noink = true;
                // node.value = cell.data;
                // node.textContent = cell.data;
                cell.element.appendChild(node);
            },
            name(cell) {
                cell.element.innerHTML = '<span class="status">' + cell.data + '</span>';
            },
            level(cell) {
                cell.element.innerHTML = '';
                var node = document.createElement('paper-progress');
                node.style.width = "100%";
                node.value = cell.data * 10;
                cell.element.appendChild(node);
            }
        }
    }
}