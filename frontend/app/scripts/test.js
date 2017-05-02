'use strict';
class Test {
    static view() {
        // var popup = app.view.createPopup("user-info-pop");
        // popup.init("test");
        app.view.removeElement(app.$.login_view);
        app.view.switchView("config-view");
        app.$.app_container.style.display = "block";
    }
    static test() {
        var text = "adminme";
        var key = "ANYCLICK-KO-100001";
        var encrypted = CryptoUtil.encrypt(text, key);
        log.info("encrypted : " + encrypted);
        var decrypted = CryptoUtil.decrypt(encrypted, key);
        log.info("decrypted : " + decrypted);
    }
}
// var nodes = [{
// 	"id": "0",
// 	"parent_id": "0",
// 	"depth": 0,
// 	"children": null
// }, {
// 	"id": "1",
// 	"parent_id": "0",
// 	"depth": 1,
// 	"children": null
// }, {
// 	"id": "2",
// 	"parent_id": "1",
// 	"depth": 2,
// 	"children": null
// }];
// var map = {},
// 	node, roots = [];
// for (var i = 0; i < nodes.length; i += 1) {
// 	node = nodes[i];
// 	node.children = [];
// 	map[node.id] = node;
// 	if (node.depth != 0) {
// 		log.info(node.id);
// 		log.info(map[node.parent_id]);
// 		map[node.parent_id].children.push(node);
// 	} else {
// 		roots.push(node);
// 	}
// }
// log.info("map");
// log.info(map);
// log.info("result");
// log.info(roots[0].children);
// log.info(roots[0].children[0]);