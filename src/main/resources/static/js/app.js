'use strict';

(function () {
    function init() {
        var router = new Router([
            new Route('people', 'people', 'people.js'),
            new Route('about', 'about.html')
        ]);
    }
    init();
}());