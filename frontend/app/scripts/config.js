'use strict';
class Config {
    constructor() {
        log.setDefaultLevel("debug");
        this.mask = app.mask;
        this.base_url = '/';
        this.ajax_url = "http://localhost:9000/api/";
        this.ajax_timeout = 50000;
        this.ajax_after_delay = 0.5;
        this.init_view_id = "test-view";
        //this.configPace();
        this.configAjax();
        this.configRoute();
        this.configAlert();
    }
    loadConfig() {}
    configAlert() {
        alertify.dialog('alert').set({
            label: '확인',
            transition: 'fade',
            title: "에러",
            movable: false,
            closable: true,
            modal: true
        });
        alertify.dialog('confirm').set({
            labels: {
                ok: '확인',
                cancel: '취소'
            },
            transition: 'fade',
            title: "에러",
            movable: false,
            closable: true,
            modal: true
        });
    }
    // configPace() {
    //     Pace.on("start", () => {
    //         app.$.mask.classList.add('show');
    //         //me.menu_box.startLoading();
    //     });
    //     Pace.on("restart", () => {
    //         //me.menu_box.startLoading();
    //     });
    //     Pace.on("stop", (e) => {
    //         TweenMax.delayedCall(this.ajax_after_delay, function() {
    //             app.$.mask.classList.remove('show');
    //         });
    //         //me.menu_box.endLoading();
    //     });
    //     Pace.on("done", () => {
    //         //me.menu_box.endLoading();
    //     });
    // }
    configAjax() {
        var me = this;
        axios.defaults.baseURL = this.ajax_url;
        axios.defaults.timeout = this.ajax_timeout;
        axios.defaults.withCredentials = true;
        //axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
        //axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        axios.defaults.paramsSerializer = function(params) {
            if(data instanceof FormData) {
                return data;
            }
            return Qs.stringify(params, {
                arrayFormat: 'brackets'
            })
        };
        axios.defaults.transformRequest = [function(data) {
            if(data instanceof FormData) {
                return data;
            }
            return Qs.stringify(data, {
                arrayFormat: 'brackets'
            })
        }];
        axios.interceptors.request.use(function(config) {
            // 상태바 데이터 업데이트 경우에는 SKIP
            if(String(config.url).indexOf("getStatusData") == -1) {
                me.beforeAjax();
            }
            return config;
        }, function(error) {
            return Promise.reject(error);
        });
        axios.interceptors.response.use(function(response) {
            me.afterAjax();
            return response;
        }, function(error) {
            me.afterAjax(0);
            return me.handleAjaxError(error);
        });
    }
    beforeAjax() {
        //Pace.start();
        app.$.mask.classList.add('show');
    }
    afterAjax($delay) {
        //Pace.stop();
        var delay = ($delay == undefined) ? this.ajax_after_delay : $delay;
        delay = delay * 1000;
        _.delay(function() {
            app.$.mask.classList.remove('show');
        }, delay);
    }
    handleAjaxError(error) {
        if(error.response && error.response.status == 401) {
            error = "세션이 종료되었습니다."
            app.async(function() {
                location.reload(true);
            }, 2000);
        } else {
            if(error.message.indexOf("Network Error") != -1) {
                app.showAlert("에러", "서버에 접속할수 없습니다.");
            } else {
                app.showAlert("에러", error.message);
            }
        }
        return Promise.reject(error);
    }
    configRoute() {
        if(window.location.port === '') { // if production
            page.base(this.base_url.replace(/\/$/, ''));
        }
        page(this.base_url, () => {});
        page('*', () => {
            if(this.base_url) {
                page.redirect(this.base_url);
            } else {
                page.redirect("/");
            }
            // app.showAlert("에러", "잘못된 경로입니다.");
            // TweenLite.delayedCall(0.5, function() {
            //     page.redirect(app.base_url);
            // });
        });
        // add #! before urls
        page({
            hashbang: false
        });
        // function loadData(ctx, next) {
        // 	var id = ctx.params.id
        // 	$.getJSON('/user/' + id + '.json', function(user) {
        // 		ctx.user = user;
        // 		next();
        // 	});
        // }
        // // Middleware
        // function scrollToTop(ctx, next) {
        // 	app.scrollPageToTop();
        // 	next();
        // }
        // function closeDrawer(ctx, next) {
        // 	app.closeDrawer();
        // 	next();
        // }
        // function setFocus(selected) {
        // 	document.querySelector('section[data-route="' + selected + '"] .page-title').focus();
        // }
        // function changeRoute(ctx, next) {
        // 	app.current_url = ctx.path;
        // 	next();
        // }
        // // Routes
        // page('*', changeRoute, function(ctx, next) {
        // 	next();
        // });
        // page.exit('*', function(ctx, next) {
        // 	next();
        // });
        // page('/', function() {
        // 	app.route = 'home';
        // });
        // page(app.base_url, function() {
        // 	app.route = 'home';
        // });
        // page('/users', function() {
        // 	app.route = 'users';
        // });
        // page('/users/:name', function(data) {
        // 	app.route = 'user-info';
        // 	app.params = data.params;
        // });
        // page('/dom', function() {
        // 	app.route = 'dom';
        // });
        // page('/admin', function() {
        // 	app.route = 'admin';
        // });
        // page('/map', function() {
        // 	app.route = 'map';
        // });
    }
}