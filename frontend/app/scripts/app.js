(function(window) {
    'use strict';
    var webComponentsSupported = ('registerElement' in document && 'import' in document.createElement('link') && 'content' in document.createElement('template'));
    console.log("webComponentsSupported : " + webComponentsSupported);
    if(!webComponentsSupported) {
        // var script = document.createElement('script');
        // script.async = true;
        // script.src = '/bower_components/webcomponentsjs/webcomponents-lite.min.js';
        // script.onload = load;
        // document.head.appendChild(script);
        var body = document.querySelector('body')
        body.innerHTML = "<h2 style='color:white;text-align:center'>해당 브라우저는 지원되지 않습니다. 크롬 최신버전을 사용해주세요.</h2>";
    } else {
        load();
    }

    function load() {
        console.log("load");
        var link = document.querySelector('#element');
        if(link.import && link.import.readyState === 'complete') {
            loadComplete();
        } else {
            link.addEventListener('load', loadComplete);
            link.addEventListener('error', loadError);
        }
    }

    function loadError() {
        alert.show("서버에 접속할 수 없습니다.");
    }

    function loadComplete() {
        console.log('loadComplete');
        // window.Polymer = {
        //     dom: 'shady',
        //     lazyRegister: false
        // };
        createApp();
    }

    function createApp() {
        console.log('createApp');
        var element = document.createElement("app-view");
        element.id = "app";
        element.classList.add('view');
        document.body.appendChild(element);
    }
})(window);