'use strict';

(function () {
    function init() {
        var router = new Router([
            new Route('times', 'times'),
            new Route('calendar', 'calendar'),
            new Route('people', 'people', 'people.js'),
            new Route('about', 'about.html')
        ]);
    }
    init();
}());