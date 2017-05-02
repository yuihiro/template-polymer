class ViewController {
    constructor() {
        this.view_container = app.main_container;
        this.popup_container = app.popup_container;
        this.prev_view = null;
        this.prev_view_id = null;
        this.current_view = null;
        this.current_view_id = null;
    }
    switchView($id, $title, $sub_title) {
        this.prev_view_id = this.current_view_id;
        this.current_view_id = $id;
        if(this.current_view) {
            this.prev_view = this.current_view;
            this.removeElement(this.prev_view);
        }
        if($title == undefined) {
            var node = app.menu_container.querySelector("a[value=" + $id + "]");
            if(node != null) {
                $title = node.textContent;
                $sub_title = node.getAttribute("sub-title");
            }
        }
        this.current_view = this.createView(this.current_view_id);
        this.current_view.title = $title;
        this.current_view.subTitle = $sub_title;
        this.current_view.classList.add('view');
        return this.current_view;
        //Polymer.dom(this.current_view).classList.add("view");
    }
    createView($element_id) {
        return this.createElement($element_id, this.view_container)
    }
    createElement($element_id, $parent_node) {
        var element = document.createElement($element_id);
        $parent_node.appendChild(element);
        return element;
    }
    removeElement($element) {
        $element.parentNode.removeChild($element);
    }
    createPopup($element_id) {
        var popup = this.createElement($element_id, this.popup_container);
        popup.addEventListener('ready', () => {
            log.info("엥");
        });
        popup.classList.add('popup');
        return popup;
    }
    removePop($element) {
        this.removeElement($element);
    }
    fadeIn($element, $func) {
        $element.style.opacity = 0;
        $element.style.visibility = "visible";
        TweenMax.to($element, 1, {
            opacity: 1,
            delay: 0.5,
            onComplete() {
                if($func) {
                    $func.apply();
                }
            }
        });
    }
    fadeOut($element, $func) {
        var me = this;
        $element.style.opacity = 1;
        TweenMax.to($element, 0.5, {
            opacity: 0,
            delay: 0,
            onComplete() {
                if($func) {
                    $func.apply();
                }
                me.removeElement($element);
            }
        });
    }
    refresh() {
        this.current_view.refresh();
    }
    resize() {
        //log.info("[app] resize " + this.offsetWidth + "/" + this.offsetHeight);
    }
    defaultPopup(popup) {
        popup.noOverlap = false;
        popup.noAutoFocus = false;
        popup.alwaysOnTop = false;
        popup.withBackdrop = true;
        popup.modal = true;
        //popup.canceled = true;
        popup.noCancelOnOutsideClick = true;
        popup.noCancelOnEscKey = false;
        //popup.autoFitOnAttach = true
    }
    /*
    	    loadElement($element_file, $element_id, $parent_node) {
                Polymer.Base.importHref($element_file, function(e) {
                    return this.createElement($element_id, $parent_node);
                });
            }
            removeChildElements($element) {
                if($element.hasChildNodes()) {
                    while($element.childNodes.length >= 1) {
                        $element.removeChild($element.firstChild);
                    }
                }
            }
        scrollPageToTop() {
            //this.$.headerPanelMain.scrollToTop(true);
        };
        closeDrawer() {
            //this.$.paperDrawerPanel.closeDrawer();
        }
    	*/
}